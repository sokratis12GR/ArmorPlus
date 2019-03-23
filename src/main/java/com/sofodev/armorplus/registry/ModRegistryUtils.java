/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.caps.abilities.MaterialType;
import com.sofodev.armorplus.items.armors.APArmorMaterial;
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


    public static void registerAll(ItemUltimateArmor[] ultArmor) {
        for (int i = 0; i < ultArmor.length; i++) {
            ultArmor[i] = new ItemUltimateArmor(equipmentSlots[i]);
        }
    }

    public static void registerAll(ItemSpecialSword[] swords) {
        for (int i = 0; i < swords.length; i++) {
            swords[i] = new ItemSpecialSword(Swords.values()[i]);
        }
    }

    public static void registerAll(ItemSpecialBattleAxe[] battleAxes) {
        for (int i = 0; i < battleAxes.length; i++) {
            battleAxes[i] = new ItemSpecialBattleAxe(BattleAxes.values()[i]);
        }
    }

    public static void registerAll(ItemSpecialBow[] bows) {
        for (int i = 0; i < bows.length; i++) {
            bows[i] = new ItemSpecialBow(Bows.values()[i]);
        }
    }

    public static void register(ItemArmorV2[] armor, MaterialType material) {
        IntStream.range(0, armor.length).forEachOrdered(i -> armor[i] = new ItemArmorV2(material, equipmentSlots[i]));
    }

    public static void register(ItemArmorBase[] armor, APArmorMaterial material) {
        int bound = armor.length;
        for (int i = 0; i < bound; i++) {
            armor[i] = new ItemArmorBase(material, equipmentSlots[i]);
        }

    }
}