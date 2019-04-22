/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.config;

import com.sofodev.armorplus.api.properties.*;
import com.sofodev.armorplus.common.blocks.HarvestProps;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.common.blocks.base.ToolType.PICKAXE;
import static com.sofodev.armorplus.common.config.ModConfig.RecipesDifficulty.*;
import static net.minecraft.item.ItemStack.areItemsEqual;
import static net.minecraftforge.common.config.Config.*;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * @author Sokratis Fotkatzikis
 **/
@EventBusSubscriber(modid = MODID)
public class ModConfig {

    @Config(modid = MODID, name = "armorplus/experimental", category = "experimental")
    public static class Experimental {

        @Comment({"This will enable experimental features that the mod provides, use with caution can corrupt/destroy/break your worlds"})
        public static boolean enableExperimentalMode = false;

    }

    @Config(modid = MODID, name = "armorplus/config", category = "")
    public static class MainConfig {

        public static final Global global = new Global();

        public static final TheGiftOfTheGods tgotg = new TheGiftOfTheGods();

        public static class Global {
            @Comment({"Will make ArmorPlus use the Crafting Table 3x3 for all the recipes instead of the tiered benches"})
            public boolean useJsonRecipes = false;

            @RangeInt(min = -1, max = 2)
            @Comment({
                "Sets the Recipe Difficulty",
                "-1 Disables almost all in-game recipes from the mod (can be used with the \'useJsonRecipes\' property to only enable vanilla crafting table recipes),",
                "0 = easy (All recipes are extremely easy),",
                "1 = expert (default, All Recipes Are Harder),",
                "2 = hellish (Repairing for A+ items is DISABLED)"
            })
            public int gameMode = 1;
        }

        public static class TheGiftOfTheGods {
            @Comment({"Enable/Disable the WhiteList"})
            public boolean enableWhiteList = false;
            @Comment({"Add WhiteListed Items to the \"The Gift Of The Gods\"",
                "If You add want to add an item to the whitelist",
                "You will need to replace 1 from \"minecraft:dirt\" to the item you want to add"
            })
            public String[] whiteListedItems = {"minecraft:dirt"};
            @Comment({"Enable/Disable the BlackList"})
            public boolean enableBlackList = false;
            @Comment({"Add Blacklisted Items to the \"The Gift Of The Gods\"",
                "If You add want to add an item to the blacklist",
                "You will need to replace 1 from \"minecraft:dirt\" to the item you want to add"
            })
            public String[] blackListedItems = {"minecraft:dirt"};

            @Comment({"Enable/Disable The Gift Of The Gods"})
            public boolean enable = true;
            @Comment({"Set the cooldown ticks until you can use The Gift Of The Gods (1 second = 20 ticks)"})
            public int cooldownTicks = 600;
            @Comment({"Set the max amount of uses for the item"})
            public int maxUses = 2;
        }
    }

    @Config(modid = MODID, name = "armorplus/entities", category = "")
    public static class EntitiesConfig {

        public static EnderDragonZombieMob ender_dragon_zombie = new EnderDragonZombieMob();

        public static MobDrops mob_drops = new MobDrops();

        public static class EnderDragonZombieMob {
            @Comment({"Enable/Disable the Ender Dragon Zombie to spawn in the End"})
            public boolean enableSpawnEnd = false;
            @Comment({"Set the Ender Dragon Zombie's Health"})
            public double health = 40.0;
            @Comment({"Set the Ender Dragon Zombie's Armor"})
            public double armor = 2.0;
            @Comment({"Set the Ender Dragon Zombie's Attack Damage"})
            public double attackDamage = 3.0;
            @Comment({"Set the Ender Dragon Zombie's Movement Speed"})
            public double movementSpeed = 0.23000000417232513;
            @Comment({"Enable/Disable the Ender Dragon Zombie's Withering Effect"})
            public boolean enableWithering = true;
            @Comment({"Set the Ender Dragon Zombie's Withering effect Duration"})
            public int witheringEffectDuration = 20;
            @Comment({"Set the Ender Dragon Zombie's Withering effect Level"})
            public int witheringEffectLevel = 4;
        }

