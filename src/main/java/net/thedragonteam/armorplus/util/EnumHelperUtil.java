/*
 * Copyright (c) TheDragonTeam 2016.
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

public class EnumHelperUtil {

    public static EnumRarity addRarity(String enumName, TextFormatting formatting, String displayName) {
        return EnumHelper.addRarity(enumName, formatting, displayName);
    }

    public static EnumRarity addRarity(String enumName, String formattingName, String displayName) {
        return addRarity(enumName, getValueByName(formattingName), displayName);
    }

    public static EnumRarity addRarity(String enumName, int colorIndex, String displayName) {
        return addRarity(enumName, fromColorIndex(colorIndex), displayName);
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
        switch (enumTiers) {
            case TIER_1:
                return addArmorMaterial(enumName, textureName, durability, armorPoints, 8, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, toughnessPoints);
            case TIER_2:
                return addArmorMaterial(enumName, textureName, durability, armorPoints, 16, SoundEvents.ITEM_ARMOR_EQUIP_IRON, toughnessPoints);
            case TIER_3:
                return addArmorMaterial(enumName, textureName, durability, armorPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, toughnessPoints);
            case TIER_4:
                return addArmorMaterial(enumName, textureName, durability, armorPoints, 60, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, toughnessPoints);
        }
        return addArmorMaterial(enumName, textureName, durability, armorPoints, 0, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, toughnessPoints);
    }
}
