package com.sofodev.armorplus.registry.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class APEnchantment extends Enchantment {

    private int min;
    private int max;
    private int minEnchantability;
    private int maxEnchantability;
    private boolean isTreasure;
    private boolean isBookAllowed;

    protected APEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }

    public APEnchantment(Enchantment.Rarity rarity, EnchantmentType type, EquipmentSlotType[] allowedSlots,
                         int min, int max, int minEnchantability, int maxEnchantability, boolean isTreasure, boolean isBookAllowed) {
        super(rarity, type, allowedSlots);
        this.min = min;
        this.max = max;
        this.minEnchantability = minEnchantability;
        this.maxEnchantability = maxEnchantability;
        this.isTreasure = isTreasure;
        this.isBookAllowed = isBookAllowed;
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return enchantmentLevel * this.minEnchantability;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + this.maxEnchantability;
    }

    @Override
    public int getMaxLevel() {
        return this.max;
    }

    @Override
    public int getMinLevel() {
        return this.min;
    }

    @Override
    public boolean isTreasureOnly() {
        return this.isTreasure;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return this.isBookAllowed;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return super.canApplyAtEnchantingTable(stack);
    }

    @Override
    protected boolean checkCompatibility(Enchantment ench) {
        return super.checkCompatibility(ench);
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return super.canEnchant(stack);
    }
}