        public static class MobDrops {

            public Trophy trophy = new Trophy();
            public EnderDragonScales ender_dragon_scale = new EnderDragonScales();
            public WitherBones wither_bone = new WitherBones();
            public GuardianScales guardian_scale = new GuardianScales();

            public class Trophy {

                @Comment({"Enable/Disable the trophy dropped when killing a boss (vanilla bosses & some of the wip armorplus bosses)"})
                public boolean enableTrophyDrops = false;
            }

            public class EnderDragonScales {

                @Comment({"Set the amount that the Ender Dragon will drop"})
                public int dropAmount = 12;
                @Comment({"Enable/Disable the drop from the Ender Dragon"})
                public boolean drop = true;
            }

            public class WitherBones {
                @Comment({"Set the amount that the Wither Boss will drop"})
                public int dropAmount = 6;
                @Comment({"Enable/Disable the drop from the Wither Boss"})
                public boolean bossDrop = true;
                @Comment({"Enable/Disable the drop from the Wither Skeleton"})
                public boolean witherSkeletonDrop = true;
            }

            public class GuardianScales {

                @Comment({"Set the amount that the Elder Guardian will drop"})
                public int dropAmount = 6;
                @Comment({"Enable/Disable the drop from the Elder Guardian"})
                public boolean elderDrop = true;
                @Comment({"Enable/Disable the drop from the Guardian"})
                public boolean guardianDrop = true;
            }
        }
    }

    @Config(modid = MODID, name = "armorplus/integrations", category = "integrations")
    public static class IntegrationsConfig {

        @Comment({"Enable/Disable the Tinkers' Construct integration"})
        public static boolean enableTConstructIntegration = true;
        @Comment({"Enable/Disable the JEI integration"})
        public static boolean enableJEIIntegration = true;
        @Comment({"Enable/Disable the The One Probe integration"})
        public static boolean enableTOPIntegration = true;
        @Comment({"Enable/Disable the ProjectE integration"})
        public static boolean enableProjectEIntegration = false;

        @Comment({"Draconic Evolution: Set the chaos resistance of the [Ender Dragon, Guardian, Super Star] armor"})
        @Config.RangeDouble(min = 0.90f, max = 0.99f)
        public static double normalChaosResistance = 0.90f;

        @Comment({"Draconic Evolution: enable/disable the chaos immunity of the [Ultimate] armor"})
        public static boolean ultimateChaosImmunity = true;

    }

    @Config(modid = MODID, name = "armorplus/debug", category = "debug")
    public static class DebugConfig {

        @Comment({"Enable/Disable Debug Mode"})
        public static boolean debugMode = false;
        @Comment({"Enable/Disable Debug Mode for the Ender Dragon Zombie"})
        public static boolean debugModeEnderDragonZombie = false;
        @Comment({"Enable/Disable Debug Mode for the Gift Of The Gods"})
        public static boolean debugModeTGOTG = false;
        @Comment({"Enable/Disable Debug Mode for the Enchantments"})
        public static boolean debugModeEnchantments = false;
    }

    @Config(modid = MODID, name = "armorplus/worldgen", category = "")
    public static class WorldGenConfig {

        public static OreLavaCrystal lava_crystal = new OreLavaCrystal();
        public static Tower tower = new Tower();

        public static class OreLavaCrystal {

            @Comment({"Configuration for the 'Overworld' dimension"})
            public DimensionOre overworld = new DimensionOre(true, 10, 6, 16, 4);
            @Comment({"Configuration for the 'The Nether' dimension"})
            public DimensionOre the_nether = new DimensionOre(false, 0, 0, 0, 0);
            @Comment({"Configuration for the 'The End' dimension"})
            public DimensionOre the_end = new DimensionOre(false, 0, 0, 0, 0);

