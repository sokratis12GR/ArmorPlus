/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.castle.base;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.blocks.base.BaseBlock;

public class BaseCastleBlock extends BaseBlock {

    public MapColor color;

    public BaseCastleBlock(String colorName, MapColor mapColor) {
        super(Material.ROCK, colorName + "_stone_brick", 10.0F, 5.0F, "pickaxe", 0);
        this.color = mapColor;
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state) {
        return color;
    }
}
