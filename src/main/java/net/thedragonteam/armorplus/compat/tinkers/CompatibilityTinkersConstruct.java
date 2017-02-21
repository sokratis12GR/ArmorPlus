/*
 * Copyright (c) TheDragonTeam 2016-2017.
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
        switch (phase) {
            case PRE_INIT:
                TiC.preInit();
                if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
                    TiCModifiers.initRender();
                break;
            case INIT:
                TiC.init();
                break;
            case POST_INIT:
                if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
                    TiCMaterials.registerMaterialRendering();
                TiC.postInit();
                break;
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