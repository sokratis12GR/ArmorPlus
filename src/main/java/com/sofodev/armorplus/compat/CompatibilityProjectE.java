/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat;

import com.sofodev.armorplus.config.ModConfig;

import java.util.Map;

/**
 * @author Sokratis Fotkatzikis
 */
public class CompatibilityProjectE implements ICompatibility {

    @Override
    public void loadCompatibility(InitializationPhase phase) {
        //   if (phase == InitializationPhase.POST_INIT) {
        //       IEMCProxy emc = ProjectEAPI.getEMCProxy();
        //       ItemStack guardianScale = new ItemStack(guardianScale);
        //       ItemStack witherBone = new ItemStack(witherBone);
        //       ItemStack enderDragonScale = new ItemStack(enderDragonScale);
        //       ItemStack theUltimateMaterial = new ItemStack(ModItems.theUltimateMaterial);
        //       ItemStack lavaCrystal = new ItemStack(ModItems.itemLavaCrystal);
        //       ItemStack infusedLavaCrystal = new ItemStack(ModItems.itemInfusedLavaCrystal);
        //       emc.registerCustomEMC(new ItemStack(Items.SKULL, 1), 46421);
        //       emc.registerCustomEMC(guardianScale, 69632);
        //       emc.registerCustomEMC(witherBone, 69632);
        //       emc.registerCustomEMC(enderDragonScale, 131072);
        //       emc.registerCustomEMC(lavaCrystal, 10192);
        //       emc.registerCustomEMC(infusedLavaCrystal, 14778);
        //       emc.registerCustomEMC(theUltimateMaterial, 285624);
        //       emc.registerCustomEMC(ModBlocks.blockLavaInfusedObsidian, 1152);
        //       emc.registerCustomEMC(Items.DRAGON_BREATH, 87381);
        //       ProjectEEMCIntegration.registerEasyEMC();
        //       ProjectEEMCIntegration.registerExpertEMC();
        //   }
    }

    public static void addConversion(Object object, Map<Object, Integer> map) {
        //   IConversionProxy conversion = ProjectEAPI.getConversionProxy();
        //   conversion.addConversion(1, new ItemStack(object), map);
    }

    @Override
    public String getMODID() {
        return "projecte";
    }

    @Override
    public boolean enableCompat() {
        return ModConfig.IntegrationsConfig.enableProjectEIntegration;
    }
}