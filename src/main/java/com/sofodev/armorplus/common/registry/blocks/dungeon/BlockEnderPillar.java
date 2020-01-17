/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.blocks.dungeon;

import com.sofodev.armorplus.common.iface.IModdedBlock;
import com.sofodev.armorplus.common.registry.blocks.BlockProperties;
import com.sofodev.armorplus.common.registry.blocks.HarvestProps;
import com.sofodev.armorplus.common.registry.blocks.base.BlockBase;
import com.sofodev.armorplus.common.registry.blocks.base.ToolType;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.sofodev.armorplus.common.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockEnderPillar extends BlockBase implements IModdedBlock {

    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");
    protected static AxisAlignedBB PILLAR_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
    protected static AxisAlignedBB PILLAR_COLLISION_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.750D, 1.0D, 0.750D);

    public BlockEnderPillar() {
        super(Material.ROCK, "ender_pillar", new BlockProperties(10000, 100, new HarvestProps(ToolType.PICKAXE, 4, true)));
        this.setDefaultState(this.blockState.getBaseState().withProperty(UP, Boolean.FALSE).withProperty(DOWN, Boolean.FALSE));
    }

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        BlockPos offset = pos.offset(facing);
        return canConnectTo(world, offset);
    }

    public boolean canConnectTo(IBlockAccess world, BlockPos pos) {
        BlockPos otherA = pos.offset(EnumFacing.UP);
        BlockPos otherB = pos.offset(EnumFacing.DOWN);
        IBlockState stateA = world.getBlockState(otherA);
        IBlockState stateB = world.getBlockState(otherB);
        return attachesTo(world, stateA, otherA, EnumFacing.UP.getOpposite()) && attachesTo(world, stateB, otherB, EnumFacing.DOWN.getOpposite());
    }

    public boolean canConnectTo(IBlockAccess world, BlockPos pos, EnumFacing dir) {
        BlockPos other = pos.offset(dir);
        IBlockState state = world.getBlockState(other);
        return attachesTo(world, state, other, dir.getOpposite());
    }

    public final boolean attachesTo(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing) {
        Block block = state.getBlock();
        BlockFaceShape blockfaceshape = state.getBlockFaceShape(world, pos, facing);
        return block == ForgeRegistries.BLOCKS.getValue(setRL("ender_pillar")) && blockfaceshape == BlockFaceShape.SOLID || blockfaceshape == BlockFaceShape.MIDDLE_POLE_THIN;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(UP, canConnectTo(worldIn, pos, EnumFacing.UP))
            .withProperty(DOWN, canConnectTo(worldIn, pos, EnumFacing.DOWN));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, UP, DOWN);
    }

    @SuppressWarnings("deprecation")
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return PILLAR_AABB;
    }

    //@SuppressWarnings("deprecation")
    //@Override
    //@SideOnly(Side.CLIENT)
    //public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
    //    return PILLAR_AABB.offset(pos);
    //}

    //@Nullable
    //@Override
    //public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
    //    return PILLAR_AABB;
    //}

    @SuppressWarnings("deprecation")
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.PURPLE;
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

    /**
     * Determines if an entity can path through this block
     */
    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return true;
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
        this.initModel(0);
    }
}
