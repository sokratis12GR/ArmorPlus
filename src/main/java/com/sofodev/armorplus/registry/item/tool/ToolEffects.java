package com.sofodev.armorplus.registry.item.tool;

import com.sofodev.armorplus.api.EffectEntry;
import com.sofodev.armorplus.api.EffectScope;
import com.sofodev.armorplus.api.EffectScopes;
import com.sofodev.armorplus.api.RuneEffectType;
import com.sofodev.armorplus.events.RuneEvents;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.DeBuff;
import com.sofodev.armorplus.registry.item.extra.ToolBuff;
import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.item.tool.properties.tool.Tool;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The tool effects a player gets from the tool held in the main hand: its material's own effects
 * ({@link IAPTool#getHeldBuffs()}, e.g. Infused Lava's Auto-Smelt) and its socketed tool runes (effects such as Haste and
 * {@link ToolBuff} abilities such as Auto-Smelt).
 * <p>
 * Separate from the armor effects ({@link ArmorEffects}): tool effects have their
 * own screen (Tool Effects key) and toggles, and never count as set effects. Switching items is an equipment change, so the
 * cache is rebuilt then ({@link EffectScope#invalidate}).
 */
public final class ToolEffects {

    /** The tool scope, use {@link EffectScopes#TOOL}. Sources are {@link EffectEntry#MATERIAL} or {@link EffectEntry#RUNE}, toggle keys the effect id. */
    public static final EffectScope SCOPE = new Scope();

    private ToolEffects() {
    }

    private static final class Scope extends EffectScope {

        private Scope() {
            super("tool", ModAttachments.DISABLED_TOOL_EFFECTS, true);
        }

        @Override
        protected List<EffectEntry> collect(LivingEntity entity) {
            if (!(entity instanceof Player player)) return List.of();
            List<EffectEntry> entries = new ArrayList<>();
            // the held tool's own material effects (e.g. Infused Lava's Auto-Smelt), then its socketed tool runes
            if (player.getMainHandItem().getItem() instanceof Tool tool && tool.getMat().config().enableWeaponEffects().get()) {
                for (BuffInstance buff : tool.getMat().getHeldBuffs()) entries.add(entry(player, buff, EffectEntry.MATERIAL));
            }
            for (BuffInstance buff : Runes.heldToolRunes(player)) entries.add(entry(player, buff, EffectEntry.RUNE));
            return entries;
        }

        @Override
        protected void onToggled(ServerPlayer player, Optional<BuffInstance> effect) {
            RuneEvents.syncEffects(player, effect.map(List::of).orElse(List.of()));
        }

        @Override
        public void invalidate(LivingEntity entity) {
            Runes.invalidate(entity);
            super.invalidate(entity);
        }

        @Override
        public void clearCaches() {
            Runes.clearCaches();
            super.clearCaches();
        }
    }

    /** @return the active effects applied to the player while the tool is held (everything but the on-hit {@link DeBuff}s) */
    public static List<BuffInstance> held(Player player) {
        return SCOPE.active(player).stream().filter(instance -> !(instance.getBuff() instanceof DeBuff)).toList();
    }

    /**
     * @return the active on-hit {@link DeBuff}s, applied to the mobs hit with the tool like weapon effects, and the
     * {@link RuneEffectType}s (their {@link RuneEffectType#onHit} hook)
     */
    public static List<BuffInstance> onHit(Player player) {
        return SCOPE.active(player).stream().filter(instance -> instance.getBuff() instanceof DeBuff || instance.getBuff() instanceof RuneEffectType).toList();
    }
}
