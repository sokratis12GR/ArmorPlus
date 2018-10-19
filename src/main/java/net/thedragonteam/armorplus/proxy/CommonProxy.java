/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.proxy;

import net.minecraft.util.datafix.DataFixesManager;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.armorplus.commands.CommandArmorPlus;
import net.thedragonteam.armorplus.compat.tinkers.TiC;
import net.thedragonteam.armorplus.entity.dungeon.guardianoverlord.EntityGuardianOverlord;
import net.thedragonteam.armorplus.entity.dungeon.guardianoverlord.projectile.EntityFreezeBomb;
import net.thedragonteam.armorplus.entity.dungeon.skeletalking.EntitySkeletalKing;
import net.thedragonteam.armorplus.entity.dungeon.skeletalking.projectile.EntityWitherMinion;
import net.thedragonteam.armorplus.registry.*;
import net.thedragonteam.armorplus.worldgen.OreGen;
import net.thedragonteam.armorplus.worldgen.nbt.StructureGenNBT;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Arrays;
import java.util.stream.IntStream;

import static net.thedragonteam.armorplus.compat.ICompatibility.InitializationPhase.*;
import static net.thedragonteam.armorplus.util.LoaderUtils.isTiCIntegrationEnabled;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        ModBlocks.registerBlocks();
        ModItems.registerItems();
        APItems.registerItemNames();
        APItems.registerWeaponsA();
        APItems.registerWeaponsB();
        this.registerEntityFixes();
        ModEntities.registerEntitySettings();
        this.registerWorldGenerator(new OreGen(), new StructureGenNBT());
        //TConstruct
        if (isTiCIntegrationEnabled()) TiC.instance().preInit(event);
        ModItems.registerTCItems();
        APItems.registerTCItemNames();
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
        ModCompatibility.loadCompat(INIT);
        ModRecipes.init();
        //TConstruct
        if (isTiCIntegrationEnabled()) TiC.instance().init(event);
        Arrays.stream(ModItems.ardite).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
        Arrays.stream(ModItems.cobalt).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
        Arrays.stream(ModItems.manyullyn).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
        Arrays.stream(ModItems.knightSlime).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
        Arrays.stream(ModItems.pigIron).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
        LogHelper.info("Finished Initialization");
    }

    public void postInit(FMLPostInitializationEvent event) {
        ModCompatibility.loadCompat(POST_INIT);
        //TConstruct
        if (isTiCIntegrationEnabled()) TiC.instance().postInit(event);
        LogHelper.info("Finished PostInitialization");
    }

    public void modMapping(FMLModIdMappingEvent event) {
        ModCompatibility.loadCompat(MAPPING);
    }

    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandArmorPlus());
    }

    private void registerWorldGenerator(IWorldGenerator... generators) {
        IntStream.range(0, generators.length).forEachOrdered(i -> GameRegistry.registerWorldGenerator(generators[i], i));
    }
}