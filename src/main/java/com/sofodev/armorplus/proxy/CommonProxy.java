/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.proxy;

import com.sofodev.armorplus.caps.abilities.AbilityDataHandler;
import com.sofodev.armorplus.commands.CommandArmorPlus;
import com.sofodev.armorplus.compat.ICompatibility;
import com.sofodev.armorplus.compat.tinkers.TiC;
import com.sofodev.armorplus.entity.dungeon.guardianoverlord.EntityGuardianOverlord;
import com.sofodev.armorplus.entity.dungeon.guardianoverlord.projectile.EntityFreezeBomb;
import com.sofodev.armorplus.entity.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.armorplus.entity.dungeon.skeletalking.projectile.EntityWitherMinion;
import com.sofodev.armorplus.registry.*;
import com.sofodev.armorplus.util.LoaderUtils;
import com.sofodev.armorplus.worldgen.OreGen;
import com.sofodev.armorplus.worldgen.nbt.StructureGenNBT;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.config.ModConfig.Experimental.enableExperimentalMode;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.global_registry;
import static com.sofodev.armorplus.registry.ModItems.*;

/**
 * @author Sokratis Fotkatzikis
 **/
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        if (enableExperimentalMode) {
            AbilityDataHandler.register();
        }
        ModBlocks.registerBlocks();
        registerItems();
        APItems.registerItemNames();
        APItems.registerWeaponsA();
        APItems.registerWeaponsB();
        this.registerEntityFixes();
        ModEntities.registerEntitySettings();
        this.registerWorldGenerator(new OreGen(), new StructureGenNBT());
        //TConstruct
        if (LoaderUtils.isTiCIntegrationEnabled()) TiC.instance().preInit(event);
        registerTCItems();
        APItems.registerTCItemNames();
        ModCompatibility.registerModCompat();
        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.PRE_INIT);
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
        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.INIT);
        ModRecipes.init();
        //TConstruct
        if (LoaderUtils.isTiCIntegrationEnabled()) TiC.instance().init(event);
        if (!enableExperimentalMode) {
            if (global_registry.enableArditeArmor) {
                Arrays.stream(ardite).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
            }
            if (global_registry.enableCobaltArmor) {
                Arrays.stream(cobalt).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
            }
            if (global_registry.enableManyullynArmor) {
                Arrays.stream(manyullyn).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
            }
            if (global_registry.enableKnightSlimeArmor) {
                Arrays.stream(knightSlime).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
            }
            if (global_registry.enablePigIronArmor) {
                Arrays.stream(pigIron).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
            }
        }
        LogHelper.getLogger(MODID).info("Finished Initialization");
    }

    public void postInit(FMLPostInitializationEvent event) {
        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.POST_INIT);
        //TConstruct
        if (LoaderUtils.isTiCIntegrationEnabled()) TiC.instance().postInit(event);
        LogHelper.getLogger(MODID).info("Finished PostInitialization");
    }

    public void modMapping(FMLModIdMappingEvent event) {
        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.MAPPING);
    }

    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandArmorPlus());
    }

    private void registerWorldGenerator(IWorldGenerator... generators) {
        IntStream.range(0, generators.length).forEachOrdered(i -> GameRegistry.registerWorldGenerator(generators[i], i));
    }
}