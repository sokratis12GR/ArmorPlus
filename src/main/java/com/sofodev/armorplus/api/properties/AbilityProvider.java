/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.properties;

import com.sofodev.armorplus.api.properties.iface.IRemovable;
import com.sofodev.armorplus.config.Abilities;

/**
 * @author Sokratis Fotkatzikis
 */
public class AbilityProvider implements IRemovable {

    private final Abilities abilities;
    private final boolean enabled;

    public AbilityProvider() {
        this("empty", -1, 0, false);
    }

    public AbilityProvider(Abilities ability) {
        this(ability.name, ability.level, ability.duration);
    }

    public AbilityProvider(String ability) {
        this(ability, 0, 12, true);
    }


    public AbilityProvider(String ability, int duration) {
        this(ability, 0, duration, true);
    }

    public AbilityProvider(String ability, int abilityLevel, int duration) {
        this(ability, abilityLevel, duration, true);
    }

    public AbilityProvider(String ability, int abilityLevel, int duration, boolean enabled) {
        this(new String[]{ability}, new int[]{abilityLevel}, new int[]{duration}, enabled);
    }

    public AbilityProvider(String[] abilities, int[] abilityLevels, int[] abilityDuration) {
        this(abilities, abilityLevels, abilityDuration, true);
    }

    public AbilityProvider(String[] abilities, int[] abilityLevels, int[] abilityDuration, boolean enabled) {
        this.abilities = new Abilities(abilities, abilityDuration, abilityLevels);
        this.enabled = enabled;
    }

    public Abilities getAbilities() {
        return abilities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}