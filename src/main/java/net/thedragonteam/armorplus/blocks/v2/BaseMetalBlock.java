/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.v2;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.base.BlockBase;

import javax.annotation.Nonnull;

public class BaseMetalBlock extends BlockBase {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public Block beaconBase;

    public BaseMetalBlock(Metals metals) {
        super(Material.IRON, metals.getName() + "_block", 20.0F, 5.0F, ToolType.PICKAXE, 1);
        this.beaconBase = metals.getBeaconBase();
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
        return this == beaconBase;
    }

    @Override
    @Nonnull
    public IBlockState getStateForPlacement(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ, int meta, @Nonnull EntityLivingBase placer, EnumHand hand) {
        IBlockState iblockstate = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
        iblockstate = iblockstate.withProperty(FACING, placer.getHorizontalFacing());
        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState();
        iblockstate = iblockstate.withProperty(FACING, EnumFacing.getHorizontal(meta));
        return iblockstate;
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public IBlockState withRotation(@Nonnull IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

}
