package com.sofodev.armorplus.registry.blocks;

import net.minecraft.world.level.block.Block;

import static net.minecraft.world.level.block.Blocks.STONE;
import static net.minecraft.world.level.material.MapColor.COLOR_LIGHT_BLUE;

public class APFrostBlock extends Block {

    public APFrostBlock() {
        super(Properties.copy(STONE)
                .mapColor(COLOR_LIGHT_BLUE)
                .strength(5.0f, 1000.0f)
                .lightLevel((light) -> 8)
                .requiresCorrectToolForDrops());
    }

    public APFrostBlock(Properties props) {
        super(props.lightLevel((light) -> 8));
    }
}