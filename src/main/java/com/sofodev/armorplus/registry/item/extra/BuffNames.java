package com.sofodev.armorplus.registry.item.extra;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import static com.sofodev.thedragonlib.util.RomanNumeralUtil.generate;

/**
 * Display and slot rules of buffs, shared by both sides (tooltips, screens, rune names, the armor effect rules).
 */
public final class BuffNames {

    private BuffNames() {
    }

    /** @return false for abilities, level I and effects whose level makes no difference ("singleLevelEffects") */
    public static boolean showsLevel(BuffInstance buff) {
        if (!buff.getBuff().isEffect() || buff.getAmplifier() < 1) return false;
        String id = BuffParser.idOf(buff.getBuff());
        for (String single : ArmorPlusConfig.singleLevelEffects.get()) {
            if (BuffParser.idOf(single).equals(id)) return false;
        }
        return true;
    }

    /** @return " II", " III", ... or "" if the level isn't shown, see {@link #showsLevel(BuffInstance)} */
    public static String levelSuffix(BuffInstance buff) {
        return showsLevel(buff) ? " " + generate(buff.getAmplifier() + 1) : "";
    }

    /**
     * @return the armor pieces the effect belongs to ("effectSlots" config), in slot order, or an empty set if it uses the
     * piece count instead (sets) / fits into any piece (runes)
     */
    public static Set<EquipmentSlot> effectSlots(String buffId) {
        for (String entry : ArmorPlusConfig.effectSlots.get()) {
            String[] parts = entry.split("\\|", -1);
            if (!BuffParser.idOf(parts[0]).equals(buffId)) continue;
            Set<EquipmentSlot> slots = EnumSet.noneOf(EquipmentSlot.class);
            for (String slot : parts[1].split(",", -1)) slots.add(EquipmentSlot.byName(slot.trim()));
            return slots;
        }
        return Set.of();
    }

    /** @return the translation key of an armor slot, e.g. "armorplus.slot.head" */
    public static String slotKey(EquipmentSlot slot) {
        return "armorplus.slot." + slot.getName();
    }

    /** @return the slot names joined with ", ", e.g. "Leggings, Boots" */
    public static Component slotNames(Collection<EquipmentSlot> slots) {
        MutableComponent names = Component.empty();
        boolean first = true;
        for (EquipmentSlot slot : slots) {
            if (!first) names.append(", ");
            names.append(Component.translatable(slotKey(slot)));
            first = false;
        }
        return names;
    }
}
