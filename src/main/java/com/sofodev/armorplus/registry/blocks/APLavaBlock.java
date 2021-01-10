package com.sofodev.armorplus.registry.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class APLavaBlock extends Block {

    public APLavaBlock() {
        super(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(5.0f, 1000.0f)
                .setLightLevel((light) -> 8).harvestLevel(2).harvestTool(ToolType.PICKAXE).variableOpacity()
        );
    }

    public APLavaBlock(AbstractBlock.Properties props) {
        super(props.setLightLevel((light) -> 8).harvestLevel(2).harvestTool(ToolType.PICKAXE));
    }
}