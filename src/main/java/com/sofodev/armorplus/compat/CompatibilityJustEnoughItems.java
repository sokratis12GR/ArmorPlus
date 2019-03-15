/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat;

import com.sofodev.armorplus.config.ModConfig;

/**
 * @author Sokratis Fotkatzikis
 */
public class CompatibilityJustEnoughItems implements ICompatibility {

    @Override
    public String getMODID() {
        return "jei";
    }

    @Override
    public boolean enableCompat() {
        return ModConfig.IntegrationsConfig.enableJEIIntegration;
    }
}