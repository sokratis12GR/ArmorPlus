package com.sofodev.armorplus.datagen;

import com.sofodev.armorplus.datagen.recipe.Grid;
import com.sofodev.armorplus.datagen.recipe.GridInput;
import com.sofodev.armorplus.datagen.recipe.Result;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
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

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.sofodev.armorplus.ArmorPlus.AP_STONE_BRICKS_LENGTH;
import static com.sofodev.armorplus.ArmorPlus.AP_TOOL_MATERIAL_LENGTH;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModBlocks.STONE_BRICKS;
import static com.sofodev.armorplus.registry.ModBlocks.STONE_BRICK_STAIRS;
import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.registry.ModItems.INFUSED_FROST_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.INFUSED_LAVA_CRYSTAL;
import static com.sofodev.armorplus.utils.DataUtils.getPath;
import static java.util.Arrays.asList;
import static net.minecraft.world.item.Items.*;
import static net.neoforged.neoforge.common.Tags.Items.*;

public class Recipes extends RecipeProvider {

    public static final List<TagKey<Item>> BRICK_COLORS = List.of(DYES_BLACK, DYES_BLUE, DYES_BROWN, DYES_CYAN, DYES_GRAY, DYES_GREEN, DYES_LIGHT_BLUE, DYES_LIGHT_GRAY, DYES_LIME, DYES_MAGENTA, DYES_ORANGE, DYES_PINK, DYES_PURPLE, DYES_RED, DYES_WHITE, DYES_YELLOW);
    public static final List<ItemLike> MATERIALS_ORDERED = Stream.of(COAL, REDSTONE, LAPIS_LAZULI, EMERALD, Items.OBSIDIAN, INFUSED_LAVA_CRYSTAL.get(), GUARDIAN_SCALE.get(), WITHER_BONE.get(), ENDER_DRAGON_SCALE.get()).collect(Collectors.toList());
    public static final List<ItemLike> BLOCK_MATERIALS_ORDERED = Stream.of(COAL_BLOCK, REDSTONE_BLOCK, LAPIS_BLOCK, EMERALD_BLOCK, COMPRESSED_OBSIDIAN.get(), INFUSED_LAVA_CRYSTAL.get(), GUARDIAN_SCALE.get(), WITHER_BONE.get(), ENDER_DRAGON_SCALE.get()).collect(Collectors.toList());
    public static final List<ItemLike> LOW_TO_MID_TIER_MATERIAL_LIST = Stream.of(COAL_BLOCK, REDSTONE_BLOCK, LAPIS_BLOCK, EMERALD_BLOCK, COMPRESSED_OBSIDIAN.get()).collect(Collectors.toList());

    public Recipes(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        SmithingRecipeMaker smither = new SmithingRecipeMaker(registries, output);
        smither.registerSmithingRecipes(output);
        this.registerCraftingRecipes(output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new Recipes(registries, output);
        }

        @Override
        public String getName() {
            return "ArmorPlus Recipes";
        }
    }

