/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api;

public interface IClientTicker
{
    void clientTick();

    boolean needsTicks();
}