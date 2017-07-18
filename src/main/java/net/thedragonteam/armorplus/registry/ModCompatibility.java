/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.common.Loader;
import net.thedragonteam.armorplus.compat.*;

import java.util.ArrayList;

/**
 * net.thedragonteam.armorplus.registry
 * ArmorPlus created by sokratis12GR on 6/22/2016 12:20 AM.
 * - TheDragonTeam
 */
public class ModCompatibility {
    private static ArrayList<ICompatibility> compatibilities = new ArrayList<>();

    public static void registerModCompat() {
        compatibilities.add(new CompatibilityJustEnoughItems());
        compatibilities.add(new CompatibilityBaubles());
        compatibilities.add(new CompatibilityTinkersConstruct());
        compatibilities.add(new CompatibilityMineTweaker());
    }

    public static void loadCompat(ICompatibility.InitializationPhase phase) {
        compatibilities.stream().filter(
                compatibility -> Loader.isModLoaded(compatibility.getModid()) && compatibility.enableCompat()
        ).forEachOrdered(compatibility -> compatibility.loadCompatibility(phase));
    }
}