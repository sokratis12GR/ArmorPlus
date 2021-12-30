package com.sofodev.armorplus.registry.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class APFrostBlock extends Block {

    public APFrostBlock() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_BLUE).strength(5.0f, 1000.0f).lightLevel((light) -> 8));
    }

    public APFrostBlock(Properties props) {
        super(props.lightLevel((light) -> 8));
    }
}