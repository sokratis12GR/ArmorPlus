package com.sofodev.armorplus.config;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

import java.util.List;

import static com.sofodev.armorplus.ArmorPlus.MODNAME;
import static java.util.Arrays.asList;

public class ArmorPlusConfig {

    private static final ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

    public static MaterialConfig coalMaterial;
    public static MaterialConfig lapisMaterial;
    public static MaterialConfig redstoneMaterial;
    public static MaterialConfig chickenMaterial;
    public static MaterialConfig slimeMaterial;
    public static MaterialConfig emeraldMaterial;
    public static MaterialConfig obsidianMaterial;
    public static MaterialConfig infusedLavaMaterial;
    public static MaterialConfig frostMaterial;
    public static MaterialConfig frostLavaMaterial;
    public static AdvancedMaterialConfig guardianMaterial;
    public static AdvancedMaterialConfig superStarMaterial;
    public static AdvancedMaterialConfig enderDragonMaterial;
    public static AdvancedMaterialConfig slayerMaterial;
    public static MaterialConfig enhancedMaterial;
    //Advancements
    public static final BooleanValue enableThankYouAdvancement = (builder.comment("Configure advancements")
            .push("advancements").comment("Enable/Disable the Thank You 6M advancement")
            .define("enableThankYou6M", true));
    public static final ConfigValue<List<? extends String>> autoSmeltingInput = (builder.comment("Infused Lava Tools: Smelting Recipe Input (Blocks)")
            .defineList("smeltingInput", asList("minecraft:iron_ore", "minecraft:gold_ore",
                            "minecraft:sand", "minecraft:sandstone", "minecraft:wet_sponge", "minecraft:clay", "minecraft:stone_bricks", "minecraft:cobblestone", "minecraft:stone",
                            "minecraft:acacia_log", "minecraft:birch_log", "minecraft:dark_oak_log", "minecraft:jungle_log", "minecraft:oak_log", "minecraft:spruce_log",
                            "minecraft:netherrack", "minecraft:ancient_debris", "minecraft:stone_bricks", "armorplus:ore_lava_crystal", "armorplus:ore_frost_crystal"),
                    s -> ResourceLocation.tryParse((String) s) != null));
    ;
    public static final ConfigValue<List<? extends String>> autoSmeltingOutput = (builder.comment("Infused Lava Tools: Smelting Recipe Output (Items)")
            .defineList("smeltingOutput", asList("minecraft:iron_ingot", "minecraft:gold_ingot", "minecraft:glass", "minecraft:smooth_sandstone", "minecraft:sponge",
                            "minecraft:terracotta", "minecraft:cracked_stone_bricks", "minecraft:stone", "minecraft:stone",
                            "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal",
                            "minecraft:nether_brick", "minecraft:netherite_scrap", "minecraft:cracked_stone_bricks", "armorplus:infused_lava_crystal", "armorplus:infused_frost_crystal"),
                    s -> ResourceLocation.tryParse((String) s) != null));
    public static final ConfigValue<List<? extends String>> enchantsThatWontWorkWithSoulHarden = (builder.comment("Enchantment Configuration")
            .push("enchantments")
            .comment("is a list of registry names that will not work with the enchantment \"Soul Harden\"")
            .defineList("disallowWithSoulHardenList",
                    asList("minecraft:mending", "minecraft:unbreaking", "minecraft:vanishing_curse"),
                    s -> ResourceLocation.tryParse((String) s) != null)
    );
    public static BossDropConfig witherBossDrops;
    public static BossDropConfig enderDragonDrops;
    public static BossDropConfig elderGuardianDrops;
    public static DropConfig witherSkeletonDrops;
    public static DropConfig guardianDrops;
    public static DropConfig endermanDrops;
    public static DropConfig blazeDrops;


