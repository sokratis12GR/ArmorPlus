/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.enchantments

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnumEnchantmentType
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemSword
import net.minecraft.item.ItemTool
import net.thedragonteam.armorplus.APConfig.debugMode
import net.thedragonteam.armorplus.APConfig.debugModeEnchantments
import net.thedragonteam.thedragonlib.util.LogHelper

class LifeStealEnchantment : EnchantmentBase("life_steal", Enchantment.Rarity.RARE, EnumEnchantmentType.ALL, arrayOf(EntityEquipmentSlot.MAINHAND), 1, 3, 10, 15, true, true) {

    override fun onEntityDamaged(user: EntityLivingBase?, target: Entity?, level: Int) {
        val levelIn = EnchantmentBase.Levels.values()[level]
        val damageDealt: Float
        when {
            user!!.heldItemMainhand.count > 0 && user.heldItemMainhand.item !is ItemTool || user.heldItemOffhand.count > 0 && user.heldItemOffhand.item !is ItemTool || user.heldItemMainhand.count > 0 && user.heldItemMainhand.item !is ItemSword || user.heldItemOffhand.count > 0 && user.heldItemOffhand.item !is ItemSword -> when (levelIn) {
                EnchantmentBase.Levels.ZERO -> {
                }
                EnchantmentBase.Levels.ONE -> user.heal(0.5f)
                EnchantmentBase.Levels.TWO -> user.heal(1.5f)
                EnchantmentBase.Levels.THREE -> user.heal(2.5f)
                else -> {
                }
            }
            user.heldItemMainhand.count > 0 && user.heldItemMainhand.item is ItemTool || user.heldItemOffhand.count > 0 && user.heldItemOffhand.item is ItemTool -> {
                damageDealt = (user.heldItemMainhand.item as ItemTool).toolMaterial.damageVsEntity
                val damageDealtTool = damageDealt / 0.5f
                when (levelIn) {
                    EnchantmentBase.Levels.ZERO -> {
                    }
                    EnchantmentBase.Levels.ONE -> if (damageDealtTool >= 0.0f) {
                        val damageGained = damageDealtTool + 0.5f
                        var healedDamage = 0.0f
                        when {
                            damageGained <= 4.0f -> healedDamage = 0.5f * damageGained + 0.5f - 0.312f
                            damageGained >= 4.5f && damageGained < 9.5f -> healedDamage = (0.5f * damageGained + 1.0f) / 2f + 0.272f
                            damageGained >= 9.5f && damageGained < 14.5f -> healedDamage = (0.5f * damageGained - 5.0f) / 2f + 0.411f
                            damageGained >= 14.5f && damageGained < 19.5f -> healedDamage = (0.5f * damageGained - 10.0f) / 2f + 0.914f
                            damageGained >= 19.5f -> healedDamage = (0.5f * damageGained - 15.0f) / 2f + 1.21f
                        }
                        user.heal(healedDamage)
                        if (debugMode && debugModeEnchantments)
                            LogHelper.info("Level 1 Dealt Damage: $damageDealt Final Damage: $damageDealtTool Gained Damage: $damageGained Healed Damage: $healedDamage")
                    }
                    EnchantmentBase.Levels.TWO -> if (damageDealtTool >= 0.0f) {
                        val damageGained = damageDealtTool + 1.5f
                        var healedDamage = 0.0f
                        when {
                            damageGained <= 4.0f -> healedDamage = 1.5f * damageGained + 0.312f - 0.34f
                            damageGained >= 4.5f && damageGained < 9.5f -> healedDamage = (1.5f * damageGained - 1.0f) / 2f - 0.82f
                            damageGained >= 9.5f && damageGained < 14.5f -> healedDamage = (1.5f * damageGained - 4.0f) / 2f - 1.11f
                            damageGained >= 14.5f && damageGained < 19.5f -> healedDamage = (1.5f * damageGained - 10.0f) / 2f - 3.0f
                            damageGained >= 19.5f -> healedDamage = (1.5f * damageGained - 15.0f) / 2f - 5.23f
                        }
                        user.heal(healedDamage)
                        if (debugMode && debugModeEnchantments)
                            LogHelper.info("Level 2 Dealt Damage: $damageDealt Final Damage: $damageDealtTool Gained Damage: $damageGained Healed Damage: $healedDamage")
                    }
                    EnchantmentBase.Levels.THREE -> if (damageDealtTool >= 0.0f) {
                        val damageGained = damageDealtTool + 2.5f
                        var healedDamage = 0.0f
                        when {
                            damageGained <= 4.0f -> healedDamage = 2.5f * damageGained - 2.0f
                            damageGained >= 4.5f && damageGained < 9.5f -> healedDamage = (2.5f * damageGained - 3.0f) / 2f - 2.53f
                            damageGained >= 9.5f && damageGained < 14.5f -> healedDamage = (2.5f * damageGained - 4.0f) / 2f - 3.4f
                            damageGained >= 14.5f && damageGained < 19.5f -> healedDamage = (2.5f * damageGained - 10.0f) / 2f - 6.5f
                            damageGained >= 19.5f -> healedDamage = (2.5f * damageGained - 15.0f) / 2f - 10.5f
                        }
                        user.heal(healedDamage)
                        if (debugMode && debugModeEnchantments)
                            LogHelper.info("Level 3 Dealt Damage: $damageDealt Final Damage: $damageDealtTool Gained Damage: $damageGained Healed Damage: $healedDamage")
                    }
                    else -> {
                    }
                }
            }
            user.heldItemMainhand.count > 0 && user.heldItemMainhand.item is ItemSword || user.heldItemOffhand.count > 0 && user.heldItemOffhand.item is ItemSword -> {
                damageDealt = (user.heldItemMainhand.item as ItemSword).damageVsEntity
                val damageDealtSword = damageDealt / 0.5f
                when (levelIn) {
                    EnchantmentBase.Levels.ZERO -> {
                    }
                    EnchantmentBase.Levels.ONE -> if (damageDealtSword >= 0.0f) {
                        val damageGained = damageDealtSword + 0.5f
                        var healedDamage = 0.0f
                        when {
                            damageGained <= 4.5f -> healedDamage = 0.5f * damageGained + 0.534f + 0.135f
                            damageGained >= 5.0f && damageGained < 10.0f -> healedDamage = (0.5f * damageGained + 1.0f) / 2f + 0.84f
                            damageGained >= 10.0f && damageGained < 15.0f -> healedDamage = (0.5f * damageGained - 4.0f) / 2f + 0.525f
                            damageGained >= 15.0f && damageGained < 20.0f -> healedDamage = (0.5f * damageGained - 6.0f) / 2f + 2.125f
                            damageGained >= 20.0f -> healedDamage = (0.5f * damageGained - 12.0f) / 2f + 2.5f
                        }
                        user.heal(healedDamage)
                        if (debugMode && debugModeEnchantments)
                            LogHelper.info("Level 1 Dealt Damage: $damageDealt Final Damage: $damageDealtSword Gained Damage: $damageGained Healed Damage: $healedDamage")
                    }
                    EnchantmentBase.Levels.TWO -> if (damageDealtSword >= 0.0f) {
                        val damageGained = damageDealtSword + 1.5f
                        var healedDamage = 0.0f
                        when {
                            damageGained <= 4.5f -> healedDamage = 1.5f * damageGained - 1.0f
                            damageGained >= 5.0f && damageGained < 10.0f -> healedDamage = (1.5f * damageGained - 1.0f) / 2f - 1.0f
                            damageGained >= 10.0f && damageGained < 15.0f -> healedDamage = (1.5f * damageGained - 4.0f) / 2f - 1.2f
                            damageGained >= 15.0f && damageGained < 20.0f -> healedDamage = (1.5f * damageGained - 10.0f) / 2f - 3.0f
                            damageGained >= 20.0f -> healedDamage = (1.5f * damageGained - 15.0f) / 2f - 3.3f
                        }
                        user.heal(healedDamage)
                        if (debugMode && debugModeEnchantments)
                            LogHelper.info("Level 2 Dealt Damage: $damageDealt Final Damage: $damageDealtSword Gained Damage: $damageGained Healed Damage: $healedDamage")
                    }
                    EnchantmentBase.Levels.THREE -> if (damageDealtSword >= 0.0f) {
                        val damageGained = damageDealtSword + 2.5f
                        var healedDamage = 0.0f
                        when {
                            damageGained <= 4.5f -> healedDamage = 2.5f * damageGained - 2.0f
                            damageGained >= 5.0f && damageGained < 10.0f -> healedDamage = (2.5f * damageGained - 3.0f) / 2f - 2.52f
                            damageGained >= 10.0f && damageGained < 15.0f -> healedDamage = (2.5f * damageGained - 4.0f) / 2f - 5.5f
                            damageGained >= 15.0f && damageGained < 20.0f -> healedDamage = (2.5f * damageGained - 10.0f) / 2f - 6.3f
                            damageGained >= 20.0f -> healedDamage = (2.5f * damageGained - 15.0f) / 2f - 13.22f
                        }
                        user.heal(healedDamage)
                        if (debugMode && debugModeEnchantments)
                            LogHelper.info("Level 3 Dealt Damage: $damageDealt Final Damage: $damageDealtSword Gained Damage: $damageGained Healed Damage: $healedDamage")
                    }
                    else -> {
                    }
                }
            }
        }
    }
}
