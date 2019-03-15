/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.util;

import net.minecraftforge.fml.ModList;

/**
 * @author Sokratis Fotkatzikis
 * <p>
 * LoaderUtils is an utility class that helps checking if integrations with other mods are disabled.
 * Also it checks if the other mods are present in order to validate the integration.
 */
public class LoaderUtils {

    public static boolean isTeslaLoaded() {
        return ModList.get().isLoaded("tesla");
    }

    public static boolean isBaublesLoaded() {
        return ModList.get().isLoaded("baubles");
    }

    public static boolean isTiCLoaded() {
        return ModList.get().isLoaded("tconstruct");
    }

    public static boolean isTDLLoaded() {
        return ModList.get().isLoaded("thedragonlib");
    }

    public static boolean isTOPLoaded() {
        return ModList.get().isLoaded("theoneprobe");
    }

}
