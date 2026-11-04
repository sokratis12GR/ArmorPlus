package com.sofodev.armorplus.registry.item.armor;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ArmorPlusConfig.MaterialConfig;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.extra.Buff;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffNames;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.item.extra.DeBuff;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.Nullable;

import java.util.*;

/**
 * The armor effects a player gets, from the worn ArmorPlus sets and the socketed armor runes. Tool runes are separate, see
 * {@link com.sofodev.armorplus.registry.item.tool.ToolEffects}.
 * <ul>
 *     <li>A set effect needs the pieces of its material given by its {@link SetRequirement}: the "@..." part of its entry
 *     (e.g. "@head" or "@2"), otherwise the effect's "effectSlots" default, otherwise "defaultSetPieces" (4 = full set).
 *     Pieces of different materials each count for their own material.</li>
 *     <li>Players can switch effects off per set (and for their runes) in the Armor Effects screen ("allowEffectToggles").</li>
 *     <li>When several sources give the same effect, the highest level counts.</li>
 * </ul>
 * The active effects are cached per player and only rebuilt when the equipment, the toggles or the config change.
 */
public final class ArmorEffects {

    public static final int SET_SIZE = 4;
    /** Toggle source of socketed runes; sets use their config name, e.g. "coal" or "enhanced_iron". */
    public static final String RUNE_SOURCE = "rune";

    /** Weak keys, so a respawned player (a new entity) starts fresh. */
    private static final Map<Player, List<Entry>> ACTIVE = Collections.synchronizedMap(new WeakHashMap<>());
    private static final Map<Player, Set<IBuff>> ACTIVE_BUFFS = Collections.synchronizedMap(new WeakHashMap<>());

    private ArmorEffects() {
    }

    /**
     * One effect of one source (a worn set, or the socketed runes).
     *
     * @param source   the set's config name or {@link #RUNE_SOURCE}
     * @param required worn pieces the effect needs, {@code worn} how many count (slot bound effects: of their pieces, runes: 1 of 1)
     * @param slots    the pieces a slot bound effect needs (all of them), empty if it uses the piece count
     * @param disabled switched off by the player for this source
     */
    public record Entry(BuffInstance buff, String id, String source, int required, int worn, Set<EquipmentSlot> slots,
                        boolean disabled) {

        public boolean available() {
            return worn >= required;
        }

        public boolean active() {
            return available() && !disabled;
        }

        /** @return the key saved when the player switches this effect off, e.g. "coal|minecraft:night_vision" */
        public String toggleKey() {
            return ArmorEffects.toggleKey(source, id);
        }
    }

    public static String toggleKey(String source, String id) {
        return source + "|" + id;
    }

    public static void invalidate(Player player) {
        ACTIVE.remove(player);
        ACTIVE_BUFFS.remove(player);
        Runes.invalidate(player);
    }

    public static void clearCaches() {
        ACTIVE.clear();
        ACTIVE_BUFFS.clear();
        Runes.clearCaches();
    }

    /** @return the effects the player gets right now, one per effect, the highest level of all sources (cached) */
    public static List<BuffInstance> active(Player player) {
        return activeEntries(player).stream().map(Entry::buff).toList();
    }

    /** @return like {@link #active(Player)}, with the source each effect comes from (cached) */
    public static List<Entry> activeEntries(Player player) {
        return ACTIVE.computeIfAbsent(player, p -> {
            Map<String, Entry> best = new LinkedHashMap<>();
            for (Entry entry : entries(p)) {
                if (entry.active()) best.merge(entry.id(), entry, (a, b) -> b.buff().getAmplifier() > a.buff().getAmplifier() ? b : a);
            }
            return List.copyOf(best.values());
        });
    }

    /** @return true if the player gets the buff (ability) right now (cached) */
    public static boolean has(Player player, IBuff buff) {
        return ACTIVE_BUFFS.computeIfAbsent(player, p -> {
            Set<IBuff> buffs = new HashSet<>();
            active(p).forEach(instance -> buffs.add(instance.getBuff()));
            return Set.copyOf(buffs);
        }).contains(buff);
    }

