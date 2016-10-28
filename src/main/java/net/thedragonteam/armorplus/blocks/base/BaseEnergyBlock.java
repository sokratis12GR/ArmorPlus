/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

public class BaseEnergyBlock extends BlockContainer {

    public BaseEnergyBlock(Material material, String name, TileEntity tileEntityIn) {
        this(material, name, 0, 0, tileEntityIn);
    }

    public BaseEnergyBlock(Material material, String name, float resistance, float hardness, TileEntity tileEntityIn) {
        this(material, name, resistance, hardness, null, 0, tileEntityIn);
    }

    public BaseEnergyBlock(Material material, String name, float resistance, float hardness, String tool, int harvestLevel, TileEntity tileEntityIn) {
        this(material, name, resistance, hardness, tool, harvestLevel, 0F, tileEntityIn);
    }

    private TileEntity tileEntity;

    public BaseEnergyBlock(Material material, String name, float resistance, float hardness, String tool, int harvestLevel, float lightLevel, TileEntity tileEntityIn) {
        super(material);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        setRegistryName(name);
        this.tileEntity = tileEntityIn;
        this.setResistance(resistance);
        this.setHardness(hardness);
        this.setHarvestLevel(tool, harvestLevel);
        this.setLightLevel(lightLevel);
        setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return tileEntity;
    }
}
