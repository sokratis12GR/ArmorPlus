/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat

/**
 * Implement on all primary compatibility classes.
 */
interface ICompatibility {
    /**
     * Called during each initialization phase after the given
     * [.getMODID] has been verified as loaded.

     * @param phase - The load phase at which this method is being called.
     */
    fun loadCompatibility(phase: InitializationPhase)

    /**
     * @return The `modid` of the mod we are adding compatibility for.
     */
    val modid: String

    /**
     * Whether or not compatibility should be loaded even if the mod were to be
     * found.
     *
     *
     * Generally a determined by a config option.

     * @return If Compatibility should load.
     */
    fun enableCompat(): Boolean

    /**
     * Represents a given mod initialization state.
     */
    enum class InitializationPhase {
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
        MAPPING
    }
}