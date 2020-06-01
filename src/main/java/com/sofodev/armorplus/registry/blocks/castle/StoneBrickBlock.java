package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import static net.minecraft.block.Block.Properties.create;
import static net.minecraftforge.common.ToolType.PICKAXE;

public class StoneBrickBlock extends Block {

    public StoneBrickBlock(BrickColor color) {
        super(create(Material.ROCK, color.get()).harvestTool(PICKAXE).hardnessAndResistance(5, 10)
                .variableOpacity().harvestLevel(0)
        );
    }
}
