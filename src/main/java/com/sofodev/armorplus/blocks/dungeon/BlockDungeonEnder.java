/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.dungeon;

import com.sofodev.armorplus.blocks.BlockProperties;
import com.sofodev.armorplus.blocks.base.BlockBase;
import com.sofodev.armorplus.blocks.base.ToolType;
import com.sofodev.armorplus.iface.IModdedBlock;
import com.sofodev.armorplus.registry.ModSounds;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockDungeonEnder extends BlockBase implements IModdedBlock {

    private EnderType enderBlocks;

    public BlockDungeonEnder(EnderType enderBlocks) {
        super(Material.ROCK, enderBlocks.getName(), new BlockProperties(10000, 100, ToolType.PICKAXE, 4, enderBlocks.getLightLevel(), true));
        this.enderBlocks = enderBlocks;
    }

    @SuppressWarnings("deprecation")
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.PURPLE;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (enderBlocks == EnderType.ENDER_STONE_TRAP) {
            worldIn.playSound(playerIn, pos, ModSounds.TRAP_TRIGGERED, SoundCategory.BLOCKS, 0.5F, 0.0F);
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(0, "normal");
    }
}
