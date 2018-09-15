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
import net.thedragonteam.armorplus.items.weapons.BattleAxes;
import net.thedragonteam.armorplus.items.weapons.Bows;
import net.thedragonteam.armorplus.items.weapons.Swords;

import java.util.stream.IntStream;

import static net.thedragonteam.armorplus.util.Utils.equipmentSlots;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ModRegistryUtils {

    public static void register(boolean enabled, ItemArmorBase[] armor, APArmorMaterial armorMaterial) {
        if (enabled) {
            IntStream.range(0, armor.length).forEachOrdered(i -> armor[i] = new ItemArmorBase(armorMaterial, equipmentSlots[i]));
        }
    }

    public static void register(boolean enabled, ItemUltimateArmor[] armor) {
        if (enabled) {
            IntStream.range(0, armor.length).forEachOrdered(i -> armor[i] = new ItemUltimateArmor(equipmentSlots[i]));
        }
    }

    public static void register(boolean[] enabled, ItemSpecialSword[] sword, Swords[] material) {
        IntStream.range(0, enabled.length).filter(i -> enabled[i]).forEachOrdered(i -> sword[i] = new ItemSpecialSword(material[i]));
    }

    public static void register(boolean[] enabled, ItemSpecialBattleAxe[] battleAxe, BattleAxes[] material) {
        IntStream.range(0, enabled.length).filter(i -> enabled[i]).forEachOrdered(i -> battleAxe[i] = new ItemSpecialBattleAxe(material[i]));
    }

    public static void register(boolean[] enabled, ItemSpecialBow[] bow, Bows[] material) {
        IntStream.range(0, enabled.length).filter(i -> enabled[i]).forEachOrdered(i -> bow[i] = new ItemSpecialBow(material[i]));
    }
}