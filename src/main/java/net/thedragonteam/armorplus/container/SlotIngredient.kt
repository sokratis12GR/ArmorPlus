/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container

import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.items.SlotItemHandler
import net.thedragonteam.armorplus.registry.ModItems.lavaCrystal
import net.thedragonteam.thedragonlib.util.ItemStackHandlerImproved
import net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack

class SlotIngredient(inventory: ItemStackHandlerImproved, number: Int, x: Int, y: Int) : SlotItemHandler(inventory, number, x, y) {

    override fun isItemValid(stack: ItemStack): Boolean {
        return super.isItemValid(stack) || stack == getItemStack(lavaCrystal) || stack == getItemStack(lavaCrystal, 1) || stack == getItemStack(Items.LAVA_BUCKET)
    }

    override fun getItemStackLimit(stack: ItemStack): Int {
        return 1
    }
}