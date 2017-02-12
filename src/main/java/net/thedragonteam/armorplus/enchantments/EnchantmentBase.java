/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.thedragonteam.armorplus.util.Utils;

import javax.annotation.Nonnull;

public class EnchantmentBase extends Enchantment {

    private int min;
    private int max;
    private boolean isTreasure;
    private boolean isBookAllowed;
    private int minEnchantability;
    private int maxEnchantability;

    public EnchantmentBase(String name, Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] allowedSlots, int minLevel, int maxLevel, int minEnchantability, int maxEnchantability, boolean isTreasure, boolean isBookAllowed) {
        super(rarity, type, allowedSlots);
        this.setRegistryName(name);
        this.setName(Utils.setName(name));
        this.min = minLevel;
        this.max = maxLevel;
        this.isTreasure = isTreasure;
        this.isBookAllowed = isBookAllowed;
        this.minEnchantability = minEnchantability;
        this.maxEnchantability = maxEnchantability;
        MinecraftForge.EVENT_BUS.register(this);
    }

    public EnchantmentBase(String name, Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] allowedSlots, int minLevel, int maxLevel, int minEnchantability, int maxEnchantability, boolean isTreasure) {
        this(name, rarity, type, allowedSlots, minLevel, maxLevel, minEnchantability, maxEnchantability, isTreasure, false);
    }

    public EnchantmentBase(String name, Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] allowedSlots, int minLevel, int maxLevel, boolean isTreasure, boolean isBookAllowed) {
        this(name, rarity, type, allowedSlots, minLevel, maxLevel, 10, 15, isTreasure, isBookAllowed);
    }

    public EnchantmentBase(String name, Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] allowedSlots, int minLevel, int maxLevel, boolean isTreasure) {
        this(name, rarity, type, allowedSlots, minLevel, maxLevel, isTreasure, false);
    }

    public EnchantmentBase(String name, Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] allowedSlots, int minLevel, int maxLevel, int minEnchantability, int maxEnchantability) {
        this(name, rarity, type, allowedSlots, minLevel, maxLevel, minEnchantability, maxEnchantability, false);
    }

    public EnchantmentBase(String name, Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] allowedSlots, int minLevel, int maxLevel) {
        this(name, rarity, type, allowedSlots, minLevel, maxLevel, 10, 15);
    }

    public EnchantmentBase(String name, Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] allowedSlots, int maxLevel) {
        this(name, rarity, type, allowedSlots, 1, maxLevel);
    }

    public EnchantmentBase(String name, Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] allowedSlots) {
        this(name, rarity, type, allowedSlots, 3);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return enchantmentLevel * this.minEnchantability;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
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
    public boolean canApplyTogether(Enchantment enchant) {
        return super.canApplyTogether(enchant);
    }

    @Override
    public boolean canApply(@Nonnull ItemStack stack) {
        return super.canApply(stack);
    }

    public enum Levels {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,;
    }
}
