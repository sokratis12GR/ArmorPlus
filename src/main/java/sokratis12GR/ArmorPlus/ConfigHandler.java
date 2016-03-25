package sokratis12GR.ArmorPlus;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler
{

	public static Configuration config;
	// Settings
	public static boolean enableCoalArmorRecipes;
	public static boolean enableLapisArmorRecipes;
	public static boolean enableRedstoneArmorRecipes;
	public static boolean enableEmeraldArmorRecipes;
	public static boolean enableObsidianArmorRecipes;
	public static boolean enableLavaArmorRecipes;
	public static boolean enableBoneArmorRecipes;

	// Special Effects
	// Coal Armor
	public static boolean enableCoalHNightVision;
	public static boolean enableCoalCNightVision;
	public static boolean enableCoalLNightVision;
	public static boolean enableCoalBNightVision;
	// Lapis Armor
	public static boolean enableLapisHBreathing;
	public static boolean enableLapisCBreathing;
	public static boolean enableLapisLBreathing;
	public static boolean enableLapisBBreathing;
	// Redstone Armor
	public static boolean enableRedstoneHSpeed;
	public static boolean enableRedstoneCSpeed;
	public static boolean enableRedstoneLSpeed;
	public static boolean enableRedstoneBSpeed;
	// Emerald Armor
	public static boolean enableEmeraldHHaste;
	public static boolean enableEmeraldCHaste;
	public static boolean enableEmeraldLHaste;
	public static boolean enableEmeraldBHaste;
	// Obsidian Armor
	public static boolean enableObsidianHResistance;
	public static boolean enableObsidianCResistance;
	public static boolean enableObsidianLResistance;
	public static boolean enableObsidianBResistance;
	// Lava Armor
	public static boolean enableLavaHResistance;
	public static boolean enableLavaHFireResistance;
	public static boolean enableLavaCResistance;
	public static boolean enableLavaCFireResistance;
	public static boolean enableLavaLResistance;
	public static boolean enableLavaLFireResistance;
	public static boolean enableLavaBResistance;
	public static boolean enableLavaBFireResistance;

	public static void init(File file)
	{
		config = new Configuration(file);

		syncConfig();
	}

	public static void syncConfig()
	{
		String category;
		category = "Recipes";
		config.setCategoryLanguageKey("Recipes", "config.recipes");
		enableCoalArmorRecipes = config.getBoolean("enableCoalArmorRecipes", category, true,
				"Enable/Disable The Coal Armor Recipes");
		enableLapisArmorRecipes = config.getBoolean("enableLapisArmorRecipes", category, true,
				"Enable/Disable The Lapis Armor Recipes");
		enableRedstoneArmorRecipes = config.getBoolean("enableRedstoneArmorRecipes", category, true,
				"Enable/Disable The Redstone Armor Recipes");
		enableEmeraldArmorRecipes = config.getBoolean("enableEmeraldArmorRecipes", category, true,
				"Enable/Disable The Emerald Armor Recipes");
		enableObsidianArmorRecipes = config.getBoolean("enableObsidianArmorRecipes", category, true,
				"Enable/Disable The Obsidian Armor Recipes");
		enableLavaArmorRecipes = config.getBoolean("enableLavaArmorRecipes", category, true,
				"Enable/Disable The Lava Armor Recipes");
		enableBoneArmorRecipes = config.getBoolean("enableBoneArmorRecipes", category, true,
				"Enable/Disable The Bone Armor Recipes");

		// Coal Armor
		category = "CoalArmor";
		config.setCategoryLanguageKey("CoalArmor", "config.coalarmor");
		config.isChild = true;
		enableCoalHNightVision = config.getBoolean("enableCoalHNightVision", category, true,
				"Enable/Disable The Coal Helmet NightVision");
		enableCoalCNightVision = config.getBoolean("enableCoalCNightVision", category, true,
				"Enable/Disable The Coal Chestplate NightVision");
		enableCoalLNightVision = config.getBoolean("enableCoalLNightVision", category, true,
				"Enable/Disable The Coal Leggings NightVision");
		enableCoalBNightVision = config.getBoolean("enableCoalBNightVision", category, true,
				"Enable/Disable The Coal Boots NightVision");

		// Lapis Armor
		category = "LapisArmor";
        config.setCategoryLanguageKey("LapisArmor", "config.lapisarmor");
		config.isChild = true;
		enableLapisHBreathing = config.getBoolean("enableLapisHBreathing", category, true,
				"Enable/Disable The Lapis Helmet Water Breathing");
		enableLapisCBreathing = config.getBoolean("enableLapisCBreathing", category, true,
				"Enable/Disable The Lapis Chestplate Water Breathing");
		enableLapisLBreathing = config.getBoolean("enableLapisLBreathing", category, true,
				"Enable/Disable The Lapis Leggings Water Breathing");
		enableLapisBBreathing = config.getBoolean("enableLapisBBreathing", category, true,
				"Enable/Disable The Lapis Boots Water Breathing");

		// Redstone Armor
		category = "RedstoneArmor";
        config.setCategoryLanguageKey("RedstoneArmor", "config.redstonearmor");
		config.isChild = true;
		enableRedstoneHSpeed = config.getBoolean("enableRedstoneHSpeed", category, true,
				"Enable/Disable The Redstone Helmet Speed");
		enableRedstoneCSpeed = config.getBoolean("enableRedstoneCeSpeed", category, true,
				"Enable/Disable Redstone Chestplate Speed");
		enableRedstoneLSpeed = config.getBoolean("enableRedstoneLSpeed", category, true,
				"Enable/Disable The Redstone Leggings Speed");
		enableRedstoneBSpeed = config.getBoolean("enableRedstoneBSpeed", category, true,
				"Enable/Disable The Redstone Boots Speed");

		// Emerald Armor
		category = "EmeraldArmor";
        config.setCategoryLanguageKey("EmeraldArmor", "config.emeraldarmor");
		config.isChild = true;
		enableEmeraldHHaste = config.getBoolean("enableEmeraldHHaste", category, true,
				"Enable/Disable The Emerald Helmet Haste");
		enableEmeraldCHaste = config.getBoolean("enableEmeraldCHaste", category, true,
				"Enable/Disable Emerald Chestplate Haste");
		enableEmeraldLHaste = config.getBoolean("enableEmeraldLHaste", category, true,
				"Enable/Disable The Emerald Leggings Haste");
		enableEmeraldBHaste = config.getBoolean("enableEmeraldBHaste", category, true,
				"Enable/Disable The Emerald Boots Haste");

		// Obsidian Armor
		category = "ObsidianArmor";
        config.setCategoryLanguageKey("ObsidianArmor", "config.obsidianarmor");
		config.isChild = true;
		enableObsidianHResistance = config.getBoolean("enableObsidianHResistance", category, true,
				"Enable/Disable The Obsidian Helmet Resistance");
		enableObsidianCResistance = config.getBoolean("enableObsidianCResistance", category, true,
				"Enable/Disable Obsidian Chestplate Resistance");
		enableObsidianLResistance = config.getBoolean("enableObsidianLResistance", category, true,
				"Enable/Disable The Obsidian Leggings Resistance");
		enableObsidianBResistance = config.getBoolean("enableObsidianBResistance", category, true,
				"Enable/Disable The Obsidian Boots Resistance");

		// Lava Armor
		category = "LavaArmor";
        config.setCategoryLanguageKey("LavaArmor", "config.lavaarmor");
		config.isChild = true;
		// Lava Armor Resistance
		enableLavaHResistance = config.getBoolean("enableLavaHResistance", category, true,
				"Enable/Disable The Lava Helmet Resistance");
		enableLavaCResistance = config.getBoolean("enableLavaCResistance", category, true,
				"Enable/Disable Lava Chestplate Resistance");
		enableLavaLResistance = config.getBoolean("enableLavaLResistance", category, true,
				"Enable/Disable The Lava Leggings Resistance");
		enableLavaBResistance = config.getBoolean("enableLavaBResistance", category, true,
				"Enable/Disable The Lava Boots Resistance");
		// Lava Armor FireResistance
		enableLavaHFireResistance = config.getBoolean("enableLavaHFireResistance", category, true,
				"Enable/Disable The Lava Helmet FireResistance");
		enableLavaCFireResistance = config.getBoolean("enableLavaCFireResistance", category, true,
				"Enable/Disable Lava Chestplate FireResistance");
		enableLavaLFireResistance = config.getBoolean("enableLavaLFireResistance", category, true,
				"Enable/Disable The Lava Leggings FireResistance");
		enableLavaBFireResistance = config.getBoolean("enableLavaBFireResistance", category, true,
				"Enable/Disable The Lava Boots FireResistance");

		if (config.hasChanged())
			config.save();
	}
}