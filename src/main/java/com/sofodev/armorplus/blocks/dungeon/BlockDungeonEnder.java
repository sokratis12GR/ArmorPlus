/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.dungeon;

import com.sofodev.armorplus.blocks.BlockProperties;
import com.sofodev.armorplus.blocks.HarvestProps;
import com.sofodev.armorplus.blocks.base.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockDungeonEnder extends BlockBase {

    public BlockDungeonEnder(EnderType enderBlocks) {
        super(Material.ROCK, new BlockProperties(10000, 100, new HarvestProps(ToolType.PICKAXE, 4, true), enderBlocks.getLightLevel()));
    }

    //  @Override
    //  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    //      if (enderBlocks == EnderType.ENDER_STONE_TRAP) {
    //          worldIn.playSound(playerIn, pos, ModSounds.TRAP_TRIGGERED, SoundCategory.BLOCKS, 0.5F, 0.0F);
    //      }
    //      return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    //  }
}
