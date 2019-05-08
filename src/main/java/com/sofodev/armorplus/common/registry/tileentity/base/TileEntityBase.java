/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.tileentity.base;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
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

    //  public static final TileEntityType<?> WORKBENCH = TileEntityType.Builder.create(TileWB::new).build(null).setRegistryName(new ResourceLocation(MODID, "workbench"));
    //  public static final TileEntityType<?> HIGH_TECH_BENCH = TileEntityType.Builder.create(TileHTB::new).build(null).setRegistryName(new ResourceLocation(MODID, "high_Tech_bench"));
    //  public static final TileEntityType<?> ULTI_TECH_BENCH = TileEntityType.Builder.create(TileUTB::new).build(null).setRegistryName(new ResourceLocation(MODID, "ulti_tech_bench"));
    //  public static final TileEntityType<?> CHAMPION_BENCH = TileEntityType.Builder.create(TileCB::new).build(null).setRegistryName(new ResourceLocation(MODID, "champion_bench"));
    //  public static final TileEntityType<?> LAVA_INFUSER = TileEntityType.Builder.create(TileLavaInfuser::new).build(null).setRegistryName(new ResourceLocation(MODID, "lava_infuser"));
    //  public static final TileEntityType<?> TROPHY = TileEntityType.Builder.create(TileTrophy::new).build(null).setRegistryName(new ResourceLocation(MODID, "trophy"));

    public TileEntityBase(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    //  @Override
    //  public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
    //      IItemHandler handler = this.getItemHandler(facing);
    //      return (handler != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing));
    //  }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            IItemHandler handler = this.getItemHandler(facing);
            if (handler != null) return (LazyOptional<T>) handler;
        } else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            IFluidHandler tank = this.getFluidHandler(facing);
            if (tank != null) return (LazyOptional<T>) tank;
        } else if (capability == CapabilityEnergy.ENERGY) {
            IEnergyStorage storage = this.getEnergyStorage(facing);
            if (storage != null) return (LazyOptional<T>) storage;
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