/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.items.enums;

/**
 * @author Sokratis Fotkatzikis
 **/
public enum Cosmetics {
    THE_DRAGON_TEAM,
    MODDED_CITY,
    JON_BAMS(true),
    TWITCH,
    BEAM,
    BTM_MOON,
    M1JORDAN,
    TEAM_RAPTURE;

    private final boolean subTypes;
    private final int id;

    Cosmetics() {
        this(false);
    }

    Cosmetics(boolean hasSubTypes) {
        this.id = ordinal();
        this.subTypes = hasSubTypes;
    }

    public boolean hasSubTypes() {
        return subTypes;
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public int getId() {
        return id;
    }
}
