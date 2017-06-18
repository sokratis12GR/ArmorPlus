/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container

import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

/**
 * Make a new instance.

 * @param inventory The inventory this slot will be in.
 * *
 * @param index     The index of this slot.
 * *
 * @param x         X coordinate.
 * *
 * @param y         POS_Y coordinate.
 */
class SlotUpgrades(inventory: IInventory, index: Int, x: Int, y: Int) : Slot(inventory, index, x, y) {

    override fun getSlotStackLimit(): Int {
        return 1
    }

    override fun isItemValid(itemStack: ItemStack): Boolean {
        return !itemStack.isEmpty
    }
}