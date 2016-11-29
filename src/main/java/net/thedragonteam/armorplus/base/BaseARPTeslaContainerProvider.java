/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.base;

import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.common.Optional.Method;

public class BaseARPTeslaContainerProvider implements INBTSerializable<NBTTagCompound>, ICapabilityProvider {

    private final BaseTeslaContainer container;
    private int power;
    private int maxCapacity;
    private int output;
    private int input;

    public BaseARPTeslaContainerProvider(BaseTeslaContainer container, int power, int maxCapacity, int input, int output) {
        this.container = container;
        this.power = power;
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
        container.setCapacity(maxCapacity);
        container.setOutputRate(output);
        container.setInputRate(input);
    }

    public BaseARPTeslaContainerProvider(BaseTeslaContainer container, int maxCapacity, int input, int output) {
        this.container = container;
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
        container.setCapacity(maxCapacity);
        container.setOutputRate(output);
        container.setInputRate(input);
    }

    @Method(modid = "tesla")
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == TeslaCapabilities.CAPABILITY_CONSUMER || capability == TeslaCapabilities.CAPABILITY_HOLDER;
    }

    @Method(modid = "tesla")
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == TeslaCapabilities.CAPABILITY_CONSUMER || capability == TeslaCapabilities.CAPABILITY_HOLDER)
            return (T) this.container;

        return null;
    }

    @Method(modid = "tesla")
    @Override
    public NBTTagCompound serializeNBT() {
        return this.container.serializeNBT();
    }

    @Method(modid = "tesla")
    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.container.deserializeNBT(nbt);
    }
}