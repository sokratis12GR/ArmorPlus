/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.proxy;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.client.misc.CosmeticsRenderInit;
import com.sofodev.armorplus.common.compat.tinkers.TiCMaterials;
import com.sofodev.armorplus.common.tileentity.TESRTrophy;
import com.sofodev.armorplus.common.tileentity.TileTrophy;
import com.sofodev.armorplus.common.util.LoaderUtils;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Sokratis Fotkatzikis
 **/
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(this);
        OBJLoader.INSTANCE.addDomain(ArmorPlus.MODID);
        new CosmeticsRenderInit();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ClientRegistry.bindTileEntitySpecialRenderer(TileTrophy.class, new TESRTrophy());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        new CosmeticsRenderInit();
        //TConstruct
        if (LoaderUtils.isTiCIntegrationEnabled()) TiCMaterials.registerMaterialRendering();
    }
}