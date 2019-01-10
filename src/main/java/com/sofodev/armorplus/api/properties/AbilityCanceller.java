/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis
 */
public class AbilityCanceller {

    private final String[] cancelledAbilities;

    public AbilityCanceller() {
        this("empty");
    }

    public AbilityCanceller(String cancelledAbility) {
        this(new String[]{cancelledAbility});
    }

    public AbilityCanceller(String[] cancelledAbilities) {
        this.cancelledAbilities = cancelledAbilities;
    }

    public String[] getAbilities() {
        return cancelledAbilities;
    }
}
