package com.sofodev.armorplus.registry.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class APFrostBlock extends Block {

    public APFrostBlock() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_BLUE).strength(5.0f, 1000.0f)
                .lightLevel((light) -> 8).harvestLevel(2).harvestTool(ToolType.PICKAXE)
        );
    }

    public APFrostBlock(Properties props) {
        super(props.lightLevel((light) -> 8).harvestLevel(2).harvestTool(ToolType.PICKAXE));
    }
}