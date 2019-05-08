/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.caps.abilities.data;

import com.sofodev.armorplus.common.util.PotionUtils.PotionValue;
import net.minecraft.util.ResourceLocation;

import static com.sofodev.armorplus.common.util.PotionUtils.PotionValue.GOOD;

public class AbilityPotion {
    private ResourceLocation rl;
    private int duration;
    private int amplifier;
    private boolean ambientIn;
    private PotionValue type;

    public AbilityPotion() {
        this(null, 240, 0, false, GOOD);
    }

    public AbilityPotion(ResourceLocation rl) {
        this(rl, 240, 0, false, GOOD);
    }

    public AbilityPotion(ResourceLocation rl, int amplifier) {
        this(rl, 240, amplifier, false, GOOD);
    }

    public AbilityPotion(ResourceLocation rl, int amplifier, PotionValue type) {
        this(rl, 240, amplifier, false, type);
    }

    public AbilityPotion(ResourceLocation rl, int amplifier, boolean ambientIn, PotionValue type) {
        this(rl, 240, amplifier, ambientIn, type);
    }

    public AbilityPotion(ResourceLocation rl, int duration, int amplifier, boolean ambientIn, PotionValue type) {
        this.setResourceLocation(rl);
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambientIn = ambientIn;
        this.type = type;
    }

    public AbilityPotion setResourceLocation(ResourceLocation rl) {
        this.rl = rl;
        return this;
    }

    public AbilityPotion setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public AbilityPotion setAmplifier(int amplifier) {
        this.amplifier = amplifier;
        return this;
    }

    public AbilityPotion setAmbientIn(boolean ambientIn) {
        this.ambientIn = ambientIn;
        return this;
    }

    public AbilityPotion setType(PotionValue type) {
        this.type = type;
        return this;
    }

    public ResourceLocation getResourceLocation() {
        return rl;
    }

    public int getDuration() {
        return duration;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public boolean isAmbientIn() {
        return ambientIn;
    }

    public PotionValue getType() {
        return type;
    }
}