/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus;

import net.minecraft.item.ItemStack;
import net.thedragonteam.thedragonlib.config.ModConfigProperty;

import static java.util.Arrays.fill;
import static net.minecraft.item.ItemStack.areItemsEqual;
import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EXPERT;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class APConfig {
    //WhiteList
    @ModConfigProperty(category = "items.the_gift_of_the_gods.white_list", name = "whitelistMax", comment = "Set the maximum amount of items that the player can get by the \"The Gift Of The Gods\". \nNote:You will need to have that many WhiteListed Items.")
    public static int whitelistMax = 0;
    @ModConfigProperty(category = "items.the_gift_of_the_gods.white_list", name = "whitelistMin", comment = "Set the minimum amount of items that the player can get by the \"The Gift Of The Gods\". \nNote:Don't change this from 0\"")
    public static int whitelistMin = 0;
    @ModConfigProperty(category = "items.the_gift_of_the_gods.white_list", name = "enableWhiteList", comment = "Enable/Disable the WhiteList")
    public static boolean enableWhiteList = false;
    @ModConfigProperty(category = "items.the_gift_of_the_gods.white_list", name = "whiteListedItems", comment = "Add WhiteListed Items to the \"The Gift Of The Gods\" \nIf You add want to add an item to the whitelist \nYou will need to replace 1 from \"minecraft:dirt\" to the item you want to add")
    public static String[] whiteListedItems = new String[]{"minecraft:dirt"};
    //BlackList
    @ModConfigProperty(category = "items.the_gift_of_the_gods.black_list", name = "enableBlackList", comment = "Enable/Disable the BlackList")
    public static boolean enableBlackList = true;
    @ModConfigProperty(category = "items.the_gift_of_the_gods.black_list", name = "blackListedItems", comment = "Add Blacklisted Items to the \"The Gift Of The Gods\" \nIf You add want to add an item to the blacklist \nYou will need to replace 1 from \"minecraft:dirt\" to the item you want to add")
    public static String[] blackListedItems = new String[]{"minecraft:dirt"};
    //Weapons.*.registry
    @ModConfigProperty(category = "type.coal.registry", name = "coalWeaponItemNameColor", comment = "Set the color name the Coal Weapons will have")
    public static String coalWeaponItemNameColor = "gray";
    @ModConfigProperty(category = "type.lapis.registry", name = "lapisWeaponItemNameColor", comment = "Set the color name the Lapis Weapons will have")
    public static String lapisWeaponItemNameColor = "dark_blue";
    @ModConfigProperty(category = "type.redstone.registry", name = "redstoneWeaponItemNameColor", comment = "Set the color name the Redstone Weapons will have")
    public static String redstoneWeaponItemNameColor = "dark_red";
    @ModConfigProperty(category = "type.emerald.registry", name = "emeraldWeaponItemNameColor", comment = "Set the color name the Emerald Weapons will have")
    public static String emeraldWeaponItemNameColor = "dark_green";
    @ModConfigProperty(category = "type.obsidian.registry", name = "obsidianWeaponItemNameColor", comment = "Set the color name the Obsidian Weapons will have")
    public static String obsidianWeaponItemNameColor = "dark_gray";
    @ModConfigProperty(category = "type.lava.registry", name = "lavaWeaponItemNameColor", comment = "Set the color name the Lava Weapons will have")
    public static String lavaWeaponItemNameColor = "gold";
    @ModConfigProperty(category = "type.super_star.registry", name = "superStarWeaponItemNameColor", comment = "Set the color name the Super Star Weapons will have")
    public static String superStarWeaponItemNameColor = "white";
    @ModConfigProperty(category = "type.ender_dragon.registry", name = "enderDragonWeaponItemNameColor", comment = "Set the color name the Ender Dragon Weapons will have")
    public static String enderDragonWeaponItemNameColor = "dark_purple";
    @ModConfigProperty(category = "type.guardian.registry", name = "guardianWeaponItemNameColor", comment = "Set the color name the Guardian Weapons will have")
    public static String guardianWeaponItemNameColor = "aqua";
    @ModConfigProperty(category = "type.coal.registry", name = "enableCoalWeapons", comment = "Enable/Disable the Coal Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableCoalWeapons = new boolean[3];
    @ModConfigProperty(category = "type.lapis.registry", name = "enableLapisWeapons", comment = "Enable/Disable the Lapis Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableLapisWeapons = new boolean[3];
    @ModConfigProperty(category = "type.redstone.registry", name = "enableRedstoneWeapons", comment = "Enable/Disable the Redstone Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableRedstoneWeapons = new boolean[3];
    @ModConfigProperty(category = "type.emerald.registry", name = "enableEmeraldWeapons", comment = "Enable/Disable the Emerald Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableEmeraldWeapons = new boolean[3];
    @ModConfigProperty(category = "type.obsidian.registry", name = "enableObsidianWeapons", comment = "Enable/Disable the Obsidian Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableObsidianWeapons = new boolean[3];
    @ModConfigProperty(category = "type.lava.registry", name = "enableLavaWeapons", comment = "Enable/Disable the Lava Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableLavaWeapons = new boolean[3];
    @ModConfigProperty(category = "type.guardian.registry", name = "enableGuardianWeapons", comment = "Enable/Disable the Guardian Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableGuardianWeapons = new boolean[3];
    @ModConfigProperty(category = "type.super_star.registry", name = "enableSuperStarWeapons", comment = "Enable/Disable the Super Star Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableSuperStarWeapons = new boolean[3];
    @ModConfigProperty(category = "type.ender_dragon.registry", name = "enableEnderDragonWeapons", comment = "Enable/Disable the Ender Dragon Weapons from the Game (Sword, Battle Axe, Bow)")
    public static boolean[] enableEnderDragonWeapons = new boolean[3];
    //type.*
    @ModConfigProperty(category = "type.coal", name = "coalSwordDamage", comment = "Set the amount of damage the Coal Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double coalSwordDamage = 0.5;
    @ModConfigProperty(category = "type.lapis", name = "lapisSwordDamage", comment = "Set the amount of damage the Lapis Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double lapisSwordDamage = 1.0;
    @ModConfigProperty(category = "type.redstone", name = "redstoneSwordDamage", comment = "Set the amount of damage the Redstone Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double redstoneSwordDamage = 1.0;
    @ModConfigProperty(category = "type.emerald", name = "emeraldSwordDamage", comment = "Set the amount of damage the Emerald Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double emeraldSwordDamage = 1.5;
    @ModConfigProperty(category = "type.obsidian", name = "obsidianSwordDamage", comment = "Set the amount of damage the Obsidian Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double obsidianSwordDamage = 4.0;
    @ModConfigProperty(category = "type.lava", name = "lavaSwordDamage", comment = "Set the amount of damage the Lava Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double lavaSwordDamage = 4.5;
    @ModConfigProperty(category = "type.guardian", name = "guardianSwordDamage", comment = "Set the amount of damage the Guardian Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double guardianSwordDamage = 6.0;
    @ModConfigProperty(category = "type.super_star", name = "superStarSwordDamage", comment = "Set the amount of damage the Super Star Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double superStarSwordDamage = 7.0;
    @ModConfigProperty(category = "type.ender_dragon", name = "enderDragonSwordDamage", comment = "Set the amount of damage the Ender Dragon Sword will do (Additional +4 damage will be added automatically by minecraft)")
    public static double enderDragonSwordDamage = 8.0;
    @ModConfigProperty(category = "type.coal", name = "coalSwordDurability", comment = "Set the amount of durability the Coal Sword have")
    public static int coalSwordDurability = 12;
    @ModConfigProperty(category = "type.lapis", name = "lapisSwordDurability", comment = "Set the amount of durability the Lapis Sword have")
    public static int lapisSwordDurability = 36;
    @ModConfigProperty(category = "type.redstone", name = "redstoneSwordDurability", comment = "Set the amount of durability the Redstone Sword have")
    public static int redstoneSwordDurability = 200;
    @ModConfigProperty(category = "type.emerald", name = "emeraldSwordDurability", comment = "Set the amount of durability the Emerald Sword have")
    public static int emeraldSwordDurability = 1561;
    @ModConfigProperty(category = "type.obsidian", name = "obsidianSwordDurability", comment = "Set the amount of durability the Obsidian Sword have")
    public static int obsidianSwordDurability = 1500;
    @ModConfigProperty(category = "type.lava", name = "lavaSwordDurability", comment = "Set the amount of durability the Lava Sword have")
    public static int lavaSwordDurability = 1750;
    @ModConfigProperty(category = "type.guardian", name = "guardianSwordDurability", comment = "Set the amount of durability the Guardian Sword have")
    public static int guardianSwordDurability = 1800;
    @ModConfigProperty(category = "type.super_star", name = "superStarSwordDurability", comment = "Set the amount of durability the Super Star Sword have")
    public static int superStarSwordDurability = 1950;
    @ModConfigProperty(category = "type.ender_dragon", name = "enderDragonSwordDurability", comment = "Set the amount of durability the Ender Dragon Sword have")
    public static int enderDragonSwordDurability = 2310;
    //type.*.effects
    @ModConfigProperty(category = "type.coal.effects", name = "coalWeaponsEffectLevel", comment = "Set the level of the Effect by the Coal Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int coalWeaponsEffectLevel = 0;
    @ModConfigProperty(category = "type.lapis.effects", name = "lapisWeaponsEffectLevel", comment = "Set the level of the Effect by the Lapis Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int lapisWeaponsEffectLevel = 1;
    @ModConfigProperty(category = "type.redstone.effects", name = "redstoneWeaponsEffectLevel", comment = "Set the level of the Effect by the Redstone Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int redstoneWeaponsEffectLevel = 1;
    @ModConfigProperty(category = "type.emerald.effects", name = "emeraldWeaponsEffectLevel", comment = "Set the level of the Effect by the Emerald Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int emeraldWeaponsEffectLevel = 1;
    @ModConfigProperty(category = "type.obsidian.effects", name = "obsidianWeaponsEffectLevel", comment = "Set the level of the Effect by the Obsidian Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int obsidianWeaponsEffectLevel = 1;
    @ModConfigProperty(category = "type.guardian.effects", name = "guardianWeaponsEffectLevel", comment = "Set the level of the Effect by the Guardian Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int guardianWeaponsEffectLevel = 1;
    @ModConfigProperty(category = "type.super_star.effects", name = "superStarWeaponsEffectLevel", comment = "Set the level of the Effect by the Super Star Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int superStarWeaponsEffectLevel = 1;
    @ModConfigProperty(category = "type.super_star.effects", name = "enderDragonWeaponsEffectLevel", comment = "Set the level of the Effect by the Super Star Weapons. (0 = level 1, 1 = level 2 etc.)")
    public static int enderDragonWeaponsEffectLevel = 3;
    @ModConfigProperty(category = "type.coal.effects", name = "coalWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Coal Weapons will have (to disable the effect set the effects \'false\')")
    public static String coalWeaponsAddPotionEffect = "blindness";
    @ModConfigProperty(category = "type.lapis.effects", name = "lapisWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Lapis Weapons will have (to disable the effect set the effects \'false\')")
    public static String lapisWeaponsAddPotionEffect = "nausea";
    @ModConfigProperty(category = "type.redstone.effects", name = "redstoneWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Redstone Weapons will have (to disable the effect set the effects \'false\')")
    public static String redstoneWeaponsAddPotionEffect = "slowness";
    @ModConfigProperty(category = "type.emerald.effects", name = "emeraldWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Emerald Weapons will have (to disable the effect set the effects \'false\')")
    public static String emeraldWeaponsAddPotionEffect = "mining_fatigue";
    @ModConfigProperty(category = "type.obsidian.effects", name = "obsidianWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Obsidian Weapons will have (to disable the effect set the effects \'false\')")
    public static String obsidianWeaponsAddPotionEffect = "weakness";
    @ModConfigProperty(category = "type.guardian.effects", name = "guardianWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Guardian Weapons will have (to disable the effect set the effects \'false\')")
    public static String guardianWeaponsAddPotionEffect = "nausea";
    @ModConfigProperty(category = "type.super_star.effects", name = "superStarWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Super Star Weapons will have (to disable the effect set the effects \'false\')")
    public static String superStarWeaponsAddPotionEffect = "wither";
    @ModConfigProperty(category = "type.super_star.effects", name = "enderDragonWeaponsAddPotionEffect", comment = "Adds the Potion Effect the Super Star Weapons will have (to disable the effect set the effects \'false\')")
    public static String enderDragonWeaponsAddPotionEffect = "wither";
    @ModConfigProperty(category = "type.coal.effects", name = "enableCoalWeaponsEffects", comment = "Enable/Disable the Potion Effect the Coal Weapons will have")
    public static boolean enableCoalWeaponsEffects = true;
    @ModConfigProperty(category = "type.lapis.effects", name = "enableLapisWeaponsEffects", comment = "Enable/Disable the Potion Effect the Lapis Weapons will have")
    public static boolean enableLapisWeaponsEffects = true;
    @ModConfigProperty(category = "type.redstone.effects", name = "enableRedstoneWeaponsEffects", comment = "Enable/Disable the Potion Effect the Redstone Weapons will have")
    public static boolean enableRedstoneWeaponsEffects = true;
    @ModConfigProperty(category = "type.emerald.effects", name = "enableEmeraldWeaponsEffects", comment = "Enable/Disable the Potion Effect the Emerald Weapons will have")
    public static boolean enableEmeraldWeaponsEffects = true;
    @ModConfigProperty(category = "type.obsidian.effects", name = "enableObsidianWeaponsEffects", comment = "Enable/Disable the Potion Effect the Obsidian Weapons will have")
    public static boolean enableObsidianWeaponsEffects = true;
    @ModConfigProperty(category = "type.guardian.effects", name = "enableGuardianWeaponsEffects", comment = "Enable/Disable the Potion Effect the Guardian Weapons will have")
    public static boolean enableGuardianWeaponsEffects = true;
    @ModConfigProperty(category = "type.super_star.effects", name = "enableSuperStarWeaponsEffects", comment = "Enable/Disable the Potion Effect the Super Star Weapons will have")
    public static boolean enableSuperStarWeaponsEffects = true;
    @ModConfigProperty(category = "type.super_star.effects", name = "enableEnderDragonWeaponsEffects", comment = "Enable/Disable the Potion Effect the Super Star Weapons will have")
    public static boolean enableEnderDragonWeaponsEffects = true;
    @ModConfigProperty(category = "type.coal", name = "coalBattleAxeDamage", comment = "Set the amount of damage the Coal BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double coalBattleAxeDamage = 2.5;
    @ModConfigProperty(category = "type.lapis", name = "lapisBattleAxeDamage", comment = "Set the amount of damage the Lapis BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double lapisBattleAxeDamage = 3.0;
    @ModConfigProperty(category = "type.redstone", name = "redstoneBattleAxeDamage", comment = "Set the amount of damage the Redstone BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double redstoneBattleAxeDamage = 3.0;
    @ModConfigProperty(category = "type.emerald", name = "emeraldBattleAxeDamage", comment = "Set the amount of damage the Emerald BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double emeraldBattleAxeDamage = 3.5;
    @ModConfigProperty(category = "type.obsidian", name = "obsidianBattleAxeDamage", comment = "Set the amount of damage the Obsidian BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double obsidianBattleAxeDamage = 6.0;
    @ModConfigProperty(category = "type.lava", name = "lavaBattleAxeDamage", comment = "Set the amount of damage the Lava BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double lavaBattleAxeDamage = 6.5;
    @ModConfigProperty(category = "type.guardian", name = "guardianBattleAxeDamage", comment = "Set the amount of damage the Guardian BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double guardianBattleAxeDamage = 7.0;
    @ModConfigProperty(category = "type.super_star", name = "superStarBattleAxeDamage", comment = "Set the amount of damage the Super Star BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double superStarBattleAxeDamage = 8.0;
    @ModConfigProperty(category = "type.ender_dragon", name = "enderDragonBattleAxeDamage", comment = "Set the amount of damage the Ender Dragon BattleAxe will do (Additional +4 damage will be added automatically by minecraft)")
    public static double enderDragonBattleAxeDamage = 10.0;
    @ModConfigProperty(category = "type.coal", name = "coalBattleAxeDurability", comment = "Set the amount of durability the Coal BattleAxe have")
    public static int coalBattleAxeDurability = 24;
    @ModConfigProperty(category = "type.lapis", name = "lapisBattleAxeDurability", comment = "Set the amount of durability the Lapis BattleAxe have")
    public static int lapisBattleAxeDurability = 72;
    @ModConfigProperty(category = "type.redstone", name = "redstoneBattleAxeDurability", comment = "Set the amount of durability the Redstone BattleAxe have")
    public static int redstoneBattleAxeDurability = 200;
    @ModConfigProperty(category = "type.emerald", name = "emeraldBattleAxeDurability", comment = "Set the amount of durability the Emerald BattleAxe have")
    public static int emeraldBattleAxeDurability = 1561;
    @ModConfigProperty(category = "type.obsidian", name = "obsidianBattleAxeDurability", comment = "Set the amount of durability the Obsidian BattleAxe have")
    public static int obsidianBattleAxeDurability = 1500;
    @ModConfigProperty(category = "type.lava", name = "lavaBattleAxeDurability", comment = "Set the amount of durability the Lava BattleAxe have")
    public static int lavaBattleAxeDurability = 1750;
    @ModConfigProperty(category = "type.guardian", name = "guardianBattleAxeDurability", comment = "Set the amount of durability the Guardian BattleAxe have")
    public static int guardianBattleAxeDurability = 1800;
    @ModConfigProperty(category = "type.super_star", name = "superStarBattleAxeDurability", comment = "Set the amount of durability the Super Star BattleAxe have")
    public static int superStarBattleAxeDurability = 1950;
    @ModConfigProperty(category = "type.ender_dragon", name = "enderDragonBattleAxeDurability", comment = "Set the amount of durability the Ender Dragon BattleAxe have")
    public static int enderDragonBattleAxeDurability = 2310;
    //Weapons.*
    @ModConfigProperty(category = "type.coal", name = "coalBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Coal Bow will do")
    public static double coalBowArrowBonusDamage = -2.0;
    @ModConfigProperty(category = "type.lapis", name = "lapisBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Lapis Bow will do")
    public static double lapisBowArrowBonusDamage = -1.5;
    @ModConfigProperty(category = "type.redstone", name = "redstoneBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Redstone Bow will do")
    public static double redstoneBowArrowBonusDamage = -1.5;
    @ModConfigProperty(category = "type.emerald", name = "emeraldBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Emerald Bow will do")
    public static double emeraldBowArrowBonusDamage = -0.5;
    @ModConfigProperty(category = "type.obsidian", name = "obsidianBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Obsidian Bow will do")
    public static double obsidianBowArrowBonusDamage = 0.0;
    @ModConfigProperty(category = "type.lava", name = "lavaBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Lava Bow will do")
    public static double lavaBowArrowBonusDamage = 0.5;
    @ModConfigProperty(category = "type.guardian", name = "guardianBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Guardian Bow will do")
    public static double guardianBowArrowBonusDamage = 1.5;
    @ModConfigProperty(category = "type.super_star", name = "superStarBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Super Star Bow will do")
    public static double superStarBowArrowBonusDamage = 1.5;
    @ModConfigProperty(category = "type.ender_dragon", name = "enderDragonBowArrowBonusDamage", comment = "Set the amount of bonus arrow damage the Ender Dragon Bow will do")
    public static double enderDragonBowArrowBonusDamage = 1.5;
    @ModConfigProperty(category = "type.coal", name = "coalBowDurability", comment = "Set the amount of durability the Coal Bow have")
    public static int coalBowDurability = 59;
    @ModConfigProperty(category = "type.lapis", name = "lapisBowDurability", comment = "Set the amount of durability the Lapis Bow have")
    public static int lapisBowDurability = 18;
    @ModConfigProperty(category = "type.redstone", name = "redstoneBowDurability", comment = "Set the amount of durability the Redstone Bow have")
    public static int redstoneBowDurability = 200;
    @ModConfigProperty(category = "type.emerald", name = "emeraldBowDurability", comment = "Set the amount of durability the Emerald Bow have")
    public static int emeraldBowDurability = 1561;
    @ModConfigProperty(category = "type.obsidian", name = "obsidianBowDurability", comment = "Set the amount of durability the Obsidian Bow have")
    public static int obsidianBowDurability = 1500;
    @ModConfigProperty(category = "type.lava", name = "lavaBowDurability", comment = "Set the amount of durability the Lava Bow have")
    public static int lavaBowDurability = 1750;
    @ModConfigProperty(category = "type.guardian", name = "guardianBowDurability", comment = "Set the amount of durability the Guardian Bow have")
    public static int guardianBowDurability = 1800;
    @ModConfigProperty(category = "type.super_star", name = "superStarBowDurability", comment = "Set the amount of durability the Super Star Bow have")
    public static int superStarBowDurability = 1950;
    @ModConfigProperty(category = "type.ender_dragon", name = "enderDragonBowDurability", comment = "Set the amount of durability the Ender Dragon Bow have")
    public static int enderDragonBowDurability = 2310;
    //Armors.*.effects
    @ModConfigProperty(category = "type.coal.effects", name = "coalArmorAddPotionEffect", comment = "Adds the Potion Effect the Coal Armor will have (to disable the effect set the effects \'false\')")
    public static String coalArmorAddPotionEffect = "night_vision";
    @ModConfigProperty(category = "type.lapis.effects", name = "lapisArmorAddPotionEffect", comment = "Adds the Potion Effect the Lapis Armor will have (to disable the effect set the effects \'false\')")
    public static String lapisArmorAddPotionEffect = "water_breathing";
    @ModConfigProperty(category = "type.redstone.effects", name = "redstoneArmorAddPotionEffect", comment = "Adds the Potion Effect the Redstone Armor will have (to disable the effect set the effects \'false\')")
    public static String redstoneArmorAddPotionEffect = "haste";
    @ModConfigProperty(category = "type.emerald.effects", name = "emeraldArmorAddPotionEffect", comment = "Adds the Potion Effect the Emerald Armor will have (to disable the effect set the effects \'false\')")
    public static String emeraldArmorAddPotionEffect = "speed";
    @ModConfigProperty(category = "type.obsidian.effects", name = "obsidianArmorAddPotionEffect", comment = "Adds the Potion Effect the Obsidian Armor will have (to disable the effect set the effects \'false\')")
    public static String obsidianArmorAddPotionEffect = "resistance";
    @ModConfigProperty(category = "type.lava.effects", name = "lavaArmorAddPotionEffect", comment = "Adds the Potion Effect the Lava Armor will have (to disable the effect set the effects \'false\')")
    public static String lavaArmorAddPotionEffect = "fire_resistance";
    @ModConfigProperty(category = "type.lava.effects", name = "enableLavaArmorOnWaterTouchDeBuff", comment = "Enables/Disables the DeBuffs that the armor will get when touching water without Water Breathing Potion. \naka nothing happens when player wears this armor while in water.")
    public static boolean enableLavaArmorOnWaterTouchDeBuff = true;
    @ModConfigProperty(category = "type.guardian.effects", name = "guardianArmorAddPotionEffect", comment = "Adds the Potion Effect the Guardian Armor will have (to disable the effect set the effects \'false\')")
    public static String guardianArmorAddPotionEffect = "water_breathing";
    @ModConfigProperty(category = "type.super_star.effects", name = "superStarArmorAddPotionEffect", comment = "Adds the Potion Effect the Super Star Armor will have (to disable the effect set the effects \'false\')")
    public static String superStarArmorAddPotionEffect = "regeneration";
    @ModConfigProperty(category = "type.ultimate.effects", name = "theUltimateArmorAddPotionEffect", comment = "Adds the Potion Effects the Ultimate Armor will have (to disable the effect set the effects \'false\')")
    public static String[] theUltimateArmorAddPotionEffect = new String[]{("saturation"), ("regeneration"), ("water_breathing")};
    @ModConfigProperty(category = "type.ultimate.effects", name = "theUltimateArmorRemovePotionEffect", comment = "The Potion Effect that the Ultimate Dragon Armor will be Removing")
    public static String theUltimateArmorRemovePotionEffect = "wither";
    @ModConfigProperty(category = "type.super_star.effects", name = "superStarArmorRemovePotionEffect", comment = "The Potion Effect that the Super Star Armor will be Removing")
    public static String superStarArmorRemovePotionEffect = "wither";
    @ModConfigProperty(category = "type.ender_dragon.effects", name = "enderDragonArmorRemovePotionEffect", comment = "The Potion Effect that the Ender Dragon Armor will be Removing")
    public static String enderDragonArmorRemovePotionEffect = "wither";
    @ModConfigProperty(category = "type.ardite.effects", name = "arditeArmorAddPotionEffect", comment = "Adds the Potion Effect the Ardite Armor will have (to disable the effect set the effects \'false\')")
    public static String arditeArmorAddPotionEffect = "fire_resistance";
    @ModConfigProperty(category = "type.cobalt.effects", name = "cobaltArmorAddPotionEffect", comment = "Adds the Potion Effect the Cobalt Armor will have (to disable the effect set the effects \'false\')")
    public static String cobaltArmorAddPotionEffect = "haste";
    @ModConfigProperty(category = "type.manyullyn.effects", name = "manyullynArmorAddPotionEffect", comment = "Adds the Potion Effect the Manyullyn Armor will have (to disable the effect set the effects \'false\')")
    public static String manyullynArmorAddPotionEffect = "strength";
    @ModConfigProperty(category = "type.pig_iron.effects", name = "pigIronArmorAddPotionEffect", comment = "Adds the Potion Effect the Pig Iron Armor will have (to disable the effect set the effects \'false\')")
    public static String pigIronArmorAddPotionEffect = "saturation";
    @ModConfigProperty(category = "type.knight_slime.effects", name = "knightSlimeArmorAddPotionEffect", comment = "Adds the Potion Effect the Knight Slime Armor will have (to disable the effect set the effects \'false\')")
    public static String knightSlimeArmorAddPotionEffect = "jump_boost";
    @ModConfigProperty(category = "type.chicken.effects", name = "chickenArmorAddPotionEffect", comment = "Adds the Potion Effect the Chicken Armor will have (to disable the effect set the effects \'false\')")
    public static String chickenArmorAddPotionEffect = "speed";
    @ModConfigProperty(category = "type.slime.effects", name = "slimeArmorAddPotionEffect", comment = "Adds the Potion Effect the Slime Armor will have (to disable the effect set the effects \'false\')")
    public static String slimeArmorAddPotionEffect = "jump_boost";
    //Armors.*.registry
    @ModConfigProperty(category = "type.coal.registry", name = "coalArmorItemNameColor", comment = "Set the color name the Coal Armor will have")
    public static String coalArmorItemNameColor = "gray";
    @ModConfigProperty(category = "type.lapis.registry", name = "lapisArmorItemNameColor", comment = "Set the color name the Lapis Armor will have")
    public static String lapisArmorItemNameColor = "dark_blue";
    @ModConfigProperty(category = "type.redstone.registry", name = "redstoneArmorItemNameColor", comment = "Set the color name the Redstone Armor will have")
    public static String redstoneArmorItemNameColor = "dark_red";
    @ModConfigProperty(category = "type.emerald.registry", name = "emeraldArmorItemNameColor", comment = "Set the color name the Emerald Armor will have")
    public static String emeraldArmorItemNameColor = "dark_green";
    @ModConfigProperty(category = "type.obsidian.registry", name = "obsidianArmorItemNameColor", comment = "Set the color name the Obsidian Armor will have")
    public static String obsidianArmorItemNameColor = "dark_gray";
    @ModConfigProperty(category = "type.lava.registry", name = "lavaArmorItemNameColor", comment = "Set the color name the Lava Armor will have")
    public static String lavaArmorItemNameColor = "gold";
    @ModConfigProperty(category = "type.super_star.registry", name = "superStarArmorItemNameColor", comment = "Set the color name the Super Star Armor will have")
    public static String superStarArmorItemNameColor = "white";
    @ModConfigProperty(category = "type.ender_dragon.registry", name = "enderDragonArmorItemNameColor", comment = "Set the color name the Ender Dragon Armor will have")
    public static String enderDragonArmorItemNameColor = "dark_purple";
    @ModConfigProperty(category = "type.guardian.registry", name = "guardianArmorItemNameColor", comment = "Set the color name the Guardian Armor will have")
    public static String guardianArmorItemNameColor = "aqua";
    @ModConfigProperty(category = "type.ultimate.registry", name = "theUltimateArmorItemNameColor", comment = "Set the color name the Ultimate Armor will have")
    public static String theUltimateArmorItemNameColor = "green";
    @ModConfigProperty(category = "type.ardite.registry", name = "arditeArmorItemNameColor", comment = "Set the color name the Ardite Armor will have")
    public static String arditeArmorItemNameColor = "dark_red";
    @ModConfigProperty(category = "type.cobalt.registry", name = "cobaltArmorItemNameColor", comment = "Set the color name the Cobalt Armor will have")
    public static String cobaltArmorItemNameColor = "blue";
    @ModConfigProperty(category = "type.manyullyn.registry", name = "manyullynArmorItemNameColor", comment = "Set the color name the Manyullyn Armor will have")
    public static String manyullynArmorItemNameColor = "dark_purple";
    @ModConfigProperty(category = "type.pig_iron.registry", name = "pigIronArmorItemNameColor", comment = "Set the color name the Pig Iron Armor will have")
    public static String pigIronArmorItemNameColor = "light_purple";
    @ModConfigProperty(category = "type.knight_slime.registry", name = "knightSlimeArmorItemNameColor", comment = "Set the color name the Knight Slime Armor will have")
    public static String knightSlimeArmorItemNameColor = "dark_purple";
    @ModConfigProperty(category = "type.chicken.registry", name = "chickenArmorItemNameColor", comment = "Set the color name the Chicken Armor will have")
    public static String chickenArmorItemNameColor = "aqua";
    @ModConfigProperty(category = "type.slime.registry", name = "slimeArmorItemNameColor", comment = "Set the color name the Slime Armor will have")
    public static String slimeArmorItemNameColor = "green";
    @ModConfigProperty(category = "type.coal.registry", name = "coalArmorToughnessPoints", comment = "Set the amount of toughness points the Coal Armor will have")
    public static double coalArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "type.lapis.registry", name = "lapisArmorToughnessPoints", comment = "Set the amount of toughness points the Lapis Armor will have")
    public static double lapisArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "type.redstone.registry", name = "redstoneArmorToughnessPoints", comment = "Set the amount of toughness points the Redstone Armor will have")
    public static double redstoneArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "type.emerald.registry", name = "emeraldArmorToughnessPoints", comment = "Set the amount of toughness points the Emerald Armor will have")
    public static double emeraldArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "type.obsidian.registry", name = "obsidianArmorToughnessPoints", comment = "Set the amount of toughness points the Obsidian Armor will have")
    public static double obsidianArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "type.lava.registry", name = "lavaArmorToughnessPoints", comment = "Set the amount of toughness points the Lava Armor will have")
    public static double lavaArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "type.super_star.registry", name = "superStarArmorToughnessPoints", comment = "Set the amount of toughness points the Super Star Armor will have")
    public static double superStarArmorToughnessPoints = 2.0;
    @ModConfigProperty(category = "type.ender_dragon.registry", name = "enderDragonArmorToughnessPoints", comment = "Set the amount of toughness points the Ender Dragon Armor will have")
    public static double enderDragonArmorToughnessPoints = 2.0;
    @ModConfigProperty(category = "type.guardian.registry", name = "guardianArmorToughnessPoints", comment = "Set the amount of toughness points the Guardian Armor will have")
    public static double guardianArmorToughnessPoints = 2.0;
    @ModConfigProperty(category = "type.ultimate.registry", name = "theUltimateArmorToughnessPoints", comment = "Set the amount of toughness points the Ultimate Armor will have")
    public static double theUltimateArmorToughnessPoints = 3.0;
    @ModConfigProperty(category = "type.ardite.registry", name = "arditeArmorToughnessPoints", comment = "Set the amount of toughness points the Ardite Armor will have")
    public static double arditeArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "type.cobalt.registry", name = "cobaltArmorToughnessPoints", comment = "Set the amount of toughness points the Cobalt Armor will have")
    public static double cobaltArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "type.manyullyn.registry", name = "manyullynArmorToughnessPoints", comment = "Set the amount of toughness points the Manyullyn Armor will have")
    public static double manyullynArmorToughnessPoints = 2.0;
    @ModConfigProperty(category = "type.pig_iron.registry", name = "pigIronArmorToughnessPoints", comment = "Set the amount of toughness points the Pig Iron Armor will have")
    public static double pigIronArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "type.knight_slime.registry", name = "knightSlimeArmorToughnessPoints", comment = "Set the amount of toughness points the Knight Slime Armor will have")
    public static double knightSlimeArmorToughnessPoints = 1.0;
    @ModConfigProperty(category = "type.chicken.registry", name = "chickenArmorToughnessPoints", comment = "Set the amount of toughness points the Chicken Armor will have")
    public static double chickenArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "type.slime.registry", name = "slimeArmorToughnessPoints", comment = "Set the amount of toughness points the Slime Armor will have")
    public static double slimeArmorToughnessPoints = 0.0;
    @ModConfigProperty(category = "type.coal.registry", name = "coalArmorProtectionPoints", comment = "Set the amount of protection points the Coal Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] coalArmorProtectionPoints = new int[]{1, 1, 2, 1};
    @ModConfigProperty(category = "type.lapis.registry", name = "lapisArmorProtectionPoints", comment = "Set the amount of protection points the Lapis Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] lapisArmorProtectionPoints = new int[]{1, 2, 3, 2};
    @ModConfigProperty(category = "type.redstone.registry", name = "redstoneArmorProtectionPoints", comment = "Set the amount of protection points the Redstone Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] redstoneArmorProtectionPoints = new int[]{1, 2, 3, 2};
    @ModConfigProperty(category = "type.emerald.registry", name = "emeraldArmorProtectionPoints", comment = "Set the amount of protection points the Emerald Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] emeraldArmorProtectionPoints = new int[]{3, 6, 8, 3};
    @ModConfigProperty(category = "type.obsidian.registry", name = "obsidianArmorProtectionPoints", comment = "Set the amount of protection points the Obsidian Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] obsidianArmorProtectionPoints = new int[]{3, 6, 7, 3};
    @ModConfigProperty(category = "type.lava.registry", name = "lavaArmorProtectionPoints", comment = "Set the amount of protection points the Lava Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] lavaArmorProtectionPoints = new int[]{3, 6, 8, 3};
    @ModConfigProperty(category = "type.guardian.registry", name = "guardianArmorProtectionPoints", comment = "Set the amount of protection points the Guardian Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] guardianArmorProtectionPoints = new int[]{4, 7, 8, 3};
    @ModConfigProperty(category = "type.super_star.registry", name = "superStarArmorProtectionPoints", comment = "Set the amount of protection points the Super Star Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] superStarArmorProtectionPoints = new int[]{4, 7, 8, 3};
    @ModConfigProperty(category = "type.ender_dragon.registry", name = "enderDragonArmorProtectionPoints", comment = "Set the amount of protection points the Ender Dragon Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] enderDragonArmorProtectionPoints = new int[]{4, 7, 8, 3};
    @ModConfigProperty(category = "type.ultimate.registry", name = "theUltimateArmorProtectionPoints", comment = "Set the amount of protection points the Ultimate Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] theUltimateArmorProtectionPoints = new int[]{4, 8, 9, 4};
    @ModConfigProperty(category = "type.ardite.registry", name = "arditeArmorProtectionPoints", comment = "Set the amount of protection points the Ardite Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] arditeArmorProtectionPoints = new int[]{2, 3, 4, 2};
    @ModConfigProperty(category = "type.cobalt.registry", name = "cobaltArmorProtectionPoints", comment = "Set the amount of protection points the Cobalt Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] cobaltArmorProtectionPoints = new int[]{2, 3, 4, 2};
    @ModConfigProperty(category = "type.manyullyn.registry", name = "manyullynArmorProtectionPoints", comment = "Set the amount of protection points the Manyullyn Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] manyullynArmorProtectionPoints = new int[]{3, 5, 5, 3};
    @ModConfigProperty(category = "type.pig_iron.registry", name = "pigIronArmorProtectionPoints", comment = "Set the amount of protection points the Pig Iron Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] pigIronArmorProtectionPoints = new int[]{2, 3, 4, 3};
    @ModConfigProperty(category = "type.knight_slime.registry", name = "knightSlimeArmorProtectionPoints", comment = "Set the amount of protection points the Knight Slime Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] knightSlimeArmorProtectionPoints = new int[]{2, 3, 4, 3};
    @ModConfigProperty(category = "type.chicken.registry", name = "chickenArmorProtectionPoints", comment = "Set the amount of protection points the Chicen Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] chickenArmorProtectionPoints = new int[]{1, 1, 2, 1};
    @ModConfigProperty(category = "type.slime.registry", name = "slimeArmorProtectionPoints", comment = "Set the amount of protection points the Slime Armor will have (boots, leggings, chestplate, helmet)")
    public static int[] slimeArmorProtectionPoints = new int[]{1, 1, 2, 1};
    //TheGiftOfTheGods (TGOTG)
    @ModConfigProperty(category = "items.the_gift_of_the_gods", name = "enableTheGiftOfTheGods", comment = "Enable/Disable The Gift Of The Gods")
    public static boolean enableTheGiftOfTheGods = true;
    @ModConfigProperty(category = "items.the_gift_of_the_gods", name = "cooldownTicks", comment = "Set the cooldown ticks until you can use The Gift Of The Gods (1 second = 20 ticks)")
    public static int cooldownTicks = 600;
    @ModConfigProperty(category = "items.the_gift_of_the_gods", name = "maxUses", comment = "Set the max amount of uses for the item")
    public static int maxUses = 2;
    //EnderDragonZombie
    @ModConfigProperty(category = "entities.ender_Dragon_zombie.spawn", name = "enableEnderDragonZombieSpawnEnd", comment = "Enable/Disable the Ender Dragon Zombie to spawn in the End")
    public static boolean enableEnderDragonZombieSpawnEnd = false;
    @ModConfigProperty(category = "entities.ender_Dragon_zombie.properties", name = "enderDragonZombieHealth", comment = "Set the Ender Dragon Zombie's Health")
    public static double enderDragonZombieHealth = 40.0;
    @ModConfigProperty(category = "entities.ender_Dragon_zombie.properties", name = "enderDragonZombieArmor", comment = "Set the Ender Dragon Zombie's Armor")
    public static double enderDragonZombieArmor = 2.0;
    @ModConfigProperty(category = "entities.ender_Dragon_zombie.properties", name = "enderDragonZombieAttackDamage", comment = "Set the Ender Dragon Zombie's Attack Damage")
    public static double enderDragonZombieAttackDamage = 3.0;
    @ModConfigProperty(category = "entities.ender_Dragon_zombie.properties", name = "enderDragonZombieMovementSpeed", comment = "Set the Ender Dragon Zombie's Movement Speed")
    public static double enderDragonZombieMovementSpeed = 0.23000000417232513;
    @ModConfigProperty(category = "entities.ender_Dragon_zombie.properties", name = "enderDragonZombieFollowRange", comment = "Set the Ender Dragon Zombie's Follow Range")
    public static double enderDragonZombieFollowRange = 35.0;
    @ModConfigProperty(category = "entities.ender_Dragon_zombie.effects", name = "enableEnderDragonZombieWithering", comment = "Enable/Disable the Ender Dragon Zombie's Withering Effect")
    public static boolean enableEnderDragonZombieWithering = true;
    @ModConfigProperty(category = "entities.ender_Dragon_zombie.effects", name = "enderDragonZombieWitheringEffectDuration", comment = "Set the Ender Dragon Zombie's Withering Effect Duration")
    public static int enderDragonZombieWitheringEffectDuration = 20;
    @ModConfigProperty(category = "entities.ender_Dragon_zombie.effects", name = "enderDragonZombieWitheringEffectLevel", comment = "Set the Ender Dragon Zombie's Withering Effect Level")
    public static int enderDragonZombieWitheringEffectLevel = 4;
    //Debug
    @ModConfigProperty(category = "debug", name = "debugMode", comment = "Enable/Disable Debug Mode")
    public static boolean debugMode = false;
    @ModConfigProperty(category = "debug", name = "debugModeTGOTG", comment = "Enable/Disable Debug Mode for the Gift Of The Gods")
    public static boolean debugModeTGOTG = false;
    @ModConfigProperty(category = "debug", name = "debugModeEnchantments", comment = "Enable/Disable Debug Mode for the Enchantments")
    public static boolean debugModeEnchantments = false;
    //EnderDragon
    @ModConfigProperty(category = "type.ender_dragon.effects", name = "enableEnderDragonEffect", comment = "Enable/Disable the Ender Dragon Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableEnderDragonEffect = new boolean[4];
    @ModConfigProperty(category = "type.ender_dragon.effects", name = "enableFullEnderDragonArmorEffect", comment = "Enable/Disable the Full Ender Dragon Armor Effect")
    public static boolean enableFullEnderDragonArmorEffect = true;
    //Guardian
    @ModConfigProperty(category = "type.guardian.effects", name = "enableGuardianEffect", comment = "Enable/Disable the Guardian Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableGuardianEffect = new boolean[4];
    @ModConfigProperty(category = "type.guardian.effects", name = "enableFullGuardianArmorEffect", comment = "Enable/Disable the Full Guardian Armor Effect")
    public static boolean enableFullGuardianArmorEffect = true;
    //SuperStarArmor
    @ModConfigProperty(category = "type.super_star.effects", name = "enableSuperStarEffect", comment = "Enable/Disable the Super Star Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableSuperStarEffect = new boolean[4];
    @ModConfigProperty(category = "type.super_star.effects", name = "enableFullSuperStarArmorEffect", comment = "Enable/Disable the Full Super Star Armor Effect")
    public static boolean enableFullSuperStarArmorEffect = true;
    //LavaArmor
    @ModConfigProperty(category = "type.lava.effects", name = "enableLavaEffects", comment = "Enable/Disable the Lava Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableLavaEffect = new boolean[4];
    @ModConfigProperty(category = "type.lava.effects", name = "enableFullLavaArmorEffect", comment = "Enable/Disable the Full Lava Armor Effect")
    public static boolean enableFullLavaArmorEffect = true;
    //ObsidianArmor
    @ModConfigProperty(category = "type.obsidian.effects", name = "enableObsidianEffect", comment = "Enable/Disable the Obsidian Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableObsidianEffect = new boolean[4];
    @ModConfigProperty(category = "type.obsidian.effects", name = "enableFullObsidianArmorEffect", comment = "Enable/Disable the Full Obsidian Armor Effect")
    public static boolean enableFullObsidianArmorEffect = true;
    //EmeraldArmor
    @ModConfigProperty(category = "type.emerald.effects", name = "enableEmeraldEffect", comment = "Enable/Disable the Emerald Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableEmeraldEffect = new boolean[4];
    @ModConfigProperty(category = "type.emerald.effects", name = "enableFullEmeraldArmorEffect", comment = "Enable/Disable the Full Emerald Armor Effect")
    public static boolean enableFullEmeraldArmorEffect = true;
    //RedstoneArmor
    @ModConfigProperty(category = "type.redstone.effects", name = "enableRedstoneEffect", comment = "Enable/Disable the Redstone Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableRedstoneEffect = new boolean[4];
    @ModConfigProperty(category = "type.redstone.effects", name = "enableFullRedstoneArmorEffect", comment = "Enable/Disable the Full Redstone Armor Effect")
    public static boolean enableFullRedstoneArmorEffect = true;
    //LapisArmor
    @ModConfigProperty(category = "type.lapis.effects", name = "enableLapisEffect", comment = "Enable/Disable the Lapis Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableLapisEffect = new boolean[4];
    @ModConfigProperty(category = "type.lapis.effects", name = "enableFullLapisArmorEffect", comment = "Enable/Disable the Full Lapis Armor Effect")
    public static boolean enableFullLapisArmorEffect = true;
    //CoalArmor
    @ModConfigProperty(category = "type.coal.effects", name = "enableCoalEffect", comment = "Enable/Disable the Coal Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableCoalEffect = new boolean[4];
    @ModConfigProperty(category = "type.coal.effects", name = "enableFullCoalArmorEffect", comment = "Enable/Disable the Full Coal Armor Effect")
    public static boolean enableFullCoalArmorEffect = true;
    //ChickenArmor
    @ModConfigProperty(category = "type.chicken.effects", name = "enableChickenEffect", comment = "Enable/Disable the Chicken Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableChickenEffect = new boolean[4];
    @ModConfigProperty(category = "type.chicken.effects", name = "enableFullChickenArmorEffect", comment = "Enable/Disable the Full Chicken Armor Effect")
    public static boolean enableFullChickenArmorEffect = true;
    //SlimeArmor
    @ModConfigProperty(category = "type.slime.effects", name = "enableSlimeEffect", comment = "Enable/Disable the Slime Effect (Boots, Leggings, Chestplate, Helmet)")
    public static boolean[] enableSlimeEffect = new boolean[4];
    @ModConfigProperty(category = "type.slime.effects", name = "enableFullSlimeArmorEffect", comment = "Enable/Disable the Full Slime Armor Effect")
    public static boolean enableFullSlimeArmorEffect = true;
    //Registry
    @ModConfigProperty(category = "type.coal.registry", name = "enableCoalArmor", comment = "Enable/Disable the Coal Armor from the Game")
    public static boolean enableCoalArmor = true;
    @ModConfigProperty(category = "type.lapis.registry", name = "enableLapisArmor", comment = "Enable/Disable the Lapis Armor from the Game")
    public static boolean enableLapisArmor = true;
    @ModConfigProperty(category = "type.redstone.registry", name = "enableRedstoneArmor", comment = "Enable/Disable the Redstone Armor from the Game")
    public static boolean enableRedstoneArmor = true;
    @ModConfigProperty(category = "type.emerald.registry", name = "enableEmeraldArmor", comment = "Enable/Disable the Emerald Armor from the Game")
    public static boolean enableEmeraldArmor = true;
    @ModConfigProperty(category = "type.obsidian.registry", name = "enableObsidianArmor", comment = "Enable/Disable the Obsidian Armor from the Game")
    public static boolean enableObsidianArmor = true;
    @ModConfigProperty(category = "type.lava.registry", name = "enableLavaArmor", comment = "Enable/Disable the Lava Armor from the Game")
    public static boolean enableLavaArmor = true;
    @ModConfigProperty(category = "type.super_star.registry", name = "enableSuperStarArmor", comment = "Enable/Disable the Super Star Armor from the Game")
    public static boolean enableSuperStarArmor = true;
    @ModConfigProperty(category = "type.ender_dragon.registry", name = "enableEnderDragonArmor", comment = "Enable/Disable the Ender Dragon Armor from the Game")
    public static boolean enableEnderDragonArmor = true;
    @ModConfigProperty(category = "type.guardian.registry", name = "enableGuardianArmor", comment = "Enable/Disable the Guardian Armor from the Game")
    public static boolean enableGuardianArmor = true;
    @ModConfigProperty(category = "type.ultimate.registry", name = "enableTheUltimateArmor", comment = "Enable/Disable The Ultimate Armor from the Game")
    public static boolean enableTheUltimateArmor = true;
    @ModConfigProperty(category = "type.ardite.registry", name = "enableArditeArmor", comment = "Enable/Disable the Ardite Armors from the Game")
    public static boolean enableArditeArmor = true;
    @ModConfigProperty(category = "type.cobalt.registry", name = "enableCobaltArmor", comment = "Enable/Disable the Cobalt Armors from the Game")
    public static boolean enableCobaltArmor = true;
    @ModConfigProperty(category = "type.manyullyn.registry", name = "enableManyullynArmor", comment = "Enable/Disable the Manyullyn Armors from the Game")
    public static boolean enableManyullynArmor = true;
    @ModConfigProperty(category = "type.pig_iron.registry", name = "enablePigIronArmor", comment = "Enable/Disable the Pig Iron Armors from the Game")
    public static boolean enablePigIronArmor = true;
    @ModConfigProperty(category = "type.knight_slime.registry", name = "enableKnightSlimeArmor", comment = "Enable/Disable the Knight Slime Armors from the Game")
    public static boolean enableKnightSlimeArmor = true;
    @ModConfigProperty(category = "type.chicken.registry", name = "enableChickenArmor", comment = "Enable/Disable the Chicken Armors from the Game")
    public static boolean enableChickenArmor = true;
    @ModConfigProperty(category = "type.slime.registry", name = "enableSlimeArmor", comment = "Enable/Disable the Slime Armors from the Game")
    public static boolean enableSlimeArmor = true;
    //TheUltimateArmor
    @ModConfigProperty(category = "type.ultimate.registry", name = "makeTheUltimateArmorUnbreakable", comment = "Sets The Ultimate Armor Unbreakable")
    public static boolean makeTheUltimateArmorUnbreakable = false;
    //FlightAbility
    @ModConfigProperty(category = "type.global", name = "enableFlightAbility", comment = "Enable/Disable the Armors Flight")
    public static boolean enableFlightAbility = true;
    //EffectLevel
    @ModConfigProperty(category = "type.coal.effects", name = "coalArmorEffectLevel", comment = "Set the level of the Effect by the Coal Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int coalArmorEffectLevel = 0;
    @ModConfigProperty(category = "type.lapis.effects", name = "lapisArmorEffectLevel", comment = "Set the level of the Effect by the Lapis Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int lapisArmorEffectLevel = 0;
    @ModConfigProperty(category = "type.redstone.effects", name = "redstoneArmorEffectLevel", comment = "Set the level of the Effect by the Redstone Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int redstoneArmorEffectLevel = 1;
    @ModConfigProperty(category = "type.emerald.effects", name = "emeraldArmorEffectLevel", comment = "Set the level of the Effect by the Emerald Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int emeraldArmorEffectLevel = 1;
    @ModConfigProperty(category = "type.obsidian.effects", name = "obsidianArmorEffectLevel", comment = "Set the level of the Effect by the Obsidian Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int obsidianArmorEffectLevel = 0;
    @ModConfigProperty(category = "type.lava.effects", name = "lavaArmorEffectLevel", comment = "Set the level of the Effect by the Lava Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int lavaArmorEffectLevel = 0;
    @ModConfigProperty(category = "type.chicken.effects", name = "chickenArmorEffectLevel", comment = "Set the level of the Effect by the Chicken Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int chickenArmorEffectLevel = 4;
    @ModConfigProperty(category = "type.slime.effects", name = "slimeArmorEffectLevel", comment = "Set the level of the Effect by the Slime Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int slimeArmorEffectLevel = 2;
    @ModConfigProperty(category = "type.guardian.effects", name = "guardianArmorEffectLevel", comment = "Set the level of the Effect by the Guardian Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int guardianArmorEffectLevel = 0;
    @ModConfigProperty(category = "type.super_star.effects", name = "superStarArmorEffectLevel", comment = "Set the level of the Effect by the Super Star Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int superStarArmorEffectLevel = 1;
    @ModConfigProperty(category = "type.ultimate.effects", name = "ultimateArmorEffectLevel", comment = "Set the level of the Effect by The Ultimate Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int[] ultimateArmorEffectLevels = new int[]{0, 1, 0};
    @ModConfigProperty(category = "type.ardite.effects", name = "arditeArmorEffectLevel", comment = "Set the level of the Effect by the Ardite Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int arditeArmorEffectLevel = 0;
    @ModConfigProperty(category = "type.cobalt.effects", name = "cobaltArmorEffectLevel", comment = "Set the level of the Effect by the Cobalt Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int cobaltArmorEffectLevel = 2;
    @ModConfigProperty(category = "type.manyullyn.effects", name = "manyullynArmorEffectLevel", comment = "Set the level of the Effect by the Manyullyn Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int manyullynArmorEffectLevel = 1;
    @ModConfigProperty(category = "type.knight_slime.effects", name = "knightSlimeArmorEffectLevel", comment = "Set the level of the Effect by the Knight Slime Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int knightSlimeArmorEffectLevel = 1;
    @ModConfigProperty(category = "type.pig_iron.effects", name = "pigIronArmorEffectLevel", comment = "Set the level of the Effect by the Pig Iron Armor. (0 = level 1, 1 = level 2 etc.)")
    public static int pigIronArmorEffectLevel = 0;
    //WorldGeneration
    @ModConfigProperty(category = "world.generation.overworld", name = "enableLavaCrystalOreOverworldGen", comment = "Enable/Disable The Lava Crystal World Generation in the dimension `Overworld`")
    public static boolean enableLavaCrystalOverworldGen = true;
    @ModConfigProperty(category = "world.generation.the_end", name = "enableLavaCrystalOreTheEndGen", comment = "Enable/Disable The Lava Crystal World Generation in the dimension `The End`")
    public static boolean enableLavaCrystalTheEndGen = false;
    @ModConfigProperty(category = "world.generation.the_nether", name = "enableLavaCrystalOreTheNetherGen", comment = "Enable/Disable The Lava Crystal World Generation in the dimension `The Nether`")
    public static boolean enableLavaCrystalTheNetherGen = false;
    @ModConfigProperty(category = "world.generation.overworld", name = "lavaCrystalOverworldRarityWorkingOne", comment = "Set the rarity level of the Lava Crystal Generation in the dimension `Overworld`")
    public static int lavaCrystalOverworldRarityWorkingOne = 10;
    @ModConfigProperty(category = "world.generation.overworld", name = "lavaCrystalOverworldMinYSpawn", comment = "Set the min POS_Y level of the Lava Crystal Generation in the dimension `Overworld`")
    public static int lavaCrystalOverworldMinYSpawn = 6;
    @ModConfigProperty(category = "world.generation.overworld", name = "lavaCrystalOverworldMaxYSpawn", comment = "Set the max POS_Y level of the Lava Crystal Generation in the dimension `Overworld`")
    public static int lavaCrystalOverworldMaxYSpawn = 16;
    @ModConfigProperty(category = "world.generation.the_end", name = "lavaCrystalTheEndRarity", comment = "Set the rarity level of the Lava Crystal Generation in the dimension `The End`")
    public static int lavaCrystalTheEndRarity = 0;
    @ModConfigProperty(category = "world.generation.the_end", name = "lavaCrystalTheEndMinYSpawn", comment = "Set the min POS_Y level of the Lava Crystal Generation in the dimension `The End`")
    public static int lavaCrystalTheEndMinYSpawn = 0;
    @ModConfigProperty(category = "world.generation.the_end", name = "lavaCrystalTheEndMaxYSpawn", comment = "Set the max POS_Y level of the Lava Crystal Generation in the dimension `The End`")
    public static int lavaCrystalTheEndMaxYSpawn = 0;
    @ModConfigProperty(category = "world.generation.the_nether", name = "lavaCrystalTheNetherRarity", comment = "Set the rarity level of the Lava Crystal Generation in the dimension `The Nether`")
    public static int lavaCrystalTheNetherRarity = 0;
    @ModConfigProperty(category = "world.generation.the_nether", name = "lavaCrystalTheNetherMinYSpawn", comment = "Set the min POS_Y level of the Lava Crystal Generation in the dimension `The Nether`")
    public static int lavaCrystalTheNetherMinYSpawn = 0;
    @ModConfigProperty(category = "world.generation.the_nether", name = "lavaCrystalTheNetherMaxYSpawn", comment = "Set the max POS_Y level of the Lava Crystal Generation in the dimension `The Nether`")
    public static int lavaCrystalTheNetherMaxYSpawn = 0;
    @ModConfigProperty(category = "world.generation.overworld", name = "lavaCrystalOverworldVeinAmountWorking", comment = "Set the vein amount of the Lava Crystal Generation in the dimension `Overworld`")
    public static int lavaCrystalOverworldVeinAmountWorking = 4;
    @ModConfigProperty(category = "world.generation.the_end", name = "lavaCrystalTheEndVeinAmount", comment = "Set the vein amount of the Lava Crystal Generation in the dimension `The End`")
    public static int lavaCrystalTheEndVeinAmount = 0;
    @ModConfigProperty(category = "world.generation.the_nether", name = "lavaCrystalTheNetherVeinAmount", comment = "Set the vein amount of the Lava Crystal Generation in the dimension `The Nether`")
    public static int lavaCrystalTheNetherVeinAmount = 0;
    @ModConfigProperty(category = "world.generation.nether_tower", name = "towerGenSpawnNeedOfChance", comment = "Set the spawn chance need of the Tower Generation in the Overworld")
    public static int towerGenSpawnNeedOfChance = 7000;
    @ModConfigProperty(category = "world.generation.nether_tower", name = "towerGenSpawnChance", comment = "Set the spawn chance of the Tower Generation in the Overworld")
    public static int towerGenSpawnChance = 1;
    @ModConfigProperty(category = "world.generation.nether_tower", name = "enableTowerGen", comment = "Enable/Disable the Tower Generation")
    public static boolean enableTowerGen = true;
    @ModConfigProperty(category = "world.generation.nether_tower", name = "shouldOnlyGenerateInTheOverworld", comment = "Enable/Disable the tower generation outside the overworld")
    public static boolean shouldOnlyGenerateInTheOverworld = true;
    //TinkersEffects
    @ModConfigProperty(category = "type.ardite.effects", name = "enableArditeArmorEffects", comment = "Enable/Disable Ardite Armor Effects")
    public static boolean enableArditeArmorEffect = true;
    @ModConfigProperty(category = "type.cobalt.effects", name = "enableCobaltArmorEffects", comment = "Enable/Disable Cobalt Armor Effects")
    public static boolean enableCobaltArmorEffect = true;
    @ModConfigProperty(category = "type.manyullyn.effects", name = "enableManyullynArmorEffects", comment = "Enable/Disable Manyullyn Armor Effects")
    public static boolean enableManyullynArmorEffect = true;
    @ModConfigProperty(category = "type.pig_iron.effects", name = "enablePigIronArmorEffects", comment = "Enable/Disable Manyullym Armor Effects")
    public static boolean enablePigIronArmorEffect = true;
    @ModConfigProperty(category = "type.pig_iron.effects", name = "enableKnightSlimeArmorEffects", comment = "Enable/Disable Knight Slime Armor Effects")
    public static boolean enableKnightSlimeArmorEffect = true;
    //TheUltimateArmor
    @ModConfigProperty(category = "type.ultimate.ability", name = "enableTheUltimateArmorInvincibility", comment = "Enable/Disable The Ultimate Armor Invincibility")
    public static boolean enableTheUltimateArmorInvincibility = false;
    @ModConfigProperty(category = "type.ultimate.debuffs", name = "enableTheUltimateArmorDeBuffs", comment = "Enable/Disable The Ultimate Armor DeBuffs")
    public static boolean enableTheUltimateArmorDeBuffs = true;
    //MobDrops
    @ModConfigProperty(category = "mob_drops.ender_dragon", name = "enderDragonScaleDropAmount", comment = "Set the amount of dropped Ender Dragon Scales that the Ender Dragon will drop")
    public static int enderDragonScaleDropAmount = 12;
    @ModConfigProperty(category = "mob_drops.ender_dragon", name = "enderDragonScaleDrop", comment = "Set the amount of dropped Ender Dragon Scales that the Ender Dragon will drop")
    public static boolean enderDragonScaleDrop = true;
    @ModConfigProperty(category = "mob_drops.wither_boss", name = "witherBoneWitherBossDropAmount", comment = "Set the amount of dropped Wither Bones that the Wither Boss will drop")
    public static int witherBoneWitherBossDropAmount = 6;
    @ModConfigProperty(category = "mob_drops.wither_boss", name = "witherBoneWitherBossDrop", comment = "Enable/Disable the drop of Wither Bones from the Wither Boss")
    public static boolean witherBoneWitherBossDrop = true;
    @ModConfigProperty(category = "mob_drops.wither_skeleton", name = "witherBoneWitherSkeletonDrop", comment = "Enable/Disable the drop of Wither Bones from the Wither Skeleton")
    public static boolean witherBoneWitherSkeletonDrop = true;
    @ModConfigProperty(category = "mob_drops.elder_guardian", name = "guardianScaleElderDropAmount", comment = "Set the amount of dropped Guardian Scales that the Elder Guardian will drop")
    public static int guardianScaleElderDropAmount = 6;
    @ModConfigProperty(category = "mob_drops.elder_guardian", name = "guardianScaleElderGuardianDrop", comment = "Enable/Disable the drop of Guardian Scales from the Elder Guardian")
    public static boolean guardianScaleElderGuardianDrop = true;
    @ModConfigProperty(category = "mob_drops.guardian", name = "guardianScaleGuardianDrop", comment = "Enable/Disable the drop of Guardian Scales from the Guardian")
    public static boolean guardianScaleGuardianDrop = true;

    @ModConfigProperty(category = "integrations.tinkers", name = "enableTConstructIntegration", comment = "Enable/Disable the Tinkers' Construct integration")
    public static boolean enableTConstructIntegration = true;
    @ModConfigProperty(category = "integrations.jei", name = "enableJEIIntegration", comment = "Enable/Disable the JEI integration")
    public static boolean enableJEIIntegration = true;

    static {
        fill(enableCoalWeapons, true);
        fill(enableLapisWeapons, true);
        fill(enableRedstoneWeapons, true);
        fill(enableEmeraldWeapons, true);
        fill(enableObsidianWeapons, true);
        fill(enableLavaWeapons, true);
        fill(enableGuardianWeapons, true);
        fill(enableSuperStarWeapons, true);
        fill(enableEnderDragonWeapons, true);
    }


    public static RecipesDifficulty getRD() {
        return EXPERT;
    }

    public enum RecipesDifficulty {
        EXPERT() {
            @Override
            public boolean isItemRepairable(ItemStack repair, ItemStack expert) {
                return areItemsEqual(repair, expert);
            }
        };

        RecipesDifficulty() {
        }

        public abstract boolean isItemRepairable(ItemStack repair, ItemStack expert);
    }
}