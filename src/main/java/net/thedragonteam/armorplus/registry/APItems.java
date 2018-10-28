/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.GlobalRegistry;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.global_registry;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
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
        GlobalRegistry gr = global_registry;
        if (gr.enableCoalArmor) {
            coalHelmet = coal[0];
            coalChestplate = coal[1];
            coalLeggings = coal[2];
            coalBoots = coal[3];
        }
        if (gr.enableLapisArmor) {
            lapisHelmet = lapis[0];
            lapisChestplate = lapis[1];
            lapisLeggings = lapis[2];
            lapisBoots = lapis[3];
        }
        if (gr.enableRedstoneArmor) {
            redstoneHelmet = redstone[0];
            redstoneChestplate = redstone[1];
            redstoneLeggings = redstone[2];
            redstoneBoots = redstone[3];
        }
        if (gr.enableEmeraldArmor) {
            emeraldHelmet = emerald[0];
            emeraldChestplate = emerald[1];
            emeraldLeggings = emerald[2];
            emeraldBoots = emerald[3];
        }
        if (gr.enableObsidianArmor) {
            obsidianHelmet = obsidian[0];
            obsidianChestplate = obsidian[1];
            obsidianLeggings = obsidian[2];
            obsidianBoots = obsidian[3];
        }
        if (gr.enableLavaArmor) {
            lavaHelmet = lava[0];
            lavaChestplate = lava[1];
            lavaLeggings = lava[2];
            lavaBoots = lava[3];
        }
        if (gr.enableChickenArmor) {
            chickenHelmet = chicken[0];
            chickenChestplate = chicken[1];
            chickenLeggings = chicken[2];
            chickenBoots = chicken[3];
        }
        if (gr.enableSlimeArmor) {
            slimeHelmet = slime[0];
            slimeChestplate = slime[1];
            slimeLeggings = slime[2];
            slimeBoots = slime[3];
        }
        if (gr.enableGuardianArmor) {
            guardianHelmet = guardian[0];
            guardianChestplate = guardian[1];
            guardianLeggings = guardian[2];
            guardianBoots = guardian[3];
        }
        if (gr.enableSuperStarArmor) {
            superStarHelmet = superStar[0];
            superStarChestplate = superStar[1];
            superStarLeggings = superStar[2];
            superStarBoots = superStar[3];
        }
        if (gr.enableEnderDragonArmor) {
            enderDragonHelmet = enderDragon[0];
            enderDragonChestplate = enderDragon[1];
            enderDragonLeggings = enderDragon[2];
            enderDragonBoots = enderDragon[3];
        }
        if (gr.enableTheUltimateArmor) {
            theUltimateHelmet = theUltimate[0];
            theUltimateChestplate = theUltimate[1];
            theUltimateLeggings = theUltimate[2];
            theUltimateBoots = theUltimate[3];
        }

        infusedLavaCrystal = getItemStack(itemLavaCrystal, 1);
        chainmail = getItemStack(materials, 0);
        guardianScale = getItemStack(materials, 1);
        witherBone = getItemStack(materials, 2);
        enderDragonScale = getItemStack(materials, 3);
        theUltimateMaterial = getItemStack(materials, 4);
    }

    public static void registerWeaponsA() {
        GlobalRegistry gr = global_registry;
        if (gr.enableCoalWeapons[0]) coalSword = sword[0];
        if (gr.enableCoalWeapons[1]) coalBattleAxe = battleAxe[0];
        if (gr.enableCoalWeapons[2]) coalBow = bow[0];
        if (gr.enableLapisWeapons[0]) lapisSword = sword[1];
        if (gr.enableLapisWeapons[1]) lapisBattleAxe = battleAxe[1];
        if (gr.enableLapisWeapons[2]) lapisBow = bow[1];
        if (gr.enableRedstoneWeapons[0]) redstoneSword = sword[2];
        if (gr.enableRedstoneWeapons[1]) redstoneBattleAxe = battleAxe[2];
        if (gr.enableRedstoneWeapons[2]) redstoneBow = bow[2];
        if (gr.enableEmeraldWeapons[0]) emeraldSword = sword[3];
        if (gr.enableEmeraldWeapons[1]) emeraldBattleAxe = battleAxe[3];
        if (gr.enableEmeraldWeapons[2]) emeraldBow = bow[3];
        if (gr.enableObsidianWeapons[0]) obsidianSword = sword[4];
        if (gr.enableObsidianWeapons[1]) obsidianBattleAxe = battleAxe[4];
        if (gr.enableObsidianWeapons[2]) obsidianBow = bow[4];
    }

    public static void registerWeaponsB() {
        GlobalRegistry gr = global_registry;
        if (gr.enableLavaWeapons[0]) lavaSword = sword[5];
        if (gr.enableLavaWeapons[1]) lavaBattleAxe = battleAxe[5];
        if (gr.enableLavaWeapons[2]) lavaBow = bow[5];
        if (gr.enableGuardianWeapons[0]) guardianSword = sword[6];
        if (gr.enableGuardianWeapons[1]) guardianBattleAxe = battleAxe[6];
        if (gr.enableGuardianWeapons[2]) guardianBow = bow[6];
        if (gr.enableSuperStarWeapons[0]) superStarSword = sword[7];
        if (gr.enableSuperStarWeapons[1]) superStarBattleAxe = battleAxe[7];
        if (gr.enableSuperStarWeapons[2]) superStarBow = bow[7];
        if (gr.enableEnderDragonWeapons[0]) enderDragonSword = sword[8];
        if (gr.enableEnderDragonWeapons[1]) enderDragonBattleAxe = battleAxe[8];
        if (gr.enableEnderDragonWeapons[2]) enderDragonBow = bow[8];
    }

    public static void registerTCItemNames() {
        GlobalRegistry gr = global_registry;
        if (gr.enableCobaltArmor) {
            cobaltHelmet = cobalt[0];
            cobaltChestplate = cobalt[1];
            cobaltLeggings = cobalt[2];
            cobaltBoots = cobalt[3];
        }
        if (gr.enableArditeArmor) {
            arditeHelmet = ardite[0];
            arditeChestplate = ardite[1];
            arditeLeggings = ardite[2];
            arditeBoots = ardite[3];
        }
        if (gr.enableManyullynArmor) {
            manyullynHelmet = manyullyn[0];
            manyullynChestplate = manyullyn[1];
            manyullynLeggings = manyullyn[2];
            manyullynBoots = manyullyn[3];
        }
        if (gr.enablePigIronArmor) {
            pigIronHelmet = pigIron[0];
            pigIronChestplate = pigIron[1];
            pigIronLeggings = pigIron[2];
            pigIronBoots = pigIron[3];
        }
        if (gr.enableKnightSlimeArmor) {
            knightSlimeHelmet = knightSlime[0];
            knightSlimeChestplate = knightSlime[1];
            knightSlimeLeggings = knightSlime[2];
            knightSlimeBoots = knightSlime[3];
        }
    }
}