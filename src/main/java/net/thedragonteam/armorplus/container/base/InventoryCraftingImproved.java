/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class InventoryCraftingImproved extends InventoryCrafting implements IInventory {
    private final NonNullList<ItemStack> stackList;
    /**
     * the width of the crafting inventory
     */
    private final int inventoryWidth;
    private final int inventoryHeight;
    /**
     * Class containing the callbacks for the events on_GUIClosed and on_CraftMaxtrixChanged.
     */
    private final Container eventHandler;

    public InventoryCraftingImproved(Container eventHandlerIn, int width, int height) {
        super(eventHandlerIn, width, height);
        this.stackList = NonNullList.withSize(width * height, ItemStack.EMPTY);
        this.eventHandler = eventHandlerIn;
        this.inventoryWidth = width;
        this.inventoryHeight = height;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return this.stackList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.stackList.stream().allMatch(ItemStack::isEmpty);
    }

    /**
     * Returns the stack in the given slot.
     */
    @Override
    @Nonnull
    public ItemStack getStackInSlot(int index) {
        return index >= this.getSizeInventory() ? ItemStack.EMPTY : this.stackList.get(index);
    }

    /**
     * Gets the ItemStack in the slot specified.
     */
    @Override
    @Nonnull
    public ItemStack getStackInRowAndColumn(int row, int column) {
        return row >= 0 && row < this.inventoryWidth && column >= 0 && column <= this.inventoryHeight ? this.getStackInSlot(row + column * this.inventoryWidth) : ItemStack.EMPTY;
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    @Nonnull
    @Override
    public String getName() {
        return "container.armorplus.crafting";
    }

    /**
     * Returns true if this thing is named
     */
    @Override
    public boolean hasCustomName() {
        return false;
    }

    /**
     * Get the formatted ChatComponent that will be used for the sender's username in chat
     */
    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    @Override
    @Nonnull
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.stackList, index);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    @Override
    @Nonnull
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = ItemStackHelper.getAndSplit(this.stackList, index, count);

        if (!itemstack.isEmpty()) this.eventHandler.onCraftMatrixChanged(this);

        return itemstack;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int index, @Nonnull ItemStack stack) {
        this.stackList.set(index, stack);
        this.eventHandler.onCraftMatrixChanged(this);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    /**
     * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
     * hasn't changed and skip it.
     */
    @Override
    public void markDirty() {
    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    @Override
    public boolean isUsableByPlayer(@Nonnull EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(@Nonnull EntityPlayer player) {
    }

    @Override
    public void closeInventory(@Nonnull EntityPlayer player) {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    @Override
    public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        this.stackList.clear();
    }

    @Override
    public int getHeight() {
        return this.inventoryHeight;
    }

    @Override
    public int getWidth() {
        return this.inventoryWidth;
    }
}