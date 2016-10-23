/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.bows;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.items.base.BaseBow;
import net.thedragonteam.armorplus.registry.ModItems;

/**
 * net.thedragonteam.armorplus.items.bows
 * ArmorPlus created by sokratis12GR on 4/19/2016.
 * - TheDragonTeam
 */
public class CoalBow extends BaseBow {

    public CoalBow() {
        super(59, "coal_bow", 2.0F, Items.COAL, Item.getItemFromBlock(Blocks.COAL_BLOCK), TextFormatting.GRAY, ModItems.coalBow);
    }
}
