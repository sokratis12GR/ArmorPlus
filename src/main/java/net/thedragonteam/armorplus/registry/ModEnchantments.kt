/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry

import net.minecraft.enchantment.Enchantment
import net.minecraftforge.fml.common.registry.GameRegistry
import net.thedragonteam.armorplus.enchantments.FuriousEnchantment
import net.thedragonteam.armorplus.enchantments.LifeStealEnchantment

object ModEnchantments {

    val FURIOUS: Enchantment = FuriousEnchantment()
    val LIFESTEAL: Enchantment = LifeStealEnchantment()

    fun registerEnchantments() {
        GameRegistry.register(LifeStealEnchantment())
        GameRegistry.register(FuriousEnchantment())
    }
}
