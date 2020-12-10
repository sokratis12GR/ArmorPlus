/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.lavainfuser;

import com.sofodev.armorplus.common.registry.ModItems;
import com.sofodev.armorplus.common.registry.items.materials.ItemLavaCrystal;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import static com.sofodev.armorplus.common.tileentity.TileLavaInfuser.isItemFuel;

/**
 * @author Sokratis Fotkatzikis
 */
public class SlotLavaInfuserFuel extends Slot {

    public SlotLavaInfuserFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    public static boolean isAllowed(ItemStack stack) {
        return stack.getItem() == Items.LAVA_BUCKET || stack.getItem() == ModItems.itemLavaCrystal;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return isItemFuel(stack) || isAllowed(stack);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        int stackSize;
        if (isAllowed(stack)) {
            if (stack.getItem() == Items.LAVA_BUCKET) {
                stackSize = 1;
                return stackSize;
            } else if (stack.getItem() instanceof ItemLavaCrystal) {
                stackSize = 64;
                return stackSize;
            }
        }
        return super.getItemStackLimit(stack);
    }
}