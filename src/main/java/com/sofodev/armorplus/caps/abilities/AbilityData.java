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
    NONE(0, "Empty", true, null, Material.NONE),
    NIGHT_VISION(1, "Night Vision", true, HEAD, COAL),
    WATER_BREATHING(2, "Water Breathing", true, HEAD, LAPIS, GUARDIAN),
    RESISTANCE(3, "Resistance", true, CHEST, INFUSED_LAVA),
    FIRE_RESISTANCE(4, "Fire Resistance", true, CHEST, INFUSED_LAVA),
    HASTE(5, "Haste", true, CHEST, REDSTONE, EMERALD),
    SPEED(6, "Speed", true, FEET, REDSTONE, EMERALD, GUARDIAN),
    JUMP_BOOST(7, "Jump Boost", true, FEET, SLIME),
    REGENERATION(8, "Regeneration", true, CHEST, SUPER_STAR),
    STRENGTH(9, "Strength", true, CHEST, MANYULLYN),
    INVISIBILITY(10, "Invisibility", true, CHEST, ULTIMATE),
    WITHER_PROOF(99, "Wither-Proof", false, CHEST, SUPER_STAR, ENDER_DRAGON),
    FLIGHT(100, "Flight", false, CHEST, ENDER_DRAGON),
    //TBD
    STEP_ASSIST(101, "Step Assist", false, LEGS, Material.NONE),
    BONUS_XP_ON_KILL(102, "Bonus XP on Kill", false, CHEST, Material.NONE),
    WALK_ON_LAVA(103, "Walk on Lava", false, FEET, Material.NONE),
    SWIMMING_SPEED(104, "Swimming Speed", false, FEET, Material.NONE),
    UNDERWATER_VISION(105, "Underwater Vision", false, HEAD, Material.NONE);

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
    private final EntityEquipmentSlot slot;
    /**
     * The materials that the ability is available for
     */
    private final Material[] materials;

    AbilityData(int id, String name, boolean isPotion, EntityEquipmentSlot slot, Material... materials) {
        this.id = id;
        this.name = name;
        this.isPotion = isPotion;
        this.slot = slot;
        this.materials = materials;
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

    public EntityEquipmentSlot getSlots() {
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

    public static boolean canProvide(ItemStack stack, int id) {
        if (stack.getItem() instanceof ItemArmorV2) {
            ItemArmorV2 armor = ((ItemArmorV2) stack.getItem());
            AbilityData data = getData(id);
            for (Material mat : data.getMaterials()) {
                if (mat == armor.material) {
                 //   for (EntityEquipmentSlot equipmentSlot : data.getSlots()) {
                        if (armor.getEquipmentSlot() == data.getSlots()) {
                            return data.getID() == id;
                        }
                 //   }
                }
            }
        }
        return false;
    }
}
