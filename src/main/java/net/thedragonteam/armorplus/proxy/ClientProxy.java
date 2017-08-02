/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.proxy;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.compat.tinkers.TiCMaterials;
import net.thedragonteam.armorplus.compat.tinkers.modifiers.TiCModifiers;
import net.thedragonteam.armorplus.util.LoaderUtils;

import static net.thedragonteam.armorplus.APConfig.enableTConstructIntegration;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        OBJLoader.INSTANCE.addDomain(ArmorPlus.MODID);
        if (APConfig.enableTConstructIntegration) {
            TiCModifiers.initRender();
        }
    }

    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        if (LoaderUtils.isTiCLoaded() && enableTConstructIntegration) {
            TiCMaterials.registerMaterialRendering();
        }
    }

    public void initRenderMaterials() {
    }
}