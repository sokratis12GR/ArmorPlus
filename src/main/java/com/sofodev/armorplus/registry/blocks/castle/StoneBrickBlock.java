package com.sofodev.armorplus.registry.blocks.castle;

import com.sofodev.armorplus.registry.blocks.APBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class StoneBrickBlock extends APBlock {

    public StoneBrickBlock(BrickColor color) {
        super(Properties.of(Material.STONE, color.get()).dynamicShape().requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    }
}