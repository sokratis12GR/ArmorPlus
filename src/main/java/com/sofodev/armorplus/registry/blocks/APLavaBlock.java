package com.sofodev.armorplus.registry.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class APLavaBlock extends Block {

    public APLavaBlock() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(5.0f, 1000.0f).lightLevel((light) -> 8).dynamicShape());
    }

    public APLavaBlock(Properties props) {
        super(props.lightLevel((light) -> 8));
    }

}