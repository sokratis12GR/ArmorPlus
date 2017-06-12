/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat;

/**
 * net.thedragonteam.armorplus.compat.jei
 * ArmorPlus created by sokratis12GR on 6/21/2016 10:54 PM.
 * - TheDragonTeam
 */
public class CompatibilityJustEnoughItems implements ICompatibility {

    /**
     * Called during each initialization phase after the given
     * [.getMODID] has been verified as loaded.
     *
     * @param phase - The load phase at which this method is being called.
     */
    @Override
    public void loadCompatibility(InitializationPhase phase) {
    }

    /**
     * @return The `getMODID` of the mod we are adding compatibility for.
     */
    @Override
    public String getMODID() {
        return "jei";
    }

    /**
     * Whether or not compatibility should be loaded even if the mod were to be
     * found.
     * <p>
     * <p>
     * Generally a determined by a config option.
     *
     * @return If Compatibility should load.
     */
    @Override
    public boolean enableCompat() {
        return true;
    }
}