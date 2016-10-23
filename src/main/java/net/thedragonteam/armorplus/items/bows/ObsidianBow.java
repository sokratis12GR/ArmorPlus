/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.bows;

import net.minecraft.init.Blocks;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.items.base.BaseBow;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

/**
 * net.thedragonteam.armorplus.items.bows
 * ArmorPlus created by sokratis12GR on 4/19/2016.
 * - TheDragonTeam
 */
public class ObsidianBow extends BaseBow {

    public ObsidianBow() {
        super(1500, "obsidian_bow", 6.0F, Blocks.OBSIDIAN, ModBlocks.compressedObsidian, TextFormatting.DARK_GRAY, ModItems.obsidianBow);
    }
}
