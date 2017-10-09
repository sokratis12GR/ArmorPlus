/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.enchantment.EnumEnchantmentType;
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

import static net.minecraft.util.text.TextFormatting.fromColorIndex;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.minecraftforge.common.util.EnumHelper.addEnchantmentType;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
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
        return EnumHelper.addArmorMaterial(enumName, textureName, durability, armorPoints, enchantability, soundOnEquip, (float) toughnessPoints);
    }

    public static ArmorMaterial addArmorMaterial(String enumName, String textureName, int durability, int[] armorPoints, double toughnessPoints, EnumTiers enumTiers) {
        return addArmorMaterial(enumName, textureName, durability, armorPoints, enumTiers.getEnchantability(), SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, toughnessPoints);
    }
}
