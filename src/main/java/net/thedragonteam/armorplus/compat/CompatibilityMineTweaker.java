/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat;

import net.thedragonteam.armorplus.compat.crafttweaker.CTArmorPlusPlugin;
import net.thedragonteam.thedragonlib.util.LogHelper;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
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