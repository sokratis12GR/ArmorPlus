package sokratis12GR.ArmorPlus.compat.jei;

import sokratis12GR.ArmorPlus.compat.ICompatibility;

/**
 * sokratis12GR.ArmorPlus.compatibility.jei
 * ArmorPlus created by sokratis12GR on 6/21/2016 10:54 PM.
 */
public class CompatibilityJustEnoughItems implements ICompatibility {
    @Override
    public void loadCompatibility(InitializationPhase phase) {
    }

    @Override
    public String getMODID() {
        return "JEI";
    }

    @Override
    public boolean enableCompat() {
        return true;
    }
}