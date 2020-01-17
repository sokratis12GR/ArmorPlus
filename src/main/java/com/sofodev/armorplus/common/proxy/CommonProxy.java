/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.proxy;

import com.sofodev.armorplus.api.caps.abilities.AbilityDataHandler;
import com.sofodev.armorplus.common.compat.ICompatibility;
import com.sofodev.armorplus.common.compat.tinkers.TiC;
import com.sofodev.armorplus.common.registry.commands.subcommands.teleporter.CommandTeleport;
import com.sofodev.armorplus.common.registry.*;
import com.sofodev.armorplus.common.registry.commands.CommandArmorPlus;
import com.sofodev.armorplus.common.registry.constants.APItems;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.guardianoverlord.EntityGuardianOverlord;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.guardianoverlord.projectile.EntityFreezeBomb;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.skeletalking.projectile.EntityWitherMinion;
import com.sofodev.armorplus.common.registry.items.armors.ArmorMaterials;
import com.sofodev.armorplus.common.util.LoaderUtils;
import com.sofodev.armorplus.common.worldgen.OreGen;
import com.sofodev.armorplus.common.worldgen.nbt.StructureGenNBT;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.common.config.ModConfig.Experimental.enableExperimentalMode;
import static com.sofodev.armorplus.common.registry.ModItems.*;

/**
 * @author Sokratis Fotkatzikis
 **/
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        if (enableExperimentalMode) {
            AbilityDataHandler.register();
        }
        new ArmorMaterials();
        ModBlocks.registerBlocks();
        registerItems();
        APItems.registerItemNames();
        APItems.registerWeaponsA();
        APItems.registerWeaponsB();
        registerTCItems();
        APItems.registerTCItemNames();
        this.registerEntityFixes();
        ModEntities.registerEntitySettings();
        this.registerWorldGenerator(new OreGen(), new StructureGenNBT());
        //TConstruct
        if (LoaderUtils.isTiCIntegrationEnabled()) TiC.instance().preInit(event);
        ModDimensions.init();
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
        Arrays.stream(ardite).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
        Arrays.stream(cobalt).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
        Arrays.stream(manyullyn).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
        Arrays.stream(knightSlime).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
        Arrays.stream(pigIron).forEach(armor -> armor.setRepairStack(armor.material.getRepairStack()));
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