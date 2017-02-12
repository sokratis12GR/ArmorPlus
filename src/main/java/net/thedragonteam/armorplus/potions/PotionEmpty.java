/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.potions;

import net.minecraft.potion.Potion;

import static net.thedragonteam.armorplus.util.Utils.setName;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class PotionEmpty extends Potion {

    public PotionEmpty() {
        super(false, 0xfff);
        setPotionName(setName("empty"));
        setRegistryName("empty");
    }
}
