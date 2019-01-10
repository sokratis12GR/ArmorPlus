/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis
 */
public class ArmorAbility {

    private final AbilityCanceller abilityCanceller;
    private final AbilityProvider abilityProvider;

    public ArmorAbility() {
        this(new AbilityProvider());
    }

    public ArmorAbility(String ability) {
        this(new AbilityProvider(ability));
    }

    public ArmorAbility(String ability, int abilityLevel) {
        this(new AbilityProvider(ability, abilityLevel, 12));
    }

    public ArmorAbility(String cancelledAbility, String appliedAbility) {
        this(new AbilityCanceller(cancelledAbility), new AbilityProvider(appliedAbility));
    }

    public ArmorAbility(String cancelledAbility, String appliedAbility, int abilityLevel) {
        this(new AbilityCanceller(cancelledAbility), new AbilityProvider(appliedAbility, abilityLevel, 12));
    }

    public ArmorAbility(AbilityProvider abilityProvider) {
        this(new AbilityCanceller(), abilityProvider);
    }

    public ArmorAbility(AbilityCanceller abilityCanceller) {
        this(abilityCanceller, new AbilityProvider());
    }

    public ArmorAbility(AbilityCanceller abilityCanceller, AbilityProvider abilityProvider) {
        this.abilityCanceller = abilityCanceller;
        this.abilityProvider = abilityProvider;
    }

    public AbilityCanceller getAbilityCanceller() {
        return abilityCanceller;
    }

    public AbilityProvider getAbilityProvider() {
        return abilityProvider;
    }
}
