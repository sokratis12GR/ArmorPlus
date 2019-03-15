/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.proxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

/**
 * @author Sokratis Fotkatzikis
 **/
@Mod.EventBusSubscriber(modid = MODID, bus = MOD)
public class ClientProxy implements IProxy {
    @Override
    public void setup(FMLCommonSetupEvent event) {

    }

    //  @Override
    //  public void preInit(FMLPreInitializationEvent event) {
    //      super.preInit(event);
    //      MinecraftForge.EVENT_BUS.register(this);
    //      OBJLoader.INSTANCE.addDomain(MODID);
    //      new CosmeticsRenderInit();
    //  }
//
    //  @Override
    //  public void init(FMLInitializationEvent event) {
    //      super.init(event);
    //      ClientRegistry.bindTileEntitySpecialRenderer(TileTrophy.class, new TESRTrophy());
    //  }
//
    //  @Override
    //  public void postInit(FMLPostInitializationEvent event) {
    //      super.postInit(event);
    //      new CosmeticsRenderInit();
    //      //TConstruct
    //      if (LoaderUtils.isTiCIntegrationEnabled()) TiCMaterials.registerMaterialRendering();
    //  }
}