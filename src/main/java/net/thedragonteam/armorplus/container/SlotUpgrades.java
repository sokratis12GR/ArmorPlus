/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotUpgrades extends Slot {

    /**
     * Make a new instance.
     *
     * @param inventory The inventory this slot will be in.
     * @param index     The index of this slot.
     * @param x         X coordinate.
     * @param y         POS_Y coordinate.
     */
    public SlotUpgrades(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return !itemStack.isEmpty();
    }
}