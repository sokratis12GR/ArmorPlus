/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.potions.base;

import net.minecraft.potion.Potion;
import net.thedragonteam.armorplus.util.Utils;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public abstract class PotionBase extends Potion {

    public PotionBase(Boolean isBadEffectIn, int liquidColorIn, String potionName) {
        super(isBadEffectIn, liquidColorIn);
        this.setPotionName(Utils.setName(potionName));
        this.setRegistryName(potionName);
    }
}
