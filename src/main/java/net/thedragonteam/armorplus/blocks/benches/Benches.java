/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.benches;

import net.minecraft.util.IStringSerializable;

public enum Benches implements IStringSerializable {
    WORKBENCH("workbench"),
    HIGH_TECH("high_tech_bench"),
    ULTI_TECH("ulti_tech_bench"),
    CHAMPION("champion_bench"),
    WORKBENCH_NEW("workbench_new");

    private final String name;

    Benches(String nameIn) {
        this.name = nameIn;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }
}
