/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.integration;

import net.minecraftforge.fml.common.Loader;
import net.thedragonteam.armorplus.integration.tinkers.TiCMaterials;
import net.thedragonteam.armorplus.integration.tinkers.TiCModifiers;
import net.thedragonteam.armorplus.integration.tinkers.TiCTraits;
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
		lavacrystalInt.registerRepresentativeItem();
		obsidianInt.integrate();
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