            public static class DimensionOre {

                public DimensionOre(boolean enable, int rarity, int minYSpawn, int maxYSpawn, int veinAmount) {
                    this.enable = enable;
                    this.rarity = rarity;
                    this.minYSpawn = minYSpawn;
                    this.maxYSpawn = maxYSpawn;
                    this.veinAmount = veinAmount;
                }

                @Comment({"Enable/Disable the lava crystal world generation"})
                public boolean enable;
                @Comment({"Set the rarity level of the lava crystal world gen"})
                public int rarity;
                @Comment({"Set the minimum y getExactRandPos level of the lava crystal world gen"})
                public int minYSpawn;
                @Comment({"Set the maximum y getExactRandPos level of the lava crystal world gen"})
                public int maxYSpawn;
                @Comment({"Set the vein amount of the lava crystal world gen"})
                public int veinAmount;
            }
        }

        public static class Tower {
            @Comment({"Set the spawn chance need of the Tower Generation in the Overworld"})
            public int chanceNeededForSpawning = 7000;
            @Comment({"Set the spawn chance of the Tower Generation in the Overworld"})
            public int spawnChance = 1;
            @Comment({"Enable/Disable the Tower Generation"})
            public boolean enable = true;
            @Comment({"Enable/Disable the tower generation outside the overworld"})
            public boolean shouldOnlyGenerateInTheOverworld = true;
        }
    }

    @Config(modid = MODID, name = "armorplus/misc", category = "misc")
    public static class Misc {

        @Comment({"Enable/Disable the armor Flight"})
        public static boolean enableFlightAbility = true;
    }

    @Config(modid = MODID, name = "armorplus/registry", category = "")
    public static class RegistryConfig {

        public static Blocks blocks = new Blocks();

        public static class Blocks {

            @Comment({"This includes all benches: [Workbench, High-Tech Bench, Ulti-Tech Bench, Champion Bench]"})
            public BlockRegistry benches = new BlockRegistry(new HarvestProps(PICKAXE, 2));
            public BlockRegistry stone_bricks = new BlockRegistry(new HarvestProps(PICKAXE));
            public BlockRegistry ore_lava_crystal = new BlockRegistry(new HarvestProps(PICKAXE, 3));
            public BlockRegistry lava_cactus = new BlockRegistry(new HarvestProps(PICKAXE));
            public BlockRegistry lava_infuser = new BlockRegistry(new HarvestProps(PICKAXE, 1));
            @Comment({"This includes: Block Lava Crystal, Block Infused Lava Crystal, Block Compressed Lava Crystal, Block Compressed Infused Lava Crystal & Block Lava Infused Obsidian"})
            public BlockRegistry lava_material = new BlockRegistry(new HarvestProps(PICKAXE, 2));
            public BlockRegistry lava_nether_brick = new BlockRegistry(new HarvestProps(PICKAXE, 1));
            public BlockRegistry block_compressed_obsidian = new BlockRegistry(new HarvestProps(PICKAXE, 3));
            public BlockRegistry block_trophy = new BlockRegistry(new HarvestProps(PICKAXE, 1));
            public BlockRegistry block_metal = new BlockRegistry(new HarvestProps(PICKAXE, 1));

            public class BlockRegistry {

                public BlockRegistry(HarvestProps props) {
                    this.props = props;
                    toolType = props.getType().getTool();
                    harvestLevel = props.getHarvestLevel();
                    isUnbreakable = props.isUnbreakable();
                }

                @Ignore
                public HarvestProps props;

                @Comment({
                    "The tool type required to mine this block",
                    "Available tool types are: 'pickaxe', 'axe', 'shovel'"
                })
                public String toolType;
                @Comment({
                    "The harvest level of the tool required to mine this block",
                    "Wood: 0, Stone: 1, Iron: 2, Diamond: 3, Gold: 0"
                })
                public int harvestLevel;
                @Comment({"Sets whether the block can be mined or not (Like bedrock)"})
                public boolean isUnbreakable;
            }

        }

