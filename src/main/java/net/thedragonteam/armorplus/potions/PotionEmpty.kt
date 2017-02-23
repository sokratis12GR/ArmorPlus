/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.potions

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.potion.Potion
import net.minecraft.potion.PotionEffect
import net.thedragonteam.armorplus.potions.base.PotionBaseGood

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
class PotionEmpty : PotionBaseGood(0xfff, "empty") {

    override fun shouldRender(effect: PotionEffect?): Boolean {
        return false
    }

    override fun shouldRenderHUD(effect: PotionEffect?): Boolean {
        return false
    }

    override fun shouldRenderInvText(effect: PotionEffect?): Boolean {
        return false
    }

    override fun isReady(duration: Int, amplifier: Int): Boolean {
        return false
    }

    override fun isInstant(): Boolean {
        return true
    }

    override fun isBadEffect(): Boolean {
        return true
    }

    override fun setIconIndex(width: Int, height: Int): Potion {
        return super.setIconIndex(width, height)
    }

    override fun performEffect(entityLivingBaseIn: EntityLivingBase, amplifier: Int) {
        super.performEffect(entityLivingBaseIn, amplifier)
    }

    override fun affectEntity(source: Entity?, indirectSource: Entity?, entityLivingBaseIn: EntityLivingBase, amplifier: Int, health: Double) {
        super.affectEntity(source, indirectSource, entityLivingBaseIn, amplifier, health)
    }
}
