package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;

import static net.minecraft.block.Block.Properties.from;

public class StoneBrickWallBlock extends WallBlock {

    public static final BooleanProperty UP = BlockStateProperties.UP;

    public StoneBrickWallBlock(Block block) {
        super(from(block));
        this.setDefaultState(this.stateContainer.getBaseState().with(UP, Boolean.TRUE).with(NORTH, Boolean.FALSE)
                .with(EAST, Boolean.FALSE).with(SOUTH, Boolean.FALSE).with(WEST, Boolean.FALSE).with(WATERLOGGED, Boolean.FALSE)
        );
    }
}