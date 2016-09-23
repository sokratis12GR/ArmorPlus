/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.tileentity.energy;

import net.darkhax.tesla.api.implementation.InfiniteTeslaProducer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.darkhax.tesla.lib.TeslaUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Optional;

/**
 * A TileEntity that produces infinite amounts of tesla power.
 */
public class TileEntityCreativePower extends TileEntity implements ITickable {

    @Optional.Method(modid = "tesla")
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

        if (capability == TeslaCapabilities.CAPABILITY_PRODUCER)
            return (T) new InfiniteTeslaProducer();

        return super.getCapability(capability, facing);
    }

    @Optional.Method(modid = "tesla")
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

        if (capability == TeslaCapabilities.CAPABILITY_PRODUCER)
            return true;

        return super.hasCapability(capability, facing);
    }

    @Optional.Method(modid = "tesla")
    @Override
    public void update() {
        TeslaUtils.distributePowerToAllFaces(this.worldObj, this.pos, 50, false);
    }
}