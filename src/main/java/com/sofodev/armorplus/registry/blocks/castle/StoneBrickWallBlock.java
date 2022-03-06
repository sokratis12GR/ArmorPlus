package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.WallSide;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy;

public class StoneBrickWallBlock extends WallBlock {

    public static final BooleanProperty UP = BlockStateProperties.UP;

    public StoneBrickWallBlock(Block block) {
        super(copy(block).requiresCorrectToolForDrops());
        this.registerDefaultState(this.stateDefinition.any().setValue(UP, TRUE).setValue(NORTH_WALL, WallSide.NONE).setValue(EAST_WALL, WallSide.NONE).setValue(SOUTH_WALL, WallSide.NONE).setValue(WEST_WALL, WallSide.NONE).setValue(WATERLOGGED, FALSE));
    }
}