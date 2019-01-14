/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.castle.base;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.iface.IModdedBlock;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.blocks;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockStoneBrickWall extends BlockWall implements IModdedBlock {

    private BlockStoneBrick stoneBricks;

    public BlockStoneBrickWall(BlockStoneBrick modelState) {
        super(modelState);
        this.stoneBricks = modelState;
        this.setRegistryName(Utils.setRL(stoneBricks.name + "_wall"));
        this.setTranslationKey(Utils.setName(stoneBricks.name + "_wall"));
        this.setResistance(10f);
        this.setHardness(5f);
        this.setHarvestLevel(blocks.stone_bricks.toolType, blocks.stone_bricks.harvestLevel);
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }

    @SuppressWarnings("deprecation")
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return this.stoneBricks.color;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(getItemStack(this));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        this.initModel("stone_bricks", 0);
    }

}

