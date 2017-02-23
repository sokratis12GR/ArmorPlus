/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.minecraftforge.common.ForgeHooks
import net.thedragonteam.armorplus.registry.APBlocks.highTechBench
import net.thedragonteam.armorplus.registry.APItems.*
import net.thedragonteam.armorplus.registry.ModAchievements
import net.thedragonteam.armorplus.util.EnchantmentUtils.getEnchantment
import net.thedragonteam.thedragonlib.util.ItemStackUtils.getItem

/**
 * net.thedragonteam.armorplus.api.crafting.benches
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
        if (this.hasStack) {
            this.amountCrafted += Math.min(amount, this.stack.count)
        }

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
            stack!!.item === getItem(highTechBench) -> this.player.addStat(ModAchievements.craftHighTechBench, 1)
            stack!!.item === lapisSword -> stack!!.addEnchantment(getEnchantment("looting"), 3)
            stack!!.item === lapisBattleAxe -> stack!!.addEnchantment(getEnchantment("looting"), 3)
            stack!!.item === lapisBow -> stack!!.addEnchantment(getEnchantment("looting"), 3)
            stack!!.item === coalHelmet || stack!!.item === coalChestplate || stack!!.item === coalLeggings || stack!!.item === coalBoots -> this.player.addStat(ModAchievements.craftCoalArmor, 1)
            stack!!.item === lapisHelmet || stack!!.item === lapisChestplate || stack!!.item === lapisLeggings || stack!!.item === lapisBoots -> this.player.addStat(ModAchievements.craftLapisArmor, 1)
            stack!!.item === redstoneHelmet || stack!!.item === redstoneChestplate || stack!!.item === redstoneLeggings || stack!!.item === redstoneBoots -> this.player.addStat(ModAchievements.craftRedstoneArmor, 1)
            stack!!.item === emeraldHelmet || stack!!.item === emeraldChestplate || stack!!.item === emeraldLeggings || stack!!.item === emeraldBoots -> this.player.addStat(ModAchievements.craftEmeraldArmor, 1)
            stack!!.item === obsidianHelmet || stack!!.item === obsidianChestplate || stack!!.item === obsidianLeggings || stack!!.item === obsidianBoots -> this.player.addStat(ModAchievements.craftObsidianArmor, 1)
            stack!!.item === lavaHelmet || stack!!.item === lavaChestplate || stack!!.item === lavaLeggings || stack!!.item === lavaBoots -> this.player.addStat(ModAchievements.craftLavaArmor, 1)
            stack!!.item === slimeHelmet || stack!!.item === slimeChestplate || stack!!.item === slimeLeggings || stack!!.item === slimeBoots -> this.player.addStat(ModAchievements.craftSlimeArmor, 1)
            stack!!.item === chickenHelmet || stack!!.item === chickenChestplate || stack!!.item === chickenLeggings || stack!!.item === chickenBoots -> this.player.addStat(ModAchievements.craftChickenArmor, 1)/*Its time to go Faster! - Achievement Trigger*//*Jump Jump Jump! - Achievement Trigger*//*The Overpowered! - Achievement Trigger*//*Indestructible! - Achievement Trigger*//*Swing Swing Faster! - Achievement Trigger*//*Speeedy! - Achievement Trigger*//*Never Drown Again - Achievement Trigger*//*Vision Like A Bat! - Achievement Trigger*/
        }/*Its time to go Faster! - Achievement Trigger*//*Jump Jump Jump! - Achievement Trigger*//*The Overpowered! - Achievement Trigger*//*Indestructible! - Achievement Trigger*//*Swing Swing Faster! - Achievement Trigger*//*Speeedy! - Achievement Trigger*//*Never Drown Again - Achievement Trigger*//*Vision Like A Bat! - Achievement Trigger*/
    }

    override fun onTake(player: EntityPlayer?, stack: ItemStack): ItemStack {
        this.onCrafting(stack)
        ForgeHooks.setCraftingPlayer(player)
        val nonnulllist = WorkbenchCraftingManager.getInstance().getRemainingItems(this.craftMatrix, player!!.world)
        ForgeHooks.setCraftingPlayer(null)

        for (i in nonnulllist.indices) {
            var itemstack = this.craftMatrix.getStackInSlot(i)
            val itemstack1 = nonnulllist[i]

            if (!itemstack.isEmpty) {
                this.craftMatrix.decrStackSize(i, 1)
                itemstack = this.craftMatrix.getStackInSlot(i)
            }

            if (!itemstack1.isEmpty) {
                when {
                    itemstack.isEmpty -> this.craftMatrix.setInventorySlotContents(i, itemstack1)
                    ItemStack.areItemsEqual(itemstack, itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1) -> {
                        itemstack1.grow(itemstack.count)
                        this.craftMatrix.setInventorySlotContents(i, itemstack1)
                    }
                    !this.player.inventory.addItemStackToInventory(itemstack1) -> this.player.dropItem(itemstack1, false)
                }
            }
        }

        return stack
    }
}
