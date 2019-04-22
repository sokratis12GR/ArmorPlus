/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry;

import com.sofodev.armorplus.common.iface.IModelHelper;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Sokratis Fotkatzikis
 **/
@SideOnly(Side.CLIENT)
public class ModModelUtils {

    public static void register(IModelHelper modelHolder) {
        if (Utils.isNotNull(modelHolder)) {
            modelHolder.initModel();
        }
    }

    public static void register(IModelHelper... modelHolder) {
        for (IModelHelper model : modelHolder) {
            model.initModel();
        }
    }

    public static void register(IModelHelper[]... modelHolder) {
        for (IModelHelper[] model : modelHolder) {
            register(model);
        }
    }
}