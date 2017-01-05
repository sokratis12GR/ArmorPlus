/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import java.util.Calendar;

public class HolidayUtils {

    public boolean isChristmas() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER) {
            if (calendar.get(Calendar.DAY_OF_MONTH) > 22 && calendar.get(Calendar.DAY_OF_MONTH) > 29) {
                return true;
            }
        }
        return false;
    }
}
