/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.thedragonteam.armorplus.ArmorPlus;

/**
 * sokratis12gr.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:46 PM.
 */
public class SteelBlock extends Block {

    public SteelBlock() {
        super(Material.IRON);
        setUnlocalizedName("SteelBlock");
        this.setResistance(20.0F);
        this.setCreativeTab(ArmorPlus.TAB_ARMORPLUS_BLOCKS);
        this.setHardness(5.0F);
        this.setHarvestLevel("pickaxe", 1);
    }
}
