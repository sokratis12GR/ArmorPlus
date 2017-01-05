/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.minecraftforge.energy.IEnergyStorage;

public class TeslaForgeUnitsWrapper implements ITeslaProducer, ITeslaHolder, ITeslaConsumer {

    private final IEnergyStorage storage;

    public TeslaForgeUnitsWrapper(IEnergyStorage storage) {
        this.storage = storage;
    }

    @Override
    public long givePower(long power, boolean simulated) {
        return this.storage.receiveEnergy((int) power, simulated);
    }

    @Override
    public long getStoredPower() {
        return this.storage.getEnergyStored();
    }

    @Override
    public long getCapacity() {
        return this.storage.getMaxEnergyStored();
    }

    @Override
    public long takePower(long power, boolean simulated) {
        return this.storage.extractEnergy((int) power, simulated);
    }
}