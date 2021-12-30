package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class StoneBrickBlock extends Block {

    public StoneBrickBlock(BrickColor color) {
        super(Properties.of(Material.STONE, color.get()).dynamicShape());
    }
}