package com.sofodev.armorplus.registry.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class APLavaBlock extends Block {

    public APLavaBlock() {
        super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(5.0f, 1000.0f)
                .lightLevel((light) -> 8).harvestLevel(2).harvestTool(ToolType.PICKAXE).dynamicShape()
        );
    }

    public APLavaBlock(AbstractBlock.Properties props) {
        super(props.lightLevel((light) -> 8).harvestLevel(2).harvestTool(ToolType.PICKAXE));
    }


}