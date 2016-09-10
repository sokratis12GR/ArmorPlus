/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.thedragonteam.armorplus.ArmorPlus;

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:46 PM.
 * - TheDragonTeam
 */
public class WhiteStoneBrick extends Block {

    public WhiteStoneBrick() {
        super(Material.ROCK);
        setUnlocalizedName(ArmorPlus.MODID + "." + "white_stone_brick");
        this.setResistance(10.0F);
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
        this.setHardness(5.0F);
        this.setHarvestLevel("pickaxe", 0);
    }
}
