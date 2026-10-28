package com.sofodev.armorplus.config;

import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

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
    public static MaterialConfig guardianMaterial;
    public static MaterialConfig superStarMaterial;
    public static MaterialConfig enderDragonMaterial;
    public static MaterialConfig wardenMaterial;
    public static MaterialConfig slayerMaterial;
    public static MaterialConfig arditeMaterial;
    public static MaterialConfig cobaltMaterial;
    public static MaterialConfig knightSlimeMaterial;
    public static MaterialConfig pigIronMaterial;
    public static MaterialConfig manyullynMaterial;
    public static MaterialConfig enhancedMaterial;
    public static MaterialConfig enhancedChainmailMaterial;
    public static MaterialConfig enhancedGoldenMaterial;
    public static MaterialConfig enhancedCopperMaterial;
    public static MaterialConfig enhancedIronMaterial;
    public static MaterialConfig enhancedDiamondMaterial;
    public static MaterialConfig enhancedNetheriteMaterial;
    public static MaterialConfig amethystMaterial;
    public static MaterialConfig quartzMaterial;
    public static MaterialConfig glowstoneMaterial;
    public static MaterialConfig honeyMaterial;
    public static BooleanValue enableAutoSmelting;
    public static ConfigValue<List<? extends String>> autoSmeltingRecipes;
    public static ConfigValue<List<? extends String>> hiddenItems;
    public static ConfigValue<List<? extends String>> hiddenBlocks;
    public static ConfigValue<List<? extends String>> enchantsThatWontWorkWithSoulHarden;
    public static BooleanValue enableThankYouAdvancement;
    public static BooleanValue enableLoreFlavor;
    public static BooleanValue enableLoreInfo;
    public static BossDropConfig witherBossDrops;
    public static BossDropConfig enderDragonDrops;
    public static BossDropConfig elderGuardianDrops;
    public static DropConfig witherSkeletonDrops;
    public static DropConfig guardianDrops;
    public static DropConfig endermanDrops;
    public static DropConfig blazeDrops;
    public static DropConfig wardenDrops;


    public static boolean isIdentifier(Object value) {
        return value instanceof String s && Identifier.tryParse(s) != null;
    }

    /**
     * A smelting recipe is a "block|result" pair of identifiers, e.g. "minecraft:sand|minecraft:glass".
     */
    public static boolean isSmeltingRecipe(Object value) {
        if (!(value instanceof String s)) return false;
        String[] parts = s.split("\\|", -1);
        return parts.length == 2 && isIdentifier(parts[0].trim()) && isIdentifier(parts[1].trim());
    }

    public static ArmorPlusConfig create(ModConfigSpec.Builder builder) {
        enableAutoSmelting = (builder.comment("Infused Lava tool auto-smelting")
                .push("autoSmelting").comment("Enable automatic smelting of configured mined blocks")
                .define("enabled", true));
        autoSmeltingRecipes = (builder.comment("Infused Lava Tools: Smelting Recipes, formatted as \"block|result\" (e.g. \"minecraft:sand|minecraft:glass\")")
                .defineListAllowEmpty("smeltingRecipes", asList(
                                "minecraft:iron_ore|minecraft:iron_ingot",
                                "minecraft:gold_ore|minecraft:gold_ingot",
                                "minecraft:sand|minecraft:glass",
                                "minecraft:sandstone|minecraft:smooth_sandstone",
                                "minecraft:wet_sponge|minecraft:sponge",
                                "minecraft:clay|minecraft:terracotta",
                                "minecraft:stone_bricks|minecraft:cracked_stone_bricks",
                                "minecraft:cobblestone|minecraft:stone",
                                "minecraft:stone|minecraft:stone",
                                "minecraft:acacia_log|minecraft:charcoal",
                                "minecraft:birch_log|minecraft:charcoal",
                                "minecraft:dark_oak_log|minecraft:charcoal",
                                "minecraft:jungle_log|minecraft:charcoal",
                                "minecraft:oak_log|minecraft:charcoal",
                                "minecraft:spruce_log|minecraft:charcoal",
                                "minecraft:netherrack|minecraft:nether_brick",
                                "minecraft:ancient_debris|minecraft:netherite_scrap",
                                "armorplus:ore_lava_crystal|armorplus:infused_lava_crystal",
                                "armorplus:ore_frost_crystal|armorplus:infused_frost_crystal"),
                        () -> "modid:block|modid:item", ArmorPlusConfig::isSmeltingRecipe));
        hiddenItems = (builder.pop()
                .comment("Feature-flag-independent availability (requires restart)")
                .push("availability")
                .comment("Item IDs disabled in commands, recipes' feature checks and creative visibility. Block items follow hiddenBlocks too.")
                .defineListAllowEmpty("hiddenItems", List.<String>of(), () -> "armorplus:item_id", ArmorPlusConfig::isIdentifier));
        hiddenBlocks = (builder.comment("Block IDs disabled in /setblock and item visibility where applicable.")
                .defineListAllowEmpty("hiddenBlocks", List.<String>of(), () -> "armorplus:block_id", ArmorPlusConfig::isIdentifier));
        enchantsThatWontWorkWithSoulHarden = (builder.pop().comment("Enchantment Configuration")
                .push("enchantments")
                .comment("is a list of registry names that will not work with the enchantment \"Soul Harden\"")
                .defineListAllowEmpty("disallowWithSoulHardenList",
                        asList("minecraft:mending", "minecraft:unbreaking", "minecraft:vanishing_curse"),
                        () -> "modid:enchantment", ArmorPlusConfig::isIdentifier)
        );
        //Advancements
        enableThankYouAdvancement = (builder.pop().comment("Configure advancements")
                .push("advancements").comment("Enable/Disable the Thank You 6M advancement")
                .define("enableThankYou6M", true));
        enableLoreFlavor = (builder.pop().comment("Configure item tooltips (client side)")
                .push("tooltips").comment("Show the flavor (lore) line on items and materials")
                .define("enableFlavorText", true));
        enableLoreInfo = (builder.comment("Show the detailed info lines (sources, usage, ...) on items and materials. When disabled, the \"Press Shift\" hint is hidden too")
                .define("enableInfoText", true));
        builder.pop();

        builder.comment("Configure Items")
                .push("items");
        // Buff entries use the "id|level" format (optionally "id|level|seconds"), see BuffParser
        coalMaterial = MaterialConfig.create("coal", builder,
                List.of("minecraft:night_vision|1"),
                List.of("minecraft:blindness|1|10"));
        lapisMaterial = MaterialConfig.create("lapis", builder,
                List.of("minecraft:water_breathing|1"),
                List.of("minecraft:nausea|1|10"));
        redstoneMaterial = MaterialConfig.create("redstone", builder,
                List.of("minecraft:haste|2"),
                List.of("minecraft:mining_fatigue|2|10"));
        chickenMaterial = MaterialConfig.create("chicken", builder,
                List.of("minecraft:speed|4"),
                List.of("minecraft:levitation|1|2"));
        slimeMaterial = MaterialConfig.create("slime", builder,
                List.of("minecraft:jump_boost|4", "minecraft:slow_falling|2"),
                List.of("minecraft:slowness|2|5"));
        emeraldMaterial = MaterialConfig.create("emerald", builder,
                List.of("minecraft:speed|2"),
                List.of("minecraft:slowness|1|20"));
        obsidianMaterial = MaterialConfig.create("obsidian", builder,
                List.of("minecraft:resistance|2"),
                List.of("minecraft:weakness|3|20"));
        infusedLavaMaterial = MaterialConfig.create("infused_lava", builder,
                List.of("minecraft:fire_resistance|1", "armorplus:fire_extinguish"),
                List.of("armorplus:ignite"));
        frostMaterial = MaterialConfig.create("frost", builder,
                List.of("armorplus:fire_weakness", "armorplus:powdered_snow_immunity"),
                List.of("minecraft:slowness|3|5", "minecraft:mining_fatigue|1|5"));
        frostLavaMaterial = MaterialConfig.create("frost_lava", builder,
                List.of("armorplus:natural_immunity", "armorplus:powdered_snow_immunity"),
                List.of("minecraft:slowness|2|5", "armorplus:ignite"));
        guardianMaterial = MaterialConfig.create("guardian", builder,
                List.of("minecraft:night_vision|1", "conduit_power|2", "dolphins_grace|3",
                        "armorplus:fire_extinguish", "armorplus:mining_fatigue_immunity"),
                List.of("minecraft:weakness|2|4", "minecraft:nausea|2|20"));
        superStarMaterial = MaterialConfig.create("super_star", builder,
                List.of("minecraft:regeneration|1|200", "minecraft:fire_resistance|1", "minecraft:absorption|1|200",
                        "armorplus:wither_immunity", "armorplus:fire_extinguish"),
                List.of("minecraft:wither|2|4", "minecraft:glowing|1|20"));
        enderDragonMaterial = MaterialConfig.create("ender_dragon", builder,
                List.of("minecraft:fire_resistance|1", "minecraft:slow_falling|1", "armorplus:flight",
                        "armorplus:wither_immunity", "armorplus:fire_extinguish"),
                List.of("minecraft:wither|4|4", "minecraft:slowness|2|20"));
        wardenMaterial = MaterialConfig.create("warden", builder,
                List.of("minecraft:night_vision|1", "minecraft:slow_falling|1", "armorplus:sculk_immunity",
                        "armorplus:warden_pacify", "armorplus:darkness_immunity"),
                List.of("minecraft:darkness|1|20", "minecraft:nausea|2|20"));
        slayerMaterial = MaterialConfig.create("slayer", builder,
                List.of("minecraft:night_vision|1", "minecraft:water_breathing|1", "minecraft:jump_boost|2",
                        "minecraft:regeneration|1|200", "minecraft:fire_resistance|1", "minecraft:absorption|1|200",
                        "minecraft:slow_falling|1", "armorplus:flight", "armorplus:wither_immunity",
                        "armorplus:natural_immunity", "armorplus:fire_extinguish", "conduit_power|2", "dolphins_grace|3",
                        "armorplus:sculk_immunity", "armorplus:warden_pacify", "armorplus:darkness_immunity",
                        "armorplus:mining_fatigue_immunity"),
                List.of("minecraft:wither|4|4", "minecraft:slowness|2|20", "minecraft:glowing|1|20",
                        "minecraft:weakness|2|4", "minecraft:nausea|2|20", "minecraft:darkness|1|20"));
        arditeMaterial = MaterialConfig.create("ardite", builder,
                List.of("minecraft:fire_resistance|1"),
                List.of("armorplus:ignite"));
        cobaltMaterial = MaterialConfig.create("cobalt", builder,
                List.of("minecraft:haste|1"),
                List.of("minecraft:weakness|1|5"));
        knightSlimeMaterial = MaterialConfig.create("knight_slime", builder,
                List.of("minecraft:resistance|1", "minecraft:jump_boost|1"),
                List.of("minecraft:slowness|1|5", "minecraft:glowing|1|10"));
        pigIronMaterial = MaterialConfig.create("pig_iron", builder,
                List.of("minecraft:saturation|1"),
                List.of("minecraft:hunger|2|10"));
        manyullynMaterial = MaterialConfig.create("manyullyn", builder,
                List.of("minecraft:strength|1", "minecraft:fire_resistance|1"),
                List.of("minecraft:wither|1|4", "armorplus:ignite"));
        // Fallback for equipment without a config of its own; the enhanced vanilla sets below each have theirs
        enhancedMaterial = MaterialConfig.create("enhanced", builder, List.of(), null);
        enhancedChainmailMaterial = MaterialConfig.create("enhanced_chainmail", builder,
                List.of("minecraft:speed|1"),
                List.of("minecraft:slowness|1|3"));
        enhancedGoldenMaterial = MaterialConfig.create("enhanced_golden", builder,
                List.of("minecraft:absorption|1", "minecraft:luck|1"),
                List.of("minecraft:glowing|1|10", "minecraft:weakness|1|4"));
        enhancedCopperMaterial = MaterialConfig.create("enhanced_copper", builder,
                List.of("minecraft:haste|1"),
                List.of("minecraft:mining_fatigue|1|5"));
        enhancedIronMaterial = MaterialConfig.create("enhanced_iron", builder,
                List.of("minecraft:resistance|1"),
                List.of("minecraft:slowness|1|4"));
        enhancedDiamondMaterial = MaterialConfig.create("enhanced_diamond", builder,
                List.of("minecraft:strength|1"),
                List.of("minecraft:weakness|2|6"));
        enhancedNetheriteMaterial = MaterialConfig.create("enhanced_netherite", builder,
                List.of("minecraft:resistance|1", "minecraft:fire_resistance|1"),
                List.of("minecraft:wither|1|4"));
        amethystMaterial = MaterialConfig.create("amethyst", builder,
                List.of("minecraft:regeneration|1", "armorplus:illuminate"),
                List.of("minecraft:glowing|1|20"));
        quartzMaterial = MaterialConfig.create("quartz", builder,
                List.of("minecraft:absorption|1"),
                List.of("minecraft:blindness|1|20"));
        glowstoneMaterial = MaterialConfig.create("glowstone", builder,
                List.of("minecraft:night_vision|1", "armorplus:illuminate"),
                List.of("minecraft:nausea|1|10"));
        honeyMaterial = MaterialConfig.create("honey", builder,
                List.of("minecraft:saturation|1"),
                List.of("minecraft:slowness|1|10"));
        builder.pop();
        builder.comment("Mob Drops Configuration").push("drops");
        witherBossDrops = BossDropConfig.create("wither_boss", builder);
        enderDragonDrops = BossDropConfig.create("ender_dragon", builder);
        elderGuardianDrops = BossDropConfig.create("elder_guardian", builder);
        witherSkeletonDrops = DropConfig.create("wither_skeleton", builder, 0, 3, 0.25);
        guardianDrops = DropConfig.create("guardian", builder, 0, 3, 0.25);
        endermanDrops = DropConfig.create("enderman", builder, 0, -1, 0.25);
        blazeDrops = DropConfig.create("blaze", builder, 0, -1, 0.25);
        wardenDrops = DropConfig.create("warden", builder, 1, 1, -1);
        builder.pop();

        return new ArmorPlusConfig();
    }

    /**
     * Per-material configuration.
     * <ul>
     *     <li>{@code armorBuffs}: full set effects, as "id|level" entries (e.g. "minecraft:haste|1", "armorplus:flight")</li>
     *     <li>{@code weaponBuffs}: on-hit effects, as "id|level|seconds" entries; {@code null} for materials without weapons</li>
     *     <li>{@code disabledBuffs}: ids of buffs from either list to switch off without removing them</li>
     * </ul>
     */
    public record MaterialConfig(String name, ConfigValue<Boolean> enableArmorEffects,
                                 ConfigValue<Boolean> enableWeaponEffects,
                                 ConfigValue<List<? extends String>> armorBuffs,
                                 ConfigValue<List<? extends String>> weaponBuffs,
                                 ConfigValue<List<? extends String>> disabledBuffs) implements IMaterialConfig {

        public static MaterialConfig create(String name, ModConfigSpec.Builder builder, List<String> armorBuffs, List<String> weaponBuffs) {
            builder.comment(name + " equipment Configuration")
                    .push(name);
            ConfigValue<Boolean> enableArmorEffects = (builder.comment(name + " armor: enable/disable full set effects")
                    .define("enableArmorEffects", true));
            ConfigValue<Boolean> enableWeaponEffects = (builder.comment(name + " weapons: enable/disable on hit effects")
                    .define("enableWeaponEffects", true));
            ConfigValue<List<? extends String>> armorBuffsValue = (builder.comment(name + " armor: full set effects, formatted as \"modid:effect|level\" (e.g. \"minecraft:haste|1\"), optionally \"|seconds\" for the duration. ArmorPlus abilities: armorplus:flight, armorplus:illuminate, armorplus:wither_immunity, armorplus:fire_weakness, armorplus:natural_immunity, armorplus:fire_extinguish, armorplus:darkness_immunity, armorplus:mining_fatigue_immunity, armorplus:powdered_snow_immunity, armorplus:sculk_immunity, armorplus:warden_pacify")
                    .defineListAllowEmpty("armorBuffs", armorBuffs, () -> BuffParser.FORMAT, BuffParser::isValid));
            ConfigValue<List<? extends String>> weaponBuffsValue = weaponBuffs == null ? null : (builder.comment(name + " weapons: effects applied to hit targets, formatted as \"modid:effect|level|seconds\" (e.g. \"minecraft:wither|2|4\"). ArmorPlus abilities: armorplus:ignite")
                    .defineListAllowEmpty("weaponBuffs", weaponBuffs, () -> BuffParser.FORMAT, BuffParser::isValid));
            ConfigValue<List<? extends String>> disabledBuffs = (builder.comment(name + ": ids of individual buffs to disable (e.g. \"minecraft:night_vision\") while keeping them in the lists above")
                    .defineListAllowEmpty("disabledBuffs", List.<String>of(), () -> "modid:effect", ArmorPlusConfig::isIdentifier));
            builder.pop();

            return new MaterialConfig(name, enableArmorEffects, enableWeaponEffects, armorBuffsValue, weaponBuffsValue, disabledBuffs);
        }

        public List<BuffInstance> armorBuffInstances() {
            return BuffParser.armor(armorBuffs.get(), disabledBuffs.get());
        }

        public List<BuffInstance> weaponBuffInstances() {
            return weaponBuffs == null ? List.of() : BuffParser.weapon(weaponBuffs.get(), disabledBuffs.get());
        }
    }

    public interface IMaterialConfig {
        ConfigValue<Boolean> enableArmorEffects();

        ConfigValue<Boolean> enableWeaponEffects();
    }

    /**
     * Regular (material) and soul drops of a regular mob.
     * <ul>
     *     <li>{@code regularDropChance}: chance (0-1) that the material drops at all; the amount is then random between {@code regularDropMin} and {@code regularDropMax}</li>
     *     <li>{@code soulDropChance}: chance (0-1) that a Soul Stealer kill drops the mob's soul</li>
     * </ul>
     * The chance/amount options only exist for the drops the mob actually has.
     */
    public record DropConfig(ConfigValue<Boolean> enableRegularDrops, ConfigValue<Boolean> enableSoulDrops,
                             ConfigValue<Double> regularDropChance, ConfigValue<Integer> regularDropMin,
                             ConfigValue<Integer> regularDropMax, ConfigValue<Double> soulDropChance) {

        /**
         * @param regularMax default maximum material amount, or -1 if the mob has no regular drop
         * @param soulChance default soul chance, or -1 if the mob has no soul drop
         */
        public static DropConfig create(String name, ModConfigSpec.Builder builder, int regularMin, int regularMax, double soulChance) {
            builder.comment(name + " drop configuration")
                    .push(name);
            ConfigValue<Boolean> enableRegularDrops = (builder.comment(MODNAME + "'s " + name + " regular drops: enable/disable")
                    .define("enableRegularDrops", true));
            ConfigValue<Boolean> enableSoulDrops = (builder.comment(MODNAME + "'s " + name + " soul drops: enable/disable")
                    .define("enableSoulDrops", true));
            ConfigValue<Double> regularChance = null;
            ConfigValue<Integer> min = null, max = null;
            if (regularMax >= 0) {
                regularChance = builder.comment(name + " regular drops: chance (0.0 - 1.0) that the material drops on death")
                        .defineInRange("regularDropChance", 1.0, 0.0, 1.0);
                min = builder.comment(name + " regular drops: minimum amount")
                        .defineInRange("regularDropMin", regularMin, 0, 64);
                max = builder.comment(name + " regular drops: maximum amount")
                        .defineInRange("regularDropMax", regularMax, 0, 64);
            }
            ConfigValue<Double> soul = null;
            if (soulChance >= 0) {
                soul = builder.comment(name + " soul drops: chance (0.0 - 1.0) that a kill with a Soul Stealer weapon drops the soul")
                        .defineInRange("soulDropChance", soulChance, 0.0, 1.0);
            }
            builder.pop();

            return new DropConfig(enableRegularDrops, enableSoulDrops, regularChance, min, max, soul);
        }

    }

    /** Same as {@link DropConfig} for bosses, which additionally have trophy drops. */
    public record BossDropConfig(ConfigValue<Boolean> enableTrophyDrops, ConfigValue<Boolean> enableRegularDrops,
                                 ConfigValue<Boolean> enableSoulDrops, ConfigValue<Double> regularDropChance,
                                 ConfigValue<Integer> regularDropMin, ConfigValue<Integer> regularDropMax,
                                 ConfigValue<Double> soulDropChance) {


        public static BossDropConfig create(String name, ModConfigSpec.Builder builder) {
            builder.comment(name + " drop configuration")
                    .push(name);
            ConfigValue<Boolean> enableTrophyDrops = (builder.comment(MODNAME + "'s " + name + " trophy drops: enable/disable  (CURRENTLY DISABLED BY THE MOD)")
                    .define("enableTrophyDrops", false));
            ConfigValue<Boolean> enableRegularDrops = (builder.comment(MODNAME + "'s " + name + " regular drops: enable/disable")
                    .define("enableBossRegularDrops", true));
            ConfigValue<Boolean> enableSoulDrops = (builder.comment(MODNAME + "'s " + name + " soul drops: enable/disable")
                    .define("enableBossSoulDrops", true));
            ConfigValue<Double> regularChance = builder.comment(name + " regular drops: chance (0.0 - 1.0) that the material drops on death")
                    .defineInRange("regularDropChance", 1.0, 0.0, 1.0);
            ConfigValue<Integer> min = builder.comment(name + " regular drops: minimum amount")
                    .defineInRange("regularDropMin", 4, 0, 64);
            ConfigValue<Integer> max = builder.comment(name + " regular drops: maximum amount")
                    .defineInRange("regularDropMax", 6, 0, 64);
            ConfigValue<Double> soul = builder.comment(name + " soul drops: chance (0.0 - 1.0) that a kill with a Soul Stealer weapon drops the boss soul")
                    .defineInRange("soulDropChance", 1.0, 0.0, 1.0);
            builder.pop();

            return new BossDropConfig(enableTrophyDrops, enableRegularDrops, enableSoulDrops, regularChance, min, max, soul);
        }

    }
}
