/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.common.Loader;
import net.thedragonteam.armorplus.compat.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ModCompatibility {
    private static ArrayList<ICompatibility> compatibilities = new ArrayList<>();

    public static void registerModCompat() {
        addCompatibilities(
            new CompatibilityJustEnoughItems(),
            new CompatibilityBaubles(),
            new CompatibilityTinkersConstruct(),
            new CompatibilityMineTweaker(),
            new CompatibilityProjectE()
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