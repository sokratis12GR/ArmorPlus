/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.tileentity.wrappers;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;

/**
 * @author Sokratis Fotkatzikis
 */
public class AdvancedInventoryWrapper extends InvWrapper {

    private final boolean canInsert;
    private final boolean canExtract;

    public AdvancedInventoryWrapper(IInventory inv, boolean canInsert, boolean canExtract) {
        super(inv);
        this.canInsert = canInsert;
        this.canExtract = canExtract;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return !canInsert ? stack : super.insertItem(slot, stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return !canExtract ? ItemStack.EMPTY : super.extractItem(slot, amount, simulate);
    }
}
