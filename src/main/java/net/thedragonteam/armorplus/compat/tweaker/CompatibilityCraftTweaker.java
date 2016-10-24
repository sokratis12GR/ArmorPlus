/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.compat.tweaker;

import net.thedragonteam.armorplus.compat.ICompatibility;

public class CompatibilityCraftTweaker implements ICompatibility {

    @Override
    public void loadCompatibility(ICompatibility.InitializationPhase phase) {
    }

    @Override
    public String getMODID() {
        return "MineTweaker3";
    }

    @Override
    public boolean enableCompat() {
        return true;
    }
}