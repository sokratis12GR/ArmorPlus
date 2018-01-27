/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat;

import static net.thedragonteam.armorplus.ModConfig.IntegrationsConfig.enableJEIIntegration;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CompatibilityJustEnoughItems implements ICompatibility {

    @Override
    public String getMODID() {
        return "jei";
    }

    @Override
    public boolean enableCompat() {
        return enableJEIIntegration;
    }
}