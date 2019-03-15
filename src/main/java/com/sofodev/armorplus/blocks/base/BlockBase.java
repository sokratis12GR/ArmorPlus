/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.base;

import com.sofodev.armorplus.blocks.BlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockBase extends Block {

    private final BlockProperties prop;

    public BlockBase(Material material, BlockProperties prop) {
        super(Properties.create(material).hardnessAndResistance(prop.getHardness(), prop.getResistance()).lightValue(prop.getLightOpacity())
        );
        this.prop = prop;
    }

    @Override
    public int getHarvestLevel(IBlockState state) {
        return prop.getHarvestLevel();
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(IBlockState state) {
        return prop.getToolType();
    }
}