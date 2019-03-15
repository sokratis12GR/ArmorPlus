/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.castle.base;

import com.sofodev.armorplus.blocks.BlockProperties;
import com.sofodev.armorplus.blocks.base.BlockBase;
import com.sofodev.armorplus.blocks.castle.BrickColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;

import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.blocks;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockStoneBrickTower extends BlockBase {

    public BlockStoneBrickTower(BrickColor brickColor) {
        super(Material.ROCK,  new BlockProperties(10.0f, 5.0f, blocks.stone_bricks));
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
