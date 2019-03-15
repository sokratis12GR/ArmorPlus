/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.weapons.effects;

import com.sofodev.armorplus.config.ModConfig.RegistryConfig.OriginMaterial;

/**
 * @author Sokratis Fotkatzikis
 */
public class WeaponEffects {
    private final Negative negative;
    private final Ignite ignite;

    public WeaponEffects(OriginMaterial weapons) {
        this.negative = new Negative(weapons);
        this.ignite = new Ignite(weapons);
    }

    public WeaponEffects(Negative negative, Ignite ignite) {
        this.negative = negative;
        this.ignite = ignite;
    }

    public Negative getNegative() {
        return negative;
    }

    public Ignite getIgnite() {
        return ignite;
    }
}
