/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.items.special.effects;

import com.sofodev.armorplus.api.properties.iface.IRemovable;
import com.sofodev.armorplus.common.config.ModConfig;

/**
 * @author Sokratis Fotkatzikis
 * <p>
 * Provides the ability to set an entity on fire, for a specified amount of time.
 * Also the ability to disable such effect
 **/
public class Ignite implements IRemovable {

    private final boolean shouldApplyFire;
    private final int fireSeconds;

    public Ignite(ModConfig.RegistryConfig.OriginMaterial material) {
        this(material.weapons.shouldApplyFire, material.weapons.onFireSeconds);
    }

    private Ignite(boolean shouldApplyFire, int fireSeconds) {
        this.shouldApplyFire = shouldApplyFire;
        this.fireSeconds = fireSeconds;
    }

    public int getFireSeconds() {
        return fireSeconds;
    }

    @Override
    public boolean isEnabled() {
        return shouldApplyFire;
    }
}
