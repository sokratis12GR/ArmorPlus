/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.ritual;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.tileentity.TileEntityRitualAltar;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;
import static net.thedragonteam.thedragonlib.util.LogHelper.INSTANCE;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class BlockRitualAltar extends BlockBase implements ITileEntityProvider {

    protected static final AxisAlignedBB NOT_CONNECTED_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);

    private TileEntityRitualAltar ritualAltar;

    public BlockRitualAltar() {
        super(Material.ROCK, "ritual_altar", 10, 10);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return NOT_CONNECTED_AABB;
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        for (Item item : ModItems.templates)
            if (entityIn instanceof EntityItem && Objects.equals(((EntityItem) entityIn).getEntityItem().getItem(), item)) {
                ritualAltar.isItemValid(getItemStack(item));
                INSTANCE.info("Is Item Valid: " + ritualAltar.isItemValid(getItemStack(item)));
            }

        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return ritualAltar = new TileEntityRitualAltar();
    }
}
