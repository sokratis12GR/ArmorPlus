/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.util.Utils;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
@SideOnly(Side.CLIENT)
public class ModModelUtils {

    public static void registerModels(IModelHelper... objects) {
        Arrays.stream(objects).forEachOrdered(IModelHelper::initModel);
    }

    public static void registerModels(IModelHelper[]... modelHelpers) {
        Arrays.stream(modelHelpers).forEachOrdered(ModModelUtils::registerModels);
    }

    public static void registerModels(boolean isEnabled, IModelHelper modelHolder) {
        if (isEnabled && Utils.isNotNull(modelHolder)) registerModels(modelHolder);
    }

    public static void registerModels(boolean isEnabled, IModelHelper... modelHolders) {
        Arrays.stream(modelHolders).forEachOrdered(modelHolder -> registerModels(isEnabled, modelHolder));
    }

    public static void registerModels(boolean isEnabled[], IModelHelper[] modelHolder) {
        IntStream.range(0, isEnabled.length).forEachOrdered(i -> registerModels(isEnabled[i], modelHolder[i]));
    }

    public static void registerModels(boolean[] isEnabled, IModelHelper[]... armor) {
        IntStream.range(0, isEnabled.length).forEachOrdered(i -> registerModels(isEnabled[i], armor[i]));
    }

}