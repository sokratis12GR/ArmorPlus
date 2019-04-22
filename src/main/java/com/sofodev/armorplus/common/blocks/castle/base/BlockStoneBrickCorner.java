/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.blocks.castle.base;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.common.blocks.castle.BrickColor;
import com.sofodev.armorplus.common.iface.IModdedBlock;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.blocks;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockStoneBrickCorner extends BlockStairs implements IModdedBlock {

    private BrickColor brickColor;

    public BlockStoneBrickCorner(BrickColor brickColor, IBlockState modelState) {
        super(modelState);
        this.setTranslationKey(Utils.setName(brickColor.getName() + "_stone_brick_corner"));
        this.setRegistryName(Utils.setRL(brickColor.getName() + "_stone_brick_corner"));
        this.setHardness(10.0f);
        this.setResistance(5.0f);
        this.setHarvestLevel(blocks.stone_bricks.toolType, blocks.stone_bricks.harvestLevel);
        this.setLightOpacity(255);
        this.setCreativeTab(ArmorPlus.tabArmorPlusBlocks);
        this.brickColor = brickColor;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        this.initModel("stone_bricks", 0);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings({"deprecation", "NullableProblems"})
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return this.brickColor.getMapColor();
    }
}
