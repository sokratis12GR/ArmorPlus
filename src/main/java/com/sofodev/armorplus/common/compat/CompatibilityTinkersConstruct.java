/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.compat;

import com.sofodev.armorplus.common.config.ModConfig;

/**
 * @author Sokratis Fotkatzikis
 */
public class CompatibilityTinkersConstruct implements ICompatibility {

    @Override
    public String getMODID() {
        return "tconstruct";
    }

    @Override
    public boolean enableCompat() {
        return ModConfig.IntegrationsConfig.enableTConstructIntegration;
    }
}