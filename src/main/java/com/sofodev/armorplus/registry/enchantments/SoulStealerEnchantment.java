package com.sofodev.armorplus.registry.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;

import static net.minecraft.world.entity.EquipmentSlot.MAINHAND;
import static net.minecraft.world.item.enchantment.EnchantmentCategory.WEAPON;

public class SoulStealerEnchantment extends APEnchantment {

    public SoulStealerEnchantment() {
        super(Rarity.VERY_RARE, WEAPON, new EquipmentSlot[]{MAINHAND},
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