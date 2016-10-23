/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.bows;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.items.base.BaseBow;
import net.thedragonteam.armorplus.registry.ModItems;

/**
 * net.thedragonteam.armorplus.items.bows
 * ArmorPlus created by sokratis12GR on 4/19/2016.
 * - TheDragonTeam
 */
public class RedstoneBow extends BaseBow {

    public RedstoneBow() {
        super(200, "redstone_bow", 3.5F, Items.REDSTONE, Blocks.REDSTONE_BLOCK, TextFormatting.DARK_RED, ModItems.redstoneBow);
    }
}
