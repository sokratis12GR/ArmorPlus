/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container.base

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
open class ContainerBenchBase(tile: TileEntity, recipeSlotsIn: Int, mainInventorySlotsIn: Int, fullInventorySlotsIn: Int) : ContainerBase() {
    var world: World = tile.world

    init {
        recipeSlots = recipeSlotsIn
        mainInventorySlots = mainInventorySlotsIn
        fullInventorySlots = fullInventorySlotsIn
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    override fun transferStackInSlot(playerIn: EntityPlayer?, index: Int): ItemStack {
        var itemstack = ItemStack.EMPTY
        val slot = inventorySlots[index]

        if (slot != null && slot.hasStack) {
            val itemstack1 = slot.stack
            itemstack = itemstack1.copy()

            when {
                index == 0 -> {
                    itemstack1.item.onCreated(itemstack1, world, playerIn)

                    if (!this.mergeItemStack(itemstack1, recipeSlots, fullInventorySlots, true)) {
                        return ItemStack.EMPTY
                    }

                    slot.onSlotChange(itemstack1, itemstack)
                }
                index >= recipeSlots && index < mainInventorySlots -> if (!this.mergeItemStack(itemstack1, mainInventorySlots, fullInventorySlots, false)) {
                    return ItemStack.EMPTY
                }
                index >= mainInventorySlots && index < fullInventorySlots -> if (!this.mergeItemStack(itemstack1, recipeSlots, mainInventorySlots, false)) {
                    return ItemStack.EMPTY
                }
                !this.mergeItemStack(itemstack1, recipeSlots, fullInventorySlots, false) -> return ItemStack.EMPTY
            }

            when {
                itemstack1.isEmpty -> slot.putStack(ItemStack.EMPTY)
                else -> slot.onSlotChanged()
            }

            if (itemstack1.count == itemstack.count) {
                return ItemStack.EMPTY
            }

            val itemstack2 = slot.onTake(playerIn, itemstack1)

            if (index == 0) {
                playerIn!!.dropItem(itemstack2, false)
            }
        }

        return itemstack
    }

    companion object {

        var recipeSlots: Int = 0
        var mainInventorySlots: Int = 0
        var fullInventorySlots: Int = 0
    }
}
