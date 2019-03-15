/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.properties.iface;

import com.sofodev.armorplus.api.properties.AbilityCanceller;
import com.sofodev.armorplus.api.properties.AbilityProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sokratis Fotkatzikis
 * <p>
 * The IEffectHolder implementor will have the ability to create specific objects to contain effects and/or remove them.
 */
public interface IEffectHolder {

    /**
     * @return the abilities that will be applied
     * @since A+ 11.15.0.43
     */
    AbilityProvider getApplicableAbilities();

    /**
     * @return The abilities that will be cancelled.
     * @since A+ 11.15.0.43
     */
    AbilityCanceller getRemovableAbilities();

    //These will be removed in [1.13)

    /**
     * @return A list of the effects that will be applied to the entity
     * @deprecated since A+ 11.15.0.43. Use {@link #getApplicableAbilities()}
     */
    @Deprecated
    default List<String> getApplyEffectNames() {
        return new ArrayList<>();
    }

    /**
     * @return A list of the amplifier levels of the given effects provided by {@link #getApplyEffectNames()}
     * @deprecated since A+ 11.15.0.43. Use {@link #getApplicableAbilities()}
     */
    @Deprecated
    default List<Integer> getApplyEffectLevels() {
        return new ArrayList<>();
    }

    /**
     * @return A list of the durations of the given effects provided by {@link #getApplyEffectNames()} ()}
     * @deprecated since A+ 11.15.0.43. Use {@link #getApplicableAbilities()}
     */
    @Deprecated
    default List<Integer> getApplyEffectDurations() {
        return new ArrayList<>();
    }

    /**
     * @return A list of the effects that will be removed from the entity
     */
    @Deprecated
    default List<String> getRemoveEffectNames() {
        return new ArrayList<>();
    }
}
