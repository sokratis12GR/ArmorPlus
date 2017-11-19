/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.thedragonteam.armorplus.armors.APArmorMaterial;
import net.thedragonteam.armorplus.armors.base.ItemArmorBase;
import net.thedragonteam.armorplus.armors.base.ItemUltimateArmor;
import net.thedragonteam.armorplus.items.base.ItemSpecialBattleAxe;
import net.thedragonteam.armorplus.items.base.ItemSpecialBow;
import net.thedragonteam.armorplus.items.base.ItemSpecialSword;
import net.thedragonteam.armorplus.items.enums.BattleAxes;
import net.thedragonteam.armorplus.items.enums.Bows;
import net.thedragonteam.armorplus.items.enums.Swords;

import java.util.stream.IntStream;

import static net.thedragonteam.armorplus.util.Utils.equipmentSlots;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ModRegistryUtils {

    public static void registerArmor(boolean isEnabled, ItemArmorBase[] armor, APArmorMaterial armorMaterial) {
        if (isEnabled) {
            IntStream.range(0, armor.length).forEachOrdered(i -> armor[i] = new ItemArmorBase(armorMaterial, equipmentSlots[i]));
        }
    }

    public static void registerArmor(boolean isEnabled, ItemUltimateArmor[] armor) {
        if (isEnabled) {
            IntStream.range(0, armor.length).forEachOrdered(i -> armor[i] = new ItemUltimateArmor(equipmentSlots[i]));
        }
    }

    public static void registerSword(boolean[] isEnabled, ItemSpecialSword[] sword, Swords[] material) {
        IntStream.range(0, isEnabled.length).filter(i -> isEnabled[i]).forEachOrdered(i -> sword[i] = new ItemSpecialSword(material[i]));
    }

    public static void registerBattleAxe(boolean[] isEnabled, ItemSpecialBattleAxe[] battleAxe, BattleAxes[] material) {
        IntStream.range(0, isEnabled.length).filter(i -> isEnabled[i]).forEachOrdered(i -> battleAxe[i] = new ItemSpecialBattleAxe(material[i]));
    }

    public static void registerBow(boolean[] isEnabled, ItemSpecialBow[] bow, Bows[] material) {
        IntStream.range(0, isEnabled.length).filter(i -> isEnabled[i]).forEachOrdered(i -> bow[i] = new ItemSpecialBow(material[i]));
    }
}