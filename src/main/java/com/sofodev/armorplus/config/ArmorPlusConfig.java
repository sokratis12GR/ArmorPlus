package com.sofodev.armorplus.config;

import com.sofodev.armorplus.config.ConfigHelper.ConfigValueListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

import static com.sofodev.armorplus.ArmorPlus.MODNAME;
import static java.util.Arrays.asList;

public class ArmorPlusConfig {

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
    public static ConfigValueListener<Boolean> enableThankYouAdvancement;
    public static ConfigValueListener<List<? extends String>> autoSmeltingInput;
    public static ConfigValueListener<List<? extends String>> autoSmeltingOutput;

    public static ConfigValueListener<List<? extends String>> enchantsThatWontWorkWithSoulHarden;
    public static BossDropConfig witherBossDrops;
    public static BossDropConfig enderDragonDrops;
    public static BossDropConfig elderGuardianDrops;
    public static DropConfig witherSkeletonDrops;
    public static DropConfig guardianDrops;
    public static DropConfig endermanDrops;
    public static DropConfig blazeDrops;
    public ArmorPlusConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber) {
        //Advancements
        builder.comment("Configure advancements")
                .push("advancements");
        enableThankYouAdvancement = subscriber.subscribe(builder.comment("Enable/Disable the Thank You 6M advancement")
                .define("enableThankYou6M", true));
        builder.pop();
        builder.comment("Configure Items")
                .push("items");
        coalMaterial = new MaterialConfig(builder, subscriber, "coal");
        lapisMaterial = new MaterialConfig(builder, subscriber, "lapis");
        redstoneMaterial = new MaterialConfig(builder, subscriber, "redstone");
        chickenMaterial = new MaterialConfig(builder, subscriber, "chicken");
        slimeMaterial = new MaterialConfig(builder, subscriber, "slime");
        emeraldMaterial = new MaterialConfig(builder, subscriber, "emerald");
        obsidianMaterial = new MaterialConfig(builder, subscriber, "obsidian");
        infusedLavaMaterial = new MaterialConfig(builder, subscriber, "infused_lava");
        builder.comment("Infused Lava Extra ")
                .push("infused_lava.extra");
        autoSmeltingInput = subscriber.subscribe(builder.comment("Infused Lava Tools: Smelting Recipe Input (Blocks)")
                .defineList("smeltingInput", asList("minecraft:iron_ore", "minecraft:gold_ore",
                                "minecraft:sand", "minecraft:sandstone", "minecraft:wet_sponge", "minecraft:clay", "minecraft:stone_bricks", "minecraft:cobblestone", "minecraft:stone",
                                "minecraft:acacia_log", "minecraft:birch_log", "minecraft:dark_oak_log", "minecraft:jungle_log", "minecraft:oak_log", "minecraft:spruce_log",
                                "minecraft:netherrack", "minecraft:ancient_debris", "minecraft:stone_bricks", "armorplus:ore_lava_crystal", "armorplus:ore_frost_crystal"),
                        s -> ResourceLocation.tryParse((String) s) != null));
        autoSmeltingOutput = subscriber.subscribe(builder.comment("Infused Lava Tools: Smelting Recipe Output (Items)")
                .defineList("smeltingOutput", asList("minecraft:iron_ingot", "minecraft:gold_ingot", "minecraft:glass", "minecraft:smooth_sandstone", "minecraft:sponge",
                                "minecraft:terracotta", "minecraft:cracked_stone_bricks", "minecraft:stone", "minecraft:stone",
                                "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal",
                                "minecraft:nether_brick", "minecraft:netherite_scrap", "minecraft:cracked_stone_bricks", "armorplus:infused_lava_crystal", "armorplus:infused_frost_crystal"),
                        s -> ResourceLocation.tryParse((String) s) != null));
        builder.pop(2);
        frostMaterial = new MaterialConfig(builder, subscriber, "frost");
        frostLavaMaterial = new MaterialConfig(builder, subscriber, "frost_lava");
        guardianMaterial = new AdvancedMaterialConfig(builder, subscriber, "guardian", true, 0, true, 0, false, 0,
                false, 0, false, 0, true, 1, false, 0, false, 0,
                false, 0, false, 0, false, 0, false, 0, false, 0,
                false, 0, false, false, false, true);
        superStarMaterial = new AdvancedMaterialConfig(builder, subscriber, "super_star", false, 0, false, 0, false, 0,
                false, 0, false, 0, false, 0, true, 0, false, 0,
                true, 0, false, 0, false, 0, false, 0, true, 0,
                false, 0, false, true, false, true);
        enderDragonMaterial = new AdvancedMaterialConfig(builder, subscriber, "ender_dragon", false, 0, false, 0, false, 0,
                false, 0, false, 0, false, 0, false, 0, false, 0,
                true, 0, false, 0, false, 0, false, 0, false, 0,
                true, 0, true, true, false, true);
        slayerMaterial = new AdvancedMaterialConfig(builder, subscriber, "slayer", false, 0, true, 0, false, 0,
                false, 0, false, 0, true, 1, true, 0, false, 0,
                true, 0, false, 0, false, 0, false, 0, true, 0,
                true, 0, true, true, true, true);
        enhancedMaterial = new MaterialConfig(builder, subscriber, "enhanced");
        builder.pop();
        builder.comment("[REMOVED] - WORLD GEN - Is now handled by DATA PACKS!").push("world_gen");
        builder.pop();
        builder.comment("Enchantment Configuration").push("enchantments");
        enchantsThatWontWorkWithSoulHarden = subscriber.subscribe(builder.comment("is a list of registry names that will not work with the enchantment \"Soul Harden\"")
                .defineList("disallowWithSoulHardenList",
                        asList("minecraft:mending", "minecraft:unbreaking", "minecraft:vanishing_curse"),
                        s -> ResourceLocation.tryParse((String) s) != null)
        );
        builder.pop();
        builder.comment("Mob Drops Configuration").push("drops");
        witherBossDrops = new BossDropConfig(builder, subscriber, "wither_boss");
        enderDragonDrops = new BossDropConfig(builder, subscriber, "ender_dragon");
        elderGuardianDrops = new BossDropConfig(builder, subscriber, "elder_guardian");
        witherSkeletonDrops = new DropConfig(builder, subscriber, "wither_skeleton");
        guardianDrops = new DropConfig(builder, subscriber, "guardian");
        endermanDrops = new DropConfig(builder, subscriber, "enderman");
        blazeDrops = new DropConfig(builder, subscriber, "blaze");
        builder.pop();
    }

    public static class MaterialConfig {
        public ConfigValueListener<Boolean> enableArmorEffects;
        public ConfigValueListener<Boolean> enableWeaponEffects;

        public MaterialConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber, String name) {
            builder.comment(name + " equipment Configuration")
                    .push(name);
            enableArmorEffects = subscriber.subscribe(builder.comment(name + " armor: enable/disable full set effects")
                    .define("enableArmorEffects", true));
            enableWeaponEffects = subscriber.subscribe(builder.comment(name + " weapons: enable/disable on hit effects")
                    .define("enableWeaponEffects", true));
            builder.pop();
        }
    }

    public static class AdvancedMaterialConfig extends MaterialConfig {
        public ConfigValueListener<Boolean> enableNIGHT_VISION;         // Index 0
        public ConfigValueListener<Integer> amplifierNIGHT_VISION;      // Index 0
        public ConfigValueListener<Boolean> enableWATER_BREATHING;      // Index 1
        public ConfigValueListener<Integer> amplifierWATER_BREATHING;   // Index 1
        public ConfigValueListener<Boolean> enableSTRENGTH;             // Index 2
        public ConfigValueListener<Integer> amplifierSTRENGTH;          // Index 2
        public ConfigValueListener<Boolean> enableSPEED;                // Index 3
        public ConfigValueListener<Integer> amplifierSPEED;             // Index 3
        public ConfigValueListener<Boolean> enableHASTE;                // Index 4
        public ConfigValueListener<Integer> amplifierHASTE;             // Index 4
        public ConfigValueListener<Boolean> enableJUMP_BOOST;           // Index 5
        public ConfigValueListener<Integer> amplifierJUMP_BOOST;        // Index 5
        public ConfigValueListener<Boolean> enableREGENERATION;         // Index 6
        public ConfigValueListener<Integer> amplifierREGENERATION;      // Index 6
        public ConfigValueListener<Boolean> enableRESISTANCE;           // Index 7
        public ConfigValueListener<Integer> amplifierRESISTANCE;        // Index 7
        public ConfigValueListener<Boolean> enableFIRE_RESISTANCE;      // Index 8
        public ConfigValueListener<Integer> amplifierFIRE_RESISTANCE;   // Index 8
        public ConfigValueListener<Boolean> enableSATURATION;           // Index 9
        public ConfigValueListener<Integer> amplifierSATURATION;        // Index 9
        public ConfigValueListener<Boolean> enableINVISIBILITY;         // Index 10
        public ConfigValueListener<Integer> amplifierINVISIBILITY;      // Index 10
        public ConfigValueListener<Boolean> enableHEALTH_BOOST;         // Index 11
        public ConfigValueListener<Integer> amplifierHEALTH_BOOST;      // Index 11
        public ConfigValueListener<Boolean> enableABSORPTION;           // Index 12
        public ConfigValueListener<Integer> amplifierABSORPTION;        // Index 12
        public ConfigValueListener<Boolean> enableSLOW_FALLING;         // Index 13
        public ConfigValueListener<Integer> amplifierSLOW_FALLING;      // Index 13
        public ConfigValueListener<Boolean> enableFLIGHT;               // Index 14
        public ConfigValueListener<Boolean> enableWITHER_IMMUNITY;      // Index 15
        public ConfigValueListener<Boolean> enableNATURAL_IMMUNITY;     // Index 16
        public ConfigValueListener<Boolean> enableFIRE_EXTINGUISH;      // Index 17

        public AdvancedMaterialConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber, String name,
                boolean enableNightVision, int amplifierNightVision, boolean enableWaterBreathing, int amplifierWaterBreathing, boolean enableStrength, int amplifierStrength,
                boolean enableSpeed, int amplifierSpeed, boolean enableHaste, int amplifierHaste, boolean enableJumpBoost, int amplifierJumpBoost,
                boolean enableRegeneration, int amplifierRegeneration, boolean enableResistance, int amplifierResistance, boolean enableFireResistance, int amplifierFireResistance,
                boolean enableSaturation, int amplifierSaturation, boolean enableInvisibility, int amplifierInvisibility, boolean enableHealthBoost, int amplifierHealthBoost,
                boolean enableAbsorption, int amplifierAbsorption, boolean enableSlowFalling, int amplifierSlowFalling,
                boolean enableFlight, boolean enableWitherImmunity, boolean enableNaturalImmunity, boolean enableFireExtinguish) {
            super(builder, subscriber, name);
            builder.comment(name + " extended equipment Configuration")
                    .push(name).push("effects");
            enableNIGHT_VISION = subscriber.subscribe(builder.comment(name + " armor: enable/disable night vision")
                    .define("enableNIGHT_VISION", enableNightVision));
            amplifierNIGHT_VISION = subscriber.subscribe(builder.comment(name + " armor: night vision amplifier")
                    .define("amplifierNIGHT_VISION", amplifierNightVision));
            enableWATER_BREATHING = subscriber.subscribe(builder.comment(name + " armor: enable/disable water breathing")
                    .define("enableWATER_BREATHING", enableWaterBreathing));
            amplifierWATER_BREATHING = subscriber.subscribe(builder.comment(name + " armor: water breathing amplifier")
                    .define("amplifierWATER_BREATHING", amplifierWaterBreathing));
            enableSTRENGTH = subscriber.subscribe(builder.comment(name + " armor: enable/disable strength")
                    .define("enableSTRENGTH", enableStrength));
            amplifierSTRENGTH = subscriber.subscribe(builder.comment(name + " armor: strength amplifier")
                    .define("amplifierSTRENGTH", amplifierStrength));
            enableSPEED = subscriber.subscribe(builder.comment(name + " armor: enable/disable speed")
                    .define("enableSPEED", enableSpeed));
            amplifierSPEED = subscriber.subscribe(builder.comment(name + " armor: speed amplifier")
                    .define("amplifierSPEED", amplifierSpeed));
            enableHASTE = subscriber.subscribe(builder.comment(name + " armor: enable/disable haste")
                    .define("enableHASTE", enableHaste));
            amplifierHASTE = subscriber.subscribe(builder.comment(name + " armor: haste amplifier")
                    .define("amplifierHASTE", amplifierHaste));
            enableJUMP_BOOST = subscriber.subscribe(builder.comment(name + " armor: enable/disable jump boost")
                    .define("enableJUMP_BOOST", enableJumpBoost));
            amplifierJUMP_BOOST = subscriber.subscribe(builder.comment(name + " armor: jump boost amplifier")
                    .define("amplifierJUMP_BOOST", amplifierJumpBoost));
            enableREGENERATION = subscriber.subscribe(builder.comment(name + " armor: enable/disable regeneration")
                    .define("enableREGENERATION", enableRegeneration));
            amplifierREGENERATION = subscriber.subscribe(builder.comment(name + " armor: regeneration amplifier")
                    .define("amplifierREGENERATION", amplifierRegeneration));
            enableRESISTANCE = subscriber.subscribe(builder.comment(name + " armor: enable/disable resistance")
                    .define("enableRESISTANCE", enableResistance));
            amplifierRESISTANCE = subscriber.subscribe(builder.comment(name + " armor: resistance amplifier")
                    .define("amplifierRESISTANCE", amplifierResistance));
            enableFIRE_RESISTANCE = subscriber.subscribe(builder.comment(name + " armor: enable/disable fire resistance")
                    .define("enableFIRE_RESISTANCE", enableFireResistance));
            amplifierFIRE_RESISTANCE = subscriber.subscribe(builder.comment(name + " armor: fire resistance amplifier")
                    .define("amplifierFIRE_RESISTANCE", amplifierFireResistance));
            enableSATURATION = subscriber.subscribe(builder.comment(name + " armor: enable/disable saturation")
                    .define("enableSATURATION", enableSaturation));
            amplifierSATURATION = subscriber.subscribe(builder.comment(name + " armor: saturation amplifier")
                    .define("amplifierSATURATION", amplifierSaturation));
            enableINVISIBILITY = subscriber.subscribe(builder.comment(name + " armor: enable/disable invisibility")
                    .define("enableINVISIBILITY", enableInvisibility));
            amplifierINVISIBILITY = subscriber.subscribe(builder.comment(name + " armor: invisibility amplifier")
                    .define("amplifierINVISIBILITY", amplifierInvisibility));
            enableHEALTH_BOOST = subscriber.subscribe(builder.comment(name + " armor: enable/disable health boost")
                    .define("enableHEALTH_BOOST", enableHealthBoost));
            amplifierHEALTH_BOOST = subscriber.subscribe(builder.comment(name + " armor: health boost amplifier")
                    .define("amplifierHEALTH_BOOST", amplifierHealthBoost));
            enableABSORPTION = subscriber.subscribe(builder.comment(name + " armor: enable/disable absorption")
                    .define("enableABSORPTION", enableAbsorption));
            amplifierABSORPTION = subscriber.subscribe(builder.comment(name + " armor: absorption amplifier")
                    .define("amplifierABSORPTION", amplifierAbsorption));
            enableSLOW_FALLING = subscriber.subscribe(builder.comment(name + " armor: enable/disable slow falling")
                    .define("enableSLOW_FALLING", enableSlowFalling));
            amplifierSLOW_FALLING = subscriber.subscribe(builder.comment(name + " armor: slow falling amplifier")
                    .define("amplifierSLOW_FALLING", amplifierSlowFalling));
            enableFLIGHT = subscriber.subscribe(builder.comment(name + " armor: enable/disable flight")
                    .define("enableFLIGHT", enableFlight));
            enableWITHER_IMMUNITY = subscriber.subscribe(builder.comment(name + " armor: enable/disable wither immunity")
                    .define("enableWITHER_IMMUNITY", enableWitherImmunity));
            enableNATURAL_IMMUNITY = subscriber.subscribe(builder.comment(name + " armor: enable/disable natural immunity")
                    .define("enableNATURAL_IMMUNITY", enableNaturalImmunity));
            enableFIRE_EXTINGUISH = subscriber.subscribe(builder.comment(name + " armor: enable/disable fire extinguish")
                    .define("enableFIRE_EXTINGUISH", enableFireExtinguish));
            builder.pop().pop();
        }
    }


    public static class DropConfig {
        public ConfigValueListener<Boolean> enableRegularDrops;
        public ConfigValueListener<Boolean> enableSoulDrops; //Obtained via Soul Stealer

        public DropConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber, String name) {
            builder.comment(name + " drop configuration")
                    .push(name);
            enableRegularDrops = subscriber.subscribe(builder.comment(MODNAME + "'s " + name + " regular drops: enable/disable")
                    .define("enableRegularDrops", true));
            enableSoulDrops = subscriber.subscribe(builder.comment(MODNAME + "'s " + name + " soul drops: enable/disable")
                    .define("enableSoulDrops", true));
            builder.pop();
        }
    }

    public static class BossDropConfig {
        public ConfigValueListener<Boolean> enableTrophyDrops;
        public ConfigValueListener<Boolean> enableRegularDrops;
        public ConfigValueListener<Boolean> enableSoulDrops; //Obtained via Soul Stealer

        public BossDropConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber, String name) {
            builder.comment(name + " drop configuration")
                    .push(name);
            enableTrophyDrops = subscriber.subscribe(builder.comment(MODNAME + "'s " + name + " trophy drops: enable/disable  (CURRENTLY DISABLED BY THE MOD)")
                    .define("enableTrophyDrops", false));
            enableRegularDrops = subscriber.subscribe(builder.comment(MODNAME + "'s " + name + " regular drops: enable/disable")
                    .define("enableBossRegularDrops", true));
            enableSoulDrops = subscriber.subscribe(builder.comment(MODNAME + "'s " + name + " soul drops: enable/disable")
                    .define("enableBossSoulDrops", true));
            builder.pop();
        }
    }
}