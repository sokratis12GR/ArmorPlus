package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.block.Block;

import static net.minecraft.block.AbstractBlock.Properties.copy;

public class CastleBlock extends Block {

    public CastleBlock(Block block) {
        super(copy(block));
    }
}