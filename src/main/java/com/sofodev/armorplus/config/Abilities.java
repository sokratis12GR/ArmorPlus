/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.config;

import java.util.stream.IntStream;

public class Abilities {

    public String[] name = new String[100];
    public int[] duration = new int[100];
    public int[] level = new int[100];

    public Abilities(String[] name, int[] duration, int[] level) {
        this.name = name;
        this.duration = duration;
        this.level = level;
    }

    public Abilities(Ability[] abilities) {
        IntStream.range(0, abilities.length).forEach(i -> {
            name[i] = abilities[i].getName();
            duration[i] = abilities[i].getDuration();
            level[i] = abilities[i].getLevel();
        });
    }

    public String[] getName() {
        return name;
    }

    public int[] getDuration() {
        return duration;
    }

    public int[] getLevel() {
        return level;
    }
}
