/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.bows;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.items.base.BaseBow;
import net.thedragonteam.armorplus.registry.ModItems;

/**
 * net.thedragonteam.armorplus.items.bows
 * ArmorPlus created by sokratis12GR on 4/19/2016.
 * - TheDragonTeam
 */
public class LavaBow extends BaseBow {

    public LavaBow() {
        super(1750, "lava_bow", 7.0F, ModItems.lavaCrystal, new ItemStack(ModItems.lavaCrystal, 1, 1), TextFormatting.GOLD, ModItems.lavaBow);
    }
}
