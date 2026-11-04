package com.sofodev.armorplus.registry.item.armor;

import net.minecraft.world.entity.EquipmentSlot;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * What a set effect needs to be active: either specific armor pieces of the set (all of them), or a number of worn pieces
 * of the set in any slots.
 * <p>
 * In config entries it's the optional {@code "@..."} suffix of a buff entry (see
 * {@link com.sofodev.armorplus.registry.item.extra.BuffParser}):
 * <ul>
 *     <li>{@code "minecraft:night_vision|1@head"} - the helmet of the set</li>
 *     <li>{@code "minecraft:conduit_power|2@legs,feet"} - the leggings and the boots of the set</li>
 *     <li>{@code "minecraft:haste|2@2"} - any 2 pieces of the set</li>
 * </ul>
 * Entries without a suffix use the "effectSlots" default of the effect, otherwise "defaultSetPieces".
 *
 * @param slots  the pieces the effect needs (all of them), empty if it uses the piece count
 * @param pieces the number of worn pieces it needs if {@code slots} is empty, 1 - {@link ArmorEffects#SET_SIZE}
 */
public record SetRequirement(Set<EquipmentSlot> slots, int pieces) {

    private static final List<String> SLOT_NAMES = List.of("head", "chest", "legs", "feet");

    public SetRequirement {
        slots = slots.isEmpty() ? Set.of() : Collections.unmodifiableSet(EnumSet.copyOf(slots));
        pieces = slots.isEmpty() ? Math.clamp(pieces, 1, ArmorEffects.SET_SIZE) : slots.size();
    }

    /** @return a requirement of these pieces of the set, e.g. {@code slots(EquipmentSlot.HEAD)} */
    public static SetRequirement slots(EquipmentSlot... slots) {
        return new SetRequirement(slots.length == 0 ? Set.of() : EnumSet.of(slots[0], slots), 0);
    }

    public static SetRequirement slots(Set<EquipmentSlot> slots) {
        return new SetRequirement(slots, 0);
    }

    /** @return a requirement of any {@code pieces} worn pieces of the set */
    public static SetRequirement pieces(int pieces) {
        return new SetRequirement(Set.of(), pieces);
    }

    public boolean slotBound() {
        return !slots.isEmpty();
    }

    /** @return the number of pieces needed, the slot count for slot bound effects */
    public int required() {
        return pieces;
    }

    /**
     * @param worn the material worn in each armor slot
     * @return how many of the needed pieces of the material are worn, at most {@link #required()}
     */
    public int worn(Map<EquipmentSlot, ? extends IAPArmor> worn, IAPArmor mat) {
        int count = 0;
        for (Map.Entry<EquipmentSlot, ? extends IAPArmor> entry : worn.entrySet()) {
            if (entry.getValue() == mat && (slots.isEmpty() || slots.contains(entry.getKey()))) count++;
        }
        return Math.min(count, required());
    }

    /** @return true if the effect is listed on the piece in this slot (slot bound effects only on their own pieces) */
    public boolean shownOn(EquipmentSlot slot) {
        return slots.isEmpty() || slots.contains(slot);
    }

    /** @return the config form, e.g. "head", "legs,feet" or "2" */
    @Override
    public String toString() {
        if (slots.isEmpty()) return String.valueOf(pieces);
        return String.join(",", slots.stream().map(EquipmentSlot::getName).toList());
    }

    /** Syntax check of a suffix (without the "@"): a piece count of 1 - 4 or comma separated slots head, chest, legs and feet. */
    public static boolean isValid(String text) {
        return parse(text) != null;
    }

    /** @return the requirement of a suffix (without the "@"), or null if it's invalid */
    public static @Nullable SetRequirement parse(String text) {
        String value = text.trim();
        if (value.isEmpty()) return null;
        if (Character.isDigit(value.charAt(0))) {
            try {
                int pieces = Integer.parseInt(value);
                return pieces >= 1 && pieces <= ArmorEffects.SET_SIZE ? pieces(pieces) : null;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        Set<EquipmentSlot> slots = EnumSet.noneOf(EquipmentSlot.class);
        for (String slot : value.split(",", -1)) {
            String name = slot.trim();
            if (!SLOT_NAMES.contains(name)) return null;
            slots.add(EquipmentSlot.byName(name));
        }
        return slots(slots);
    }
}
