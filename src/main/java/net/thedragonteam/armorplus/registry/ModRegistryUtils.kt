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
        if (isEnabled) for (anArmor in armor) anArmor.initModel()
    }

    fun registerArmorModel(isEnabled: Boolean, armor: Array<ItemUltimateArmor>) {
        if (isEnabled) {
            for (anArmor in armor) anArmor.initModel()
            theUltimateParts.initModel()
        }
    }

    fun registerSwordModel(isEnabled: BooleanArray, sword: Array<ItemSpecialSword>) {
        isEnabled
                .asSequence()
                .filter { it }
                .forEach { for (aSword in sword) aSword.initModel() }
    }

    fun registerBattleAxeModel(isEnabled: BooleanArray, battleAxe: Array<ItemSpecialBattleAxe>) {
        isEnabled
                .asSequence()
                .filter { it }
                .forEach { for (aBattleAxe in battleAxe) aBattleAxe.initModel() }
    }

    fun registerBowModel(isEnabled: BooleanArray, bow: Array<ItemSpecialBow>) {
        isEnabled
                .asSequence()
                .filter { it }
                .forEach { for (aBow in bow) aBow.initModel() }
    }

    fun registerArmor(isEnabled: Boolean, armor: Array<ItemArmorBase>, armorMaterial: APArmorMaterial) {
        if (isEnabled)
            for (i in armor.indices)
                armor[i] = ItemArmorBase(armorMaterial, equipmentSlots[i])
    }

    fun registerArmor(isEnabled: Boolean, armor: Array<ItemUltimateArmor>) {
        if (isEnabled) {
            for (i in armor.indices)
                armor[i] = ItemUltimateArmor(equipmentSlots[i])
            theUltimateParts = ItemUltimateParts()
        }
    }

    fun registerSword(isEnabled: BooleanArray, sword: Array<ItemSpecialSword>, material: Array<Swords>) {
        sword.indices
                .asSequence()
                .filter { isEnabled[it] }
                .forEach { sword[it] = ItemSpecialSword(material[it]) }
    }

    fun registerBattleAxe(isEnabled: BooleanArray, battleAxe: Array<ItemSpecialBattleAxe>, material: Array<BattleAxes>) {
        battleAxe.indices
                .asSequence()
                .filter { isEnabled[it] }
                .forEach { battleAxe[it] = ItemSpecialBattleAxe(material[it]) }
    }

    fun registerBow(isEnabled: BooleanArray, bow: Array<ItemSpecialBow>, material: Array<Bows>) {
        bow.indices
                .asSequence()
                .filter { isEnabled[it] }
                .forEach { bow[it] = ItemSpecialBow(material[it]) }
    }
}