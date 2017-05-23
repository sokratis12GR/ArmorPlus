/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry

import net.thedragonteam.armorplus.armors.APArmorMaterial
import net.thedragonteam.armorplus.armors.base.ItemArmorBase
import net.thedragonteam.armorplus.armors.base.ItemUltimateArmor
import net.thedragonteam.armorplus.items.ItemUltimateParts
import net.thedragonteam.armorplus.items.base.ItemSpecialBattleAxe
import net.thedragonteam.armorplus.items.base.ItemSpecialBow
import net.thedragonteam.armorplus.items.base.ItemSpecialSword
import net.thedragonteam.armorplus.items.enums.BattleAxes
import net.thedragonteam.armorplus.items.enums.Bows
import net.thedragonteam.armorplus.items.enums.Swords

import net.thedragonteam.armorplus.registry.ModItems.equipmentSlots
import net.thedragonteam.armorplus.registry.ModItems.theUltimateParts

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
object ModRegistryUtils {

    fun registerArmorModel(isEnabled: Boolean, armor: Array<ItemArmorBase>) {
        if (isEnabled) armor.forEach(ItemArmorBase::initModel)
    }

    fun registerArmorModel(isEnabled: Boolean, armor: Array<ItemUltimateArmor>) {
        if (isEnabled) {
            armor.forEach(ItemUltimateArmor::initModel)
            theUltimateParts.initModel()
        }
    }

    fun registerSwordModel(isEnabled: BooleanArray, sword: Array<ItemSpecialSword>) {
        sword.indices.asSequence().filter { isEnabled[it] }.forEach { it -> sword[it].initModel() }
    }

    fun registerBattleAxeModel(isEnabled: BooleanArray, battleAxe: Array<ItemSpecialBattleAxe>) {
        battleAxe.indices.asSequence().filter { isEnabled[it] }.forEach { it -> battleAxe[it].initModel() }
    }

    fun registerBowModel(isEnabled: BooleanArray, bow: Array<ItemSpecialBow>) {
        bow.indices.asSequence().filter { isEnabled[it] }.forEach { it -> bow[it].initModel() }
    }

    fun registerArmor(isEnabled: Boolean, armor: Array<ItemArmorBase>, armorMaterial: APArmorMaterial) {
        if (isEnabled) {
            armor.indices.forEach { i -> armor[i] = ItemArmorBase(armorMaterial, equipmentSlots[i]) }
        }
    }

    fun registerArmor(isEnabled: Boolean, armor: Array<ItemUltimateArmor>) {
        if (isEnabled) {
            armor.indices.forEach { i -> armor[i] = ItemUltimateArmor(equipmentSlots[i]) }
            theUltimateParts = ItemUltimateParts()
        }
    }

    fun registerSword(isEnabled: BooleanArray, sword: Array<ItemSpecialSword>, material: Array<Swords>) {
        sword.indices.asSequence().filter { isEnabled[it] }.forEach { it -> sword[it] = ItemSpecialSword(material[it]) }

    }

    fun registerBattleAxe(isEnabled: BooleanArray, battleAxe: Array<ItemSpecialBattleAxe>, material: Array<BattleAxes>) {
        battleAxe.indices.asSequence().filter { isEnabled[it] }.forEach { battleAxe[it] = ItemSpecialBattleAxe(material[it]) }

    }

    fun registerBow(isEnabled: BooleanArray, bow: Array<ItemSpecialBow>, material: Array<Bows>) {
        for (it in bow.indices.asSequence().filter { isEnabled[it] }) {
            bow[it] = ItemSpecialBow(material[it])
        }
    }
}