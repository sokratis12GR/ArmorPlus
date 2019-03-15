/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.util;

import com.sofodev.armorplus.data.ArmorData;
import com.sofodev.armorplus.data.ItemData;
import com.sofodev.armorplus.items.armors.Tier;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static net.minecraft.enchantment.Enchantment.getEnchantmentByID;

/**
 * @author Sokratis Fotkatzikis
 **/
public class EnumHelperUtil {

    //  public static EnumRarity addRarity(String enumName, Object color, String displayName) {
    //      if (color instanceof TextFormatting) {
    //          return EnumHelper.addRarity(enumName, (TextFormatting) color, displayName);
    //      } else if (color instanceof String) {
    //          return EnumHelper.addRarity(enumName, getValueByName((String) color), displayName);
    //      } else if (color instanceof Integer) {
    //          return EnumHelper.addRarity(enumName, fromColorIndex((int) color), displayName);
    //      }
    //      return EnumRarity.COMMON;
    //  }

    public static EnumEnchantmentType addEnchantType(String enumName, Predicate<Item> itemPredicate) {
        return EnumEnchantmentType.create(enumName, itemPredicate);
    }

    public static IItemTier addMaterial(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Supplier<Ingredient>> repairMaterialIn) {
        return new ItemData(harvestLevelIn, maxUsesIn, efficiencyIn, attackDamageIn, enchantabilityIn, repairMaterialIn);
    }

    public static IArmorMaterial addArmorMaterial(String textureName, int durability, int[] armorPoints, int enchantability, SoundEvent soundOnEquip, double toughnessPoints, Supplier<Supplier<Ingredient>> supplier) {
        return new ArmorData(MODID + ":" + textureName, durability, armorPoints, enchantability, soundOnEquip, toughnessPoints, supplier);
    }

    public static IArmorMaterial addArmorMaterial(String textureName, int durability, int[] armorPoints, double toughnessPoints, Tier tier, Supplier<Supplier<Ingredient>> supplier) {
        return addArmorMaterial(textureName, durability, armorPoints, tier.getEnchantability(), SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, toughnessPoints, supplier);
    }
//
    //   public static HorseArmorType addHorseArmor(String name, int armorStrength, Item itemIn) {
    //       return HorseArmorType.create(name, armorStrength, ArmorPlus.MODID + ":textures/entity/horse/armor/" + name + ".png", name, itemIn);
    //   }
//
    //   public static HorseArmorType addHorseArmor(APArmorMaterial material, int armorStrength, Item itemIn) {
    //       return addHorseArmor(material.getName(), armorStrength, itemIn);
    //   }

    public static Enchantment getEnchantment(int nameOrId) {
        return getEnchantmentByID(nameOrId);
    }

    public static Enchantment getEnchantment(ResourceLocation nameOrId) {
        return ForgeRegistries.ENCHANTMENTS.getValue(nameOrId);
    }

    public static Enchantment getEnchantment(String nameOrId) {
        return getEnchantment(new ResourceLocation(nameOrId));
    }

    public static ResourceLocation getRL(Enchantment enchant) {
        return ForgeRegistries.ENCHANTMENTS.getKey(enchant);
    }
}
