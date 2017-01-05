/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerImproved extends ItemStackHandler {

    private boolean tempIgnoreConditions;

    public ItemStackHandlerImproved(int slots) {
        super(slots);
    }

    public NonNullList<ItemStack> getItems() {
        return this.stacks;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (!this.tempIgnoreConditions && !this.canInsert(stack, slot)) {
            return stack;
        }

        return super.insertItem(slot, stack, simulate);
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (!this.tempIgnoreConditions && !this.canExtract(this.getStackInSlot(slot), slot)) {
            return ItemStackUtils.getNull();
        }

        return super.extractItem(slot, amount, simulate);
    }

    public boolean canInsert(ItemStack stack, int slot) {
        return true;
    }

    public boolean canExtract(ItemStack stack, int slot) {
        return true;
    }
}