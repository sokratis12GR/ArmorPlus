/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus;

import net.minecraft.item.ItemStack;
import net.thedragonteam.thedragonlib.config.ModConfigProperty;

import static java.util.Locale.ENGLISH;
import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.*;

/**
 * net.thedragonteam.armorplus
 * ArmorPlus created by sokratis12GR on 7/26/2016 4:47 PM.
 * - TheDragonTeam
 */
public class APConfig {

    //GameModes
    @ModConfigProperty(category = "GameModes", name = "recipes", comment = "Sets the Recipe Difficulty \n0 = easy (All recipes are extremely easy) , 1 = expert (default, All Recipes Are Harder), 2 = hellish (Repairing for A+ items is DISABLED)")
    public static int gameMode = 1;
    //WhiteList
    @ModConfigProperty(category = "WhiteList", name = "whitelistMax", comment = "Set the maximum amount of items that the player can get by the \"The Gift Of The Gods\". \nNote:You will need to have that many WhiteListed Items.")
    public static int whitelistMax = 0;
    @ModConfigProperty(category = "WhiteList", name = "whitelistMin", comment = "Set the minimum amount of items that the player can get by the \"The Gift Of The Gods\". \nNote:Don't change this from 0\"")
    public static int whitelistMin = 0;
    @ModConfigProperty(category = "WhiteList", name = "enableWhiteList", comment = "Enable/Disable the WhiteList")
    public static boolean enableWhiteList = false;
    @ModConfigProperty(category = "WhiteList", name = "whiteListedItems", comment = "Add WhiteListed Items to the \"The Gift Of The Gods\" \nIf You add want to add an item to the whitelist \nYou will need to replace 1 from \"minecraft:dirt\" to the item you want to add")
    public static String[] whiteListedItems = new String[]{"minecraft:dirt"};
    //BlackList
    @ModConfigProperty(category = "BlackList", name = "enableBlackList", comment = "Enable/Disable the BlackList")
    public static boolean enableBlackList = true;
    @ModConfigProperty(category = "BlackList", name = "blackListedItems", comment = "Add Blacklisted Items to the \"The Gift Of The Gods\" \nIf You add want to add an item to the blacklist \nYou will need to replace 1 from \"minecraft:dirt\" to the item you want to add")
    public static String[] blackListedItems = new String[]{"minecraft:dirt"};
    //Weapons.*.Registry
    @ModConfigProperty(category = "Weapons.Coal.Registry", name = "coalWeaponItemNameColor", comment = "Set the color name the Coal Weapons will have")
    public static String coalWeaponItemNameColor = "gray".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.Lapis.Registry", name = "lapisWeaponItemNameColor", comment = "Set the color name the Lapis Weapons will have")
    public static String lapisWeaponItemNameColor = "dark_blue".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.Redstone.Registry", name = "redstoneWeaponItemNameColor", comment = "Set the color name the Redstone Weapons will have")
    public static String redstoneWeaponItemNameColor = "dark_red".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.Emerald.Registry", name = "emeraldWeaponItemNameColor", comment = "Set the color name the Emerald Weapons will have")
    public static String emeraldWeaponItemNameColor = "dark_green".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.Obsidian.Registry", name = "obsidianWeaponItemNameColor", comment = "Set the color name the Obsidian Weapons will have")
    public static String obsidianWeaponItemNameColor = "dark_gray".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.Lava.Registry", name = "lavaWeaponItemNameColor", comment = "Set the color name the Lava Weapons will have")
    public static String lavaWeaponItemNameColor = "gold".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.SuperStar.Registry", name = "superStarWeaponItemNameColor", comment = "Set the color name the Super Star Weapons will have")
    public static String superStarWeaponItemNameColor = "white".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.EnderDragon.Registry", name = "enderDragonWeaponItemNameColor", comment = "Set the color name the Ender Dragon Weapons will have")
    public static String enderDragonWeaponItemNameColor = "dark_purple".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.Guardian.Registry", name = "guardianWeaponItemNameColor", comment = "Set the color name the Guardian Weapons will have")
    public static String guardianWeaponItemNameColor = "aqua".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.Tesla.Registry", name = "teslaWeaponItemNameColor", comment = "Set the color name the Tesla Weapons will have")
    public static String teslaWeaponItemNameColor = "dark_aqua".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.Coal.Registry", name = "enableCoalWeapons", comment = "Enable/Disable the Coal Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableCoalWeapons = new boolean[]{true, true, true};
    @ModConfigProperty(category = "Weapons.Lapis.Registry", name = "enableLapisWeapons", comment = "Enable/Disable the Lapis Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableLapisWeapons = new boolean[]{true, true, true};
    @ModConfigProperty(category = "Weapons.Redstone.Registry", name = "enableRedstoneWeapons", comment = "Enable/Disable the Redstone Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableRedstoneWeapons = new boolean[]{true, true, true};
    @ModConfigProperty(category = "Weapons.Emerald.Registry", name = "enableEmeraldWeapons", comment = "Enable/Disable the Emerald Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableEmeraldWeapons = new boolean[]{true, true, true};
    @ModConfigProperty(category = "Weapons.Obsidian.Registry", name = "enableObsidianWeapons", comment = "Enable/Disable the Obsidian Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableObsidianWeapons = new boolean[]{true, true, true};
    @ModConfigProperty(category = "Weapons.Lava.Registry", name = "enableLavaWeapons", comment = "Enable/Disable the Lava Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableLavaWeapons = new boolean[]{true, true, true};
    @ModConfigProperty(category = "Weapons.Guardian.Registry", name = "enableGuardianWeapons", comment = "Enable/Disable the Guardian Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableGuardianWeapons = new boolean[]{true, true, true};
    @ModConfigProperty(category = "Weapons.SuperStar.Registry", name = "enableSuperStarWeapons", comment = "Enable/Disable the Super Star Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableSuperStarWeapons = new boolean[]{true, true, true};
    @ModConfigProperty(category = "Weapons.EnderDragon.Registry", name = "enableEnderDragonWeapons", comment = "Enable/Disable the Ender Dragon Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableEnderDragonWeapons = new boolean[]{true, true, true};

    //Weapons.*
    @ModConfigProperty(category = "Weapons.Coal", name = "coalSwordDamage", comment = "Set the amount of damage the Coal Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double coalSwordDamage = 0.5;
    @ModConfigProperty(category = "Weapons.Lapis", name = "lapisSwordDamage", comment = "Set the amount of damage the Lapis Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double lapisSwordDamage = 1.0;
    @ModConfigProperty(category = "Weapons.Redstone", name = "redstoneSwordDamage", comment = "Set the amount of damage the Redstone Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double redstoneSwordDamage = 1.0;
    @ModConfigProperty(category = "Weapons.Emerald", name = "emeraldSwordDamage", comment = "Set the amount of damage the Emerald Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double emeraldSwordDamage = 1.5;
    @ModConfigProperty(category = "Weapons.Obsidian", name = "obsidianSwordDamage", comment = "Set the amount of damage the Obsidian Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double obsidianSwordDamage = 4.0;
    @ModConfigProperty(category = "Weapons.Lava", name = "lavaSwordDamage", comment = "Set the amount of damage the Lava Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double lavaSwordDamage = 4.5;
    @ModConfigProperty(category = "Weapons.Guardian", name = "guardianSwordDamage", comment = "Set the amount of damage the Guardian Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double guardianSwordDamage = 6.0;
    @ModConfigProperty(category = "Weapons.SuperStar", name = "superStarSwordDamage", comment = "Set the amount of damage the Super Star Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double superStarSwordDamage = 7.0;
    @ModConfigProperty(category = "Weapons.EnderDragon", name = "enderDragonSwordDamage", comment = "Set the amount of damage the Ender Dragon Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double enderDragonSwordDamage = 8.0;
    @ModConfigProperty(category = "Weapons.Coal", name = "coalSwordDurability", comment = "Set the amount of durability the Coal Sword have")
    public static int coalSwordDurability = 12;
    @ModConfigProperty(category = "Weapons.Lapis", name = "lapisSwordDurability", comment = "Set the amount of durability the Lapis Sword have")
    public static int lapisSwordDurability = 36;
    @ModConfigProperty(category = "Weapons.Redstone", name = "redstoneSwordDurability", comment = "Set the amount of durability the Redstone Sword have")
    public static int redstoneSwordDurability = 200;
    @ModConfigProperty(category = "Weapons.Emerald", name = "emeraldSwordDurability", comment = "Set the amount of durability the Emerald Sword have")
    public static int emeraldSwordDurability = 1561;
    @ModConfigProperty(category = "Weapons.Obsidian", name = "obsidianSwordDurability", comment = "Set the amount of durability the Obsidian Sword have")
    public static int obsidianSwordDurability = 1500;
    @ModConfigProperty(category = "Weapons.Lava", name = "lavaSwordDurability", comment = "Set the amount of durability the Lava Sword have")
    public static int lavaSwordDurability = 1750;
    @ModConfigProperty(category = "Weapons.Guardian", name = "guardianSwordDurability", comment = "Set the amount of durability the Guardian Sword have")
    public static int guardianSwordDurability = 1800;
    @ModConfigProperty(category = "Weapons.SuperStar", name = "superStarSwordDurability", comment = "Set the amount of durability the Super Star Sword have")
    public static int superStarSwordDurability = 1950;
    @ModConfigProperty(category = "Weapons.EnderDragon", name = "enderDragonSwordDurability", comment = "Set the amount of durability the Ender Dragon Sword have")
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
    public static String coalWeaponsAddPotionEffect = "blindness".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.Lapis.Effects", name = "lapisWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Lapis Weapons will have (to disable the effect set the effects \'false\')")
    public static String lapisWeaponsAddPotionEffect = "nausea".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.Redstone.Effects", name = "redstoneWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Redstone Weapons will have (to disable the effect set the effects \'false\')")
    public static String redstoneWeaponsAddPotionEffect = "slowness".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.Emerald.Effects", name = "emeraldWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Emerald Weapons will have (to disable the effect set the effects \'false\')")
    public static String emeraldWeaponsAddPotionEffect = "mining_fatigue".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.Obsidian.Effects", name = "obsidianWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Obsidian Weapons will have (to disable the effect set the effects \'false\')")
    public static String obsidianWeaponsAddPotionEffect = "weakness".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.Guardian.Effects", name = "guardianWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Guardian Weapons will have (to disable the effect set the effects \'false\')")
    public static String guardianWeaponsAddPotionEffect = "nausea".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.SuperStar.Effects", name = "superStarWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Super Star Weapons will have (to disable the effect set the effects \'false\')")
    public static String superStarWeaponsAddPotionEffect = "wither".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Weapons.SuperStar.Effects", name = "enderDragonWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Super Star Weapons will have (to disable the effect set the effects \'false\')")
    public static String enderDragonWeaponsAddPotionEffect = "wither".toLowerCase(ENGLISH);
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

    @ModConfigProperty(category = "Weapons.Coal", name = "coalBattleAxeDamage", comment = "Set the amount of damage the Coal BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double coalBattleAxeDamage = 2.5;
    @ModConfigProperty(category = "Weapons.Lapis", name = "lapisBattleAxeDamage", comment = "Set the amount of damage the Lapis BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double lapisBattleAxeDamage = 3.0;
    @ModConfigProperty(category = "Weapons.Redstone", name = "redstoneBattleAxeDamage", comment = "Set the amount of damage the Redstone BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double redstoneBattleAxeDamage = 3.0;
    @ModConfigProperty(category = "Weapons.Emerald", name = "emeraldBattleAxeDamage", comment = "Set the amount of damage the Emerald BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double emeraldBattleAxeDamage = 3.5;
    @ModConfigProperty(category = "Weapons.Obsidian", name = "obsidianBattleAxeDamage", comment = "Set the amount of damage the Obsidian BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double obsidianBattleAxeDamage = 6.0;
    @ModConfigProperty(category = "Weapons.Lava", name = "lavaBattleAxeDamage", comment = "Set the amount of damage the Lava BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double lavaBattleAxeDamage = 6.5;
    @ModConfigProperty(category = "Weapons.Guardian", name = "guardianBattleAxeDamage", comment = "Set the amount of damage the Guardian BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double guardianBattleAxeDamage = 7.0;
    @ModConfigProperty(category = "Weapons.SuperStar", name = "superStarBattleAxeDamage", comment = "Set the amount of damage the Super Star BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double superStarBattleAxeDamage = 8.0;
    @ModConfigProperty(category = "Weapons.EnderDragon", name = "enderDragonBattleAxeDamage", comment = "Set the amount of damage the Ender Dragon BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double enderDragonBattleAxeDamage = 10.0;
    @ModConfigProperty(category = "Weapons.Coal", name = "coalBattleAxeDurability", comment = "Set the amount of durability the Coal BattleAxe have")
    public static int coalBattleAxeDurability = 24;
    @ModConfigProperty(category = "Weapons.Lapis", name = "lapisBattleAxeDurability", comment = "Set the amount of durability the Lapis BattleAxe have")
    public static int lapisBattleAxeDurability = 72;
    @ModConfigProperty(category = "Weapons.Redstone", name = "redstoneBattleAxeDurability", comment = "Set the amount of durability the Redstone BattleAxe have")
    public static int redstoneBattleAxeDurability = 200;
    @ModConfigProperty(category = "Weapons.Emerald", name = "emeraldBattleAxeDurability", comment = "Set the amount of durability the Emerald BattleAxe have")
    public static int emeraldBattleAxeDurability = 1561;
    @ModConfigProperty(category = "Weapons.Obsidian", name = "obsidianBattleAxeDurability", comment = "Set the amount of durability the Obsidian BattleAxe have")
    public static int obsidianBattleAxeDurability = 1500;
    @ModConfigProperty(category = "Weapons.Lava", name = "lavaBattleAxeDurability", comment = "Set the amount of durability the Lava BattleAxe have")
    public static int lavaBattleAxeDurability = 1750;
    @ModConfigProperty(category = "Weapons.Guardian", name = "guardianBattleAxeDurability", comment = "Set the amount of durability the Guardian BattleAxe have")
    public static int guardianBattleAxeDurability = 1800;
    @ModConfigProperty(category = "Weapons.SuperStar", name = "superStarBattleAxeDurability", comment = "Set the amount of durability the Super Star BattleAxe have")
    public static int superStarBattleAxeDurability = 1950;
    @ModConfigProperty(category = "Weapons.EnderDragon", name = "enderDragonBattleAxeDurability", comment = "Set the amount of durability the Ender Dragon BattleAxe have")
    public static int enderDragonBattleAxeDurability = 2310;

    //Weapons.*
    @ModConfigProperty(category = "Weapons.Coal", name = "coalBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Coal Bow will do")
    public static double coalBowArrowBonusDamage = -2.0;
    @ModConfigProperty(category = "Weapons.Lapis", name = "lapisBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Lapis Bow will do")
    public static double lapisBowArrowBonusDamage = -1.5;
    @ModConfigProperty(category = "Weapons.Redstone", name = "redstoneBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Redstone Bow will do")
    public static double redstoneBowArrowBonusDamage = -1.5;
    @ModConfigProperty(category = "Weapons.Emerald", name = "emeraldBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Emerald Bow will do")
    public static double emeraldBowArrowBonusDamage = -0.5;
    @ModConfigProperty(category = "Weapons.Obsidian", name = "obsidianBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Obsidian Bow will do")
    public static double obsidianBowArrowBonusDamage = 0.0;
    @ModConfigProperty(category = "Weapons.Lava", name = "lavaBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Lava Bow will do")
    public static double lavaBowArrowBonusDamage = 0.5;
    @ModConfigProperty(category = "Weapons.Guardian", name = "guardianBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Guardian Bow will do")
    public static double guardianBowArrowBonusDamage = 1.5;
    @ModConfigProperty(category = "Weapons.SuperStar", name = "superStarBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Super Star Bow will do")
    public static double superStarBowArrowBonusDamage = 1.5;
    @ModConfigProperty(category = "Weapons.EnderDragon", name = "enderDragonBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Ender Dragon Bow will do")
    public static double enderDragonBowArrowBonusDamage = 1.5;
    @ModConfigProperty(category = "Weapons.Coal", name = "coalBowDurability", comment = "Set the amount of durability the Coal Bow have")
    public static int coalBowDurability = 59;
    @ModConfigProperty(category = "Weapons.Lapis", name = "lapisBowDurability", comment = "Set the amount of durability the Lapis Bow have")
    public static int lapisBowDurability = 18;
    @ModConfigProperty(category = "Weapons.Redstone", name = "redstoneBowDurability", comment = "Set the amount of durability the Redstone Bow have")
    public static int redstoneBowDurability = 200;
    @ModConfigProperty(category = "Weapons.Emerald", name = "emeraldBowDurability", comment = "Set the amount of durability the Emerald Bow have")
    public static int emeraldBowDurability = 1561;
    @ModConfigProperty(category = "Weapons.Obsidian", name = "obsidianBowDurability", comment = "Set the amount of durability the Obsidian Bow have")
    public static int obsidianBowDurability = 1500;
    @ModConfigProperty(category = "Weapons.Lava", name = "lavaBowDurability", comment = "Set the amount of durability the Lava Bow have")
    public static int lavaBowDurability = 1750;
    @ModConfigProperty(category = "Weapons.Guardian", name = "guardianBowDurability", comment = "Set the amount of durability the Guardian Bow have")
    public static int guardianBowDurability = 1800;
    @ModConfigProperty(category = "Weapons.SuperStar", name = "superStarBowDurability", comment = "Set the amount of durability the Super Star Bow have")
    public static int superStarBowDurability = 1950;
    @ModConfigProperty(category = "Weapons.EnderDragon", name = "enderDragonBowDurability", comment = "Set the amount of durability the Ender Dragon Bow have")
    public static int enderDragonBowDurability = 2310;

    //Armors.*.Effects
    @ModConfigProperty(category = "Armors.Coal.Effects", name = "coalArmorAddPotionEffect", comment = "Adds the Potion Effect the Coal Armor will have (to disable the effect set the effects \'false\')")
    public static String coalArmorAddPotionEffect = "night_vision".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Lapis.Effects", name = "lapisArmorAddPotionEffect", comment = "Adds the Potion Effect the Lapis Armor will have (to disable the effect set the effects \'false\')")
    public static String lapisArmorAddPotionEffect = "water_breathing".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Redstone.Effects", name = "redstoneArmorAddPotionEffect", comment = "Adds the Potion Effect the Redstone Armor will have (to disable the effect set the effects \'false\')")
    public static String redstoneArmorAddPotionEffect = "haste".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Emerald.Effects", name = "emeraldArmorAddPotionEffect", comment = "Adds the Potion Effect the Emerald Armor will have (to disable the effect set the effects \'false\')")
    public static String emeraldArmorAddPotionEffect = "speed".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Obsidian.Effects", name = "obsidianArmorAddPotionEffect", comment = "Adds the Potion Effect the Obsidian Armor will have (to disable the effect set the effects \'false\')")
    public static String obsidianArmorAddPotionEffect = "resistance".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Lava.Effects", name = "lavaArmorAddPotionEffect", comment = "Adds the Potion Effect the Lava Armor will have (to disable the effect set the effects \'false\')")
    public static String lavaArmorAddPotionEffect = "fire_resistance".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Lava.Effects", name = "enableLavaArmorOnWaterTouchDeBuff", comment = "Enables/Disables the DeBuffs that the armor will get when touching water without Water Breathing Potion. \naka nothing happens when player wears this armor while in water.")
    public static boolean enableLavaArmorOnWaterTouchDeBuff = true;

    @ModConfigProperty(category = "Armors.Guardian.Effects", name = "guardianArmorAddPotionEffect", comment = "Adds the Potion Effect the Guardian Armor will have (to disable the effect set the effects \'false\')")
    public static String guardianArmorAddPotionEffect = "water_breathing".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.SuperStar.Effects", name = "superStarArmorAddPotionEffect", comment = "Adds the Potion Effect the Super Star Armor will have (to disable the effect set the effects \'false\')")
    public static String superStarArmorAddPotionEffect = "regeneration".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Ultimate.Effects", name = "theUltimateArmorAddPotionEffect", comment = "Adds the Potion Effects the Ultimate Armor will have (to disable the effect set the effects \'false\')")
    public static String[] theUltimateArmorAddPotionEffect = new String[]{("saturation"), ("regeneration"), ("water_breathing")};
    @ModConfigProperty(category = "Armors.Ultimate.Effects", name = "theUltimateArmorRemovePotionEffect", comment = "The Potion Effect that the Ultimate Dragon Armor will be Removing")
    public static String theUltimateArmorRemovePotionEffect = "wither".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.SuperStar.Effects", name = "superStarArmorRemovePotionEffect", comment = "The Potion Effect that the Super Star Armor will be Removing")
    public static String superStarArmorRemovePotionEffect = "wither".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.EnderDragon.Effects", name = "enderDragonArmorRemovePotionEffect", comment = "The Potion Effect that the Ender Dragon Armor will be Removing")
    public static String enderDragonArmorRemovePotionEffect = "wither".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Ardite.Effects", name = "arditeArmorAddPotionEffect", comment = "Adds the Potion Effect the Ardite Armor will have (to disable the effect set the effects \'false\')")
    public static String arditeArmorAddPotionEffect = "fire_resistance".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Cobalt.Effects", name = "cobaltArmorAddPotionEffect", comment = "Adds the Potion Effect the Cobalt Armor will have (to disable the effect set the effects \'false\')")
    public static String cobaltArmorAddPotionEffect = "haste".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Manyullyn.Effects", name = "manyullynArmorAddPotionEffect", comment = "Adds the Potion Effect the Manyullyn Armor will have (to disable the effect set the effects \'false\')")
    public static String manyullynArmorAddPotionEffect = "strength".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.PigIron.Effects", name = "pigIronArmorAddPotionEffect", comment = "Adds the Potion Effect the Pig Iron Armor will have (to disable the effect set the effects \'false\')")
    public static String pigIronArmorAddPotionEffect = "saturation".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.KnightSlime.Effects", name = "knightSlimeArmorAddPotionEffect", comment = "Adds the Potion Effect the Knight Slime Armor will have (to disable the effect set the effects \'false\')")
    public static String knightSlimeArmorAddPotionEffect = "jump_boost".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Chicken.Effects", name = "chickenArmorAddPotionEffect", comment = "Adds the Potion Effect the Chicken Armor will have (to disable the effect set the effects \'false\')")
    public static String chickenArmorAddPotionEffect = "speed".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Slime.Effects", name = "slimeArmorAddPotionEffect", comment = "Adds the Potion Effect the Slime Armor will have (to disable the effect set the effects \'false\')")
    public static String slimeArmorAddPotionEffect = "jump_boost".toLowerCase(ENGLISH);
    //Armors.*.Registry
    @ModConfigProperty(category = "Armors.Coal.Registry", name = "coalArmorItemNameColor", comment = "Set the color name the Coal Armor will have")
    public static String coalArmorItemNameColor = "gray".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Lapis.Registry", name = "lapisArmorItemNameColor", comment = "Set the color name the Lapis Armor will have")
    public static String lapisArmorItemNameColor = "dark_blue".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Redstone.Registry", name = "redstoneArmorItemNameColor", comment = "Set the color name the Redstone Armor will have")
    public static String redstoneArmorItemNameColor = "dark_red".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Emerald.Registry", name = "emeraldArmorItemNameColor", comment = "Set the color name the Emerald Armor will have")
    public static String emeraldArmorItemNameColor = "dark_green".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Obsidian.Registry", name = "obsidianArmorItemNameColor", comment = "Set the color name the Obsidian Armor will have")
    public static String obsidianArmorItemNameColor = "dark_gray".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Lava.Registry", name = "lavaArmorItemNameColor", comment = "Set the color name the Lava Armor will have")
    public static String lavaArmorItemNameColor = "gold".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.SuperStar.Registry", name = "superStarArmorItemNameColor", comment = "Set the color name the Super Star Armor will have")
    public static String superStarArmorItemNameColor = "white".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.EnderDragon.Registry", name = "enderDragonArmorItemNameColor", comment = "Set the color name the Ender Dragon Armor will have")
    public static String enderDragonArmorItemNameColor = "dark_purple".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Guardian.Registry", name = "guardianArmorItemNameColor", comment = "Set the color name the Guardian Armor will have")
    public static String guardianArmorItemNameColor = "aqua".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Ultimate.Registry", name = "theUltimateArmorItemNameColor", comment = "Set the color name the Ultimate Armor will have")
    public static String theUltimateArmorItemNameColor = "green".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Ardite.Registry", name = "arditeArmorItemNameColor", comment = "Set the color name the Ardite Armor will have")
    public static String arditeArmorItemNameColor = "dark_red".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Cobalt.Registry", name = "cobaltArmorItemNameColor", comment = "Set the color name the Cobalt Armor will have")
    public static String cobaltArmorItemNameColor = "blue".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Manyullyn.Registry", name = "manyullynArmorItemNameColor", comment = "Set the color name the Manyullyn Armor will have")
    public static String manyullynArmorItemNameColor = "dark_purple".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.PigIron.Registry", name = "pigIronArmorItemNameColor", comment = "Set the color name the Pig Iron Armor will have")
    public static String pigIronArmorItemNameColor = "light_purple".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.KnightSlime.Registry", name = "knightSlimeArmorItemNameColor", comment = "Set the color name the Knight Slime Armor will have")
    public static String knightSlimeArmorItemNameColor = "dark_purple".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Chicken.Registry", name = "chickenArmorItemNameColor", comment = "Set the color name the Chicken Armor will have")
    public static String chickenArmorItemNameColor = "aqua".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Slime.Registry", name = "slimeArmorItemNameColor", comment = "Set the color name the Slime Armor will have")
    public static String slimeArmorItemNameColor = "green".toLowerCase(ENGLISH);
    @ModConfigProperty(category = "Armors.Coal.Registry", name = "coalArmorToughnessPoints", comment = "Set the amount of toughness points the Coal Armor will have")
    public static double coalArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "Armors.Lapis.Registry", name = "lapisArmorToughnessPoints", comment = "Set the amount of toughness points the Lapis Armor will have")
    public static double lapisArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "Armors.Redstone.Registry", name = "redstoneArmorToughnessPoints", comment = "Set the amount of toughness points the Redstone Armor will have")
    public static double redstoneArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "Armors.Emerald.Registry", name = "emeraldArmorToughnessPoints", comment = "Set the amount of toughness points the Emerald Armor will have")
    public static double emeraldArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "Armors.Obsidian.Registry", name = "obsidianArmorToughnessPoints", comment = "Set the amount of toughness points the Obsidian Armor will have")
    public static double obsidianArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "Armors.Lava.Registry", name = "lavaArmorToughnessPoints", comment = "Set the amount of toughness points the Lava Armor will have")
    public static double lavaArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "Armors.SuperStar.Registry", name = "superStarArmorToughnessPoints", comment = "Set the amount of toughness points the Super Star Armor will have")
    public static double superStarArmorToughnessPoints = 2.0;
    @ModConfigProperty(category = "Armors.EnderDragon.Registry", name = "enderDragonArmorToughnessPoints", comment = "Set the amount of toughness points the Ender Dragon Armor will have")
    public static double enderDragonArmorToughnessPoints = 2.0;
    @ModConfigProperty(category = "Armors.Guardian.Registry", name = "guardianArmorToughnessPoints", comment = "Set the amount of toughness points the Guardian Armor will have")
    public static double guardianArmorToughnessPoints = 2.0;
    @ModConfigProperty(category = "Armors.Ultimate.Registry", name = "theUltimateArmorToughnessPoints", comment = "Set the amount of toughness points the Ultimate Armor will have")
    public static double theUltimateArmorToughnessPoints = 3.0;
    @ModConfigProperty(category = "Armors.Ardite.Registry", name = "arditeArmorToughnessPoints", comment = "Set the amount of toughness points the Ardite Armor will have")
    public static double arditeArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "Armors.Cobalt.Registry", name = "cobaltArmorToughnessPoints", comment = "Set the amount of toughness points the Cobalt Armor will have")
    public static double cobaltArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "Armors.Manyullyn.Registry", name = "manyullynArmorToughnessPoints", comment = "Set the amount of toughness points the Manyullyn Armor will have")
    public static double manyullynArmorToughnessPoints = 2.0;
    @ModConfigProperty(category = "Armors.PigIron.Registry", name = "pigIronArmorToughnessPoints", comment = "Set the amount of toughness points the Pig Iron Armor will have")
    public static double pigIronArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "Armors.KnightSlime.Registry", name = "knightSlimeArmorToughnessPoints", comment = "Set the amount of toughness points the Knight Slime Armor will have")
    public static double knightSlimeArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "Armors.Chicken.Registry", name = "chickenArmorToughnessPoints", comment = "Set the amount of toughness points the Chicken Armor will have")
    public static double chickenArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "Armors.Slime.Registry", name = "slimeArmorToughnessPoints", comment = "Set the amount of toughness points the Slime Armor will have")
    public static double slimeArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "Armors.Coal.Registry", name = "coalArmorProtectionPoints", comment = "Set the amount of protection points the Coal Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] coalArmorProtectionPoints = new int[]{1, 1, 2, 1};
    @ModConfigProperty(category = "Armors.Lapis.Registry", name = "lapisArmorProtectionPoints", comment = "Set the amount of protection points the Lapis Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] lapisArmorProtectionPoints = new int[]{1, 2, 3, 2};
    @ModConfigProperty(category = "Armors.Redstone.Registry", name = "redstoneArmorProtectionPoints", comment = "Set the amount of protection points the Redstone Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] redstoneArmorProtectionPoints = new int[]{1, 2, 3, 2};
    @ModConfigProperty(category = "Armors.Emerald.Registry", name = "emeraldArmorProtectionPoints", comment = "Set the amount of protection points the Emerald Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] emeraldArmorProtectionPoints = new int[]{3, 6, 8, 3};
    @ModConfigProperty(category = "Armors.Obsidian.Registry", name = "obsidianArmorProtectionPoints", comment = "Set the amount of protection points the Obsidian Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] obsidianArmorProtectionPoints = new int[]{3, 6, 7, 3};
    @ModConfigProperty(category = "Armors.Lava.Registry", name = "lavaArmorProtectionPoints", comment = "Set the amount of protection points the Lava Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] lavaArmorProtectionPoints = new int[]{3, 6, 8, 3};
    @ModConfigProperty(category = "Armors.Guardian.Registry", name = "guardianArmorProtectionPoints", comment = "Set the amount of protection points the Guardian Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] guardianArmorProtectionPoints = new int[]{4, 7, 8, 3};
    @ModConfigProperty(category = "Armors.SuperStar.Registry", name = "superStarArmorProtectionPoints", comment = "Set the amount of protection points the Super Star Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] superStarArmorProtectionPoints = new int[]{4, 7, 8, 3};
    @ModConfigProperty(category = "Armors.EnderDragon.Registry", name = "enderDragonArmorProtectionPoints", comment = "Set the amount of protection points the Ender Dragon Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] enderDragonArmorProtectionPoints = new int[]{4, 7, 8, 3};
    @ModConfigProperty(category = "Armors.Ultimate.Registry", name = "theUltimateArmorProtectionPoints", comment = "Set the amount of protection points the Ultimate Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] theUltimateArmorProtectionPoints = new int[]{4, 8, 9, 4};
    @ModConfigProperty(category = "Armors.Ardite.Registry", name = "arditeArmorProtectionPoints", comment = "Set the amount of protection points the Ardite Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] arditeArmorProtectionPoints = new int[]{2, 3, 4, 2};
    @ModConfigProperty(category = "Armors.Cobalt.Registry", name = "cobaltArmorProtectionPoints", comment = "Set the amount of protection points the Cobalt Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] cobaltArmorProtectionPoints = new int[]{2, 3, 4, 2};
    @ModConfigProperty(category = "Armors.Manyullyn.Registry", name = "manyullynArmorProtectionPoints", comment = "Set the amount of protection points the Manyullyn Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] manyullynArmorProtectionPoints = new int[]{3, 5, 5, 3};
    @ModConfigProperty(category = "Armors.PigIron.Registry", name = "pigIronArmorProtectionPoints", comment = "Set the amount of protection points the Pig Iron Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] pigIronArmorProtectionPoints = new int[]{2, 3, 4, 3};
    @ModConfigProperty(category = "Armors.KnightSlime.Registry", name = "knightSlimeArmorProtectionPoints", comment = "Set the amount of protection points the Knight Slime Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] knightSlimeArmorProtectionPoints = new int[]{2, 3, 4, 3};
    @ModConfigProperty(category = "Armors.Chicken.Registry", name = "chickenArmorProtectionPoints", comment = "Set the amount of protection points the Chicen Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] chickenArmorProtectionPoints = new int[]{1, 1, 2, 1};
    @ModConfigProperty(category = "Armors.Slime.Registry", name = "slimeArmorProtectionPoints", comment = "Set the amount of protection points the Slime Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] slimeArmorProtectionPoints = new int[]{1, 1, 2, 1};

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

    //EnderDragon
    @ModConfigProperty(category = "Armors.EnderDragon.Effects", name = "enableEnderDragonEffect", comment = "Enable/Disable the Ender Dragon Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableEnderDragonEffect = new boolean[4];
    @ModConfigProperty(category = "Armors.EnderDragon.Effects", name = "enableFullEnderDragonArmorEffect", comment = "Enable/Disable the Full Ender Dragon Armor Effect")
    public static boolean enableFullEnderDragonArmorEffect = true;
    //Guardian
    @ModConfigProperty(category = "Armors.Guardian.Effects", name = "enableGuardianEffect", comment = "Enable/Disable the Guardian Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableGuardianEffect = new boolean[4];
    @ModConfigProperty(category = "Armors.Guardian.Effects", name = "enableFullGuardianArmorEffect", comment = "Enable/Disable the Full Guardian Armor Effect")
    public static boolean enableFullGuardianArmorEffect = true;
    //SuperStarArmor
    @ModConfigProperty(category = "Armors.SuperStar.Effects", name = "enableSuperStarEffect", comment = "Enable/Disable the Super Star Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableSuperStarEffect = new boolean[4];
    @ModConfigProperty(category = "Armors.SuperStar.Effects", name = "enableFullSuperStarArmorEffect", comment = "Enable/Disable the Full Super Star Armor Effect")
    public static boolean enableFullSuperStarArmorEffect = true;
    //LavaArmor
    @ModConfigProperty(category = "Armors.Lava.Effects", name = "enableLavaEffects", comment = "Enable/Disable the Lava Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableLavaEffect = new boolean[4];
    @ModConfigProperty(category = "Armors.Lava.Effects", name = "enableFullLavaArmorEffect", comment = "Enable/Disable the Full Lava Armor Effect")
    public static boolean enableFullLavaArmorEffect = true;
    //ObsidianArmor
    @ModConfigProperty(category = "Armors.Obsidian.Effects", name = "enableObsidianEffect", comment = "Enable/Disable the Obsidian Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableObsidianEffect = new boolean[4];
    @ModConfigProperty(category = "Armors.Obsidian.Effects", name = "enableFullObsidianArmorEffect", comment = "Enable/Disable the Full Obsidian Armor Effect")
    public static boolean enableFullObsidianArmorEffect = true;
    //EmeraldArmor
    @ModConfigProperty(category = "Armors.Emerald.Effects", name = "enableEmeraldEffect", comment = "Enable/Disable the Emerald Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableEmeraldEffect = new boolean[4];
    @ModConfigProperty(category = "Armors.Emerald.Effects", name = "enableFullEmeraldArmorEffect", comment = "Enable/Disable the Full Emerald Armor Effect")
    public static boolean enableFullEmeraldArmorEffect = true;
    //RedstoneArmor
    @ModConfigProperty(category = "Armors.Redstone.Effects", name = "enableRedstoneEffect", comment = "Enable/Disable the Redstone Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableRedstoneEffect = new boolean[4];
    @ModConfigProperty(category = "Armors.Redstone.Effects", name = "enableFullRedstoneArmorEffect", comment = "Enable/Disable the Full Redstone Armor Effect")
    public static boolean enableFullRedstoneArmorEffect = true;
    //LapisArmor
    @ModConfigProperty(category = "Armors.Lapis.Effects", name = "enableLapisEffect", comment = "Enable/Disable the Lapis Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableLapisEffect = new boolean[4];
    @ModConfigProperty(category = "Armors.Lapis.Effects", name = "enableFullLapisArmorEffect", comment = "Enable/Disable the Full Lapis Armor Effect")
    public static boolean enableFullLapisArmorEffect = true;
    //CoalArmor
    @ModConfigProperty(category = "Armors.Coal.Effects", name = "enableCoalEffect", comment = "Enable/Disable the Coal Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableCoalEffect = new boolean[4];
    @ModConfigProperty(category = "Armors.Coal.Effects", name = "enableFullCoalArmorEffect", comment = "Enable/Disable the Full Coal Armor Effect")
    public static boolean enableFullCoalArmorEffect = true;
    //ChickenArmor
    @ModConfigProperty(category = "Armors.Chicken.Effects", name = "enableChickenEffect", comment = "Enable/Disable the Chicken Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableChickenEffect = new boolean[4];
    @ModConfigProperty(category = "Armors.Chicken.Effects", name = "enableFullChickenArmorEffect", comment = "Enable/Disable the Full Chicken Armor Effect")
    public static boolean enableFullChickenArmorEffect = true;
    //SlimeArmor
    @ModConfigProperty(category = "Armors.Slime.Effects", name = "enableSlimeEffect", comment = "Enable/Disable the Slime Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableSlimeEffect = new boolean[4];
    @ModConfigProperty(category = "Armors.Slime.Effects", name = "enableFullSlimeArmorEffect", comment = "Enable/Disable the Full Slime Armor Effect")
    public static boolean enableFullSlimeArmorEffect = true;
    //Registry
    @ModConfigProperty(category = "Armors.Coal.Registry", name = "enableCoalArmor", comment = "Enable/Disable the Coal Armor from the Game")
    public static boolean enableCoalArmor = true;
    @ModConfigProperty(category = "Armors.Lapis.Registry", name = "enableLapisArmor", comment = "Enable/Disable the Lapis Armor from the Game")
    public static boolean enableLapisArmor = true;
    @ModConfigProperty(category = "Armors.Redstone.Registry", name = "enableRedstoneArmor", comment = "Enable/Disable the Redstone Armor from the Game")
    public static boolean enableRedstoneArmor = true;
    @ModConfigProperty(category = "Armors.Emerald.Registry", name = "enableEmeraldArmor", comment = "Enable/Disable the Emerald Armor from the Game")
    public static boolean enableEmeraldArmor = true;
    @ModConfigProperty(category = "Armors.Obsidian.Registry", name = "enableObsidianArmor", comment = "Enable/Disable the Obsidian Armor from the Game")
    public static boolean enableObsidianArmor = true;
    @ModConfigProperty(category = "Armors.Lava.Registry", name = "enableLavaArmor", comment = "Enable/Disable the Lava Armor from the Game")
    public static boolean enableLavaArmor = true;
    @ModConfigProperty(category = "Armors.SuperStar.Registry", name = "enableSuperStarArmor", comment = "Enable/Disable the Super Star Armor from the Game")
    public static boolean enableSuperStarArmor = true;
    @ModConfigProperty(category = "Armors.EnderDragon.Registry", name = "enableEnderDragonArmor", comment = "Enable/Disable the Ender Dragon Armor from the Game")
    public static boolean enableEnderDragonArmor = true;
    @ModConfigProperty(category = "Armors.Guardian.Registry", name = "enableGuardianArmor", comment = "Enable/Disable the Guardian Armor from the Game")
    public static boolean enableGuardianArmor = true;
    @ModConfigProperty(category = "Armors.Ultimate.Registry", name = "enableTheUltimateArmor", comment = "Enable/Disable The Ultimate Armor from the Game")
    public static boolean enableTheUltimateArmor = true;
    @ModConfigProperty(category = "Armors.Ardite.Registry", name = "enableArditeArmor", comment = "Enable/Disable the Ardite Armors from the Game")
    public static boolean enableArditeArmor = true;
    @ModConfigProperty(category = "Armors.Cobalt.Registry", name = "enableCobaltArmor", comment = "Enable/Disable the Cobalt Armors from the Game")
    public static boolean enableCobaltArmor = true;
    @ModConfigProperty(category = "Armors.Manyullyn.Registry", name = "enableManyullynArmor", comment = "Enable/Disable the Manyullyn Armors from the Game")
    public static boolean enableManyullynArmor = true;
    @ModConfigProperty(category = "Armors.PigIron.Registry", name = "enablePigIronArmor", comment = "Enable/Disable the Pig Iron Armors from the Game")
    public static boolean enablePigIronArmor = true;
    @ModConfigProperty(category = "Armors.KnightSlime.Registry", name = "enableKnightSlimeArmor", comment = "Enable/Disable the Knight Slime Armors from the Game")
    public static boolean enableKnightSlimeArmor = true;
    @ModConfigProperty(category = "Armors.Chicken.Registry", name = "enableChickenArmor", comment = "Enable/Disable the Chicken Armors from the Game")
    public static boolean enableChickenArmor = true;
    @ModConfigProperty(category = "Armors.Slime.Registry", name = "enableSlimeArmor", comment = "Enable/Disable the Slime Armors from the Game")
    public static boolean enableSlimeArmor = true;

    //TheUltimateArmor
    @ModConfigProperty(category = "Armors.Ultimate.Registry", name = "makeTheUltimateArmorUnbreakable", comment = "Sets The Ultimate Armor Unbreakable")
    public static boolean makeTheUltimateArmorUnbreakable = false;
    //FlightAbility
    @ModConfigProperty(category = "FlightAbility", name = "enableFlightAbility", comment = "Enable/Disable the Armors Flight")
    public static boolean enableFlightAbility = true;
    //EffectLevel
    @ModConfigProperty(category = "Armors.Coal.Effects", name = "coalArmorEffectLevel", comment = "Set the level of the Effect by the Coal Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int coalArmorEffectLevel = 0;
    @ModConfigProperty(category = "Armors.Lapis.Effects", name = "lapisArmorEffectLevel", comment = "Set the level of the Effect by the Lapis Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int lapisArmorEffectLevel = 0;
    @ModConfigProperty(category = "Armors.Redstone.Effects", name = "redstoneArmorEffectLevel", comment = "Set the level of the Effect by the Redstone Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int redstoneArmorEffectLevel = 1;
    @ModConfigProperty(category = "Armors.Emerald.Effects", name = "emeraldArmorEffectLevel", comment = "Set the level of the Effect by the Emerald Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int emeraldArmorEffectLevel = 1;
    @ModConfigProperty(category = "Armors.Obsidian.Effects", name = "obsidianArmorEffectLevel", comment = "Set the level of the Effect by the Obsidian Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int obsidianArmorEffectLevel = 0;
    @ModConfigProperty(category = "Armors.Lava.Effects", name = "lavaArmorEffectLevel", comment = "Set the level of the Effect by the Lava Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int lavaArmorEffectLevel = 0;
    @ModConfigProperty(category = "Armors.Chicken.Effects", name = "chickenArmorEffectLevel", comment = "Set the level of the Effect by the Chicken Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int chickenArmorEffectLevel = 4;
    @ModConfigProperty(category = "Armors.Slime.Effects", name = "slimeArmorEffectLevel", comment = "Set the level of the Effect by the Slime Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int slimeArmorEffectLevel = 2;
    @ModConfigProperty(category = "Armors.Guardian.Effects", name = "guardianArmorEffectLevel", comment = "Set the level of the Effect by the Guardian Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int guardianArmorEffectLevel = 0;
    @ModConfigProperty(category = "Armors.SuperStar.Effects", name = "superStarArmorEffectLevel", comment = "Set the level of the Effect by the Super Star Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int superStarArmorEffectLevel = 1;
    @ModConfigProperty(category = "Armors.Ultimate.Effects", name = "ultimateArmorEffectLevel", comment = "Set the level of the Effect by The Ultimate Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int[] ultimateArmorEffectLevels = new int[]{0, 1, 0};
    @ModConfigProperty(category = "Armors.Ardite.Effects", name = "arditeArmorEffectLevel", comment = "Set the level of the Effect by the Ardite Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int arditeArmorEffectLevel = 0;
    @ModConfigProperty(category = "Armors.Cobalt.Effects", name = "cobaltArmorEffectLevel", comment = "Set the level of the Effect by the Cobalt Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int cobaltArmorEffectLevel = 2;
    @ModConfigProperty(category = "Armors.Manyullyn.Effects", name = "manyullynArmorEffectLevel", comment = "Set the level of the Effect by the Manyullyn Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int manyullynArmorEffectLevel = 1;
    @ModConfigProperty(category = "Armors.KnightSlime.Effects", name = "knightSlimeArmorEffectLevel", comment = "Set the level of the Effect by the Knight Slime Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int knightSlimeArmorEffectLevel = 1;
    @ModConfigProperty(category = "Armors.PigIron.Effects", name = "pigIronArmorEffectLevel", comment = "Set the level of the Effect by the Pig Iron Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int pigIronArmorEffectLevel = 0;

    //WorldGeneration
    @ModConfigProperty(category = "WorldGeneration.Overworld", name = "enableLavaCrystalOreOverworldGen", comment = "Enable/Disable The Lava Crystal World Generation in the dimension `Overworld`")
    public static boolean enableLavaCrystalOverworldGen = true;
    @ModConfigProperty(category = "WorldGeneration.TheEnd", name = "enableLavaCrystalOreTheEndGen", comment = "Enable/Disable The Lava Crystal World Generation in the dimension `The End`")
    public static boolean enableLavaCrystalTheEndGen = false;
    @ModConfigProperty(category = "WorldGeneration.TheNether", name = "enableLavaCrystalOreTheNetherGen", comment = "Enable/Disable The Lava Crystal World Generation in the dimension `The Nether`")
    public static boolean enableLavaCrystalTheNetherGen = false;
    @ModConfigProperty(category = "WorldGeneration.Overworld", name = "lavaCrystalOverworldRarityWorkingOne", comment = "Set the rarity level of the Lava Crystal Generation in the dimension `Overworld`")
    public static int lavaCrystalOverworldRarityWorkingOne = 10;
    @ModConfigProperty(category = "WorldGeneration.Overworld", name = "lavaCrystalOverworldMinYSpawn", comment = "Set the min POS_Y level of the Lava Crystal Generation in the dimension `Overworld`")
    public static int lavaCrystalOverworldMinYSpawn = 6;
    @ModConfigProperty(category = "WorldGeneration.Overworld", name = "lavaCrystalOverworldMaxYSpawn", comment = "Set the max POS_Y level of the Lava Crystal Generation in the dimension `Overworld`")
    public static int lavaCrystalOverworldMaxYSpawn = 16;
    @ModConfigProperty(category = "WorldGeneration.TheEnd", name = "lavaCrystalTheEndRarity", comment = "Set the rarity level of the Lava Crystal Generation in the dimension `The End`")
    public static int lavaCrystalTheEndRarity = 0;
    @ModConfigProperty(category = "WorldGeneration.TheEnd", name = "lavaCrystalTheEndMinYSpawn", comment = "Set the min POS_Y level of the Lava Crystal Generation in the dimension `The End`")
    public static int lavaCrystalTheEndMinYSpawn = 0;
    @ModConfigProperty(category = "WorldGeneration.TheEnd", name = "lavaCrystalTheEndMaxYSpawn", comment = "Set the max POS_Y level of the Lava Crystal Generation in the dimension `The End`")
    public static int lavaCrystalTheEndMaxYSpawn = 0;
    @ModConfigProperty(category = "WorldGeneration.TheNether", name = "lavaCrystalTheNetherRarity", comment = "Set the rarity level of the Lava Crystal Generation in the dimension `The Nether`")
    public static int lavaCrystalTheNetherRarity = 0;
    @ModConfigProperty(category = "WorldGeneration.TheNether", name = "lavaCrystalTheNetherMinYSpawn", comment = "Set the min POS_Y level of the Lava Crystal Generation in the dimension `The Nether`")
    public static int lavaCrystalTheNetherMinYSpawn = 0;
    @ModConfigProperty(category = "WorldGeneration.TheNether", name = "lavaCrystalTheNetherMaxYSpawn", comment = "Set the max POS_Y level of the Lava Crystal Generation in the dimension `The Nether`")
    public static int lavaCrystalTheNetherMaxYSpawn = 0;
    @ModConfigProperty(category = "WorldGeneration.Overworld", name = "lavaCrystalOverworldVeinAmountWorking", comment = "Set the vein amount of the Lava Crystal Generation in the dimension `Overworld`")
    public static int lavaCrystalOverworldVeinAmountWorking = 4;
    @ModConfigProperty(category = "WorldGeneration.TheEnd", name = "lavaCrystalTheEndVeinAmount", comment = "Set the vein amount of the Lava Crystal Generation in the dimension `The End`")
    public static int lavaCrystalTheEndVeinAmount = 0;
    @ModConfigProperty(category = "WorldGeneration.TheNether", name = "lavaCrystalTheNetherVeinAmount", comment = "Set the vein amount of the Lava Crystal Generation in the dimension `The Nether`")
    public static int lavaCrystalTheNetherVeinAmount = 0;
    @ModConfigProperty(category = "WorldGeneration.TowerGeneration", name = "towerGenSpawnNeedOfChance", comment = "Set the spawn chance need of the Tower Generation in the Overworld")
    public static int towerGenSpawnNeedOfChance = 7000;
    @ModConfigProperty(category = "WorldGeneration.TowerGeneration", name = "towerGenSpawnChance", comment = "Set the spawn chance of the Tower Generation in the Overworld")
    public static int towerGenSpawnChance = 1;
    @ModConfigProperty(category = "WorldGeneration.TowerGeneration", name = "enableTowerGen", comment = "Enable/Disable the Tower Generation")
    public static boolean enableTowerGen = true;

    //TinkersEffects
    @ModConfigProperty(category = "Armors.Ardite.Effects", name = "enableArditeArmorEffects", comment = "Enable/Disable Ardite Armor Effects")
    public static boolean enableArditeArmorEffect = true;
    @ModConfigProperty(category = "Armors.Cobalt.Effects", name = "enableCobaltArmorEffects", comment = "Enable/Disable Cobalt Armor Effects")
    public static boolean enableCobaltArmorEffect = true;
    @ModConfigProperty(category = "Armors.Manyullyn.Effects", name = "enableManyullynArmorEffects", comment = "Enable/Disable Manyullyn Armor Effects")
    public static boolean enableManyullynArmorEffect = true;
    @ModConfigProperty(category = "Armors.PigIron.Effects", name = "enablePigIronArmorEffects", comment = "Enable/Disable Manyullym Armor Effects")
    public static boolean enablePigIronArmorEffect = true;
    @ModConfigProperty(category = "Armors.PigIron.Effects", name = "enableKnightSlimeArmorEffects", comment = "Enable/Disable Knight Slime Armor Effects")
    public static boolean enableKnightSlimeArmorEffect = true;
    //TheUltimateArmor
    @ModConfigProperty(category = "Armors.Ultimate.Ability", name = "enableTheUltimateArmorInvincibility", comment = "Enable/Disable The Ultimate Armor Invincibility")
    public static boolean enableTheUltimateArmorInvincibility = false;
    @ModConfigProperty(category = "Armors.Ultimate.DeBuffs", name = "enableTheUltimateArmorDeBuffs", comment = "Enable/Disable The Ultimate Armor DeBuffs")
    public static boolean enableTheUltimateArmorDeBuffs = true;
    //Items.Recipes
    @ModConfigProperty(category = "Registry.Recipes", name = "enableRedstoneAppleRecipes", comment = "Enable/Disable The Redstone Apple Recipes")
    public static boolean enableRedstoneAppleRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableElytraRecipe", comment = "Enable/Disable The Elytra Recipe")
    public static boolean enableElytraRecipe = false;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableArrowRecipes", comment = "Enable/Disable The armorplus arrow recipes")
    public static boolean enableArrowRecipes = true;
    //MobDrops
    @ModConfigProperty(category = "MobDrops.EnderDragon", name = "enderDragonScaleDropAmount", comment = "Set the amount of dropped Ender Dragon Scales that the Ender Dragon will drop")
    public static int enderDragonScaleDropAmount = 12;
    @ModConfigProperty(category = "MobDrops.EnderDragon", name = "enderDragonScaleDrop", comment = "Set the amount of dropped Ender Dragon Scales that the Ender Dragon will drop")
    public static boolean enderDragonScaleDrop = true;
    @ModConfigProperty(category = "MobDrops.WitherBoss", name = "witherBoneWitherBossDropAmount", comment = "Set the amount of dropped Wither Bones that the Wither Boss will drop")
    public static int witherBoneWitherBossDropAmount = 6;
    @ModConfigProperty(category = "MobDrops.WitherBoss", name = "witherBoneWitherBossDrop", comment = "Enable/Disable the drop of Wither Bones from the Wither Boss")
    public static boolean witherBoneWitherBossDrop = true;
    @ModConfigProperty(category = "MobDrops.WitherSkeleton", name = "witherBoneWitherSkeletonDrop", comment = "Enable/Disable the drop of Wither Bones from the Wither Skeleton")
    public static boolean witherBoneWitherSkeletonDrop = true;
    @ModConfigProperty(category = "MobDrops.ElderGuardian", name = "guardianScaleElderDropAmount", comment = "Set the amount of dropped Guardian Scales that the Elder Guardian will drop")
    public static int guardianScaleElderDropAmount = 6;
    @ModConfigProperty(category = "MobDrops.ElderGuardian", name = "guardianScaleElderGuardianDrop", comment = "Enable/Disable the drop of Guardian Scales from the Elder Guardian")
    public static boolean guardianScaleElderGuardianDrop = true;
    @ModConfigProperty(category = "MobDrops.Guardian", name = "guardianScaleGuardianDrop", comment = "Enable/Disable the drop of Guardian Scales from the Guardian")
    public static boolean guardianScaleGuardianDrop = true;

    @ModConfigProperty(category = "Registry.Recipes", name = "enableCoalArmorRecipes", comment = "Enable/Disable The Coal Armor Recipes")
    public static boolean enableCoalArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableCharcoalCoalArmorRecipe", comment = "Enable/Disable The Charcoal Coal Armor Recipes")
    public static boolean enableCharcoalCoalArmorRecipe = false;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableLapisArmorRecipes", comment = "Enable/Disable The Lapis Armor Recipes")
    public static boolean enableLapisArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableRedstoneArmorRecipes", comment = "Enable/Disable The Redstone Armor Recipes")
    public static boolean enableRedstoneArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableEmeraldArmorRecipes", comment = "Enable/Disable The Emerald Armor Recipes")
    public static boolean enableEmeraldArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableObsidianArmorRecipes", comment = "Enable/Disable The Obsidian Armor Recipes")
    public static boolean enableObsidianArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableLavaArmorRecipes", comment = "Enable/Disable The Lava Armor Recipes")
    public static boolean enableLavaArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableSuperStarArmorRecipes", comment = "Enable/Disable The Super Star Armor Recipes")
    public static boolean enableSuperStarArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableEnderDragonArmorRecipes", comment = "Enable/Disable The Ender Dragon Armor Recipes")
    public static boolean enableEnderDragonArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableGuardianArmorRecipes", comment = "Enable/Disable The Guardian Armor Recipes")
    public static boolean enableGuardianArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableTheUltimateArmorRecipes", comment = "Enable/Disable The Ultimate Armor Recipes")
    public static boolean enableTheUltimateArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableChainArmorRecipes", comment = "Enable/Disable The Chain Armors Recipes")
    public static boolean enableChainArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableArditeArmorRecipes", comment = "Enable/Disable The Ardite Armors Recipes")
    public static boolean enableArditeArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableCobaltArmorRecipes", comment = "Enable/Disable The Cobalt Armors Recipes")
    public static boolean enableCobaltArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableManyullynArmorRecipes", comment = "Enable/Disable The Manyullyn Armors Recipes")
    public static boolean enableManyullynArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enablePigIronArmorRecipes", comment = "Enable/Disable The Pig Iron Armors Recipes")
    public static boolean enablePigIronArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableKnightSlimeArmorRecipes", comment = "Enable/Disable The Knight Slime Armors Recipes")
    public static boolean enableKnightSlimeArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableSlimeArmorRecipes", comment = "Enable/Disable The Slime Armors Recipes")
    public static boolean enableSlimeArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableChickenArmorRecipes", comment = "Enable/Disable The Chicken Armors Recipes")
    public static boolean enableChickenArmorRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableSwordsRecipes", comment = "Enable/Disable ArmorPlus Sword's Recipes")
    public static boolean enableSwordsRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableBattleAxesRecipes", comment = "Enable/Disable ArmorPlus Battle Axes's Recipes")
    public static boolean enableBattleAxesRecipes = true;
    @ModConfigProperty(category = "Registry.Recipes", name = "enableBowsRecipes", comment = "Enable/Disable ArmorPlus Bows's Recipes")
    public static boolean enableBowsRecipes = true;

    //EnergyItems
    @ModConfigProperty(category = "EnergyItems", name = "input", comment = "Set the amount of Energy that the item can input\nSword, Helmet, Axe, Shovel, Hoe")
    public static int[] energyInput = new int[]{10, 10, 10, 10, 10};
    @ModConfigProperty(category = "EnergyItems", name = "output", comment = "Set the amount of Energy that the item can output\nSword, Helmet, Axe, Shovel, Hoe")
    public static int[] energyOutput = new int[]{10, 10, 10, 10, 10};
    @ModConfigProperty(category = "EnergyItems", name = "maxCapacity", comment = "Set the max capacity that the item can hold\nSword, Helmet, Axe, Shovel, Hoe")
    public static int[] maxEnergyCapacity = new int[]{3000, 3000, 3000, 3000, 3000};

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
            public boolean isItemRepairable(ItemStack repair, ItemStack easy, ItemStack expert) {
                return false;
            }
        },
        EASY(true) {
            @Override
            public boolean isItemRepairable(ItemStack repair, ItemStack easy, ItemStack expert) {
                return repair.getItem() == easy.getItem() && repair.getMetadata() == easy.getMetadata();
            }
        },
        EXPERT(true) {
            @Override
            public boolean isItemRepairable(ItemStack repair, ItemStack easy, ItemStack expert) {
                return repair.getItem() == expert.getItem() && repair.getMetadata() == expert.getMetadata();
            }
        },
        HELLISH(true) {
            @Override
            public boolean isItemRepairable(ItemStack repair, ItemStack easy, ItemStack expert) {
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

        public abstract boolean isItemRepairable(ItemStack repair, ItemStack easy, ItemStack expert);
    }
}