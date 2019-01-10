/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.tileentity.base;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * @author Sokratis Fotkatzikis
 **/
public abstract class TileEntityBase extends TileEntity {

    public TileEntityBase() {
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        IItemHandler handler = this.getItemHandler(facing);
        return (handler != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing));
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            IItemHandler handler = this.getItemHandler(facing);
            if (handler != null) return (T) handler;
        } else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            IFluidHandler tank = this.getFluidHandler(facing);
            if (tank != null) return (T) tank;
        } else if (capability == CapabilityEnergy.ENERGY) {
            IEnergyStorage storage = this.getEnergyStorage(facing);
            if (storage != null) return (T) storage;
        }
        return super.getCapability(capability, facing);
    }

    public IFluidHandler getFluidHandler(EnumFacing facing) {
        return null;
    }

    public IEnergyStorage getEnergyStorage(EnumFacing facing) {
        return null;
    }

    public IItemHandler getItemHandler(EnumFacing facing) {
        return null;
    }
}