    public static ArmorPlusConfig create(ModConfigSpec.Builder builder) {
        //Advancements
        builder.comment("Configure Items")
                .push("items");
        coalMaterial = MaterialConfig.create("coal", builder);
        lapisMaterial = MaterialConfig.create("lapis", builder);
        redstoneMaterial = MaterialConfig.create("redstone", builder);
        chickenMaterial = MaterialConfig.create("chicken", builder);
        slimeMaterial = MaterialConfig.create("slime", builder);
        emeraldMaterial = MaterialConfig.create("emerald", builder);
        obsidianMaterial = MaterialConfig.create("obsidian", builder);
        infusedLavaMaterial = MaterialConfig.create("infused_lava", builder);
        frostMaterial = MaterialConfig.create("frost", builder);
        frostLavaMaterial = MaterialConfig.create("frost_lava", builder);
        guardianMaterial = AdvancedMaterialConfig.create("guardian", builder, true, 0, true, 0, false, 0,
                false, 0, false, 0, true, 1, false, 0, false, 0,
                false, 0, false, 0, false, 0, false, 0, false, 0,
                false, 0, false, false, false, true);
        superStarMaterial = AdvancedMaterialConfig.create("super_star", builder, false, 0, false, 0, false, 0,
                false, 0, false, 0, false, 0, true, 0, false, 0,
                true, 0, false, 0, false, 0, false, 0, true, 0,
                false, 0, false, true, false, true);
        enderDragonMaterial = AdvancedMaterialConfig.create("ender_dragon", builder, false, 0, false, 0, false, 0,
                false, 0, false, 0, false, 0, false, 0, false, 0,
                true, 0, false, 0, false, 0, false, 0, false, 0,
                true, 0, true, true, false, true);
        slayerMaterial = AdvancedMaterialConfig.create("slayer", builder, false, 0, true, 0, false, 0,
                false, 0, false, 0, true, 1, true, 0, false, 0,
                true, 0, false, 0, false, 0, false, 0, true, 0,
                true, 0, true, true, true, true);
        enhancedMaterial = MaterialConfig.create("enhanced", builder);
        builder.pop();
        builder.comment("Mob Drops Configuration").push("drops");
        witherBossDrops = BossDropConfig.create("wither_boss", builder);
        enderDragonDrops = BossDropConfig.create("ender_dragon", builder);
        elderGuardianDrops = BossDropConfig.create("elder_guardian", builder);
        witherSkeletonDrops = DropConfig.create("wither_skeleton", builder);
        guardianDrops = DropConfig.create("guardian", builder);
        endermanDrops = DropConfig.create("enderman", builder);
        blazeDrops = DropConfig.create("blaze", builder);
        builder.pop();

        return new ArmorPlusConfig();
    }

    public record MaterialConfig(ConfigValue<Boolean> enableArmorEffects, ConfigValue<Boolean> enableWeaponEffects) implements IMaterialConfig {

        public static MaterialConfig create(String name, ModConfigSpec.Builder builder) {
            builder.comment(name + " equipment Configuration")
                    .push(name);
            ConfigValue<Boolean> enableArmorEffects = (builder.comment(name + " armor: enable/disable full set effects")
                    .define("enableArmorEffects", true));
            ConfigValue<Boolean> enableWeaponEffects = (builder.comment(name + " weapons: enable/disable on hit effects")
                    .define("enableWeaponEffects", true));
            builder.pop();

            return new MaterialConfig(enableArmorEffects, enableWeaponEffects);
        }

    }

