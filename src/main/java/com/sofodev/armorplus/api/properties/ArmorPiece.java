/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.properties;

import com.sofodev.armorplus.api.properties.iface.IArmor;

/**
 * @author Sokratis Fotkatzikis
 */
public class ArmorPiece implements IArmor {

    private final int armor;

    public ArmorPiece(int armor) {
        this.armor = armor;
    }

    public static ArmorPiece create(int armor) {
        return new ArmorPiece(armor);
    }

    @Override
    public int getArmor() {
        return this.armor;
    }
}
