/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.compat;

/**
 * @author Sokratis Fotkatzikis
 */
public class CompatibilityDraconicEvolution implements ICompatibility {

    @Override
    public String getMODID() {
        return "draconicevolution";
    }

    @Override
    public boolean enableCompat() {
        return true;
    }
}