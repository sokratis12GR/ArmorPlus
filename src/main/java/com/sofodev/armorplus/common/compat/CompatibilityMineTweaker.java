/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.compat;

import com.sofodev.armorplus.common.compat.crafttweaker.CTArmorPlusPlugin;
import net.thedragonteam.thedragonlib.util.LogHelper;

/**
 * @author Sokratis Fotkatzikis
 */
public class CompatibilityMineTweaker implements ICompatibility {

    @Override
    public void loadCompatibility(InitializationPhase phase) {
        if (phase == InitializationPhase.PRE_INIT) {
            try {
                CTArmorPlusPlugin.init();
            } catch (Throwable e) {
                LogHelper.error("ArmorPlus (A+) seems to be having trouble with CraftTweaker.");
            }
        }
    }

    @Override
    public String getMODID() {
        return "crafttweaker";
    }

    @Override
    public boolean enableCompat() {
        return true;
    }
}