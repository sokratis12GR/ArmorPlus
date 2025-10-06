package com.sofodev.armorplus.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

import java.util.List;

import static com.sofodev.armorplus.ArmorPlus.MODNAME;
import static java.util.Arrays.asList;

public record ArmorPlusConfig(
        MaterialConfig coalMaterial,
        MaterialConfig lapisMaterial,
        MaterialConfig redstoneMaterial,
        MaterialConfig chickenMaterial,
        MaterialConfig slimeMaterial,
        MaterialConfig emeraldMaterial,
        MaterialConfig obsidianMaterial,
        MaterialConfig infusedLavaMaterial,
        MaterialConfig frostMaterial,
        MaterialConfig frostLavaMaterial,
        AdvancedMaterialConfig guardianMaterial,
        AdvancedMaterialConfig superStarMaterial,
        AdvancedMaterialConfig enderDragonMaterial,
        AdvancedMaterialConfig slayerMaterial,
        MaterialConfig enhancedMaterial,
        ConfigValue<Boolean> enableThankYouAdvancement,
        ConfigValue<List<? extends String>> autoSmeltingInput,
        ConfigValue<List<? extends String>> autoSmeltingOutput,
        ConfigValue<List<? extends String>> enchantsThatWontWorkWithSoulHarden,
        BossDropConfig witherBossDrops,
        BossDropConfig enderDragonDrops,
        BossDropConfig elderGuardianDrops,
        DropConfig witherSkeletonDrops,
        DropConfig guardianDrops,
        DropConfig endermanDrops,
        DropConfig blazeDrops
) {

    public static ArmorPlusConfig create(ForgeConfigSpec.Builder builder) {
        // Advancements
        builder.comment("Configure advancements").push("advancements");
        var enableThankYouAdvancement = builder.comment("Enable/Disable the Thank You 6M advancement")
                .define("enableThankYou6M", true);
        builder.pop();

        // Items
        builder.comment("Configure Items").push("items");
        var coalMaterial = new MaterialConfig(builder, "coal");
        var lapisMaterial = new MaterialConfig(builder, "lapis");
        var redstoneMaterial = new MaterialConfig(builder, "redstone");
        var chickenMaterial = new MaterialConfig(builder, "chicken");
        var slimeMaterial = new MaterialConfig(builder, "slime");
        var emeraldMaterial = new MaterialConfig(builder, "emerald");
        var obsidianMaterial = new MaterialConfig(builder, "obsidian");
        var infusedLavaMaterial = new MaterialConfig(builder, "infused_lava");

        builder.comment("Infused Lava Extra ").push("infused_lava.extra");
        var autoSmeltingInput = builder.comment("Infused Lava Tools: Smelting Recipe Input (Blocks)")
                .defineList("smeltingInput", asList("minecraft:iron_ore", "minecraft:gold_ore",
                                "minecraft:sand", "minecraft:sandstone"),
                        s -> ResourceLocation.tryParse((String) s) != null);

        var autoSmeltingOutput = builder.comment("Infused Lava Tools: Smelting Recipe Output (Items)")
                .defineList("smeltingOutput", asList("minecraft:iron_ingot", "minecraft:gold_ingot",
                                "minecraft:glass", "minecraft:glass"),
                        s -> ResourceLocation.tryParse((String) s) != null);
        builder.pop(2);

        var frostMaterial = new MaterialConfig(builder, "frost");
        var frostLavaMaterial = new MaterialConfig(builder, "frost_lava");

        var guardianMaterial = new AdvancedMaterialConfig(builder, "guardian", true, 0, true, 0, false, 0,
                false, 0, false, 0, true, 1, false, 0, false, 0,
                false, 0, false, 0, false, 0, false, 0, false, 0,
                false, 0, false, false, false, true);

        var superStarMaterial = new AdvancedMaterialConfig(builder, "super_star", false, 0, false, 0, false, 0,
                false, 0, false, 0, false, 0, true, 0, false, 0,
                true, 0, false, 0, false, 0, false, 0, true, 0,
                false, 0, false, true, false, true);

        var enderDragonMaterial = new AdvancedMaterialConfig(builder, "ender_dragon", false, 0, false, 0, false, 0,
                false, 0, false, 0, false, 0, false, 0, false, 0,
                true, 0, false, 0, false, 0, false, 0, false, 0,
                true, 0, true, true, false, true);

        var slayerMaterial = new AdvancedMaterialConfig(builder, "slayer", false, 0, true, 0, false, 0,
                false, 0, false, 0, true, 1, true, 0, false, 0,
                true, 0, false, 0, false, 0, false, 0, true, 0,
                true, 0, true, true, true, true);

        var enhancedMaterial = new MaterialConfig(builder, "enhanced");

        // Enchantments
        builder.comment("Enchantment Configuration").push("enchantments");
        var enchantsThatWontWorkWithSoulHarden = builder.comment("is a list of registry names that will not work with the enchantment \"Soul Harden\"")
                .defineList("disallowWithSoulHardenList",
                        asList("minecraft:mending", "minecraft:unbreaking", "minecraft:vanishing_curse"),
                        s -> ResourceLocation.tryParse((String) s) != null);
        builder.pop();

        // Mob Drops
        builder.comment("Mob Drops Configuration").push("drops");
        var witherBossDrops = new BossDropConfig(builder, "wither_boss");
        var enderDragonDrops = new BossDropConfig(builder, "ender_dragon");
        var elderGuardianDrops = new BossDropConfig(builder, "elder_guardian");
        var witherSkeletonDrops = new DropConfig(builder, "wither_skeleton");
        var guardianDrops = new DropConfig(builder, "guardian");
        var endermanDrops = new DropConfig(builder, "enderman");
        var blazeDrops = new DropConfig(builder, "blaze");
        builder.pop();

        return new ArmorPlusConfig(
                coalMaterial, lapisMaterial, redstoneMaterial, chickenMaterial, slimeMaterial, emeraldMaterial, obsidianMaterial,
                infusedLavaMaterial, frostMaterial, frostLavaMaterial, guardianMaterial, superStarMaterial, enderDragonMaterial, slayerMaterial,
                enhancedMaterial, enableThankYouAdvancement, autoSmeltingInput, autoSmeltingOutput, enchantsThatWontWorkWithSoulHarden,
                witherBossDrops, enderDragonDrops, elderGuardianDrops, witherSkeletonDrops, guardianDrops, endermanDrops, blazeDrops
        );
    }

    public record MaterialConfig(ConfigValue<Boolean> enableArmorEffects,
                                 ConfigValue<Boolean> enableWeaponEffects) implements IMaterialConfig {
        public MaterialConfig(ForgeConfigSpec.Builder builder, String name) {
            this(
                    builder.comment(name + " armor: enable/disable full set effects")
                            .define("enableArmorEffects", true),
                    builder.comment(name + " weapons: enable/disable on hit effects")
                            .define("enableWeaponEffects", true)
            );
        }
    }

    public record AdvancedMaterialConfig(
            ConfigValue<Boolean> enableArmorEffects,
            ConfigValue<Boolean> enableWeaponEffects,
            ConfigValue<Boolean> enableNIGHT_VISION,
            ConfigValue<Integer> amplifierNIGHT_VISION,
            ConfigValue<Boolean> enableWATER_BREATHING,
            ConfigValue<Integer> amplifierWATER_BREATHING,
            ConfigValue<Boolean> enableSTRENGTH,
            ConfigValue<Integer> amplifierSTRENGTH,
            ConfigValue<Boolean> enableSPEED,
            ConfigValue<Integer> amplifierSPEED,
            ConfigValue<Boolean> enableHASTE,
            ConfigValue<Integer> amplifierHASTE,
            ConfigValue<Boolean> enableJUMP_BOOST,
            ConfigValue<Integer> amplifierJUMP_BOOST,
            ConfigValue<Boolean> enableREGENERATION,
            ConfigValue<Integer> amplifierREGENERATION,
            ConfigValue<Boolean> enableRESISTANCE,
            ConfigValue<Integer> amplifierRESISTANCE,
            ConfigValue<Boolean> enableFIRE_RESISTANCE,
            ConfigValue<Integer> amplifierFIRE_RESISTANCE,
            ConfigValue<Boolean> enableSATURATION,
            ConfigValue<Integer> amplifierSATURATION,
            ConfigValue<Boolean> enableINVISIBILITY,
            ConfigValue<Integer> amplifierINVISIBILITY,
            ConfigValue<Boolean> enableHEALTH_BOOST,
            ConfigValue<Integer> amplifierHEALTH_BOOST,
            ConfigValue<Boolean> enableABSORPTION,
            ConfigValue<Integer> amplifierABSORPTION,
            ConfigValue<Boolean> enableSLOW_FALLING,
            ConfigValue<Integer> amplifierSLOW_FALLING,
            ConfigValue<Boolean> enableFLIGHT,
            ConfigValue<Boolean> enableWITHER_IMMUNITY,
            ConfigValue<Boolean> enableNATURAL_IMMUNITY,
            ConfigValue<Boolean> enableFIRE_EXTINGUISH
    ) implements IMaterialConfig {
        public AdvancedMaterialConfig(ForgeConfigSpec.Builder builder, String name,
                                      boolean enableNightVision, int amplifierNightVision, boolean enableWaterBreathing, int amplifierWaterBreathing,
                                      boolean enableStrength, int amplifierStrength, boolean enableSpeed, int amplifierSpeed,
                                      boolean enableHaste, int amplifierHaste, boolean enableJumpBoost, int amplifierJumpBoost,
                                      boolean enableRegeneration, int amplifierRegeneration, boolean enableResistance, int amplifierResistance,
                                      boolean enableFireResistance, int amplifierFireResistance, boolean enableSaturation, int amplifierSaturation,
                                      boolean enableInvisibility, int amplifierInvisibility, boolean enableHealthBoost, int amplifierHealthBoost,
                                      boolean enableAbsorption, int amplifierAbsorption, boolean enableSlowFalling, int amplifierSlowFalling,
                                      boolean enableFlight, boolean enableWitherImmunity, boolean enableNaturalImmunity, boolean enableFireExtinguish) {
            this(
                    new MaterialConfig(builder, name).enableArmorEffects,
                    new MaterialConfig(builder, name).enableWeaponEffects,
                    builder.comment(name + " armor: enable/disable night vision").define("enableNIGHT_VISION", enableNightVision),
                    builder.comment(name + " armor: night vision amplifier").define("amplifierNIGHT_VISION", amplifierNightVision),
                    builder.comment(name + " armor: enable/disable water breathing").define("enableWATER_BREATHING", enableWaterBreathing),
                    builder.comment(name + " armor: water breathing amplifier").define("amplifierWATER_BREATHING", amplifierWaterBreathing),
                    builder.comment(name + " armor: enable/disable strength").define("enableSTRENGTH", enableStrength),
                    builder.comment(name + " armor: strength amplifier").define("amplifierSTRENGTH", amplifierStrength),
                    builder.comment(name + " armor: enable/disable speed").define("enableSPEED", enableSpeed),
                    builder.comment(name + " armor: speed amplifier").define("amplifierSPEED", amplifierSpeed),
                    builder.comment(name + " armor: enable/disable haste").define("enableHASTE", enableHaste),
                    builder.comment(name + " armor: haste amplifier").define("amplifierHASTE", amplifierHaste),
                    builder.comment(name + " armor: enable/disable jump boost").define("enableJUMP_BOOST", enableJumpBoost),
                    builder.comment(name + " armor: jump boost amplifier").define("amplifierJUMP_BOOST", amplifierJumpBoost),
                    builder.comment(name + " armor: enable/disable regeneration").define("enableREGENERATION", enableRegeneration),
                    builder.comment(name + " armor: regeneration amplifier").define("amplifierREGENERATION", amplifierRegeneration),
                    builder.comment(name + " armor: enable/disable resistance").define("enableRESISTANCE", enableResistance),
                    builder.comment(name + " armor: resistance amplifier").define("amplifierRESISTANCE", amplifierResistance),
                    builder.comment(name + " armor: enable/disable fire resistance").define("enableFIRE_RESISTANCE", enableFireResistance),
                    builder.comment(name + " armor: fire resistance amplifier").define("amplifierFIRE_RESISTANCE", amplifierFireResistance),
                    builder.comment(name + " armor: enable/disable saturation").define("enableSATURATION", enableSaturation),
                    builder.comment(name + " armor: saturation amplifier").define("amplifierSATURATION", amplifierSaturation),
                    builder.comment(name + " armor: enable/disable invisibility").define("enableINVISIBILITY", enableInvisibility),
                    builder.comment(name + " armor: invisibility amplifier").define("amplifierINVISIBILITY", amplifierInvisibility),
                    builder.comment(name + " armor: enable/disable health boost").define("enableHEALTH_BOOST", enableHealthBoost),
                    builder.comment(name + " armor: health boost amplifier").define("amplifierHEALTH_BOOST", amplifierHealthBoost),
                    builder.comment(name + " armor: enable/disable absorption").define("enableABSORPTION", enableAbsorption),
                    builder.comment(name + " armor: absorption amplifier").define("amplifierABSORPTION", amplifierAbsorption),
                    builder.comment(name + " armor: enable/disable slow falling").define("enableSLOW_FALLING", enableSlowFalling),
                    builder.comment(name + " armor: slow falling amplifier").define("amplifierSLOW_FALLING", amplifierSlowFalling),
                    builder.comment(name + " armor: enable/disable flight").define("enableFLIGHT", enableFlight),
                    builder.comment(name + " armor: enable/disable wither immunity").define("enableWITHER_IMMUNITY", enableWitherImmunity),
                    builder.comment(name + " armor: enable/disable natural immunity").define("enableNATURAL_IMMUNITY", enableNaturalImmunity),
                    builder.comment(name + " armor: enable/disable fire extinguish").define("enableFIRE_EXTINGUISH", enableFireExtinguish)
            );
        }
    }

    public interface IMaterialConfig {
        ConfigValue<Boolean> enableArmorEffects();

        ConfigValue<Boolean> enableWeaponEffects();
    }

    public record DropConfig(ConfigValue<Boolean> enableRegularDrops, ConfigValue<Boolean> enableSoulDrops) {
        public DropConfig(ForgeConfigSpec.Builder builder, String name) {
            this(
                    builder.comment(MODNAME + "'s " + name + " regular drops: enable/disable").define("enableRegularDrops", true),
                    builder.comment(MODNAME + "'s " + name + " soul drops: enable/disable").define("enableSoulDrops", true)
            );
        }
    }

    public record BossDropConfig(ConfigValue<Boolean> enableTrophyDrops, ConfigValue<Boolean> enableRegularDrops,
                                 ConfigValue<Boolean> enableSoulDrops) {
        public BossDropConfig(ForgeConfigSpec.Builder builder, String name) {
            this(
                    builder.comment(MODNAME + "'s " + name + " trophy drops: enable/disable  (CURRENTLY DISABLED BY THE MOD)").define("enableTrophyDrops", false),
                    builder.comment(MODNAME + "'s " + name + " regular drops: enable/disable").define("enableBossRegularDrops", true),
                    builder.comment(MODNAME + "'s " + name + " soul drops: enable/disable").define("enableBossSoulDrops", true)
            );
        }
    }
}
