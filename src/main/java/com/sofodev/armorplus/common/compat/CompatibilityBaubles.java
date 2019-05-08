/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.compat;

/**
 * @author Sokratis Fotkatzikis
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