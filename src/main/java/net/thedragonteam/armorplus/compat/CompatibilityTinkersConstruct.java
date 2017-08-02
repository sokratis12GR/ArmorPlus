/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat;

import static net.thedragonteam.armorplus.APConfig.enableTConstructIntegration;

/**
 * net.thedragonteam.armorplus.compat.jei
 * ArmorPlus created by sokratis12GR on 6/21/2016 10:54 PM.
 * - TheDragonTeam
 */
public class CompatibilityTinkersConstruct implements ICompatibility {
    @Override
    public void loadCompatibility(ICompatibility.InitializationPhase phase) {
    }

    @Override
    public String getMODID() {
        return "tconstruct";
    }

    @Override
    public boolean enableCompat() {
        return enableTConstructIntegration;
    }
}