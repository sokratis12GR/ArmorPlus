package net.thedragonteam.armorplus.util;

import static net.minecraftforge.fml.common.Loader.isModLoaded;

/**
 * ArmorPlus - 1.12 created by sokratis12GR
 * - TheDragonTeam
 */
public class LoaderUtils {

    public static boolean isTeslaLoaded() {
        return isModLoaded("tesla");
    }

    public static boolean isBaublesLoaded() {
        return isModLoaded("baubles");
    }

    public static boolean isTiCLoaded() {
        return isModLoaded("tconstruct");
    }

    public static boolean isTDLLoaded() {
        return isModLoaded("thedragonlib");
    }

}
