/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.proxy;

import net.minecraft.util.datafix.DataFixesManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.armorplus.client.gui.APTab;
import net.thedragonteam.armorplus.commands.CommandArmorPlus;
import net.thedragonteam.armorplus.entity.dungeon.guardian.EntityGuardianOverlord;
import net.thedragonteam.armorplus.entity.dungeon.guardian.projectile.EntityFreezeBomb;
import net.thedragonteam.armorplus.registry.*;
import net.thedragonteam.armorplus.resources.GlobalEventsArmorPlus;
import net.thedragonteam.armorplus.tileentity.*;
import net.thedragonteam.armorplus.worldgen.OreGen;
import net.thedragonteam.armorplus.worldgen.nbt.StructureGenNBT;
import net.thedragonteam.thedragonlib.util.LogHelper;

import static net.thedragonteam.armorplus.compat.ICompatibility.InitializationPhase.*;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        ModSounds.init();
        ModPotions.registerPotions();
        ModEntities.init();
        ModBlocks.init();
        LogHelper.debug("Blocks Successfully Registered");
        APBlocks.init();
        ModItems.init();// Initializes the items
        LogHelper.debug("Items Successfully Registered");
        APItems.init(); // Initializes the helper item class
        registerWorldGenerators();
        registerTileEntities();
        MinecraftForge.EVENT_BUS.register(new MobDrops());
        registerFixes();
        ModCompatibility.registerModCompat();
        ModCompatibility.loadCompat(PRE_INIT);
        LogHelper.info("Finished PreInitialization");
    }

    public void init(FMLInitializationEvent event) {
        registerEvents();
        ModOreDicts.registerOreDictEntries();
        APTab.init();
      //  ModEnchantments.registerEnchantments();
        ModCompatibility.loadCompat(INIT);
        LogHelper.info("Finished Initialization");
    }

    public void postInit(FMLPostInitializationEvent event) {
        ModCompatibility.loadCompat(POST_INIT);
        LogHelper.info("Finished PostInitialization");
    }

    public void modMapping(FMLModIdMappingEvent event) {
        ModCompatibility.loadCompat(MAPPING);
    }

    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandArmorPlus());
    }

    public void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new GlobalEventsArmorPlus());
        //Register to receive subscribed events
        MinecraftForge.EVENT_BUS.register(this);
        //    ModAchievements.init();
        ModRecipes.init();
        LavaInfuserRecipes.init();
    }

    public void registerModels() {
    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntityWithAlternatives(TileEntityWorkbench.class, "Workbench", "APWorkbench", "WorkbenchTier1", "WorkbenchTierOne");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityHighTechBench.class, "HighTechBench", "APHighTechBench", "WorkbenchTier2", "WorkbenchTierTwo");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityUltiTechBench.class, "UltiTechBench", "APUltiTechBench", "WorkbenchTier3", "WorkbenchTierThree");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityChampionBench.class, "ChampionBench", "APChampionBench", "WorkbenchTier4", "WorkbenchTierFour");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityLavaInfuser.class, "LavaInfuserRecipe", "APLavaInfuser");
    }

    public void registerWorldGenerators() {
        GameRegistry.registerWorldGenerator(new OreGen(), 1);
        GameRegistry.registerWorldGenerator(new StructureGenNBT(), 2);
    }

    public void registerFixes() {
        TileEntityLavaInfuser.registerFixesFurnace(DataFixesManager.createFixer());
        EntityGuardianOverlord.registerFixesElderGuardian(DataFixesManager.createFixer());
        EntityFreezeBomb.registerFixesFreezeBomb(DataFixesManager.createFixer());
    }
}