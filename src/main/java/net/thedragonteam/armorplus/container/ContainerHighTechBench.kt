/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.InventoryCraftResult
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager
import net.thedragonteam.armorplus.api.crafting.hightechbench.SlotCrafting
import net.thedragonteam.armorplus.container.base.ContainerBenchBase
import net.thedragonteam.armorplus.container.base.InventoryCraftingImproved
import net.thedragonteam.armorplus.tileentity.TileEntityHighTechBench

/**
 * net.thedragonteam.armorplus.container
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:39 AM.
 * - TheDragonTeam
 */
class ContainerHighTechBench(playerInventory: InventoryPlayer, tile: TileEntityHighTechBench) : ContainerBenchBase(tile, ContainerHighTechBench.RECIPE_SLOTS, ContainerHighTechBench.MAIN_INVENTORY_SLOTS, ContainerHighTechBench.FULL_INVENTORY_SLOTS) {
    /**
     * The crafting matrix inventory (4x4).
     */
    var craftMatrix = InventoryCraftingImproved(this, 4, 4)
    var craftResult: IInventory = InventoryCraftResult()

    init {
        this.addSlotToContainer(SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 44))

        (0 until RECIPE_SIZE).forEach { i -> (0 until RECIPE_SIZE).forEach { j -> this.addSlotToContainer(Slot(this.craftMatrix, j + i * RECIPE_SIZE, 12 + j * ITEM_BOX, 17 + i * ITEM_BOX)) } }

        0.rangeTo(2).forEach { k -> (0 until ROW_SLOTS).forEach { i1 -> this.addSlotToContainer(Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * ITEM_BOX, 102 + k * ITEM_BOX)) } }
        (0 until ROW_SLOTS).forEach { l -> this.addSlotToContainer(Slot(playerInventory, l, 8 + l * ITEM_BOX, 160)) }

        this.onCraftMatrixChanged(this.craftMatrix)
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    override fun onCraftMatrixChanged(inventoryIn: IInventory?) {
        this.craftResult.setInventorySlotContents(0, HighTechBenchCraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.world))
    }

    /**
     * Called when the container is closed.
     */
    override fun onContainerClosed(playerIn: EntityPlayer) {
        super.onContainerClosed(playerIn)

        if (!this.world.isRemote) {
            (0 until RECIPE_SIZE_TOTAL)
                    .asSequence()
                    .map { this.craftMatrix.removeStackFromSlot(it) }
                    .filterNot { it.isEmpty }
                    .forEach { playerIn.dropItem(it, false) }
        }
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
        private val RECIPE_SLOTS = 17
        private val RECIPE_SIZE = 4
        private val RECIPE_SIZE_TOTAL = 16
        private val ROW_SLOTS = 9
        private val FULL_INVENTORY_SLOTS = RECIPE_SLOTS + 36
        private val MAIN_INVENTORY_SLOTS = RECIPE_SLOTS + 27
    }
}