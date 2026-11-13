package com.sofodev.armorplus.registry.item.armor;

import com.sofodev.armorplus.api.EffectEntry;
import com.sofodev.armorplus.api.EffectScope;
import com.sofodev.armorplus.api.EffectScopes;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ArmorPlusConfig.MaterialConfig;
import com.sofodev.armorplus.events.ModGlobalEvents;
import com.sofodev.armorplus.events.RuneEvents;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.extra.Buff;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffNames;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.item.extra.DeBuff;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.Nullable;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The armor effects a player gets, from the worn ArmorPlus sets and the socketed armor runes. Tool runes are separate, see
 * {@link ToolEffects}.
 * <ul>
 *     <li>A set effect needs the pieces of its material given by its {@link SetRequirement}: the "@..." part of its entry
 *     (e.g. "@head" or "@2"), otherwise the effect's "effectSlots" default, otherwise "defaultSetPieces" (4 = full set).
 *     Pieces of different materials each count for their own material.</li>
 *     <li>Players can switch effects off per set (and for their runes) in the Armor Effects screen ("allowEffectToggles").</li>
 *     <li>An effect upgraded on the pieces counts at the lowest level of the pieces it needs.</li>
 *     <li>When several sources give the same effect, the highest level counts.</li>
 * </ul>
 * The active effects are cached per player and only rebuilt when the equipment, the toggles or the config change.
 */
public final class ArmorEffects {

    public static final int SET_SIZE = 4;

    /**
     * The armor scope, use {@link EffectScopes#ARMOR}. Sources are the set's config name, e.g. "coal" or "enhanced_iron", or
     * {@link EffectEntry#RUNE}; toggle keys are "source|effect", e.g. "coal|minecraft:night_vision". The active effects are one
     * per effect, the highest level of all sources (cached).
     */
    public static final EffectScope SCOPE = new Scope();

    private ArmorEffects() {
    }

    private static final class Scope extends EffectScope {

        private Scope() {
            super("armor", ModAttachments.DISABLED_EFFECTS, true);
        }

        /** @return e.g. "coal|minecraft:night_vision": each set (and the runes) is switched off on its own */
        @Override
        public String toggleKey(String source, String id) {
            return source + "|" + id;
        }

        /** Sets in slot order, then runes; one entry per source and effect, the higher level wins. */
        @Override
        protected List<EffectEntry> collect(LivingEntity player) {
            Map<String, EffectEntry> entries = new LinkedHashMap<>();
            for (IAPArmor mat : new LinkedHashSet<>(wornMaterials(player).values())) {
                if (!(mat.config() instanceof MaterialConfig cfg) || !cfg.enableArmorEffects().get()) continue;
                for (BuffInstance buff : mat.getBuffInstances().get()) {
                    if (buff.getBuff() == Buff.NONE || buff.getBuff() instanceof DeBuff || !buff.isEnabled()) continue;
                    String id = BuffParser.idOf(buff.getBuff());
                    String key = toggleKey(cfg.name(), id);
                    SetRequirement requirement = requirement(buff);
                    add(entries, new EffectEntry(setBuff(player, mat, buff, requirement, id), id, cfg.name(), key, requirement.required(),
                            requirement.worn(wornMaterials(player, id), mat), requirement.slots(), isDisabled(player, key)));
                }
            }
            for (BuffInstance buff : Runes.wornArmorRunes(player)) add(entries, entry(player, buff, EffectEntry.RUNE));
            return List.copyOf(entries.values());
        }

        /** @return the set's config name, e.g. "coal", or "rune" */
        @Override
        public Component sourceName(EffectEntry entry) {
            return Component.literal(entry.source());
        }

        /** When several sources give the same effect, the highest level counts. */
        @Override
        protected List<EffectEntry> combine(List<EffectEntry> active) {
            return highestPerEffect(active);
        }