    public static boolean isDisabled(Player player, String toggleKey) {
        return ArmorPlusConfig.allowEffectToggles.get() && player.getData(ModAttachments.DISABLED_EFFECTS.get()).contains(toggleKey);
    }

    /**
     * @return every effect of the worn sets (including the ones that need more pieces) and of the socketed runes, with its
     * status, grouped by source (sets in slot order, then runes)
     */
    public static List<Entry> entries(Player player) {
        Map<EquipmentSlot, IAPArmor> bySlot = wornMaterials(player);

        Map<String, Entry> entries = new LinkedHashMap<>();
        for (IAPArmor mat : new LinkedHashSet<>(bySlot.values())) {
            if (!(mat.config() instanceof MaterialConfig cfg) || !cfg.enableArmorEffects().get()) continue;
            for (BuffInstance buff : mat.getBuffInstances().get()) {
                if (buff.getBuff() == Buff.NONE || buff.getBuff() instanceof DeBuff || !buff.isEnabled()) continue;
                String id = BuffParser.idOf(buff.getBuff());
                SetRequirement requirement = requirement(buff);
                add(entries, new Entry(buff, id, cfg.name(), requirement.required(), requirement.worn(bySlot, mat),
                        requirement.slots(), isDisabled(player, toggleKey(cfg.name(), id))));
            }
        }
        for (BuffInstance buff : Runes.wornArmorRunes(player)) {
            String id = BuffParser.idOf(buff.getBuff());
            add(entries, new Entry(buff, id, RUNE_SOURCE, 1, 1, Set.of(), isDisabled(player, toggleKey(RUNE_SOURCE, id))));
        }        return List.copyOf(entries.values());
    }

    /** One entry per source and effect: the higher level wins. */
    private static void add(Map<String, Entry> entries, Entry entry) {
        entries.merge(entry.toggleKey(), entry, (a, b) -> b.buff().getAmplifier() > a.buff().getAmplifier() ? b : a);
    }

    /** @return the ArmorPlus material worn in each armor slot (slots without ArmorPlus armor are missing) */
    public static Map<EquipmentSlot, IAPArmor> wornMaterials(Player player) {
        Map<EquipmentSlot, IAPArmor> bySlot = new EnumMap<>(EquipmentSlot.class);
        for (EquipmentSlot slot : Runes.ARMOR_SLOTS) {
            if (player.getItemBySlot(slot).getItem() instanceof APArmorItem armor) bySlot.put(slot, armor.getMat());
        }
        return bySlot;
    }

    /**
     * The pieces a set effect needs: the entry's own requirement ("@head", "@2", ...), otherwise the effect's
     * "effectSlots" default, otherwise "defaultSetPieces".
     */
    public static SetRequirement requirement(BuffInstance buff) {
        if (buff.getRequirement() != null) return buff.getRequirement();
        Set<EquipmentSlot> slots = BuffNames.effectSlots(BuffParser.idOf(buff.getBuff()));
        return slots.isEmpty() ? SetRequirement.pieces(ArmorPlusConfig.defaultSetPieces.get()) : SetRequirement.slots(slots);
    }

    /**
     * @param player the player whose worn armor counts, null counts nothing
     * @return the "[3/4]" style progress of the material's buff for tooltips: the needed pieces of the set the player wears
     * out of the pieces it needs ("[0/1]" = needs this piece, "[1/2]" = this and one other piece, one is worn, ...)
     */
    public static Component progress(@Nullable Player player, IAPArmor mat, BuffInstance buff) {
        SetRequirement requirement = requirement(buff);
        int worn = player == null ? 0 : requirement.worn(wornMaterials(player), mat);
        boolean met = worn >= requirement.required();
        return Component.literal("[" + worn + "/" + requirement.required() + "]").withStyle(met ? ChatFormatting.GREEN : ChatFormatting.GRAY);
    }

    /** @return true if the buff should be listed on the piece in this slot (slot bound effects only on their own pieces) */
    public static boolean shownOn(BuffInstance buff, EquipmentSlot pieceSlot) {
        return requirement(buff).shownOn(pieceSlot);
    }
}
