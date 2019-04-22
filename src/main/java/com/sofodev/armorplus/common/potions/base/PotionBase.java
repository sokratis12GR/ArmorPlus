/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.potions.base;

import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.potion.Potion;

/**
 * @author Sokratis Fotkatzikis
 **/
public abstract class PotionBase extends Potion {

    public PotionBase(Boolean isBad, int liquidColor, String name) {
        super(isBad, liquidColor);
        this.setPotionName(Utils.setName(name));
        this.setRegistryName(name);
    }
}
