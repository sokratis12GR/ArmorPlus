/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.compat;

import com.sofodev.armorplus.common.compat.projecte.ProjectEEMCIntegration;
import com.sofodev.armorplus.common.config.ModConfig;
import com.sofodev.armorplus.common.registry.ModBlocks;
import com.sofodev.armorplus.common.registry.ModItems;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.proxy.IConversionProxy;
import moze_intel.projecte.api.proxy.IEMCProxy;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Map;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class CompatibilityProjectE implements ICompatibility {

    @Override
    public void loadCompatibility(InitializationPhase phase) {
        if (phase == InitializationPhase.POST_INIT) {
            IEMCProxy emc = ProjectEAPI.getEMCProxy();
            ItemStack guardianScale = getItemStack(ModItems.materials, 1);
            ItemStack witherBone = getItemStack(ModItems.materials, 2);
            ItemStack enderDragonScale = getItemStack(ModItems.materials, 3);
            ItemStack theUltimateMaterial = getItemStack(ModItems.materials, 4);
            ItemStack lavaCrystal = getItemStack(ModItems.itemLavaCrystal);
            ItemStack infusedLavaCrystal = getItemStack(ModItems.itemLavaCrystal, 1);
            emc.registerCustomEMC(getItemStack(Items.SKULL, 1), 46421);
            emc.registerCustomEMC(guardianScale, 69632);
            emc.registerCustomEMC(witherBone, 69632);
            emc.registerCustomEMC(enderDragonScale, 131072);
            emc.registerCustomEMC(lavaCrystal, 10192);
            emc.registerCustomEMC(infusedLavaCrystal, 14778);
            emc.registerCustomEMC(theUltimateMaterial, 285624);
            emc.registerCustomEMC(ModBlocks.blockLavaInfusedObsidian, 1152);
            emc.registerCustomEMC(Items.DRAGON_BREATH, 87381);
            ProjectEEMCIntegration.registerEasyEMC();
            ProjectEEMCIntegration.registerExpertEMC();
        }
    }

    public static void addConversion(Object object, Map<Object, Integer> map) {
        IConversionProxy conversion = ProjectEAPI.getConversionProxy();
        conversion.addConversion(1, getItemStack(object), map);
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