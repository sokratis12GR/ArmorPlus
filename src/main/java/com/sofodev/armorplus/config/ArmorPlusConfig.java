package com.sofodev.armorplus.config;

import com.sofodev.armorplus.registry.item.armor.SetRequirement;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.rune.RitualCatalysts;
import com.sofodev.armorplus.registry.rune.RuneOverrides;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * All ArmorPlus config values (except the equipment stats, see {@link ArmorPlusStatsConfig}), split over several files so
 * each stays short:
 * <ul>
 *     <li>{@value #COMMON_FILE} ({@link #create}): auto-smelting, availability, enchantments, advancements</li>
 *     <li>{@value #EFFECTS_FILE} ({@link #createEffects}): set effect rules and each material's armor and weapon effects</li>
 *     <li>{@value #DROPS_FILE} ({@link #createDrops}): mob and boss drops</li>
 *     <li>{@value #RUNES_FILE} ({@link #createRunes}): runes, sockets and extraction</li>
 *     <li>{@value #CLIENT_FILE} ({@link #createClient}): tooltips and overlays (client side)</li>
 * </ul>
 */
public class ArmorPlusConfig {

    public static final String COMMON_FILE = "armorplus/common.toml";
    public static final String EFFECTS_FILE = "armorplus/effects.toml";
    public static final String DROPS_FILE = "armorplus/drops.toml";
    public static final String RUNES_FILE = "armorplus/runes.toml";
    public static final String CLIENT_FILE = "armorplus/client/client.toml";

    /* ---------------------------------------- armorplus/common.toml ---------------------------------------- */
    public static BooleanValue enableAutoSmelting;
    public static ConfigValue<List<? extends String>> autoSmeltingRecipes;
    public static ConfigValue<List<? extends String>> hiddenItems;
    public static ConfigValue<List<? extends String>> hiddenBlocks;
    public static ConfigValue<List<? extends String>> enchantsThatWontWorkWithSoulHarden;
    public static BooleanValue enableThankYouAdvancement;

    /* ---------------------------------------- armorplus/effects.toml ---------------------------------------- */
    public static ConfigValue<Integer> defaultSetPieces;
    public static BooleanValue allowEffectToggles;
    public static ConfigValue<Integer> instantEffectCooldown;
    public static ConfigValue<Double> heldInstantEffectStrength;
    public static ConfigValue<List<? extends String>> effectSlots;
    public static ConfigValue<List<? extends String>> singleLevelEffects;
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

    /* ---------------------------------------- armorplus/drops.toml ---------------------------------------- */
    public static DropConfig witherBossDrops;
    public static DropConfig enderDragonDrops;
    public static DropConfig elderGuardianDrops;
    public static DropConfig witherSkeletonDrops;
    public static DropConfig guardianDrops;
    public static DropConfig endermanDrops;
    public static DropConfig blazeDrops;
    public static DropConfig wardenDrops;

    /* ---------------------------------------- armorplus/runes.toml ---------------------------------------- */
    public static BooleanValue enableRunes;
    public static BooleanValue allowVanillaGear;
    public static BooleanValue allowModdedGear;
    public static ConfigValue<List<? extends String>> excludedRuneMods;
    public static BooleanValue returnRunesOnRemoval;
    public static ConfigValue<List<? extends String>> runeOverrides;
    public static ConfigValue<List<? extends String>> ritualCatalysts;
    public static BooleanValue randomSockets;
    public static ConfigValue<Integer> socketXpCost;
    public static BooleanValue enableSocketPunch;
    public static ConfigValue<Integer> socketPunchXpCost;
    public static ConfigValue<Integer> runeRemovalXpCost;
    public static ConfigValue<Integer> maxRuneLevel;
    public static ConfigValue<Integer> upgradeBaseCost;
    public static ConfigValue<Integer> upgradeCostPerLevel;
    public static ConfigValue<Integer> maxArmorSockets;
    public static ConfigValue<List<? extends Integer>> armorSocketWeights;
    public static ConfigValue<Integer> maxWeaponSockets;
    public static ConfigValue<List<? extends Integer>> weaponSocketWeights;
    public static ConfigValue<Integer> maxToolSockets;
    public static ConfigValue<List<? extends Integer>> toolSocketWeights;
    public static ConfigValue<Integer> maxRangedSockets;
    public static ConfigValue<List<? extends Integer>> rangedSocketWeights;
    public static ConfigValue<Integer> enderShotCooldown;
    public static ConfigValue<Integer> enderShotDurability;
    public static ConfigValue<Integer> extractionBaseCost;
    public static ConfigValue<Integer> extractionCostPerLevel;
    public static ConfigValue<Integer> extractionLevelReduction;
    public static BooleanValue destroySourceOnExtract;
    public static ConfigValue<Double> extractionDurabilityCost;
    public static ConfigValue<Double> extractionDurabilityCostFewEffects;
    public static ConfigValue<Integer> extractionFewEffects;
    public static ConfigValue<Integer> extractionUpgradeCostPerLevel;
    public static ConfigValue<List<? extends String>> worldRunes;
    public static ConfigValue<List<? extends String>> runeLootSources;

    /* ---------------------------------------- armorplus/client/client.toml ---------------------------------------- */
    public static BooleanValue enableLoreFlavor;
    public static BooleanValue enableLoreInfo;
    public static BooleanValue showSocketOverlay;


    public static boolean isIdentifier(Object value) {
        return value instanceof String s && ResourceLocation.tryParse(s) != null;
    }

    /**
     * A smelting recipe is a "block|result" pair of identifiers, e.g. "minecraft:sand|minecraft:glass".
     */
    public static boolean isSmeltingRecipe(Object value) {
        if (!(value instanceof String s)) return false;
        String[] parts = s.split("\\|", -1);
        return parts.length == 2 && isIdentifier(parts[0].trim()) && isIdentifier(parts[1].trim());
    }

    /** An "id|pieces" pair with comma separated slots head, chest, legs and feet (optionally how many of them) or a piece count, e.g. "minecraft:conduit_power|legs,feet", "minecraft:speed|chest,legs,feet:2" or "minecraft:speed|2". */
    public static boolean isEffectSlot(Object value) {
        if (!(value instanceof String s)) return false;
        String[] parts = s.split("\\|", -1);
        return parts.length == 2 && isIdentifier(parts[0].trim()) && SetRequirement.isValid(parts[1]);
    }

    /** Socket count weights, one non-negative weight per socket count (index 0 = no sockets). */
    public static boolean isWeight(Object value) {
        return value instanceof Integer i && i >= 0;
    }

    /** A "kind|buff entry" pair, e.g. "armor|minecraft:luck|1", "weapon|minecraft:poison|1|5" or "tool|minecraft:haste|2". */
    public static boolean isWorldRune(Object value) {
        if (!(value instanceof String s)) return false;
        int split = s.indexOf('|');
        if (split < 0) return false;
        String kind = s.substring(0, split).trim();
        return (kind.equals("armor") || kind.equals("weapon") || kind.equals("tool") || kind.equals("ranged")) && BuffParser.isValid(s.substring(split + 1));
    }

    /** A "loot_table|chance" pair, e.g. "minecraft:chests/ancient_city|0.15". */
    public static boolean isLootSource(Object value) {
        if (!(value instanceof String s)) return false;
        String[] parts = s.split("\\|", -1);
        if (parts.length != 2 || !isIdentifier(parts[0].trim())) return false;
        try {
            double chance = Double.parseDouble(parts[1].trim());
            return chance >= 0.0 && chance <= 1.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /** armorplus/common.toml: the general options. */
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
        enableThankYouAdvancement = (builder.pop().comment("Configure advancements")
                .push("advancements").comment("Enable/Disable the Thank You 6M advancement")
                .define("enableThankYou6M", true));
        builder.pop();
        return new ArmorPlusConfig();
    }

    /** armorplus/client/client.toml: tooltips and overlays. */
    public static Void createClient(ModConfigSpec.Builder builder) {
        enableLoreFlavor = builder.comment("Show the flavor (lore) line on items and materials")
                .define("enableFlavorText", true);
        enableLoreInfo = builder.comment("Show the detailed info lines (sources, usage, ...) on items and materials. When disabled, the \"Press Shift\" hint is hidden too")
                .define("enableInfoText", true);
        showSocketOverlay = builder.comment("Show the socket symbol on equipment in inventories, including vanilla and other mods' equipment")
                .define("showSocketOverlay", true);
        return null;
    }

    /** armorplus/effects.toml: the set effect rules, then one section per material. */
    public static Void createEffects(ModConfigSpec.Builder builder) {
        defaultSetPieces = builder.comment(
                        "Effect entries (armorBuffs, weaponBuffs) are formatted as \"modid:effect|level\", optionally \"|seconds\" for the duration, e.g. \"minecraft:haste|2\" or \"minecraft:wither|2|4\".",
                        "Any vanilla or modded effect works, as do the ArmorPlus abilities: armorplus:flight, armorplus:illuminate, armorplus:wither_immunity, armorplus:fire_weakness, armorplus:natural_immunity,",
                        "armorplus:fire_extinguish, armorplus:darkness_immunity, armorplus:mining_fatigue_immunity, armorplus:powdered_snow_immunity, armorplus:sculk_immunity, armorplus:warden_pacify (armor) and armorplus:ignite (weapons).",
                        "Start an entry with \"!\" to switch it off without removing it, e.g. \"!minecraft:haste|2\".",
                        "Armor entries can end with the pieces of the set they need: \"@head\" (or chest, legs, feet), \"@legs,feet\" (all of these pieces), \"@chest,legs,feet:2\" (any 2 of these pieces) or \"@2\" (any 2 pieces), e.g. \"minecraft:night_vision|1@head\".",
                        "Entries without it use their \"effectSlots\" default, otherwise \"defaultSetPieces\".",
                        "",
                        "Number of worn pieces of a set its effects need (4 = full set), unless the entry says otherwise or the effect is listed in \"effectSlots\"")
                .defineInRange("defaultSetPieces", 4, 1, 4);
        allowEffectToggles = builder.comment("Let players switch individual set and rune effects on/off for themselves, per set (Armor Effects key, G by default), tool rune effects (Tool Effects key, H by default), weapon effects (Weapon Effects key, J by default) and ranged rune effects (Ranged Effects key, K by default)")
                .define("allowEffectToggles", true);
        instantEffectCooldown = builder.comment("Seconds before a worn or held instant effect (Instant Health, Saturation, ...) can fire again for a player, shared by all gear. Instant Health and Saturation wait until they'd help, and they don't start the cooldown until then. 0 = once a second. On-hit effects of weapons aren't affected")
                .defineInRange("instantEffectCooldown", 5, 0, 3600);
        heldInstantEffectStrength = builder.comment("Strength of instant effects from held tools, compared to worn armor (0.5 = half). Instant Health heals this fraction of the normal amount, effects without a strength like Saturation aren't affected")
                .defineInRange("heldInstantEffectStrength", 0.5, 0.0, 1.0);
        effectSlots = builder.comment("The default pieces an effect needs, for every set, formatted as \"id|pieces\": comma separated slots head, chest, legs and feet (all of them needed, e.g. \"minecraft:night_vision|head\" or \"minecraft:conduit_power|legs,feet\"; \":count\" needs only that many of them, e.g. \"minecraft:speed|chest,legs,feet:2\") or a piece count (any of that many pieces, e.g. \"minecraft:speed|2\"). "
                        + "Set effects with slots are only listed on those pieces and runes of the effect only fit into them; a piece count without slots only applies to sets, runes of the effect fit into any piece. "
                        + "A set can use other pieces for an effect by ending its entry with them, e.g. \"minecraft:night_vision|1@chest\" or \"minecraft:night_vision|1@4\"")
                .defineListAllowEmpty("effectSlots", List.of(
                        "minecraft:night_vision|head",
                        "minecraft:water_breathing|head",
                        "minecraft:saturation|head,chest,legs,feet:2",
                        "armorplus:illuminate|head,chest,legs,feet:2",
                        "armorplus:darkness_immunity|head",
                        "minecraft:haste|chest",
                        "minecraft:resistance|head,chest,legs,feet:2",
                        "minecraft:fire_resistance|head,chest,legs,feet:2",
                        "minecraft:regeneration|head,chest,legs,feet:2",
                        "minecraft:absorption|head,chest,legs,feet:2",
                        "minecraft:strength|head,chest,legs,feet:2",
                        "minecraft:luck|legs",
                        "armorplus:fire_extinguish|head,chest,legs,feet:2",
                        "armorplus:mining_fatigue_immunity|chest,legs:2",
                        "minecraft:speed|legs,feet:1",
                        "minecraft:jump_boost|legs,feet:1",
                        "minecraft:slow_falling|legs,feet:1",
                        "minecraft:hero_of_the_village|head,chest:1",
                        "minecraft:conduit_power|legs,feet:1",
                        "minecraft:dolphins_grace|legs,feet:1",
                        "minecraft:health_boost|head,chest,legs,feet:2",
                        "minecraft:invisibility|head,chest,legs,feet:2",
                        "armorplus:flight|head,chest,legs,feet",
                        "armorplus:wither_immunity|head,chest,legs,feet",
                        "armorplus:natural_immunity|head,chest,legs,feet",
                        "armorplus:powdered_snow_immunity|head,chest,legs,feet",
                        "armorplus:sculk_immunity|head,chest,legs,feet",
                        "armorplus:warden_pacify|head,chest,legs,feet",
                        "armorplus:fire_weakness|head,chest,legs,feet:1"),
                        () -> "modid:effect|head", ArmorPlusConfig::isEffectSlot);
        singleLevelEffects = builder.comment("Effects whose level makes no difference, so tooltips never show a level for them (levels I are never shown)")
                .defineListAllowEmpty("singleLevelEffects", List.of("minecraft:night_vision", "minecraft:fire_resistance", "minecraft:water_breathing",
                        "minecraft:invisibility", "minecraft:slow_falling", "minecraft:glowing", "minecraft:dolphins_grace"), () -> "modid:effect", ArmorPlusConfig::isIdentifier);

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
                List.of("minecraft:night_vision|1", "minecraft:conduit_power|2", "minecraft:dolphins_grace|3",
                        "armorplus:fire_extinguish", "armorplus:mining_fatigue_immunity"),
                List.of("minecraft:weakness|2|4"),
                List.of("armorplus:piercing"));
        superStarMaterial = MaterialConfig.create("super_star", builder,
                List.of("minecraft:regeneration|1|200", "minecraft:fire_resistance|1", "minecraft:absorption|1|200",
                        "armorplus:wither_immunity", "armorplus:fire_extinguish"),
                List.of("minecraft:wither|2|4", "minecraft:glowing|1|20"),
                List.of("armorplus:volley|1"));
        enderDragonMaterial = MaterialConfig.create("ender_dragon", builder,
                List.of("minecraft:fire_resistance|1", "minecraft:slow_falling|1", "armorplus:flight",
                        "armorplus:wither_immunity", "armorplus:fire_extinguish"),
                List.of("minecraft:wither|4|4", "minecraft:slowness|2|20"),
                List.of("armorplus:ender_shot"));
        wardenMaterial = MaterialConfig.create("warden", builder,
                List.of("minecraft:night_vision|1", "minecraft:slow_falling|1", "armorplus:sculk_immunity",
                        "armorplus:warden_pacify", "armorplus:darkness_immunity"),
                List.of("minecraft:darkness|1|20"),
                List.of("armorplus:seeker"));
        slayerMaterial = MaterialConfig.create("slayer", builder,
                List.of("minecraft:night_vision|1", "minecraft:water_breathing|1", "minecraft:jump_boost|2",
                        "minecraft:regeneration|1|200", "minecraft:fire_resistance|1", "minecraft:absorption|1|200",
                        "minecraft:slow_falling|1", "armorplus:flight", "armorplus:wither_immunity",
                        "armorplus:natural_immunity", "armorplus:fire_extinguish", "minecraft:conduit_power|2", "minecraft:dolphins_grace|3",
                        "armorplus:sculk_immunity", "armorplus:warden_pacify", "armorplus:darkness_immunity",
                        "armorplus:mining_fatigue_immunity"),
                List.of("minecraft:wither|4|4", "minecraft:slowness|2|20", "minecraft:glowing|1|20",
                        "minecraft:weakness|2|4"),
                List.of("armorplus:volley|1", "armorplus:seeker", "armorplus:ender_shot", "armorplus:piercing"));
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
        return null;
    }

    /** armorplus/drops.toml: one section per mob. */
    public static Void createDrops(ModConfigSpec.Builder builder) {
        witherBossDrops = DropConfig.create("wither_boss", builder, 4, 6, 1.0);
        enderDragonDrops = DropConfig.create("ender_dragon", builder, 4, 6, 1.0);
        elderGuardianDrops = DropConfig.create("elder_guardian", builder, 4, 6, 1.0);
        witherSkeletonDrops = DropConfig.create("wither_skeleton", builder, 0, 3, 0.25);
        guardianDrops = DropConfig.create("guardian", builder, 0, 3, 0.25);
        endermanDrops = DropConfig.create("enderman", builder, 0, -1, 0.25);
        blazeDrops = DropConfig.create("blaze", builder, 0, -1, 0.25);
        wardenDrops = DropConfig.create("warden", builder, 1, 1, -1);
        return null;
    }

    /** armorplus/runes.toml: extracting buffs from ArmorPlus equipment into runes and socketing them (Rune Altar and its ritual). */
    public static Void createRunes(ModConfigSpec.Builder builder) {
        enableRunes = builder.comment("Enable/disable the rune system. When disabled, socketed runes stop working but are kept on the items")
                .define("enableRunes", true);
        allowVanillaGear = builder.comment("Allow socketing runes into vanilla armor, weapons and tools (minecraft:*). Runes already socketed stop working when disabled, but are kept and can still be removed")
                .define("allowVanillaGear", true);
        allowModdedGear = builder.comment("Allow socketing runes into armor, weapons and tools from other mods (anything but minecraft:* and armorplus:*). Runes already socketed stop working when disabled, but are kept and can still be removed")
                .define("allowModdedGear", true);
        excludedRuneMods = builder.comment("Mod ids whose equipment can't hold runes while \"allowModdedGear\" is enabled, e.g. \"examplemod\". Empty means all mods. Vanilla and ArmorPlus equipment aren't affected")
                .defineListAllowEmpty("excludedRuneMods", List.of(),
                        () -> "examplemod", value -> value instanceof String id && ResourceLocation.isValidNamespace(id));
        returnRunesOnRemoval = builder.comment("Return the runes when removing them in a Grindstone (otherwise they are destroyed)")
                .define("returnRunesOnRemoval", false);
        runeOverrides = builder.comment("Rules for runes of specific buffs, one entry per buff, formatted as \"id|key=value|key=value...\" with the keys:",
                        "  socket=levels       experience levels to socket the rune instead of \"socketXpCost\" (1 - 39)",
                        "  extract=levels      fixed experience levels to extract it instead of the base and per level cost",
                        "  catalyst=item*count item the Rune Altar consumes in addition to extract it (\"*count\" is optional)",
                        "  extractable=false   the buff can't be extracted",
                        "  disabled=true       runes of the buff are inactive (existing runes are kept but do nothing)",
                        "  max=level           highest level the rune can be upgraded to instead of \"maxRuneLevel\" (1 - 256, 1 means no upgrading)",
                        "  upgrade_catalyst=item*count item the ritual consumes from the pedestals to upgrade with it instead of \"ritualCatalysts\" (\"none\" for no item, count 1 - 4)",
                        "  upgrade_catalyst@level=item*count the same for upgrades that reach that level, which also counts for higher levels until the next such entry, e.g. \"upgrade_catalyst@5=armorplus:astral_sigil\"",
                        "  socket_catalyst=item*count item the ritual consumes from the pedestals to socket it instead of \"ritualCatalysts\" (\"none\" for no item, count 1 - 4)",
                        "e.g. \"armorplus:flight|socket=25|extract=30|catalyst=minecraft:nether_star*1\"")
                .defineListAllowEmpty("runeOverrides", List.of(
                                "armorplus:flight|socket=25|extract=30|catalyst=minecraft:nether_star|socket_catalyst=armorplus:astral_sigil",
                                "armorplus:warden_pacify|socket=15|extract=20|catalyst=minecraft:nether_star|socket_catalyst=armorplus:equinox_sigil",
                                "armorplus:natural_immunity|socket=10|extract=15|socket_catalyst=armorplus:rime_sigil",
                                "armorplus:wither_immunity|catalyst=minecraft:nether_star|socket_catalyst=armorplus:equinox_sigil",
                                "armorplus:ender_shot|catalyst=minecraft:nether_star|socket_catalyst=armorplus:equinox_sigil",
                                "armorplus:seeker|socket_catalyst=armorplus:equinox_sigil",
                                "armorplus:volley|max=3",
                                "armorplus:quick_draw|max=4",
                                "armorplus:ammo_saver|max=4",
                                "minecraft:conduit_power|catalyst=minecraft:nether_star|socket_catalyst=armorplus:equinox_sigil"),
                        () -> "modid:effect|socket=10", RuneOverrides::isValid);

        ritualCatalysts = builder.comment("What the ritual of a Rune Altar uses up from its pedestals, formatted as \"step|item\" or \"step|item|count\" (count 1 - 4, each pedestal holds one item)",
                        "The step is \"socket\" for socketing or the level an upgrade reaches, which also counts for all higher levels until the next entry. Without an entry the step needs no catalyst",
                        "The step can also be a rune type, for what every upgrade of that type uses on top, e.g. \"bow\". The types are modded (buffs of other mods), instant and effect (mob effects), and armor, weapon, tool and bow (abilities of that gear)")
                .defineListAllowEmpty("ritualCatalysts", List.of(
                                "socket|armorplus:lava_crystal",
                                "2|armorplus:ember_sigil",
                                "3|armorplus:rime_sigil",
                                "4|armorplus:equinox_sigil",
                                "5|armorplus:astral_sigil",
                                "armor|armorplus:armor_sigil",
                                "weapon|armorplus:weapon_sigil",
                                "tool|armorplus:tool_sigil",
                                "bow|armorplus:bow_sigil",
                                "effect|armorplus:effect_sigil",
                                "instant|armorplus:instant_sigil",
                                "modded|armorplus:modded_sigil"),

                        () -> "2|armorplus:ember_sigil", RitualCatalysts::isValid);

        builder.comment("Socketing runes in the ritual of a Rune Altar, removing them in a Grindstone").push("sockets");
        randomSockets = builder.comment("Roll a random number of sockets for each piece of equipment when it's crafted (or first picked up), using the weights of each kind. When disabled every piece has the maximum number of sockets")
                .define("randomSockets", true);
        socketXpCost = builder.comment("Experience levels it costs to socket a rune (see \"runeOverrides\" for specific buffs)")
                .defineInRange("socketXpCost", 3, 1, 39);
        enableSocketPunch = builder.comment("Enable the Socket Punch, which adds one socket (up to the maximum) to equipment in an Anvil. Needs \"randomSockets\" (without it every piece already has the maximum)")
                .define("enableSocketPunch", true);
        socketPunchXpCost = builder.comment("Experience levels it costs to add a socket with a Socket Punch")
                .defineInRange("socketPunchXpCost", 10, 1, 39);
        runeRemovalXpCost = builder.comment("Experience levels it costs to take a single socketed rune out in a Rune Altar, which returns the rune")
                .defineInRange("runeRemovalXpCost", 5, 0, 1000);
        builder.comment("Armor sockets").push("armorSockets");
        maxArmorSockets = builder.comment("Maximum number of rune sockets on an armor piece (0 disables armor runes)")
                .defineInRange("maxSockets", 2, 0, 8);
        armorSocketWeights = builder.comment("Weight of each socket count, starting at 0 sockets (e.g. [50, 40, 10] = 50% none, 40% one, 10% two). Counts above maxSockets are capped")
                .defineListAllowEmpty("weights", List.of(50, 40, 10), () -> 0, ArmorPlusConfig::isWeight);
        builder.pop();
        builder.comment("Weapon sockets").push("weaponSockets");
        maxWeaponSockets = builder.comment("Maximum number of rune sockets on a weapon (0 disables weapon runes)")
                .defineInRange("maxSockets", 2, 0, 8);
        weaponSocketWeights = builder.comment("Weight of each socket count, starting at 0 sockets. Counts above maxSockets are capped")
                .defineListAllowEmpty("weights", List.of(50, 40, 10), () -> 0, ArmorPlusConfig::isWeight);
        builder.pop();
        builder.comment("Tool sockets: pickaxes, shovels, hoes, ... (#minecraft:enchantable/mining, except axes, which count as weapons). Tool runes work while the tool is held").push("toolSockets");
        maxToolSockets = builder.comment("Maximum number of rune sockets on a tool (0 disables tool runes)")
                .defineInRange("maxSockets", 2, 0, 8);
        toolSocketWeights = builder.comment("Weight of each socket count, starting at 0 sockets. Counts above maxSockets are capped")
                .defineListAllowEmpty("weights", List.of(50, 40, 10), () -> 0, ArmorPlusConfig::isWeight);
        builder.pop();
        builder.comment("Ranged sockets: bows and crossbows. Ranged runes work on the arrows they fire").push("rangedSockets");
        maxRangedSockets = builder.comment("Maximum number of rune sockets on a bow or crossbow (0 disables ranged runes)")
                .defineInRange("maxSockets", 2, 0, 8);
        rangedSocketWeights = builder.comment("Weight of each socket count, starting at 0 sockets. Counts above maxSockets are capped")
                .defineListAllowEmpty("weights", List.of(50, 40, 10), () -> 0, ArmorPlusConfig::isWeight);
        enderShotCooldown = builder.comment("Seconds before a player can fire another Ender Shot")
                .defineInRange("enderShotCooldown", 30, 0, 3600);
        enderShotDurability = builder.comment("Extra durability a bow or crossbow loses per Ender Shot")
                .defineInRange("enderShotDurability", 10, 0, 1000);
        builder.pop();
        builder.pop();

        builder.comment("Extracting runes in the Rune Altar").push("extraction");
        extractionBaseCost = builder.comment("Base experience level cost to extract a rune (see \"runeOverrides\" for specific buffs)")
                .defineInRange("extractionBaseCost", 5, 0, 1000);
        extractionCostPerLevel = builder.comment("Additional experience levels per level of the extracted buff (abilities count as level 1)")
                .defineInRange("extractionCostPerLevel", 3, 0, 1000);
        extractionLevelReduction = builder.comment("Levels lost when extracting a buff (e.g. Haste II becomes a Haste I rune). The level never goes below I")
                .defineInRange("extractionLevelReduction", 1, 0, 255);
        destroySourceOnExtract = builder.comment("Destroy the equipment the rune is extracted from. When disabled it loses the extracted effect and some durability instead (see extractionDurabilityCost)")
                .define("destroySourceOnExtract", false);
        extractionDurabilityCost = builder.comment("Fraction (0.0 - 1.0) of the max durability lost when the equipment is not destroyed")
                .defineInRange("extractionDurabilityCost", 0.25, 0.0, 1.0);
        extractionDurabilityCostFewEffects = builder.comment("Like extractionDurabilityCost, for equipment with at most extractionFewEffects extractable effects")
                .defineInRange("extractionDurabilityCostFewEffects", 0.5, 0.0, 1.0);
        extractionFewEffects = builder.comment("Equipment with at most this many extractable effects uses extractionDurabilityCostFewEffects")
                .defineInRange("extractionFewEffects", 2, 0, 64);
        extractionUpgradeCostPerLevel = builder.comment("Additional experience levels to extract an effect, for every level it was upgraded on the equipment")
                .defineInRange("extractionUpgradeCostPerLevel", 5, 0, 1000);
        builder.pop();

        builder.comment("Upgrading runes by combining two identical ones in the ritual of a Rune Altar").push("upgrading");
        maxRuneLevel = builder.comment("Highest level a rune or an equipment's own effect can be upgraded to (1 disables upgrading), see \"runeOverrides\" max=... for specific buffs. Runes above it keep their level")
                .defineInRange("maxRuneLevel", 5, 1, 256);
        upgradeBaseCost = builder.comment("Base experience level cost to upgrade a rune. The total is capped at 39")
                .defineInRange("upgradeBaseCost", 5, 1, 39);
        upgradeCostPerLevel = builder.comment("Additional experience levels per level of the rune being upgraded")
                .defineInRange("upgradeCostPerLevel", 3, 0, 39);
        builder.pop();

        builder.comment("Runes found in the world").push("world");
        worldRunes = builder.comment("Extra runes that can be found in loot, formatted as \"armor|modid:effect|level\", \"weapon|modid:effect|level|seconds\", \"tool|modid:effect|level\" (while held) or \"ranged|modid:effect|level|seconds\". These are added to the datapack runes (data/<namespace>/armorplus/rune/*.json), which include the built-in ones")
                .defineListAllowEmpty("worldRunes", List.of(),
                        () -> "armor|modid:effect|level", ArmorPlusConfig::isWorldRune);
        runeLootSources = builder.comment("Loot tables that can contain a world rune, formatted as \"loot_table|chance\" (chance 0.0 - 1.0)")
                .defineListAllowEmpty("runeLootSources", List.of(
                                "minecraft:chests/ancient_city|0.15",
                                "minecraft:chests/end_city_treasure|0.10",
                                "minecraft:chests/bastion_treasure|0.10",
                                "minecraft:chests/woodland_mansion|0.10",
                                "minecraft:chests/stronghold_library|0.08",
                                "minecraft:chests/buried_treasure|0.05",
                                "minecraft:chests/desert_pyramid|0.10",
                                "minecraft:chests/jungle_temple|0.10",
                                "minecraft:chests/nether_bridge|0.10",
                                "minecraft:chests/simple_dungeon|0.08",
                                "minecraft:chests/abandoned_mineshaft|0.08",
                                "minecraft:chests/pillager_outpost|0.08",
                                "minecraft:chests/trial_chambers/reward|0.08",
                                "minecraft:chests/stronghold_corridor|0.06",
                                "minecraft:chests/stronghold_crossing|0.06",
                                "minecraft:chests/shipwreck_treasure|0.06",
                                "minecraft:chests/underwater_ruin_big|0.06",
                                "minecraft:chests/igloo_chest|0.06",
                                "minecraft:chests/ruined_portal|0.05",
                                "minecraft:chests/bastion_other|0.05",
                                "minecraft:chests/village/village_armorer|0.04",
                                "minecraft:chests/village/village_weaponsmith|0.04",
                                "minecraft:chests/village/village_toolsmith|0.04"),
                        () -> "modid:loot_table|chance", ArmorPlusConfig::isLootSource);
        builder.pop();
        return null;
    }

    /**
     * Per-material configuration.
     * <ul>
     *     <li>{@code armorBuffs}: set effects, as "id|level" entries with an optional requirement (e.g. "minecraft:haste|1",
     *     "minecraft:night_vision|1@head", "armorplus:flight"), see {@link SetRequirement}</li>
     *     <li>{@code weaponBuffs}: on-hit effects, as "id|level|seconds" entries; {@code null} for materials without weapons</li>
     *     <li>{@code bowBuffs}: what only the material's bows add to their {@code weaponBuffs}, e.g. "armorplus:seeker"; {@code null} for materials without weapons</li>
     * </ul>
     * Entries starting with "!" are switched off, see {@link BuffParser#isDisabled(Object)}.
     */
    public record MaterialConfig(String name, ConfigValue<Boolean> enableArmorEffects,
                                 ConfigValue<Boolean> enableWeaponEffects,
                                 ConfigValue<List<? extends String>> armorBuffs,
                                 ConfigValue<List<? extends String>> weaponBuffs,
                                 ConfigValue<List<? extends String>> bowBuffs) implements IMaterialConfig {

        public static MaterialConfig create(String name, ModConfigSpec.Builder builder, List<String> armorBuffs, List<String> weaponBuffs) {
            return create(name, builder, armorBuffs, weaponBuffs, List.of());
        }

        public static MaterialConfig create(String name, ModConfigSpec.Builder builder, List<String> armorBuffs, List<String> weaponBuffs, List<String> bowBuffs) {
            builder.comment(name + " equipment").push(name);
            ConfigValue<Boolean> enableArmorEffects = (builder.comment("Enable/disable the set effects of the armor")
                    .define("enableArmorEffects", true));
            ConfigValue<Boolean> enableWeaponEffects = (builder.comment("Enable/disable the on hit effects of the weapons, tools and bows")
                    .define("enableWeaponEffects", true));
            ConfigValue<List<? extends String>> armorBuffsValue = (builder.comment("Armor set effects, e.g. \"minecraft:haste|1\", \"minecraft:night_vision|1@head\" or \"!minecraft:haste|1\" (off), see the top of this file")
                    .defineListAllowEmpty("armorBuffs", armorBuffs, () -> BuffParser.FORMAT, BuffParser::isValid));
            ConfigValue<List<? extends String>> weaponBuffsValue = weaponBuffs == null ? null : (builder.comment("Effects applied to hit targets, e.g. \"minecraft:wither|2|4\" (level 2 for 4 seconds), see the top of this file")
                    .defineListAllowEmpty("weaponBuffs", weaponBuffs, () -> BuffParser.FORMAT, BuffParser::isValid));
            ConfigValue<List<? extends String>> bowBuffsValue = weaponBuffs == null ? null : (builder.comment("What the bows add to the weapon effects: ranged abilities and on-hit effects, e.g. \"armorplus:piercing|2\" or \"armorplus:seeker\"")
                    .defineListAllowEmpty("bowBuffs", bowBuffs, () -> BuffParser.FORMAT, BuffParser::isValid));
            builder.pop();

            return new MaterialConfig(name, enableArmorEffects, enableWeaponEffects, armorBuffsValue, weaponBuffsValue, bowBuffsValue);
        }

        public List<BuffInstance> armorBuffInstances() {
            return BuffParser.armor(armorBuffs.get());
        }

        public List<BuffInstance> weaponBuffInstances() {
            return weaponBuffs == null ? List.of() : BuffParser.weapon(weaponBuffs.get());
        }
    }

    public interface IMaterialConfig {
        ConfigValue<Boolean> enableArmorEffects();

        ConfigValue<Boolean> enableWeaponEffects();
    }

    /**
     * Regular (material) and soul drops of a mob or boss. A chance of 0 disables the drop.
     * <ul>
     *     <li>{@code regularDropChance}: chance (0-1) that the material drops at all; the amount is then random between {@code regularDropMin} and {@code regularDropMax}</li>
     *     <li>{@code soulDropChance}: chance (0-1) that a Soul Stealer kill drops the mob's soul</li>
     * </ul>
     * The options only exist for the drops the mob actually has (the others are null).
     */
    public record DropConfig(ConfigValue<Double> regularDropChance, ConfigValue<Integer> regularDropMin,
                             ConfigValue<Integer> regularDropMax, ConfigValue<Double> soulDropChance) {

        /**
         * @param regularMax default maximum material amount, or -1 if the mob has no regular drop
         * @param soulChance default soul chance, or -1 if the mob has no soul drop
         */
        public static DropConfig create(String name, ModConfigSpec.Builder builder, int regularMin, int regularMax, double soulChance) {
            builder.comment(name + " drops").push(name);
            ConfigValue<Double> regularChance = null;
            ConfigValue<Integer> min = null, max = null;
            if (regularMax >= 0) {
                regularChance = builder.comment("Chance (0.0 - 1.0) that the material drops on death, 0 disables it")
                        .defineInRange("regularDropChance", 1.0, 0.0, 1.0);
                min = builder.comment("Minimum material amount")
                        .defineInRange("regularDropMin", regularMin, 0, 64);
                max = builder.comment("Maximum material amount")
                        .defineInRange("regularDropMax", regularMax, 0, 64);
            }
            ConfigValue<Double> soul = null;
            if (soulChance >= 0) {
                soul = builder.comment("Chance (0.0 - 1.0) that a kill with a Soul Stealer weapon drops the soul, 0 disables it")
                        .defineInRange("soulDropChance", soulChance, 0.0, 1.0);
            }
            builder.pop();

            return new DropConfig(regularChance, min, max, soul);
        }
    }
}
