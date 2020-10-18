package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WallHeight;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static net.minecraft.block.AbstractBlock.Properties.from;

public class StoneBrickWallBlock extends WallBlock {

    public static final BooleanProperty UP = BlockStateProperties.UP;

    public StoneBrickWallBlock(Block block) {
        super(from(block));
        this.setDefaultState(this.stateContainer.getBaseState().with(UP, TRUE).with(WALL_HEIGHT_NORTH, WallHeight.NONE).with(WALL_HEIGHT_EAST, WallHeight.NONE).with(WALL_HEIGHT_SOUTH, WallHeight.NONE).with(WALL_HEIGHT_WEST, WallHeight.NONE).with(WATERLOGGED, FALSE));
    }
}