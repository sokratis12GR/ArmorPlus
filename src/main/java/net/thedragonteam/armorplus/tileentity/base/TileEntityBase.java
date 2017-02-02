/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.tileentity.base;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.util.APTeslaUtils;
import net.thedragonteam.armorplus.util.TeslaForgeUnitsWrapper;

import javax.annotation.Nonnull;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public abstract class TileEntityBase extends TileEntity {

    private Object teslaWrapper;

    TileEntityBase() {
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {
        IItemHandler handler = this.getItemHandler(facing);
        return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && handler != null || super.hasCapability(capability, facing));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            IItemHandler handler = this.getItemHandler(facing);
            if (handler != null) {
                return (T) handler;
            }
        } else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            IFluidHandler tank = this.getFluidHandler(facing);
            if (tank != null) {
                return (T) tank;
            }
        } else if (capability == CapabilityEnergy.ENERGY) {
            IEnergyStorage storage = this.getEnergyStorage(facing);
            if (storage != null) {
                return (T) storage;
            }
        } else if (ArmorPlus.isTeslaLoaded()) {
            if (capability == APTeslaUtils.teslaConsumer || capability == APTeslaUtils.teslaProducer || capability == APTeslaUtils.teslaHolder) {
                IEnergyStorage storage = this.getEnergyStorage(facing);
                if (storage != null) {
                    if (this.teslaWrapper == null) {
                        this.teslaWrapper = new TeslaForgeUnitsWrapper(storage);
                    }
                    return (T) this.teslaWrapper;
                }
            }
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
