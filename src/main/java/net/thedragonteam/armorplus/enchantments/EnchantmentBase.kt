/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.enchantments

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnumEnchantmentType
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import net.thedragonteam.armorplus.util.Utils

open class EnchantmentBase @JvmOverloads constructor(name: String, rarity: Enchantment.Rarity, type: EnumEnchantmentType, allowedSlots: Array<EntityEquipmentSlot>, private val min: Int, private val max: Int, private val minEnchantability: Int = 10, private val maxEnchantability: Int = 15, private val isTreasure: Boolean = false, private val isBookAllowed: Boolean = false) : Enchantment(rarity, type, allowedSlots) {

    init {
        this.setRegistryName(name)
        this.setName(Utils.setName(name))
        MinecraftForge.EVENT_BUS.register(this)
    }

    @JvmOverloads constructor(name: String, rarity: Enchantment.Rarity, type: EnumEnchantmentType, allowedSlots: Array<EntityEquipmentSlot>, minLevel: Int, maxLevel: Int, isTreasure: Boolean, isBookAllowed: Boolean = false) : this(name, rarity, type, allowedSlots, minLevel, maxLevel, 10, 15, isTreasure, isBookAllowed) {}

    @JvmOverloads constructor(name: String, rarity: Enchantment.Rarity, type: EnumEnchantmentType, allowedSlots: Array<EntityEquipmentSlot>, maxLevel: Int = 3) : this(name, rarity, type, allowedSlots, 1, maxLevel) {}

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    override fun getMinEnchantability(enchantmentLevel: Int): Int {
        return enchantmentLevel * this.minEnchantability
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    override fun getMaxEnchantability(enchantmentLevel: Int): Int {
        return this.getMinEnchantability(enchantmentLevel) + this.maxEnchantability
    }

    override fun getMaxLevel(): Int {
        return this.max
    }

    override fun getMinLevel(): Int {
        return this.min
    }

    override fun isTreasureEnchantment(): Boolean {
        return this.isTreasure
    }

    override fun isAllowedOnBooks(): Boolean {
        return this.isBookAllowed
    }

    override fun canApplyAtEnchantingTable(stack: ItemStack): Boolean {
        return super.canApplyAtEnchantingTable(stack)
    }

    public override fun canApplyTogether(enchant: Enchantment?): Boolean {
        return super.canApplyTogether(enchant)
    }

    override fun canApply(stack: ItemStack): Boolean {
        return super.canApply(stack)
    }

    enum class Levels {
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
        TEN, ;
    }
}
