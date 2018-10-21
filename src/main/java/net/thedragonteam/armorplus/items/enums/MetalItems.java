/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.enums;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public enum MetalItems {
    ELECTRICAL_INGOT,
    STEEL_INGOT;

    private final int id;

    MetalItems() {
        this.id = ordinal();
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public int getId() {
        return id;
    }
}