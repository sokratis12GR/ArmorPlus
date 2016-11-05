/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.client.gui.ARPTab;
import net.thedragonteam.armorplus.commands.CommandArmorPlus;
import net.thedragonteam.armorplus.compat.ICompatibility;
import net.thedragonteam.armorplus.entity.ARPEntities;
import net.thedragonteam.armorplus.integration.TiC;
import net.thedragonteam.armorplus.registry.*;
import net.thedragonteam.armorplus.resources.GlobalEventsArmorPlus;
import net.thedragonteam.armorplus.tileentity.TileEntityHighTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityUltiTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityWorkbench;
import net.thedragonteam.armorplus.util.ARPAchievements;
import net.thedragonteam.armorplus.worldgen.OreGen;
import net.thedragonteam.armorplus.worldgen.StructureGen;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.io.File;

public class CommonProxy {

    public static File configDir;

    public void preInit(FMLPreInitializationEvent event) {
        LogHelper.info("Begin PreInitialization");
        ModCompatibility.registerModCompat();
        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.PRE_INIT);
        ARPEntities.init();
        ModBlocks.init();
        LogHelper.debug("Blocks Successfully Registered");
        ModItems.init();
        LogHelper.debug("Items Successfully Registered");
        registerWorldGenerators();
        registerTileEntities();
        configDir = new File(event.getModConfigurationDirectory() + "/" + ArmorPlus.MODID);
        configDir.mkdirs();
        net.thedragonteam.armorplus.util.Logger.init(new File(event.getModConfigurationDirectory().getPath()));
        if (Loader.isModLoaded("tconstruct")) TiC.preInit();
        MinecraftForge.EVENT_BUS.register(new MobDrops());
        LogHelper.info("Finished PreInitialization");
    }

    public void init(FMLInitializationEvent event) {
        LogHelper.info("Begin Initialization");
        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.INIT);
        registerEvents();
        ModOreDicts.registerOreDictEnties();
        ARPTab.initialize();
        ModEnchantments.registerEnchantments();
        if (Loader.isModLoaded("tconstruct")) TiC.init();
        LogHelper.info("Finished Initialization");
    }

    public void postInit(FMLPostInitializationEvent event) {
        LogHelper.info("Begin PostInitialization");
        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.POST_INIT);
        LogHelper.info("Finished PostInitialization");
    }

    public void modMapping(FMLModIdMappingEvent event) {
        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.MAPPING);
    }

    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandArmorPlus());
    }

    public void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new GlobalEventsArmorPlus());
        //Register to receive subscribed events
        MinecraftForge.EVENT_BUS.register(this);
        ARPAchievements.init();
        ModRecipes.init();
    }

    public void registerModels() {
    }

    public void registerRenderer() {
    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntityWithAlternatives(TileEntityWorkbench.class, "Workbench", "ARPWorkbench", "WorkbenchTier1", "WorkbenchTierOne");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityHighTechBench.class, "HighTechBench", "ARPHighTechBench", "WorkbenchTier2", "WorkbenchTierTwo");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityUltiTechBench.class, "UltiTechBench", "ARPHighTechBench", "WorkbenchTier3", "WorkbenchTierThree");
    }

    public void registerWorldGenerators() {
        GameRegistry.registerWorldGenerator(new OreGen(), 0);
        GameRegistry.registerWorldGenerator(new StructureGen(), 1);
    }
}