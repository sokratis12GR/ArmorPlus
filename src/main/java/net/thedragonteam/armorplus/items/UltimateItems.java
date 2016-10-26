/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items;

import net.minecraft.util.IStringSerializable;

public enum UltimateItems implements IStringSerializable {
    BOOTS_LEFT("boots_left"),
    BOOTS_MIDDLE("boots_middle"),
    BOOTS_RIGHT("boots_right"),
    LEGGINGS_LEFT("leggings_left"),
    LEGGINGS_MIDDLE("leggings_middle"),
    LEGGINGS_RIGHT("leggings_right"),
    CHESTPLATE_LEFT("chestplate_left"),
    CHESTPLATE_MIDDLE("chestplate_middle"),
    CHESTPLATE_RIGHT("chestplate_right"),
    HELMET_LEFT("helmet_left"),
    HELMET_MIDDLE("helmet_middle"),
    HELMET_RIGHT("helmet_right");

    private final String name;

    UltimateItems(String nameIn) {
        this.name = nameIn;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }
}
