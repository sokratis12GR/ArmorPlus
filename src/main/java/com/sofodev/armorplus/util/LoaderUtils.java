/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.util;

import com.sofodev.armorplus.config.ModConfig;

import static net.minecraftforge.fml.common.Loader.isModLoaded;

/**
 * @author Sokratis Fotkatzikis
 * <p>
 * LoaderUtils is an utility class that helps checking if integrations with other mods are disabled.
 * Also it checks if the other mods are present in order to validate the integration.
 */
public class LoaderUtils {

    public static boolean isTiCIntegrationEnabled() {
        return LoaderUtils.isTiCLoaded() && ModConfig.IntegrationsConfig.enableTConstructIntegration;
    }

    public static boolean isTOPIntegrationEnabled() {
        return LoaderUtils.isTOPLoaded() && ModConfig.IntegrationsConfig.enableTOPIntegration;
    }

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

    public static boolean isTOPLoaded() {
        return isModLoaded("theoneprobe");
    }

}
