package com.sofodev.armorplus.data;

import com.sofodev.armorplus.data.recipe.GridInput;
import com.sofodev.armorplus.data.recipe.Result;
import com.sofodev.armorplus.registry.APItems;
import com.sofodev.armorplus.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags.IOptionalNamedTag;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.sofodev.armorplus.ArmorPlus.AP_STONE_BRICKS_LENGTH;
import static com.sofodev.armorplus.ArmorPlus.AP_TOOL_MATERIAL_LENGTH;
import static com.sofodev.armorplus.registry.APItems.INFUSED_LAVA_CRYSTAL;
import static com.sofodev.armorplus.registry.APItems.*;
import static com.sofodev.armorplus.registry.ModBlocks.STONE_BRICKS;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.utils.DataUtils.getPath;
import static java.util.Arrays.asList;
import static net.minecraft.item.Items.*;
import static net.minecraftforge.common.Tags.Items.*;

public class Recipes extends RecipeProvider implements IDataProvider, IConditionBuilder {

    public static final List<IOptionalNamedTag<Item>> BRICK_COLORS = Stream.of(DYES_BLACK, DYES_BLUE, DYES_GREEN, DYES_PURPLE, DYES_RED, DYES_WHITE, DYES_YELLOW, DYES_ORANGE).collect(Collectors.toList());
    public static final List<IItemProvider> MATERIALS_ORDERED = Stream.of(COAL, REDSTONE, LAPIS_LAZULI, EMERALD, Items.OBSIDIAN, INFUSED_LAVA_CRYSTAL.get(), GUARDIAN_SCALE.get(), WITHER_BONE.get(), ENDER_DRAGON_SCALE.get()).collect(Collectors.toList());
    public static final List<IItemProvider> BLOCK_MATERIALS_ORDERED = Stream.of(COAL_BLOCK, REDSTONE_BLOCK, LAPIS_BLOCK, EMERALD_BLOCK, COMPRESSED_OBSIDIAN.get(), INFUSED_LAVA_CRYSTAL.get(), GUARDIAN_SCALE.get(), WITHER_BONE.get(), ENDER_DRAGON_SCALE.get()).collect(Collectors.toList());
    public static final List<IItemProvider> LOW_TO_MID_TIER_MATERIAL_LIST = Stream.of(COAL_BLOCK, REDSTONE_BLOCK, LAPIS_BLOCK, EMERALD_BLOCK, COMPRESSED_OBSIDIAN.get(), INFUSED_LAVA_CRYSTAL.get()).collect(Collectors.toList());


    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> con) {
        this.registerCraftingRecipes(con);
        this.registerSmithingRecipes(con);
    }

    private void registerCraftingRecipes(Consumer<IFinishedRecipe> con) {
        CraftingRecipeMaker crafter = new CraftingRecipeMaker(generator);
        //StoneBricks+CastleBlocks
        int l = AP_STONE_BRICKS_LENGTH;
        for (int i = 0; i < l; i++) {
            crafter.buildStoneBrick(con, CASTLE_BLOCKS[i], CASTLE_BLOCK_TOWERS[i], CASTLE_BLOCK_CORNERS[i], CASTLE_BLOCK_WALLS[i]);
        }
        for (int i = 0; i < l; i++) {
            crafter.buildStoneBrick(con, STONE_BRICKS[i], STONE_BRICK_TOWERS[i], STONE_BRICK_CORNERS[i], STONE_BRICK_WALLS[i]);
        }
        for (int i = 0; i < l; i++) {
            crafter.buildColoredBrick(con, STONE_BRICKS[i], BRICK_COLORS.get(i));
        }
        //Bows
        for (int i = 0; i < AP_TOOL_MATERIAL_LENGTH - 4; i++) {
            crafter.buildSword(con, SWORDS[i], LOW_TO_MID_TIER_MATERIAL_LIST.get(i), STICK);
            crafter.buildBattleAxe(con, BATTLE_AXES[i], LOW_TO_MID_TIER_MATERIAL_LIST.get(i), STICK);
            crafter.buildPickaxe(con, PICKAXES[i], LOW_TO_MID_TIER_MATERIAL_LIST.get(i), STICK);
            crafter.buildBow(con, BOWS[i], LOW_TO_MID_TIER_MATERIAL_LIST.get(i));
        }
        crafter.buildSword(con, GUARDIAN_SWORD_BASE, GUARDIAN_SCALE.get(), OBSIDIAN_STICK.get());
        crafter.buildSword(con, SUPER_STAR_SWORD_BASE, WITHER_BONE.get(), OBSIDIAN_STICK.get());
        crafter.buildSword(con, ENDER_DRAGON_SWORD_BASE, ENDER_DRAGON_SCALE.get(), OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, GUARDIAN_BATTLE_AXE_BASE, GUARDIAN_SCALE.get(), OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, SUPER_STAR_BATTLE_AXE_BASE, WITHER_BONE.get(), OBSIDIAN_STICK.get());
        crafter.buildBattleAxe(con, ENDER_DRAGON_BATTLE_AXE_BASE, ENDER_DRAGON_SCALE.get(), OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, GUARDIAN_PICKAXE_BASE, GUARDIAN_SCALE.get(), OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, SUPER_STAR_PICKAXE_BASE, WITHER_BONE.get(), OBSIDIAN_STICK.get());
        crafter.buildPickaxe(con, ENDER_DRAGON_PICKAXE_BASE, ENDER_DRAGON_SCALE.get(), OBSIDIAN_STICK.get());
        crafter.buildBow(con, GUARDIAN_BOW_BASE, GUARDIAN_SCALE.get());
        crafter.buildBow(con, SUPER_STAR_BOW_BASE, WITHER_BONE.get());
        crafter.buildBow(con, ENDER_DRAGON_BOW_BASE, ENDER_DRAGON_SCALE.get());
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
        crafter.buildArmorSet(con, "coal", COAL_BLOCK, "");
        crafter.buildArmorSet(con, "lapis", LAPIS_BLOCK, "");
        crafter.buildArmorSet(con, "redstone", REDSTONE_BLOCK, "");
        crafter.buildArmorSet(con, "emerald", EMERALD_BLOCK, "");
        crafter.buildArmorSet(con, "obsidian", Items.OBSIDIAN, "");
        crafter.buildArmorSet(con, "infused_lava", INFUSED_LAVA_CRYSTAL.get(), "");
        crafter.buildArmorSet(con, "guardian", GUARDIAN_SCALE.get(), "_base");
        crafter.buildArmorSet(con, "super_star", WITHER_BONE.get(), "_base");
        crafter.buildArmorSet(con, "ender_dragon", ENDER_DRAGON_SCALE.get(), "_base");
        int bound = MACES.length - 1;
        IntStream.range(0, bound).forEach(i -> {
            Item mace = MACES[i].get();
            String path = getPath(mace).replace("item_", "").replace("_mace", "");
            crafter.build(con, Result.build(mace, "maces", path), GridInput.build(" DD", " SD", "S  ", 'S', 'D'), STICK, BLOCK_MATERIALS_ORDERED.get(i));
            crafter.build(con, Result.build(mace, "maces", path).setSuffix("_alt"), GridInput.build("DD ", "DS ", "  S", 'S', 'D'), STICK, BLOCK_MATERIALS_ORDERED.get(i));
        });
        //Storage Blocks
        crafter.buildStorage(con, COMPRESSED_LAVA_CRYSTAL.get(), ModBlocks.LAVA_CRYSTAL.get());
        crafter.buildStorage(con, ModBlocks.LAVA_CRYSTAL.get(), APItems.LAVA_CRYSTAL.get());
        crafter.buildStorage(con, COMPRESSED_INFUSED_LAVA_CRYSTAL.get(), ModBlocks.INFUSED_LAVA_CRYSTAL.get());
        crafter.buildStorage(con, ModBlocks.INFUSED_LAVA_CRYSTAL.get(), APItems.INFUSED_LAVA_CRYSTAL.get());
        crafter.buildStorage(con, COMPRESSED_OBSIDIAN.get(), Items.OBSIDIAN);

        //Other
        crafter.build(con, Result.build(OBSIDIAN_STICK.get(), 4), Items.OBSIDIAN, Items.OBSIDIAN);
    }

    private void registerSmithingRecipes(Consumer<IFinishedRecipe> con) {
        SmithingRecipeMaker smither = new SmithingRecipeMaker(generator);
        //ArmorBase + Soul = Complete Form
        smither.buildBaseToFullSmithing(con, SUPER_STAR_BASES, WITHER_BOSS_SOUL.get());
        smither.buildBaseToFullSmithing(con, GUARDIAN_BASES, ELDER_GUARDIAN_SOUL.get());
        smither.buildBaseToFullSmithing(con, ENDER_DRAGON_BASES, ENDER_DRAGON_SOUL.get());

        smither.buildBaseToFullSmithing(con, WITHER_BOSS_SOUL.get(),
                SUPER_STAR_SWORD_BASE, SUPER_STAR_BATTLE_AXE_BASE, SUPER_STAR_PICKAXE_BASE, SUPER_STAR_BOW_BASE
        );
        smither.buildBaseToFullSmithing(con, ELDER_GUARDIAN_SOUL.get(),
                GUARDIAN_SWORD_BASE, GUARDIAN_BATTLE_AXE_BASE, GUARDIAN_PICKAXE_BASE, GUARDIAN_BOW_BASE
        );
        smither.buildBaseToFullSmithing(con, ENDER_DRAGON_SOUL.get(),
                ENDER_DRAGON_SWORD_BASE, ENDER_DRAGON_BATTLE_AXE_BASE, ENDER_DRAGON_PICKAXE_BASE, ENDER_DRAGON_BOW_BASE
        );

        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_HELMET, ENHANCED_NETHERITE);
        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_CHESTPLATE, ENHANCED_NETHERITE);
        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_LEGGINGS, ENHANCED_NETHERITE);
        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_BOOTS, ENHANCED_NETHERITE);
    }

    @Override
    public String getName() {
        return "Recipes";
    }
}
