package net.thedragonteam.armorplus;

import net.thedragonteam.core.config.ModConfigProperty;

/**
 * net.thedragonteam.armorplus
 * ArmorPlus created by sokratis12GR on 7/26/2016 4:47 PM.
 */
public class ARPConfig {
    //Debug
    @ModConfigProperty(category = "Debug", name = "debugMode", comment = "Enable/Disable Debug Mode")
    public static boolean debugMode = false;

    //Weapons
    @ModConfigProperty(category = "Weapons", name = "enableSwordsRecipes", comment = "Enable/Disable The ArmorPlus Sword's Recipes")
    public static boolean enableSwordsRecipes = true;
    @ModConfigProperty(category = "Weapons", name = "enableBattleAxesRecipes", comment = "Enable/Disable The ArmorPlus Battle Axes's Recipes")
    public static boolean enableBattleAxesRecipes = true;
    @ModConfigProperty(category = "Weapons", name = "enableBowsRecipes", comment = "Enable/Disable The ArmorPlus Bows's Recipes")
    public static boolean enableBowsRecipes = true;

    //SuperStarArmor
    @ModConfigProperty(category = "Armors.SuperStarArmor.Effects", name = "enableSuperStarHRegen", comment = "Enable/Disable The Super Star Helmet Regeneration")
    public static boolean enableSuperStarHRegen = true;

    @ModConfigProperty(category = "Armors.SuperStarArmor.Effects", name = "enableSuperStarCRegen", comment = "Enable/Disable The Super Star Chestplate Regeneration")
    public static boolean enableSuperStarCRegen = true;

    @ModConfigProperty(category = "Armors.SuperStarArmor.Effects", name = "enableSuperStarLRegen", comment = "Enable/Disable The Super Star Leggings Regeneration")
    public static boolean enableSuperStarLRegen = true;

    @ModConfigProperty(category = "Armors.SuperStarArmor.Effects", name = "enableSuperStarBRegen", comment = "Enable/Disable The Super Star Boots Regeneration")
    public static boolean enableSuperStarBRegen = true;

    @ModConfigProperty(category = "Armors.SuperStarArmor.Effects", name = "enableFullSuperStarArmorEffect", comment = "Enable/Disable The Full Super Star Armor Effect")
    public static boolean enableFullSuperStarArmorEffect = false;

    //LavaArmor
    @ModConfigProperty(category = "Armors.LavaArmor.Effects", name = "enableLavaHEffects", comment = "Enable/Disable The Lava Helmet Resistance and Fire Resistance")
    public static boolean enableLavaHEffects = true;

    @ModConfigProperty(category = "Armors.LavaArmor.Effects", name = "enableLavaCEffects", comment = "Enable/Disable The Lava Chestplate Resistance and Fire Resistance")
    public static boolean enableLavaCEffects = true;

    @ModConfigProperty(category = "Armors.LavaArmor.Effects", name = "enableLavaLEffects", comment = "Enable/Disable The Lava Leggings Resistance and Fire Resistance")
    public static boolean enableLavaLEffects = true;

    @ModConfigProperty(category = "Armors.LavaArmor.Effects", name = "enableLavaBEffects", comment = "Enable/Disable The Lava Boots Resistance and Fire Resistance")
    public static boolean enableLavaBEffects = true;

    @ModConfigProperty(category = "Armors.LavaArmor.Effects", name = "enableFullLavaArmorEffect", comment = "Enable/Disable The Full Lava Armor Effect")
    public static boolean enableFullLavaArmorEffect = false;

    //ObsidianArmor
    @ModConfigProperty(category = "Armors.ObsidianArmor.Effects", name = "enableObsidianHResistance", comment = "Enable/Disable The Obsidian Helmet Resistance")
    public static boolean enableObsidianHResistance = true;

    @ModConfigProperty(category = "Armors.ObsidianArmor.Effects", name = "enableObsidianCResistance", comment = "Enable/Disable The Obsidian Chestplate Resistance")
    public static boolean enableObsidianCResistance = true;

    @ModConfigProperty(category = "Armors.ObsidianArmor.Effects", name = "enableObsidianLResistance", comment = "Enable/Disable The Obsidian Leggings Resistance")
    public static boolean enableObsidianLResistance = true;

    @ModConfigProperty(category = "Armors.ObsidianArmor.Effects", name = "enableObsidianBResistance", comment = "Enable/Disable The Obsidian Boots Resistance")
    public static boolean enableObsidianBResistance = true;

