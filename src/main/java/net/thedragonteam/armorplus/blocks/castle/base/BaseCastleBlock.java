/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.castle.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.thedragonteam.armorplus.ArmorPlus;

public class BaseCastleBlock extends Block {

    public BaseCastleBlock(String name) {
        super(Material.ROCK);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        this.setResistance(10.0F);
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
        this.setHardness(5.0F);
        this.setHarvestLevel("pickaxe", 0);
    }
}
