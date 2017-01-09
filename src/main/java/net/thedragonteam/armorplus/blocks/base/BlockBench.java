/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.blocks.benches.Benches;
import net.thedragonteam.armorplus.client.gui.GuiHandler;
import net.thedragonteam.armorplus.tileentity.TileEntityChampionBench;
import net.thedragonteam.armorplus.tileentity.TileEntityHighTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityUltiTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityWorkbench;

import javax.annotation.Nullable;

public class BlockBench extends BlockBase implements ITileEntityProvider {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public Benches benches;

    public BlockBench(Benches benches) {
        super(Material.IRON, benches.getName(), 1000.0F, 10.0F, ToolType.PICKAXE, 2);
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.benches = benches;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        switch (benches) {
            case WORKBENCH:
            case HIGH_TECH:
            case ULTI_TECH:
            case CHAMPION:
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
                break;
            case WORKBENCH_NEW:
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation("armorplus:workbench", "inventory"));
                break;
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        switch (benches) {
            case WORKBENCH:
                if (!worldIn.isRemote) {
                    playerIn.openGui(ArmorPlus.instance, GuiHandler.GUI_WORKBENCH, worldIn, pos.getX(), pos.getY(), pos.getZ());
                }
                break;
            case HIGH_TECH:
                if (!worldIn.isRemote) {
                    playerIn.openGui(ArmorPlus.instance, GuiHandler.GUI_HIGH_TECH_BENCH, worldIn, pos.getX(), pos.getY(), pos.getZ());
                }
                break;
            case ULTI_TECH:
                if (!worldIn.isRemote) {
                    playerIn.openGui(ArmorPlus.instance, GuiHandler.GUI_ULTI_TECH_BENCH, worldIn, pos.getX(), pos.getY(), pos.getZ());
                }
                break;
            case CHAMPION:
                if (!worldIn.isRemote) {
                    playerIn.openGui(ArmorPlus.instance, GuiHandler.GUI_CHAMPION_BENCH, worldIn, pos.getX(), pos.getY(), pos.getZ());
                }
                break;
            case WORKBENCH_NEW:
                if (!worldIn.isRemote) {
                    playerIn.openGui(ArmorPlus.instance, GuiHandler.GUI_WORKBENCH_NEW, worldIn, pos.getX(), pos.getY(), pos.getZ());
                }
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        IBlockState iblockstate = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
        iblockstate = iblockstate.withProperty(FACING, placer.getHorizontalFacing());
        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState();
        iblockstate = iblockstate.withProperty(FACING, EnumFacing.getHorizontal(meta));
        return iblockstate;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        switch (benches) {
            case WORKBENCH:
                return new TileEntityWorkbench();
            case HIGH_TECH:
                return new TileEntityHighTechBench();
            case ULTI_TECH:
                return new TileEntityUltiTechBench();
            case CHAMPION:
                return new TileEntityChampionBench();
            case WORKBENCH_NEW:
                return new TileEntityWorkbench();
        }
        return null;
    }
}
