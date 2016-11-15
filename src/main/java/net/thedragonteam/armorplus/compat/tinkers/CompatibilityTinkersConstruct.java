/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.compat.tinkers;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.thedragonteam.armorplus.compat.ICompatibility;

import javax.annotation.Nonnull;

/**
 * net.thedragonteam.armorplus.compat.jei
 * ArmorPlus created by sokratis12GR on 6/21/2016 10:54 PM.
 * - TheDragonTeam
 */
public class CompatibilityTinkersConstruct implements ICompatibility {
    @Override
    public void loadCompatibility(InitializationPhase phase) {
        if (phase == InitializationPhase.PRE_INIT) {
            TiC.preInit();
            if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
                TiCModifiers.initRender();
        }
        if (phase == InitializationPhase.INIT) {
            TiC.init();
        }
        if (phase == InitializationPhase.POST_INIT) {
            if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
                TiCMaterials.registerMaterialRendering();
            TiC.postInit();
        }
    }

    @Override
    @Nonnull
    public String getMODID() {
        return "tconstruct";
    }

    @Override
    public boolean enableCompat() {
        return true;
    }
}