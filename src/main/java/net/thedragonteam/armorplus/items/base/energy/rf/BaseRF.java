/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.base.energy.rf;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.thedragonteam.armorplus.items.base.BaseItem;

public class BaseRF extends BaseItem implements IEnergyContainerItem {

    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public BaseRF(int capacity, String name) {
        this(capacity, capacity, capacity, name);
    }

    public BaseRF(int capacity, int maxTransfer, String name) {
        this(capacity, maxTransfer, maxTransfer, name);
    }

    public BaseRF(int capacity, int maxReceive, int maxExtract, String name) {
        super(name);
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    public BaseRF setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public BaseRF setMaxTransfer(int maxTransfer) {
        setMaxReceive(maxTransfer);
        setMaxExtract(maxTransfer);
        return this;
    }

    public BaseRF setMaxReceive(int maxReceive) {
        this.maxReceive = maxReceive;
        return this;
    }

    public BaseRF setMaxExtract(int maxExtract) {
        this.maxExtract = maxExtract;
        return this;
    }

    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (!container.hasTagCompound()) {
            container.setTagCompound(new NBTTagCompound());
        }
        int energy = container.getTagCompound().getInteger("Energy");
        int energyReceived = Math.min(this.capacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            energy += energyReceived;
            container.getTagCompound().setInteger("Energy", energy);
        }
        return energyReceived;
    }

    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        if ((container.getTagCompound() == null) || (!container.getTagCompound().hasKey("Energy"))) {
            return 0;
        }
        int energy = container.getTagCompound().getInteger("Energy");
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
            container.getTagCompound().setInteger("Energy", energy);
        }
        return energyExtracted;
    }

    public int getEnergyStored(ItemStack container) {
        if ((container.getTagCompound() == null) || (!container.getTagCompound().hasKey("Energy"))) {
            return 0;
        }
        return container.getTagCompound().getInteger("Energy");
    }

    public int getMaxEnergyStored(ItemStack container) {
        return this.capacity;
    }

}