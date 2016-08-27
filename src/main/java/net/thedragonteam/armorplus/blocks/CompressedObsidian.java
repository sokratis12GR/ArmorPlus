/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.thedragonteam.armorplus.ArmorPlus;

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:50 PM.
 * - TheDragonTeam
 */
public class CompressedObsidian extends Block {

    public CompressedObsidian() {
        super(Material.ROCK);
        setUnlocalizedName(ArmorPlus.MODID + "." + "compressed_obsidian");
        this.setResistance(2000.0F);
        this.setCreativeTab(ArmorPlus.TAB_ARMORPLUS_BLOCKS);
        this.setHardness(50.0F);
        this.setHarvestLevel("pickaxe", 3);
    }
}
