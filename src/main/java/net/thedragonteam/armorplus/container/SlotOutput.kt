/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container

import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.SlotItemHandler

class SlotOutput(itemHandler: IItemHandler, index: Int, xPosition: Int, yPosition: Int) : SlotItemHandler(itemHandler, index, xPosition, yPosition) {

    override fun isItemValid(stack: ItemStack): Boolean {
        return false
    }
}