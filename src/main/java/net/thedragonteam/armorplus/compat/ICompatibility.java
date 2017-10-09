/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat;

/**
 * Implement on all primary compatibility classes. * @author Sokratis Fotkatzikis - TheDragonTeam
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
        /**
         * Represents
         * [net.minecraftforge.fml.common.event.FMLPreInitializationEvent]
         */
        PRE_INIT,
        /**
         * Represents
         * [net.minecraftforge.fml.common.event.FMLInitializationEvent]
         */
        INIT,
        /**
         * Represents
         * [net.minecraftforge.fml.common.event.FMLPostInitializationEvent]
         */
        POST_INIT,
        /**
         * Represents
         * [net.minecraftforge.fml.common.event.FMLModIdMappingEvent]
         */
        MAPPING,;
    }
}