    @ModConfigProperty(category = "Armors.ObsidianArmor.Effects", name = "enableFullObsidianArmorEffect", comment = "Enable/Disable The Full Obsidian Armor Effect")
    public static boolean enableFullObsidianArmorEffect = false;

    //EmeraldArmor
    @ModConfigProperty(category = "Armors.EmeraldArmor.Effects", name = "enableEmeraldHHaste", comment = "Enable/Disable The Emerald Helmet Haste")
    public static boolean enableEmeraldHHaste = true;

    @ModConfigProperty(category = "Armors.EmeraldArmor.Effects", name = "enableEmeraldCHaste", comment = "Enable/Disable The Emerald Chestplate Haste")
    public static boolean enableEmeraldCHaste = true;

    @ModConfigProperty(category = "Armors.EmeraldArmor.Effects", name = "enableEmeraldLHaste", comment = "Enable/Disable The Emerald Leggings Haste")
    public static boolean enableEmeraldLHaste = true;

    @ModConfigProperty(category = "Armors.EmeraldArmor.Effects", name = "enableEmeraldBHaste", comment = "Enable/Disable The Emerald Boots Haste")
    public static boolean enableEmeraldBHaste = true;

    @ModConfigProperty(category = "Armors.EmeraldArmor.Effects", name = "enableFullEmeraldArmorEffect", comment = "Enable/Disable The Full Emerald Armor Effect")
    public static boolean enableFullEmeraldArmorEffect = false;

    //RedstoneArmor
    @ModConfigProperty(category = "Armors.RedstoneArmor.Effects", name = "enableRedstoneHSpeed", comment = "Enable/Disable The Redstone Helmet Speed")
    public static boolean enableRedstoneHSpeed = true;

    @ModConfigProperty(category = "Armors.RedstoneArmor.Effects", name = "enableRedstoneCSpeed", comment = "Enable/Disable The Redstone Chestplate Speed")
    public static boolean enableRedstoneCSpeed = true;

    @ModConfigProperty(category = "Armors.RedstoneArmor.Effects", name = "enableRedstoneLSpeed", comment = "Enable/Disable The Redstone Leggings Speed")
    public static boolean enableRedstoneLSpeed = true;

    @ModConfigProperty(category = "Armors.RedstoneArmor.Effects", name = "enableRedstoneBSpeed", comment = "Enable/Disable The Redstone Boots Speed")
    public static boolean enableRedstoneBSpeed = true;

    @ModConfigProperty(category = "Armors.RedstoneArmor.Effects", name = "enableFullRedstoneArmorEffect", comment = "Enable/Disable The Full Redstone Armor Effect")
    public static boolean enableFullRedstoneArmorEffect = false;

    //LapisArmor
    @ModConfigProperty(category = "Armors.LapisArmor.Effects", name = "enableLapisHBreathing", comment = "Enable/Disable The Lapis Helmet Water Breathing")
    public static boolean enableLapisHBreathing = true;

    @ModConfigProperty(category = "Armors.LapisArmor.Effects", name = "enableLapisCBreathing", comment = "Enable/Disable The Lapis Chestplate Water Breathing")
    public static boolean enableLapisCBreathing = true;

    @ModConfigProperty(category = "Armors.LapisArmor.Effects", name = "enableLapisLBreathing", comment = "Enable/Disable The Lapis Leggings Water Breathing")
    public static boolean enableLapisLBreathing = true;

    @ModConfigProperty(category = "Armors.LapisArmor.Effects", name = "enableLapisBBreathing", comment = "Enable/Disable The Lapis Boots Water Breathing")
    public static boolean enableLapisBBreathing = true;

    @ModConfigProperty(category = "Armors.LapisArmor.Effects", name = "enableFullLapisArmorEffect", comment = "Enable/Disable The Full Lapis Armor Effect")
    public static boolean enableFullLapisArmorEffect = false;

    //CoalArmor
    @ModConfigProperty(category = "Armors.CoalArmor.Effects", name = "enableCoalHNightVision", comment = "Enable/Disable The Coal Helmet NightVision")
    public static boolean enableCoalHNightVision = true;

    @ModConfigProperty(category = "Armors.CoalArmor.Effects", name = "enableCoalCNightVision", comment = "Enable/Disable The Coal Chestplate NightVision")
    public static boolean enableCoalCNightVision = true;

    @ModConfigProperty(category = "Armors.CoalArmor.Effects", name = "enableCoalLNightVision", comment = "Enable/Disable The Coal Leggings NightVision")
    public static boolean enableCoalLNightVision = true;

