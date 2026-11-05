package com.sofodev.armorplus.registry.item.armor;

import net.minecraft.world.entity.EquipmentSlot;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * What a set effect needs to be active: specific armor pieces of the set (all of them or a number of them), or a number
 * of worn pieces of the set in any slots.
 * <p>
 * In config entries it's the optional {@code "@..."} suffix of a buff entry (see
 * {@link BuffParser}):
 * <ul>
 *     <li>{@code "minecraft:night_vision|1@head"} - the helmet of the set</li>
 *     <li>{@code "minecraft:conduit_power|2@legs,feet"} - the leggings and the boots of the set</li>
 *     <li>{@code "minecraft:speed|1@chest,legs,feet:2"} - any 2 of the chestplate, leggings and boots of the set</li>
 *     <li>{@code "minecraft:haste|2@2"} - any 2 pieces of the set</li>
 * </ul>
 * Entries without a suffix use the "effectSlots" default of the effect, otherwise "defaultSetPieces".
 *
 * @param slots  the pieces that count for the effect, empty if all of them do
 * @param pieces the number of worn pieces it needs, 1 - {@link ArmorEffects#SET_SIZE}, at most the size of {@code slots}
 */
public record SetRequirement(Set<EquipmentSlot> slots, int pieces) {

    /** Separates the slots from the number of them an effect needs, e.g. "chest,legs,feet:2". */
    public static final char COUNT_SEPARATOR = ':';
    private static final List<String> SLOT_NAMES = List.of("head", "chest", "legs", "feet");

    public SetRequirement {
        slots = slots.isEmpty() ? Set.of() : Collections.unmodifiableSet(EnumSet.copyOf(slots));
        pieces = Math.clamp(pieces, 1, slots.isEmpty() ? ArmorEffects.SET_SIZE : slots.size());
    }

    /** @return a requirement of all of these pieces of the set, e.g. {@code slots(EquipmentSlot.HEAD)} */
    public static SetRequirement slots(EquipmentSlot... slots) {
        Set<EquipmentSlot> set = slots.length == 0 ? Set.of() : EnumSet.of(slots[0], slots);
        return slots(set);
    }

    public static SetRequirement slots(Set<EquipmentSlot> slots) {
        return new SetRequirement(slots, slots.size());
    }

    /** @return a requirement of any {@code pieces} worn pieces of the set */
    public static SetRequirement pieces(int pieces) {
        return new SetRequirement(Set.of(), pieces);
    }

    public boolean slotBound() {
        return !slots.isEmpty();
    }

    /** @return the number of pieces needed */
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

    /** @return the slots in config form without the number of them, e.g. "legs,feet" */
    public String slotNames() {
        return String.join(",", slots.stream().map(EquipmentSlot::getName).toList());
    }

    /** @return the config form, e.g. "head", "legs,feet", "chest,legs,feet:2" or "2" */
    @Override
    public String toString() {
        if (slots.isEmpty()) return String.valueOf(pieces);
        return pieces < slots.size() ? slotNames() + COUNT_SEPARATOR + pieces : slotNames();
    }

    /**
     * Syntax check of a suffix (without the "@"): a piece count of 1 - 4 or comma separated slots head, chest, legs and feet,
     * optionally followed by how many of them are needed (":2", at most the number of slots).
     */
    public static boolean isValid(String text) {
        return parse(text) != null;
    }

    /** @return the requirement of a suffix (without the "@"), or null if it's invalid */
    public static @Nullable SetRequirement parse(String text) {
        String value = text.trim();
        if (value.isEmpty()) return null;
        if (Character.isDigit(value.charAt(0))) {
            int pieces = parseCount(value, ArmorEffects.SET_SIZE);
            return pieces > 0 ? pieces(pieces) : null;
        }
        int split = value.indexOf(COUNT_SEPARATOR);
        Set<EquipmentSlot> slots = EnumSet.noneOf(EquipmentSlot.class);
        for (String slot : (split < 0 ? value : value.substring(0, split)).split(",", -1)) {
            String name = slot.trim();
            if (!SLOT_NAMES.contains(name)) return null;
            slots.add(EquipmentSlot.byName(name));
        }
        if (split < 0) return slots(slots);
        int pieces = parseCount(value.substring(split + 1), slots.size());
        return pieces > 0 ? new SetRequirement(slots, pieces) : null;
    }

    /** @return the number if it's 1 - {@code max}, otherwise 0 */
    private static int parseCount(String text, int max) {
        try {
            int count = Integer.parseInt(text.trim());
            return count >= 1 && count <= max ? count : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
