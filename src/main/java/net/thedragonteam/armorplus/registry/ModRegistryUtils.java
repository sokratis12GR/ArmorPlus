/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.armors.APArmorMaterial;
import net.thedragonteam.armorplus.armors.base.ItemArmorBase;
import net.thedragonteam.armorplus.armors.base.ItemUltimateArmor;
import net.thedragonteam.armorplus.items.base.ItemSpecialBattleAxe;
import net.thedragonteam.armorplus.items.base.ItemSpecialBow;
import net.thedragonteam.armorplus.items.base.ItemSpecialSword;
import net.thedragonteam.armorplus.items.enums.BattleAxes;
import net.thedragonteam.armorplus.items.enums.Bows;
import net.thedragonteam.armorplus.items.enums.Swords;

import static java.util.Arrays.setAll;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;
import static net.thedragonteam.armorplus.registry.ModItems.equipmentSlots;

public class ModRegistryUtils {

    @SideOnly(Side.CLIENT)
    public static void registerArmorModel(boolean isEnabled, ItemArmorBase[] armor) {
        if (isEnabled) {
            stream(armor).forEachOrdered(ItemArmorBase::initModel);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerArmorModel(boolean isEnabled, ItemUltimateArmor[] armor) {
        if (isEnabled) {
            stream(armor).forEachOrdered(ItemUltimateArmor::initModel);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerSwordModel(boolean isEnabled[], ItemSpecialSword[] sword) {
        range(0, isEnabled.length).filter(i -> isEnabled[i]).forEachOrdered(i -> sword[i].initModel());
    }

    @SideOnly(Side.CLIENT)
    public static void registerBattleAxeModel(boolean isEnabled[], ItemSpecialBattleAxe[] battleAxe) {
        range(0, isEnabled.length).filter(i -> isEnabled[i]).forEachOrdered(i -> battleAxe[i].initModel());
    }

    @SideOnly(Side.CLIENT)
    public static void registerBowModel(boolean isEnabled[], ItemSpecialBow[] bow) {
        range(0, isEnabled.length).filter(i -> isEnabled[i]).forEachOrdered(i -> bow[i].initModel());
    }

    public static void registerArmor(boolean isEnabled, ItemArmorBase[] armor, APArmorMaterial armorMaterial) {
        if (isEnabled) {
            setAll(armor, i -> new ItemArmorBase(armorMaterial, equipmentSlots[i]));
        }
    }

    public static void registerArmor(boolean isEnabled, ItemUltimateArmor[] armor) {
        if (isEnabled) {
            setAll(armor, i -> new ItemUltimateArmor(equipmentSlots[i]));
        }
    }

    public static void registerSword(boolean[] isEnabled, ItemSpecialSword[] sword, Swords[] material) {
        range(0, sword.length).filter(i -> isEnabled[i]).forEachOrdered(i -> sword[i] = new ItemSpecialSword(material[i]));
    }

    public static void registerBattleAxe(boolean[] isEnabled, ItemSpecialBattleAxe[] battleAxe, BattleAxes[] material) {
        range(0, battleAxe.length).filter(i -> isEnabled[i]).forEachOrdered(i -> battleAxe[i] = new ItemSpecialBattleAxe(material[i]));
    }

    public static void registerBow(boolean[] isEnabled, ItemSpecialBow[] bow, Bows[] material) {
        range(0, bow.length).filter(i1 -> isEnabled[i1]).forEachOrdered(i1 -> bow[i1] = new ItemSpecialBow(material[i1]));
    }
}