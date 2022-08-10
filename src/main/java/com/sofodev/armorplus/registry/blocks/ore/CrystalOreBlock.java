package com.sofodev.armorplus.registry.blocks.ore;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class CrystalOreBlock extends Block {

    public CrystalOreBlock(Variant variant) {
        super(BlockBehaviour.Properties.of(Material.STONE).strength(variant.getHardness(), variant.getResistance()).requiresCorrectToolForDrops()
                .lightLevel((light) -> variant.getLightValue()));
    }

}