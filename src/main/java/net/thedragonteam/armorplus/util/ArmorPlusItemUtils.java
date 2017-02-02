/*
 * Copyright (c) TheDragonTeam 2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.thedragonteam.armorplus.APConfig.getRD;
import static net.thedragonteam.armorplus.util.ItemStackUtils.getItemStack;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class ArmorPlusItemUtils {

    public static boolean isItemRepairable(ItemStack repair, ItemStack easy, ItemStack expert) {
        switch (getRD()) {
            case EASY:
                return repair == easy;
            case EXPERT:
                return repair == expert;
            case HELLISH:
                return false;
        }
        return false;
    }

    public static boolean isItemRepairable(ItemStack repair, Item easy, Item expert) {
        return isItemRepairable(repair, getItemStack(easy), getItemStack(expert));
    }

    public static boolean isItemRepairable(ItemStack repair, Block easy, ItemStack expert) {
        return isItemRepairable(repair, getItemStack(easy), expert);
    }

    public static boolean isItemRepairable(ItemStack repair, Block easy, Block expert) {
        return isItemRepairable(repair, getItemStack(easy), getItemStack(expert));
    }

    public static boolean isItemRepairable(ItemStack repair, ItemStack easy, Block expert) {
        return isItemRepairable(repair, easy, getItemStack(expert));
    }

    public static boolean isItemRepairable(ItemStack repair, ItemStack easy, Item expert) {
        return isItemRepairable(repair, easy, getItemStack(expert));
    }

    public static boolean isItemRepairable(ItemStack repair, Item easy, ItemStack expert) {
        return isItemRepairable(repair, getItemStack(easy), expert);
    }

    public static boolean isItemRepairable(ItemStack repair, Block easy, Item expert) {
        return isItemRepairable(repair, getItemStack(easy), getItemStack(expert));
    }

    public static boolean isItemRepairable(ItemStack repair, Item easy, Block expert) {
        return isItemRepairable(repair, getItemStack(easy), getItemStack(expert));
    }
}