        @Override
        protected void onToggled(ServerPlayer player, Optional<BuffInstance> effect) {
            RuneEvents.syncEffects(player, effect.map(List::of).orElse(List.of()));
            ModGlobalEvents.checkAndApplyBuffs(player);
        }

        @Override
        public void invalidate(LivingEntity entity) {
            super.invalidate(entity);
            Runes.invalidate(entity);
        }

        @Override
        public void clearCaches() {
            super.clearCaches();
            Runes.clearCaches();
        }

        private static void add(Map<String, EffectEntry> entries, EffectEntry entry) {
            entries.merge(entry.toggleKey(), entry, EffectScope::higher);
        }
    }


    /** @return the buff at the lowest level of the pieces that count for it, so upgrading it takes all of them */
    private static BuffInstance setBuff(LivingEntity player, IAPArmor mat, BuffInstance buff, SetRequirement requirement, String id) {
        int level = Integer.MAX_VALUE;
        for (Map.Entry<EquipmentSlot, IAPArmor> piece : wornMaterials(player, id).entrySet()) {
            if (piece.getValue() != mat || !(requirement.slots().isEmpty() || requirement.slots().contains(piece.getKey()))) continue;
            level = Math.min(level, Runes.nativeLevel(player.getItemBySlot(piece.getKey()), id));
        }
        return level == Integer.MAX_VALUE || level <= buff.getAmplifier() + 1 ? buff : buff.atLevel(level);
    }

    /** @return the ArmorPlus material worn in each armor slot (slots without ArmorPlus armor are missing) */
    public static Map<EquipmentSlot, IAPArmor> wornMaterials(LivingEntity player) {
        Map<EquipmentSlot, IAPArmor> bySlot = new EnumMap<>(EquipmentSlot.class);
        for (EquipmentSlot slot : Runes.ARMOR_SLOTS) {
            if (player.getItemBySlot(slot).getItem() instanceof APArmorItem armor) bySlot.put(slot, armor.getMat());
        }
        return bySlot;
    }

    /** @return like {@link #wornMaterials(LivingEntity)}, without the pieces the buff was extracted from: they no longer count for it */
    public static Map<EquipmentSlot, IAPArmor> wornMaterials(LivingEntity player, String buffId) {
        Map<EquipmentSlot, IAPArmor> bySlot = wornMaterials(player);
        bySlot.keySet().removeIf(slot -> Runes.isExtracted(player.getItemBySlot(slot), buffId));
        return bySlot;
    }

    /**
     * The pieces a set effect needs: the entry's own requirement ("@head", "@2", ...), otherwise the effect's
     * "effectSlots" default, otherwise "defaultSetPieces".
     */
    public static SetRequirement requirement(BuffInstance buff) {
        if (buff.getRequirement() != null) return buff.getRequirement();
        SetRequirement requirement = BuffNames.effectRequirement(BuffParser.idOf(buff.getBuff()));
        return requirement != null ? requirement : SetRequirement.pieces(ArmorPlusConfig.defaultSetPieces.get());
    }

    /**
     * @param player the player whose worn armor counts, null counts nothing
     * @return the "[3/4]" style progress of the material's buff for tooltips: the needed pieces of the set the player wears
     * out of the pieces it needs ("[0/1]" = needs this piece, "[1/2]" = this and one other piece, one is worn, ...)
     */
    public static Component progress(@Nullable Player player, IAPArmor mat, BuffInstance buff) {
        SetRequirement requirement = requirement(buff);
        int worn = player == null ? 0 : requirement.worn(wornMaterials(player, BuffParser.idOf(buff.getBuff())), mat);
        boolean met = worn >= requirement.required();
        return Component.literal("[" + worn + "/" + requirement.required() + "]").withStyle(met ? ChatFormatting.GREEN : ChatFormatting.GRAY);
    }

    /** @return true if the buff should be listed on the piece in this slot (slot bound effects only on their own pieces) */
    public static boolean shownOn(BuffInstance buff, EquipmentSlot pieceSlot) {
        return requirement(buff).shownOn(pieceSlot);
    }
}
