/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks.v2;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.thedragonteam.armorplus.ArmorPlus;

public class BaseMetalBlock extends Block {

    public BaseMetalBlock(String name) {
        super(Material.IRON);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        this.setResistance(20.0F);
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
        this.setHardness(5.0F);
        this.setHarvestLevel("pickaxe", 1);
    }
}
