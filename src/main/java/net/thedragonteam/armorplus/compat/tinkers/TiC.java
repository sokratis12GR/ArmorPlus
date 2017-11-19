/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.tinkers;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
//public class TiC extends AbstractToolPulse {
//
//    private static final TiC INSTANCE = new TiC();
//
//    public static TiC instance() {
//        return INSTANCE;
//    }
//
//    public static TiCMaterials tinkersMaterials = new TiCMaterials();
//    public static TiCModifiers tiCModifiers = new TiCModifiers();
//    public static MaterialIntegration lavacrystalInt = new MaterialIntegration(TiCMaterials.INFUSED_LAVA_CRYSTAL);
//    public static MaterialIntegration steelInt = new MaterialIntegration(TinkerMaterials.steel, TinkerFluids.steel);
//    public static MaterialIntegration obsidianInt = new MaterialIntegration(TiCMaterials.COMPRESSED_OBSIDIAN);
//    public static MaterialIntegration infusedObsidianInt = new MaterialIntegration(TiCMaterials.LAVA_INFUSED_OBSIDIAN);
//
//    public void preInit(FMLPreInitializationEvent event) {
//        this.intMaterials(lavacrystalInt);
//        this.intMaterials(infusedObsidianInt);
//        this.intMaterials(obsidianInt);
//        if (getMaterial("steel") == null) this.intMaterials(steelInt);
//        tinkersMaterials.setupMaterialStats(event);
//    }
//
//    public void init(FMLInitializationEvent event) {
//        tiCModifiers.init(event);
//        tinkersMaterials.setupMaterials(event);
//    }
//
//    public void postInit(FMLPostInitializationEvent event) {
//        tinkersMaterials.postInit(event);
//        initRepresentativeItem(lavacrystalInt, "blockInfusedLavaCrystal");
//        initRepresentativeItem(obsidianInt, "blockCompressedObsidian");
//        initRepresentativeItem(infusedObsidianInt, "blockLavaInfusedObsidian");
//    }
//
//    public void intMaterials(MaterialIntegration material) {
//        TinkerRegistry.addMaterial(material.material);
//        TinkerRegistry.integrate(material);
//    }
//
//    public void intMaterials(Material material) {
//    }
//
//    public static void initRepresentativeItem(MaterialIntegration material, String oreDictItem) {
//        material.setRepresentativeItem(oreDictItem);
//    }
//}