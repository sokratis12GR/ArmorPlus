/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.enums;

/**
 * @author Sokratis Fotkatzikis
 **/
public enum Cosmetics {
    THE_DRAGON_TEAM,
    MODDED_CITY,
    JON_BAMS,
    TWITCH,
    BEAM,
    BTM_MOON,
    M1JORDAN,
    TEAM_RAPTURE;

    Cosmetics() {
    }

    public String getName() {
        return this.name().toLowerCase();
    }
}