        @Comment({"Configurations for the Coal MaterialType"})
        public static OriginMaterial coal = new OriginMaterial(
            new CombinedArmor("gray", "night_vision", false, new Armor(1, 1, 2, 1)),
            new CombinedWeapon("gray", "blindness", new WeaponSet(0.5, -2.0, 24)),
            new CombinedTool("gray", new ToolSet(2.0F, 24))
        );

        @Comment({"Configurations for the Lapis MaterialType"})
        public static OriginMaterial lapis = new OriginMaterial(
            new CombinedArmor("dark_blue", "water_breathing", false, new Armor(1, 2, 3, 2)),
            new CombinedWeapon("dark_blue", "nausea", 1, new WeaponSet(1.0, -1.5, 200)),
            new CombinedTool("dark_blue", new ToolSet(6.0F, 200))
        );

        @Comment({"Configurations for the Redstone MaterialType"})
        public static OriginMaterial redstone = new OriginMaterial(
            new CombinedArmor("dark_red", "haste", 1, new Armor(1, 2, 3, 2)),
            new CombinedWeapon("dark_red", "mining_fatigue", 1, new WeaponSet(1.0, -1.5, 200)),
            new CombinedTool("dark_red", new ToolSet(6.0F, 200))
        );

        @Comment({"Configurations for the Emerald MaterialType"})
        public static OriginMaterial emerald = new OriginMaterial(
            new CombinedArmor("dark_green", "speed", 1, new Armor(1.0, 3, 6, 8, 3)),
            new CombinedWeapon("dark_green", "slowness", 1, new WeaponSet(1.5, -0.5, 1561)),
            new CombinedTool("dark_green", new ToolSet(8.0F, 1561))
        );

        @Comment({"Configurations for the Obsidian MaterialType"})
        public static OriginMaterial obsidian = new OriginMaterial(
            new CombinedArmor("dark_gray", "resistance", false, new Armor(1.0, 3, 6, 7, 3)),
            new CombinedWeapon("dark_gray", "weakness", 1, new WeaponSet(4.0, 0.0, 1500)),
            new CombinedTool("dark_gray", new ToolSet(4.0F, 5000))
        );

        @Comment({"Configurations for the Lava MaterialType"})
        public static OriginMaterial lava = new OriginMaterial(
            new CombinedArmor("gold", "fire_resistance", false, new Armor(1.0, 3, 6, 8, 3)), true,
            new CombinedWeapon("gold", new WeaponSet(4.5, 0.5, 1750)), true, 8,
            new CombinedTool("gold", new ToolSet(12.0F, 1750))
        );

        @Comment({"Configurations for the Guardian MaterialType"})
        public static OriginMaterial guardian = new OriginMaterial(
            new CombinedArmor("aqua", "water_breathing", false, new Armor(2.0, 4, 7, 8, 3)),
            new CombinedWeapon("aqua", "nausea", 1, new WeaponSet(6.0, 1.5, 1800)),
            new CombinedTool("aqua", new ToolSet(15.0F, 1800))
        );

        @Comment({"Configurations for the Super Star MaterialType"})
        public static OriginMaterial super_star = new OriginMaterial(
            new CombinedArmor("white", "wither", "regeneration", 1, new Armor(2.0, 4, 7, 8, 3)),
            new CombinedWeapon("white", "wither", 1, new WeaponSet(7.0, 1.5, 1950)),
            new CombinedTool("white", new ToolSet(15.0F, 1950))
        );

        @Comment({"Configurations for the Ender Dragon MaterialType"})
        public static OriginMaterial ender_dragon = new OriginMaterial(
            new CombinedArmor("dark_purple", "wither", true, new Armor(2.0, 4, 7, 8, 3)),
            new CombinedWeapon("dark_purple", "wither", 3, new WeaponSet(8.0, 1.5, 2310)),
            new CombinedTool("dark_purple", new ToolSet(25.0f, 2310))
        );

