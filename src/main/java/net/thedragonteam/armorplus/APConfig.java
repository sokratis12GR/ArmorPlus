/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.thedragonlib.config.ModConfigProperty;

import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.*;

/**
 * net.thedragonteam.armorplus
 * ArmorPlus created by sokratis12GR on 7/26/2016 4:47 PM.
 * - TheDragonTeam
 */
public class APConfig {
    //Weapons.*.Registry
    @ModConfigProperty(category = "Weapons.Coal.Registry", name = "coalWeaponItemNameColor", comment = "Set the color name the Coal Weapons will have")
    public static String coalWeaponItemNameColor = "gray";
    @ModConfigProperty(category = "Weapons.Lapis.Registry", name = "lapisWeaponItemNameColor", comment = "Set the color name the Lapis Weapons will have")
    public static String lapisWeaponItemNameColor = "dark_blue";
    @ModConfigProperty(category = "Weapons.Redstone.Registry", name = "redstoneWeaponItemNameColor", comment = "Set the color name the Redstone Weapons will have")
    public static String redstoneWeaponItemNameColor = "dark_red";
    @ModConfigProperty(category = "Weapons.Emerald.Registry", name = "emeraldWeaponItemNameColor", comment = "Set the color name the Emerald Weapons will have")
    public static String emeraldWeaponItemNameColor = "dark_green";
    @ModConfigProperty(category = "Weapons.Obsidian.Registry", name = "obsidianWeaponItemNameColor", comment = "Set the color name the Obsidian Weapons will have")
    public static String obsidianWeaponItemNameColor = "dark_gray";
    @ModConfigProperty(category = "Weapons.Lava.Registry", name = "lavaWeaponItemNameColor", comment = "Set the color name the Lava Weapons will have")
    public static String lavaWeaponItemNameColor = "gold";
    @ModConfigProperty(category = "Weapons.SuperStar.Registry", name = "superStarWeaponItemNameColor", comment = "Set the color name the Super Star Weapons will have")
    public static String superStarWeaponItemNameColor = "white";
    @ModConfigProperty(category = "Weapons.EnderDragon.Registry", name = "enderDragonWeaponItemNameColor", comment = "Set the color name the Ender Dragon Weapons will have")
    public static String enderDragonWeaponItemNameColor = "dark_purple";
    @ModConfigProperty(category = "Weapons.Guardian.Registry", name = "guardianWeaponItemNameColor", comment = "Set the color name the Guardian Weapons will have")
    public static String guardianWeaponItemNameColor = "aqua";
    @ModConfigProperty(category = "Weapons.RF.Registry", name = "rfWeaponItemNameColor", comment = "Set the color name the RF Weapons will have")
    public static String rfWeaponItemNameColor = "dark_red";
    @ModConfigProperty(category = "Weapons.Tesla.Registry", name = "teslaWeaponItemNameColor", comment = "Set the color name the Tesla Weapons will have")
    public static String teslaWeaponItemNameColor = "dark_aqua";
    @ModConfigProperty(category = "Weapons.Coal.Registry", name = "enableCoalSword", comment = "Enable/Disable the Coal Sword from the Game")
    public static boolean enableCoalSword = true;
    @ModConfigProperty(category = "Weapons.Coal.Registry", name = "enableCoalBattleAxe", comment = "Enable/Disable the Coal BattleAxe from the Game")
    public static boolean enableCoalBattleAxe = true;
    @ModConfigProperty(category = "Weapons.Coal.Registry", name = "enableCoalBow", comment = "Enable/Disable the Coal Bow from the Game")
    public static boolean enableCoalBow = true;
    @ModConfigProperty(category = "Weapons.Lapis.Registry", name = "enableLapisSword", comment = "Enable/Disable the Lapis Sword from the Game")
    public static boolean enableLapisSword = true;
    @ModConfigProperty(category = "Weapons.Lapis.Registry", name = "enableLapisSword", comment = "Enable/Disable the Lapis BattleAxe from the Game")
    public static boolean enableLapisBattleAxe = true;
    @ModConfigProperty(category = "Weapons.Lapis.Registry", name = "enableLapisSword", comment = "Enable/Disable the Lapis Bow from the Game")
    public static boolean enableLapisBow = true;
    @ModConfigProperty(category = "Weapons.Redstone.Registry", name = "enableRedstoneSword", comment = "Enable/Disable the Redstone Sword from the Game")
    public static boolean enableRedstoneSword = true;
    @ModConfigProperty(category = "Weapons.Redstone.Registry", name = "enableRedstoneBattleAxe", comment = "Enable/Disable the Redstone BattleAxe from the Game")
    public static boolean enableRedstoneBattleAxe = true;
    @ModConfigProperty(category = "Weapons.Redstone.Registry", name = "enableRedstoneBow", comment = "Enable/Disable the Redstone Bow from the Game")
    public static boolean enableRedstoneBow = true;
    @ModConfigProperty(category = "Weapons.Emerald.Registry", name = "enableEmeraldSword", comment = "Enable/Disable the Emerald Sword from the Game")
    public static boolean enableEmeraldSword = true;
    @ModConfigProperty(category = "Weapons.Emerald.Registry", name = "enableEmeraldBattleAxe", comment = "Enable/Disable the Emerald Battle from the Game")
    public static boolean enableEmeraldBattleAxe = true;
    @ModConfigProperty(category = "Weapons.Emerald.Registry", name = "enableEmeraldBow", comment = "Enable/Disable the Emerald Bow from the Game")
    public static boolean enableEmeraldBow = true;
    @ModConfigProperty(category = "Weapons.Obsidian.Registry", name = "enableObsidianSword", comment = "Enable/Disable the Obsidian Sword from the Game")
    public static boolean enableObsidianSword = true;
    @ModConfigProperty(category = "Weapons.Obsidian.Registry", name = "enableObsidianBattleAxe", comment = "Enable/Disable the Obsidian BattleAxe from the Game")
    public static boolean enableObsidianBattleAxe = true;
    @ModConfigProperty(category = "Weapons.Obsidian.Registry", name = "enableObsidianBow", comment = "Enable/Disable the Obsidian Bow from the Game")
    public static boolean enableObsidianBow = true;
    @ModConfigProperty(category = "Weapons.Lava.Registry", name = "enableLavaSword", comment = "Enable/Disable the Lava Sword from the Game")
    public static boolean enableLavaSword = true;
    @ModConfigProperty(category = "Weapons.Lava.Registry", name = "enableLavaBattleAxe", comment = "Enable/Disable the Lava BattleAxe from the Game")
    public static boolean enableLavaBattleAxe = true;
    @ModConfigProperty(category = "Weapons.Lava.Registry", name = "enableLavaBow", comment = "Enable/Disable the Lava Bow from the Game")
    public static boolean enableLavaBow = true;
    @ModConfigProperty(category = "Weapons.Guardian.Registry", name = "enableGuardianSword", comment = "Enable/Disable the Guardian Sword from the Game")
    public static boolean enableGuardianSword = true;
    @ModConfigProperty(category = "Weapons.Guardian.Registry", name = "enableGuardianBattleAxe", comment = "Enable/Disable the Guardian BattleAxe from the Game")
    public static boolean enableGuardianBattleAxe = true;
    @ModConfigProperty(category = "Weapons.Guardian.Registry", name = "enableGuardianBow", comment = "Enable/Disable the Guardian Bow from the Game")
    public static boolean enableGuardianBow = true;
    @ModConfigProperty(category = "Weapons.SuperStar.Registry", name = "enableSuperStarSword", comment = "Enable/Disable the Super Star Sword from the Game")
    public static boolean enableSuperStarSword = true;
    @ModConfigProperty(category = "Weapons.SuperStar.Registry", name = "enableSuperStarBattleAxe", comment = "Enable/Disable the Super Star BattleAxe from the Game")
    public static boolean enableSuperStarBattleAxe = true;
    @ModConfigProperty(category = "Weapons.SuperStar.Registry", name = "enableSuperStarBow", comment = "Enable/Disable the Super Star Bow from the Game")
    public static boolean enableSuperStarBow = true;
    @ModConfigProperty(category = "Weapons.EnderDragon.Registry", name = "enableEnderDragonSword", comment = "Enable/Disable the Ender Dragon Sword from the Game")
    public static boolean enableEnderDragonSword = true;
    @ModConfigProperty(category = "Weapons.EnderDragon.Registry", name = "enableEnderDragonBattleAxe", comment = "Enable/Disable the Ender Dragon BattleAxe from the Game")
    public static boolean enableEnderDragonBattleAxe = true;
    @ModConfigProperty(category = "Weapons.EnderDragon.Registry", name = "enableEnderDragonBow", comment = "Enable/Disable the Ender Dragon Bow from the Game")
    public static boolean enableEnderDragonBow = true;

