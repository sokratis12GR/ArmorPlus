/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.InventoryCraftResult
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.thedragonteam.armorplus.api.crafting.workbench_new.SlotCrafting
import net.thedragonteam.armorplus.api.crafting.workbench_new.WorkbenchNewCraftingManager
import net.thedragonteam.armorplus.container.inventory.InventoryCraftingNew

/**
 * net.thedragonteam.armorplus.container
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:39 AM.
 * - TheDragonTeam
 */
class ContainerWorkbenchNew(playerInventory: InventoryPlayer, private val world: World) : Container() {
    /**
     * The crafting matrix inventory (3x3).
     */
    var craftMatrix = InventoryCraftingNew(this, 9)
    var craftResult: IInventory = InventoryCraftResult()

    init {
        this.addSlotToContainer(SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 35))

        for (i in 0 .. RECIPE_SIZE - 1)
            for (j in 0 .. RECIPE_SIZE - 1)
                this.addSlotToContainer(Slot(this.craftMatrix, j + i * RECIPE_SIZE, 15 + j * ITEM_BOX, 28 + i * ITEM_BOX))

        for (k in 0 .. 2)
            for (i1 in 0 .. ROW_SLOTS - 1)
                this.addSlotToContainer(Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * ITEM_BOX, 174 + k * ITEM_BOX))

        for (l in 0 .. ROW_SLOTS - 1)
            this.addSlotToContainer(Slot(playerInventory, l, 8 + l * ITEM_BOX, 232))

        this.onCraftMatrixChanged(this.craftMatrix)
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    override fun onCraftMatrixChanged(inventoryIn: IInventory?) {
        this.craftResult.setInventorySlotContents(0, WorkbenchNewCraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.world))
    }

    /**
     * Called when the container is closed.
     */
    override fun onContainerClosed(playerIn: EntityPlayer) {
        super.onContainerClosed(playerIn)

        if (!this.world.isRemote)
            (0 .. RECIPE_SIZE_TOTAL - 1)
                    .asSequence()
                    .map { this.craftMatrix.removeStackFromSlot(it) }
                    .filterNot { it.isEmpty }
                    .forEach { playerIn.dropItem(it, false) }
    }

    /**
     * Determines whether supplied player can use this container
     */
    override fun canInteractWith(playerIn: EntityPlayer): Boolean {
        return true
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    override fun transferStackInSlot(playerIn: EntityPlayer?, index: Int): ItemStack {
        var itemstack = ItemStack.EMPTY
        val slot = this.inventorySlots[index]

        if (slot != null && slot.hasStack) {
            val itemstack1 = slot.stack
            itemstack = itemstack1.copy()

            when (index) {
                0 -> {
                    itemstack1.item.onCreated(itemstack1, this.world, playerIn)

                    if (!this.mergeItemStack(itemstack1, RECIPE_SLOTS, FULL_INVENTORY_SLOTS, true)) return ItemStack.EMPTY

                    slot.onSlotChange(itemstack1, itemstack)
                }
                in RECIPE_SLOTS .. (MAIN_INVENTORY_SLOTS - 1) -> if (!this.mergeItemStack(itemstack1, MAIN_INVENTORY_SLOTS, FULL_INVENTORY_SLOTS, false)) {
                    return ItemStack.EMPTY
                }
                in MAIN_INVENTORY_SLOTS .. (FULL_INVENTORY_SLOTS - 1) -> when {
                    !this.mergeItemStack(itemstack1, RECIPE_SLOTS, MAIN_INVENTORY_SLOTS, false) -> return ItemStack.EMPTY
                    !this.mergeItemStack(itemstack1, RECIPE_SLOTS, FULL_INVENTORY_SLOTS, false) -> return ItemStack.EMPTY
                }
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

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
    override fun canMergeSlot(stack: ItemStack?, slotIn: Slot?): Boolean {
        return slotIn!!.inventory !== this.craftResult && super.canMergeSlot(stack, slotIn)
    }

    companion object {

        private val ITEM_BOX = 18
        private val RECIPE_SLOTS = 10
        private val RECIPE_SIZE = 3
        private val RECIPE_SIZE_TOTAL = 9
        private val ROW_SLOTS = 9
        private val FULL_INVENTORY_SLOTS = RECIPE_SLOTS + 36
        private val MAIN_INVENTORY_SLOTS = RECIPE_SLOTS + 27
    }
}