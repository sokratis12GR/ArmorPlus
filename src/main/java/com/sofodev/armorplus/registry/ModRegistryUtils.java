/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.ArmorPlus;
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
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.IForgeRegistry;

import static com.sofodev.armorplus.util.Utils.equipmentSlots;
import static com.sofodev.armorplus.util.Utils.setRL;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ModRegistryUtils {

    public static void registerItems(IForgeRegistry<Item> registry) {
        for (EntityEquipmentSlot equipmentSlot : equipmentSlots) {
            registry.register(new ItemUltimateArmor(equipmentSlot, new Item.Properties().maxStackSize(1).group(ArmorPlus.tabArmorplus)).setRegistryName(createPieces(equipmentSlot, "the_ultimate")));
        }
        for (APArmorMaterial mat : APArmorMaterial.values()) {
            for (EntityEquipmentSlot equipmentSlot : equipmentSlots) {
                registry.register(new ItemArmorBase(mat, equipmentSlot, new Item.Properties().maxStackSize(1).group(ArmorPlus.tabArmorplus)).setRegistryName(createPieces(equipmentSlot, mat.getName())));
            }
        }
        for (Swords swords : Swords.values()) {
            registry.register(new ItemSpecialSword(swords, new Item.Properties().group(ArmorPlus.tabArmorplusWeapons)).setRegistryName(setRL(swords.getName() + "_sword")));
        }
        for (BattleAxes battleAxes : BattleAxes.values()) {
            registry.register(new ItemSpecialBattleAxe(battleAxes, new Item.Properties().group(ArmorPlus.tabArmorplusWeapons).addToolType(ToolType.AXE, 2)).setRegistryName(setRL(battleAxes.getName() + "_battle_axe")));
        }
        for (Bows bows : Bows.values()) {
            registry.register(new ItemSpecialBow(bows, new Item.Properties().defaultMaxDamage(bows.getDurability(false)).group(ArmorPlus.tabArmorplusWeapons)).setRegistryName(setRL(bows.getName() + "_bow")));
        }
        for (ItemUltimatePart.UltimatePart ultimatePart : ItemUltimatePart.UltimatePart.values()) {
            registry.register(new ItemUltimatePart(new Item.Properties().group(ArmorPlus.tabArmorplusItems)).setRegistryName(setRL(ultimatePart.getName())));
        }
    }

    private static ResourceLocation createPieces(EntityEquipmentSlot slot, String name) {
        String piece;
        if (slot == HEAD) {
            piece = name + "_helmet";
        } else if (slot == CHEST) {
            piece = name + "_chestplate";
        } else if (slot == LEGS) {
            piece = name + "_leggings";
        } else if (slot == FEET) {
            piece = name + "_boots";
        } else {
            piece = name + "";
        }
        return setRL(piece);
    }

  //  private static ResourceLocation createPieces(EntityEquipmentSlot slot) {
  //      String piece;
  //      if (slot == HEAD) {
  //          piece = "the_ultimate_helmet";
  //      } else if (slot == CHEST) {
  //          piece = "the_ultimate_chestplate";
  //      } else if (slot == LEGS) {
  //          piece = "the_ultimate_leggings";
  //      } else piece = slot == FEET ? "the_ultimate_boots" : "";
  //      return setRL(piece);
  //  }

    public static void registerItems(IForgeRegistry<Item> registry, MaterialType material) {
        for (EntityEquipmentSlot equipmentSlot : equipmentSlots) {
            registry.register(new ItemArmorV2(material, equipmentSlot).setRegistryName(createPieces(equipmentSlot, material.getName() + "_prototype")));
        }
    }
}