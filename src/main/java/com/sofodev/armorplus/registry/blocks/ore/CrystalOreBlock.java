package com.sofodev.armorplus.registry.blocks.ore;

import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.material.Material;

public class CrystalOreBlock extends OreBlock {

    public CrystalOreBlock(Variant variant) {
        super(Properties.of(Material.STONE).strength(variant.getHardness(), variant.getResistance()).requiresCorrectToolForDrops()
                .lightLevel((light) -> 8));
    }

}