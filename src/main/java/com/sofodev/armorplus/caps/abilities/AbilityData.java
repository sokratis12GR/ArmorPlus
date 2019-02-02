/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.caps.abilities;

import com.sofodev.armorplus.items.armors.Material;
import com.sofodev.armorplus.items.armors.base.ItemArmorV2;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import java.util.Arrays;

import static com.sofodev.armorplus.items.armors.Material.*;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

public enum AbilityData {
    NONE(0, "Empty", true, EntityEquipmentSlot.values(), Material.NONE),
    NIGHT_VISION(1, "Night Vision", true, HEAD, COAL, INFUSED_LAVA),
    WATER_BREATHING(2, "Water Breathing", true, HEAD, LAPIS, GUARDIAN),
    RESISTANCE(3, "Resistance", true, HEAD, CHEST, LEGS, FEET, OBSIDIAN, INFUSED_LAVA),
    FIRE_RESISTANCE(4, "Fire Resistance", true, HEAD, CHEST, LEGS, FEET, OBSIDIAN, INFUSED_LAVA),
    HASTE(5, "Haste", true, CHEST, REDSTONE, EMERALD),
    SPEED(6, "Speed", true, FEET, REDSTONE, EMERALD, GUARDIAN),
    JUMP_BOOST(7, "Jump Boost", true, FEET, SLIME),
    REGENERATION(8, "Regeneration", true, CHEST, SUPER_STAR, ULTIMATE),
    STRENGTH(9, "Strength", true, CHEST, MANYULLYN),
    INVISIBILITY(10, "Invisibility", true, CHEST, ULTIMATE),
    ABSORPTION(11, "Absorption", true, CHEST, INFUSED_LAVA),
    WITHER_PROOF(99, "Wither Proof", false, CHEST, SUPER_STAR, ENDER_DRAGON, ULTIMATE),
    FLIGHT(100, "Flight", false, CHEST, ENDER_DRAGON, ULTIMATE),
    //TBD
    STEP_ASSIST(101, "Step Assist", false, LEGS, REDSTONE, SUPER_STAR),
    BONUS_XP_ON_KILL(102, "Bonus XP on Kill", false, CHEST, COAL, LAPIS, EMERALD),
    WALK_ON_LAVA(103, "Walk on Lava", false, FEET, INFUSED_LAVA),
    SWIMMING_SPEED(104, "Swimming Speed", false, FEET, GUARDIAN),
    UNDERWATER_VISION(105, "Underwater Vision", false, HEAD, GUARDIAN);

    /**
     * The ability "unique" id
     */
    private final int id;
    /**
     * Ability's name
     */
    private final String name;
    /**
     * Whether or not the ability is a potion effect
     */
    private final boolean isPotion;
    /**
     * The slots that the ability is available for
     */
    private final EntityEquipmentSlot[] slot;
    /**
     * The materials that the ability is available for
     */
    private final Material[] materials;

    AbilityData(int id, String name, boolean isPotion, EntityEquipmentSlot[] slot, Material... materials) {
        this.id = id;
        this.name = name;
        this.isPotion = isPotion;
        this.slot = slot;
        this.materials = materials;
    }

    AbilityData(int id, String name, boolean isPotion, EntityEquipmentSlot a, EntityEquipmentSlot b, EntityEquipmentSlot c, EntityEquipmentSlot d, Material... materials) {
        this(id, name, isPotion, new EntityEquipmentSlot[]{a, b, c, d}, materials);
    }

    AbilityData(int id, String name, boolean isPotion, EntityEquipmentSlot a, EntityEquipmentSlot b, EntityEquipmentSlot c, Material... materials) {
        this(id, name, isPotion, a, b, c, null, materials);
    }

    AbilityData(int id, String name, boolean isPotion, EntityEquipmentSlot a, EntityEquipmentSlot b, Material... materials) {
        this(id, name, isPotion, a, b, null, null, materials);
    }

    AbilityData(int id, String name, boolean isPotion, EntityEquipmentSlot a, Material... materials) {
        this(id, name, isPotion, a, null, null, null, materials);
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSafeName() {
        return name.toLowerCase().replace(" ", "_");
    }

    public boolean isPotion() {
        return isPotion;
    }

    public EntityEquipmentSlot[] getSlots() {
        return slot;
    }

    public Material[] getMaterials() {
        return materials;
    }

    public AbilityData getData() {
        return this;
    }

    public static AbilityData getData(int id) {
        return Arrays.stream(AbilityData.values()).filter(data -> data.getID() == id).findFirst().orElse(NONE);
    }

    public static AbilityData getData(String safeID) {
        return Arrays.stream(AbilityData.values()).filter(data -> data.getSafeName().equals(safeID)).findFirst().orElse(NONE);
    }

    public static boolean canProvide(ItemStack stack, String id) {
        if (stack.getItem() instanceof ItemArmorV2) {
            ItemArmorV2 armor = ((ItemArmorV2) stack.getItem());
            AbilityData data = getData(id);
            if (Arrays.stream(
                data.getMaterials()
            ).filter(
                mat -> mat == armor.material
            ).flatMap(
                mat -> Arrays.stream(data.getSlots())
            ).anyMatch(
                equipmentSlot -> armor.getEquipmentSlot() == equipmentSlot
            )) {
                return data.getSafeName().equals(id);
            }
        }
        return false;
    }
}
