/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

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
    public static TiCTraits tinkerTraits;

    public static void preInit() {
        tinkerTraits = new TiCTraits();
        tinkersMaterials = new TiCMaterials();
        tinkersMatInt = new MaterialIntegration(TiCMaterials.lavacrystal).toolforge();
        tinkersMatInt.integrate();
        tinkersModifiers = new TiCModifiers();
        tinkersMaterials.setupMaterials();
    }

    public static void init() {
    }

    public static void postInit() {
        if (Loader.isModLoaded("tconstruct")) {
            TiCMaterials.registerMaterialRendering();
        }
    }

}