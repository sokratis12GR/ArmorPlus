/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.integration;

import net.minecraftforge.fml.common.Loader;
import net.thedragonteam.armorplus.integration.tinkers.TiCMaterials;
import net.thedragonteam.armorplus.integration.tinkers.TiCModifiers;
import net.thedragonteam.armorplus.integration.tinkers.TiCTraits;
import slimeknights.tconstruct.library.MaterialIntegration;

public class TiC {

    public static TiCModifiers tinkersModifiers;
    public static TiCMaterials tinkersMaterials;
    public static MaterialIntegration tinkersMatInt;
    public static TiCTraits tinkersTraits;

    public static void preInit() {
        tinkersTraits = new TiCTraits();
        tinkersMaterials = new TiCMaterials();
        tinkersMatInt = new MaterialIntegration(TiCMaterials.lavacrystal);
        tinkersMatInt.integrate();
        tinkersMatInt.toolforge();
        tinkersModifiers = new TiCModifiers();
        tinkersMaterials.setupMaterials();
        tinkersMaterials.postInit();
    }

    public static void init() {
    }

    public static void postInit() {
        if (Loader.isModLoaded("tconstruct")) {
            TiCMaterials.registerMaterialRendering();
        }
    }

}