/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.thedragonteam.armorplus.ArmorPlus;

public class BaseEnergyBlock extends BlockContainer {

    public BaseEnergyBlock(Material material, String name) {
        this(material, name, 0, 0);
    }

    public BaseEnergyBlock(Material material, String name, float resistance, float hardness) {
        this(material, name, resistance, hardness, null, 0);
    }

    public BaseEnergyBlock(Material material, String name, float resistance, float hardness, String tool, int harvestLevel) {
        this(material, name, resistance, hardness, tool, harvestLevel, 0F);
    }

    public BaseEnergyBlock(Material material, String name, float resistance, float hardness, String tool, int harvestLevel, float lightLevel) {
        super(material);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        setRegistryName(name);
        this.setResistance(resistance);
        this.setHardness(hardness);
        this.setHarvestLevel(tool, harvestLevel);
        this.setLightLevel(lightLevel);
        setCreativeTab(ArmorPlus.tabArmorplusTesla);
    }

    @Optional.Method(modid = "tesla")
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}
