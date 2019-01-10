/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis
 */
public class CombinedArmor {

    private final String color;
    private final ArmorAbility ability;
    private final Armor armor;

    public CombinedArmor(String color, Armor armor) {
        this(color, new ArmorAbility(), armor);
    }

    public CombinedArmor(String color, String ability, boolean cancelling, Armor armor) {
        this(color, cancelling ? new ArmorAbility(new AbilityCanceller(ability)) : new ArmorAbility(ability), armor);
    }

    public CombinedArmor(String color, String ability, int level, Armor armor) {
        this(color, new ArmorAbility(ability, level), armor);
    }

    public CombinedArmor(String color, String cancelledAbility, String appliedAbility, Armor armor) {
        this(color, new ArmorAbility(cancelledAbility, appliedAbility), armor);
    }

    public CombinedArmor(String color, String cancelledAbility, String appliedAbility, int level, Armor armor) {
        this(color, new ArmorAbility(cancelledAbility, appliedAbility, level), armor);
    }

    public CombinedArmor(String color, ArmorAbility ability, Armor armor) {
        this.color = color;
        this.ability = ability;
        this.armor = armor;
    }

    public String getColor() {
        return color;
    }

    public ArmorAbility getAbility() {
        return ability;
    }

    public Armor getArmor() {
        return armor;
    }
}
