/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.thedragonteam.armorplus.armors.APArmorMaterial;
import net.thedragonteam.armorplus.armors.base.ItemArmorBase;
import net.thedragonteam.armorplus.armors.base.ItemUltimateArmor;
import net.thedragonteam.armorplus.items.ItemUltimateParts;
import net.thedragonteam.armorplus.items.base.ItemSpecialBattleAxe;
import net.thedragonteam.armorplus.items.base.ItemSpecialBow;
import net.thedragonteam.armorplus.items.base.ItemSpecialSword;
import net.thedragonteam.armorplus.items.enums.BattleAxes;
import net.thedragonteam.armorplus.items.enums.Bows;
import net.thedragonteam.armorplus.items.enums.Swords;

import static net.thedragonteam.armorplus.registry.ModItems.equipmentSlots;
import static net.thedragonteam.armorplus.registry.ModItems.theUltimateParts;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class ModRegistryUtils {

    public static void registerArmorModel(boolean isEnabled, ItemArmorBase[] armor) {
        if (isEnabled) for (ItemArmorBase anArmor : armor) anArmor.initModel();
    }

    public static void registerArmorModel(boolean isEnabled, ItemUltimateArmor[] armor) {
        if (isEnabled) {
            for (ItemUltimateArmor anArmor : armor) anArmor.initModel();
            theUltimateParts.initModel();
        }
    }

    public static void registerSwordModel(boolean isEnabled[], ItemSpecialSword[] sword) {
        for (boolean anIsEnabled : isEnabled) if (anIsEnabled) for (ItemSpecialSword aSword : sword) aSword.initModel();
    }

    public static void registerBattleAxeModel(boolean isEnabled[], ItemSpecialBattleAxe[] battleAxe) {
        for (boolean anIsEnabled : isEnabled)
        if (anIsEnabled) for (ItemSpecialBattleAxe aBattleAxe : battleAxe) aBattleAxe.initModel();
    }

    public static void registerBowModel(boolean isEnabled[], ItemSpecialBow[] bow) {
        for (boolean anIsEnabled : isEnabled)
        if (anIsEnabled) for (ItemSpecialBow aBow : bow) aBow.initModel();
    }

    public static void registerArmor(boolean isEnabled, ItemArmorBase[] armor, APArmorMaterial armorMaterial) {
        if (isEnabled) for (int i = 0; i < armor.length; i++)
        armor[i] = new ItemArmorBase(armorMaterial, equipmentSlots[i]);
    }

    public static void registerArmor(boolean isEnabled, ItemUltimateArmor[] armor) {
        if (isEnabled) {
            for (int i = 0; i < armor.length; i++)
            armor[i] = new ItemUltimateArmor(equipmentSlots[i]);
            theUltimateParts = new ItemUltimateParts();
        }
    }

    public static void registerSword(boolean[] isEnabled, ItemSpecialSword[] sword, Swords[] material) {
        for (int i = 0; i < sword.length; i++)
        if (isEnabled[i])
            sword[i] = new ItemSpecialSword(material[i]);
    }

    public static void registerBattleAxe(boolean[] isEnabled, ItemSpecialBattleAxe[] battleAxe, BattleAxes[] material) {
        for (int i = 0; i < battleAxe.length; i++)
        if (isEnabled[i])
            battleAxe[i] = new ItemSpecialBattleAxe(material[i]);
    }

    public static void registerBow(boolean[] isEnabled, ItemSpecialBow[] bow, Bows[] material) {
        for (int i1 = 0; i1 < bow.length; i1++)
        if (isEnabled[i1])
            bow[i1] = new ItemSpecialBow(material[i1]);
    }
}