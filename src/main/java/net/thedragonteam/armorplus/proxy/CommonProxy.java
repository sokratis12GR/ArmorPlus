/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.proxy;

import net.minecraft.util.datafix.DataFixesManager;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.armorplus.client.gui.APTab;
import net.thedragonteam.armorplus.commands.CommandArmorPlus;
import net.thedragonteam.armorplus.entity.dungeon.guardian.EntityGuardianOverlord;
import net.thedragonteam.armorplus.entity.dungeon.guardian.projectile.EntityFreezeBomb;
import net.thedragonteam.armorplus.entity.dungeon.wither.EntitySkeletalKing;
import net.thedragonteam.armorplus.entity.dungeon.wither.projectile.EntityWitherMinion;
import net.thedragonteam.armorplus.registry.*;
import net.thedragonteam.armorplus.worldgen.OreGen;
import net.thedragonteam.armorplus.worldgen.nbt.StructureGenNBT;
import net.thedragonteam.thedragonlib.util.LogHelper;

import static net.thedragonteam.armorplus.compat.ICompatibility.InitializationPhase.*;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        ModBlocks.registerBlocks();
        ModItems.registerItems();
        APItems.registerItemNames();
        this.registerEntityFixes();
        ModEntities.registerEntitySettings();
        registerWorldGenerators();
        ModCompatibility.registerModCompat();
        ModCompatibility.loadCompat(PRE_INIT);
        LogHelper.info("Finished PreInitialization");
    }

    private void registerEntityFixes() {
        EntityGuardianOverlord.registerFixesElderGuardian(DataFixesManager.createFixer());
        EntityFreezeBomb.registerFixesFreezeBomb(DataFixesManager.createFixer());
        EntitySkeletalKing.registerFixesSkeletalKing(DataFixesManager.createFixer());
        EntityWitherMinion.registerFixesWitherMinion(DataFixesManager.createFixer());
    }

    public void init(FMLInitializationEvent event) {
        ModOreDicts.registerOreDictEntries();
        APTab.registerTabs();
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

    public void registerWorldGenerators() {
        GameRegistry.registerWorldGenerator(new OreGen(), 1);
        GameRegistry.registerWorldGenerator(new StructureGenNBT(), 2);
    }
}