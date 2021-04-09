package com.sofodev.armorplus.registry.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class APBlock extends Block {

    public APBlock(Material material, MaterialColor color, float hardness, float resistance, int lightLevel, int harvestLevel, ToolType tool) {
        super(Properties.of(material, color).strength(hardness, resistance)
                .lightLevel((light) -> lightLevel).harvestLevel(harvestLevel).harvestTool(tool)
        );
    }

    public APBlock(Material material, float hardness, float resistance, int harvestLevel, ToolType tool) {
        super(Properties.of(material).strength(hardness, resistance).harvestLevel(harvestLevel).harvestTool(tool));
    }

    public APBlock(Material material, float hardness, float resistance, int harvestLevel) {
        super(Properties.of(material).strength(hardness, resistance).harvestLevel(harvestLevel));
    }

    public APBlock(Material material, float hardness, float resistance) {
        super(Properties.of(material).strength(hardness, resistance));
    }

    public APBlock(Material material) {
        super(Properties.of(material));
    }

    public APBlock(Properties props) {
        super(props);
    }
}