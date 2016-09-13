/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.thedragonteam.armorplus.blocks.base.BaseBlock;

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 8/15/2016.
 * - TheDragonTeam
 */
public class LavaNetherBrick extends BaseBlock {

    public LavaNetherBrick() {
        super(Material.ROCK, "lava_nether_brick", 20.0F, 3.0F, "pickaxe", 1, 1.0F);
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state) {
        return MapColor.NETHERRACK;
    }
}