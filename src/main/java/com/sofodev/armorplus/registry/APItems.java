/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.config.ModConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 **/
public class APItems {

    public static Item coalHelmet, coalChestplate, coalLeggings, coalBoots;
    public static Item lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots;
    public static Item redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots;
    public static Item emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots;
    public static Item obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots;
    public static Item lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots;
    public static Item chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots;
    public static Item slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots;
    public static Item guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots;
    public static Item superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots;
    public static Item enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots;
    public static Item theUltimateHelmet, theUltimateChestplate, theUltimateLeggings, theUltimateBoots;
    public static Item cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots;
    public static Item arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots;
    public static Item manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots;
    public static Item pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots;
    public static Item knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots;
    public static Item coalSword, coalBattleAxe, coalBow;
    public static Item lapisSword, lapisBattleAxe, lapisBow;
    public static Item redstoneSword, redstoneBattleAxe, redstoneBow;
    public static Item emeraldSword, emeraldBattleAxe, emeraldBow;
    public static Item obsidianSword, obsidianBattleAxe, obsidianBow;
    public static Item lavaSword, lavaBattleAxe, lavaBow;
    public static Item guardianSword, guardianBattleAxe, guardianBow;
    public static Item superStarSword, superStarBattleAxe, superStarBow;
    public static Item enderDragonSword, enderDragonBattleAxe, enderDragonBow;

    public static ItemStack material;
    public static ItemStack chainmail;
    public static ItemStack guardianScale;
    public static ItemStack witherBone;
    public static ItemStack enderDragonScale;
    public static ItemStack theUltimateMaterial;
    public static ItemStack infusedLavaCrystal;

    public static void registerItemNames() {
        ModConfig.RegistryConfig.GlobalRegistry gr = ModConfig.RegistryConfig.global_registry;
        if (gr.enableCoalArmor) {
            coalHelmet = ModItems.coal[0];
            coalChestplate = ModItems.coal[1];
            coalLeggings = ModItems.coal[2];
            coalBoots = ModItems.coal[3];
        }
        if (gr.enableLapisArmor) {
            lapisHelmet = ModItems.lapis[0];
            lapisChestplate = ModItems.lapis[1];
            lapisLeggings = ModItems.lapis[2];
            lapisBoots = ModItems.lapis[3];
        }
        if (gr.enableRedstoneArmor) {
            redstoneHelmet = ModItems.redstone[0];
            redstoneChestplate = ModItems.redstone[1];
            redstoneLeggings = ModItems.redstone[2];
            redstoneBoots = ModItems.redstone[3];
        }
        if (gr.enableEmeraldArmor) {
            emeraldHelmet = ModItems.emerald[0];
            emeraldChestplate = ModItems.emerald[1];
            emeraldLeggings = ModItems.emerald[2];
            emeraldBoots = ModItems.emerald[3];
        }
        if (gr.enableObsidianArmor) {
            obsidianHelmet = ModItems.obsidian[0];
            obsidianChestplate = ModItems.obsidian[1];
            obsidianLeggings = ModItems.obsidian[2];
            obsidianBoots = ModItems.obsidian[3];
        }
        if (gr.enableLavaArmor) {
            lavaHelmet = ModItems.lava[0];
            lavaChestplate = ModItems.lava[1];
            lavaLeggings = ModItems.lava[2];
            lavaBoots = ModItems.lava[3];
        }
        if (gr.enableChickenArmor) {
            chickenHelmet = ModItems.chicken[0];
            chickenChestplate = ModItems.chicken[1];
            chickenLeggings = ModItems.chicken[2];
            chickenBoots = ModItems.chicken[3];
        }
        if (gr.enableSlimeArmor) {
            slimeHelmet = ModItems.slime[0];
            slimeChestplate = ModItems.slime[1];
            slimeLeggings = ModItems.slime[2];
            slimeBoots = ModItems.slime[3];
        }
        if (gr.enableGuardianArmor) {
            guardianHelmet = ModItems.guardian[0];
            guardianChestplate = ModItems.guardian[1];
            guardianLeggings = ModItems.guardian[2];
            guardianBoots = ModItems.guardian[3];
        }
        if (gr.enableSuperStarArmor) {
            superStarHelmet = ModItems.superStar[0];
            superStarChestplate = ModItems.superStar[1];
            superStarLeggings = ModItems.superStar[2];
            superStarBoots = ModItems.superStar[3];
        }
        if (gr.enableEnderDragonArmor) {
            enderDragonHelmet = ModItems.enderDragon[0];
            enderDragonChestplate = ModItems.enderDragon[1];
            enderDragonLeggings = ModItems.enderDragon[2];
            enderDragonBoots = ModItems.enderDragon[3];
        }
        if (gr.enableTheUltimateArmor) {
            theUltimateHelmet = ModItems.theUltimate[0];
            theUltimateChestplate = ModItems.theUltimate[1];
            theUltimateLeggings = ModItems.theUltimate[2];
            theUltimateBoots = ModItems.theUltimate[3];
        }

        infusedLavaCrystal = getItemStack(ModItems.itemLavaCrystal, 1);
        chainmail = getItemStack(ModItems.materials, 0);
        guardianScale = getItemStack(ModItems.materials, 1);
        witherBone = getItemStack(ModItems.materials, 2);
        enderDragonScale = getItemStack(ModItems.materials, 3);
        theUltimateMaterial = getItemStack(ModItems.materials, 4);
    }

