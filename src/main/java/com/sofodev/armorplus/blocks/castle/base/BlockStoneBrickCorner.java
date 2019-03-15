/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.castle.base;

import com.sofodev.armorplus.blocks.castle.BrickColor;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.blocks;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockStoneBrickCorner extends BlockStairs {

    public BlockStoneBrickCorner(BrickColor brickColor, IBlockState modelState) {
        super(modelState, Properties.create(Material.ROCK).hardnessAndResistance(10.0f, 5.0f).lightValue(255));
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

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

}
