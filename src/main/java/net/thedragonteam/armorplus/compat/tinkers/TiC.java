/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.tinkers;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.thedragonteam.armorplus.compat.tinkers.modifiers.TiCModifiers;
import net.thedragonteam.armorplus.compat.tinkers.modifiers.TiCTraits;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.tools.TinkerMaterials;

import static slimeknights.tconstruct.library.TinkerRegistry.getMaterial;

public class TiC {

    public static TiCModifiers tinkersModifiers;
    public static TiCMaterials tinkersMaterials;
    public static MaterialIntegration lavacrystalInt = new MaterialIntegration(TiCMaterials.INFUSED_LAVA_CRYSTAL);
    public static MaterialIntegration steelInt = new MaterialIntegration(TinkerMaterials.steel, TinkerFluids.steel);
    public static MaterialIntegration obsidianInt = new MaterialIntegration(TiCMaterials.COMPRESSED_OBSIDIAN);
    public static MaterialIntegration infusedObsidianInt = new MaterialIntegration(TiCMaterials.LAVA_INFUSED_OBSIDIAN);
    public static TiCTraits tinkersTraits;

    public static void preInit(FMLPreInitializationEvent event) {
        tinkersModifiers = new TiCModifiers();
        tinkersTraits = new TiCTraits();
        tinkersMaterials = new TiCMaterials();
        intMaterials(lavacrystalInt);
        intMaterials(infusedObsidianInt);
        intMaterials(obsidianInt);
        if (getMaterial("steel") == null) intMaterials(steelInt);
    }

    public static void init(FMLInitializationEvent event) {
        tinkersMaterials.setupMaterials(event);
    }

    public static void postInit(FMLPostInitializationEvent event) {
        tinkersMaterials.postInit(event);
        initRepresentativeItem(lavacrystalInt, "blockInfusedLavaCrystal");
        initRepresentativeItem(obsidianInt, "blockCompressedObsidian");
        initRepresentativeItem(infusedObsidianInt, "blockLavaInfusedObsidian");
    }

    public static void intMaterials(MaterialIntegration material) {
        material.toolforge();
        material.integrate();
    }

    public static void initRepresentativeItem(MaterialIntegration material, String oreDictItem) {
        material.setRepresentativeItem(oreDictItem);
    }
}