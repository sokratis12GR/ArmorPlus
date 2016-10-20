/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.thedragonteam.armorplus.ArmorPlus;

public class BaseBlock extends Block {

    public BaseBlock(Material material, String name) {
        this(material, name, 0F, 0F);
    }

    public BaseBlock(Material material, String name, float resistance, float hardness) {
        this(material, name, resistance, hardness, null, 0);
    }

    public BaseBlock(Material material, String name, float resistance, float hardness, String tool, int harvestLevel) {
        this(material, name, resistance, hardness, tool, harvestLevel, 0F);
    }

    public BaseBlock(Material material, String name, float resistance, float hardness, String tool, int harvestLevel, float lightLevel) {
        super(material);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        this.setResistance(resistance);
        this.setHardness(hardness);
        this.setHarvestLevel(tool, harvestLevel);
        this.setLightLevel(lightLevel);
        setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }
}
