/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.potions.base

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
open class PotionBaseGood(liquidColorIn: Int, potionName: String) : PotionBase(false, liquidColorIn, potionName) {

    init {
        this.setBeneficial()
    }
}
