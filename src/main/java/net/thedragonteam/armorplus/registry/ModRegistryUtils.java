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
import static net.thedragonteam.armorplus.registry.ModItems.equipmentSlots;
import static net.thedragonteam.armorplus.registry.ModItems.theUltimateParts;

public class ModRegistryUtils {

    @SideOnly(Side.CLIENT)
    public static void registerArmorModel(boolean isEnabled, ItemArmorBase[] armor) {
        if (isEnabled) {
            for (ItemArmorBase anArmor : armor) {
                anArmor.initModel();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerArmorModel(boolean isEnabled, ItemUltimateArmor[] armor) {
        if (isEnabled) {
            for (ItemUltimateArmor anArmor : armor) {
                anArmor.initModel();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerSwordModel(boolean isEnabled[], ItemSpecialSword[] sword) {
        for (int i = 0; i < isEnabled.length; i++) {
            if (isEnabled[i]) {
                sword[i].initModel();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerBattleAxeModel(boolean isEnabled[], ItemSpecialBattleAxe[] battleAxe) {
        for (int i = 0; i < isEnabled.length; i++) {
            if (isEnabled[i]) {
                battleAxe[i].initModel();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerBowModel(boolean isEnabled[], ItemSpecialBow[] bow) {
        for (int i = 0; i < isEnabled.length; i++) {
            if (isEnabled[i]) {
                bow[i].initModel();
            }
        }
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
        for (int i = 0; i < sword.length; i++) {
            if (isEnabled[i]) {
                sword[i] = new ItemSpecialSword(material[i]);
            }
        }
    }

    public static void registerBattleAxe(boolean[] isEnabled, ItemSpecialBattleAxe[] battleAxe, BattleAxes[] material) {
        for (int i = 0; i < battleAxe.length; i++) {
            if (isEnabled[i]) {
                battleAxe[i] = new ItemSpecialBattleAxe(material[i]);
            }
        }
    }

    public static void registerBow(boolean[] isEnabled, ItemSpecialBow[] bow, Bows[] material) {
        for (int i1 = 0; i1 < bow.length; i1++) {
            if (isEnabled[i1]) {
                bow[i1] = new ItemSpecialBow(material[i1]);
            }
        }
    }
}