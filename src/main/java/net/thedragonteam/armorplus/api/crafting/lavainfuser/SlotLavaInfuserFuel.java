/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.lavainfuser;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser;

public class SlotLavaInfuserFuel extends Slot {
    public SlotLavaInfuserFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack) {
        return TileEntityLavaInfuser.isItemFuel(stack) || isLavaBucket(stack);
    }

    public int getItemStackLimit(ItemStack stack) {
        return isLavaBucket(stack) ? 1 : super.getItemStackLimit(stack);
    }

    public static boolean isLavaBucket(ItemStack stack) {
        return stack.getItem() == Items.LAVA_BUCKET;
    }
}