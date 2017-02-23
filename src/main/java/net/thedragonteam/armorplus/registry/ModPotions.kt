/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry

import net.minecraft.potion.Potion
import net.minecraftforge.fml.common.registry.GameRegistry
import net.thedragonteam.armorplus.potions.PotionEmpty

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
object ModPotions {

    val EMPTY: Potion = PotionEmpty() //To Prevent Nulls :D

    fun registerPotions() {
        GameRegistry.register(PotionEmpty())
    }

}
