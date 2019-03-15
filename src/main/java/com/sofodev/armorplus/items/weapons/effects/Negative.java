/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.weapons.effects;

import com.sofodev.armorplus.api.properties.iface.IRemovable;
import com.sofodev.armorplus.config.ModConfig;
import com.sofodev.armorplus.data.Abilities;

/**
 * @author Sokratis Fotkatzikis
 * <p>
 * Provides the ability of creating multiple negative effects.
 **/
public class Negative implements IRemovable {

    private final boolean enableEffect;
    private final String[] effects;
    private final int[] effectLevels;
    private final int[] effectDurations;

    public Negative(ModConfig.RegistryConfig.OriginMaterial material) {
        this(material.weapons.enableEffects, material.weapons.abilities);
    }

    private Negative(boolean enableEffect, Abilities abilities) {
        this.enableEffect = enableEffect;
        this.effects = abilities.name;
        this.effectLevels = abilities.level;
        this.effectDurations = abilities.duration;
    }

    @Override
    public boolean isEnabled() {
        return enableEffect;
    }

    public String[] getEffects() {
        return effects;
    }

    public int[] getEffectLevels() {
        return effectLevels;
    }

    public int[] getEffectDurations() {
        return effectDurations;
    }
}
