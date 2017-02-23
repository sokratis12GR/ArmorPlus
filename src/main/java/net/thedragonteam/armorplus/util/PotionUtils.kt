/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util

import net.minecraft.entity.EntityLivingBase
import net.minecraft.potion.Potion
import net.minecraft.potion.Potion.getPotionFromResourceLocation
import net.minecraft.potion.PotionEffect
import net.minecraft.util.text.translation.I18n.translateToLocal
import net.thedragonteam.armorplus.registry.ModPotions

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
 object PotionUtils {

    fun addPotion(entity: EntityLivingBase, potion: String?, duration: Int, amplifier: Int, ambientIn: Boolean, showParticlesIn: Boolean) {
        if (potion != null)
            entity.addPotionEffect(PotionEffect(getPotion(potion)!!, duration, amplifier, ambientIn, showParticlesIn))
    }

    fun addPotion(entity: EntityLivingBase, potion: Potion?, duration: Int, amplifier: Int, ambientIn: Boolean, showParticlesIn: Boolean) {
        if (potion != null)
            entity.addPotionEffect(PotionEffect(potion, duration, amplifier, ambientIn, showParticlesIn))
    }

    fun addPotion(entity: EntityLivingBase, potion: String?, duration: Int, amplifier: Int, ambientIn: Boolean, potionType: PotionType) {
        if (potion != null)
            addPotion(entity, getPotion(potion), duration, amplifier, ambientIn, potionType.hasParticles())
    }

    fun addPotion(entity: EntityLivingBase, potion: Potion?, duration: Int, amplifier: Int, ambientIn: Boolean, potionType: PotionType) {
        if (potion != null) addPotion(entity, potion, duration, amplifier, ambientIn, potionType.hasParticles())
    }

    fun addPotion(entity: EntityLivingBase, potion: Potion?, duration: Int, amplifier: Int, potionType: PotionType) {
        if (potion != null) addPotion(entity, potion, duration, amplifier, false, potionType.hasParticles())
    }

    fun addPotion(entity: EntityLivingBase, potion: String?, amplifier: Int, potionType: PotionType) {
        if (potion != null) addPotion(entity, getPotion(potion), 240, amplifier, false, potionType.hasParticles())
    }

    fun addPotion(entity: EntityLivingBase, potion: Potion?, amplifier: Int, potionType: PotionType) {
        if (potion != null) addPotion(entity, potion, 240, amplifier, false, potionType.hasParticles())
    }

    fun removePotion(entity: EntityLivingBase, potion: String?) {
        entity.removePotionEffect(if (potion != null) getPotion(potion) else ModPotions.EMPTY)
    }

    fun removePotion(entity: EntityLivingBase, potion: Potion?) {
        entity.removePotionEffect(potion ?: ModPotions.EMPTY)
    }

    fun localizePotion(resourceLocation: String): String {
        return if (getPotion(resourceLocation) != null) translateToLocal(getPotion(resourceLocation)!!.name + ".name").trim { it <= ' ' } else ""
    }

    fun getPotion(resourceLocation: String): Potion? {
        return if (getPotionFromResourceLocation(resourceLocation) != null) getPotionFromResourceLocation(resourceLocation) else ModPotions.EMPTY
    }

    enum class PotionType constructor(private val showParticles: Boolean) {
        GOOD(false),
        BAD(true);

        fun hasParticles(): Boolean {
            return showParticles
        }
    }
}
