/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.thedragonteam.armorplus.ArmorPlus;

public class BaseBlock extends Block {

    public BaseBlock(Material material, String name) {
        super(material);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }

    public BaseBlock(Material material, String name, float resistance, float hardness) {
        super(material);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        this.setResistance(resistance);
        this.setHardness(hardness);
        setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }

    public BaseBlock(Material material, String name, float resistance, float hardness, String tool, int harvestLevel) {
        super(material);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        this.setResistance(resistance);
        this.setHardness(hardness);
        this.setHarvestLevel(tool, harvestLevel);
        setCreativeTab(ArmorPlus.tabArmorplusBlocks);
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
