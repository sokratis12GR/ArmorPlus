/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.lava;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.thedragonteam.armorplus.blocks.base.BlockBase;

import javax.annotation.Nonnull;

public class BlockLavaInfusedObsidian extends BlockBase {

    public BlockLavaInfusedObsidian() {
        super(Material.ROCK, "lava_infused_obsidian", 2000.0F, 25.0F, ToolType.PICKAXE, 2, 0.8F);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public MapColor getMapColor(IBlockState state) {
        return MapColor.RED;
    }
}
