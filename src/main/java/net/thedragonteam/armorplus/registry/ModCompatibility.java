/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.common.Loader;
import net.thedragonteam.armorplus.compat.ICompatibility;
import net.thedragonteam.armorplus.compat.jei.CompatibilityJustEnoughItems;

import java.util.ArrayList;

/**
 * net.thedragonteam.armorplus.registry
 * ArmorPlus created by sokratis12GR on 6/22/2016 12:20 AM.
 * - TheDragonTeam
 */
public class ModCompatibility {
    private static ArrayList<ICompatibility> compatibilities = new ArrayList<ICompatibility>();

    public static void registerModCompat() {
        compatibilities.add(new CompatibilityJustEnoughItems());
    }

    public static void loadCompat(ICompatibility.InitializationPhase phase) {
        for (ICompatibility compatibility : compatibilities)
            if (Loader.isModLoaded(compatibility.getMODID()) && compatibility.enableCompat())
                compatibility.loadCompatibility(phase);
    }
}