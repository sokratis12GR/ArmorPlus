/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat;

import static net.thedragonteam.armorplus.APConfig.enableTConstructIntegration;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CompatibilityTinkersConstruct implements ICompatibility {

    @Override
    public String getMODID() {
        return "tconstruct";
    }

    @Override
    public boolean enableCompat() {
        return enableTConstructIntegration;
    }
}