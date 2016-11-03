/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.proxy;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.ARPEntities;
import net.thedragonteam.armorplus.entity.entityarrow.EntityCoalArrow;
import net.thedragonteam.armorplus.entity.entityarrow.EntityLapisArrow;
import net.thedragonteam.armorplus.entity.entityarrow.EntityLavaArrow;
import net.thedragonteam.armorplus.entity.entityarrow.EntityRedstoneArrow;
import net.thedragonteam.armorplus.entity.render.RenderCoalArrow;
import net.thedragonteam.armorplus.entity.render.RenderLapisArrow;
import net.thedragonteam.armorplus.entity.render.RenderLavaArrow;
import net.thedragonteam.armorplus.entity.render.RenderRedstoneArrow;
import net.thedragonteam.armorplus.integration.TiC;
import net.thedragonteam.armorplus.integration.tinkers.TiCMaterials;
import net.thedragonteam.armorplus.misc.CosmeticsRenderInit;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        if (event.getSide().isClient() && Loader.isModLoaded("tconstruct")) TiC.init();
        super.init(event);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        OBJLoader.INSTANCE.addDomain(ArmorPlus.MODID);
        registerModels();
        registerRenderer();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        if (event.getSide().isClient() && Loader.isModLoaded("tconstruct")) TiCMaterials.registerMaterialRendering();
        new CosmeticsRenderInit();
    }

    @Override
    public void registerModels() {
        ModItems.initModels();
        ModBlocks.initModels();
        ARPEntities.initModels();
    }

    public void registerRenderer() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCoalArrow.class, RenderCoalArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLapisArrow.class, RenderLapisArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRedstoneArrow.class, RenderRedstoneArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLavaArrow.class, RenderLavaArrow::new);
    }
}