package com.sofodev.armorplus.datagen;

import com.sofodev.armorplus.datagen.recipe.Grid;
import com.sofodev.armorplus.datagen.recipe.GridInput;
import com.sofodev.armorplus.datagen.recipe.Result;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.item.tool.properties.mace.APMaceMaterial;
import com.sofodev.armorplus.registry.item.tool.properties.tool.APToolMaterial;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RepairItemRecipe;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.sofodev.armorplus.ArmorPlus.AP_STONE_BRICKS_LENGTH;
import static com.sofodev.armorplus.ArmorPlus.AP_TOOL_MATERIAL_LENGTH;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModBlocks.STONE_BRICKS;
import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.registry.ModItems.INFUSED_FROST_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.INFUSED_LAVA_CRYSTAL;
import static com.sofodev.armorplus.utils.DataUtils.getPath;
import static java.util.Arrays.asList;
import static net.minecraft.world.item.Items.*;
import static net.neoforged.neoforge.common.Tags.Items.*;

public class Recipes extends RecipeProvider implements DataProvider, IConditionBuilder {


    public static final List<TagKey<Item>> BRICK_COLORS = List.of(DYES_BLACK, DYES_BLUE, DYES_BROWN, DYES_CYAN, DYES_GRAY, DYES_GREEN, DYES_LIGHT_BLUE, DYES_LIGHT_GRAY, DYES_LIME, DYES_MAGENTA, DYES_ORANGE, DYES_PINK, DYES_PURPLE, DYES_RED, DYES_WHITE, DYES_YELLOW);
    public static final List<ItemLike> MATERIALS_ORDERED = Stream.of(COAL, REDSTONE, LAPIS_LAZULI, EMERALD, Items.OBSIDIAN, INFUSED_LAVA_CRYSTAL.get(), GUARDIAN_SCALE.get(), WITHER_BONE.get(), ENDER_DRAGON_SCALE.get()).collect(Collectors.toList());
    public static final List<ItemLike> BLOCK_MATERIALS_ORDERED = Stream.of(COAL_BLOCK, REDSTONE_BLOCK, LAPIS_BLOCK, EMERALD_BLOCK, COMPRESSED_OBSIDIAN.get(), INFUSED_LAVA_CRYSTAL.get(), GUARDIAN_SCALE.get(), WITHER_BONE.get(), ENDER_DRAGON_SCALE.get()).collect(Collectors.toList());
    public static final List<ItemLike> LOW_TO_MID_TIER_MATERIAL_LIST = Stream.of(COAL_BLOCK, REDSTONE_BLOCK, LAPIS_BLOCK, EMERALD_BLOCK, COMPRESSED_OBSIDIAN.get()).collect(Collectors.toList());
    private DataGenerator generator;
    private final CompletableFuture<HolderLookup.Provider> provider;

    public Recipes(DataGenerator generatorIn, CompletableFuture<HolderLookup.Provider> provider) {
        super(generatorIn.getPackOutput(), provider);
        generator = generatorIn;
        this.provider = provider;
    }

    @Override
    protected void buildRecipes(RecipeOutput con) {
        SmithingRecipeMaker smither = new SmithingRecipeMaker(generator, provider);
        smither.buildRecipes(con);
        this.registerCraftingRecipes(con);
    }

