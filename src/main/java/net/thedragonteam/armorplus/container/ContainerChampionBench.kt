/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.InventoryCraftResult
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.thedragonteam.armorplus.api.crafting.championbench.ChampionBenchCraftingManager
import net.thedragonteam.armorplus.api.crafting.ultitechbench.SlotCrafting
import net.thedragonteam.armorplus.container.base.ContainerBenchBase
import net.thedragonteam.armorplus.container.base.InventoryCraftingImproved
import net.thedragonteam.armorplus.tileentity.TileEntityChampionBench

/**
 * net.thedragonteam.armorplus.container
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:39 AM.
 * - TheDragonTeam
 */
class ContainerChampionBench(playerInventory: InventoryPlayer, tile: TileEntityChampionBench) : ContainerBenchBase(tile, ContainerChampionBench.RECIPE_SLOTS, ContainerChampionBench.MAIN_INVENTORY_SLOTS, ContainerChampionBench.FULL_INVENTORY_SLOTS) {
    /**
     * The crafting matrix inventory (10x10).
     */
    var craftMatrix = InventoryCraftingImproved(this, 10, 10)
    var craftResult: IInventory = InventoryCraftResult()

    init {
        this.world = tile.world
        //1x1 Output Inventory
        this.addSlotToContainer(SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 230, 134))

        //10x10 Crafting Inventory
        (0 until RECIPE_SIZE).forEach { i -> (0 until RECIPE_SIZE).forEach { j -> this.addSlotToContainer(Slot(this.craftMatrix, j + i * RECIPE_SIZE, 39 + j * ITEM_BOX, 17 + i * ITEM_BOX)) } }
        //2x2 Armor Inventory
        this.addPlayerArmorInventoryTop(playerInventory, 5, 217)
        this.addPlayerArmorInventoryBot(playerInventory, 5, 235)
        //3x9 Main Inventory
        0.rangeTo(2).forEach { k -> (0 until ROW_SLOTS).forEach { i1 -> this.addSlotToContainer(Slot(playerInventory, i1 + k * 9 + 9, 48 + i1 * ITEM_BOX, 199 + k * ITEM_BOX)) } }

        //1x9 HotBar Inventory
        (0 until ROW_SLOTS).forEach { l -> this.addSlotToContainer(Slot(playerInventory, l, 5, 17 + l * ITEM_BOX)) }

        this.onCraftMatrixChanged(this.craftMatrix)
    }

    private fun addPlayerArmorInventoryTop(inventory: InventoryPlayer, xPos: Int, yPos: Int) {
        for (k in 0.rangeTo(1)) {
            val equipmentSlot = EQUIPMENT_SLOTS[k]
            addSlotToContainer(SlotArmor(inventory, 4 * 9 + (3 - k), xPos + k * ITEM_BOX, yPos, inventory.player, equipmentSlot))
        }
    }

    private fun addPlayerArmorInventoryBot(inventory: InventoryPlayer, xPos: Int, yPos: Int) {
        for (k in 0.rangeTo(1)) {
            val equipmentSlot = EQUIPMENT_SLOTS[k + 2]
            addSlotToContainer(SlotArmor(inventory, 4 * 9 + (3 - (k + 2)), xPos + k * ITEM_BOX, yPos, inventory.player, equipmentSlot))
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    override fun onCraftMatrixChanged(inventoryIn: IInventory?) {
        this.craftResult.setInventorySlotContents(0, ChampionBenchCraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.world))
    }

    /**
     * Called when the container is closed.
     */
    override fun onContainerClosed(playerIn: EntityPlayer) {
        super.onContainerClosed(playerIn)

        if (!this.world.isRemote) {
            (0 until RECIPE_SIZE_TOTAL).asSequence().map { this.craftMatrix.removeStackFromSlot(it) }.filterNot { it.isEmpty }.forEach { playerIn.dropItem(it, false) }
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
        private val RECIPE_SLOTS = 101
        private val RECIPE_SIZE = 10
        private val RECIPE_SIZE_TOTAL = 100
        private val ROW_SLOTS = 9
        private val FULL_INVENTORY_SLOTS = RECIPE_SLOTS + 36
        private val MAIN_INVENTORY_SLOTS = RECIPE_SLOTS + 27
        private val EQUIPMENT_SLOTS = arrayOf(EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET)
    }
}