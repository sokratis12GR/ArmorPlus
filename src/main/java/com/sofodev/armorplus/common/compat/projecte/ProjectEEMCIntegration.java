/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.compat.projecte;

import com.google.common.collect.ImmutableMap;
import com.sofodev.armorplus.common.config.ModConfig;
import com.sofodev.armorplus.common.registry.APBlocks;
import com.sofodev.armorplus.common.registry.APItems;
import com.sofodev.armorplus.common.registry.ModBlocks;
import com.sofodev.armorplus.common.registry.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.thedragonlib.util.ItemStackUtils;

import static com.google.common.collect.ImmutableMap.of;
import static com.sofodev.armorplus.common.compat.CompatibilityProjectE.addConversion;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.init.Items.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ProjectEEMCIntegration {

    public static void registerEasyEMC() {
        ItemStack guardianScale = getItemStack(ModItems.materials, 1);
        ItemStack witherBone = getItemStack(ModItems.materials, 2);
        ItemStack enderDragonScale = getItemStack(ModItems.materials, 3);
        if (ModConfig.getRD() == ModConfig.RecipesDifficulty.EASY) {
            registerEasyArmorEMC(guardianScale, APItems.guardianHelmet, APItems.guardianChestplate, APItems.guardianLeggings, APItems.guardianBoots);
            registerEasyArmorEMC(witherBone, APItems.superStarHelmet, APItems.superStarChestplate, APItems.superStarLeggings, APItems.superStarBoots);
            registerEasyArmorEMC(enderDragonScale, APItems.enderDragonHelmet, APItems.enderDragonChestplate, APItems.enderDragonLeggings, APItems.enderDragonBoots);
            registerEasyMeleeEMC(guardianScale, APItems.guardianSword, APItems.guardianBattleAxe);
            registerEasyMeleeEMC(witherBone, APItems.superStarSword, APItems.superStarBattleAxe);
            registerEasyMeleeEMC(enderDragonScale, APItems.enderDragonSword, APItems.enderDragonBattleAxe);
            registerEasyRangedEMC(guardianScale, PRISMARINE_SHARD, APItems.guardianBow);
            registerEasyRangedEMC(witherBone, STRING, APItems.superStarBow);
            registerEasyRangedEMC(enderDragonScale, STRING, APItems.enderDragonBow);
        }
    }

    private static void registerEasyMeleeEMC(ItemStack material, Item sword, Item battleAxe) {
        addConversion(sword, of(material, 7, STICK, 6));
        addConversion(battleAxe, of(material, 12, STICK, 6));
    }

    private static void registerEasyRangedEMC(ItemStack materialA, Item materialB, Item bow) {
        addConversion(bow, of(materialA, 11, getItemStack(materialB), 7));
    }

    private static void registerEasyArmorEMC(ItemStack material, Item head, Item chest, Item legs, Item feet) {
        createArmorEMC(material, head, 11, chest, 34, legs, 24, feet, 6);
    }

    public static void registerExpertEMC() {
        ItemStack guardianScale = getItemStack(ModItems.materials, 1);
        ItemStack witherBone = getItemStack(ModItems.materials, 2);
        ItemStack enderDragonScale = getItemStack(ModItems.materials, 3);
        ItemStack theUltimateMaterial = getItemStack(ModItems.materials, 4);
        ItemStack infusedLavaCrystal = getItemStack(ModItems.itemLavaCrystal, 1);
        addConversion(getItemStack(ModBlocks.blockCompressedObsidian), of(OBSIDIAN, 9));
        addConversion(APBlocks.highTechBench, of(getItemStack(infusedLavaCrystal), 3, getItemStack(REDSTONE_BLOCK), 5, getItemStack(ModBlocks.benches[0]), 1));
        addConversion(APBlocks.ultiTechBench, of(
            infusedLavaCrystal, 2, theUltimateMaterial, 6, getItemStack(ModBlocks.benches[0]), 1, getItemStack(ModBlocks.benches[1]), 1, ModBlocks.blockCompressedObsidian, 8
        ));
        if (ModConfig.getRD() == ModConfig.RecipesDifficulty.EXPERT || ModConfig.getRD() == ModConfig.RecipesDifficulty.HELLISH) {
            //
            createWorkbenchArmorEMC(COAL_BLOCK, APItems.coalHelmet, APItems.coalChestplate, APItems.coalLeggings, APItems.coalBoots);
            createWorkbenchArmorEMC(LAPIS_BLOCK, APItems.lapisHelmet, APItems.lapisChestplate, APItems.lapisLeggings, APItems.lapisBoots);
            createWorkbenchArmorEMC(REDSTONE_BLOCK, APItems.redstoneHelmet, APItems.redstoneChestplate, APItems.redstoneLeggings, APItems.redstoneBoots);
            createWorkbenchArmorEMC(SLIME_BLOCK, APItems.slimeHelmet, APItems.slimeChestplate, APItems.slimeLeggings, APItems.slimeBoots);
            //
            createHighTechArmorEMC(EMERALD_BLOCK, EMERALD, APItems.emeraldHelmet, APItems.emeraldChestplate, APItems.emeraldLeggings, APItems.emeraldBoots);
            createHighTechArmorEMC(ModBlocks.blockCompressedObsidian, OBSIDIAN, APItems.obsidianHelmet, APItems.obsidianChestplate, APItems.obsidianLeggings, APItems.obsidianBoots);
            createHighTechArmorEMC(ModBlocks.blockInfusedLavaCrystal, infusedLavaCrystal, APItems.lavaHelmet, APItems.lavaChestplate, APItems.lavaLeggings, APItems.lavaBoots);
            //
            addConversion(APItems.chickenHelmet, of(getItemStack(FEATHER), 3, getItemStack(EGG), 2));
            addConversion(APItems.chickenChestplate, of(getItemStack(FEATHER), 5, getItemStack(EGG), 3));
            addConversion(APItems.chickenLeggings, of(getItemStack(FEATHER), 5, getItemStack(EGG), 2));
            addConversion(APItems.chickenBoots, of(getItemStack(FEATHER), 2, getItemStack(EGG), 2));
            //
            createOriginWeaponsEMC(COAL_BLOCK, APItems.coalSword, APItems.coalBattleAxe, APItems.coalBow);
            createOriginWeaponsEMC(LAPIS_BLOCK, APItems.lapisSword, APItems.lapisBattleAxe, APItems.lapisBow);
            createOriginWeaponsEMC(REDSTONE_BLOCK, APItems.redstoneSword, APItems.redstoneBattleAxe, APItems.redstoneBow);
            //
            createHighTechWeaponsEMC(EMERALD_BLOCK, APItems.emeraldSword, APItems.emeraldBattleAxe, APItems.emeraldBow);
            createHighTechWeaponsEMC(ModBlocks.blockCompressedObsidian, APItems.obsidianSword, APItems.obsidianBattleAxe, APItems.obsidianBow);
            createHighTechWeaponsEMC(infusedLavaCrystal, APItems.lavaSword, APItems.lavaBattleAxe, APItems.lavaBow);
            registerGuardianEMC(guardianScale);
            registerSuperStarEMC(witherBone);
            registerEnderDragonEMC(enderDragonScale);
            registerUltimateEMC();
        }
    }

    private static void createHighTechWeaponsEMC(Object material, Item sword, Item battleAxe, Item bow) {
        addConversion(sword, of(getItemStack(material), 4, getItemStack(STICK), 1));
        addConversion(battleAxe, of(getItemStack(material), 8, getItemStack(STICK), 4));
        addConversion(bow, of(getItemStack(material), 7, getItemStack(STRING), 5));
    }

    private static void createHighTechArmorEMC(Object materialA, Object materialB, Item head, Item chest, Item legs, Item feet) {
        addConversion(head,
            of(getItemStack(materialA), 4, getItemStack(materialB), 5)
        );
        addConversion(chest,
            of(getItemStack(materialA), 13, getItemStack(materialB), 7)
        );
        addConversion(legs,
            of(getItemStack(materialA), 7, getItemStack(materialB), 9)
        );
        addConversion(feet,
            of(getItemStack(materialA), 4, getItemStack(materialB), 2)
        );
    }

    private static void createOriginWeaponsEMC(Object material, Item sword, Item battleAxe, Item bow) {
        addConversion(sword, of(getItemStack(material), 2, getItemStack(STICK), 1));
        addConversion(battleAxe, of(getItemStack(material), 4, getItemStack(STICK), 2));
        addConversion(bow, of(getItemStack(material), 3, getItemStack(STRING), 3));
    }

    private static void createWorkbenchArmorEMC(Object material, Item head, Item chest, Item legs, Item feet) {
        createArmorEMC(getItemStack(material), head, 5, chest, 8, legs, 7, feet, 4);
    }

    private static void createArmorEMC(ItemStack material, Item head, int headAmount, Item chest, int chestAmount, Item legs, int legsAmount, Item feet, int feetAmount) {
        addConversion(head,
            of(material, headAmount)
        );
        addConversion(chest,
            of(material, chestAmount)
        );
        addConversion(legs,
            of(material, legsAmount)
        );
        addConversion(feet,
            of(material, feetAmount)
        );
    }

    private static void registerUltimateEMC() {
        //
        registerUltimatePartsEMC(0, 1, 2, APItems.guardianHelmet, APItems.enderDragonHelmet, APItems.superStarHelmet);
        addConversion(APItems.theUltimateHelmet, of(APItems.theUltimateMaterial, 26, APItems.infusedLavaCrystal, 12,
            ItemStackUtils.getItemStack(ModItems.theUltimateParts, 0), 1, ItemStackUtils.getItemStack(ModItems.theUltimateParts, 1), 1, ItemStackUtils.getItemStack(ModItems.theUltimateParts, 2), 1
        ));
        //
        registerUltimatePartsEMC(3, 4, 5, APItems.guardianChestplate, APItems.enderDragonChestplate, APItems.superStarChestplate);
        addConversion(APItems.theUltimateChestplate, of(APItems.theUltimateMaterial, 26, APItems.infusedLavaCrystal, 12,
            ItemStackUtils.getItemStack(ModItems.theUltimateParts, 3), 1, ItemStackUtils.getItemStack(ModItems.theUltimateParts, 4), 1, ItemStackUtils.getItemStack(ModItems.theUltimateParts, 5), 1
        ));
        //
        registerUltimatePartsEMC(6, 7, 8, APItems.guardianLeggings, APItems.enderDragonLeggings, APItems.superStarLeggings);
        addConversion(APItems.theUltimateLeggings, of(APItems.theUltimateMaterial, 26, APItems.infusedLavaCrystal, 12,
            ItemStackUtils.getItemStack(ModItems.theUltimateParts, 6), 1, ItemStackUtils.getItemStack(ModItems.theUltimateParts, 7), 1, ItemStackUtils.getItemStack(ModItems.theUltimateParts, 8), 1
        ));
        //
        registerUltimatePartsEMC(9, 10, 11, APItems.guardianBoots, APItems.enderDragonBoots, APItems.superStarBoots);
        addConversion(APItems.theUltimateBoots, of(APItems.theUltimateMaterial, 26, APItems.infusedLavaCrystal, 12,
            ItemStackUtils.getItemStack(ModItems.theUltimateParts, 9), 1, ItemStackUtils.getItemStack(ModItems.theUltimateParts, 10), 1, ItemStackUtils.getItemStack(ModItems.theUltimateParts, 11), 1
        ));
    }

    private static void registerUltimatePartsEMC(int right, int middle, int left, Item itemRight, Item itemMid, Item itemLeft) {
        addConversion(ItemStackUtils.getItemStack(ModItems.theUltimateParts, right), new ImmutableMap.Builder<Object, Integer>().put(APItems.theUltimateMaterial, 28).put(APItems.infusedLavaCrystal, 12)
            .put(APItems.guardianScale, 2).put(SPONGE, 2).put(PRISMARINE_SHARD, 2).put(PRISMARINE_CRYSTALS, 2).put(itemRight, 1).build()
        );
        addConversion(ItemStackUtils.getItemStack(ModItems.theUltimateParts, middle), new ImmutableMap.Builder<Object, Integer>().put(APItems.theUltimateMaterial, 28).put(APItems.infusedLavaCrystal, 12)
            .put(APItems.enderDragonScale, 2).put(END_CRYSTAL, 2).put(ENDER_EYE, 2).put(ENDER_PEARL, 2).put(itemMid, 1).build()
        );
        addConversion(ItemStackUtils.getItemStack(ModItems.theUltimateParts, left), new ImmutableMap.Builder<Object, Integer>().put(APItems.theUltimateMaterial, 28).put(APItems.infusedLavaCrystal, 12)
            .put(APItems.witherBone, 2).put(NETHER_BRICK, 2).put(SOUL_SAND, 2).put(NETHERRACK, 2).put(itemLeft, 1).build()
        );
    }

    private static void registerGuardianEMC(ItemStack guardianScale) {
        addConversion(APItems.guardianHelmet,
            of(guardianScale, 8, getItemStack(SEA_LANTERN), 3, getItemStack(PRISMARINE_CRYSTALS), 3, getItemStack(PRISMARINE), 2)
        );
        addConversion(APItems.guardianChestplate,
            of(guardianScale, 20, getItemStack(SEA_LANTERN), 4, getItemStack(PRISMARINE_CRYSTALS), 4, getItemStack(PRISMARINE), 6)
        );
        addConversion(APItems.guardianLeggings,
            of(guardianScale, 15, getItemStack(SEA_LANTERN), 1, getItemStack(PRISMARINE_CRYSTALS), 4, getItemStack(PRISMARINE), 4)
        );
        addConversion(APItems.guardianBoots,
            of(guardianScale, 6, getItemStack(SPONGE), 2)
        );
        addConversion(APItems.guardianSword, of(guardianScale, 7, getItemStack(PRISMARINE_SHARD), 6));
        addConversion(APItems.guardianBattleAxe, of(guardianScale, 12, getItemStack(PRISMARINE_SHARD), 6));
        addConversion(APItems.guardianBow, of(guardianScale, 11, getItemStack(PRISMARINE_SHARD), 7));
    }

    private static void registerSuperStarEMC(ItemStack witherBone) {
        addConversion(APItems.superStarHelmet,
            of(witherBone, 8, getItemStack(NETHER_STAR), 5, getItemStack(SOUL_SAND), 2)
        );
        addConversion(APItems.superStarChestplate,
            of(witherBone, 19, getItemStack(NETHER_STAR), 10, getItemStack(SOUL_SAND), 4, getItemStack(Items.SKULL, 1), 1)
        );
        addConversion(APItems.superStarLeggings,
            of(witherBone, 6, getItemStack(NETHER_STAR), 7, getItemStack(SOUL_SAND), 4)
        );
        addConversion(APItems.superStarBoots,
            of(witherBone, 4, getItemStack(NETHER_STAR), 2, getItemStack(SOUL_SAND), 2)
        );
        addConversion(APItems.superStarSword, of(witherBone, 6, getItemStack(NETHER_STAR), 6, getItemStack(Items.SKULL, 1), 1));
        addConversion(APItems.superStarBattleAxe, of(witherBone, 12, getItemStack(NETHER_STAR), 5, getItemStack(Items.SKULL, 1), 1));
        addConversion(APItems.superStarBow, of(witherBone, 6, getItemStack(NETHER_STAR), 5, getItemStack(STRING), 7));
    }

    private static void registerEnderDragonEMC(ItemStack enderDragonScale) {
        addConversion(APItems.enderDragonHelmet,
            of(enderDragonScale, 11, getItemStack(ENDER_EYE), 2, getItemStack(END_CRYSTAL), 2)
        );
        addConversion(APItems.enderDragonChestplate,
            of(enderDragonScale, 18, getItemStack(ENDER_EYE), 6, getItemStack(END_CRYSTAL), 6, getItemStack(END_STONE), 4)
        );
        addConversion(APItems.enderDragonLeggings,
            of(enderDragonScale, 17, getItemStack(ENDER_EYE), 3, getItemStack(END_CRYSTAL), 6)
        );
        addConversion(APItems.enderDragonBoots,
            of(enderDragonScale, 4, getItemStack(ENDER_EYE), 2, getItemStack(END_CRYSTAL), 2)
        );
        addConversion(APItems.enderDragonSword, of(enderDragonScale, 6, getItemStack(DRAGON_BREATH), 6));
        addConversion(APItems.enderDragonBattleAxe, of(enderDragonScale, 12, getItemStack(DRAGON_BREATH), 6));
        addConversion(APItems.enderDragonBow, of(enderDragonScale, 6, getItemStack(DRAGON_BREATH), 5, getItemStack(STRING), 7));
    }
}
