package com.sofodev.armorplus.registry.block.castle;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.WallSide;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class StoneBrickWallBlock extends WallBlock {

    public static final BooleanProperty UP = BlockStateProperties.UP;

    public StoneBrickWallBlock(Block block) {
        super(com.sofodev.armorplus.registry.RegistryContext.blockProperties(ofFullCopy(block).requiresCorrectToolForDrops()));
    }
}