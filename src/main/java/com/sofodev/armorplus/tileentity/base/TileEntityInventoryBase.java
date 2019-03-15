/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.tileentity.base;

import com.sofodev.thedragonlib.util.ItemStackHandlerImproved;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandler;

/**
 * @author Sokratis Fotkatzikis
 **/
public abstract class TileEntityInventoryBase extends TileEntityBase {

    public ItemStackHandlerImproved itemHandler;

    public TileEntityInventoryBase(TileEntityType<?> tileEntityTypeIn, int itemHandler) {
        super(tileEntityTypeIn);
        this.itemHandler = new ItemStackHandlerImproved(itemHandler) {
            @Override
            public boolean canInsert(ItemStack stack, int slot) {
                return TileEntityInventoryBase.this.isItemValidForSlot(slot, stack);
            }

            @Override
            public boolean canExtract(ItemStack stack, int slot) {
                return TileEntityInventoryBase.this.canExtractItem(slot, stack);
            }

            @Override
            public int getSlotLimit(int slot) {
                return TileEntityInventoryBase.this.getMaxStackSizePerSlot(slot);
            }

            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                TileEntityInventoryBase.this.markDirty();
            }
        };
    }

    @Override
    public IItemHandler getItemHandler(EnumFacing facing) {
        return this.itemHandler;
    }

    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }

    public boolean canExtractItem(int slot, ItemStack stack) {
        return true;
    }

    public int getMaxStackSizePerSlot(int slot) {
        return 64;
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }
}