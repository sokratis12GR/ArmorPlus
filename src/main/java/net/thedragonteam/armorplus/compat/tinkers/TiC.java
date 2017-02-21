/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.tinkers;

import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.tools.TinkerMaterials;

public class TiC {

    public static TiCModifiers tinkersModifiers;
    public static TiCMaterials tinkersMaterials;
    public static MaterialIntegration lavacrystalInt;
    public static MaterialIntegration steelInt;
    public static MaterialIntegration obsidianInt;
    public static TiCTraits tinkersTraits;

    public static void preInit() {
        tinkersTraits = new TiCTraits();
        tinkersMaterials = new TiCMaterials();
        lavacrystalInt = new MaterialIntegration(TiCMaterials.lavacrystal);
        lavacrystalInt.registerRepresentativeItem();
        lavacrystalInt.integrate();
        lavacrystalInt.toolforge();
        if (TinkerRegistry.getMaterial("steel") == null) {
            steelInt = new MaterialIntegration(TinkerMaterials.steel, TinkerFluids.steel);
            steelInt.integrate();
        }
        obsidianInt = new MaterialIntegration(TiCMaterials.compressed_obsidian);
        obsidianInt.registerRepresentativeItem();
        obsidianInt.integrate();
        tinkersModifiers = new TiCModifiers();
        tinkersMaterials.setupMaterials();
    }

    public static void init() {
    }

    public static void postInit() {
    }
}