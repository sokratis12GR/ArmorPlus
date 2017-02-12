/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.blocks.benches.Benches;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.tileentity.TileEntityChampionBench;
import net.thedragonteam.armorplus.tileentity.TileEntityHighTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityUltiTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityWorkbench;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.thedragonteam.armorplus.blocks.benches.Benches.*;
import static net.thedragonteam.armorplus.client.gui.GuiHandler.*;

public class BlockBench extends BlockBase implements ITileEntityProvider, IModelHelper {

    private static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public Benches benches;

    private int[] guiNumber = new int[]{
            GUI_WORKBENCH, GUI_HIGH_TECH_BENCH, GUI_ULTI_TECH_BENCH, GUI_CHAMPION_BENCH, GUI_WORKBENCH_NEW
    };
    private Benches[] bench = new Benches[]{
            WORKBENCH, HIGH_TECH, ULTI_TECH, CHAMPION, WORKBENCH_NEW
    };
    private TileEntity[] tileEntities = new TileEntity[]{
            new TileEntityWorkbench(), new TileEntityHighTechBench(), new TileEntityUltiTechBench(), new TileEntityChampionBench(), new TileEntityWorkbench()
    };

    public BlockBench(Benches benches) {
        super(Material.IRON, benches.getName(), 1000.0F, 10.0F, ToolType.PICKAXE, 2);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.benches = benches;
    }

    public void initModel() {
        switch (benches) {
            case WORKBENCH:
            case HIGH_TECH:
            case ULTI_TECH:
            case CHAMPION:
                this.initModel(this, getRegistryName(), 0);
                break;
            case WORKBENCH_NEW:
                this.initModel(this, "armorplus:workbench", 0);
                break;
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        for (int i = 0; i < guiNumber.length; i++)
            if (benches == bench[i] && !worldIn.isRemote)
                playerIn.openGui(ArmorPlus.instance, guiNumber[i], worldIn, pos.getX(), pos.getY(), pos.getZ());
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Nonnull
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
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
    @SuppressWarnings("deprecation")
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState();
        iblockstate = iblockstate.withProperty(FACING, EnumFacing.getHorizontal(meta));
        return iblockstate;
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public IBlockState withRotation(@Nonnull IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        switch (benches) {
            case WORKBENCH:
                return tileEntities[0];
            case HIGH_TECH:
                return tileEntities[1];
            case ULTI_TECH:
                return tileEntities[2];
            case CHAMPION:
                return tileEntities[3];
            case WORKBENCH_NEW:
                return tileEntities[0];
        }
        return null;
    }
}
