/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class SlotArmor
/**
 * Make a new instance.

 * @param inventory The inventory this slot will be in.
 * *
 * @param index     The index of this slot.
 * *
 * @param x         X coordinate.
 * *
 * @param y         POS_Y coordinate.
 * *
 * @param player    The player entity.
 * *
 * @param armorType The armor type.
 */
(inventory: IInventory, index: Int, x: Int, y: Int, private val player: EntityPlayer, private val armorType: EntityEquipmentSlot) : Slot(inventory, index, x, y) {

    override fun getSlotStackLimit(): Int {
        return 1
    }

    override fun isItemValid(itemStack: ItemStack?): Boolean {
        return !itemStack!!.isEmpty && itemStack.item.isValidArmor(itemStack, armorType, player)
    }

    @SideOnly(Side.CLIENT)
    override fun getSlotTexture(): String? {
        return ItemArmor.EMPTY_SLOT_NAMES[armorType.index]
    }

}