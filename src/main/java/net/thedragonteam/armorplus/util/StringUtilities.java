/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.util;

public class StringUtilities {

    /**
     * @param value
     *
     * @return
     */
    public static String upperCaseFirst(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}