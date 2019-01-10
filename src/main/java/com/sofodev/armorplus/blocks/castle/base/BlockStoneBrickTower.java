/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.castle.base;

import com.sofodev.armorplus.blocks.BlockProperties;
import com.sofodev.armorplus.blocks.base.BlockBase;
import com.sofodev.armorplus.blocks.base.ToolType;
import com.sofodev.armorplus.blocks.castle.BrickColor;
import com.sofodev.armorplus.iface.IModdedBlock;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockStoneBrickTower extends BlockBase implements IModdedBlock {

    private BrickColor brickColor;

    public BlockStoneBrickTower(BrickColor brickColor) {
        super(Material.ROCK, brickColor.getName() + "_stone_brick_tower", new BlockProperties(10.0f, 5.0f, ToolType.PICKAXE, 0));
        this.brickColor = brickColor;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        this.initModel("stone_bricks", 0);
    }

    @SuppressWarnings("deprecation")
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return this.brickColor.getMapColor();
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
