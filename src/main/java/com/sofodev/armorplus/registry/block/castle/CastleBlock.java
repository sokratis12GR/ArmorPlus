package com.sofodev.armorplus.registry.block.castle;

import net.minecraft.world.level.block.Block;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class CastleBlock extends Block {

    public CastleBlock(Block block) {
        super(com.sofodev.armorplus.registry.RegistryContext.blockProperties(ofFullCopy(block).requiresCorrectToolForDrops()));
    }
}