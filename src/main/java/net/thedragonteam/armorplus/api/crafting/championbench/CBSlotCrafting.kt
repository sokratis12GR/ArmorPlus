/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.championbench

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.minecraftforge.common.ForgeHooks

/**
 * net.thedragonteam.armorplus.api.crafting.hightechbench
 * ArmorPlus created by sokratis12GR on 6/21/2016 3:55 PM.
 * - TheDragonTeam
 */
class CBSlotCrafting(
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
    }

    override fun onTake(player: EntityPlayer?, stack: ItemStack): ItemStack {
        this.onCrafting(stack)
        ForgeHooks.setCraftingPlayer(player)
        val nonnulllist = ChampionBenchCraftingManager.getInstance().getRemainingItems(this.craftMatrix, player!!.world)
        ForgeHooks.setCraftingPlayer(null)

        for (i in nonnulllist.indices) {
            var itemstack = this.craftMatrix.getStackInSlot(i)

            if (!itemstack.isEmpty) {
                this.craftMatrix.decrStackSize(i, 1)
                itemstack = this.craftMatrix.getStackInSlot(i)
            }

            if (!nonnulllist[i].isEmpty) {
                if (itemstack.isEmpty) this.craftMatrix.setInventorySlotContents(i, nonnulllist[i])
                else if (ItemStack.areItemsEqual(itemstack, nonnulllist[i]) && ItemStack.areItemStackTagsEqual(itemstack, nonnulllist[i])) {
                    nonnulllist[i].grow(itemstack.count)
                    this.craftMatrix.setInventorySlotContents(i, nonnulllist[i])
                }
                else if (!this.player.inventory.addItemStackToInventory(nonnulllist[i])) this.player.dropItem(nonnulllist[i], false)
            }
        }

        return stack
    }
}
