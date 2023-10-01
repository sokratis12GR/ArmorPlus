package com.sofodev.armorplus.registry.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class APBlock extends Block {

    public APBlock(Block material, MapColor color, float hardness, float resistance, int lightLevel) {
        super(BlockBehaviour.Properties.copy(material)
                .mapColor(color)
                .strength(hardness, resistance)
                .lightLevel((light) -> lightLevel)
                .requiresCorrectToolForDrops());
    }

    public APBlock(Block material, float hardness, float resistance) {
        super(BlockBehaviour.Properties.copy(material).strength(hardness, resistance).requiresCorrectToolForDrops());
    }

    public APBlock(Block material) {
        super(BlockBehaviour.Properties.copy(material).requiresCorrectToolForDrops());
    }

    public APBlock(Properties props) {
        super(props.requiresCorrectToolForDrops());
    }
}