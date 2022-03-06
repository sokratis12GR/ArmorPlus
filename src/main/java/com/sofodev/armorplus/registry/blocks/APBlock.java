package com.sofodev.armorplus.registry.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class APBlock extends Block {

    public APBlock(Material material, MaterialColor color, float hardness, float resistance, int lightLevel) {
        super(Properties.of(material, color).strength(hardness, resistance).lightLevel((light) -> lightLevel).requiresCorrectToolForDrops());
    }

    public APBlock(Material material, float hardness, float resistance) {
        super(Properties.of(material).strength(hardness, resistance).requiresCorrectToolForDrops());
    }

    public APBlock(Material material) {
        super(Properties.of(material).requiresCorrectToolForDrops());
    }

    public APBlock(Properties props) {
        super(props.requiresCorrectToolForDrops());
    }
}