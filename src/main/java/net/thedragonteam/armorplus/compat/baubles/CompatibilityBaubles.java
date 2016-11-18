/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.compat.baubles;

import net.thedragonteam.armorplus.compat.ICompatibility;

/**
 * net.thedragonteam.armorplus.compat.jei
 * ArmorPlus created by sokratis12GR on 6/21/2016 10:54 PM.
 * - TheDragonTeam
 */
public class CompatibilityBaubles implements ICompatibility {
    @Override
    public void loadCompatibility(InitializationPhase phase) {
    }


    @Override
    public String getMODID() {
        return "Baubles";
    }

    @Override
    public boolean enableCompat() {
        return true;
    }
}