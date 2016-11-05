/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.spawners;

import net.minecraft.util.IStringSerializable;

public enum Spawners implements IStringSerializable {
    ENDER_DRAGON_ZOMBIE("ender_dragon_zombie"),
    GUARDIAN("guardian");

    private final String name;

    Spawners(String nameIn) {
        this.name = nameIn;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }
}
