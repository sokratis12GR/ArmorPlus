/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.armors;

public enum Material {
    NONE(0),
    COAL(1),
    LAPIS(1),
    REDSTONE(1),
    EMERALD(2),
    OBSIDIAN(2),
    INFUSED_LAVA(2),
    GUARDIAN(3),
    SUPER_STAR(3),
    ENDER_DRAGON(3),
    CHICKEN(1),
    SLIME(1),
    ARDITE(2),
    COBALT(2),
    KNIGHT_SLIME(2),
    PIG_IRON(2),
    MANYULLYN(2),
    ULTIMATE(4);

    private final int limit;

    Material(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }
}
