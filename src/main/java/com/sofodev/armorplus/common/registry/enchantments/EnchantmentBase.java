/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author Sokratis Fotkatzikis
 */
public abstract class EnchantmentBase extends Enchantment {

    private int min;
    private int max;
    private int minEnchantability;
    private int maxEnchantability;
    private boolean isTreasure;
    private boolean isBookAllowed;

    public EnchantmentBase(String name, Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] allowedSlots, int min, int max, int minEnchantability, int maxEnchantability, boolean isTreasure, boolean isBookAllowed) {
        super(rarity, type, allowedSlots);
        this.min = min;
        this.max = max;
        this.minEnchantability = minEnchantability;
        this.maxEnchantability = maxEnchantability;
        this.isTreasure = isTreasure;
        this.isBookAllowed = isBookAllowed;
        this.setRegistryName(name);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public EnchantmentBase(String name, Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] allowedSlots, int min, int max, boolean isTreasure) {
        this(name, rarity, type, allowedSlots, min, max, 10, 15, isTreasure, false);
    }

    public EnchantmentBase(String name, Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] allowedSlots, int max, boolean isTreasure) {
        this(name, rarity, type, allowedSlots, 1, max, isTreasure);
    }

    public EnchantmentBase(String name, Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] allowedSlots, int min, int max) {
        this(name, rarity, type, allowedSlots, min, max, 10, 15, false, false);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return enchantmentLevel * this.minEnchantability;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + this.maxEnchantability;
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
    public boolean isTreasureEnchantment() {
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
    protected boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return super.canApply(stack);
    }
}
