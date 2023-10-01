package com.sofodev.armorplus.registry.blocks.ore;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static net.minecraft.world.level.block.Blocks.STONE;

public class CrystalOreBlock extends Block {

    public CrystalOreBlock(Variant variant) {
        super(BlockBehaviour.Properties.copy(STONE)
                .strength(variant.getHardness(), variant.getResistance())
                .requiresCorrectToolForDrops()
                .lightLevel((light) -> variant.getLightValue()));
    }

}