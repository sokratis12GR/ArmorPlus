/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.iface.IModelHelper;
import com.sofodev.armorplus.util.Utils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Sokratis Fotkatzikis
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