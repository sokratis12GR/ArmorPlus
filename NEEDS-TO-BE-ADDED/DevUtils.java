/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus;


/**
 * @author Sokratis Fotkatzikis
 **/
@SuppressWarnings("ConstantConditions")
public class DevUtils {

    private static final boolean ENABLE_TOWER_DEV = false;
    private static final boolean ENABLE_DEV_TOOL = false;

    public static boolean enableTowerDevEnv() {
        return isDev(ENABLE_TOWER_DEV);
    }

    public static boolean enableDevTool() {
        return isDev(ENABLE_DEV_TOOL);
    }

    private static boolean isDev(boolean type) {
        return type;
    }
}
