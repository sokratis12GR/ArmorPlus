/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.blocks.special;

import com.sofodev.armorplus.common.blocks.BlockProperties;
import com.sofodev.armorplus.common.blocks.base.BlockBase;
import com.sofodev.armorplus.common.iface.IModdedBlock;
import com.sofodev.armorplus.common.tileentity.TileTrophy;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import static com.sofodev.armorplus.common.blocks.special.Trophy.ANY;
import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.blocks;

public class BlockTrophy extends BlockBase implements IModdedBlock {

    private final ResourceLocation mob;
    private Trophy type;

    public BlockTrophy(Trophy type) {
        super(Material.CORAL, type == ANY ? "trophy" : type.getName() + "_trophy", new BlockProperties(20.0f, 3.0f, blocks.block_trophy.props));
        this.type = type;
        this.mob = type.getEntityId();
    }

    public BlockTrophy(ResourceLocation mob) {
        super(Material.CORAL, mob.getPath() + "_trophy", new BlockProperties(20.0f, 3.0f, blocks.block_trophy.props));
        this.type = null;
        this.mob = mob;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        TileTrophy trophy = new TileTrophy();
        if (type == null) {
            trophy.setEntityId(mob);
            trophy.setEntityScale(0.1f);
        } else {
            trophy.setEntityId(mob);
            trophy.setEntityScale(type.getScale());
        }
        return trophy;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (stack.hasDisplayName() && tileentity instanceof TileTrophy) {
            ((TileTrophy) tileentity).setCustomName(stack.getDisplayName());
        }

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        ItemStack itemstack = super.getPickBlock(state, target, world, pos, player);
        TileTrophy tileTrophy = (TileTrophy) world.getTileEntity(pos);
        NBTTagCompound nbttagcompound = tileTrophy.saveToNbt(new NBTTagCompound());
        if (!nbttagcompound.isEmpty()) {
            itemstack.setTagInfo("BlockEntityTag", nbttagcompound);
        }
        return itemstack;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
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

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(0, "normal");
    }
}
