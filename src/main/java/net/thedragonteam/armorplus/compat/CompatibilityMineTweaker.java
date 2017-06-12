/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat;

/**
 * net.thedragonteam.armorplus.compat.jei
 * ArmorPlus created by sokratis12GR on 6/21/2016 10:54 PM.
 * - TheDragonTeam
 */
public class CompatibilityMineTweaker implements ICompatibility {
    @Override
    public void loadCompatibility(InitializationPhase phase) {
     //   switch (phase) {
     //       case POST_INIT:
     //           try {
     //               MTArmorPlusPlugin.init();
     //           } catch (Throwable e) {
     //               LogHelper.error("ArmorPlus seems to be having trouble with CraftTweaker.");
     //           }
     //   }
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