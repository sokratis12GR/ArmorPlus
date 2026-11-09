package com.sofodev.armorplus.registry.item.extra;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.armor.SetRequirement;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;

import static com.sofodev.armorplus.utils.RomanNumeralUtil.generate;

/**
 * Display and slot rules of buffs, shared by both sides (tooltips, screens, the armor effect rules).
 */
public final class BuffNames {

    private BuffNames() {
    }

    /** @return false for abilities, level I and effects whose level makes no difference ("singleLevelEffects") */
    public static boolean showsLevel(BuffInstance buff) {
        return buff.getAmplifier() >= 1 && hasLevels(buff.getBuff());
    }

    /** @return false for abilities and effects whose level makes no difference ("singleLevelEffects") */
    public static boolean hasLevels(IBuff buff) {
        if (!buff.isEffect()) return buff.isLeveled();
        String id = BuffParser.idOf(buff);
        for (String single : ArmorPlusConfig.singleLevelEffects.get()) {
            if (BuffParser.idOf(single).equals(id)) return false;
        }
        return true;
    }

    /** @return " II", " III", ... or "" if the level isn't shown, see {@link #showsLevel(BuffInstance)} */
    public static String levelSuffix(BuffInstance buff) {
        return showsLevel(buff) ? " " + generate(buff.getAmplifier() + 1) : "";
    }

    /** @return the default pieces of the effect ("effectSlots" config), or null if it isn't listed */
    public static @Nullable SetRequirement effectRequirement(String buffId) {
        for (String entry : ArmorPlusConfig.effectSlots.get()) {
            String[] parts = entry.split("\\|", -1);
            if (parts.length == 2 && BuffParser.idOf(parts[0]).equals(buffId)) return SetRequirement.parse(parts[1]);
        }
        return null;
    }

    /**
     * @return the armor pieces the effect belongs to ("effectSlots" config), in slot order, or an empty set if it uses a
     * piece count instead
     */
    public static Set<EquipmentSlot> effectSlots(String buffId) {
        SetRequirement requirement = effectRequirement(buffId);
        return requirement == null ? Set.of() : requirement.slots();
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
