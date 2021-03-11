package com.sofodev.armorplus.registry.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class APBlock extends Block {

    public APBlock(Material material, MaterialColor color, float hardness, float resistance, int lightLevel, int harvestLevel, ToolType tool) {
        super(Properties.create(material, color).hardnessAndResistance(hardness, resistance)
                .setLightLevel((light) -> lightLevel).harvestLevel(harvestLevel).harvestTool(tool)
        );
    }

    public APBlock(Material material, float hardness, float resistance, int harvestLevel, ToolType tool) {
        super(Properties.create(material).hardnessAndResistance(hardness, resistance).harvestLevel(harvestLevel).harvestTool(tool));
    }

    public APBlock(Material material, float hardness, float resistance, int harvestLevel) {
        super(Properties.create(material).hardnessAndResistance(hardness, resistance).harvestLevel(harvestLevel));
    }

    public APBlock(Material material, float hardness, float resistance) {
        super(Properties.create(material).hardnessAndResistance(hardness, resistance));
    }

    public APBlock(Material material) {
        super(Properties.create(material));
    }

    public APBlock(Properties props) {
        super(props);
    }
}