package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WallHeight;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static net.minecraft.block.AbstractBlock.Properties.copy;

public class StoneBrickWallBlock extends WallBlock {

    public static final BooleanProperty UP = BlockStateProperties.UP;

    public StoneBrickWallBlock(Block block) {
        super(copy(block));
        this.registerDefaultState(this.stateDefinition.any().setValue(UP, TRUE).setValue(NORTH_WALL, WallHeight.NONE).setValue(EAST_WALL, WallHeight.NONE).setValue(SOUTH_WALL, WallHeight.NONE).setValue(WEST_WALL, WallHeight.NONE).setValue(WATERLOGGED, FALSE));
    }
}