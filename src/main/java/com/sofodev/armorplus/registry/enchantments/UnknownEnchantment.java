package com.sofodev.armorplus.registry.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

import static net.minecraft.world.item.enchantment.EnchantmentCategory.*;

/**
 * The "Unknown" enchantment provides different effects based on the item it is applied on
 */
public class UnknownEnchantment extends APEnchantment {

    public UnknownEnchantment() {
        super(Enchantment.Rarity.VERY_RARE, WEAPON, EquipmentSlot.values(),
                1, 1, 30, 60, true, true
        );
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        Item item = stack.getItem();
        return WEAPON.canEnchant(item) || BREAKABLE.canEnchant(item) || VANISHABLE.canEnchant(item) || WEARABLE.canEnchant(item);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return this.canEnchant(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}