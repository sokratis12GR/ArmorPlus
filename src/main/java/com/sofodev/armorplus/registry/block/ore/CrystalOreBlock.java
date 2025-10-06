package com.sofodev.armorplus.registry.block.ore;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static net.minecraft.world.level.block.Blocks.STONE;

public class CrystalOreBlock extends Block {

    private final Variant variant;

    public CrystalOreBlock(Variant variant) {
        super(BlockBehaviour.Properties.ofFullCopy(STONE)
                .strength(variant.getHardness(), variant.getResistance())
                .requiresCorrectToolForDrops()
                .lightLevel((light) -> variant.getLightValue()));
        this.variant = variant;
    }

    public Variant getVariant() {
        return variant;
    }
}