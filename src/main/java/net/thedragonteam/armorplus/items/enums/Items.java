/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.enums;

public enum Items {
    ELECTRICAL_INGOT("electrical_ingot", 0),
    STEEL_INGOT("steel_ingot", 1);

    private final String name;

    private final int id;

    Items(String nameIn, int ID) {
        this.name = nameIn;
        this.id = ID;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }
}
