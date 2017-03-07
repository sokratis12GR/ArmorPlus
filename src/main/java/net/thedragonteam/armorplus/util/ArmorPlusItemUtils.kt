/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.thedragonteam.armorplus.APConfig.getRD
import net.thedragonteam.thedragonlib.util.ItemStackUtils.getItem
import javax.annotation.Nullable

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
@Nullable
object ArmorPlusItemUtils {

    fun isItemRepairable(repair: ItemStack, easy: Item, expert: Item): Boolean {
        return getRD().isItemRepairable(repair, easy, expert)
    }

    fun isItemRepairable(repair: ItemStack, easy: ItemStack, expert: ItemStack): Boolean {
        return isItemRepairable(repair, getItem(easy), getItem(expert))
    }

    fun isItemRepairable(repair: ItemStack, easy: Block, expert: ItemStack): Boolean {
        return isItemRepairable(repair, getItem(easy), getItem(expert))
    }

    fun isItemRepairable(repair: ItemStack, easy: Block, expert: Block): Boolean {
        return isItemRepairable(repair, getItem(easy), getItem(expert))
    }

    fun isItemRepairable(repair: ItemStack, easy: ItemStack, expert: Block): Boolean {
        return isItemRepairable(repair, getItem(easy), getItem(expert))
    }

    fun isItemRepairable(repair: ItemStack, easy: ItemStack, expert: Item): Boolean {
        return isItemRepairable(repair, getItem(easy), expert)
    }

    fun isItemRepairable(repair: ItemStack, easy: Item, expert: ItemStack): Boolean {
        return isItemRepairable(repair, easy, getItem(expert))
    }

    fun isItemRepairable(repair: ItemStack, easy: Block, expert: Item): Boolean {
        return isItemRepairable(repair, getItem(easy), expert)
    }

    fun isItemRepairable(repair: ItemStack, easy: Item, expert: Block): Boolean {
        return isItemRepairable(repair, easy, getItem(expert))
    }
}
