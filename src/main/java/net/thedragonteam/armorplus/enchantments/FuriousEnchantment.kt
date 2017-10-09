/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.enchantments

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnumEnchantmentType
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.init.MobEffects
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.potion.PotionEffect

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
class FuriousEnchantment : EnchantmentBase("furious", Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR, arrayOf(EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET), 1, 3, 10, 15, true, true) {

    override fun onUserHurt(user: EntityLivingBase?, attacker: Entity?, level: Int) {
        val levelIn = EnchantmentBase.Levels.values()[level]
        when (levelIn) {
            EnchantmentBase.Levels.ZERO -> {
            }
            EnchantmentBase.Levels.ONE -> user?.addPotionEffect(PotionEffect(MobEffects.STRENGTH, 460, 0))
            EnchantmentBase.Levels.TWO -> {
                user?.addPotionEffect(PotionEffect(MobEffects.STRENGTH, 460, 0))
                user?.addPotionEffect(PotionEffect(MobEffects.SPEED, 460, 0))
            }
            EnchantmentBase.Levels.THREE -> {
                user?.addPotionEffect(PotionEffect(MobEffects.STRENGTH, 460, 1))
                user?.addPotionEffect(PotionEffect(MobEffects.SPEED, 920, 0))
            }
            else -> {
                return
            }
        }
    }
}
