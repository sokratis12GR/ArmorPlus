package com.sofodev.armorplus.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

public class TilePortal extends TileEntity {

    /**
     * The dimension that the player will teleport to.
     */
    private int dimID;

    public TilePortal() {
        //Empty because we want it empty
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName() {
        return "container.portal";
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldRenderFace(EnumFacing facing) {
        return facing == EnumFacing.UP;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.loadFromNbt(nbt);
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        this.saveToNbt(nbt);
        return nbt;
    }

    public float getDimensionID() {
        return dimID;
    }

    public void setDimensionID(int id) {
        this.dimID = id;
    }

    private String dimensionID = "DimID";

    public void loadFromNbt(NBTTagCompound nbt) {
        if (nbt.hasKey(dimensionID, 99)) {
            this.dimID = nbt.getInteger(dimensionID);
        }
    }

    public NBTTagCompound saveToNbt(NBTTagCompound nbt) {
        nbt.setInteger(dimensionID, this.dimID);
        return nbt;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public BlockPos getPos() {
        return this.pos;
    }

    @SideOnly(Side.CLIENT)
    public int getParticleAmount() {
        return Arrays.stream(EnumFacing.values()).mapToInt(facing -> this.shouldRenderFace(facing) ? 1 : 0).sum();
    }


    //
    //
    //  NBT HELPER METHODS (Required)
    //
    //

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 12, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public boolean onlyOpsCanSetNbt() {
        return false;
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        return super.receiveClientEvent(id, type);
    }
}