    public static void registerWeaponsA() {
        ModConfig.RegistryConfig.GlobalRegistry gr = ModConfig.RegistryConfig.global_registry;
        if (gr.enableCoalWeapons[0]) coalSword = ModItems.sword[0];
        if (gr.enableCoalWeapons[1]) coalBattleAxe = ModItems.battleAxe[0];
        if (gr.enableCoalWeapons[2]) coalBow = ModItems.bow[0];
        if (gr.enableLapisWeapons[0]) lapisSword = ModItems.sword[1];
        if (gr.enableLapisWeapons[1]) lapisBattleAxe = ModItems.battleAxe[1];
        if (gr.enableLapisWeapons[2]) lapisBow = ModItems.bow[1];
        if (gr.enableRedstoneWeapons[0]) redstoneSword = ModItems.sword[2];
        if (gr.enableRedstoneWeapons[1]) redstoneBattleAxe = ModItems.battleAxe[2];
        if (gr.enableRedstoneWeapons[2]) redstoneBow = ModItems.bow[2];
        if (gr.enableEmeraldWeapons[0]) emeraldSword = ModItems.sword[3];
        if (gr.enableEmeraldWeapons[1]) emeraldBattleAxe = ModItems.battleAxe[3];
        if (gr.enableEmeraldWeapons[2]) emeraldBow = ModItems.bow[3];
        if (gr.enableObsidianWeapons[0]) obsidianSword = ModItems.sword[4];
        if (gr.enableObsidianWeapons[1]) obsidianBattleAxe = ModItems.battleAxe[4];
        if (gr.enableObsidianWeapons[2]) obsidianBow = ModItems.bow[4];
    }

    public static void registerWeaponsB() {
        ModConfig.RegistryConfig.GlobalRegistry gr = ModConfig.RegistryConfig.global_registry;
        if (gr.enableLavaWeapons[0]) lavaSword = ModItems.sword[5];
        if (gr.enableLavaWeapons[1]) lavaBattleAxe = ModItems.battleAxe[5];
        if (gr.enableLavaWeapons[2]) lavaBow = ModItems.bow[5];
        if (gr.enableGuardianWeapons[0]) guardianSword = ModItems.sword[6];
        if (gr.enableGuardianWeapons[1]) guardianBattleAxe = ModItems.battleAxe[6];
        if (gr.enableGuardianWeapons[2]) guardianBow = ModItems.bow[6];
        if (gr.enableSuperStarWeapons[0]) superStarSword = ModItems.sword[7];
        if (gr.enableSuperStarWeapons[1]) superStarBattleAxe = ModItems.battleAxe[7];
        if (gr.enableSuperStarWeapons[2]) superStarBow = ModItems.bow[7];
        if (gr.enableEnderDragonWeapons[0]) enderDragonSword = ModItems.sword[8];
        if (gr.enableEnderDragonWeapons[1]) enderDragonBattleAxe = ModItems.battleAxe[8];
        if (gr.enableEnderDragonWeapons[2]) enderDragonBow = ModItems.bow[8];
    }

    public static void registerTCItemNames() {
        ModConfig.RegistryConfig.GlobalRegistry gr = ModConfig.RegistryConfig.global_registry;
        if (gr.enableCobaltArmor) {
            cobaltHelmet = ModItems.cobalt[0];
            cobaltChestplate = ModItems.cobalt[1];
            cobaltLeggings = ModItems.cobalt[2];
            cobaltBoots = ModItems.cobalt[3];
        }
        if (gr.enableArditeArmor) {
            arditeHelmet = ModItems.ardite[0];
            arditeChestplate = ModItems.ardite[1];
            arditeLeggings = ModItems.ardite[2];
            arditeBoots = ModItems.ardite[3];
        }
        if (gr.enableManyullynArmor) {
            manyullynHelmet = ModItems.manyullyn[0];
            manyullynChestplate = ModItems.manyullyn[1];
            manyullynLeggings = ModItems.manyullyn[2];
            manyullynBoots = ModItems.manyullyn[3];
        }
        if (gr.enablePigIronArmor) {
            pigIronHelmet = ModItems.pigIron[0];
            pigIronChestplate = ModItems.pigIron[1];
            pigIronLeggings = ModItems.pigIron[2];
            pigIronBoots = ModItems.pigIron[3];
        }
        if (gr.enableKnightSlimeArmor) {
            knightSlimeHelmet = ModItems.knightSlime[0];
            knightSlimeChestplate = ModItems.knightSlime[1];
            knightSlimeLeggings = ModItems.knightSlime[2];
            knightSlimeBoots = ModItems.knightSlime[3];
        }
    }
}