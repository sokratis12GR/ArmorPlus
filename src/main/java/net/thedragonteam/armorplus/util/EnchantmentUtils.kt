/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util

import net.minecraft.enchantment.Enchantment

import net.minecraft.enchantment.Enchantment.*

object EnchantmentUtils {

    fun getEnchantment(name: String): Enchantment {
        return getEnchantmentByLocation(name) as Enchantment
    }

    fun getEnchantment(id: Int): Enchantment {
        return getEnchantmentByID(id) as Enchantment
    }

    fun getID(enchant: Enchantment): Int {
        return getEnchantmentID(enchant)
    }
}
