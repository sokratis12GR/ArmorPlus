/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.armors.base.ItemArmorBase;
import net.thedragonteam.armorplus.armors.base.ItemUltimateArmor;
import net.thedragonteam.armorplus.items.base.ItemSpecialBattleAxe;
import net.thedragonteam.armorplus.items.base.ItemSpecialBow;
import net.thedragonteam.armorplus.items.base.ItemSpecialSword;
import net.thedragonteam.armorplus.util.Utils;

import java.util.Arrays;
import java.util.stream.IntStream;

@SideOnly(Side.CLIENT)
public class ModModelUtils {

    private static void registerArmorModel(boolean isEnabled, ItemArmorBase... armor) {
        if (isEnabled) {
            Arrays.stream(armor).filter(Utils.INSTANCE::isNotNull).forEachOrdered(ItemArmorBase::initModel);
        }
    }

    public static void registerArmorModel(boolean[] isEnabled, ItemArmorBase[]... armor) {
        IntStream.range(0, isEnabled.length).forEachOrdered(i -> registerArmorModel(isEnabled[i], armor[i]));
    }

    public static void registerArmorModel(boolean isEnabled, ItemUltimateArmor[] armor) {
        if (isEnabled) {
            Arrays.stream(armor).filter(Utils.INSTANCE::isNotNull).forEachOrdered(ItemUltimateArmor::initModel);
        }
    }

    public static void registerSwordModel(boolean isEnabled[], ItemSpecialSword[] sword) {
        IntStream.range(0, isEnabled.length).forEachOrdered(i -> registerSwordModel(isEnabled[i], sword[i]));
    }

    private static void registerSwordModel(boolean isEnabled, ItemSpecialSword sword) {
        if (isEnabled && Utils.INSTANCE.isNotNull(sword)) sword.initModel();
    }

    public static void registerBattleAxeModel(boolean isEnabled[], ItemSpecialBattleAxe[] battleAxe) {
        IntStream.range(0, isEnabled.length).forEachOrdered(i -> registerBattleAxeModel(isEnabled[i], battleAxe[i]));
    }

    private static void registerBattleAxeModel(boolean isEnabled, ItemSpecialBattleAxe battleAxe) {
        if (isEnabled && Utils.INSTANCE.isNotNull(battleAxe)) battleAxe.initModel();
    }

    public static void registerBowModel(boolean isEnabled[], ItemSpecialBow[] bow) {
        IntStream.range(0, isEnabled.length).forEachOrdered(i -> registerBowModel(isEnabled[i], bow[i]));
    }

    private static void registerBowModel(boolean isEnabled, ItemSpecialBow bow) {
        if (isEnabled && Utils.INSTANCE.isNotNull(bow)) bow.initModel();
    }
}