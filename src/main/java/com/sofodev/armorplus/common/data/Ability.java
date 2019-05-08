/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.data;

public class Ability {

    public String name;
    public int duration;
    public int level;

    public Ability(String name, int duration, int level) {
        this.name = name;
        this.duration = duration;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getLevel() {
        return level;
    }
}
