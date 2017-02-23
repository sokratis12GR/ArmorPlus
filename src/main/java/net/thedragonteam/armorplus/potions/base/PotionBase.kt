/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.potions.base

import net.minecraft.potion.Potion

import net.thedragonteam.armorplus.util.Utils.setName

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
abstract class PotionBase(isBadEffectIn: Boolean, liquidColorIn: Int, potionName: String) : Potion(isBadEffectIn, liquidColorIn) {

    init {
        this.setPotionName(setName(potionName))
        this.setRegistryName(potionName)
    }
}
