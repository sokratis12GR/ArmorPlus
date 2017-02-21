/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

import static net.thedragonteam.armorplus.util.Utils.setName;

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
        this(material, name, resistance, hardness, tool, harvestLevel, lightLevel, 0);
    }

    public BaseBlock(Material material, String name, float resistance, float hardness, String tool, int harvestLevel, float lightLevel, int lightOpacity) {
        super(material);
        this.setUnlocalizedName(setName(name));
        this.setRegistryName(name);
        this.setResistance(resistance);
        this.setHardness(hardness);
        this.setHarvestLevel(tool, harvestLevel);
        this.setLightLevel(lightLevel);
        this.setLightOpacity(lightOpacity);
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
