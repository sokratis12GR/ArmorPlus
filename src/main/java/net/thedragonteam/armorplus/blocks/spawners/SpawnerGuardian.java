/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks.spawners;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.blocks.spawners.base.SpawnerBase;

import java.util.Random;

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:50 PM.
 * - TheDragonTeam
 */
public class SpawnerGuardian extends SpawnerBase {

    public SpawnerGuardian() {
        super(Material.ROCK, "spawner_guardian", 20.0F, 20.0F);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float x, float y, float z) {
        if (!world.isRemote) {
            EntityGuardian guardian = new EntityGuardian(world);
            guardian.setPosition(pos.getX() + 0.5, pos.up(1).getY(), pos.getZ() + 0.5);
            world.spawnEntityInWorld(guardian);
            world.destroyBlock(pos, true);
        }
        return true;
    }

//    @Override
//    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
//        if (!worldIn.isRemote) {
//            if (worldIn.isAnyPlayerWithinRangeAt(pos.getX(), pos.getY(), pos.getZ(), 100D)) {
//                EntityGuardian guardian = new EntityGuardian(worldIn);
//                guardian.setPosition(pos.getX() + 0.5, pos.up(1).getY(), pos.getZ() + 0.5);
//                worldIn.spawnEntityInWorld(guardian);
//            }
//        }
//    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        if (!worldIn.isRemote) {
            if (worldIn.isAnyPlayerWithinRangeAt(pos.getX(), pos.up(1).getY(), pos.getZ(), 10)) {
                EntityGuardian guardian = new EntityGuardian(worldIn);
                guardian.setPosition(pos.getX() + 0.5, pos.up(1).getY(), pos.getZ() + 0.5);
                worldIn.spawnEntityInWorld(guardian);
                worldIn.destroyBlock(pos, true);
            }
        }
    }

    @Override
    public Block setTickRandomly(boolean shouldTick) {
        return super.setTickRandomly(true);
    }
}
