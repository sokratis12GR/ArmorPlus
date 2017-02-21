/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.potions.base;

import net.minecraft.potion.Potion;

import static net.thedragonteam.armorplus.util.Utils.setName;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public abstract class PotionBase extends Potion {

    public PotionBase(boolean isBadEffectIn, int liquidColorIn, String potionName) {
        super(isBadEffectIn, liquidColorIn);
        this.setPotionName(setName(potionName));
        this.setRegistryName(potionName);
    }
}