        @Comment({"Configurations for the Ultimate MaterialType"})
        public static UltimateMaterial ultimate = new UltimateMaterial();

        @Comment({"Configurations for the Chicken MaterialType"})
        public static ArmorProperties chicken = new ArmorProperties(new CombinedArmor("aqua", "speed", 4, new Armor(1, 1, 2, 1)), false);
        @Comment({"Configurations for the Slime MaterialType"})
        public static ArmorProperties slime = new ArmorProperties(new CombinedArmor("green", "jump_boost", 2, new Armor(1, 1, 2, 1)), false);
        @Comment({"Configurations for the Ardite MaterialType"})
        public static ArmorProperties ardite = new ArmorProperties(new CombinedArmor("dark_red", "fire_resistance", false, new Armor(1.0, 2, 3, 4, 2)), false);
        @Comment({"Configurations for the Cobalt MaterialType"})
        public static ArmorProperties cobalt = new ArmorProperties(new CombinedArmor("blue", "haste", 2, new Armor(1.0, 2, 3, 4, 2)), false);
        @Comment({"Configurations for the Manyullyn MaterialType"})
        public static ArmorProperties manyullyn = new ArmorProperties(new CombinedArmor("dark_purple", "strength", 1, new Armor(2.0, 3, 5, 5, 3)), false);
        @Comment({"Configurations for the Pig Iron MaterialType"})
        public static ArmorProperties pig_iron = new ArmorProperties(new CombinedArmor("light_purple", "saturation", false, new Armor(1.0, 2, 3, 4, 3)), false);
        @Comment({"Configurations for the Knight Slime MaterialType"})
        public static ArmorProperties knight_slime = new ArmorProperties(new CombinedArmor("dark_purple", "jump_boost", 1, new Armor(1.0, 2, 3, 4, 3)), false);

        public static RegistryRecipes recipes = new RegistryRecipes();

        public static class ArmorProperties {

            public ArmorProperties(CombinedArmor properties, boolean enableOnWaterTouchDeBuff) {
                this.itemNameColor = properties.getColor();
                this.removePotionEffects = properties.getAbility().getAbilityCanceller().getAbilities();
                AbilityProvider abilityProperties = properties.getAbility().getAbilityProvider();
                this.abilities = abilityProperties.getAbilities();
                this.enableSetEffects = abilityProperties.isEnabled();
                Armor armor = properties.getArmor();
                this.toughnessPoints = armor.getToughnessPoints();
                this.protectionPoints = armor.getArmorPoints();
                this.enablePieceEffects = new boolean[4];
                this.setUnbreakable = false;
                this.enableOnWaterTouchDeBuff = enableOnWaterTouchDeBuff;
            }

            @Comment({"Set the color name the armor will have"})
            public String itemNameColor;
            @Comment({"The potion effect(s) that the armor will be removing (to disable the effect set the effects \'empty\')"})
            public String[] removePotionEffects;
            @Comment({"Adds the potion effect the armor will have (to disable the effect set the effects \'false\')"})
            public Abilities abilities;
            @Comment({"Enable/Disable the set effect(s)"})
            public boolean enableSetEffects;
            @Comment({"Set the amount of toughness points the armor will have"})
            public double toughnessPoints;
            @Comment({"Set the amount of protection points the armor will have (boots, leggings, chestplate, helmet)"})
            public int[] protectionPoints;
            @Comment({"Enable/Disable the piece effect(s) (Boots, Leggings, Chestplate, Helmet)"})
            public boolean[] enablePieceEffects;
            @Comment({"Sets the armor unbreakable"})
            public boolean setUnbreakable;
            @Comment({
                "Enables/Disables the de-buffs that the armor will get when touching water without Water Breathing potion",
                "a.k.a nothing happens when player wears this armor while in water.",
                "CURRENTLY ONLY WORKS FOR THE INFUSED LAVA ARMOR"
            })
            public boolean enableOnWaterTouchDeBuff;
        }

