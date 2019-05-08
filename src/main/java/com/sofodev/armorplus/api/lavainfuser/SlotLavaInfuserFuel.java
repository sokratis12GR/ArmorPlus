/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.lavainfuser;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import static com.sofodev.armorplus.common.registry.tileentity.TileLavaInfuser.isItemFuel;
import static com.sofodev.thedragonlib.util.ItemStackUtils.getItem;

/**
 * @author Sokratis Fotkatzikis
 */
public class SlotLavaInfuserFuel extends Slot {

    public SlotLavaInfuserFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    public static boolean isAllowed(ItemStack stack) {
        return stack.getItem() == Items.LAVA_BUCKET || stack.getItem() == getItem("lava_crystal");
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return isItemFuel(stack) || isAllowed(stack);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return isAllowed(stack) ? 1 : super.getItemStackLimit(stack);
    }
}