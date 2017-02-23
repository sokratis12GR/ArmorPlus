/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.util

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

/**
 * net.thedragonteam.armorplus.api.util
 * ArmorPlus created by sokratis12GR on 6/26/2016 1:08 PM.
 * - TheDragonTeam
 */
object NBTHelper {

    fun checkNBT(stack: ItemStack): ItemStack {
        when {
            stack.tagCompound == null -> stack.tagCompound = NBTTagCompound()
        }
        return stack
    }
}