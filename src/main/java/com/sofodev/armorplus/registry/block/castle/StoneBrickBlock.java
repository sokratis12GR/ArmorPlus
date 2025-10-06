package com.sofodev.armorplus.registry.block.castle;

import com.sofodev.armorplus.registry.block.APBlock;

import static net.minecraft.world.level.block.Blocks.STONE;

public class StoneBrickBlock extends APBlock {

    public StoneBrickBlock(BrickColor color) {
        super(Properties.ofFullCopy(STONE)
                .mapColor(color.get()).dynamicShape().requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    }
}