    public record AdvancedMaterialConfig(String name, ConfigValue<Boolean> enableArmorEffects,
                                         ConfigValue<Boolean> enableWeaponEffects,
                                         ConfigValue<Boolean> enableNightVision,
                                         ConfigValue<Integer> amplifierNightVision,
                                         ConfigValue<Boolean> enableWaterBreathing,
                                         ConfigValue<Integer> amplifierWaterBreathing,
                                         ConfigValue<Boolean> enableStrength, ConfigValue<Integer> amplifierStrength,
                                         ConfigValue<Boolean> enableSpeed, ConfigValue<Integer> amplifierSpeed,
                                         ConfigValue<Boolean> enableHaste, ConfigValue<Integer> amplifierHaste,
                                         ConfigValue<Boolean> enableJumpBoost, ConfigValue<Integer> amplifierJumpBoost,
                                         ConfigValue<Boolean> enableRegeneration,
                                         ConfigValue<Integer> amplifierRegeneration,
                                         ConfigValue<Boolean> enableResistance,
                                         ConfigValue<Integer> amplifierResistance,
                                         ConfigValue<Boolean> enableFireResistance,
                                         ConfigValue<Integer> amplifierFireResistance,
                                         ConfigValue<Boolean> enableSaturation,
                                         ConfigValue<Integer> amplifierSaturation,
                                         ConfigValue<Boolean> enableInvisibility,
                                         ConfigValue<Integer> amplifierInvisibility,
                                         ConfigValue<Boolean> enableHealthBoost,
                                         ConfigValue<Integer> amplifierHealthBoost,
                                         ConfigValue<Boolean> enableAbsorption,
                                         ConfigValue<Integer> amplifierAbsorption,
                                         ConfigValue<Boolean> enableSlowFalling,
                                         ConfigValue<Integer> amplifierSlowFalling,
                                         ConfigValue<Boolean> enableFlight, ConfigValue<Boolean> enableWitherImmunity,
                                         ConfigValue<Boolean> enableNaturalImmunity,
                                         ConfigValue<Boolean> enableFireExtinguish) implements IMaterialConfig {

        public static AdvancedMaterialConfig create(String name, ModConfigSpec.Builder builder,
                                                    boolean enableNightVision, int amplifierNightVision, boolean enableWaterBreathing, int amplifierWaterBreathing, boolean enableStrength, int amplifierStrength,
                                                    boolean enableSpeed, int amplifierSpeed, boolean enableHaste, int amplifierHaste, boolean enableJumpBoost, int amplifierJumpBoost,
                                                    boolean enableRegeneration, int amplifierRegeneration, boolean enableResistance, int amplifierResistance, boolean enableFireResistance, int amplifierFireResistance,
                                                    boolean enableSaturation, int amplifierSaturation, boolean enableInvisibility, int amplifierInvisibility, boolean enableHealthBoost, int amplifierHealthBoost,
                                                    boolean enableAbsorption, int amplifierAbsorption, boolean enableSlowFalling, int amplifierSlowFalling,
                                                    boolean enableFlight, boolean enableWitherImmunity, boolean enableNaturalImmunity, boolean enableFireExtinguish) {
            ConfigValue<Boolean> enableArmorEffects = (builder.comment(name + " armor: enable/disable full set effects")
                    .define("enableArmorEffects", true));
            ConfigValue<Boolean> enableWeaponEffects = (builder.comment(name + " weapons: enable/disable on hit effects")
                    .define("enableWeaponEffects", true));
            builder.comment(name + " extended equipment Configuration").push(name).push("effects");
            ConfigValue<Boolean> enableNIGHT_VISION = (builder.comment(name + " armor: enable/disable night vision")
                    .define("enableNIGHT_VISION", enableNightVision));
            ConfigValue<Integer> amplierNIGHT_VISION = (builder.comment(name + " armor: night vision amplifier")
                    .defineInRange("amplifierNIGHT_VISION", amplifierNightVision, 0, Integer.MAX_VALUE));
            ConfigValue<Boolean> enableWATER_BREATHING = (builder.comment(name + " armor: enable/disable water breathing")
                    .define("enableWATER_BREATHING", enableWaterBreathing));
            ConfigValue<Integer> amplierWATER_BREATHING = (builder.comment(name + " armor: water breathing amplifier")
                    .defineInRange("amplifierWATER_BREATHING", amplifierWaterBreathing, 0, Integer.MAX_VALUE));
            ConfigValue<Boolean> enableSTRENGTH = (builder.comment(name + " armor: enable/disable strength")
                    .define("enableSTRENGTH", enableStrength));
            ConfigValue<Integer> amplierSTRENGTH = (builder.comment(name + " armor: strength amplifier")
                    .defineInRange("amplifierSTRENGTH", amplifierStrength, 0, Integer.MAX_VALUE));
            ConfigValue<Boolean> enableSPEED = (builder.comment(name + " armor: enable/disable speed")
                    .define("enableSPEED", enableSpeed));
            ConfigValue<Integer> amplierSPEED = (builder.comment(name + " armor: speed amplifier")
                    .defineInRange("amplifierSPEED", amplifierSpeed, 0, Integer.MAX_VALUE));
            ConfigValue<Boolean> enableHASTE = (builder.comment(name + " armor: enable/disable haste")
                    .define("enableHASTE", enableHaste));
            ConfigValue<Integer> amplierHASTE = (builder.comment(name + " armor: haste amplifier")
                    .defineInRange("amplifierHASTE", amplifierHaste, 0, Integer.MAX_VALUE));
            ConfigValue<Boolean> enableJUMP_BOOST = (builder.comment(name + " armor: enable/disable jump boost")
                    .define("enableJUMP_BOOST", enableJumpBoost));
            ConfigValue<Integer> amplierJUMP_BOOST = (builder.comment(name + " armor: jump boost amplifier")
                    .defineInRange("amplifierJUMP_BOOST", amplifierJumpBoost, 0, Integer.MAX_VALUE));
            ConfigValue<Boolean> enableREGENERATION = (builder.comment(name + " armor: enable/disable regeneration")
                    .define("enableREGENERATION", enableRegeneration));
            ConfigValue<Integer> amplierREGENERATION = (builder.comment(name + " armor: regeneration amplifier")
                    .defineInRange("amplifierREGENERATION", amplifierRegeneration, 0, Integer.MAX_VALUE));
            ConfigValue<Boolean> enableRESISTANCE = (builder.comment(name + " armor: enable/disable resistance")
                    .define("enableRESISTANCE", enableResistance));
            ConfigValue<Integer> amplierRESISTANCE = (builder.comment(name + " armor: resistance amplifier")
                    .defineInRange("amplifierRESISTANCE", amplifierResistance, 0, Integer.MAX_VALUE));
            ConfigValue<Boolean> enableFIRE_RESISTANCE = (builder.comment(name + " armor: enable/disable fire resistance")
                    .define("enableFIRE_RESISTANCE", enableFireResistance));
            ConfigValue<Integer> amplierFIRE_RESISTANCE = (builder.comment(name + " armor: fire resistance amplifier")
                    .defineInRange("amplifierFIRE_RESISTANCE", amplifierFireResistance, 0, Integer.MAX_VALUE));
            ConfigValue<Boolean> enableSATURATION = (builder.comment(name + " armor: enable/disable saturation")
                    .define("enableSATURATION", enableSaturation));
            ConfigValue<Integer> amplierSATURATION = (builder.comment(name + " armor: saturation amplifier")
                    .defineInRange("amplifierSATURATION", amplifierSaturation, 0, Integer.MAX_VALUE));
            ConfigValue<Boolean> enableINVISIBILITY = (builder.comment(name + " armor: enable/disable invisibility")
                    .define("enableINVISIBILITY", enableInvisibility));
            ConfigValue<Integer> amplierINVISIBILITY = (builder.comment(name + " armor: invisibility amplifier")
                    .defineInRange("amplifierINVISIBILITY", amplifierInvisibility, 0, Integer.MAX_VALUE));
            ConfigValue<Boolean> enableHEALTH_BOOST = (builder.comment(name + " armor: enable/disable health boost")
                    .define("enableHEALTH_BOOST", enableHealthBoost));
            ConfigValue<Integer> amplierHEALTH_BOOST = (builder.comment(name + " armor: health boost amplifier")
                    .defineInRange("amplifierHEALTH_BOOST", amplifierHealthBoost, 0, Integer.MAX_VALUE));
            ConfigValue<Boolean> enableABSORPTION = (builder.comment(name + " armor: enable/disable absorption")
                    .define("enableABSORPTION", enableAbsorption));
            ConfigValue<Integer> amplierABSORPTION = (builder.comment(name + " armor: absorption amplifier")
                    .defineInRange("amplifierABSORPTION", amplifierAbsorption, 0, Integer.MAX_VALUE));
            ConfigValue<Boolean> enableSLOW_FALLING = (builder.comment(name + " armor: enable/disable slow falling")
                    .define("enableSLOW_FALLING", enableSlowFalling));
            ConfigValue<Integer> amplierSLOW_FALLING = (builder.comment(name + " armor: slow falling amplifier")
                    .defineInRange("amplifierSLOW_FALLING", amplifierSlowFalling, 0, Integer.MAX_VALUE));
            ConfigValue<Boolean> enableFLIGHT = (builder.comment(name + " armor: enable/disable flight")
                    .define("enableFLIGHT", enableFlight));
            ConfigValue<Boolean> enableWITHER_IMMUNITY = (builder.comment(name + " armor: enable/disable wither immunity")
                    .define("enableWITHER_IMMUNITY", enableWitherImmunity));
            ConfigValue<Boolean> enableNATURAL_IMMUNITY = (builder.comment(name + " armor: enable/disable natural immunity")
                    .define("enableNATURAL_IMMUNITY", enableNaturalImmunity));
            ConfigValue<Boolean> enableFIRE_EXTINGUISH = (builder.comment(name + " armor: enable/disable fire extinguish")
                    .define("enableFIRE_EXTINGUISH", enableFireExtinguish));
            builder.pop().pop();

            return new AdvancedMaterialConfig(name, enableArmorEffects, enableWeaponEffects,
                    enableNIGHT_VISION, amplierNIGHT_VISION, enableWATER_BREATHING, amplierWATER_BREATHING, enableSTRENGTH, amplierSTRENGTH, enableSPEED, amplierSPEED,
                    enableHASTE, amplierHASTE, enableJUMP_BOOST, amplierJUMP_BOOST, enableREGENERATION, amplierREGENERATION, enableRESISTANCE, amplierRESISTANCE,
                    enableFIRE_RESISTANCE, amplierFIRE_RESISTANCE, enableSATURATION, amplierSATURATION, enableINVISIBILITY, amplierINVISIBILITY, enableHEALTH_BOOST, amplierHEALTH_BOOST,
                    enableABSORPTION, amplierABSORPTION, enableSLOW_FALLING, amplierSLOW_FALLING, enableFLIGHT, enableWITHER_IMMUNITY, enableNATURAL_IMMUNITY, enableFIRE_EXTINGUISH);
        }

    }

