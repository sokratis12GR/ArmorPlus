/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CompatibilityMineTweaker implements ICompatibility {

    @Override
    public String getMODID() {
        return "crafttweaker";
    }

    @Override
    public boolean enableCompat() {
        return true;
    }
}