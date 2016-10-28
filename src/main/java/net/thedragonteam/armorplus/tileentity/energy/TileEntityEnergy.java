/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.tileentity.energy;

import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class TileEntityEnergy extends TileEntity implements IEnergyStorage {
    public ITeslaHolder invTesla = new ITeslaHolder() {

        @Override
        public long getStoredPower() {
            return 0;
        }

        @Override
        public long getCapacity() {
            return 100000000;
        }
    };

    private boolean isTesla(Capability<?> capability) {
        return capability == TeslaCapabilities.CAPABILITY_HOLDER;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return isTesla(capability) || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == TeslaCapabilities.CAPABILITY_HOLDER ? TeslaCapabilities.CAPABILITY_HOLDER.cast(invTesla) : super.getCapability(capability, facing);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 100;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return 0;
    }

    @Override
    public int getMaxEnergyStored() {
        return 100000000;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.getPos(), 0, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }
}