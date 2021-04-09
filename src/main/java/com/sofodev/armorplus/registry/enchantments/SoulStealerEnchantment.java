package com.sofodev.armorplus.registry.enchantments;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;

import static net.minecraft.enchantment.EnchantmentType.WEAPON;
import static net.minecraft.inventory.EquipmentSlotType.MAINHAND;

public class SoulStealerEnchantment extends APEnchantment {

    public SoulStealerEnchantment() {
        super(Rarity.VERY_RARE, WEAPON, new EquipmentSlotType[]{MAINHAND},
                1, 1, 10, 60, false, true
        );
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return WEAPON.canEnchant(stack.getItem()) || stack.getItem() instanceof AxeItem || stack.getItem() instanceof BowItem;
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