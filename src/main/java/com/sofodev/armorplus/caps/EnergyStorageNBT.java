/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.caps;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

/**
 * @author Sokratis Fotkatzikis
 */
public class EnergyStorageNBT extends EnergyStorage implements INBTSerializable<NBTTagCompound> {
    public EnergyStorageNBT(int capacity) {
        super(capacity);
    }

    public EnergyStorageNBT(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public EnergyStorageNBT(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public EnergyStorageNBT(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("energy", energy);
        compound.setInteger("capacity", capacity);
        compound.setInteger("maxReceive", maxReceive);
        compound.setInteger("maxExtract", maxExtract);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        energy = nbt.getInteger("energy");
        capacity = nbt.getInteger("capacity");
        maxReceive = nbt.getInteger("maxReceive");
        maxExtract = nbt.getInteger("maxExtract");
    }
}