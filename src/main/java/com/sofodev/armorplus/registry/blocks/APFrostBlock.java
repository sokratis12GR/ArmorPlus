package com.sofodev.armorplus.registry.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class APFrostBlock extends Block {

    public APFrostBlock() {
        super(Properties.create(Material.ROCK, MaterialColor.LIGHT_BLUE).hardnessAndResistance(5.0f, 1000.0f)
            .lightValue(8).harvestLevel(2).harvestTool(ToolType.PICKAXE)
        );
    }

    public APFrostBlock(Properties props) {
        super(props.lightValue(8).harvestLevel(2).harvestTool(ToolType.PICKAXE));
    }
}
