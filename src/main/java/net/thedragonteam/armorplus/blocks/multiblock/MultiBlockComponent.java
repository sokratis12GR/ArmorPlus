/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.multiblock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MultiBlockComponent {

    protected BlockPos relPos;
    protected final IBlockState state;
    protected final TileEntity tileEntity;
    private final boolean doFancyRender;

    public MultiBlockComponent(BlockPos relPos, IBlockState state) {
        this(relPos, state, null);
    }

    public MultiBlockComponent(BlockPos relPos, IBlockState state, boolean doFancyRender) {
        this(relPos, state, doFancyRender, null);
    }

    public MultiBlockComponent(BlockPos relPos, IBlockState state, TileEntity tileEntity) {
        this(relPos, state, state.getBlock().hasTileEntity(state) == (tileEntity != null), tileEntity);
    }

    public MultiBlockComponent(BlockPos relPos, IBlockState state, boolean doFancyRender, TileEntity tileEntity) {
        this.relPos = relPos;
        this.state = state;
        this.tileEntity = tileEntity;
        this.doFancyRender = doFancyRender;
    }

    public BlockPos getRelativePosition() {
        return relPos;
    }

    public IBlockState getBlockState() {
        return state;
    }

    public boolean matches(World world, BlockPos pos) {
        return world.getBlockState(pos) == state;
    }

    public ItemStack[] getMaterials() {
        return new ItemStack[]{new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state))};
    }

    public void rotate(double angle) {
        double x = relPos.getX();
        double z = relPos.getZ();
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        double xn = x * cos - z * sin;
        double zn = x * sin + z * cos;
        relPos = new BlockPos((int) Math.round(xn), relPos.getY(), (int) Math.round(zn));
    }

    public MultiBlockComponent copy() {
        return new MultiBlockComponent(relPos, state, tileEntity);
    }

    public TileEntity getTileEntity() {
        return tileEntity;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldDoFancyRender() {
        return doFancyRender;
    }
}
