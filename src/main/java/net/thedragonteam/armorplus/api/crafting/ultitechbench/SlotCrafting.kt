/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.minecraftforge.common.ForgeHooks
import net.thedragonteam.armorplus.registry.APItems.*
import net.thedragonteam.armorplus.registry.ModAchievements
import net.thedragonteam.armorplus.util.EnchantmentUtils.getEnchantment

/**
 * net.thedragonteam.armorplus.api.crafting.hightechbench
 * ArmorPlus created by sokratis12GR on 6/21/2016 3:55 PM.
 * - TheDragonTeam
 */
class SlotCrafting(
        /**
         * The player that is using the GUI where this slot resides.
         */
        private val player: EntityPlayer,
        /**
         * The craft matrix inventory linked to this result slot.
         */
        private val craftMatrix: InventoryCrafting, inventoryIn: IInventory, slotIndex: Int, xPosition: Int, yPosition: Int) : Slot(inventoryIn, slotIndex, xPosition, yPosition) {
    /**
     * The number of items that have been crafted so far. Gets passed to ItemStack.onCrafting before being reset.
     */
    private var amountCrafted: Int = 0

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor itemHandler as well as furnace fuel.
     */
    override fun isItemValid(stack: ItemStack?): Boolean {
        return false
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    override fun decrStackSize(amount: Int): ItemStack {
        if (this.hasStack) this.amountCrafted += Math.min(amount, this.stack.count)
        return super.decrStackSize(amount)
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    override fun onCrafting(stack: ItemStack?, amount: Int) {
        this.amountCrafted += amount
        this.onCrafting(stack)
    }

    override fun onSwapCraft(p_190900_1_: Int) {
        this.amountCrafted += p_190900_1_
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    override fun onCrafting(stack: ItemStack?) {

        if (this.amountCrafted > 0) {
            stack!!.onCrafting(this.player.world, this.player, this.amountCrafted)
        }

        this.amountCrafted = 0

        when {
            stack!!.item === guardianSword -> stack!!.addEnchantment(getEnchantment("sharpness"), 1)
            stack!!.item === guardianBattleAxe -> stack!!.addEnchantment(getEnchantment("sharpness"), 1)
            stack!!.item === guardianBow -> stack!!.addEnchantment(getEnchantment("power"), 1)
            stack!!.item === guardianHelmet || stack!!.item === guardianChestplate || stack!!.item === guardianLeggings || stack!!.item === guardianBoots -> stack!!.addEnchantment(getEnchantment("thorns"), 3)
            stack!!.item === guardianHelmet || stack!!.item === guardianChestplate || stack!!.item === guardianLeggings || stack!!.item === guardianBoots -> stack!!.addEnchantment(getEnchantment("unbreaking"), 3)
            stack!!.item === guardianBoots -> stack!!.addEnchantment(getEnchantment("depth_strider"), 3)
            stack!!.item === theUltimateHelmet || stack!!.item === theUltimateChestplate || stack!!.item === theUltimateLeggings || stack!!.item === theUltimateBoots -> stack!!.addEnchantment(getEnchantment("mending"), 1)
            stack!!.item === guardianHelmet || stack!!.item === guardianChestplate || stack!!.item === guardianLeggings || stack!!.item === guardianBoots -> this.player.addStat(ModAchievements.craftGuardianArmor, 1)
            stack!!.item === superStarHelmet || stack!!.item === superStarChestplate || stack!!.item === superStarLeggings || stack!!.item === superStarBoots -> this.player.addStat(ModAchievements.craftSuperStarArmor, 1)
            stack!!.item === enderDragonHelmet || stack!!.item === enderDragonChestplate || stack!!.item === enderDragonLeggings || stack!!.item === enderDragonBoots -> this.player.addStat(ModAchievements.craftEnderDragonArmor, 1)
            stack!!.item === theUltimateHelmet || stack!!.item === theUltimateChestplate || stack!!.item === theUltimateLeggings || stack!!.item === theUltimateBoots -> this.player.addStat(ModAchievements.craftTheUltimateArmor, 1)/*The Ultimate Power! - Achievement Trigger*//*The Power of the Ender Dragon! - Achievement Trigger*//*Godlike! - Achievement Trigger*//*Full of Thorns! - Achievement Trigger*//*Mending*//* Guardian Boots Enchantments*//*Guardian Armor Unbreaking 3*//*Guardian Armor Thorns*/
        }/*The Ultimate Power! - Achievement Trigger*//*The Power of the Ender Dragon! - Achievement Trigger*//*Godlike! - Achievement Trigger*//*Full of Thorns! - Achievement Trigger*//*Mending*//* Guardian Boots Enchantments*//*Guardian Armor Unbreaking 3*//*Guardian Armor Thorns*/
    }

    override fun onTake(player: EntityPlayer?, stack: ItemStack): ItemStack {
        this.onCrafting(stack)
        ForgeHooks.setCraftingPlayer(player)
        val nonnulllist = UltiTechBenchCraftingManager.getInstance().getRemainingItems(this.craftMatrix, player!!.world)
        ForgeHooks.setCraftingPlayer(null)

        nonnulllist.indices.forEach { i ->
            var itemstack = this.craftMatrix.getStackInSlot(i)
            val itemstack1 = nonnulllist[i]

            if (!itemstack.isEmpty) {
                this.craftMatrix.decrStackSize(i, 1)
                itemstack = this.craftMatrix.getStackInSlot(i)
            }

            if (!itemstack1.isEmpty) {
                if (itemstack.isEmpty) this.craftMatrix.setInventorySlotContents(i, itemstack1)
                else if (ItemStack.areItemsEqual(itemstack, itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1)) {
                    itemstack1.grow(itemstack.count)
                    this.craftMatrix.setInventorySlotContents(i, itemstack1)
                }
                else if (!this.player.inventory.addItemStackToInventory(itemstack1)) this.player.dropItem(itemstack1, false)
            }
        }

        return stack
    }
}
