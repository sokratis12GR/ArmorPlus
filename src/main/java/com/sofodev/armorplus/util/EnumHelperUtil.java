/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.util;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.items.armors.APArmorMaterial;
import com.sofodev.armorplus.items.armors.Tier;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.entity.player.EntityPlayer.SleepResult;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.EnumHelper;

import java.util.function.Predicate;

import static com.sofodev.armorplus.util.Utils.setLocation;
import static net.minecraft.enchantment.Enchantment.*;
import static net.minecraft.util.text.TextFormatting.fromColorIndex;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.minecraftforge.common.util.EnumHelper.addEnchantmentType;

/**
 * @author Sokratis Fotkatzikis
 **/
public class EnumHelperUtil {

    public static EnumRarity addRarity(String enumName, Object color, String displayName) {
        if (color instanceof TextFormatting) {
            return EnumHelper.addRarity(enumName, (TextFormatting) color, displayName);
        } else if (color instanceof String) {
            return EnumHelper.addRarity(enumName, getValueByName((String) color), displayName);
        } else if (color instanceof Integer) {
            return EnumHelper.addRarity(enumName, fromColorIndex((int) color), displayName);
        }
        return EnumRarity.COMMON;
    }

    public static EnumEnchantmentType addEnchantType(String enumName, Predicate<Item> itemPredicate) {
        return addEnchantmentType(enumName, itemPredicate::test);
    }

    public static EnumAction addAction(String enumName) {
        return EnumHelper.addAction(enumName);
    }

    public static SleepResult addStatus(String enumName) {
        return EnumHelper.addStatus(enumName);
    }

    public static ArmorMaterial addArmorMaterial(String enumName, String textureName, int durability, int[] armorPoints, int enchantability, SoundEvent soundOnEquip, double toughnessPoints) {
        return EnumHelper.addArmorMaterial(enumName, setLocation(textureName), durability, armorPoints, enchantability, soundOnEquip, (float) toughnessPoints);
    }

    public static ArmorMaterial addArmorMaterial(String enumName, String textureName, int durability, int[] armorPoints, double toughnessPoints, Tier tier) {
        return addArmorMaterial(enumName, textureName, durability, armorPoints, tier.getEnchantability(), SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, toughnessPoints);
    }

    public static HorseArmorType addHorseArmor(String name, int armorStrength) {
        return EnumHelper.addHorseArmor(name, ArmorPlus.MODID + ":textures/entity/horse/armor/" + name + ".png", armorStrength);
    }

    public static HorseArmorType addHorseArmor(APArmorMaterial material, int armorStrength) {
        return EnumHelper.addHorseArmor(material.getName(), ArmorPlus.MODID + ":textures/entity/horse/armor/" + material.getName() + ".png", armorStrength);
    }

    public static Enchantment getEnchantment(int nameOrId) {
        return getEnchantmentByID(nameOrId);
    }

    public static Enchantment getEnchantment(String nameOrId) {
        return getEnchantmentByLocation(nameOrId);
    }

    public static int getID(Enchantment enchant) {
        return getEnchantmentID(enchant);
    }
}
