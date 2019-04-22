/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry;

import com.sofodev.armorplus.common.compat.*;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ModCompatibility {
    private static ArrayList<ICompatibility> compatibilities = new ArrayList<>();

    public static void registerModCompat() {
        addCompatibilities(
            new CompatibilityJustEnoughItems(),
            new CompatibilityBaubles(),
            new CompatibilityTinkersConstruct(),
            new CompatibilityMineTweaker(),
            new CompatibilityProjectE(),
            new CompatibilityDraconicEvolution()
        );
    }

    private static void addCompatibilities(ICompatibility... compatibilities) {
        Collections.addAll(ModCompatibility.compatibilities, compatibilities);
    }

    public static void loadCompat(ICompatibility.InitializationPhase phase) {
        compatibilities.stream().filter(
            compatibility -> Loader.isModLoaded(compatibility.getMODID()) && compatibility.enableCompat()
        ).forEachOrdered(
            compatibility -> compatibility.loadCompatibility(phase)
        );
    }
}