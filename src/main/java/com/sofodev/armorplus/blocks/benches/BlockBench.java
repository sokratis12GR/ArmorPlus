/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.benches;

import com.sofodev.armorplus.blocks.BlockProperties;
import com.sofodev.armorplus.blocks.HarvestProps;
import com.sofodev.armorplus.blocks.base.BlockBase;
import com.sofodev.armorplus.client.GuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static net.minecraftforge.common.ToolType.PICKAXE;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockBench extends BlockBase {

    public Benches benches;

    public BlockBench(Benches benches) {
        super(Material.IRON, new BlockProperties(1000.0f, 10.0f, new HarvestProps(PICKAXE, 2)));
        this.benches = benches;
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            GuiHandler.openBenchGUI(benches.getGuiLocation(), pos, (EntityPlayerMP) player, benches.getId());
        }
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
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
        return benches.getTileEntity();
    }
}
