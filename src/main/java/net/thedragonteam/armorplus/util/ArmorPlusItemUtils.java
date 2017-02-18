/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.thedragonteam.armorplus.APConfig.getRD;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItem;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class ArmorPlusItemUtils {

    public static boolean isItemRepairable(ItemStack repair, Item easy, Item expert) {
        return getRD().isItemRepairable(repair, easy, expert);
    }

    public static boolean isItemRepairable(ItemStack repair, ItemStack easy, ItemStack expert) {
        return isItemRepairable(repair, getItem(easy), getItem(expert));
    }

    public static boolean isItemRepairable(ItemStack repair, Block easy, ItemStack expert) {
        return isItemRepairable(repair, getItem(easy), getItem(expert));
    }

    public static boolean isItemRepairable(ItemStack repair, Block easy, Block expert) {
        return isItemRepairable(repair, getItem(easy), getItem(expert));
    }

    public static boolean isItemRepairable(ItemStack repair, ItemStack easy, Block expert) {
        return isItemRepairable(repair, getItem(easy), getItem(expert));
    }

    public static boolean isItemRepairable(ItemStack repair, ItemStack easy, Item expert) {
        return isItemRepairable(repair, getItem(easy), expert);
    }

    public static boolean isItemRepairable(ItemStack repair, Item easy, ItemStack expert) {
        return isItemRepairable(repair, easy, getItem(expert));
    }

    public static boolean isItemRepairable(ItemStack repair, Block easy, Item expert) {
        return isItemRepairable(repair, getItem(easy), expert);
    }

    public static boolean isItemRepairable(ItemStack repair, Item easy, Block expert) {
        return isItemRepairable(repair, easy, getItem(expert));
    }
}
