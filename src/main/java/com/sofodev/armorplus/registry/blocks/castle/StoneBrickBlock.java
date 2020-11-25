package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import static net.minecraftforge.common.ToolType.PICKAXE;

import net.minecraft.block.AbstractBlock.Properties;

public class StoneBrickBlock extends Block {

    public StoneBrickBlock(BrickColor color) {
        super(Properties.create(Material.ROCK, color.get()).harvestTool(PICKAXE).hardnessAndResistance(5, 10)
                .variableOpacity().harvestLevel(0)
        );
    }
}