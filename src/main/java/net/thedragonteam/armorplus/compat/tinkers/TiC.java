/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.tinkers;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.thedragonteam.armorplus.compat.tinkers.modifiers.TiCModifiers;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.tools.AbstractToolPulse;
import slimeknights.tconstruct.tools.TinkerMaterials;

import static slimeknights.tconstruct.library.TinkerRegistry.getMaterial;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class TiC extends AbstractToolPulse {

    private static final TiC INSTANCE = new TiC();
    public static TiCMaterials tinkersMaterials = new TiCMaterials();
    public static TiCModifiers tiCModifiers = new TiCModifiers();
    public static MaterialIntegration lavacrystalInt = new MaterialIntegration(TiCMaterials.INFUSED_LAVA_CRYSTAL);
    public static MaterialIntegration steelInt = new MaterialIntegration(TinkerMaterials.steel, TinkerFluids.steel);
    public static MaterialIntegration obsidianInt = new MaterialIntegration(TiCMaterials.COMPRESSED_OBSIDIAN);
    public static MaterialIntegration infusedObsidianInt = new MaterialIntegration(TiCMaterials.LAVA_INFUSED_OBSIDIAN);

    public static TiC instance() {
        return INSTANCE;
    }

    public static void initRepresentativeItem(MaterialIntegration material, String oreDictItem) {
        material.setRepresentativeItem(oreDictItem);
    }

    public void preInit(FMLPreInitializationEvent event) {
        this.initMaterials(lavacrystalInt);
        this.initMaterials(infusedObsidianInt);
        this.initMaterials(obsidianInt);
        if (getMaterial("steel") == null) this.initMaterials(steelInt);
        tinkersMaterials.setupMaterialStats(event);
    }

    public void init(FMLInitializationEvent event) {
        tiCModifiers.init(event);
        tinkersMaterials.setupMaterials(event);
    }

    public void postInit(FMLPostInitializationEvent event) {
        tinkersMaterials.postInit(event);
        initRepresentativeItem(lavacrystalInt, "blockInfusedLavaCrystal");
        initRepresentativeItem(obsidianInt, "blockCompressedObsidian");
        initRepresentativeItem(infusedObsidianInt, "blockLavaInfusedObsidian");
    }

    public void initMaterials(MaterialIntegration material) {
        TinkerRegistry.addMaterial(material.material);
        TinkerRegistry.integrate(material);
    }
}