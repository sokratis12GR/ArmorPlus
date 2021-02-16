package com.sofodev.armorplus.registry.enchantments;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.minecraft.enchantment.EnchantmentType.*;

/**
 * The "Unknown" enchantment provides different effects based on the item it is applied on
 */
public class UnknownEnchantment extends APEnchantment {

    public UnknownEnchantment() {
        super(Rarity.VERY_RARE, WEAPON, EquipmentSlotType.values(),
                1, 1, 30, 60, true, true
        );
    }

    @Override
    public boolean canApply(ItemStack stack) {
        Item item = stack.getItem();
        return WEAPON.canEnchantItem(item) || BREAKABLE.canEnchantItem(item) || VANISHABLE.canEnchantItem(item) || WEARABLE.canEnchantItem(item);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return this.canApply(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}