    private void registerCraftingRecipes(RecipeOutput con) {
        CraftingRecipeMaker crafter = new CraftingRecipeMaker(generator, provider);
        //StoneBricks+CastleBlocks
        int l = AP_STONE_BRICKS_LENGTH;

        if (l != BRICK_COLORS.size()) {
            throw new IllegalStateException("Stone brick color count does not match registered block count: " + l + " blocks, " + BRICK_COLORS.size() + " colors");
        }

        for (int i = 0; i < l; i++) {
            crafter.buildStoneBrick(con, CASTLE_BLOCKS[i], CASTLE_BLOCK_TOWERS[i], CASTLE_BLOCK_CORNERS[i], CASTLE_BLOCK_WALLS[i], CASTLE_BLOCK_STAIRS[i], CASTLE_BLOCK_SLABS[i]);

            crafter.buildStoneBrick(con, STONE_BRICKS[i], STONE_BRICK_TOWERS[i], STONE_BRICK_CORNERS[i], STONE_BRICK_WALLS[i], ModBlocks.STONE_BRICK_STAIRS[i], STONE_BRICK_SLABS[i]);

            crafter.buildColoredBrick(con, STONE_BRICKS[i], BRICK_COLORS.get(i), Items.STONE_BRICKS, "colored_stone_bricks");

            crafter.buildColoredBrick(con, CASTLE_BLOCKS[i], BRICK_COLORS.get(i), Items.CHISELED_STONE_BRICKS, "colored_castle_blocks");
        }

        //Weapons are always looked up by material (never by a hard-coded index)
        List<APToolMaterial> lowToMidTools = List.of(APToolMaterial.COAL_MAT, APToolMaterial.REDSTONE_MAT, APToolMaterial.LAPIS_MAT, APToolMaterial.EMERALD_MAT, APToolMaterial.OBSIDIAN_MAT);
        for (int i = 0; i < lowToMidTools.size(); i++) {
            buildToolSet(crafter, con, lowToMidTools.get(i), LOW_TO_MID_TIER_MATERIAL_LIST.get(i), STICK);
        }
        buildToolSet(crafter, con, APToolMaterial.INFUSED_LAVA_MAT, INFUSED_LAVA_CRYSTAL.get(), OBSIDIAN_STICK.get());

        crafter.buildSword(con, GUARDIAN_SWORD_BASE, GUARDIAN_SCALE.get(), PRISMARINE, OBSIDIAN_STICK.get());
        crafter.buildSword(con, SUPER_STAR_SWORD_BASE, WITHER_BONE.get(), NETHER_STAR, OBSIDIAN_STICK.get());
        crafter.buildSword(con, ENDER_DRAGON_SWORD_BASE, ENDER_DRAGON_SCALE.get(), DRAGON_BREATH, OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, GUARDIAN_BATTLE_AXE_BASE, GUARDIAN_SCALE.get(), PRISMARINE, OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, SUPER_STAR_BATTLE_AXE_BASE, WITHER_BONE.get(), OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, ENDER_DRAGON_BATTLE_AXE_BASE, ENDER_DRAGON_SCALE.get(), DRAGON_BREATH, OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, GUARDIAN_PICKAXE_BASE, GUARDIAN_SCALE.get(), PRISMARINE, OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, SUPER_STAR_PICKAXE_BASE, WITHER_BONE.get(), NETHER_STAR, OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, ENDER_DRAGON_PICKAXE_BASE, ENDER_DRAGON_SCALE.get(), DRAGON_BREATH, OBSIDIAN_STICK.get());
        crafter.buildShovel(con, GUARDIAN_SHOVEL_BASE, GUARDIAN_SCALE.get(), PRISMARINE, OBSIDIAN_STICK.get());
        crafter.buildShovel(con, SUPER_STAR_SHOVEL_BASE, WITHER_BONE.get(), NETHER_STAR, OBSIDIAN_STICK.get());
        crafter.buildShovel(con, ENDER_DRAGON_SHOVEL_BASE, ENDER_DRAGON_SCALE.get(), DRAGON_BREATH, OBSIDIAN_STICK.get());
        crafter.buildBow(con, GUARDIAN_BOW_BASE, PRISMARINE, GUARDIAN_SCALE.get());
        crafter.buildBow(con, SUPER_STAR_BOW_BASE, NETHER_STAR, WITHER_BONE.get());
        crafter.buildBow(con, ENDER_DRAGON_BOW_BASE, DRAGON_BREATH, ENDER_DRAGON_SCALE.get());

        //Warden bases contain no hearts: the smithing upgrade adds exactly 1 Warden Heart per weapon
        crafter.buildSword(con, WARDEN_SWORD_BASE, ECHO_SHARD, SCULK_CATALYST, OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, WARDEN_BATTLE_AXE_BASE, ECHO_SHARD, SCULK_CATALYST, OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, WARDEN_PICKAXE_BASE, ECHO_SHARD, SCULK_CATALYST, OBSIDIAN_STICK.get());
        crafter.buildShovel(con, WARDEN_SHOVEL_BASE, ECHO_SHARD, SCULK_CATALYST, OBSIDIAN_STICK.get());
        crafter.buildBow(con, WARDEN_BOW_BASE, SCULK_CATALYST, ECHO_SHARD);

        //Amethyst/Quartz/Glowstone/Honey (low tier, base block + stick)
        buildToolSet(crafter, con, APToolMaterial.AMETHYST_MAT, AMETHYST_BLOCK, STICK);
        buildToolSet(crafter, con, APToolMaterial.QUARTZ_MAT, QUARTZ_BLOCK, STICK);
        buildToolSet(crafter, con, APToolMaterial.GLOWSTONE_MAT, GLOWSTONE, STICK);
        buildToolSet(crafter, con, APToolMaterial.HONEY_MAT, HONEYCOMB_BLOCK, STICK);

        //Weapons for the remaining armor materials (parity with the armor sets). Top tier ones use the obsidian stick.
        Map<APToolMaterial, ItemLike> parityMaterials = new LinkedHashMap<>();
        parityMaterials.put(APToolMaterial.CHICKEN_MAT, FEATHER);
        parityMaterials.put(APToolMaterial.SLIME_MAT, SLIME_BLOCK);
        parityMaterials.put(APToolMaterial.ARDITE_MAT, ARDITE_INGOT.get());
        parityMaterials.put(APToolMaterial.COBALT_MAT, COBALT_INGOT.get());
        parityMaterials.put(APToolMaterial.KNIGHT_SLIME_MAT, KNIGHT_SLIME_CRYSTAL.get());
        parityMaterials.put(APToolMaterial.PIG_IRON_MAT, PIG_IRON_INGOT.get());
        parityMaterials.put(APToolMaterial.MANYULLYN_MAT, MANYULLYN_INGOT.get());
        parityMaterials.put(APToolMaterial.FROST_MAT, INFUSED_FROST_CRYSTAL.get());
        parityMaterials.put(APToolMaterial.FROST_LAVA_MAT, INFUSED_FROST_LAVA_CRYSTAL.get());
        parityMaterials.put(APToolMaterial.CHAINMAIL_MAT, ENHANCED_CHAINMAIL.get());
        parityMaterials.put(APToolMaterial.GOLDEN_MAT, ENHANCED_GOLD.get());
        parityMaterials.put(APToolMaterial.COPPER_MAT, ENHANCED_COPPER.get());
        parityMaterials.put(APToolMaterial.IRON_MAT, ENHANCED_IRON.get());
        parityMaterials.put(APToolMaterial.DIAMOND_MAT, ENHANCED_DIAMOND.get());
        parityMaterials.put(APToolMaterial.NETHERITE_MAT, ENHANCED_NETHERITE.get());
        List<APToolMaterial> obsidianStickParity = List.of(APToolMaterial.MANYULLYN_MAT, APToolMaterial.FROST_MAT, APToolMaterial.FROST_LAVA_MAT, APToolMaterial.NETHERITE_MAT);
        parityMaterials.forEach((mat, material) -> {
            ItemLike stick = obsidianStickParity.contains(mat) ? OBSIDIAN_STICK.get() : STICK;
            buildToolSet(crafter, con, mat, material, stick);
            buildMace(crafter, con, mat, stick, material);
        });
        //Arrows
        crafter.buildOrderedArrow(con, asList(ITEM_COAL_ARROW, ITEM_REDSTONE_ARROW, ITEM_LAPIS_ARROW, ITEM_EMERALD_ARROW, ITEM_OBSIDIAN_ARROW, ITEM_INFUSED_LAVA_ARROW, ITEM_GUARDIAN_ARROW, ITEM_SUPER_STAR_ARROW, ITEM_ENDER_DRAGON_ARROW));
        crafter.buildArrow(con, ITEM_AMETHYST_ARROW, AMETHYST_BLOCK);
        crafter.buildArrow(con, ITEM_QUARTZ_ARROW, QUARTZ_BLOCK);
        crafter.buildArrow(con, ITEM_GLOWSTONE_ARROW, GLOWSTONE);
        crafter.buildArrow(con, ITEM_HONEY_ARROW, HONEYCOMB_BLOCK);
        crafter.buildArrow(con, ITEM_WARDEN_ARROW, WARDEN_HEART.get());
        //Lesser Souls to Boss Souls recipes
        crafter.buildSoul(con, WITHER_BOSS_SOUL, WITHER_SKELETON_SOUL.get(), WITHER_BONE.get(), SOUL_SAND);
        crafter.buildSoul(con, ELDER_GUARDIAN_SOUL, GUARDIAN_SOUL.get(), GUARDIAN_SCALE.get(), PRISMARINE);
        crafter.buildSoul(con, ENDER_DRAGON_SOUL, ENDERMAN_SOUL.get(), ENDER_DRAGON_SCALE.get(), END_STONE);

        //Vanilla Material + Glowstone = Enchaned: Material
        crafter.buildEnhanced(con, ENHANCED_CHAINMAIL, CHAINMAIL.get());
        crafter.buildEnhanced(con, ENHANCED_COPPER, COPPER_INGOT);
        crafter.buildEnhanced(con, ENHANCED_IRON, IRON_INGOT);
        crafter.buildEnhanced(con, ENHANCED_GOLD, GOLD_INGOT);
        crafter.buildEnhanced(con, ENHANCED_DIAMOND, DIAMOND);
        crafter.buildEnhanced(con, ENHANCED_NETHERITE, NETHERITE_INGOT);
        //Enhanced: Armors
        String enGroup = "ap_en_";
        String enPath = "enhanced";
        crafter.buildArmorSet(con, "chainmail", ENHANCED_CHAINMAIL.get(), enGroup + "chainmail", enPath, "", "");
        crafter.buildArmorSet(con, "iron", ENHANCED_IRON.get(), enGroup + "iron", enPath, "", "");
        crafter.buildArmorSet(con, "golden", ENHANCED_GOLD.get(), enGroup + "gold", enPath, "", "");
        crafter.buildArmorSet(con, "diamond", ENHANCED_DIAMOND.get(), enGroup + "diamond", enPath, "", "");
        //Bases
        crafter.buildArmorSet(con, "chicken", FEATHER, "");
        crafter.buildArmorSet(con, "slime", SLIME_BLOCK, "");

        crafter.buildArmorSet(con, "coal", COAL_BLOCK, "");
        crafter.buildArmorSet(con, "lapis", LAPIS_BLOCK, "");
        crafter.buildArmorSet(con, "redstone", REDSTONE_BLOCK, "");
        crafter.buildArmorSet(con, "emerald", EMERALD_BLOCK, "");
        crafter.buildArmorSet(con, "obsidian", COMPRESSED_OBSIDIAN.get(), "");
        crafter.buildArmorSet(con, "infused_lava", INFUSED_LAVA_CRYSTAL.get(), "");
        crafter.buildArmorSet(con, "guardian", GUARDIAN_SCALE.get(), PRISMARINE, "_base");
        crafter.buildArmorSet(con, "super_star", WITHER_BONE.get(), NETHER_STAR, "_base");
        crafter.buildArmorSet(con, "ender_dragon", ENDER_DRAGON_SCALE.get(), DRAGON_BREATH, "_base");
        crafter.buildArmorSet(con, "frost", INFUSED_FROST_CRYSTAL.get(), "");
        crafter.buildArmorSet(con, "frost_lava", INFUSED_FROST_LAVA_CRYSTAL.get(), "");
        //Warden armor bases contain no hearts: the smithing upgrade adds 1 Warden Heart per piece
        crafter.buildArmorSet(con, "warden", ECHO_SHARD, SCULK_CATALYST, "_base");
        crafter.buildArmorSet(con, "amethyst", AMETHYST_BLOCK, "");
        crafter.buildArmorSet(con, "quartz", QUARTZ_BLOCK, "");
        crafter.buildArmorSet(con, "glowstone", GLOWSTONE, "");
        crafter.buildArmorSet(con, "honey", HONEYCOMB_BLOCK, "");
        //Tinkers' Construct-inspired alloy ingots
        crafter.build(con, RecipeCategory.MISC, Result.build(PIG_IRON_INGOT.get(), 1, "ap_tc_mats", "alloy"), GridInput.build("III", "IFI", "III", 'I', 'F'), IRON_INGOT, Ingredient.of(ROTTEN_FLESH));
        crafter.build(con, RecipeCategory.MISC, Result.build(COBALT_INGOT.get(), 1, "ap_tc_mats", "alloy"), GridInput.build("III", "ILI", "III", 'I', 'L'), IRON_INGOT, Ingredient.of(LAPIS_BLOCK));
        crafter.build(con, RecipeCategory.MISC, Result.build(ARDITE_INGOT.get(), 1, "ap_tc_mats", "alloy"), GridInput.build("III", "IMI", "III", 'I', 'M'), IRON_INGOT, Ingredient.of(MAGMA_BLOCK));
        crafter.build(con, RecipeCategory.MISC, Result.build(KNIGHT_SLIME_CRYSTAL.get(), 1, "ap_tc_mats", "alloy"), GridInput.build("SSS", "SPS", "SSS", 'S', 'P'), SLIME_BALL, Ingredient.of(PIG_IRON_INGOT.get()));
        crafter.build(con, RecipeCategory.MISC, Result.build(MANYULLYN_INGOT.get(), 1, "ap_tc_mats", "alloy"), ARDITE_INGOT.get(), Ingredient.of(ARDITE_INGOT.get()), Ingredient.of(ARDITE_INGOT.get()), Ingredient.of(ARDITE_INGOT.get()), Ingredient.of(COBALT_INGOT.get()), Ingredient.of(COBALT_INGOT.get()), Ingredient.of(COBALT_INGOT.get()), Ingredient.of(COBALT_INGOT.get()));
        //Tinkers' Construct-inspired armor sets
        crafter.buildArmorSet(con, "ardite", ARDITE_INGOT.get(), "");
        crafter.buildArmorSet(con, "cobalt", COBALT_INGOT.get(), "");
        crafter.buildArmorSet(con, "pig_iron", PIG_IRON_INGOT.get(), "");
        crafter.buildArmorSet(con, "knight_slime", KNIGHT_SLIME_CRYSTAL.get(), "");
        crafter.buildArmorSet(con, "manyullyn", MANYULLYN_INGOT.get(), "");
        //Maces
        List<APToolMaterial> blockMaceMaterials = List.of(APToolMaterial.COAL_MAT, APToolMaterial.REDSTONE_MAT, APToolMaterial.LAPIS_MAT, APToolMaterial.EMERALD_MAT, APToolMaterial.OBSIDIAN_MAT, APToolMaterial.INFUSED_LAVA_MAT, APToolMaterial.GUARDIAN_MAT, APToolMaterial.SUPER_STAR_MAT, APToolMaterial.ENDER_DRAGON_MAT);
        IntStream.range(0, blockMaceMaterials.size()).forEach(i -> buildMace(crafter, con, blockMaceMaterials.get(i), i > 4 ? OBSIDIAN_STICK.get() : WOODEN_ROD.get(), BLOCK_MATERIALS_ORDERED.get(i)));
        Item wardenMace = maceOf(APToolMaterial.WARDEN_MAT);
        String wardenMacePath = getPath(wardenMace).replace("item_", "").replace("_mace", "");
        crafter.build(con, RecipeCategory.COMBAT, Result.build(wardenMace, "maces", wardenMacePath), GridInput.build(" EH", " SE", "S  ", 'S', 'E', 'H'), OBSIDIAN_STICK.get(), ECHO_SHARD, WARDEN_HEART.get());
        crafter.build(con, RecipeCategory.COMBAT, Result.build(wardenMace, "maces", wardenMacePath).setSuffix("_alt"), GridInput.build("HE ", "ES ", "  S", 'S', 'E', 'H'), OBSIDIAN_STICK.get(), ECHO_SHARD, WARDEN_HEART.get());
        buildMace(crafter, con, APToolMaterial.SLAYER_MAT, OBSIDIAN_STICK.get(), THE_ULTIMATE_MATERIAL.get());
        buildMace(crafter, con, APToolMaterial.AMETHYST_MAT, WOODEN_ROD.get(), AMETHYST_BLOCK);
        buildMace(crafter, con, APToolMaterial.QUARTZ_MAT, WOODEN_ROD.get(), QUARTZ_BLOCK);
        buildMace(crafter, con, APToolMaterial.GLOWSTONE_MAT, WOODEN_ROD.get(), GLOWSTONE);
        buildMace(crafter, con, APToolMaterial.HONEY_MAT, WOODEN_ROD.get(), HONEYCOMB_BLOCK);
        //Storage Blocks
        crafter.buildStorage(con, COMPRESSED_LAVA_CRYSTAL.get(), ModBlocks.LAVA_CRYSTAL.get());
        crafter.buildStorage(con, ModBlocks.LAVA_CRYSTAL.get(), ModItems.LAVA_CRYSTAL.get());
        crafter.buildStorage(con, COMPRESSED_INFUSED_LAVA_CRYSTAL.get(), ModBlocks.INFUSED_LAVA_CRYSTAL.get());
        crafter.buildStorage(con, ModBlocks.INFUSED_LAVA_CRYSTAL.get(), INFUSED_LAVA_CRYSTAL.get());
        crafter.buildStorage(con, COMPRESSED_OBSIDIAN.get(), Items.OBSIDIAN);
        crafter.buildStorage(con, ModItems.LAVA_CRYSTAL.get(), LAVA_SHARD.get());
        crafter.buildStorage(con, ModItems.FROST_CRYSTAL.get(), FROST_SHARD.get());

        //Ultimate Material
        crafter.build(con, RecipeCategory.MISC, Result.build(THE_ULTIMATE_MATERIAL.get(), 1), INFUSED_FROST_LAVA_CRYSTAL.get(), WITHER_BONE.get(), ENDER_DRAGON_SCALE.get(), GUARDIAN_SCALE.get(), ECHO_SHARD);
        //Slayer Set
        crafter.buildComplexArmorSet(con, "slayer", THE_ULTIMATE_MATERIAL.get(), "super_star", "ender_dragon", "guardian", "_base");
        crafter.buildSword(con, SLAYER_SWORD_BASE, THE_ULTIMATE_MATERIAL.get(), SUPER_STAR_SWORD_BASE.get(), ENDER_DRAGON_SWORD_BASE.get(), GUARDIAN_SWORD_BASE.get(), OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, SLAYER_BATTLE_AXE_BASE, THE_ULTIMATE_MATERIAL.get(), SUPER_STAR_BATTLE_AXE_BASE.get(), ENDER_DRAGON_BATTLE_AXE_BASE.get(), GUARDIAN_BATTLE_AXE_BASE.get(), OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, SLAYER_PICKAXE_BASE, THE_ULTIMATE_MATERIAL.get(), SUPER_STAR_PICKAXE_BASE.get(), ENDER_DRAGON_PICKAXE_BASE.get(), GUARDIAN_PICKAXE_BASE.get(), OBSIDIAN_STICK.get());
        crafter.buildShovel(con, SLAYER_SHOVEL_BASE, THE_ULTIMATE_MATERIAL.get(), SUPER_STAR_SHOVEL_BASE.get(), ENDER_DRAGON_SHOVEL_BASE.get(), GUARDIAN_SHOVEL_BASE.get(), OBSIDIAN_STICK.get());
        crafter.buildBow(con, SLAYER_BOW_BASE, THE_ULTIMATE_MATERIAL.get(), SUPER_STAR_BOW_BASE.get(), ENDER_DRAGON_BOW_BASE.get(), GUARDIAN_BOW_BASE.get());

        crafter.build(con, RecipeCategory.MISC, Result.build(SLAYER_SOUL.get(), 1).setGroup("slayer").setSuffix(""), ELDER_GUARDIAN_SOUL.get(), WITHER_BOSS_SOUL.get(), ENDER_DRAGON_SOUL.get(), WARDEN_HEART.get());
        //Other
        crafter.build(con, RecipeCategory.BUILDING_BLOCKS, Result.build(SOUL_BOX.get()).setGroup("villager").setSuffix(""), GridInput.build("ASA", "SGS", "ASA", 'A', 'S', 'G'), Items.STONE, SOUL_SAND, GLOWSTONE);
        //Runes
        crafter.build(con, RecipeCategory.MISC, Result.build(BLANK_RUNE.get(), 2).setGroup("runes").setSuffix(""), GridInput.build("SAS", "ALA", "SAS", 'S', 'A', 'L'), Items.SMOOTH_STONE, AMETHYST_SHARD, LAPIS_LAZULI);
        crafter.build(con, RecipeCategory.MISC, Result.build(SOCKET_PUNCH.get()).setGroup("runes").setSuffix(""), GridInput.build("  D", " A ", "S  ", 'D', 'A', 'S'), DIAMOND, AMETHYST_SHARD, OBSIDIAN_STICK.get());
        crafter.build(con, RecipeCategory.MISC, Result.build(RUNE_ALTAR.get()).setGroup("runes").setSuffix(""), GridInput.build("ARA", "DOD", "OOO", 'A', 'R', 'D', 'O'), AMETHYST_SHARD, BLANK_RUNE.get(), DIAMOND, Items.OBSIDIAN);
        crafter.build(con, RecipeCategory.MISC, Result.build(EMPOWERING_PEDESTAL.get()).setGroup("runes").setSuffix(""), GridInput.build("ARA", " O ", "OOO", 'A', 'R', 'O'), AMETHYST_SHARD, BLANK_RUNE.get(), Items.OBSIDIAN);
        crafter.build(con, RecipeCategory.MISC, Result.build(EMBER_SIGIL.get(), 2).setGroup("runes").setSuffix(""), GridInput.build("A A", " C ", "A A", 'A', 'C'), AMETHYST_SHARD, INFUSED_LAVA_CRYSTAL.get());
        crafter.build(con, RecipeCategory.MISC, Result.build(RIME_SIGIL.get(), 2).setGroup("runes").setSuffix(""), GridInput.build("A A", " C ", "A A", 'A', 'C'), AMETHYST_SHARD, INFUSED_FROST_CRYSTAL.get());
        crafter.build(con, RecipeCategory.MISC, Result.build(EQUINOX_SIGIL.get(), 1).setGroup("runes").setSuffix(""), EMBER_SIGIL.get(), RIME_SIGIL.get(), INFUSED_FROST_LAVA_CRYSTAL.get());
        crafter.build(con, RecipeCategory.MISC, Result.build(ASTRAL_SIGIL.get(), 1).setGroup("runes").setSuffix(""), EQUINOX_SIGIL.get(), EQUINOX_SIGIL.get(), ENDER_DRAGON_SCALE.get(), GUARDIAN_SCALE.get(), WITHER_BONE.get());
        crafter.build(con, RecipeCategory.MISC, Result.build(ARMOR_SIGIL.get(), 2).setGroup("runes").setSuffix(""), EMBER_SIGIL.get(), RIME_SIGIL.get(), Items.IRON_CHESTPLATE);
        crafter.build(con, RecipeCategory.MISC, Result.build(WEAPON_SIGIL.get(), 2).setGroup("runes").setSuffix(""), EMBER_SIGIL.get(), RIME_SIGIL.get(), Items.IRON_SWORD);
        crafter.build(con, RecipeCategory.MISC, Result.build(TOOL_SIGIL.get(), 2).setGroup("runes").setSuffix(""), EMBER_SIGIL.get(), RIME_SIGIL.get(), Items.IRON_PICKAXE);
        crafter.build(con, RecipeCategory.MISC, Result.build(BOW_SIGIL.get(), 2).setGroup("runes").setSuffix(""), EMBER_SIGIL.get(), RIME_SIGIL.get(), Items.BOW);
        crafter.build(con, RecipeCategory.MISC, Result.build(EFFECT_SIGIL.get(), 2).setGroup("runes").setSuffix(""), EMBER_SIGIL.get(), RIME_SIGIL.get(), Items.FERMENTED_SPIDER_EYE);
        crafter.build(con, RecipeCategory.MISC, Result.build(INSTANT_SIGIL.get(), 2).setGroup("runes").setSuffix(""), EMBER_SIGIL.get(), RIME_SIGIL.get(), Items.GLISTERING_MELON_SLICE);
        crafter.build(con, RecipeCategory.MISC, Result.build(MODDED_SIGIL.get(), 2).setGroup("runes").setSuffix(""), EMBER_SIGIL.get(), RIME_SIGIL.get(), Items.ENDER_PEARL);
        crafter.build(con, RecipeCategory.MISC, Result.build(OBSIDIAN_STICK.get(), 4).setGroup("materials").setSuffix(""), GridInput.build(Grid.build("X", "X", " "), 'X'), Items.OBSIDIAN);
        crafter.build(con, RecipeCategory.MISC, Result.build(WOODEN_ROD.get(), 2).setGroup("materials").setSuffix(""), STICK, STICK, STICK, STICK);
        crafter.build(con, RecipeCategory.BUILDING_BLOCKS, Result.build(SNOW_BRICK.get(), 4).setGroup("snow_brick").setSuffix(""), GridInput.buildSmallSquare('X'), SNOW_BLOCK);
        crafter.build(con, RecipeCategory.BUILDING_BLOCKS, Result.build(SNOW_BRICK_STAIRS.get(), 4).setGroup("snow_brick_stairs").setSuffix(""), GridInput.buildStairs('X'), SNOW_BRICK.get());
        crafter.build(con, RecipeCategory.BUILDING_BLOCKS, Result.build(SNOW_BRICK_SLAB.get(), 4).setGroup("snow_brick_slab").setSuffix(""), GridInput.buildSlab('X'), SNOW_BRICK.get());

        SpecialRecipeBuilder.special(RepairItemRecipe::new).save(con, "repair_item");
    }

