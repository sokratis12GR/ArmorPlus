/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.lavainfuser

import net.minecraft.init.Items
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.thedragonteam.armorplus.registry.ModItems
import net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser.isItemFuel
import net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack

class SlotLavaInfuserFuel(inventoryIn: IInventory, slotIndex: Int, xPosition: Int, yPosition: Int) : Slot(inventoryIn, slotIndex, xPosition, yPosition) {

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    override fun isItemValid(stack: ItemStack?): Boolean {
        return isItemFuel(stack) || isAllowed(stack as ItemStack)
    }

    override fun getItemStackLimit(stack: ItemStack?): Int {
        return if (isAllowed(stack as ItemStack)) 1 else super.getItemStackLimit(stack)
    }

    companion object {

        var itemList = arrayOf(Items.LAVA_BUCKET, ModItems.lavaCrystal)

        fun isAllowed(stack: ItemStack): Boolean {
            itemList
                    .asSequence()
                    .filterNot { getItemStack(it).isEmpty }
                    .forEach { return stack.item === it }
            return stack.item === Items.LAVA_BUCKET
        }
    }
}