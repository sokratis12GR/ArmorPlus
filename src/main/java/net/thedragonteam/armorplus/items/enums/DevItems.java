/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.enums;

public enum DevItems {
    THE_DRAGON_TEAM("the_dragon_team", 0),
    MODDED_CITY("modded_city", 1),
    JON_BAMS("jon_bams", 2, true),
    TWITCH("twitch", 3),
    BEAM("beam", 4);

    private final String name;
    private final boolean subTypes;
    private final int id;

    DevItems(String nameIn, int idIn) {
        this(nameIn, idIn, false);
    }

    DevItems(String nameIn, int idIn, boolean hasSubTypes) {
        this.name = nameIn;
        this.id = idIn;
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
