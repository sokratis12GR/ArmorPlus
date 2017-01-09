/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.tileentity.base;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandler;
import net.thedragonteam.thedragonlib.util.ItemStackHandlerImproved;

public abstract class TileEntityInventoryBase extends TileEntityBase {

    public ItemStackHandlerImproved itemHandler;

    public TileEntityInventoryBase(int itemHandler) {
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