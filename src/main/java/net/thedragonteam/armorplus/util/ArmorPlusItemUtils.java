/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.APConfig;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class ArmorPlusItemUtils {

    public static boolean isItemRepairable(ItemStack repair, ItemStack expert) {
        return APConfig.getRD().isItemRepairable(repair, expert);
    }
}
