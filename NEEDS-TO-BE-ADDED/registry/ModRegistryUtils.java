/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.caps.abilities.MaterialType;
import com.sofodev.armorplus.items.ItemUltimatePart;
import com.sofodev.armorplus.items.armors.APArmorMaterial;
import com.sofodev.armorplus.items.armors.base.ItemArmorBase;
import com.sofodev.armorplus.items.armors.base.ItemArmorV2;
import com.sofodev.armorplus.items.armors.base.ItemUltimateArmor;
import com.sofodev.armorplus.items.base.ItemSpecialBattleAxe;
import com.sofodev.armorplus.items.base.ItemSpecialBow;
import com.sofodev.armorplus.items.base.ItemSpecialSword;
import com.sofodev.armorplus.items.weapons.BattleAxes;
import com.sofodev.armorplus.items.weapons.Bows;
import com.sofodev.armorplus.items.weapons.Swords;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import static com.sofodev.armorplus.util.Utils.equipmentSlots;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ModRegistryUtils {

    public static void register(IForgeRegistry<Item> registry, boolean enabled, APArmorMaterial armorMaterial) {
        if (enabled) {
            for (EntityEquipmentSlot equipmentSlot : equipmentSlots) {
                registry.register(new ItemArmorBase(armorMaterial, equipmentSlot));
            }
        }
    }

    public static void register(IForgeRegistry<Item> registry, MaterialType material) {
        for (EntityEquipmentSlot equipmentSlot : equipmentSlots) {
            registry.register(new ItemArmorV2(material, equipmentSlot));
        }
    }

    public static void register(IForgeRegistry<Item> registry, boolean enabled) {
        if (enabled) {
            for (EntityEquipmentSlot equipmentSlot : equipmentSlots) {
                registry.register(new ItemUltimateArmor(equipmentSlot));
            }
        }
    }

    public static void register(IForgeRegistry<Item> registry, boolean[] enabled, Swords[] material) {
        int bound = enabled.length;
        for (int i = 0; i < bound; i++) {
            if (enabled[i]) {
                registry.register(new ItemSpecialSword(material[i]));
            }
        }
    }

    public static void register(IForgeRegistry<Item> registry, boolean[] enabled, BattleAxes[] material) {
        int bound = enabled.length;
        for (int i = 0; i < bound; i++) {
            if (enabled[i]) {
                registry.register(new ItemSpecialBattleAxe(material[i]));
            }
        }
    }

    public static void register(IForgeRegistry<Item> registry, boolean[] enabled, Bows[] material) {
        int bound = enabled.length;
        for (int i = 0; i < bound; i++) {
            if (enabled[i]) {
                registry.register(new ItemSpecialBow(material[i]));
            }
        }
    }

    public static void registerParts(IForgeRegistry<Item> registry, ItemUltimatePart.UltimatePart... prop) {
        for (ItemUltimatePart.UltimatePart ultimatePart : prop) {
            registry.register(new ItemUltimatePart(ultimatePart));
        }
    }
}