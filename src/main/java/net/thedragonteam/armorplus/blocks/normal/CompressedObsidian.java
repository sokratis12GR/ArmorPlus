/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks.normal;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.thedragonteam.armorplus.blocks.base.BaseBlock;

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:50 PM.
 * - TheDragonTeam
 */
public class CompressedObsidian extends BaseBlock {

    public CompressedObsidian() {
        super(Material.ROCK, "compressed_obsidian", 2000.0F, 50.0F, "pickaxe", 3);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state) {
        return MapColor.BLACK;
    }
}
