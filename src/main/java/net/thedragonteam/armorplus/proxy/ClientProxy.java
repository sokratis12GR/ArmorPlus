/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.proxy;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.compat.tinkers.TiCMaterials;
import net.thedragonteam.armorplus.misc.CosmeticsRenderInit;
import net.thedragonteam.armorplus.tileentity.TESRTrophy;
import net.thedragonteam.armorplus.tileentity.TileEntityTrophy;

import static net.thedragonteam.armorplus.util.LoaderUtils.isTiCIntegrationEnabled;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(this);
        OBJLoader.INSTANCE.addDomain(ArmorPlus.MODID);
        new CosmeticsRenderInit();
    }

    public void init(FMLInitializationEvent event) {
        super.init(event);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrophy.class, new TESRTrophy());
    }

    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        new CosmeticsRenderInit();
        //TConstruct
        if (isTiCIntegrationEnabled()) TiCMaterials.registerMaterialRendering();
    }
}