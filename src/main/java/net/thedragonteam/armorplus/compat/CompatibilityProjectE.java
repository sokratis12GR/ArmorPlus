/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat;

import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.proxy.IConversionProxy;
import moze_intel.projecte.api.proxy.IEMCProxy;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.Map;

import static net.thedragonteam.armorplus.ModConfig.IntegrationsConfig.enableProjectEIntegration;
import static net.thedragonteam.armorplus.compat.projecte.ProjectEEMCIntegration.registerEasyEMC;
import static net.thedragonteam.armorplus.compat.projecte.ProjectEEMCIntegration.registerExpertEMC;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CompatibilityProjectE implements ICompatibility {

    @Override
    public void loadCompatibility(InitializationPhase phase) {
        if (phase == InitializationPhase.POST_INIT) {
            IEMCProxy emc = ProjectEAPI.getEMCProxy();
            ItemStack guardianScale = getItemStack(materials, 1);
            ItemStack witherBone = getItemStack(materials, 2);
            ItemStack enderDragonScale = getItemStack(materials, 3);
            ItemStack theUltimateMaterial = getItemStack(materials, 4);
            ItemStack lavaCrystal = getItemStack(ModItems.lavaCrystal);
            ItemStack infusedLavaCrystal = getItemStack(ModItems.lavaCrystal, 1);
            emc.registerCustomEMC(getItemStack(Items.SKULL, 1), 46421);
            emc.registerCustomEMC(guardianScale, 69632);
            emc.registerCustomEMC(witherBone, 69632);
            emc.registerCustomEMC(enderDragonScale, 131072);
            emc.registerCustomEMC(lavaCrystal, 10192);
            emc.registerCustomEMC(infusedLavaCrystal, 14778);
            emc.registerCustomEMC(theUltimateMaterial, 285624);
            emc.registerCustomEMC(ModBlocks.blockLavaInfusedObsidian, 1152);
            emc.registerCustomEMC(Items.DRAGON_BREATH, 87381);
            registerEasyEMC();
            registerExpertEMC();
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
        return enableProjectEIntegration;
    }
}