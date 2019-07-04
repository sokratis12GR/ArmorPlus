/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.enchantments;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import static net.minecraft.item.ItemArmor.ArmorMaterial.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class EnhancedEnchantment extends EnchantmentBase {

    public EnhancedEnchantment() {
        super("enhanced", Rarity.VERY_RARE, EnumEnchantmentType.ARMOR, EntityEquipmentSlot.values(), 1, 1, 10, 40, false, true);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return this.matchMaterial(stack);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return this.matchMaterial(stack);
    }

    private boolean matchMaterial(ItemStack stack) {
        if (stack.getItem() instanceof ItemArmor) {
            ItemArmor armor = (ItemArmor) stack.getItem();
            return armor.getArmorMaterial() == CHAIN || armor.getArmorMaterial() == GOLD || armor.getArmorMaterial() == IRON || armor.getArmorMaterial() == DIAMOND;
        } else return false;
    }
}