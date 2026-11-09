package com.sofodev.armorplus.registry.item.tool;

import com.sofodev.armorplus.api.RuneEffectType;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.item.extra.DeBuff;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import com.sofodev.armorplus.registry.item.extra.ToolBuff;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * The tool effects a player gets: the tool runes socketed into the tool held in the main hand (effects such as Haste and
 * {@link ToolBuff} abilities such as Auto-Smelt).
 * <p>
 * Separate from the armor effects ({@link ArmorEffects}): tool effects have their
 * own screen (Tool Effects key) and toggles, and never count as set effects. Switching items is an equipment change, so the
 * cache is rebuilt then ({@link #invalidate(Player)}).
 */
public final class ToolEffects {

    private static final Map<Player, List<BuffInstance>> ACTIVE = Collections.synchronizedMap(new WeakHashMap<>());
    private static final Map<Player, Set<IBuff>> ACTIVE_BUFFS = Collections.synchronizedMap(new WeakHashMap<>());

    private ToolEffects() {
    }

    /**
     * One effect of the held tool's runes.
     *
     * @param id       the buff id, also the key saved when the player switches it off, e.g. "minecraft:haste"
     * @param disabled switched off by the player
     */
    public record Entry(BuffInstance buff, String id, boolean disabled) {
    }

    public static void invalidate(Player player) {
        Runes.invalidate(player);
        ACTIVE.remove(player);
        ACTIVE_BUFFS.remove(player);
    }

    public static void clearCaches() {
        Runes.clearCaches();
        ACTIVE.clear();
        ACTIVE_BUFFS.clear();
    }

    /** @return every effect of the held tool's runes with its status */
    public static List<Entry> entries(Player player) {
        List<Entry> entries = new ArrayList<>();
        for (BuffInstance buff : Runes.heldToolRunes(player)) {
            String id = BuffParser.idOf(buff.getBuff());
            entries.add(new Entry(buff, id, isDisabled(player, id)));
        }
        return List.copyOf(entries);
    }

    /** @return the effects the player gets from the held tool right now (cached) */
    public static List<BuffInstance> active(Player player) {
        return ACTIVE.computeIfAbsent(player, p -> entries(p).stream().filter(e -> !e.disabled()).map(Entry::buff).toList());
    }

    /** @return the active effects applied to the player while the tool is held (everything but the on-hit {@link DeBuff}s) */
    public static List<BuffInstance> held(Player player) {
        return active(player).stream().filter(instance -> !(instance.getBuff() instanceof DeBuff)).toList();
    }

    /**
     * @return the active on-hit {@link DeBuff}s, applied to the mobs hit with the tool like weapon effects, and the
     * {@link RuneEffectType}s (their {@link RuneEffectType#onHit} hook)
     */
    public static List<BuffInstance> onHit(Player player) {
        return active(player).stream().filter(instance -> instance.getBuff() instanceof DeBuff || instance.getBuff() instanceof RuneEffectType).toList();
    }

    /** @return true if the held tool gives the player the buff (ability) right now (cached) */
    public static boolean has(Player player, IBuff buff) {
        return ACTIVE_BUFFS.computeIfAbsent(player, p -> {
            Set<IBuff> buffs = new HashSet<>();
            active(p).forEach(instance -> buffs.add(instance.getBuff()));
            return Set.copyOf(buffs);
        }).contains(buff);
    }

    public static boolean isDisabled(Player player, String id) {
        return ArmorPlusConfig.allowEffectToggles.get() && player.getData(ModAttachments.DISABLED_TOOL_EFFECTS.get()).contains(id);
    }
}
