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
import net.thedragonteam.armorplus.api.crafting.ultitechbench.SlotCrafting
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager
import net.thedragonteam.armorplus.container.base.ContainerBenchBase
import net.thedragonteam.armorplus.container.base.InventoryCraftingImproved
import net.thedragonteam.armorplus.tileentity.TileEntityUltiTechBench

/**
 * net.thedragonteam.armorplus.container
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:39 AM.
 * - TheDragonTeam
 */
class ContainerUltiTechBench(playerInventory: InventoryPlayer, tile: TileEntityUltiTechBench) : ContainerBenchBase(tile, ContainerUltiTechBench.RECIPE_SLOTS, ContainerUltiTechBench.MAIN_INVENTORY_SLOTS, ContainerUltiTechBench.FULL_INVENTORY_SLOTS) {
    /**
     * The crafting matrix inventory (5x5).
     */
    var craftMatrix = InventoryCraftingImproved(this, 5, 5)
    var craftResult: IInventory = InventoryCraftResult()

    init {
        this.addSlotToContainer(SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 150, 53))

        for (i in 0 .. RECIPE_SIZE - 1)
            for (j in 0 .. RECIPE_SIZE - 1)
                this.addSlotToContainer(Slot(this.craftMatrix, j + i * RECIPE_SIZE, 12 + j * ITEM_BOX, 17 + i * ITEM_BOX))

        for (k in 0 .. 2)
        //k = y
            for (i1 in 0 .. ROW_SLOTS - 1)
                this.addSlotToContainer(Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * ITEM_BOX, 118 + k * ITEM_BOX))

        for (l in 0 .. ROW_SLOTS - 1)
            this.addSlotToContainer(Slot(playerInventory, l, 8 + l * ITEM_BOX, 176))

        this.onCraftMatrixChanged(this.craftMatrix)
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    override fun onCraftMatrixChanged(inventoryIn: IInventory?) {
        this.craftResult.setInventorySlotContents(0, UltiTechBenchCraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.world))
    }

    /**
     * Called when the container is closed.
     */
    override fun onContainerClosed(playerIn: EntityPlayer) {
        super.onContainerClosed(playerIn)

        if (!this.world.isRemote) {
            (0 .. RECIPE_SIZE_TOTAL - 1)
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
        private val RECIPE_SLOTS = 26
        private val RECIPE_SIZE = 5
        private val RECIPE_SIZE_TOTAL = 25
        private val ROW_SLOTS = 9
        private val FULL_INVENTORY_SLOTS = RECIPE_SLOTS + 36
        private val MAIN_INVENTORY_SLOTS = RECIPE_SLOTS + 27
    }
}