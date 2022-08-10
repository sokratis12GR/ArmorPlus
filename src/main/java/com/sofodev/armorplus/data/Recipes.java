package com.sofodev.armorplus.data;

import com.sofodev.armorplus.data.recipe.Grid;
import com.sofodev.armorplus.data.recipe.GridInput;
import com.sofodev.armorplus.data.recipe.Result;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.sofodev.armorplus.ArmorPlus.AP_STONE_BRICKS_LENGTH;
import static com.sofodev.armorplus.ArmorPlus.AP_TOOL_MATERIAL_LENGTH;
import static com.sofodev.armorplus.registry.ModBlocks.STONE_BRICKS;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModItems.INFUSED_FROST_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.INFUSED_LAVA_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.utils.DataUtils.getPath;
import static java.util.Arrays.asList;
import static net.minecraft.world.item.Items.*;
import static net.minecraftforge.common.Tags.Items.*;

public class Recipes extends RecipeProvider implements DataProvider, IConditionBuilder {

    public static final List<TagKey<Item>> BRICK_COLORS = Stream.of(DYES_BLACK, DYES_BLUE, DYES_GREEN, DYES_PURPLE, DYES_RED, DYES_WHITE, DYES_YELLOW, DYES_ORANGE).collect(Collectors.toList());
    public static final List<ItemLike> MATERIALS_ORDERED = Stream.of(COAL, REDSTONE, LAPIS_LAZULI, EMERALD, Items.OBSIDIAN, INFUSED_LAVA_CRYSTAL.get(), GUARDIAN_SCALE.get(), WITHER_BONE.get(), ENDER_DRAGON_SCALE.get()).collect(Collectors.toList());
    public static final List<ItemLike> BLOCK_MATERIALS_ORDERED = Stream.of(COAL_BLOCK, REDSTONE_BLOCK, LAPIS_BLOCK, EMERALD_BLOCK, COMPRESSED_OBSIDIAN.get(), INFUSED_LAVA_CRYSTAL.get(), GUARDIAN_SCALE.get(), WITHER_BONE.get(), ENDER_DRAGON_SCALE.get()).collect(Collectors.toList());
    public static final List<ItemLike> LOW_TO_MID_TIER_MATERIAL_LIST = Stream.of(COAL_BLOCK, REDSTONE_BLOCK, LAPIS_BLOCK, EMERALD_BLOCK, COMPRESSED_OBSIDIAN.get()).collect(Collectors.toList());
    private DataGenerator generator;

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
        generator = generatorIn;
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> con) {
        this.registerCraftingRecipes(con);
        this.registerSmithingRecipes(con);
    }

    private void registerCraftingRecipes(Consumer<FinishedRecipe> con) {
        CraftingRecipeMaker crafter = new CraftingRecipeMaker(generator);
        //StoneBricks+CastleBlocks
        int l = AP_STONE_BRICKS_LENGTH;
        for (int i = 0; i < l; i++) {
            crafter.buildStoneBrick(con, CASTLE_BLOCKS[i], CASTLE_BLOCK_TOWERS[i], CASTLE_BLOCK_CORNERS[i], CASTLE_BLOCK_WALLS[i], CASTLE_BLOCK_STAIRS[i], CASTLE_BLOCK_SLABS[i]);
        }
        for (int i = 0; i < l; i++) {
            crafter.buildStoneBrick(con, STONE_BRICKS[i], STONE_BRICK_TOWERS[i], STONE_BRICK_CORNERS[i], STONE_BRICK_WALLS[i], ModBlocks.STONE_BRICK_STAIRS[i], STONE_BRICK_SLABS[i]);
        }
        for (int i = 0; i < l; i++) {
            crafter.buildColoredBrick(con, STONE_BRICKS[i], BRICK_COLORS.get(i));
        }
        //Bows
        for (int i = 0; i < AP_TOOL_MATERIAL_LENGTH - 5; i++) {
            crafter.buildSword(con, SWORDS[i], LOW_TO_MID_TIER_MATERIAL_LIST.get(i), STICK);
            crafter.buildBattleAxe(con, BATTLE_AXES[i], LOW_TO_MID_TIER_MATERIAL_LIST.get(i), STICK);
            crafter.buildPickaxe(con, PICKAXES[i], LOW_TO_MID_TIER_MATERIAL_LIST.get(i), STICK);
            crafter.buildBow(con, BOWS[i], LOW_TO_MID_TIER_MATERIAL_LIST.get(i));
        }
        int lava = AP_TOOL_MATERIAL_LENGTH - 5;
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
        //Arrows
        crafter.buildOrderedArrow(con,
                asList(ITEM_COAL_ARROW, ITEM_REDSTONE_ARROW, ITEM_LAPIS_ARROW, ITEM_EMERALD_ARROW, ITEM_OBSIDIAN_ARROW,
                        ITEM_INFUSED_LAVA_ARROW, ITEM_GUARDIAN_ARROW, ITEM_SUPER_STAR_ARROW, ITEM_ENDER_DRAGON_ARROW)
        );
        //Lesser Souls to Boss Souls recipes
        crafter.buildSoul(con, WITHER_BOSS_SOUL, WITHER_SKELETON_SOUL.get(), WITHER_BONE.get(), SOUL_SAND);
        crafter.buildSoul(con, ELDER_GUARDIAN_SOUL, GUARDIAN_SOUL.get(), GUARDIAN_SCALE.get(), PRISMARINE);
        crafter.buildSoul(con, ENDER_DRAGON_SOUL, ENDERMAN_SOUL.get(), ENDER_DRAGON_SCALE.get(), END_STONE);

        //Vanilla Material + Glowstone = Enchaned: Material
        crafter.buildEnhanced(con, ENHANCED_CHAINMAIL, CHAINMAIL.get());
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
        int bound = MACES.length - 1;
        IntStream.range(0, bound).forEach(i -> {
            Item mace = MACES[i].get();
            String path = getPath(mace).replace("item_", "").replace("_mace", "");
            ItemLike material = BLOCK_MATERIALS_ORDERED.get(i);
            crafter.build(con, Result.build(mace, "maces", path), GridInput.build(" DD", " SD", "S  ", 'S', 'D'), i > 4 ? OBSIDIAN_STICK.get() : WOODEN_ROD.get(), material);
            crafter.build(con, Result.build(mace, "maces", path).setSuffix("_alt"), GridInput.build("DD ", "DS ", "  S", 'S', 'D'), i > 4 ? OBSIDIAN_STICK.get() : WOODEN_ROD.get(), material);
        });
        //Storage Blocks
        crafter.buildStorage(con, COMPRESSED_LAVA_CRYSTAL.get(), ModBlocks.LAVA_CRYSTAL.get());
        crafter.buildStorage(con, ModBlocks.LAVA_CRYSTAL.get(), ModItems.LAVA_CRYSTAL.get());
        crafter.buildStorage(con, COMPRESSED_INFUSED_LAVA_CRYSTAL.get(), ModBlocks.INFUSED_LAVA_CRYSTAL.get());
        crafter.buildStorage(con, ModBlocks.INFUSED_LAVA_CRYSTAL.get(), INFUSED_LAVA_CRYSTAL.get());
        crafter.buildStorage(con, COMPRESSED_OBSIDIAN.get(), Items.OBSIDIAN);
        crafter.buildStorage(con, ModItems.LAVA_CRYSTAL.get(), LAVA_SHARD.get());
        crafter.buildStorage(con, ModItems.FROST_CRYSTAL.get(), FROST_SHARD.get());

        //Ultimate Material
        crafter.build(con, Result.build(THE_ULTIMATE_MATERIAL.get(), 1),
                INFUSED_FROST_LAVA_CRYSTAL.get(), WITHER_BONE.get(), ENDER_DRAGON_SCALE.get(), GUARDIAN_SCALE.get()
        );
        //Slayer Set
        crafter.buildComplexArmorSet(con, "slayer", THE_ULTIMATE_MATERIAL.get(), "super_star", "ender_dragon", "guardian", "_base");
        crafter.buildSword(con, SLAYER_SWORD_BASE, THE_ULTIMATE_MATERIAL.get(),
                SUPER_STAR_SWORD_BASE.get(), ENDER_DRAGON_SWORD_BASE.get(), GUARDIAN_SWORD_BASE.get(), OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, SLAYER_BATTLE_AXE_BASE, THE_ULTIMATE_MATERIAL.get(),
                SUPER_STAR_BATTLE_AXE_BASE.get(), ENDER_DRAGON_BATTLE_AXE_BASE.get(), GUARDIAN_BATTLE_AXE_BASE.get(), OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, SLAYER_PICKAXE_BASE, THE_ULTIMATE_MATERIAL.get(),
                SUPER_STAR_PICKAXE_BASE.get(), ENDER_DRAGON_PICKAXE_BASE.get(), GUARDIAN_PICKAXE_BASE.get(), OBSIDIAN_STICK.get());
        crafter.buildBow(con, SLAYER_BOW_BASE, THE_ULTIMATE_MATERIAL.get(),
                SUPER_STAR_BOW_BASE.get(), ENDER_DRAGON_BOW_BASE.get(), GUARDIAN_BOW_BASE.get());

        crafter.build(con, Result.build(SLAYER_SOUL.get(), 1).setGroup("slayer").setSuffix(""), ELDER_GUARDIAN_SOUL.get(), WITHER_BOSS_SOUL.get(), ENDER_DRAGON_SOUL.get());
        //Other
        crafter.build(con, Result.build(SOUL_BOX.get()).setGroup("villager").setSuffix(""), GridInput.build("ASA", "SGS", "ASA", 'A', 'S', 'G'), Items.STONE, SOUL_SAND, GLOWSTONE);
        crafter.build(con, Result.build(OBSIDIAN_STICK.get(), 4).setGroup("materials").setSuffix(""), GridInput.build(Grid.build("X", "X", " "), 'X'), Items.OBSIDIAN);
        crafter.build(con, Result.build(WOODEN_ROD.get(), 2).setGroup("materials").setSuffix(""), STICK, STICK, STICK, STICK);
        crafter.build(con, Result.build(SNOW_BRICK.get(), 4).setGroup("snow_brick").setSuffix(""), GridInput.buildSmallSquare('X'), SNOW_BLOCK);
        crafter.build(con, Result.build(SNOW_BRICK_STAIRS.get(), 4).setGroup("snow_brick_stairs").setSuffix(""), GridInput.buildStairs('X'), SNOW_BRICK.get());
        crafter.build(con, Result.build(SNOW_BRICK_SLAB.get(), 4).setGroup("snow_brick_slab").setSuffix(""), GridInput.buildSlab('X'), SNOW_BRICK.get());
    }

    private void registerSmithingRecipes(Consumer<FinishedRecipe> con) {
        SmithingRecipeMaker smither = new SmithingRecipeMaker(generator);
        //ArmorBase + Soul = Complete Form
        smither.buildBaseToFullSmithing(con, SUPER_STAR_BASES, WITHER_BOSS_SOUL.get());
        smither.buildBaseToFullSmithing(con, GUARDIAN_BASES, ELDER_GUARDIAN_SOUL.get());
        smither.buildBaseToFullSmithing(con, ENDER_DRAGON_BASES, ENDER_DRAGON_SOUL.get());
        smither.buildBaseToFullSmithing(con, SLAYER_BASES, SLAYER_SOUL.get());

        smither.buildBaseToFullSmithing(con, WITHER_BOSS_SOUL.get(),
                SUPER_STAR_SWORD_BASE, SUPER_STAR_BATTLE_AXE_BASE, SUPER_STAR_PICKAXE_BASE, SUPER_STAR_BOW_BASE
        );
        smither.buildBaseToFullSmithing(con, ELDER_GUARDIAN_SOUL.get(),
                GUARDIAN_SWORD_BASE, GUARDIAN_BATTLE_AXE_BASE, GUARDIAN_PICKAXE_BASE, GUARDIAN_BOW_BASE
        );
        smither.buildBaseToFullSmithing(con, ENDER_DRAGON_SOUL.get(),
                ENDER_DRAGON_SWORD_BASE, ENDER_DRAGON_BATTLE_AXE_BASE, ENDER_DRAGON_PICKAXE_BASE, ENDER_DRAGON_BOW_BASE
        );
        smither.buildBaseToFullSmithing(con, SLAYER_SOUL.get(),
                SLAYER_SWORD_BASE, SLAYER_BATTLE_AXE_BASE, SLAYER_PICKAXE_BASE, SLAYER_BOW_BASE
        );

        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_HELMET, ENHANCED_NETHERITE);
        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_CHESTPLATE, ENHANCED_NETHERITE);
        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_LEGGINGS, ENHANCED_NETHERITE);
        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_BOOTS, ENHANCED_NETHERITE);
        smither.buildSmithing(con, INFUSED_LAVA_CRYSTAL.get(), INFUSED_FROST_CRYSTAL.get(), INFUSED_FROST_LAVA_CRYSTAL.get());
        smither.buildSmithing(con, INFUSED_FROST_CRYSTAL.get(), INFUSED_LAVA_CRYSTAL.get(), INFUSED_FROST_LAVA_CRYSTAL.get());
    }

    @Override
    public String getName() {
        return "Recipes";
    }
}
