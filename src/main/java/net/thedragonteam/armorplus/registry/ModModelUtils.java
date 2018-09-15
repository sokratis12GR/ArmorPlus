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

    public static void register(IModelHelper... objects) {
        Arrays.stream(objects).forEachOrdered(IModelHelper::initModel);
    }

    public static void register(IModelHelper[]... modelHelpers) {
        Arrays.stream(modelHelpers).forEachOrdered(ModModelUtils::register);
    }

    public static void register(boolean isEnabled, IModelHelper modelHolder) {
        if (isEnabled && Utils.isNotNull(modelHolder)) register(modelHolder);
    }

    public static void register(boolean isEnabled, IModelHelper... modelHolders) {
        Arrays.stream(modelHolders).forEachOrdered(modelHolder -> register(isEnabled, modelHolder));
    }

    public static void register(boolean isEnabled[], IModelHelper[] modelHolder) {
        IntStream.range(0, isEnabled.length).forEachOrdered(i -> register(isEnabled[i], modelHolder[i]));
    }

    public static void register(boolean[] isEnabled, IModelHelper[]... armor) {
        IntStream.range(0, isEnabled.length).forEachOrdered(i -> register(isEnabled[i], armor[i]));
    }

}