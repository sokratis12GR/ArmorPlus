/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.properties.iface;

/**
 * @author Sokratis Fotkatzikis
 */
public interface IRemovable {

    /**
     * Checks if the object is enabled.
     * If it is it will load into the game
     * If its not enabled it will be removed from the game, until re-enabled again.
     *
     * @return true if its enabled and false if its not
     */
    default boolean isEnabled() {
        return true;
    }
}
