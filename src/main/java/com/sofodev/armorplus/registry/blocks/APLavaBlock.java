package com.sofodev.armorplus.registry.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static net.minecraft.world.level.block.Blocks.STONE;
import static net.minecraft.world.level.material.MapColor.COLOR_RED;

public class APLavaBlock extends Block {

    public APLavaBlock() {
        super(BlockBehaviour.Properties.copy(STONE).mapColor(COLOR_RED).strength(5.0f, 1000.0f).lightLevel((light) -> 8).dynamicShape().requiresCorrectToolForDrops());
    }

    public APLavaBlock(Properties props) {
        super(props.lightLevel((light) -> 8));
    }

}