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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/**
 * @author Sokratis Fotkatzikis
 */
public class BlockStoneBrick extends BlockBase implements IModdedBlock {

    public MapColor color;

    public String name;

    public BlockStoneBrick(BrickColor brickColor) {
        super(Material.ROCK, brickColor.getName() + "_stone_brick", new BlockProperties(10.0f, 5.0f, ToolType.PICKAXE, 0));
        this.color = brickColor.getMapColor();
        this.name = brickColor.getName() + "_stone_brick";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        this.initModel(0, "normal");
    }

    @SuppressWarnings("deprecation")
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return this.color;
    }
}
