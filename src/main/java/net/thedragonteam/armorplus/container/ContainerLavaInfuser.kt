/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.IContainerListener
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.api.lavainfuser.LavaInfuserManager
import net.thedragonteam.armorplus.api.lavainfuser.SlotLavaInfuserFuel
import net.thedragonteam.armorplus.api.lavainfuser.SlotLavaInfuserOutput
import net.thedragonteam.armorplus.container.base.ContainerBase
import net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser

class ContainerLavaInfuser(playerInventory: InventoryPlayer, private val tile: TileEntityLavaInfuser) : ContainerBase() {
    private var cookTime: Int = 0
    private var totalCookTime: Int = 0
    private var furnaceBurnTime: Int = 0
    private var currentItemBurnTime: Int = 0

    init {
        this.addSlotToContainer(Slot(tile, 0, 69, 35))
        this.addSlotToContainer(SlotLavaInfuserFuel(tile, 1, 34, 35))
        this.addSlotToContainer(SlotLavaInfuserOutput(playerInventory.player, tile, 2, 124, 35))

        0.rangeTo(2).forEach { i -> 0.rangeTo(8).forEach { j -> this.addSlotToContainer(Slot(playerInventory, j + i * 9 + 9, 8 + j * ITEM_BOX, 84 + i * 18)) } }

        0.rangeTo(8).forEach { k -> this.addSlotToContainer(Slot(playerInventory, k, 8 + k * ITEM_BOX, 142)) }
    }

    override fun addListener(listener: IContainerListener) {
        super.addListener(listener)
        listener.sendAllWindowProperties(this, this.tile)
    }


    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    override fun detectAndSendChanges() {
        super.detectAndSendChanges()

        for (listener in this.listeners) {

            if (this.cookTime != this.tile.getField(2))
                listener.sendWindowProperty(this, 2, this.tile.getField(2))

            if (this.furnaceBurnTime != this.tile.getField(0))
                listener.sendWindowProperty(this, 0, this.tile.getField(0))

            if (this.currentItemBurnTime != this.tile.getField(1))
                listener.sendWindowProperty(this, 1, this.tile.getField(1))

            if (this.totalCookTime != this.tile.getField(3))
                listener.sendWindowProperty(this, 3, this.tile.getField(3))
        }

        this.cookTime = this.tile.getField(2)
        this.furnaceBurnTime = this.tile.getField(0)
        this.currentItemBurnTime = this.tile.getField(1)
        this.totalCookTime = this.tile.getField(3)
    }

    @SideOnly(Side.CLIENT)
    override fun updateProgressBar(id: Int, data: Int) {
        this.tile.setField(id, data)
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    override fun transferStackInSlot(playerIn: EntityPlayer, index: Int): ItemStack {
        var itemstack = ItemStack.EMPTY
        val slot = this.inventorySlots[index]

        if (slot != null && slot.hasStack) {
            val itemstack1 = slot.stack
            itemstack = itemstack1.copy()

            when {
                index == 2 -> {
                    if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                        return ItemStack.EMPTY
                    }

                    slot.onSlotChange(itemstack1, itemstack)
                }
                index != 1 && index != 0 -> when {
                    !LavaInfuserManager.getInstance().getInfusingResult(itemstack1).isEmpty -> if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY
                    }
                    TileEntityLavaInfuser.isItemFuel(itemstack1) -> if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY
                    }
                    index in 3..29 -> if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY
                    }
                    index in 30..38 && !this.mergeItemStack(itemstack1, 3, 30, false) -> return ItemStack.EMPTY
                }
                !this.mergeItemStack(itemstack1, 3, 39, false) -> return ItemStack.EMPTY
            }

            if (itemstack1.isEmpty) {
                slot.putStack(ItemStack.EMPTY)
            } else {
                slot.onSlotChanged()
            }

            if (itemstack1.count == itemstack.count) {
                return ItemStack.EMPTY
            }

            slot.onTake(playerIn, itemstack1)
        }

        return itemstack
    }

    companion object {
        private val ITEM_BOX = 18
    }
}