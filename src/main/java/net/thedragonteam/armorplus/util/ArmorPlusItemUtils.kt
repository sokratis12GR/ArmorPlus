/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util

import net.minecraft.item.ItemStack
import net.thedragonteam.armorplus.APConfig.getRD
import javax.annotation.Nullable

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
@Nullable
object ArmorPlusItemUtils {

    fun isItemRepairable(repair: ItemStack, easy: ItemStack, expert: ItemStack): Boolean {
        return getRD().isItemRepairable(repair, easy, expert)
    }
}
