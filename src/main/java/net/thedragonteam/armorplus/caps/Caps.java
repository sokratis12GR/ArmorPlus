package net.thedragonteam.armorplus.caps;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class Caps implements ICapabilitySerializable<NBTTagCompound> {
    EnergyStorageNBT storage;

    public Caps(EnergyStorageNBT storage) {
        this.storage = storage;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEnergy.ENERGY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? (T) storage : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return storage.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        storage.deserializeNBT(nbt);
    }
}