package com.sofodev.armorplus.registry.blocks.special;

import com.sofodev.armorplus.registry.blocks.APBlock;

import static net.minecraft.world.level.block.Blocks.IRON_BLOCK;

public class SoulBox extends APBlock {
    public SoulBox() {
        super(Properties.copy(IRON_BLOCK).strength(5f, 100f).requiresCorrectToolForDrops().lightLevel((i) -> 10));
    }
}
