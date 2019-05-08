/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.blocks.lava;

import com.sofodev.armorplus.common.registry.blocks.BlockProperties;
import com.sofodev.armorplus.common.registry.blocks.base.BlockBase;
import com.sofodev.armorplus.common.registry.tileentity.TileLavaInfuser;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Random;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.blocks;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockLavaInfuser extends BlockBase {
    private static boolean keepInventory;
    private boolean isInfusing;

    public BlockLavaInfuser(boolean isInfusing) {
        super(Material.ROCK, new BlockProperties(2.5f, 10000.0f));
        this.isInfusing = isInfusing;
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(IBlockState state) {
        return blocks.lava_infuser.getType();
    }

    @Override
    public int getHarvestLevel(IBlockState state) {
        return blocks.lava_infuser.getHarvestLevel();
    }

    public static void setState(boolean active, World worldIn, BlockPos pos) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        keepInventory = true;

        keepInventory = false;

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public IItemProvider getItemDropped(IBlockState p_199769_1_, World p_199769_2_, BlockPos p_199769_3_, int p_199769_4_) {
        return (Utils.getBlock("lava_infuser")).asItem();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (this.isInfusing) {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = (double) pos.getZ() + 0.5D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            if (rand.nextDouble() < 0.1D) {
                worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }
            this.spawnParticles(worldIn, d0, d1, d2, d4);
        }
    }


    private void spawnParticles(World worldIn, double d0, double d1, double d2, double d4) {
        double xSpeed = 0.1;
        double zSpeed = 0.1;
        worldIn.addParticle(Particles.LAVA, d0, d1 + 1, d2 + d4, xSpeed, 0.5, zSpeed);
        worldIn.addParticle(Particles.PORTAL, d0, d1 + 1, d2 + d4, xSpeed, 0.5, zSpeed);
        worldIn.addParticle(Particles.FLAME, d0, d1 + 1, d2 + d4, xSpeed, 0.5, zSpeed);
    }

    @Override
    public boolean onBlockActivated(IBlockState p_196250_1_, World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            //          player.openGui(ArmorPlus.instance, GuiHandler.GUI_LAVA_INFUSER, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
        return new TileLavaInfuser();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileLavaInfuser) {
                ((TileLavaInfuser) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }

    @Override
    public void onReplaced(IBlockState p_196243_1_, World world, BlockPos pos, IBlockState p_196243_4_, boolean p_196243_5_) {
        if (!keepInventory) {
            TileEntity tileentity = world.getTileEntity(pos);

            if (tileentity instanceof TileLavaInfuser) {
                InventoryHelper.dropInventoryItems(world, pos, (TileLavaInfuser) tileentity);
                world.updateComparatorOutputLevel(pos, this);
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, EntityPlayer player) {
        return super.getPickBlock(state, target, world, pos, player);
    }

    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     */
    @Override
    @SuppressWarnings("deprecation")
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}