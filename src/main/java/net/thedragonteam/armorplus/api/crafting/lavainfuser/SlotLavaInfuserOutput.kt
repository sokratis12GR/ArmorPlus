/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.lavainfuser

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.FMLCommonHandler

class SlotLavaInfuserOutput(
        /**
         * The player that is using the GUI where this slot resides.
         */
        private val player: EntityPlayer, inventoryIn: IInventory, slotIndex: Int, xPosition: Int, yPosition: Int) : Slot(inventoryIn, slotIndex, xPosition, yPosition) {
    private var removeCount: Int = 0

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    override fun isItemValid(stack: ItemStack?): Boolean {
        return false
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    override fun decrStackSize(amount: Int): ItemStack {
        if (this.hasStack) this.removeCount += Math.min(amount, this.stack.count)
        return super.decrStackSize(amount)
    }

    override fun onTake(thePlayer: EntityPlayer?, stack: ItemStack): ItemStack {
        this.onCrafting(stack)
        super.onTake(thePlayer, stack)
        return stack
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    override fun onCrafting(stack: ItemStack?, amount: Int) {
        this.removeCount += amount
        this.onCrafting(stack)
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    override fun onCrafting(stack: ItemStack?) {
        stack!!.onCrafting(this.player.world, this.player, this.removeCount)

        this.removeCount = 0

        FMLCommonHandler.instance().firePlayerSmeltedEvent(player, stack)

        //    if (stack.getItem() == Items.IRON_INGOT) {
        //        this.player.addStat(AchievementList.ACQUIRE_IRON);
        //    }
    }
}