    @ModConfigProperty(category = "Armors.CoalArmor.Effects", name = "enableCoalBNightVision", comment = "Enable/Disable The Coal Boots NightVision")
    public static boolean enableCoalBNightVision = true;

    @ModConfigProperty(category = "Armors.CoalArmor.Effects", name = "enableFullCoalArmorEffect", comment = "Enable/Disable The Full Coal Armor Effect")
    public static boolean enableFullCoalArmorEffect = false;

    //FlightAbility
    @ModConfigProperty(category = "FlightAbility", name = "enableFlightAbility", comment = "Enable/Disable The Armors Flight")
    public static boolean enableFlightAbility = true;

    //EffectLevel
    @ModConfigProperty(category = "Armors.EmeraldArmor.EffectLevel", name = "emeraldArmorEffectlevel", comment = "Set the level of the Haste effect by the Emerald Armor.")
    public static int emeraldArmorEffectlevel = 1;

    @ModConfigProperty(category = "Armors.ObsidianArmor.EffectLevel", name = "obsidianArmorEffectlevel", comment = "Set the level of the Resistance effect by the Obsidian Armor.")
    public static int obsidianArmorEffectlevel = 0;

    @ModConfigProperty(category = "Armors.RedstoneArmor.EffectLevel", name = "redstoneArmorEffectlevel", comment = "Set the level of the Swiftness effect by the Redstone Armor.")
    public static int redstoneArmorEffectlevel = 1;

    @ModConfigProperty(category = "Armors.LavaArmor.EffectLevel", name = "lavaArmorEffectlevel", comment = "Set the level of the Resistance effect by the Lava Armor.")
    public static int lavaArmorEffectlevel = 0;

    @ModConfigProperty(category = "Armors.SuperStarArmor.EffectLevel", name = "superstarArmorEffectlevel", comment = "Set the level of the Regeneration effect by the Super Star Armor.")
    public static int superstarArmorEffectlevel = 1;

    @ModConfigProperty(category = "Armors.TheUltimateArmor.EffectLevel", name = "ultimateArmorEffectlevel", comment = "Set the level of the Regeneration effect by The Ultimate Armor.")
    public static int ultimateArmorEffectlevel = 1;

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

    //GameModes
    @ModConfigProperty(category = "GameModes", name = "recipes", comment = "Sets the Recipe Difficulty \n0 = easy , 1 = expert")
    public static int recipes = 1;

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
    public static boolean enableBlackList = false;

    @ModConfigProperty(category = "BlackList", name = "blackListedItems", comment = "Add Blacklisted Items to the \"The Gift Of The Gods\" \nIf You add want to add an item to the blacklist \nYou will need to replace 1 from \"minecraft:dirt\" to the item you want to add")
    public static String[] blackListedItems = new String[]{"minecraft:dirt"};

    //TinkersEffects
    @ModConfigProperty(category = "Armors.ArditeArmor.Effects", name = "enableArditeArmorEffects", comment = "Enable/Disable Ardite Armor Effects")
    public static boolean enableArditeArmorEffects = true;

    @ModConfigProperty(category = "Armors.CobaltArmor.Effects", name = "enableCobaltArmorEffects", comment = "Enable/Disable Cobalt Armor Effects")
    public static boolean enableCobaltArmorEffects = true;

    @ModConfigProperty(category = "Armors.ManyullynArmor.Effects", name = "enableManyullynArmorEffects", comment = "Enable/Disable Manyullyn Armor Effects")
    public static boolean enableManyullynArmorEffects = true;

    @ModConfigProperty(category = "Armors.PigIronArmor.Effects", name = "enablePigIronArmorEffects", comment = "Enable/Disable Manyullym Armor Effects")
    public static boolean enablePigIronArmorEffects = true;

    @ModConfigProperty(category = "Armors.PigIronArmor.Effects", name = "enableKnightSlimeArmorEffects", comment = "Enable/Disable Knight Slime Armor Effects")
    public static boolean enableKnightSlimeArmorEffects = true;

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

    @ModConfigProperty(category = "Armors.ReinforcedArmors.Recipes", name = "enableReinforcedArmorsRecipes", comment = "Enable/Disable The Reinforced Armors Recipes")
    public static boolean enableReinforcedArmorsRecipes = true;

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

    @ModConfigProperty(category = "Armors.PigIronArmor.Recipes", name = "enableKnightSlimeArmorRecipes", comment = "Enable/Disable The Knight Slime Armors Recipes")
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
}