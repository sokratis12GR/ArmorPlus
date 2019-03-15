/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.potions.base;

import net.minecraft.potion.Potion;

/**
 * @author Sokratis Fotkatzikis
 **/
public abstract class PotionBase extends Potion {

    public PotionBase(Boolean isBad, int liquidColor) {
        super(isBad, liquidColor);
    }
}
