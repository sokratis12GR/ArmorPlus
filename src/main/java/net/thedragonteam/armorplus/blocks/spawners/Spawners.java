/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.spawners;

import net.minecraft.util.IStringSerializable;

public enum Spawners implements IStringSerializable {
    ENDER_DRAGON_ZOMBIE("ender_dragon_zombie", 0),
    GUARDIAN("guardian", 1);

    private final String name;
    private final int mobSpawn;

    Spawners(String nameIn, int mobSpawn) {
        this.name = nameIn;
        this.mobSpawn = mobSpawn;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public int getMobSpawn() {
        return mobSpawn;
    }
}
