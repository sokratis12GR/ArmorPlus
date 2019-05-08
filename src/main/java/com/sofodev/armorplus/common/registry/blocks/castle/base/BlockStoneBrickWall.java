/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.blocks.castle.base;

import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.blocks;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockStoneBrickWall extends BlockWall {

    public BlockStoneBrickWall(BlockStoneBrick modelState) {
        super(Properties.create(Material.ROCK).hardnessAndResistance(5f, 10f));
    }

    @Override
    public int getHarvestLevel(IBlockState state) {
        return blocks.stone_bricks.getHarvestLevel();
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(IBlockState state) {
        return blocks.stone_bricks.getType();
    }

}

