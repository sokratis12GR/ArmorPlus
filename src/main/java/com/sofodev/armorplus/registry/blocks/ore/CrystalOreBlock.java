package com.sofodev.armorplus.registry.blocks.ore;

import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;

import static net.minecraftforge.common.ToolType.PICKAXE;

public class CrystalOreBlock extends OreBlock {

    public CrystalOreBlock(Variant variant) {
        super(Properties.create(Material.ROCK).hardnessAndResistance(variant.getHardness(), variant.getResistance()).setRequiresTool()
                .setLightLevel((light) -> 8).harvestTool(PICKAXE).harvestLevel(variant.getHarvestLevel())
        );
    }

}