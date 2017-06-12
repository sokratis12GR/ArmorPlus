/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat;

/**
 * net.thedragonteam.armorplus.compat.jei
 * ArmorPlus created by sokratis12GR on 6/21/2016 10:54 PM.
 * - TheDragonTeam
 */
public class CompatibilityTinkersConstruct implements ICompatibility {
    @Override
    public void loadCompatibility(ICompatibility.InitializationPhase phase) {
  //      switch (phase) {
  //          case PRE_INIT:
  //              TiC.preInit();
  //              if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
  //                  //noinspection MethodCallSideOnly
  //                  TiCModifiers.initRender();
  //              }
  //          case INIT:
  //              TiC.init();
  //          case POST_INIT:
  //              if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
  //                  //noinspection MethodCallSideOnly
  //                  TiCMaterials.registerMaterialRendering();
  //              }
  //              TiC.postInit();
  //      }
    }

    @Override
    public String getMODID() {
        return "tconstruct";
    }

    @Override
    public boolean enableCompat() {
        return true;
    }
}