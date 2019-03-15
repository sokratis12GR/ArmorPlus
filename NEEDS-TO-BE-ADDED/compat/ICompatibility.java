/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat;

/**
 * Implement on all primary compatibility classes. * @author Sokratis Fotkatzikis
 */
public interface ICompatibility {
    /**
     * Called during each initialization phase after the given
     * [.getMODID] has been verified as loaded.
     *
     * @param phase - The load phase at which this method is being called.
     */
    default void loadCompatibility(InitializationPhase phase) {
    }

    /**
     * @return The `getMODID` of the mod we are adding compatibility for.
     */
    String getMODID();

    /**
     * Whether or not compatibility should be loaded even if the mod were to be
     * found.
     * <p>
     * <p>
     * Generally a determined by a config option.
     *
     * @return If Compatibility should load.
     */
    boolean enableCompat();

    /**
     * Represents a given mod initialization state.
     */
    enum InitializationPhase {
        SETUP,
        MAPPING,
        ;
    }
}