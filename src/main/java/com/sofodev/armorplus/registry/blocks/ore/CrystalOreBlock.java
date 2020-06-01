package com.sofodev.armorplus.registry.blocks.ore;

import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;

import static net.minecraftforge.common.ToolType.PICKAXE;

public class CrystalOreBlock extends OreBlock {

    public CrystalOreBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(25f, 2000f)
                .lightValue(8).harvestTool(PICKAXE).harvestLevel(3)
        );
    }

}