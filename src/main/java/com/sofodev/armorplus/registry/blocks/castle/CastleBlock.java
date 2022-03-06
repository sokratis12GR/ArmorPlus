package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.world.level.block.Block;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy;

public class CastleBlock extends Block {

    public CastleBlock(Block block) {
        super(copy(block).requiresCorrectToolForDrops());
    }
}