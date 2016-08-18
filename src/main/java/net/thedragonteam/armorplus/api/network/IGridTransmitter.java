/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.network;

import net.minecraft.world.World;

public interface IGridTransmitter<A, N extends DynamicNetwork<A, N>> {
    /**
     * Gets the network currently in use by this transmitter segment.
     *
     * @return network this transmitter is using
     */
    public N getTransmitterNetwork();

    /**
     * Sets this transmitter segment's network to a new value.
     *
     * @param network - network to set to
     */
    public void setTransmitterNetwork(N network);

    public int getCapacity();

    public World world();

    public boolean isValid();

    public boolean isOrphan();

    public void takeShare();

    public void updateShare();

}