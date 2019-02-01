/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.items.armors.APArmorMaterial;
import com.sofodev.armorplus.items.armors.Material;
import com.sofodev.armorplus.items.armors.base.ItemArmorBase;
import com.sofodev.armorplus.items.armors.base.ItemArmorV2;
import com.sofodev.armorplus.items.armors.base.ItemUltimateArmor;
import com.sofodev.armorplus.items.base.ItemSpecialBattleAxe;
import com.sofodev.armorplus.items.base.ItemSpecialBow;
import com.sofodev.armorplus.items.base.ItemSpecialSword;
import com.sofodev.armorplus.items.weapons.BattleAxes;
import com.sofodev.armorplus.items.weapons.Bows;
import com.sofodev.armorplus.items.weapons.Swords;

import java.util.stream.IntStream;

import static com.sofodev.armorplus.util.Utils.equipmentSlots;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ModRegistryUtils {

    public static void register(boolean enabled, ItemArmorBase[] armor, APArmorMaterial armorMaterial) {
        if (enabled) {
            IntStream.range(0, armor.length).forEachOrdered(i -> armor[i] = new ItemArmorBase(armorMaterial, equipmentSlots[i]));
        }
    }

    public static void register(ItemArmorV2[] armor, Material material) {
        IntStream.range(0, armor.length).forEachOrdered(i -> armor[i] = new ItemArmorV2(material, equipmentSlots[i]));
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