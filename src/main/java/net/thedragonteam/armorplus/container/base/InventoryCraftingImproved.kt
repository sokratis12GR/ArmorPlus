/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container.base

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextComponentString
import net.minecraft.util.text.TextComponentTranslation

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
class InventoryCraftingImproved(

        /**
         * Class containing the callbacks for the events on_GUIClosed and on_CraftMaxtrixChanged.
         */
        private val eventHandler: Container,

        /**
         * the width of the crafting inventory
         */
        private val inventoryWidth: Int, private val inventoryHeight: Int) : InventoryCrafting(eventHandler, inventoryWidth, inventoryHeight), IInventory {
    private val stackList: NonNullList<ItemStack>

    init {
        this.stackList = NonNullList.withSize(inventoryWidth * inventoryHeight, ItemStack.EMPTY)
    }

    /**
     * Returns the number of slots in the inventory.
     */
    override fun getSizeInventory(): Int {
        return this.stackList.size
    }

    override fun isEmpty(): Boolean {
        return this.stackList.any { it.isEmpty }
    }

    /**
     * Returns the stack in the given slot.
     */
    override fun getStackInSlot(index: Int): ItemStack {
        return if (index >= this.sizeInventory) ItemStack.EMPTY else this.stackList[index]
    }

    /**
     * Gets the ItemStack in the slot specified.
     */
    override fun getStackInRowAndColumn(row: Int, column: Int): ItemStack {
        return if (row >= 0 && row < this.inventoryWidth && column >= 0 && column <= this.inventoryHeight) this.getStackInSlot(row + column * this.inventoryWidth) else ItemStack.EMPTY
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    override fun getName(): String {
        return "container.armorplus.crafting"
    }

    /**
     * Returns true if this thing is named
     */
    override fun hasCustomName(): Boolean {
        return false
    }

    /**
     * Get the formatted ChatComponent that will be used for the sender's username in chat
     */
    override fun getDisplayName(): ITextComponent {
        return if (this.hasCustomName()) TextComponentString(this.name) else TextComponentTranslation(this.name)
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    override fun removeStackFromSlot(index: Int): ItemStack {
        return ItemStackHelper.getAndRemove(this.stackList, index)
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    override fun decrStackSize(index: Int, count: Int): ItemStack {
        val itemstack = ItemStackHelper.getAndSplit(this.stackList, index, count)

        if (!itemstack.isEmpty) this.eventHandler.onCraftMatrixChanged(this)

        return itemstack
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    override fun setInventorySlotContents(index: Int, stack: ItemStack) {
        this.stackList[index] = stack
        this.eventHandler.onCraftMatrixChanged(this)
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    override fun getInventoryStackLimit(): Int {
        return 64
    }

    /**
     * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
     * hasn't changed and skip it.
     */
    override fun markDirty() {
    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    override fun isUsableByPlayer(player: EntityPlayer): Boolean {
        return true
    }

    override fun openInventory(player: EntityPlayer) {}

    override fun closeInventory(player: EntityPlayer) {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    override fun isItemValidForSlot(index: Int, stack: ItemStack): Boolean {
        return true
    }

    override fun getField(id: Int): Int {
        return 0
    }

    override fun setField(id: Int, value: Int) {}

    override fun getFieldCount(): Int {
        return 0
    }

    override fun clear() {
        this.stackList.clear()
    }

    override fun getHeight(): Int {
        return this.inventoryHeight
    }

    override fun getWidth(): Int {
        return this.inventoryWidth
    }
}