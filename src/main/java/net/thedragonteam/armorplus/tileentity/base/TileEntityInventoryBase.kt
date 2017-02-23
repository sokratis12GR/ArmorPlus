/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.tileentity.base

import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraftforge.items.IItemHandler
import net.thedragonteam.thedragonlib.util.ItemStackHandlerImproved

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
abstract class TileEntityInventoryBase(itemHandler: Int) : TileEntityBase() {

    var itemHandler: ItemStackHandlerImproved

    init {
        this.itemHandler = object : ItemStackHandlerImproved(itemHandler) {
            override fun canInsert(stack: ItemStack, slot: Int): Boolean {
                return this@TileEntityInventoryBase.isItemValidForSlot(slot, stack)
            }

            override fun canExtract(stack: ItemStack, slot: Int): Boolean {
                return this@TileEntityInventoryBase.canExtractItem(slot, stack)
            }

            override fun getSlotLimit(slot: Int): Int {
                return this@TileEntityInventoryBase.getMaxStackSizePerSlot(slot)
            }

            override fun onContentsChanged(slot: Int) {
                super.onContentsChanged(slot)
                this@TileEntityInventoryBase.markDirty()
            }
        }
    }

    override fun getItemHandler(facing: EnumFacing): IItemHandler {
        return this.itemHandler
    }

    open fun isItemValidForSlot(slot: Int, stack: ItemStack): Boolean {
        return true
    }

    open fun canExtractItem(slot: Int, stack: ItemStack): Boolean {
        return true
    }

    fun getMaxStackSizePerSlot(slot: Int): Int {
        return 64
    }

    override fun markDirty() {
        super.markDirty()
    }
}