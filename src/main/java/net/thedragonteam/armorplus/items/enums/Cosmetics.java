/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.enums;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public enum Cosmetics {
    THE_DRAGON_TEAM("the_dragon_team"),
    MODDED_CITY("modded_city"),
    JON_BAMS("jon_bams", true),
    TWITCH("twitch"),
    BEAM("beam"),
    BTM_MOON("btm_moon"),
    M1JORDAN("m1jordan"),
    TEAM_RAPTURE("team_rapture");

    private final String name;
    private final boolean subTypes;
    private final int id;

    Cosmetics(String nameIn) {
        this(nameIn, false);
    }

    Cosmetics(String nameIn, boolean hasSubTypes) {
        this.name = nameIn;
        this.id = ordinal();
        this.subTypes = hasSubTypes;
    }

    public String toString() {
        return this.name;
    }

    public boolean hasSubTypes() {
        return subTypes;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }
}
