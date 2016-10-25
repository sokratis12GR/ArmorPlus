/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.castle.base;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.thedragonteam.armorplus.blocks.base.BaseBlock;

public class BaseStoneBrickTower extends BaseBlock {

    public MapColor mapColor;

    public BaseStoneBrickTower(String colorName, MapColor color) {
        super(Material.ROCK, colorName + "_stone_brick_tower", 10.0F, 5.0F, "pickaxe", 0);
        this.mapColor = color;
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state) {
        return mapColor;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
