/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks.spawners;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.blocks.spawners.base.SpawnerBase;
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie;

import java.util.Random;

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:50 PM.
 * - TheDragonTeam
 */
public class SpawnerEnderDragonZombie extends SpawnerBase {

    public SpawnerEnderDragonZombie() {
        super(Material.ROCK, "spawner_ender_dragon_zombie", 20.0F, 20.0F);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float x, float y, float z) {
        if (!world.isRemote) {
            EntityEnderDragonZombie enderDragonZombie = new EntityEnderDragonZombie(world);
            enderDragonZombie.setPosition(pos.getX() + 0.5, pos.up(1).getY(), pos.getZ() + 0.5);
            world.spawnEntityInWorld(enderDragonZombie);
            world.destroyBlock(pos, true);
        }
        return true;
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        if (!worldIn.isRemote) {
            if (worldIn.isAnyPlayerWithinRangeAt(pos.getX(), pos.up(1).getY(), pos.getZ(), 10)) {
                EntityEnderDragonZombie enderDragonZombie = new EntityEnderDragonZombie(worldIn);
                enderDragonZombie.setPosition(pos.getX() + 0.5, pos.up(1).getY(), pos.getZ() + 0.5);
                worldIn.spawnEntityInWorld(enderDragonZombie);
                worldIn.destroyBlock(pos, true);
            }
        }
    }

    @Override
    public Block setTickRandomly(boolean shouldTick) {
        return super.setTickRandomly(true);
    }
}