    public interface IMaterialConfig {
        ConfigValue<Boolean> enableArmorEffects();

        ConfigValue<Boolean> enableWeaponEffects();
    }

    public record DropConfig(ConfigValue<Boolean> enableRegularDrops, ConfigValue<Boolean> enableSoulDrops) {

        public static DropConfig create(String name, ModConfigSpec.Builder builder) {
            builder.comment(name + " drop configuration")
                    .push(name);
            ConfigValue<Boolean> enableRegularDrops = (builder.comment(MODNAME + "'s " + name + " regular drops: enable/disable")
                    .define("enableRegularDrops", true));
            ConfigValue<Boolean> enableSoulDrops = (builder.comment(MODNAME + "'s " + name + " soul drops: enable/disable")
                    .define("enableSoulDrops", true));
            builder.pop();

            return new DropConfig(enableRegularDrops, enableSoulDrops);
        }

    }

    public record BossDropConfig(ConfigValue<Boolean> enableTrophyDrops, ConfigValue<Boolean> enableRegularDrops,
                                 ConfigValue<Boolean> enableSoulDrops) {


        public static BossDropConfig create(String name, ModConfigSpec.Builder builder) {
            builder.comment(name + " drop configuration")
                    .push(name);
            ConfigValue<Boolean> enableTrophyDrops = (builder.comment(MODNAME + "'s " + name + " trophy drops: enable/disable  (CURRENTLY DISABLED BY THE MOD)")
                    .define("enableTrophyDrops", false));
            ;
            ConfigValue<Boolean> enableRegularDrops = (builder.comment(MODNAME + "'s " + name + " regular drops: enable/disable")
                    .define("enableBossRegularDrops", true));
            ConfigValue<Boolean> enableSoulDrops = (builder.comment(MODNAME + "'s " + name + " soul drops: enable/disable")
                    .define("enableBossSoulDrops", true));
            builder.pop();

            return new BossDropConfig(enableTrophyDrops, enableRegularDrops, enableSoulDrops);
        }

    }
}