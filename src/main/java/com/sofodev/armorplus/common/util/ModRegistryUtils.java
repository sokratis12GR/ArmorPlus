/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.util;

import com.sofodev.armorplus.api.caps.abilities.MaterialType;
import com.sofodev.armorplus.common.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.common.registry.items.armors.base.ItemArmorV2;
import com.sofodev.armorplus.common.registry.items.armors.base.ItemEnhancedArmor;
import com.sofodev.armorplus.common.registry.items.armors.base.ItemSpecialArmor;
import com.sofodev.armorplus.common.registry.items.armors.base.ItemUltimateArmor;
import com.sofodev.armorplus.common.registry.items.base.ItemSpecialBattleAxe;
import com.sofodev.armorplus.common.registry.items.base.ItemSpecialBow;
import com.sofodev.armorplus.common.registry.items.base.ItemSpecialSword;
import com.sofodev.armorplus.common.registry.items.base.special.BattleAxes;
import com.sofodev.armorplus.common.registry.items.base.special.Bows;
import com.sofodev.armorplus.common.registry.items.base.special.Swords;
import net.minecraft.item.ItemArmor;

import java.util.stream.IntStream;

import static com.sofodev.armorplus.common.util.Utils.equipmentSlots;

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

    public static void register(ItemEnhancedArmor[] armor, ItemArmor.ArmorMaterial material, String name) {
        IntStream.range(0, armor.length).forEachOrdered(i -> armor[i] = new ItemEnhancedArmor(material, equipmentSlots[i], name));
    }

    public static void register(ItemSpecialArmor[] armor, APArmorMaterial material) {
        int bound = armor.length;
        for (int i = 0; i < bound; i++) {
            armor[i] = new ItemSpecialArmor(material, equipmentSlots[i]);
        }
    }
}