        public static class OriginMaterial {

            public ArmorProperties armor;
            public OriginWeapons weapons;
            public OriginTools tools;

            public OriginMaterial(CombinedArmor armorProperties, CombinedWeapon weaponProperties, CombinedTool toolData) {
                this(armorProperties, false, weaponProperties, toolData);
            }

            public OriginMaterial(CombinedArmor armorProperties, boolean aeowtdb, CombinedWeapon weaponProperties, CombinedTool toolData) {
                this(armorProperties, aeowtdb, weaponProperties, false, 0, toolData);
            }

            public OriginMaterial(CombinedArmor combinedArmor, boolean aeowtdb, CombinedWeapon combinedWeapon, boolean wsaf, int wofs, CombinedTool toolData) {
                armor = new ArmorProperties(combinedArmor, aeowtdb);
                weapons = new OriginWeapons(combinedWeapon, wsaf, wofs);
                tools = new OriginTools(toolData);
            }

            public class OriginTools {
                public OriginPickaxe pickaxe;

                public OriginTools(CombinedTool properties) {
                    itemNameColor = properties.getColor();
                    pickaxe = new OriginPickaxe(properties.getToolSet().getPickaxe());
                }

                @Comment({"Set the color name the tools will have"})
                public String itemNameColor;

                public class OriginPickaxe {

                    OriginPickaxe(Tool tool) {

                        efficiency = tool.getEfficiency();
                        durability = tool.getDurability(false);
                    }

                    @Comment({"Set the breaking speed of the pickaxe"})
                    public double efficiency;
                    @Comment({"Set the amount of durability the pickaxe have"})
                    public int durability;
                }
            }

            public class OriginWeapons {

                public OriginSword sword;
                public OriginBattleAxe battle_axe;
                public OriginBow bow;

                public OriginWeapons(CombinedWeapon properties) {
                    this(properties, false, 0);
                }

                public OriginWeapons(CombinedWeapon properties, boolean wsaf, int wofs) {
                    AbilityProvider abilityProperties = properties.getAbilityProvider();
                    this.itemNameColor = properties.getColor();
                    this.abilities = abilityProperties.getAbilities();
                    this.enableEffects = abilityProperties.isEnabled();
                    this.shouldApplyFire = wsaf;
                    this.onFireSeconds = wofs;
                    WeaponSet weaponProperties = properties.getWeaponSet();
                    sword = new OriginSword(weaponProperties.getSword());
                    battle_axe = new OriginBattleAxe(weaponProperties.getBattleAxe());
                    bow = new OriginBow(weaponProperties.getBow());
                }

                @Comment({"Set the color name the weapons will have"})
                public String itemNameColor;
                @Comment({"Adds the potion effect the weapons will have (to disable the effect set the effects \'false\')"})
                public Abilities abilities;
                @Comment({"Enable/Disable the potion effect the weapons will have"})
                public boolean enableEffects;
                @Comment({"Enable/Disable the ability for the weapons to set entities on fire"})
                public boolean shouldApplyFire;
                @Comment({"Sets the amount of seconds the entities will be set on fire after being hit"})
                public int onFireSeconds;

                public class OriginSword {

                    OriginSword(Weapon sword) {
                        damage = sword.getDmg();
                        durability = sword.getDurability(false);
                    }

                    @Comment({"Set the amount of damage the sword will do (Additional +4 damage will be added automatically by minecraft)"})
                    public double damage;

                    @Comment({"Set the amount of durability the sword will have"})
                    public int durability;
                }

                public class OriginBattleAxe {

                    OriginBattleAxe(Weapon battleAxe) {
                        damage = battleAxe.getDmg();
                        durability = battleAxe.getDurability(false);
                    }

                    @Comment({"Set the amount of damage the battle axe will do (Additional +4 damage will be added automatically by minecraft)"})
                    public double damage;
                    @Comment({"Set the amount of durability the battle axe will have"})
                    public int durability;
                }

                public class OriginBow {

