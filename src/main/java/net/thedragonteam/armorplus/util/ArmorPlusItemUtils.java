/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ModConfig;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public final class ArmorPlusItemUtils {

    public static boolean isItemRepairable(ItemStack repair, ItemStack expert) {
        return ModConfig.getRD().isItemRepairable(repair, expert);
    }
}