    private static Item maceOf(APToolMaterial mat) {
        return MACES[APMaceMaterial.valueOf(mat.name().replace("_MAT", "_MACE")).ordinal()].get();
    }

    private static void buildToolSet(CraftingRecipeMaker crafter, RecipeOutput con, APToolMaterial mat, ItemLike material, ItemLike stick) {
        int i = mat.ordinal();
        crafter.buildSword(con, SWORDS[i], material, stick);
        crafter.buildBattleAxe(con, BATTLE_AXES[i], material, stick);
        crafter.buildPickaxe(con, PICKAXES[i], material, stick);
        crafter.buildShovel(con, SHOVELS[i], material, stick);
        crafter.buildBow(con, BOWS[i], material);
    }

    private static void buildMace(CraftingRecipeMaker crafter, RecipeOutput con, APToolMaterial mat, ItemLike stick, ItemLike material) {
        Item mace = maceOf(mat);
        String path = getPath(mace).replace("item_", "").replace("_mace", "");
        crafter.build(con, RecipeCategory.COMBAT, Result.build(mace, "maces", path), GridInput.build(" DD", " SD", "S  ", 'S', 'D'), stick, material);
        crafter.build(con, RecipeCategory.COMBAT, Result.build(mace, "maces", path).setSuffix("_alt"), GridInput.build("DD ", "DS ", "  S", 'S', 'D'), stick, material);
    }


}
