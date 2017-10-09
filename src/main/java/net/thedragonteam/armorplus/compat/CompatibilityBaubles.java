/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CompatibilityBaubles implements ICompatibility {

    @Override
    public String getMODID() {
        return "baubles";
    }

    @Override
    public boolean enableCompat() {
        return true;
    }
}