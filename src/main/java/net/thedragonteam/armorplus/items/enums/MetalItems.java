/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.enums;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public enum MetalItems {
    ELECTRICAL_INGOT("electrical_ingot"),
    STEEL_INGOT("steel_ingot");

    private final String name;

    private final int id;

    MetalItems(String nameIn) {
        this.name = nameIn;
        this.id = ordinal();
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }
}