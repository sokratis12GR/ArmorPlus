/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.bows;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.items.base.BaseBow;
import net.thedragonteam.armorplus.registry.ModItems;

/**
 * net.thedragonteam.armorplus.items.bows
 * ArmorPlus created by sokratis12GR on 4/19/2016.
 * - TheDragonTeam
 */
public class LapisBow extends BaseBow {

    public LapisBow() {
        super(250, "lapis_bow", 3.5F, new ItemStack(Items.DYE, 1, 4), Blocks.LAPIS_BLOCK, TextFormatting.DARK_BLUE, ModItems.lapisBow);
    }
}
