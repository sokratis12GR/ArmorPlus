/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.util.Utils.isNotNull;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
//TODO: FIX that...
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

    public APItems() {
    }

    @SuppressWarnings({"Duplicates", "ConstantConditions"})
    public static void registerItemNames() {
        if (enableCoalArmor) {
            coalHelmet = coal[0];
            coalChestplate = coal[1];
            coalLeggings = coal[2];
            coalBoots = coal[3];
        }
        if (enableLapisArmor) {
            lapisHelmet = lapis[0];
            lapisChestplate = lapis[1];
            lapisLeggings = lapis[2];
            lapisBoots = lapis[3];
        }
        if (enableRedstoneArmor) {
            redstoneHelmet = redstone[0];
            redstoneChestplate = redstone[1];
            redstoneLeggings = redstone[2];
            redstoneBoots = redstone[3];
        }
        if (enableEmeraldArmor) {
            emeraldHelmet = emerald[0];
            emeraldChestplate = emerald[1];
            emeraldLeggings = emerald[2];
            emeraldBoots = emerald[3];
        }
        if (enableObsidianArmor) {
            obsidianHelmet = obsidian[0];
            obsidianChestplate = obsidian[1];
            obsidianLeggings = obsidian[2];
            obsidianBoots = obsidian[3];
        }
        if (enableLavaArmor) {
            lavaHelmet = lava[0];
            lavaChestplate = lava[1];
            lavaLeggings = lava[2];
            lavaBoots = lava[3];
        }
        if (enableChickenArmor) {
            chickenHelmet = chicken[0];
            chickenChestplate = chicken[1];
            chickenLeggings = chicken[2];
            chickenBoots = chicken[3];
        }
        if (enableSlimeArmor) {
            slimeHelmet = slime[0];
            slimeChestplate = slime[1];
            slimeLeggings = slime[2];
            slimeBoots = slime[3];
        }
        if (enableGuardianArmor) {
            guardianHelmet = guardian[0];
            guardianChestplate = guardian[1];
            guardianLeggings = guardian[2];
            guardianBoots = guardian[3];
        }
        if (enableSuperStarArmor) {
            superStarHelmet = superStar[0];
            superStarChestplate = superStar[1];
            superStarLeggings = superStar[2];
            superStarBoots = superStar[3];
        }
        if (enableEnderDragonArmor) {
            enderDragonHelmet = enderDragon[0];
            enderDragonChestplate = enderDragon[1];
            enderDragonLeggings = enderDragon[2];
            enderDragonBoots = enderDragon[3];
        }
        if (enableTheUltimateArmor) {
            theUltimateHelmet = theUltimate[0];
            theUltimateChestplate = theUltimate[1];
            theUltimateLeggings = theUltimate[2];
            theUltimateBoots = theUltimate[3];
        }
        if (enableCobaltArmor) {
            cobaltHelmet = cobalt[0];
            cobaltChestplate = cobalt[1];
            cobaltLeggings = cobalt[2];
            cobaltBoots = cobalt[3];
        }
        if (enableArditeArmor) {
            arditeHelmet = ardite[0];
            arditeChestplate = ardite[1];
            arditeLeggings = ardite[2];
            arditeBoots = ardite[3];
        }
        if (enableManyullynArmor) {
            manyullynHelmet = manyullyn[0];
            manyullynChestplate = manyullyn[1];
            manyullynLeggings = manyullyn[2];
            manyullynBoots = manyullyn[3];
        }
        if (enablePigIronArmor) {
            pigIronHelmet = pigIron[0];
            pigIronChestplate = pigIron[1];
            pigIronLeggings = pigIron[2];
            pigIronBoots = pigIron[3];
        }
        if (enableKnightSlimeArmor) {
            knightSlimeHelmet = knightSlime[0];
            knightSlimeChestplate = knightSlime[1];
            knightSlimeLeggings = knightSlime[2];
            knightSlimeBoots = knightSlime[3];
        }
        if (enabled(enableCoalWeapons[0], coalSword)) coalSword = sword[0];
        if (enabled(enableCoalWeapons[1], coalBattleAxe)) coalBattleAxe = battleAxe[0];
        if (enabled(enableCoalWeapons[2], coalBow)) coalBow = bow[0];
        if (enabled(enableLapisWeapons[0], lapisSword)) lapisSword = sword[1];
        if (enabled(enableLapisWeapons[1], lapisBattleAxe)) lapisBattleAxe = battleAxe[1];
        if (enabled(enableLapisWeapons[2], lapisBow)) lapisBow = bow[1];
        if (enabled(enableRedstoneWeapons[0], redstoneSword)) redstoneSword = sword[2];
        if (enabled(enableRedstoneWeapons[1], redstoneBattleAxe)) redstoneBattleAxe = battleAxe[2];
        if (enabled(enableRedstoneWeapons[2], redstoneBow)) redstoneBow = bow[2];
        if (enabled(enableEmeraldWeapons[0], emeraldSword)) emeraldSword = sword[3];
        if (enabled(enableEmeraldWeapons[1], emeraldBattleAxe)) emeraldBattleAxe = battleAxe[3];
        if (enabled(enableEmeraldWeapons[2], emeraldBow)) emeraldBow = bow[3];
        if (enabled(enableObsidianWeapons[0], obsidianSword)) obsidianSword = sword[4];
        if (enabled(enableObsidianWeapons[1], obsidianBattleAxe)) obsidianBattleAxe = battleAxe[4];
        if (enabled(enableObsidianWeapons[2], obsidianBow)) obsidianBow = bow[4];
        if (enabled(enableLavaWeapons[0], lavaSword)) lavaSword = sword[5];
        if (enabled(enableLavaWeapons[1], lavaBattleAxe)) lavaBattleAxe = battleAxe[5];
        if (enabled(enableLavaWeapons[2], lavaBow)) lavaBow = bow[5];
        if (enabled(enableGuardianWeapons[0], guardianSword)) guardianSword = sword[6];
        if (enabled(enableGuardianWeapons[1], guardianBattleAxe)) guardianBattleAxe = battleAxe[6];
        if (enabled(enableGuardianWeapons[2], guardianBow)) guardianBow = bow[6];
        if (enabled(enableSuperStarWeapons[0], superStarSword)) superStarSword = sword[7];
        if (enabled(enableSuperStarWeapons[1], superStarBattleAxe)) superStarBattleAxe = battleAxe[7];
        if (enabled(enableSuperStarWeapons[2], superStarBow)) superStarBow = bow[7];
        if (enabled(enableEnderDragonWeapons[0], enderDragonSword)) enderDragonSword = sword[8];
        if (enabled(enableEnderDragonWeapons[1], enderDragonBattleAxe)) enderDragonBattleAxe = battleAxe[8];
        if (enabled(enableEnderDragonWeapons[2], enderDragonBow)) enderDragonBow = bow[8];

        infusedLavaCrystal = getItemStack(lavaCrystal, 1);
        chainmail = getItemStack(materials, 0);
        guardianScale = getItemStack(materials, 1);
        witherBone = getItemStack(materials, 2);
        enderDragonScale = getItemStack(materials, 3);
        theUltimateMaterial = getItemStack(materials, 4);
    }

    private static boolean enabled(boolean isEnabled, Item items) {
        return isEnabled && isNotNull(items);
    }

}