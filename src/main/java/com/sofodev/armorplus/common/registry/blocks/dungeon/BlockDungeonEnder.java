/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.blocks.dungeon;

import com.sofodev.armorplus.common.iface.IModdedBlock;
import com.sofodev.armorplus.common.registry.blocks.BlockProperties;
import com.sofodev.armorplus.common.registry.blocks.HarvestProps;
import com.sofodev.armorplus.common.registry.blocks.base.BlockBase;
import com.sofodev.armorplus.common.registry.blocks.base.ToolType;
import com.sofodev.armorplus.common.registry.constants.APSounds;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import static com.sofodev.armorplus.common.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockDungeonEnder extends BlockBase implements IModdedBlock {

    private EnderType enderBlocks;

    public BlockDungeonEnder(EnderType enderBlocks) {
        super(Material.ROCK, enderBlocks.getName(), new BlockProperties(10000, 100, new HarvestProps(ToolType.PICKAXE, 4, true), enderBlocks.getLightLevel()));
        this.enderBlocks = enderBlocks;
    }

    @SuppressWarnings("deprecation")
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.PURPLE;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (enderBlocks == EnderType.ENDER_STONE_TRAP && APSounds.TRAP_TRIGGERED != null) {
            worldIn.playSound(playerIn, pos, APSounds.TRAP_TRIGGERED, SoundCategory.BLOCKS, 0.5F, 0.0F);
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(0, "normal");
    }
}
