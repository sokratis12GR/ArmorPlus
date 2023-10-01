package com.sofodev.armorplus.registry.blocks.castle;

import com.sofodev.armorplus.registry.blocks.APBlock;

import static net.minecraft.world.level.block.Blocks.STONE;

public class StoneBrickBlock extends APBlock {

    public StoneBrickBlock(BrickColor color) {
        super(Properties.copy(STONE)
                .mapColor(color.get()).dynamicShape().requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    }
}