/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
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
import net.thedragonteam.armorplus.misc.SpecialRenderInit;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import java.io.File;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        if (event.getSide().isClient()) {
            if (Loader.isModLoaded("tconstruct")) {
                TiC.init();
            }
        }
        super.init(event);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        OBJLoader.INSTANCE.addDomain(ArmorPlus.MODID);
        registerRenderers();
        registerRenderer();
        ARPEntities.initModels();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        if (event.getSide().isClient()) {
            if (Loader.isModLoaded("tconstruct")) {
                TiCMaterials.registerMaterialRendering();
            }
        }
        new SpecialRenderInit();
    }

    @Override
    public boolean isPaused() {
        if (FMLClientHandler.instance().getClient().isSingleplayer() && !FMLClientHandler.instance().getClient().getIntegratedServer().getPublic()) {
            GuiScreen screen = FMLClientHandler.instance().getClient().currentScreen;

            if (screen != null && screen.doesGuiPauseGame()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public File getMinecraftDir() {
        return Minecraft.getMinecraft().mcDataDir;
    }

    @Override
    public void registerRenderers() {
        ModItems.initModels();
        ModBlocks.initModels();
    }

    @SuppressWarnings("unchecked")
    public void registerRenderer() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCoalArrow.class, RenderCoalArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLapisArrow.class, RenderLapisArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRedstoneArrow.class, RenderRedstoneArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLavaArrow.class, RenderLavaArrow::new);
    }

    private void registerModel(Object obj, int meta) {

        Item item;
        if (obj instanceof Item) {
            item = (Item) obj;
        } else if (obj instanceof Block) {
            item = Item.getItemFromBlock((Block) obj);
        } else {
            throw new IllegalArgumentException("Only item and block instances");
        }
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    public void registerModels() {
        registerModel(ModItems.coalArrow, 0);
        registerModel(ModItems.lapisArrow, 0);
        registerModel(ModItems.redstoneArrow, 0);
        registerModel(ModItems.lavaArrow, 0);
    }

    @Override
    public EntityPlayer getPlayer(MessageContext context) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            return context.getServerHandler().playerEntity;
        } else {
            return Minecraft.getMinecraft().thePlayer;
        }
    }

    @Override
    public void handlePacket(Runnable runnable, EntityPlayer player) {
        if (player == null || player.worldObj.isRemote) {
            Minecraft.getMinecraft().addScheduledTask(runnable);
        } else if (player != null && !player.worldObj.isRemote) {
            ((WorldServer) player.worldObj).addScheduledTask(runnable); //singleplayer
        }
    }
}