    //Weapons.*.Sword
    @ModConfigProperty(category = "Weapons.Coal.Sword", name = "coalSwordDamage", comment = "Set the amount of damage the Coal Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double coalSwordDamage = 0.5;
    @ModConfigProperty(category = "Weapons.Lapis.Sword", name = "lapisSwordDamage", comment = "Set the amount of damage the Lapis Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double lapisSwordDamage = 1.0;
    @ModConfigProperty(category = "Weapons.Redstone.Sword", name = "redstoneSwordDamage", comment = "Set the amount of damage the Redstone Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double redstoneSwordDamage = 1.0;
    @ModConfigProperty(category = "Weapons.Emerald.Sword", name = "emeraldSwordDamage", comment = "Set the amount of damage the Emerald Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double emeraldSwordDamage = 1.5;
    @ModConfigProperty(category = "Weapons.Obsidian.Sword", name = "obsidianSwordDamage", comment = "Set the amount of damage the Obsidian Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double obsidianSwordDamage = 4.0;
    @ModConfigProperty(category = "Weapons.Lava.Sword", name = "lavaSwordDamage", comment = "Set the amount of damage the Lava Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double lavaSwordDamage = 4.5;
    @ModConfigProperty(category = "Weapons.Guardian.Sword", name = "guardianSwordDamage", comment = "Set the amount of damage the Guardian Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double guardianSwordDamage = 6.0;
    @ModConfigProperty(category = "Weapons.SuperStar.Sword", name = "superStarSwordDamage", comment = "Set the amount of damage the Super Star Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double superStarSwordDamage = 7.0;
    @ModConfigProperty(category = "Weapons.EnderDragon.Sword", name = "enderDragonSwordDamage", comment = "Set the amount of damage the Ender Dragon Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double enderDragonSwordDamage = 8.0;
    @ModConfigProperty(category = "Weapons.Coal.Sword", name = "coalSwordDurability", comment = "Set the amount of durability the Coal Sword have")
    public static int coalSwordDurability = 59;
    @ModConfigProperty(category = "Weapons.Lapis.Sword", name = "lapisSwordDurability", comment = "Set the amount of durability the Lapis Sword have")
    public static int lapisSwordDurability = 250;
    @ModConfigProperty(category = "Weapons.Redstone.Sword", name = "redstoneSwordDurability", comment = "Set the amount of durability the Redstone Sword have")
    public static int redstoneSwordDurability = 200;
    @ModConfigProperty(category = "Weapons.Emerald.Sword", name = "emeraldSwordDurability", comment = "Set the amount of durability the Emerald Sword have")
    public static int emeraldSwordDurability = 1561;
    @ModConfigProperty(category = "Weapons.Obsidian.Sword", name = "obsidianSwordDurability", comment = "Set the amount of durability the Obsidian Sword have")
    public static int obsidianSwordDurability = 1500;
    @ModConfigProperty(category = "Weapons.Lava.Sword", name = "lavaSwordDurability", comment = "Set the amount of durability the Lava Sword have")
    public static int lavaSwordDurability = 1750;
    @ModConfigProperty(category = "Weapons.Guardian.Sword", name = "guardianSwordDurability", comment = "Set the amount of durability the Guardian Sword have")
    public static int guardianSwordDurability = 1800;
    @ModConfigProperty(category = "Weapons.SuperStar.Sword", name = "superStarSwordDurability", comment = "Set the amount of durability the Super Star Sword have")
    public static int superStarSwordDurability = 1950;
    @ModConfigProperty(category = "Weapons.EnderDragon.Sword", name = "enderDragonSwordDurability", comment = "Set the amount of durability the Ender Dragon Sword have")
    public static int enderDragonSwordDurability = 2310;

    //Weapons.*.Effects
    @ModConfigProperty(category = "Weapons.Coal.Effects", name = "coalWeaponsEffectLevel", comment = "Set the level of the Effect by the Coal Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int coalWeaponsEffectLevel = 0;
    @ModConfigProperty(category = "Weapons.Lapis.Effects", name = "lapisWeaponsEffectLevel", comment = "Set the level of the Effect by the Lapis Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int lapisWeaponsEffectLevel = 1;
    @ModConfigProperty(category = "Weapons.Redstone.Effects", name = "redstoneWeaponsEffectLevel", comment = "Set the level of the Effect by the Redstone Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int redstoneWeaponsEffectLevel = 1;
    @ModConfigProperty(category = "Weapons.Emerald.Effects", name = "emeraldWeaponsEffectLevel", comment = "Set the level of the Effect by the Emerald Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int emeraldWeaponsEffectLevel = 1;
    @ModConfigProperty(category = "Weapons.Obsidian.Effects", name = "obsidianWeaponsEffectLevel", comment = "Set the level of the Effect by the Obsidian Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int obsidianWeaponsEffectLevel = 1;
    @ModConfigProperty(category = "Weapons.Guardian.Effects", name = "guardianWeaponsEffectLevel", comment = "Set the level of the Effect by the Guardian Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int guardianWeaponsEffectLevel = 1;
    @ModConfigProperty(category = "Weapons.SuperStar.Effects", name = "superStarWeaponsEffectLevel", comment = "Set the level of the Effect by the Super Star Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int superStarWeaponsEffectLevel = 1;
    @ModConfigProperty(category = "Weapons.SuperStar.Effects", name = "enderDragonWeaponsEffectLevel", comment = "Set the level of the Effect by the Super Star Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int enderDragonWeaponsEffectLevel = 3;

    @ModConfigProperty(category = "Weapons.Coal.Effects", name = "coalWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Coal Weapons will have (to disable the effect set the effects \'false\')")
    public static String coalWeaponsAddPotionEffect = "blindness";
    @ModConfigProperty(category = "Weapons.Lapis.Effects", name = "lapisWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Lapis Weapons will have (to disable the effect set the effects \'false\')")
    public static String lapisWeaponsAddPotionEffect = "nausea";
    @ModConfigProperty(category = "Weapons.Redstone.Effects", name = "redstoneWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Redstone Weapons will have (to disable the effect set the effects \'false\')")
    public static String redstoneWeaponsAddPotionEffect = "slowness";
    @ModConfigProperty(category = "Weapons.Emerald.Effects", name = "emeraldWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Emerald Weapons will have (to disable the effect set the effects \'false\')")
    public static String emeraldWeaponsAddPotionEffect = "mining_fatigue";
    @ModConfigProperty(category = "Weapons.Obsidian.Effects", name = "obsidianWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Obsidian Weapons will have (to disable the effect set the effects \'false\')")
    public static String obsidianWeaponsAddPotionEffect = "weakness";
    @ModConfigProperty(category = "Weapons.Guardian.Effects", name = "guardianWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Guardian Weapons will have (to disable the effect set the effects \'false\')")
    public static String guardianWeaponsAddPotionEffect = "nausea";
    @ModConfigProperty(category = "Weapons.SuperStar.Effects", name = "superStarWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Super Star Weapons will have (to disable the effect set the effects \'false\')")
    public static String superStarWeaponsAddPotionEffect = "wither";
    @ModConfigProperty(category = "Weapons.SuperStar.Effects", name = "enderDragonWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Super Star Weapons will have (to disable the effect set the effects \'false\')")
    public static String enderDragonWeaponsAddPotionEffect = "wither";
    @ModConfigProperty(category = "Weapons.Coal.Effects", name = "enableCoalWeaponsEffects", comment = "Enable/Disable the Potion Effect the Coal Weapons will have")
    public static boolean enableCoalWeaponsEffects = true;
    @ModConfigProperty(category = "Weapons.Lapis.Effects", name = "enableLapisWeaponsEffects", comment = "Enable/Disable the Potion Effect the Lapis Weapons will have")
    public static boolean enableLapisWeaponsEffects = true;
    @ModConfigProperty(category = "Weapons.Redstone.Effects", name = "enableRedstoneWeaponsEffects", comment = "Enable/Disable the Potion Effect the Redstone Weapons will have")
    public static boolean enableRedstoneWeaponsEffects = true;
    @ModConfigProperty(category = "Weapons.Emerald.Effects", name = "enableEmeraldWeaponsEffects", comment = "Enable/Disable the Potion Effect the Emerald Weapons will have")
    public static boolean enableEmeraldWeaponsEffects = true;
    @ModConfigProperty(category = "Weapons.Obsidian.Effects", name = "enableObsidianWeaponsEffects", comment = "Enable/Disable the Potion Effect the Obsidian Weapons will have")
    public static boolean enableObsidianWeaponsEffects = true;
    @ModConfigProperty(category = "Weapons.Guardian.Effects", name = "enableGuardianWeaponsEffects", comment = "Enable/Disable the Potion Effect the Guardian Weapons will have")
    public static boolean enableGuardianWeaponsEffects = true;
    @ModConfigProperty(category = "Weapons.SuperStar.Effects", name = "enableSuperStarWeaponsEffects", comment = "Enable/Disable the Potion Effect the Super Star Weapons will have")
    public static boolean enableSuperStarWeaponsEffects = true;
    @ModConfigProperty(category = "Weapons.SuperStar.Effects", name = "enableEnderDragonWeaponsEffects", comment = "Enable/Disable the Potion Effect the Super Star Weapons will have")
    public static boolean enableEnderDragonWeaponsEffects = true;

    @ModConfigProperty(category = "Weapons.Coal.BattleAxe", name = "coalBattleAxeDamage", comment = "Set the amount of damage the Coal BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double coalBattleAxeDamage = 2.5;
    @ModConfigProperty(category = "Weapons.Lapis.BattleAxe", name = "lapisBattleAxeDamage", comment = "Set the amount of damage the Lapis BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double lapisBattleAxeDamage = 3.0;
    @ModConfigProperty(category = "Weapons.Redstone.BattleAxe", name = "redstoneBattleAxeDamage", comment = "Set the amount of damage the Redstone BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double redstoneBattleAxeDamage = 3.0;
    @ModConfigProperty(category = "Weapons.Emerald.BattleAxe", name = "emeraldBattleAxeDamage", comment = "Set the amount of damage the Emerald BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double emeraldBattleAxeDamage = 3.5;
    @ModConfigProperty(category = "Weapons.Obsidian.BattleAxe", name = "obsidianBattleAxeDamage", comment = "Set the amount of damage the Obsidian BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double obsidianBattleAxeDamage = 6.0;
    @ModConfigProperty(category = "Weapons.Lava.BattleAxe", name = "lavaBattleAxeDamage", comment = "Set the amount of damage the Lava BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double lavaBattleAxeDamage = 6.5;
    @ModConfigProperty(category = "Weapons.Guardian.BattleAxe", name = "guardianBattleAxeDamage", comment = "Set the amount of damage the Guardian BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double guardianBattleAxeDamage = 7.0;
    @ModConfigProperty(category = "Weapons.SuperStar.BattleAxe", name = "superStarBattleAxeDamage", comment = "Set the amount of damage the Super Star BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double superStarBattleAxeDamage = 8.0;
    @ModConfigProperty(category = "Weapons.EnderDragon.BattleAxe", name = "enderDragonBattleAxeDamage", comment = "Set the amount of damage the Ender Dragon BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double enderDragonBattleAxeDamage = 10.0;
    @ModConfigProperty(category = "Weapons.Coal.BattleAxe", name = "coalBattleAxeDurability", comment = "Set the amount of durability the Coal BattleAxe have")
    public static int coalBattleAxeDurability = 59;
    @ModConfigProperty(category = "Weapons.Lapis.BattleAxe", name = "lapisBattleAxeDurability", comment = "Set the amount of durability the Lapis BattleAxe have")
    public static int lapisBattleAxeDurability = 250;
    @ModConfigProperty(category = "Weapons.Redstone.BattleAxe", name = "redstoneBattleAxeDurability", comment = "Set the amount of durability the Redstone BattleAxe have")
    public static int redstoneBattleAxeDurability = 200;
    @ModConfigProperty(category = "Weapons.Emerald.BattleAxe", name = "emeraldBattleAxeDurability", comment = "Set the amount of durability the Emerald BattleAxe have")
    public static int emeraldBattleAxeDurability = 1561;
    @ModConfigProperty(category = "Weapons.Obsidian.BattleAxe", name = "obsidianBattleAxeDurability", comment = "Set the amount of durability the Obsidian BattleAxe have")
    public static int obsidianBattleAxeDurability = 1500;
    @ModConfigProperty(category = "Weapons.Lava.BattleAxe", name = "lavaBattleAxeDurability", comment = "Set the amount of durability the Lava BattleAxe have")
    public static int lavaBattleAxeDurability = 1750;
    @ModConfigProperty(category = "Weapons.Guardian.BattleAxe", name = "guardianBattleAxeDurability", comment = "Set the amount of durability the Guardian BattleAxe have")
    public static int guardianBattleAxeDurability = 1800;
    @ModConfigProperty(category = "Weapons.SuperStar.BattleAxe", name = "superStarBattleAxeDurability", comment = "Set the amount of durability the Super Star BattleAxe have")
    public static int superStarBattleAxeDurability = 1950;
    @ModConfigProperty(category = "Weapons.EnderDragon.BattleAxe", name = "enderDragonBattleAxeDurability", comment = "Set the amount of durability the Ender Dragon BattleAxe have")
    public static int enderDragonBattleAxeDurability = 2310;

    //Weapons.*.Bow
    @ModConfigProperty(category = "Weapons.Coal.Bow", name = "coalBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Coal Bow will do")
    public static double coalBowArrowBonusDamage = -1.0;
    @ModConfigProperty(category = "Weapons.Lapis.Bow", name = "lapisBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Lapis Bow will do")
    public static double lapisBowArrowBonusDamage = -0.5;
    @ModConfigProperty(category = "Weapons.Redstone.Bow", name = "redstoneBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Redstone Bow will do")
    public static double redstoneBowArrowBonusDamage = -0.5;
    @ModConfigProperty(category = "Weapons.Emerald.Bow", name = "emeraldBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Emerald Bow will do")
    public static double emeraldBowArrowBonusDamage = 0.0;
    @ModConfigProperty(category = "Weapons.Obsidian.Bow", name = "obsidianBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Obsidian Bow will do")
    public static double obsidianBowArrowBonusDamage = 0.5;
    @ModConfigProperty(category = "Weapons.Lava.Bow", name = "lavaBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Lava Bow will do")
    public static double lavaBowArrowBonusDamage = 1.0;
    @ModConfigProperty(category = "Weapons.Guardian.Bow", name = "guardianBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Guardian Bow will do")
    public static double guardianBowArrowBonusDamage = 1.5;
    @ModConfigProperty(category = "Weapons.SuperStar.Bow", name = "superStarBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Super Star Bow will do")
    public static double superStarBowArrowBonusDamage = 2.5;
    @ModConfigProperty(category = "Weapons.EnderDragon.Bow", name = "enderDragonBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Ender Dragon Bow will do")
    public static double enderDragonBowArrowBonusDamage = 3.5;
    @ModConfigProperty(category = "Weapons.Coal.Bow", name = "coalBowDurability", comment = "Set the amount of durability the Coal Bow have")
    public static int coalBowDurability = 59;
    @ModConfigProperty(category = "Weapons.Lapis.Bow", name = "lapisBowDurability", comment = "Set the amount of durability the Lapis Bow have")
    public static int lapisBowDurability = 250;
    @ModConfigProperty(category = "Weapons.Redstone.Bow", name = "redstoneBowDurability", comment = "Set the amount of durability the Redstone Bow have")
    public static int redstoneBowDurability = 200;
    @ModConfigProperty(category = "Weapons.Emerald.Bow", name = "emeraldBowDurability", comment = "Set the amount of durability the Emerald Bow have")
    public static int emeraldBowDurability = 1561;
    @ModConfigProperty(category = "Weapons.Obsidian.Bow", name = "obsidianBowDurability", comment = "Set the amount of durability the Obsidian Bow have")
    public static int obsidianBowDurability = 1500;
    @ModConfigProperty(category = "Weapons.Lava.Bow", name = "lavaBowDurability", comment = "Set the amount of durability the Lava Bow have")
    public static int lavaBowDurability = 1750;
    @ModConfigProperty(category = "Weapons.Guardian.Bow", name = "guardianBowDurability", comment = "Set the amount of durability the Guardian Bow have")
    public static int guardianBowDurability = 1800;
    @ModConfigProperty(category = "Weapons.SuperStar.Bow", name = "superStarBowDurability", comment = "Set the amount of durability the Super Star Bow have")
    public static int superStarBowDurability = 1950;
    @ModConfigProperty(category = "Weapons.EnderDragon.Bow", name = "enderDragonBowDurability", comment = "Set the amount of durability the Ender Dragon Bow have")
    public static int enderDragonBowDurability = 2310;

    //Armors.*.Effects
    @ModConfigProperty(category = "Armors.CoalArmor.Effects", name = "coalArmorAddPotionEffect", comment = "Adds the Potion Effect the Coal Armor will have (to disable the effect set the effects \'false\')")
    public static String coalArmorAddPotionEffect = "night_vision";
    @ModConfigProperty(category = "Armors.LapisArmor.Effects", name = "lapisArmorAddPotionEffect", comment = "Adds the Potion Effect the Lapis Armor will have (to disable the effect set the effects \'false\')")
    public static String lapisArmorAddPotionEffect = "water_breathing";
    @ModConfigProperty(category = "Armors.RedstoneArmor.Effects", name = "redstoneArmorAddPotionEffect", comment = "Adds the Potion Effect the Redstone Armor will have (to disable the effect set the effects \'false\')")
    public static String redstoneArmorAddPotionEffect = "speed";
    @ModConfigProperty(category = "Armors.EmeraldArmor.Effects", name = "emeraldArmorAddPotionEffect", comment = "Adds the Potion Effect the Emerald Armor will have (to disable the effect set the effects \'false\')")
    public static String emeraldArmorAddPotionEffect = "haste";
    @ModConfigProperty(category = "Armors.ObsidianArmor.Effects", name = "obsidianArmorAddPotionEffect", comment = "Adds the Potion Effect the Obsidian Armor will have (to disable the effect set the effects \'false\')")
    public static String obsidianArmorAddPotionEffect = "resistance";
    @ModConfigProperty(category = "Armors.LavaArmor.Effects", name = "lavaArmorAddPotionEffect", comment = "Adds the Potion Effect the Lava Armor will have (to disable the effect set the effects \'false\')")
    public static String lavaArmorAddPotionEffect = "fire_resistance";
    @ModConfigProperty(category = "Armors.GuardianArmor.Effects", name = "guardianArmorAddPotionEffect", comment = "Adds the Potion Effect the Guardian Armor will have (to disable the effect set the effects \'false\')")
    public static String guardianArmorAddPotionEffect = "water_breathing";
    @ModConfigProperty(category = "Armors.SuperStarArmor.Effects", name = "superStarArmorAddPotionEffect", comment = "Adds the Potion Effect the Super Star Armor will have (to disable the effect set the effects \'false\')")
    public static String superStarArmorAddPotionEffect = "regeneration";
    @ModConfigProperty(category = "Armors.SuperStarArmor.Effects", name = "superStarArmorRemovePotionEffect", comment = "The Potion Effect that the Super Star Armor will be Removing")
    public static String superStarArmorRemovePotionEffect = "wither";
    @ModConfigProperty(category = "Armors.EnderDragonArmor.Effects", name = "enderDragonArmorRemovePotionEffect", comment = "The Potion Effect that the Ender Dragon Armor will be Removing")
    public static String enderDragonArmorRemovePotionEffect = "wither";
    @ModConfigProperty(category = "Armors.TheUltimateArmor.Effects", name = "theUltimateArmorAddPotionEffect", comment = "Adds the Potion Effect the Ultimate Armor will have (to disable the effect set the effects \'false\')")
    public static String[] theUltimateArmorAddPotionEffects = new String[]{"saturation", "regeneration", "water_breathing"};
    @ModConfigProperty(category = "Armors.TheUltimateArmor.Effects", name = "theUltimateArmorRemovePotionEffect", comment = "The Potion Effect that the Ultimate Dragon Armor will be Removing")
    public static String theUltimateArmorRemovePotionEffect = "wither";
    @ModConfigProperty(category = "Armors.ArditeArmor.Effects", name = "arditeArmorAddPotionEffect", comment = "Adds the Potion Effect the Ardite Armor will have (to disable the effect set the effects \'false\')")
    public static String arditeArmorAddPotionEffect = "fire_resistance";
    @ModConfigProperty(category = "Armors.CobaltArmor.Effects", name = "cobaltArmorAddPotionEffect", comment = "Adds the Potion Effect the Cobalt Armor will have (to disable the effect set the effects \'false\')")
    public static String cobaltArmorAddPotionEffect = "haste";
    @ModConfigProperty(category = "Armors.ManyullynArmor.Effects", name = "manyullynArmorAddPotionEffect", comment = "Adds the Potion Effect the Manyullyn Armor will have (to disable the effect set the effects \'false\')")
    public static String manyullynArmorAddPotionEffect = "strength";
    @ModConfigProperty(category = "Armors.PigIronArmor.Effects", name = "pigIronArmorAddPotionEffect", comment = "Adds the Potion Effect the Pig Iron Armor will have (to disable the effect set the effects \'false\')")
    public static String pigIronArmorAddPotionEffect = "saturation";
    @ModConfigProperty(category = "Armors.KnightSlimeArmor.Effects", name = "knightSlimeArmorAddPotionEffect", comment = "Adds the Potion Effect the Knight Slime Armor will have (to disable the effect set the effects \'false\')")
    public static String knightSlimeArmorAddPotionEffect = "haste";
    @ModConfigProperty(category = "Armors.ChickenArmor.Effects", name = "chickenArmorAddPotionEffect", comment = "Adds the Potion Effect the Chicken Armor will have (to disable the effect set the effects \'false\')")
    public static String chickenArmorAddPotionEffect = "speed";
    @ModConfigProperty(category = "Armors.SlimeArmor.Effects", name = "slimeArmorAddPotionEffect", comment = "Adds the Potion Effect the Slime Armor will have (to disable the effect set the effects \'false\')")
    public static String slimeArmorAddPotionEffect = "jump_boost";
    //Armors.*.Registry
    @ModConfigProperty(category = "Armors.CoalArmor.Registry", name = "coalArmorItemNameColor", comment = "Set the color name the Coal Armor will have")
    public static String coalArmorItemNameColor = "gray";
    @ModConfigProperty(category = "Armors.LapisArmor.Registry", name = "lapisArmorItemNameColor", comment = "Set the color name the Lapis Armor will have")
    public static String lapisArmorItemNameColor = "dark_blue";
    @ModConfigProperty(category = "Armors.RedstoneArmor.Registry", name = "redstoneArmorItemNameColor", comment = "Set the color name the Redstone Armor will have")
    public static String redstoneArmorItemNameColor = "dark_red";
    @ModConfigProperty(category = "Armors.EmeraldArmor.Registry", name = "emeraldArmorItemNameColor", comment = "Set the color name the Emerald Armor will have")
    public static String emeraldArmorItemNameColor = "dark_green";
    @ModConfigProperty(category = "Armors.ObsidianArmor.Registry", name = "obsidianArmorItemNameColor", comment = "Set the color name the Obsidian Armor will have")
    public static String obsidianArmorItemNameColor = "dark_gray";
    @ModConfigProperty(category = "Armors.LavaArmor.Registry", name = "lavaArmorItemNameColor", comment = "Set the color name the Lava Armor will have")
    public static String lavaArmorItemNameColor = "gold";
    @ModConfigProperty(category = "Armors.SuperStarArmor.Registry", name = "superStarArmorItemNameColor", comment = "Set the color name the Super Star Armor will have")
    public static String superStarArmorItemNameColor = "white";
    @ModConfigProperty(category = "Armors.EnderDragonArmor.Registry", name = "enderDragonArmorItemNameColor", comment = "Set the color name the Ender Dragon Armor will have")
    public static String enderDragonArmorItemNameColor = "dark_purple";
    @ModConfigProperty(category = "Armors.GuardianArmor.Registry", name = "guardianArmorItemNameColor", comment = "Set the color name the Guardian Armor will have")
    public static String guardianArmorItemNameColor = "aqua";
    @ModConfigProperty(category = "Armors.TheUltimateArmor.Registry", name = "theUltimateArmorItemNameColor", comment = "Set the color name the Ultimate Armor will have")
    public static String theUltimateArmorItemNameColor = "green";
    @ModConfigProperty(category = "Armors.ArditeArmor.Registry", name = "arditeArmorItemNameColor", comment = "Set the color name the Ardite Armor will have")
    public static String arditeArmorItemNameColor = "dark_red";
    @ModConfigProperty(category = "Armors.CobaltArmor.Registry", name = "cobaltArmorItemNameColor", comment = "Set the color name the Cobalt Armor will have")
    public static String cobaltArmorItemNameColor = "blue";
    @ModConfigProperty(category = "Armors.ManyullynArmor.Registry", name = "manyullynArmorItemNameColor", comment = "Set the color name the Manyullyn Armor will have")
    public static String manyullynArmorItemNameColor = "dark_purple";
    @ModConfigProperty(category = "Armors.PigIronArmor.Registry", name = "pigIronArmorItemNameColor", comment = "Set the color name the Pig Iron Armor will have")
    public static String pigIronArmorItemNameColor = "light_purple";
    @ModConfigProperty(category = "Armors.KnightSlimeArmor.Registry", name = "knightSlimeArmorItemNameColor", comment = "Set the color name the Knight Slime Armor will have")
    public static String knightSlimeArmorItemNameColor = "dark_purple";
    @ModConfigProperty(category = "Armors.ChickenArmor.Registry", name = "chickenArmorItemNameColor", comment = "Set the color name the Chicken Armor will have")
    public static String chickenArmorItemNameColor = "aqua";
    @ModConfigProperty(category = "Armors.SlimeArmor.Registry", name = "slimeArmorItemNameColor", comment = "Set the color name the Slime Armor will have")
    public static String slimeArmorItemNameColor = "green";
    @ModConfigProperty(category = "Armors.SteelArmor.Registry", name = "steelArmorItemNameColor", comment = "Set the color name the Steel Armor will have")
    public static String steelArmorItemNameColor = "dark_red";
    @ModConfigProperty(category = "Armors.ElectricalArmor.Registry", name = "electricalArmorItemNameColor", comment = "Set the color name the Electrical Armor will have")
    public static String electricalArmorItemNameColor = "dark_red";

    @ModConfigProperty(category = "Armors.CoalArmor.Registry", name = "coalArmorToughnessPoints", comment = "Set the amount of toughness points the Coal Armor will have")
    public static double coalArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "Armors.LapisArmor.Registry", name = "lapisArmorToughnessPoints", comment = "Set the amount of toughness points the Lapis Armor will have")
    public static double lapisArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "Armors.RedstoneArmor.Registry", name = "redstoneArmorToughnessPoints", comment = "Set the amount of toughness points the Redstone Armor will have")
    public static double redstoneArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "Armors.EmeraldArmor.Registry", name = "emeraldArmorToughnessPoints", comment = "Set the amount of toughness points the Emerald Armor will have")
    public static double emeraldArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "Armors.ObsidianArmor.Registry", name = "obsidianArmorToughnessPoints", comment = "Set the amount of toughness points the Obsidian Armor will have")
    public static double obsidianArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "Armors.LavaArmor.Registry", name = "lavaArmorToughnessPoints", comment = "Set the amount of toughness points the Lava Armor will have")
    public static double lavaArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "Armors.SuperStarArmor.Registry", name = "superStarArmorToughnessPoints", comment = "Set the amount of toughness points the Super Star Armor will have")
    public static double superStarArmorToughnessPoints = 2.0;
    @ModConfigProperty(category = "Armors.EnderDragonArmor.Registry", name = "enderDragonArmorToughnessPoints", comment = "Set the amount of toughness points the Ender Dragon Armor will have")
    public static double enderDragonArmorToughnessPoints = 2.0;
    @ModConfigProperty(category = "Armors.GuardianArmor.Registry", name = "guardianArmorToughnessPoints", comment = "Set the amount of toughness points the Guardian Armor will have")
    public static double guardianArmorToughnessPoints = 2.0;
    @ModConfigProperty(category = "Armors.TheUltimateArmor.Registry", name = "theUltimateArmorToughnessPoints", comment = "Set the amount of toughness points the Ultimate Armor will have")
    public static double theUltimateArmorToughnessPoints = 3.0;
    @ModConfigProperty(category = "Armors.ArditeArmor.Registry", name = "arditeArmorToughnessPoints", comment = "Set the amount of toughness points the Ardite Armor will have")
    public static double arditeArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "Armors.CobaltArmor.Registry", name = "cobaltArmorToughnessPoints", comment = "Set the amount of toughness points the Cobalt Armor will have")
    public static double cobaltArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "Armors.ManyullynArmor.Registry", name = "manyullynArmorToughnessPoints", comment = "Set the amount of toughness points the Manyullyn Armor will have")
    public static double manyullynArmorToughnessPoints = 2.0;
    @ModConfigProperty(category = "Armors.PigIronArmor.Registry", name = "pigIronArmorToughnessPoints", comment = "Set the amount of toughness points the Pig Iron Armor will have")
    public static double pigIronArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "Armors.KnightSlimeArmor.Registry", name = "knightSlimeArmorToughnessPoints", comment = "Set the amount of toughness points the Knight Slime Armor will have")
    public static double knightSlimeArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "Armors.ChickenArmor.Registry", name = "chickenArmorToughnessPoints", comment = "Set the amount of toughness points the Chicken Armor will have")
    public static double chickenArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "Armors.SlimeArmor.Registry", name = "slimeArmorToughnessPoints", comment = "Set the amount of toughness points the Slime Armor will have")
    public static double slimeArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "Armors.SteelArmor.Registry", name = "steelArmorToughnessPoints", comment = "Set the amount of toughness points the Steel Armor will have")
    public static double steelArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "Armors.ElectricalArmor.Registry", name = "electricalArmorToughnessPoints", comment = "Set the amount of toughness points the Electrical Armor will have")
    public static double electricalArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "Armors.CoalArmor.Registry", name = "coalArmorProtectionPoints", comment = "Set the amount of protection points the Coal Armor will have (boots, leggings, chestplate, boots)")
    public static int[] coalArmorProtectionPoints = new int[]{1, 1, 2, 1};
    @ModConfigProperty(category = "Armors.LapisArmor.Registry", name = "lapisArmorProtectionPoints", comment = "Set the amount of protection points the Lapis Armor will have (boots, leggings, chestplate, boots)")
    public static int[] lapisArmorProtectionPoints = new int[]{1, 2, 3, 2};
    @ModConfigProperty(category = "Armors.RedstoneArmor.Registry", name = "redstoneArmorProtectionPoints", comment = "Set the amount of protection points the Redstone Armor will have (boots, leggings, chestplate, boots)")
    public static int[] redstoneArmorProtectionPoints = new int[]{1, 2, 3, 2};
    @ModConfigProperty(category = "Armors.EmeraldArmor.Registry", name = "emeraldArmorProtectionPoints", comment = "Set the amount of protection points the Emerald Armor will have (boots, leggings, chestplate, boots)")
    public static int[] emeraldArmorProtectionPoints = new int[]{3, 6, 8, 3};
    @ModConfigProperty(category = "Armors.ObsidianArmor.Registry", name = "obsidianArmorProtectionPoints", comment = "Set the amount of protection points the Obsidian Armor will have (boots, leggings, chestplate, boots)")
    public static int[] obsidianArmorProtectionPoints = new int[]{3, 6, 7, 3};
    @ModConfigProperty(category = "Armors.LavaArmor.Registry", name = "lavaArmorProtectionPoints", comment = "Set the amount of protection points the Lava Armor will have (boots, leggings, chestplate, boots)")
    public static int[] lavaArmorProtectionPoints = new int[]{3, 6, 8, 3};
    @ModConfigProperty(category = "Armors.GuardianArmor.Registry", name = "guardianArmorProtectionPoints", comment = "Set the amount of protection points the Guardian Armor will have (boots, leggings, chestplate, boots)")
    public static int[] guardianArmorProtectionPoints = new int[]{4, 7, 8, 3};
    @ModConfigProperty(category = "Armors.SuperStarArmor.Registry", name = "superStarArmorProtectionPoints", comment = "Set the amount of protection points the Super Star Armor will have (boots, leggings, chestplate, boots)")
    public static int[] superStarArmorProtectionPoints = new int[]{4, 7, 8, 3};
    @ModConfigProperty(category = "Armors.EnderDragonArmor.Registry", name = "enderDragonArmorProtectionPoints", comment = "Set the amount of protection points the Ender Dragon Armor will have (boots, leggings, chestplate, boots)")
    public static int[] enderDragonArmorProtectionPoints = new int[]{4, 7, 8, 3};
    @ModConfigProperty(category = "Armors.TheUltimateArmor.Registry", name = "theUltimateArmorProtectionPoints", comment = "Set the amount of protection points the Ultimate Armor will have (boots, leggings, chestplate, boots)")
    public static int[] theUltimateArmorProtectionPoints = new int[]{4, 8, 9, 4};
    @ModConfigProperty(category = "Armors.ArditeArmor.Registry", name = "arditeArmorProtectionPoints", comment = "Set the amount of protection points the Ardite Armor will have (boots, leggings, chestplate, boots)")
    public static int[] arditeArmorProtectionPoints = new int[]{2, 3, 4, 2};
    @ModConfigProperty(category = "Armors.CobaltArmor.Registry", name = "cobaltArmorProtectionPoints", comment = "Set the amount of protection points the Cobalt Armor will have (boots, leggings, chestplate, boots)")
    public static int[] cobaltArmorProtectionPoints = new int[]{2, 3, 4, 2};
    @ModConfigProperty(category = "Armors.ManyullynArmor.Registry", name = "manyullynArmorProtectionPoints", comment = "Set the amount of protection points the Manyullyn Armor will have (boots, leggings, chestplate, boots)")
    public static int[] manyullynArmorProtectionPoints = new int[]{3, 5, 5, 3};
    @ModConfigProperty(category = "Armors.PigIronArmor.Registry", name = "pigIronArmorProtectionPoints", comment = "Set the amount of protection points the Pig Iron Armor will have (boots, leggings, chestplate, boots)")
    public static int[] pigIronArmorProtectionPoints = new int[]{2, 3, 4, 3};
    @ModConfigProperty(category = "Armors.KnightSlimeArmor.Registry", name = "knightSlimeArmorProtectionPoints", comment = "Set the amount of protection points the Knight Slime Armor will have (boots, leggings, chestplate, boots)")
    public static int[] knightSlimeArmorProtectionPoints = new int[]{2, 3, 4, 3};
    @ModConfigProperty(category = "Armors.ChickenArmor.Registry", name = "chickenArmorProtectionPoints", comment = "Set the amount of protection points the Chicen Armor will have (boots, leggings, chestplate, boots)")
    public static int[] chickenArmorProtectionPoints = new int[]{1, 1, 2, 1};
    @ModConfigProperty(category = "Armors.SlimeArmor.Registry", name = "slimeArmorProtectionPoints", comment = "Set the amount of protection points the Slime Armor will have (boots, leggings, chestplate, boots)")
    public static int[] slimeArmorProtectionPoints = new int[]{1, 1, 2, 1};
    @ModConfigProperty(category = "Armors.SteelArmor.Registry", name = "steelArmorProtectionPoints", comment = "Set the amount of protection points the Steel Armor will have (boots, leggings, chestplate, boots)")
    public static int[] steelArmorProtectionPoints = new int[]{2, 4, 5, 2};
    @ModConfigProperty(category = "Armors.ElectricalArmor.Registry", name = "electricalArmorProtectionPoints", comment = "Set the amount of protection points the Electrical Armor will have (boots, leggings, chestplate, boots)")
    public static int[] electricalArmorProtectionPoints = new int[]{3, 5, 6, 3};

    //Armors.SteelArmor
    @ModConfigProperty(category = "Armors.SteelArmor", name = "inputSteelArmor", comment = "Set the amount of Energy that the item can input")
    public static int inputSteelArmor = 10;
    @ModConfigProperty(category = "Armors.SteelArmor", name = "outputSteelArmor", comment = "Set the amount of Energy that the item can output")
    public static int outputSteelArmor = 1;
    @ModConfigProperty(category = "Armors.SteelArmor", name = "maxCapacitySteelArmor", comment = "Set the max capacity that the item can hold")
    public static int maxCapacitySteelArmor = 3000;
    //Armors.ElectricalArmor
    @ModConfigProperty(category = "Armors.ElectricalArmor", name = "inputElectricalArmor", comment = "Set the amount of Energy that the item can input")
    public static int inputElectricalArmor = 10;
    @ModConfigProperty(category = "Armors.ElectricalArmor", name = "outputElectricalArmor", comment = "Set the amount of Energy that the item can output")
    public static int outputElectricalArmor = 1;
    @ModConfigProperty(category = "Armors.ElectricalArmor", name = "maxCapacityElectricalArmor", comment = "Set the max capacity that the item can hold")
    public static int maxCapacityElectricalArmor = 8000;
    //EnergyItems.Sword
    @ModConfigProperty(category = "EnergyItems", name = "inputSword", comment = "Set the amount of Energy that the item can input")
    public static int inputSword = 10;
    @ModConfigProperty(category = "EnergyItems", name = "outputSword", comment = "Set the amount of Energy that the item can output")
    public static int outputSword = 10;
    @ModConfigProperty(category = "EnergyItems", name = "maxCapacitySword", comment = "Set the max capacity that the item can hold")
    public static int maxCapacitySword = 3000;
    //EnergyItems.Pickaxe
    @ModConfigProperty(category = "EnergyItems", name = "inputPickaxe", comment = "Set the amount of Energy that the item can input")
    public static int inputPickaxe = 10;
    @ModConfigProperty(category = "EnergyItems", name = "outputPickaxe", comment = "Set the amount of Energy that the item can output")
    public static int outputPickaxe = 10;
    @ModConfigProperty(category = "EnergyItems", name = "maxCapacityPickaxe", comment = "Set the max capacity that the item can hold")
    public static int maxCapacityPickaxe = 3000;
    //EnergyItems.Axe
    @ModConfigProperty(category = "EnergyItems", name = "inputAxe", comment = "Set the amount of Energy that the item can input")
    public static int inputAxe = 10;
    @ModConfigProperty(category = "EnergyItems", name = "outputAxe", comment = "Set the amount of Energy that the item can output")
    public static int outputAxe = 10;
    @ModConfigProperty(category = "EnergyItems", name = "maxCapacityAxe", comment = "Set the max capacity that the item can hold")
    public static int maxCapacityAxe = 3000;
    //EnergyItemsShovel
    @ModConfigProperty(category = "EnergyItems", name = "inputShovel", comment = "Set the amount of Energy that the item can input")
    public static int inputShovel = 10;
    @ModConfigProperty(category = "EnergyItems", name = "outputShovel", comment = "Set the amount of Energy that the item can output")
    public static int outputShovel = 10;
    @ModConfigProperty(category = "EnergyItems", name = "maxCapacityShovel", comment = "Set the max capacity that the item can hold")
    public static int maxCapacityShovel = 3000;
    //EnergyItems.Hoe
    @ModConfigProperty(category = "EnergyItems", name = "inputHoe", comment = "Set the amount of Energy that the item can input")
    public static int inputHoe = 10;
    @ModConfigProperty(category = "EnergyItems", name = "outputHoe", comment = "Set the amount of Energy that the item can output")
    public static int outputHoe = 10;
    @ModConfigProperty(category = "EnergyItems", name = "maxCapacityHoe", comment = "Set the max capacity that the item can hold")
    public static int maxCapacityHoe = 3000;

    //TheGiftOfTheGods (TGOTG)
    @ModConfigProperty(category = "TheGiftOfTheGods", name = "enableTheGiftOfTheGods", comment = "Enable/Disable The Gift Of The Gods")
    public static boolean enableTheGiftOfTheGods = true;
    @ModConfigProperty(category = "TheGiftOfTheGods", name = "cooldownTicks", comment = "Set the cooldown ticks until you can use The Gift Of The Gods (1 second = 20 ticks)")
    public static int cooldownTicks = 600;
    @ModConfigProperty(category = "TheGiftOfTheGods", name = "maxUses", comment = "Set the max amount of uses for the item")
    public static int maxUses = 2;

    //EnderDragonZombie
    @ModConfigProperty(category = "EnderDragonZombie.Spawn", name = "enableEnderDragonZombieSpawnEnd", comment = "Enable/Disable the Ender Dragon Zombie to spawn in the End")
    public static boolean enableEnderDragonZombieSpawnEnd = false;
    @ModConfigProperty(category = "EnderDragonZombie.Properties", name = "enderDragonZombieHealth", comment = "Set the Ender Dragon Zombie's Health")
    public static double enderDragonZombieHealth = 40.0;
    @ModConfigProperty(category = "EnderDragonZombie.Properties", name = "enderDragonZombieArmor", comment = "Set the Ender Dragon Zombie's Armor")
    public static double enderDragonZombieArmor = 2.0;
    @ModConfigProperty(category = "EnderDragonZombie.Properties", name = "enderDragonZombieAttackDamage", comment = "Set the Ender Dragon Zombie's Attack Damage")
    public static double enderDragonZombieAttackDamage = 3.0;
    @ModConfigProperty(category = "EnderDragonZombie.Properties", name = "enderDragonZombieMovementSpeed", comment = "Set the Ender Dragon Zombie's Movement Speed")
    public static double enderDragonZombieMovementSpeed = 0.23000000417232513;
    @ModConfigProperty(category = "EnderDragonZombie.Properties", name = "enderDragonZombieFollowRange", comment = "Set the Ender Dragon Zombie's Follow Range")
    public static double enderDragonZombieFollowRange = 35.0;
    @ModConfigProperty(category = "EnderDragonZombie.Effects", name = "enableEnderDragonZombieWithering", comment = "Enable/Disable the Ender Dragon Zombie's Withering Effect")
    public static boolean enableEnderDragonZombieWithering = true;
    @ModConfigProperty(category = "EnderDragonZombie.Effects", name = "enderDragonZombieWitheringEffectDuration", comment = "Set the Ender Dragon Zombie's Withering Effect Duration")
    public static int enderDragonZombieWitheringEffectDuration = 20;
    @ModConfigProperty(category = "EnderDragonZombie.Effects", name = "enderDragonZombieWitheringEffectLevel", comment = "Set the Ender Dragon Zombie's Withering Effect Level")
    public static int enderDragonZombieWitheringEffectLevel = 4;

    //Debug
    @ModConfigProperty(category = "Debug", name = "debugMode", comment = "Enable/Disable Debug Mode")
    public static boolean debugMode = false;
    @ModConfigProperty(category = "Debug", name = "debugModeTGOTG", comment = "Enable/Disable Debug Mode for the Gift Of The Gods")
    public static boolean debugModeTGOTG = false;
    @ModConfigProperty(category = "Debug", name = "debugModeEnchantments", comment = "Enable/Disable Debug Mode for the Enchantments")
    public static boolean debugModeEnchantments = false;
    //Weapons.Recipes
    @ModConfigProperty(category = "Weapons.Recipes", name = "enableSwordsRecipes", comment = "Enable/Disable ArmorPlus Sword's Recipes")
    public static boolean enableSwordsRecipes = true;
    @ModConfigProperty(category = "Weapons.Recipes", name = "enableBattleAxesRecipes", comment = "Enable/Disable ArmorPlus Battle Axes's Recipes")
    public static boolean enableBattleAxesRecipes = true;
    @ModConfigProperty(category = "Weapons.Recipes", name = "enableBowsRecipes", comment = "Enable/Disable ArmorPlus Bows's Recipes")
    public static boolean enableBowsRecipes = true;
    //SuperStarArmor
    @ModConfigProperty(category = "Armors.SuperStarArmor.Effects", name = "enableSuperStarHEffect", comment = "Enable/Disable the Super Star Helmet Effect")
    public static boolean enableSuperStarHEffect = true;
    @ModConfigProperty(category = "Armors.SuperStarArmor.Effects", name = "enableSuperStarCEffect", comment = "Enable/Disable the Super Star Chestplate Effect")
    public static boolean enableSuperStarCEffect = true;
    @ModConfigProperty(category = "Armors.SuperStarArmor.Effects", name = "enableSuperStarLEffect", comment = "Enable/Disable the Super Star Leggings Effect")
    public static boolean enableSuperStarLEffect = true;
    @ModConfigProperty(category = "Armors.SuperStarArmor.Effects", name = "enableSuperStarBEffect", comment = "Enable/Disable the Super Star Boots Effect")
    public static boolean enableSuperStarBEffect = true;
    @ModConfigProperty(category = "Armors.SuperStarArmor.Effects", name = "enableFullSuperStarArmorEffect", comment = "Enable/Disable the Full Super Star Armor Effect")
    public static boolean enableFullSuperStarArmorEffect = false;
    //LavaArmor
    @ModConfigProperty(category = "Armors.LavaArmor.Effects", name = "enableLavaHEffects", comment = "Enable/Disable the Lava Helmet Fire Resistance Effect")
    public static boolean enableLavaHEffect = true;
    @ModConfigProperty(category = "Armors.LavaArmor.Effects", name = "enableLavaCEffects", comment = "Enable/Disable the Lava Chestplate Fire Resistance Effect")
    public static boolean enableLavaCEffect = true;
    @ModConfigProperty(category = "Armors.LavaArmor.Effects", name = "enableLavaLEffects", comment = "Enable/Disable the Lava Leggings Fire Resistance Effect")
    public static boolean enableLavaLEffect = true;
    @ModConfigProperty(category = "Armors.LavaArmor.Effects", name = "enableLavaBEffects", comment = "Enable/Disable the Lava Boots Fire Resistance")
    public static boolean enableLavaBEffect = true;
    @ModConfigProperty(category = "Armors.LavaArmor.Effects", name = "enableFullLavaArmorEffect", comment = "Enable/Disable the Full Lava Armor Effect")
    public static boolean enableFullLavaArmorEffect = false;
    //ObsidianArmor
    @ModConfigProperty(category = "Armors.ObsidianArmor.Effects", name = "enableObsidianHEffect", comment = "Enable/Disable the Obsidian Helmet Resistance")
    public static boolean enableObsidianHEffect = true;
    @ModConfigProperty(category = "Armors.ObsidianArmor.Effects", name = "enableObsidianCEffect", comment = "Enable/Disable the Obsidian Chestplate Resistance")
    public static boolean enableObsidianCEffect = true;
    @ModConfigProperty(category = "Armors.ObsidianArmor.Effects", name = "enableObsidianLEffect", comment = "Enable/Disable the Obsidian Leggings Resistance")
    public static boolean enableObsidianLEffect = true;
    @ModConfigProperty(category = "Armors.ObsidianArmor.Effects", name = "enableObsidianBEffect", comment = "Enable/Disable the Obsidian Boots Resistance")
    public static boolean enableObsidianBEffect = true;
    @ModConfigProperty(category = "Armors.ObsidianArmor.Effects", name = "enableFullObsidianArmorEffect", comment = "Enable/Disable the Full Obsidian Armor Effect")
    public static boolean enableFullObsidianArmorEffect = false;
    //EmeraldArmor
    @ModConfigProperty(category = "Armors.EmeraldArmor.Effects", name = "enableEmeraldHEffect", comment = "Enable/Disable the Emerald Helmet Effect")
    public static boolean enableEmeraldHEffect = true;
    @ModConfigProperty(category = "Armors.EmeraldArmor.Effects", name = "enableEmeraldCEffect", comment = "Enable/Disable the Emerald Chestplate Effect")
    public static boolean enableEmeraldCEffect = true;
    @ModConfigProperty(category = "Armors.EmeraldArmor.Effects", name = "enableEmeraldLEffect", comment = "Enable/Disable the Emerald Leggings Effect")
    public static boolean enableEmeraldLEffect = true;
    @ModConfigProperty(category = "Armors.EmeraldArmor.Effects", name = "enableEmeraldBEffect", comment = "Enable/Disable the Emerald Boots Effect")
    public static boolean enableEmeraldBEffect = true;
    @ModConfigProperty(category = "Armors.EmeraldArmor.Effects", name = "enableFullEmeraldArmorEffect", comment = "Enable/Disable the Full Emerald Armor Effect")
    public static boolean enableFullEmeraldArmorEffect = false;
    //RedstoneArmor
    @ModConfigProperty(category = "Armors.RedstoneArmor.Effects", name = "enableRedstoneHEffect", comment = "Enable/Disable the Redstone Helmet Effect")
    public static boolean enableRedstoneHEffect = true;
    @ModConfigProperty(category = "Armors.RedstoneArmor.Effects", name = "enableRedstoneCEffect", comment = "Enable/Disable the Redstone Chestplate Effect")
    public static boolean enableRedstoneCEffect = true;
    @ModConfigProperty(category = "Armors.RedstoneArmor.Effects", name = "enableRedstoneLEffect", comment = "Enable/Disable the Redstone Leggings Effect")
    public static boolean enableRedstoneLEffect = true;
    @ModConfigProperty(category = "Armors.RedstoneArmor.Effects", name = "enableRedstoneBEffect", comment = "Enable/Disable the Redstone Boots Effect")
    public static boolean enableRedstoneBEffect = true;
    @ModConfigProperty(category = "Armors.RedstoneArmor.Effects", name = "enableFullRedstoneArmorEffect", comment = "Enable/Disable the Full Redstone Armor Effect")
    public static boolean enableFullRedstoneArmorEffect = false;
    //LapisArmor
    @ModConfigProperty(category = "Armors.LapisArmor.Effects", name = "enableLapisHEffect", comment = "Enable/Disable the Lapis Helmet Water Effect")
    public static boolean enableLapisHEffect = true;
    @ModConfigProperty(category = "Armors.LapisArmor.Effects", name = "enableLapisCEffect", comment = "Enable/Disable the Lapis Chestplate Water Effect")
    public static boolean enableLapisCEffect = true;
    @ModConfigProperty(category = "Armors.LapisArmor.Effects", name = "enableLapisLEffect", comment = "Enable/Disable the Lapis Leggings Water Effect")
    public static boolean enableLapisLEffect = true;
    @ModConfigProperty(category = "Armors.LapisArmor.Effects", name = "enableLapisBEffect", comment = "Enable/Disable the Lapis Boots Water Effect")
    public static boolean enableLapisBEffect = true;
    @ModConfigProperty(category = "Armors.LapisArmor.Effects", name = "enableFullLapisArmorEffect", comment = "Enable/Disable the Full Lapis Armor Effect")
    public static boolean enableFullLapisArmorEffect = false;
    //CoalArmor
    @ModConfigProperty(category = "Armors.CoalArmor.Effects", name = "enableCoalHEffect", comment = "Enable/Disable the Coal Helmet Effect")
    public static boolean enableCoalHEffect = true;
    @ModConfigProperty(category = "Armors.CoalArmor.Effects", name = "enableCoalCEffect", comment = "Enable/Disable the Coal Chestplate Effect")
    public static boolean enableCoalCEffect = true;
    @ModConfigProperty(category = "Armors.CoalArmor.Effects", name = "enableCoalLEffect", comment = "Enable/Disable the Coal Leggings Effect")
    public static boolean enableCoalLEffect = true;
    @ModConfigProperty(category = "Armors.CoalArmor.Effects", name = "enableCoalBEffect", comment = "Enable/Disable the Coal Boots Effect")
    public static boolean enableCoalBEffect = true;
    @ModConfigProperty(category = "Armors.CoalArmor.Effects", name = "enableFullCoalArmorEffect", comment = "Enable/Disable the Full Coal Armor Effect")
    public static boolean enableFullCoalArmorEffect = false;
    //Registry
    @ModConfigProperty(category = "Armors.CoalArmor.Registry", name = "enableCoalArmor", comment = "Enable/Disable the Coal Armor from the Game")
    public static boolean enableCoalArmor = true;
    @ModConfigProperty(category = "Armors.LapisArmor.Registry", name = "enableLapisArmor", comment = "Enable/Disable the Lapis Armor from the Game")
    public static boolean enableLapisArmor = true;
    @ModConfigProperty(category = "Armors.RedstoneArmor.Registry", name = "enableRedstoneArmor", comment = "Enable/Disable the Redstone Armor from the Game")
    public static boolean enableRedstoneArmor = true;
    @ModConfigProperty(category = "Armors.EmeraldArmor.Registry", name = "enableEmeraldArmor", comment = "Enable/Disable the Emerald Armor from the Game")
    public static boolean enableEmeraldArmor = true;
    @ModConfigProperty(category = "Armors.ObsidianArmor.Registry", name = "enableObsidianArmor", comment = "Enable/Disable the Obsidian Armor from the Game")
    public static boolean enableObsidianArmor = true;
    @ModConfigProperty(category = "Armors.LavaArmor.Registry", name = "enableLavaArmor", comment = "Enable/Disable the Lava Armor from the Game")
    public static boolean enableLavaArmor = true;
    @ModConfigProperty(category = "Armors.SuperStarArmor.Registry", name = "enableSuperStarArmor", comment = "Enable/Disable the Super Star Armor from the Game")
    public static boolean enableSuperStarArmor = true;
    @ModConfigProperty(category = "Armors.EnderDragonArmor.Registry", name = "enableEnderDragonArmor", comment = "Enable/Disable the Ender Dragon Armor from the Game")
    public static boolean enableEnderDragonArmor = true;
    @ModConfigProperty(category = "Armors.GuardianArmor.Registry", name = "enableGuardianArmor", comment = "Enable/Disable the Guardian Armor from the Game")
    public static boolean enableGuardianArmor = true;
    @ModConfigProperty(category = "Armors.TheUltimateArmor.Registry", name = "enableTheUltimateArmor", comment = "Enable/Disable The Ultimate Armor from the Game")
    public static boolean enableTheUltimateArmor = true;
    @ModConfigProperty(category = "Armors.TheUltimateArmor.Registry", name = "makeTheUltimateArmorUnbreakable", comment = "Sets The Ultimate Armor Unbreakable")
    public static boolean makeTheUltimateArmorUnbreakable = false;
    @ModConfigProperty(category = "Armors.ArditeArmor.Registry", name = "enableArditeArmor", comment = "Enable/Disable the Ardite Armors from the Game")
    public static boolean enableArditeArmor = true;
    @ModConfigProperty(category = "Armors.CobaltArmor.Registry", name = "enableCobaltArmor", comment = "Enable/Disable the Cobalt Armors from the Game")
    public static boolean enableCobaltArmor = true;
    @ModConfigProperty(category = "Armors.ManyullynArmor.Registry", name = "enableManyullynArmor", comment = "Enable/Disable the Manyullyn Armors from the Game")
    public static boolean enableManyullynArmor = true;
    @ModConfigProperty(category = "Armors.PigIronArmor.Registry", name = "enablePigIronArmor", comment = "Enable/Disable the Pig Iron Armors from the Game")
    public static boolean enablePigIronArmor = true;
    @ModConfigProperty(category = "Armors.KnightSlimeArmor.Registry", name = "enableKnightSlimeArmor", comment = "Enable/Disable the Knight Slime Armors from the Game")
    public static boolean enableKnightSlimeArmor = true;
    @ModConfigProperty(category = "Armors.ChickenArmor.Registry", name = "enableChickenArmor", comment = "Enable/Disable the Chicken Armors from the Game")
    public static boolean enableChickenArmor = true;
    @ModConfigProperty(category = "Armors.SlimeArmor.Registry", name = "enableSlimeArmor", comment = "Enable/Disable the Slime Armors from the Game")
    public static boolean enableSlimeArmor = true;
    @ModConfigProperty(category = "Armors.SteelArmor.Registry", name = "enableSteelArmor", comment = "Enable/Disable the Steel Armors from the Game")
    public static boolean enableSteelArmor = true;
    @ModConfigProperty(category = "Armors.ElectricalArmor.Registry", name = "enableElectricalArmor", comment = "Enable/Disable the Electrical Armors from the Game")
    public static boolean enableElectricalArmor = true;
    //FlightAbility
    @ModConfigProperty(category = "FlightAbility", name = "enableFlightAbility", comment = "Enable/Disable the Armors Flight")
    public static boolean enableFlightAbility = true;
    //EffectLevel
    @ModConfigProperty(category = "Armors.CoalArmor.Effects", name = "coalArmorEffectLevel", comment = "Set the level of the Effect by the Coal Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int coalArmorEffectLevel = 0;
    @ModConfigProperty(category = "Armors.LapisArmor.Effects", name = "lapisArmorEffectLevel", comment = "Set the level of the Effect by the Lapis Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int lapisArmorEffectLevel = 0;
    @ModConfigProperty(category = "Armors.RedstoneArmor.Effects", name = "redstoneArmorEffectLevel", comment = "Set the level of the Effect by the Redstone Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int redstoneArmorEffectLevel = 1;
    @ModConfigProperty(category = "Armors.EmeraldArmor.Effects", name = "emeraldArmorEffectLevel", comment = "Set the level of the Effect by the Emerald Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int emeraldArmorEffectLevel = 1;
    @ModConfigProperty(category = "Armors.ObsidianArmor.Effects", name = "obsidianArmorEffectLevel", comment = "Set the level of the Effect by the Obsidian Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int obsidianArmorEffectLevel = 0;
    @ModConfigProperty(category = "Armors.LavaArmor.Effects", name = "lavaArmorEffectLevel", comment = "Set the level of the Effect by the Lava Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int lavaArmorEffectLevel = 0;
    @ModConfigProperty(category = "Armors.ChickenArmor.Effects", name = "chickenArmorEffectLevel", comment = "Set the level of the Effect by the Chicken Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int chickenArmorEffectLevel = 4;
    @ModConfigProperty(category = "Armors.SlimeArmor.Effects", name = "slimeArmorEffectLevel", comment = "Set the level of the Effect by the Slime Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int slimeArmorEffectLevel = 2;
    @ModConfigProperty(category = "Armors.GuardianArmor.Effects", name = "guardianArmorEffectLevel", comment = "Set the level of the Effect by the Guardian Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int guardianArmorEffectLevel = 0;
    @ModConfigProperty(category = "Armors.SuperStarArmor.Effects", name = "superStarArmorEffectLevel", comment = "Set the level of the Effect by the Super Star Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int superStarArmorEffectLevel = 1;
    @ModConfigProperty(category = "Armors.TheUltimateArmor.Effects", name = "ultimateArmorEffectLevel", comment = "Set the level of the Effect by The Ultimate Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int[] ultimateArmorEffectLevels = new int[]{0, 1, 0};
    @ModConfigProperty(category = "Armors.ArditeArmor.Effects", name = "arditeArmorEffectLevel", comment = "Set the level of the Effect by the Ardite Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int arditeArmorEffectLevel = 0;
    @ModConfigProperty(category = "Armors.CobaltArmor.Effects", name = "cobaltArmorEffectLevel", comment = "Set the level of the Effect by the Cobalt Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int cobaltArmorEffectLevel = 2;
    @ModConfigProperty(category = "Armors.ManyullynArmor.Effects", name = "manyullynArmorEffectLevel", comment = "Set the level of the Effect by the Manyullyn Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int manyullynArmorEffectLevel = 1;
    @ModConfigProperty(category = "Armors.KnightSlimeArmor.Effects", name = "knightSlimeArmorEffectLevel", comment = "Set the level of the Effect by the Knight Slime Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int knightSlimeArmorEffectLevel = 1;
    @ModConfigProperty(category = "Armors.PigIronArmor.Effects", name = "pigIronArmorEffectLevel", comment = "Set the level of the Effect by the Pig Iron Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int pigIronArmorEffectLevel = 0;

    //WorldGeneration
    @ModConfigProperty(category = "WorldGeneration.Overworld", name = "enableLavaCrystalOreOverworldGen", comment = "Enable/Disable The Lava Crystal World Generation in the dimension `Overworld`")
    public static boolean enableLavaCrystalOverworldGen = true;
    @ModConfigProperty(category = "WorldGeneration.TheEnd", name = "enableLavaCrystalOreTheEndGen", comment = "Enable/Disable The Lava Crystal World Generation in the dimension `The End`")
    public static boolean enableLavaCrystalTheEndGen = false;
    @ModConfigProperty(category = "WorldGeneration.TheNether", name = "enableLavaCrystalOreTheNetherGen", comment = "Enable/Disable The Lava Crystal World Generation in the dimension `The Nether`")
    public static boolean enableLavaCrystalTheNetherGen = false;
    @ModConfigProperty(category = "WorldGeneration.Overworld", name = "lavaCrystalOverworldRarity", comment = "Set the rarity level of the Lava Crystal Generation in the dimension `Overworld`")
    public static int lavaCrystalOverworldRarity = 7;
    @ModConfigProperty(category = "WorldGeneration.Overworld", name = "lavaCrystalOverworldMinYSpawn", comment = "Set the min Y level of the Lava Crystal Generation in the dimension `Overworld`")
    public static int lavaCrystalOverworldMinYSpawn = 6;
    @ModConfigProperty(category = "WorldGeneration.Overworld", name = "lavaCrystalOverworldMaxYSpawn", comment = "Set the max Y level of the Lava Crystal Generation in the dimension `Overworld`")
    public static int lavaCrystalOverworldMaxYSpawn = 16;
    @ModConfigProperty(category = "WorldGeneration.TheEnd", name = "lavaCrystalTheEndRarity", comment = "Set the rarity level of the Lava Crystal Generation in the dimension `The End`")
    public static int lavaCrystalTheEndRarity = 0;
    @ModConfigProperty(category = "WorldGeneration.TheEnd", name = "lavaCrystalTheEndMinYSpawn", comment = "Set the min Y level of the Lava Crystal Generation in the dimension `The End`")
    public static int lavaCrystalTheEndMinYSpawn = 0;
    @ModConfigProperty(category = "WorldGeneration.TheEnd", name = "lavaCrystalTheEndMaxYSpawn", comment = "Set the max Y level of the Lava Crystal Generation in the dimension `The End`")
    public static int lavaCrystalTheEndMaxYSpawn = 0;
    @ModConfigProperty(category = "WorldGeneration.TheNether", name = "lavaCrystalTheNetherRarity", comment = "Set the rarity level of the Lava Crystal Generation in the dimension `The Nether`")
    public static int lavaCrystalTheNetherRarity = 0;
    @ModConfigProperty(category = "WorldGeneration.TheNether", name = "lavaCrystalTheNetherMinYSpawn", comment = "Set the min Y level of the Lava Crystal Generation in the dimension `The Nether`")
    public static int lavaCrystalTheNetherMinYSpawn = 0;
    @ModConfigProperty(category = "WorldGeneration.TheNether", name = "lavaCrystalTheNetherMaxYSpawn", comment = "Set the max Y level of the Lava Crystal Generation in the dimension `The Nether`")
    public static int lavaCrystalTheNetherMaxYSpawn = 0;
    @ModConfigProperty(category = "WorldGeneration.Overworld", name = "lavaCrystalOverworldVeinAmount", comment = "Set the vein amount of the Lava Crystal Generation in the dimension `Overworld`")
    public static int lavaCrystalOverworldVeinAmount = 5;
    @ModConfigProperty(category = "WorldGeneration.TheEnd", name = "lavaCrystalTheEndVeinAmount", comment = "Set the vein amount of the Lava Crystal Generation in the dimension `The End`")
    public static int lavaCrystalTheEndVeinAmount = 0;
    @ModConfigProperty(category = "WorldGeneration.TheNether", name = "lavaCrystalTheNetherVeinAmount", comment = "Set the vein amount of the Lava Crystal Generation in the dimension `The Nether`")
    public static int lavaCrystalTheNetherVeinAmount = 0;
    @ModConfigProperty(category = "WorldGeneration.CastleGeneration", name = "castleGenSpawnChance", comment = "Set the spawn chance of the Castle Generation in the Overworld")
    public static int castleGenSpawnChance = 1;
    @ModConfigProperty(category = "WorldGeneration.CastleGeneration", name = "enableCastleGenSpawnChance", comment = "Enable/Disable the Castle Generation")
    public static boolean enableCastleGen = true;
    //GameModes
    @ModConfigProperty(category = "GameModes", name = "gamemode", comment = "Sets the Recipe Difficulty \n0 = easy , 1 = expert")
    public static int gameMode = 1;
    //WhiteList
    @ModConfigProperty(category = "WhiteList", name = "whitelistmax", comment = "Set the maximum amount of items that the player can get by the \"The Gift Of The Gods\". \nNote:You will need to have that many WhiteListed Items.")
    public static int whitelistmax = 0;
    @ModConfigProperty(category = "WhiteList", name = "whitelistmin", comment = "Set the minimum amount of items that the player can get by the \"The Gift Of The Gods\". \nNote:Don't change this from 0\"")
    public static int whitelistmin = 0;
    @ModConfigProperty(category = "WhiteList", name = "enableWhiteList", comment = "Enable/Disable the WhiteList")
    public static boolean enableWhiteList = false;
    @ModConfigProperty(category = "WhiteList", name = "whiteListedItems", comment = "Add WhiteListed Items to the \"The Gift Of The Gods\" \nIf You add want to add an item to the whitelist \nYou will need to replace 1 from \"minecraft:dirt\" to the item you want to add")
    public static String[] whiteListedItems = new String[]{"minecraft:dirt"};
    //BlackList
    @ModConfigProperty(category = "BlackList", name = "enableBlackList", comment = "Enable/Disable the BlackList")
    public static boolean enableBlackList = true;
    @ModConfigProperty(category = "BlackList", name = "blackListedItems", comment = "Add Blacklisted Items to the \"The Gift Of The Gods\" \nIf You add want to add an item to the blacklist \nYou will need to replace 1 from \"minecraft:dirt\" to the item you want to add")
    public static String[] blackListedItems = new String[]{"armorplus:dev_helmet", "armorplus:dev_chestplate", "armorplus:dev_leggings", "armorplus:dev_boots", "minecraft:dirt"};
    //TinkersEffects
    @ModConfigProperty(category = "Armors.ArditeArmor.Effects", name = "enableArditeArmorEffects", comment = "Enable/Disable Ardite Armor Effects")
    public static boolean enableArditeArmorEffect = true;
    @ModConfigProperty(category = "Armors.CobaltArmor.Effects", name = "enableCobaltArmorEffects", comment = "Enable/Disable Cobalt Armor Effects")
    public static boolean enableCobaltArmorEffect = true;
    @ModConfigProperty(category = "Armors.ManyullynArmor.Effects", name = "enableManyullynArmorEffects", comment = "Enable/Disable Manyullyn Armor Effects")
    public static boolean enableManyullynArmorEffect = true;
    @ModConfigProperty(category = "Armors.PigIronArmor.Effects", name = "enablePigIronArmorEffects", comment = "Enable/Disable Manyullym Armor Effects")
    public static boolean enablePigIronArmorEffect = true;
    @ModConfigProperty(category = "Armors.PigIronArmor.Effects", name = "enableKnightSlimeArmorEffects", comment = "Enable/Disable Knight Slime Armor Effects")
    public static boolean enableKnightSlimeArmorEffect = true;
    //TheUltimateArmor
    @ModConfigProperty(category = "Armors.TheUltimateArmor.Ability", name = "enableTheUltimateArmorInvincibility", comment = "Enable/Disable The Ultimate Armor Invincibility")
    public static boolean enableTheUltimateArmorInvincibility = false;
    @ModConfigProperty(category = "Armors.TheUltimateArmor.DeBuffs", name = "enableTheUltimateArmorDeBuffs", comment = "Enable/Disable The Ultimate Armor DeBuffs")
    public static boolean enableTheUltimateArmorDeBuffs = true;
    //EnderDragonArmor
    @ModConfigProperty(category = "Armors.EnderDragonArmor.Recipes", name = "enableEnderDragonArmorElytra", comment = "Enable/Disable The Ender Dragon Chestplate to require 2 x Elytras instead of 2 x End Crystals to craft")
    public static boolean enableEnderDragonArmorElytra = false;
    //Recipes
    @ModConfigProperty(category = "Armors.CoalArmor.Recipes", name = "enableCoalArmorRecipes", comment = "Enable/Disable The Coal Armor Recipes")
    public static boolean enableCoalArmorRecipes = true;
    @ModConfigProperty(category = "Armors.CoalArmor.Recipes", name = "enableCharcoalCoalArmorRecipe", comment = "Enable/Disable The Charcoal Coal Armor Recipes")
    public static boolean enableCharcoalCoalArmorRecipe = false;
    @ModConfigProperty(category = "Armors.LapisArmor.Recipes", name = "enableLapisArmorRecipes", comment = "Enable/Disable The Lapis Armor Recipes")
    public static boolean enableLapisArmorRecipes = true;
    @ModConfigProperty(category = "Armors.RedstoneArmor.Recipes", name = "enableRedstoneArmorRecipes", comment = "Enable/Disable The Redstone Armor Recipes")
    public static boolean enableRedstoneArmorRecipes = true;
    @ModConfigProperty(category = "Armors.EmeraldArmor.Recipes", name = "enableEmeraldArmorRecipes", comment = "Enable/Disable The Emerald Armor Recipes")
    public static boolean enableEmeraldArmorRecipes = true;
    @ModConfigProperty(category = "Armors.ObsidianArmor.Recipes", name = "enableObsidianArmorRecipes", comment = "Enable/Disable The Obsidian Armor Recipes")
    public static boolean enableObsidianArmorRecipes = true;
    @ModConfigProperty(category = "Armors.LavaArmor.Recipes", name = "enableLavaArmorRecipes", comment = "Enable/Disable The Lava Armor Recipes")
    public static boolean enableLavaArmorRecipes = true;
    @ModConfigProperty(category = "Armors.SuperStarArmor.Recipes", name = "enableSuperStarArmorRecipes", comment = "Enable/Disable The Super Star Armor Recipes")
    public static boolean enableSuperStarArmorRecipes = true;
    @ModConfigProperty(category = "Armors.EnderDragonArmor.Recipes", name = "enableEnderDragonArmorRecipes", comment = "Enable/Disable The Ender Dragon Armor Recipes")
    public static boolean enableEnderDragonArmorRecipes = true;
    @ModConfigProperty(category = "Armors.GuardianArmor.Recipes", name = "enableGuardianArmorRecipes", comment = "Enable/Disable The Guardian Armor Recipes")
    public static boolean enableGuardianArmorRecipes = true;
    @ModConfigProperty(category = "Armors.TheUltimateArmor.Recipes", name = "enableTheUltimateArmorRecipes", comment = "Enable/Disable The Ultimate Armor Recipes")
    public static boolean enableTheUltimateArmorRecipes = true;
    @ModConfigProperty(category = "Armors.ChainArmor.Recipes", name = "enableChainArmorRecipes", comment = "Enable/Disable The Chain Armors Recipes")
    public static boolean enableChainArmorRecipes = true;
    @ModConfigProperty(category = "Armors.ArditeArmor.Recipes", name = "enableArditeArmorRecipes", comment = "Enable/Disable The Ardite Armors Recipes")
    public static boolean enableArditeArmorRecipes = true;
    @ModConfigProperty(category = "Armors.CobaltArmor.Recipes", name = "enableCobaltArmorRecipes", comment = "Enable/Disable The Cobalt Armors Recipes")
    public static boolean enableCobaltArmorRecipes = true;
    @ModConfigProperty(category = "Armors.ManyullynArmor.Recipes", name = "enableManyullynArmorRecipes", comment = "Enable/Disable The Manyullyn Armors Recipes")
    public static boolean enableManyullynArmorRecipes = true;
    @ModConfigProperty(category = "Armors.PigIronArmor.Recipes", name = "enablePigIronArmorRecipes", comment = "Enable/Disable The Pig Iron Armors Recipes")
    public static boolean enablePigIronArmorRecipes = true;
    @ModConfigProperty(category = "Armors.KnightSlimeArmor.Recipes", name = "enableKnightSlimeArmorRecipes", comment = "Enable/Disable The Knight Slime Armors Recipes")
    public static boolean enableKnightSlimeArmorRecipes = true;
    @ModConfigProperty(category = "Armors.ChickenArmor.Recipes", name = "enableChickenArmorRecipes", comment = "Enable/Disable The Chicken Armors Recipes")
    public static boolean enableChickenArmorRecipes = true;
    @ModConfigProperty(category = "Armors.SlimeArmor.Recipes", name = "enableSlimeArmorRecipes", comment = "Enable/Disable The Slime Armors Recipes")
    public static boolean enableSlimeArmorRecipes = true;
    @ModConfigProperty(category = "Armors.LavaArmor.Recipes", name = "enableOldLavaArmorRecipes", comment = "Enable/Disable The Old Lava Armors Recipes")
    public static boolean enableOldLavaArmorRecipes = false;
    //Items.Recipes
    @ModConfigProperty(category = "Items.Recipes", name = "enableRedstoneAppleRecipes", comment = "Enable/Disable The Redstone Apple Recipes")
    public static boolean enableRedstoneAppleRecipes = true;
    @ModConfigProperty(category = "Items.Recipes", name = "enableElytraRecipe", comment = "Enable/Disable The Elytra Recipe")
    public static boolean enableElytraRecipe = false;
    @ModConfigProperty(category = "Items.Recipes", name = "enableArrowRecipes", comment = "Enable/Disable The ArmorPlus Arrows Recipes")
    public static boolean enableArrowRecipes = true;
    //MobDrops
    @ModConfigProperty(category = "MobDrops.EnderDragon", name = "enderdragonScaleDropAmount", comment = "Set the amount of dropped Ender Dragon Scales that the Ender Dragon will drop")
    public static int enderdragonScaleDropAmount = 12;
    @ModConfigProperty(category = "MobDrops.WitherBoss", name = "witherBoneDropAmount", comment = "Set the amount of dropped Wither Bones that the Wither Boss will drop")
    public static int witherBoneDropAmount = 6;
    @ModConfigProperty(category = "MobDrops.ElderGuardian", name = "guardianScaleElderDropAmount", comment = "Set the amount of dropped Guardian Scales that the Elder Guardian will drop")
    public static int guardianScaleElderDropAmount = 6;

    public static boolean isDebugMode() {
        return debugMode;
    }

    public static RecipesDifficulty getRD() {
        switch (gameMode) {
            case -1:
                return DISABLED;
            case 0:
                return EASY;
            case 1:
                return EXPERT;
            case 2:
                return HELLISH;
        }
        return EXPERT;
    }

    public static boolean hasRecipes() {
        return getRD().hasRecipes();
    }

    public enum RecipesDifficulty {
        DISABLED(false) {
            @Override
            public boolean isItemRepairable(ItemStack repair, Item easy, Item expert) {
                return false;
            }
        },
        EASY(true) {
            @Override
            public boolean isItemRepairable(ItemStack repair, Item easy, Item expert) {
                return repair.getItem() == easy;
            }
        },
        EXPERT(true) {
            @Override
            public boolean isItemRepairable(ItemStack repair, Item easy, Item expert) {
                return repair.getItem() == expert;
            }
        },
        HELLISH(true) {
            @Override
            public boolean isItemRepairable(ItemStack repair, Item easy, Item expert) {
                return false;
            }
        },;

        private final boolean hasRecipes;

        RecipesDifficulty(boolean hasRecipes) {
            this.hasRecipes = hasRecipes;
        }

        public boolean hasRecipes() {
            return hasRecipes;
        }

        public abstract boolean isItemRepairable(ItemStack repair, Item easy, Item expert);
    }
}