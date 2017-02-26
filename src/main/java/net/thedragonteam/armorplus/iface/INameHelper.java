/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.iface;

public interface INameHelper<T> {

    @Deprecated
    T getName(T name);

    T getName();

}