                    OriginBow(Weapon bow) {
                        arrowBonusDamage = bow.getDmg();
                        durability = bow.getDurability(false);
                    }

                    @Comment({"Set the amount of bonus arrow damage the bow will do"})
                    public double arrowBonusDamage;
                    @Comment({"Set the amount of durability the bow have"})
                    public int durability;
                }
            }
        }

        @Comment({"Configurations for the Enhanced Chainmail MaterialType"})
        public static EnhancedMaterial chain = new EnhancedMaterial(new Armor(1, 2, 5, 6, 3));
        @Comment({"Configurations for the Enhanced Iron MaterialType"})
        public static EnhancedMaterial iron = new EnhancedMaterial(new Armor(1, 3, 6, 8, 3));
        @Comment({"Configurations for the Enhanced Gold MaterialType"})
        public static EnhancedMaterial gold = new EnhancedMaterial(new Armor(1, 2, 4, 6, 3));
        @Comment({"Configurations for the Enhanced Diamond MaterialType"})
        public static EnhancedMaterial diamond = new EnhancedMaterial(new Armor(3, 4, 7, 9, 4));

        public static Enchantments enchantments = new Enchantments();

        public static class Enchantments {
            @Comment("Enable/Disable the ability to 'enhance' vanilla armors (Chainmail, Iron, Gold, Diamond) via the 'enhanced' enchantment")
            public boolean enableArmorEnhancement = true;
        }

        public static class EnhancedMaterial {

            public EnhancedMaterial(Armor armor) {
                toughnessPoints = armor.getToughnessPoints();
                protectionPoints = armor.getArmorPoints();
            }

            @Comment({"Set the amount of toughness points the armor will have"})
            public double toughnessPoints;
            @Comment({"Set the amount of protection points the armor will have (boots, leggings, chestplate, helmet)"})
            public int[] protectionPoints;
        }

        public static class UltimateMaterial {
            public Armor armor = new Armor();

            public class Armor {

                @Comment({"The potion effect(s) that the armor will be removing (to disable the effect set the effects \'empty\')"})
                public String[] removePotionEffects = {"wither"};
                @Comment({"Adds the potion effects the armor will have (to disable the effect set the effects \'false\')"})
                public String[] addPotionEffects = {"regeneration", "water_breathing"};
                @Comment({"Set the amplifier level for the effect(s) by the armor. (0 = level 1, 1 = level 2 etc.)"})
                public int[] effectLevels = {1, 0};
                @Comment({"Set the duration for the effect(s) by the armor. (in seconds)"})
                public int[] effectDurations = {12, 12};
                @Comment({"Set the color name the armor will have"})
                public String itemNameColor = "green";
                @Comment({"Set the amount of toughness points the armor will have"})
                public double toughnessPoints = 3.0;
                @Comment({"Set the amount of protection points the armor will have (boots, leggings, chestplate, helmet)"})
                public int[] protectionPoints = {4, 8, 9, 4};
                @Comment({"Sets the armor unbreakable"})
                public boolean setUnbreakable = false;
                @Comment({"Enable/Disable the armor's invincibility"})
                public boolean setInvincible = false;
                @Comment({"Enable/Disable the armor's de-buffs (when a non complete set is equiped)"})
                public boolean enableDeBuffs = true;
            }

        }