    private void registerCraftingRecipes(RecipeOutput con) {
        CraftingRecipeMaker crafter = new CraftingRecipeMaker(registries, output);
        // Stone Bricks + Castle Blocks

        for (int i = 0; i < AP_STONE_BRICKS_LENGTH; i++) {
            // Castle block variants
            crafter.buildStoneBrick(con, CASTLE_BLOCKS[i], CASTLE_BLOCK_TOWERS[i], CASTLE_BLOCK_CORNERS[i], CASTLE_BLOCK_WALLS[i], CASTLE_BLOCK_STAIRS[i], CASTLE_BLOCK_SLABS[i]);

            // Stone brick variants
            crafter.buildStoneBrick(con, STONE_BRICKS[i], STONE_BRICK_TOWERS[i], STONE_BRICK_CORNERS[i], STONE_BRICK_WALLS[i], STONE_BRICK_STAIRS[i], STONE_BRICK_SLABS[i]);

            // Colored stone bricks
            crafter.buildColoredBrick(con, STONE_BRICKS[i], BRICK_COLORS.get(i), Items.STONE_BRICKS, "colored_stone_bricks");

            // Colored castle blocks
            crafter.buildColoredBrick(con, CASTLE_BLOCKS[i], BRICK_COLORS.get(i), Items.CHISELED_STONE_BRICKS, "colored_castle_blocks");
        }
        //Bows
        for (int i = 0; i < LOW_TO_MID_TIER_MATERIAL_LIST.size(); i++) {
            crafter.buildSword(con, SWORDS[i], LOW_TO_MID_TIER_MATERIAL_LIST.get(i), STICK);
            crafter.buildBattleAxe(con, BATTLE_AXES[i], LOW_TO_MID_TIER_MATERIAL_LIST.get(i), STICK);
            crafter.buildPickaxe(con, PICKAXES[i], LOW_TO_MID_TIER_MATERIAL_LIST.get(i), STICK);
            crafter.buildBow(con, BOWS[i], LOW_TO_MID_TIER_MATERIAL_LIST.get(i));
        }
        int lava = LOW_TO_MID_TIER_MATERIAL_LIST.size();
        crafter.buildSword(con, SWORDS[lava], INFUSED_LAVA_CRYSTAL.get(), OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, BATTLE_AXES[lava], INFUSED_LAVA_CRYSTAL.get(), OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, PICKAXES[lava], INFUSED_LAVA_CRYSTAL.get(), OBSIDIAN_STICK.get());
        crafter.buildBow(con, BOWS[lava], INFUSED_LAVA_CRYSTAL.get());

        crafter.buildSword(con, GUARDIAN_SWORD_BASE, GUARDIAN_SCALE.get(), PRISMARINE, OBSIDIAN_STICK.get());
        crafter.buildSword(con, SUPER_STAR_SWORD_BASE, WITHER_BONE.get(), NETHER_STAR, OBSIDIAN_STICK.get());
        crafter.buildSword(con, ENDER_DRAGON_SWORD_BASE, ENDER_DRAGON_SCALE.get(), DRAGON_BREATH, OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, GUARDIAN_BATTLE_AXE_BASE, GUARDIAN_SCALE.get(), PRISMARINE, OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, SUPER_STAR_BATTLE_AXE_BASE, WITHER_BONE.get(), OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, ENDER_DRAGON_BATTLE_AXE_BASE, ENDER_DRAGON_SCALE.get(), DRAGON_BREATH, OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, GUARDIAN_PICKAXE_BASE, GUARDIAN_SCALE.get(), PRISMARINE, OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, SUPER_STAR_PICKAXE_BASE, WITHER_BONE.get(), NETHER_STAR, OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, ENDER_DRAGON_PICKAXE_BASE, ENDER_DRAGON_SCALE.get(), DRAGON_BREATH, OBSIDIAN_STICK.get());
        crafter.buildBow(con, GUARDIAN_BOW_BASE, PRISMARINE, GUARDIAN_SCALE.get());
        crafter.buildBow(con, SUPER_STAR_BOW_BASE, NETHER_STAR, WITHER_BONE.get());
        crafter.buildBow(con, ENDER_DRAGON_BOW_BASE, DRAGON_BREATH, ENDER_DRAGON_SCALE.get());

        //Warden bases contain no hearts: the smithing upgrade adds exactly 1 Warden Heart per weapon
        crafter.buildSword(con, WARDEN_SWORD_BASE, ECHO_SHARD, SCULK_CATALYST, OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, WARDEN_BATTLE_AXE_BASE, ECHO_SHARD, SCULK_CATALYST, OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, WARDEN_PICKAXE_BASE, ECHO_SHARD, SCULK_CATALYST, OBSIDIAN_STICK.get());
        crafter.buildBow(con, WARDEN_BOW_BASE, SCULK_CATALYST, ECHO_SHARD);

        //Amethyst/Quartz/Glowstone/Honey (low tier, base-block + stick like coal/redstone/lapis)
        crafter.buildSword(con, SWORDS[10], AMETHYST_BLOCK, STICK);
        crafter.buildBattleAxe(con, BATTLE_AXES[10], AMETHYST_BLOCK, STICK);
        crafter.buildPickaxe(con, PICKAXES[10], AMETHYST_BLOCK, STICK);
        crafter.buildBow(con, BOWS[10], AMETHYST_BLOCK);

        crafter.buildSword(con, SWORDS[11], QUARTZ_BLOCK, STICK);
        crafter.buildBattleAxe(con, BATTLE_AXES[11], QUARTZ_BLOCK, STICK);
        crafter.buildPickaxe(con, PICKAXES[11], QUARTZ_BLOCK, STICK);
        crafter.buildBow(con, BOWS[11], QUARTZ_BLOCK);

        crafter.buildSword(con, SWORDS[12], GLOWSTONE, STICK);
        crafter.buildBattleAxe(con, BATTLE_AXES[12], GLOWSTONE, STICK);
        crafter.buildPickaxe(con, PICKAXES[12], GLOWSTONE, STICK);
        crafter.buildBow(con, BOWS[12], GLOWSTONE);

        crafter.buildSword(con, SWORDS[13], HONEYCOMB_BLOCK, STICK);
        crafter.buildBattleAxe(con, BATTLE_AXES[13], HONEYCOMB_BLOCK, STICK);
        crafter.buildPickaxe(con, PICKAXES[13], HONEYCOMB_BLOCK, STICK);
        crafter.buildBow(con, BOWS[13], HONEYCOMB_BLOCK);
        //Weapons for the remaining armor materials (parity with the armor sets). The index is the APToolMaterial/APMaceMaterial ordinal.
        List<ItemLike> parityMaterials = List.of(FEATHER, SLIME_BLOCK, ARDITE_INGOT.get(), COBALT_INGOT.get(), KNIGHT_SLIME_CRYSTAL.get(), PIG_IRON_INGOT.get(), MANYULLYN_INGOT.get(), INFUSED_FROST_CRYSTAL.get(), INFUSED_FROST_LAVA_CRYSTAL.get(), ENHANCED_CHAINMAIL.get(), ENHANCED_GOLD.get(), ENHANCED_COPPER.get(), ENHANCED_IRON.get(), ENHANCED_DIAMOND.get(), ENHANCED_NETHERITE.get());
        IntStream.range(0, parityMaterials.size()).forEach(j -> {
            int i = 15 + j;
            ItemLike material = parityMaterials.get(j);
            //Manyullyn, the frost crystals and Enhanced Netherite are top tier and use the obsidian stick
            ItemLike stick = j == 6 || j == 7 || j == 8 || j == 14 ? OBSIDIAN_STICK.get() : STICK;
            crafter.buildSword(con, SWORDS[i], material, stick);
            crafter.buildBattleAxe(con, BATTLE_AXES[i], material, stick);
            crafter.buildPickaxe(con, PICKAXES[i], material, stick);
            crafter.buildBow(con, BOWS[i], material);
            Item mace = MACES[i].get();
            String path = getPath(mace).replace("item_", "").replace("_mace", "");
            crafter.build(con, RecipeCategory.COMBAT, Result.build(mace, "maces", path), GridInput.build(" DD", " SD", "S  ", 'S', 'D'), stick, material);
            crafter.build(con, RecipeCategory.COMBAT, Result.build(mace, "maces", path).setSuffix("_alt"), GridInput.build("DD ", "DS ", "  S", 'S', 'D'), stick, material);
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
        crafter.buildArmorSet(con, "copper", ENHANCED_COPPER.get(), enGroup + "copper", enPath, "", "");
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
        //Warden armor bases contain no hearts: the smithing upgrade adds 1 Warden Heart per piece (4 per set)
        crafter.buildArmorSet(con, "warden", ECHO_SHARD, SCULK_CATALYST, "_base");
        crafter.buildArmorSet(con, "frost", INFUSED_FROST_CRYSTAL.get(), "");
        crafter.buildArmorSet(con, "frost_lava", INFUSED_FROST_LAVA_CRYSTAL.get(), "");
        crafter.buildArmorSet(con, "amethyst", AMETHYST_BLOCK, "");
        crafter.buildArmorSet(con, "quartz", QUARTZ_BLOCK, "");
        crafter.buildArmorSet(con, "glowstone", GLOWSTONE, "");
        crafter.buildArmorSet(con, "honey", HONEYCOMB_BLOCK, "");
        //Tinkers' Construct-inspired alloy ingots
        //Pig Iron (~Emerald/Obsidian tier, 33 durability): a ring of iron smelted around rotten flesh ("blood")
        crafter.build(con, RecipeCategory.MISC, Result.build(PIG_IRON_INGOT.get(), 1, "ap_tc_mats", "alloy"), GridInput.build("III", "IFI", "III", 'I', 'F'), IRON_INGOT, Ingredient.of(ROTTEN_FLESH));
        //Cobalt (~just above Obsidian tier, 44 durability): iron infused with a full block of lapis
        crafter.build(con, RecipeCategory.MISC, Result.build(COBALT_INGOT.get(), 1, "ap_tc_mats", "alloy"), GridInput.build("III", "ILI", "III", 'I', 'L'), IRON_INGOT, Ingredient.of(LAPIS_BLOCK));
        //Ardite (~Infused Lava tier, 55 durability): iron forged around a magma block, matching its hotter/pricier tier than Cobalt
        crafter.build(con, RecipeCategory.MISC, Result.build(ARDITE_INGOT.get(), 1, "ap_tc_mats", "alloy"), GridInput.build("III", "IMI", "III", 'I', 'M'), IRON_INGOT, Ingredient.of(MAGMA_BLOCK));
        //Knightslime (~Pig Iron tier, 33 durability): a ring of slime grown around a Pig Iron ingot
        crafter.build(con, RecipeCategory.MISC, Result.build(KNIGHT_SLIME_CRYSTAL.get(), 1, "ap_tc_mats", "alloy"), GridInput.build("SSS", "SPS", "SSS", 'S', 'P'), SLIME_BALL, Ingredient.of(PIG_IRON_INGOT.get()));
        //Manyullyn (top tier, 66 durability, netherite-grade enchantability/toughness): true alloy of Ardite and Cobalt
        crafter.build(con, RecipeCategory.MISC, Result.build(MANYULLYN_INGOT.get(), 1, "ap_tc_mats", "alloy"), ARDITE_INGOT.get(), Ingredient.of(ARDITE_INGOT.get()), Ingredient.of(ARDITE_INGOT.get()), Ingredient.of(ARDITE_INGOT.get()), Ingredient.of(COBALT_INGOT.get()), Ingredient.of(COBALT_INGOT.get()), Ingredient.of(COBALT_INGOT.get()), Ingredient.of(COBALT_INGOT.get()));
        //Tinkers' Construct-inspired armor sets
        crafter.buildArmorSet(con, "ardite", ARDITE_INGOT.get(), "");
        crafter.buildArmorSet(con, "cobalt", COBALT_INGOT.get(), "");
        crafter.buildArmorSet(con, "pig_iron", PIG_IRON_INGOT.get(), "");
        crafter.buildArmorSet(con, "knight_slime", KNIGHT_SLIME_CRYSTAL.get(), "");
        crafter.buildArmorSet(con, "manyullyn", MANYULLYN_INGOT.get(), "");
        int bound = BLOCK_MATERIALS_ORDERED.size();
        IntStream.range(0, bound).forEach(i -> {
            Item mace = MACES[i].get();
            String path = getPath(mace).replace("item_", "").replace("_mace", "");
            ItemLike material = BLOCK_MATERIALS_ORDERED.get(i);
            crafter.build(con, RecipeCategory.COMBAT, Result.build(mace, "maces", path), GridInput.build(" DD", " SD", "S  ", 'S', 'D'), i > 4 ? OBSIDIAN_STICK.get() : WOODEN_ROD.get(), material);
            crafter.build(con, RecipeCategory.COMBAT, Result.build(mace, "maces", path).setSuffix("_alt"), GridInput.build("DD ", "DS ", "  S", 'S', 'D'), i > 4 ? OBSIDIAN_STICK.get() : WOODEN_ROD.get(), material);
        });
        //Warden mace (index 9): a single Warden Heart, like every other Warden weapon
        Item wardenMace = MACES[9].get();
        String wardenMacePath = getPath(wardenMace).replace("item_", "").replace("_mace", "");
        crafter.build(con, RecipeCategory.COMBAT, Result.build(wardenMace, "maces", wardenMacePath), GridInput.build(" EH", " SE", "S  ", 'S', 'E', 'H'), OBSIDIAN_STICK.get(), ECHO_SHARD, WARDEN_HEART.get());
        crafter.build(con, RecipeCategory.COMBAT, Result.build(wardenMace, "maces", wardenMacePath).setSuffix("_alt"), GridInput.build("HE ", "ES ", "  S", 'S', 'E', 'H'), OBSIDIAN_STICK.get(), ECHO_SHARD, WARDEN_HEART.get());
        //Slayer mace (index 10)
        Item slayerMace = MACES[10].get();
        String slayerMacePath = getPath(slayerMace).replace("item_", "").replace("_mace", "");
        crafter.build(con, RecipeCategory.COMBAT, Result.build(slayerMace, "maces", slayerMacePath), GridInput.build(" DD", " SD", "S  ", 'S', 'D'), OBSIDIAN_STICK.get(), THE_ULTIMATE_MATERIAL.get());
        crafter.build(con, RecipeCategory.COMBAT, Result.build(slayerMace, "maces", slayerMacePath).setSuffix("_alt"), GridInput.build("DD ", "DS ", "  S", 'S', 'D'), OBSIDIAN_STICK.get(), THE_ULTIMATE_MATERIAL.get());
        //Amethyst/Quartz/Glowstone/Honey maces (low tier, wooden rod)
        List<ItemLike> newLowTierMaceMaterials = List.of(AMETHYST_BLOCK, QUARTZ_BLOCK, GLOWSTONE, HONEYCOMB_BLOCK);
        IntStream.range(0, newLowTierMaceMaterials.size()).forEach(j -> {
            int i = 11 + j;
            Item mace = MACES[i].get();
            String path = getPath(mace).replace("item_", "").replace("_mace", "");
            ItemLike material = newLowTierMaceMaterials.get(j);
            crafter.build(con, RecipeCategory.COMBAT, Result.build(mace, "maces", path), GridInput.build(" DD", " SD", "S  ", 'S', 'D'), WOODEN_ROD.get(), material);
            crafter.build(con, RecipeCategory.COMBAT, Result.build(mace, "maces", path).setSuffix("_alt"), GridInput.build("DD ", "DS ", "  S", 'S', 'D'), WOODEN_ROD.get(), material);
        });
        //Storage Blocks
        crafter.buildStorage(con, COMPRESSED_LAVA_CRYSTAL.get(), ModBlocks.LAVA_CRYSTAL.get());
        crafter.buildStorage(con, ModBlocks.LAVA_CRYSTAL.get(), ModItems.LAVA_CRYSTAL.get());
        crafter.buildStorage(con, COMPRESSED_INFUSED_LAVA_CRYSTAL.get(), ModBlocks.INFUSED_LAVA_CRYSTAL.get());
        crafter.buildStorage(con, ModBlocks.INFUSED_LAVA_CRYSTAL.get(), INFUSED_LAVA_CRYSTAL.get());
        crafter.buildStorage(con, COMPRESSED_OBSIDIAN.get(), Items.OBSIDIAN);
        crafter.buildStorage(con, ModItems.LAVA_CRYSTAL.get(), LAVA_SHARD.get());
        crafter.buildStorage(con, ModItems.FROST_CRYSTAL.get(), FROST_SHARD.get());

        //Chainmail
        crafter.build(con, RecipeCategory.MISC, Result.build(CHAINMAIL.get(), 4).setGroup("materials").setSuffix(""), IRON_INGOT, IRON_NUGGET, IRON_NUGGET, IRON_NUGGET);

        //Ultimate Material
        crafter.build(con, RecipeCategory.MISC, Result.build(THE_ULTIMATE_MATERIAL.get(), 1).setGroup("materials").setSuffix(""), INFUSED_FROST_LAVA_CRYSTAL.get(), WITHER_BONE.get(), ENDER_DRAGON_SCALE.get(), GUARDIAN_SCALE.get(), ECHO_SHARD);
        //Slayer Set
        crafter.buildComplexArmorSet(con, "slayer", THE_ULTIMATE_MATERIAL.get(), "super_star", "ender_dragon", "guardian", "_base");
        crafter.buildSword(con, SLAYER_SWORD_BASE, THE_ULTIMATE_MATERIAL.get(), SUPER_STAR_SWORD_BASE.get(), ENDER_DRAGON_SWORD_BASE.get(), GUARDIAN_SWORD_BASE.get(), OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, SLAYER_BATTLE_AXE_BASE, THE_ULTIMATE_MATERIAL.get(), SUPER_STAR_BATTLE_AXE_BASE.get(), ENDER_DRAGON_BATTLE_AXE_BASE.get(), GUARDIAN_BATTLE_AXE_BASE.get(), OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, SLAYER_PICKAXE_BASE, THE_ULTIMATE_MATERIAL.get(), SUPER_STAR_PICKAXE_BASE.get(), ENDER_DRAGON_PICKAXE_BASE.get(), GUARDIAN_PICKAXE_BASE.get(), OBSIDIAN_STICK.get());
        crafter.buildBow(con, SLAYER_BOW_BASE, THE_ULTIMATE_MATERIAL.get(), SUPER_STAR_BOW_BASE.get(), ENDER_DRAGON_BOW_BASE.get(), GUARDIAN_BOW_BASE.get());

        crafter.build(con, RecipeCategory.MISC, Result.build(SLAYER_SOUL.get(), 1).setGroup("slayer").setSuffix(""), ELDER_GUARDIAN_SOUL.get(), WITHER_BOSS_SOUL.get(), ENDER_DRAGON_SOUL.get(), WARDEN_HEART.get());
        //Other
        crafter.build(con, RecipeCategory.BUILDING_BLOCKS, Result.build(SOUL_BOX.get()).setGroup("villager").setSuffix(""), GridInput.build("ASA", "SGS", "ASA", 'A', 'S', 'G'), Items.STONE, SOUL_SAND, GLOWSTONE);
        crafter.build(con, RecipeCategory.MISC, Result.build(OBSIDIAN_STICK.get(), 4).setGroup("materials").setSuffix(""), GridInput.build(Grid.build("X", "X", " "), 'X'), Items.OBSIDIAN);
        crafter.build(con, RecipeCategory.MISC, Result.build(WOODEN_ROD.get(), 2).setGroup("materials").setSuffix(""), STICK, STICK, STICK, STICK);
        crafter.build(con, RecipeCategory.BUILDING_BLOCKS, Result.build(SNOW_BRICK.get(), 4).setGroup("snow_brick").setSuffix(""), GridInput.buildSmallSquare('X'), SNOW_BLOCK);
        crafter.build(con, RecipeCategory.BUILDING_BLOCKS, Result.build(SNOW_BRICK_STAIRS.get(), 4).setGroup("snow_brick_stairs").setSuffix(""), GridInput.buildStairs('X'), SNOW_BRICK.get());
        crafter.build(con, RecipeCategory.BUILDING_BLOCKS, Result.build(SNOW_BRICK_SLAB.get(), 4).setGroup("snow_brick_slab").setSuffix(""), GridInput.buildSlab('X'), SNOW_BRICK.get());

        SpecialRecipeBuilder.special(RepairItemRecipe::new).save(con, "repair_item");


    }


}