        public static class RegistryRecipes {
            @Comment({"Enable/Disable The Redstone Apple Recipes"})
            public boolean enableRedstoneAppleRecipes = true;
            @Comment({"Enable/Disable The Elytra Recipe"})
            public boolean enableElytraRecipe = false;
            @Comment({"Enable/Disable the armorplus arrow recipes"})
            public boolean enableArrowRecipes = true;
            @Comment({"Enable/Disable The Coal armor Recipes"})
            public boolean enableCoalArmorRecipes = true;
            @Comment({"Enable/Disable The Charcoal Coal armor Recipes"})
            public boolean enableCharcoalCoalArmorRecipe = false;
            @Comment({"Enable/Disable The Lapis armor Recipes"})
            public boolean enableLapisArmorRecipes = true;
            @Comment({"Enable/Disable The Redstone armor Recipes"})
            public boolean enableRedstoneArmorRecipes = true;
            @Comment({"Enable/Disable The Emerald armor Recipes"})
            public boolean enableEmeraldArmorRecipes = true;
            @Comment({"Enable/Disable The Obsidian armor Recipes"})
            public boolean enableObsidianArmorRecipes = true;
            @Comment({"Enable/Disable the armor Recipes"})
            public boolean enableLavaArmorRecipes = true;
            @Comment({"Enable/Disable The Super Star armor Recipes"})
            public boolean enableSuperStarArmorRecipes = true;
            @Comment({"Enable/Disable The Ender Dragon armor Recipes"})
            public boolean enableEnderDragonArmorRecipes = true;
            @Comment({"Enable/Disable The Guardian armor Recipes"})
            public boolean enableGuardianArmorRecipes = true;
            @Comment({"Enable/Disable The Ultimate armor Recipes"})
            public boolean enableTheUltimateArmorRecipes = true;
            @Comment({"Enable/Disable The Chain armor Recipes"})
            public boolean enableChainArmorRecipes = true;
            @Comment({"Enable/Disable The Ardite armor Recipes"})
            public boolean enableArditeArmorRecipes = true;
            @Comment({"Enable/Disable The Cobalt armor Recipes"})
            public boolean enableCobaltArmorRecipes = true;
            @Comment({"Enable/Disable The Manyullyn armor Recipes"})
            public boolean enableManyullynArmorRecipes = true;
            @Comment({"Enable/Disable The Pig Iron armor Recipes"})
            public boolean enablePigIronArmorRecipes = true;
            @Comment({"Enable/Disable The Knight Slime armor Recipes"})
            public boolean enableKnightSlimeArmorRecipes = true;
            @Comment({"Enable/Disable The Slime armor Recipes"})
            public boolean enableSlimeArmorRecipes = true;
            @Comment({"Enable/Disable The Chicken armor Recipes"})
            public boolean enableChickenArmorRecipes = true;
            @Comment({"Enable/Disable ArmorPlus Sword's Recipes"})
            public boolean enableSwordsRecipes = true;
            @Comment({"Enable/Disable ArmorPlus Battle Axes's Recipes"})
            public boolean enableBattleAxesRecipes = true;
            @Comment({"Enable/Disable ArmorPlus Bows's Recipes"})
            public boolean enableBowsRecipes = true;
            @Comment({"Enable/Disable ArmorPlus Pickaxe's Recipes"})
            public boolean enablePickaxesRecipes = true;
        }

    }

    public static void sync() {
        ConfigManager.sync(MODID, Type.INSTANCE);
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(MODID)) {
            sync();
        }
    }

    public static RecipesDifficulty getRD() {
        switch (MainConfig.global.gameMode) {
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

    public enum RecipesDifficulty {
        DISABLED(false) {
            @Override
            public boolean isItemRepairable(ItemStack repair, ItemStack expert) {
                return false;
            }
        },
        EASY(true) {
            @Override
            public boolean isItemRepairable(ItemStack repair, ItemStack expert) {
                return areItemsEqual(repair, expert);
            }
        },
        EXPERT(true) {
            @Override
            public boolean isItemRepairable(ItemStack repair, ItemStack expert) {
                return areItemsEqual(repair, expert);
            }
        },
        HELLISH(true) {
            @Override
            public boolean isItemRepairable(ItemStack repair, ItemStack expert) {
                return false;
            }
        },
        ;

        private final boolean hasRecipes;

        RecipesDifficulty(boolean hasRecipes) {
            this.hasRecipes = hasRecipes;
        }

        public boolean hasRecipes() {
            return hasRecipes;
        }

        public abstract boolean isItemRepairable(ItemStack repair, ItemStack expert);
    }
}