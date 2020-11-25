package com.sofodev.armorplus.data;

import com.sofodev.armorplus.registry.APItems;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.IOptionalNamedTag;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.sofodev.armorplus.registry.APItems.*;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.setRL;
import static net.minecraft.item.crafting.Ingredient.fromItems;
import static net.minecraftforge.common.Tags.Items.*;

public class Recipes extends RecipeProvider implements IDataProvider, IConditionBuilder {

    private static final List<IOptionalNamedTag<Item>> BRICK_COLORS = Stream.of(
            DYES_BLACK, DYES_BLUE, DYES_GREEN, DYES_PURPLE, DYES_RED, DYES_WHITE, DYES_YELLOW, DYES_ORANGE
    ).collect(Collectors.toList());
    private static final List<IItemProvider> MATERIALS_ORDERED = Stream.of(
            Items.COAL, Items.REDSTONE, Items.LAPIS_LAZULI, Items.EMERALD, Items.OBSIDIAN,
            APItems.INFUSED_LAVA_CRYSTAL.get(), APItems.GUARDIAN_SCALE.get(), APItems.WITHER_BONE.get(), APItems.ENDER_DRAGON_SCALE.get()
    ).collect(Collectors.toList());

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        /*
          Crafting Table
         */
        //StoneBricks+CastleBlocks
        int l = STONE_BRICKS.length;
        IntStream.range(0, CASTLE_BLOCKS.length).forEach(i -> this.registerStoneBrickRecipes(consumer, CASTLE_BLOCKS[i],
                CASTLE_BLOCK_TOWERS[i], CASTLE_BLOCK_CORNERS[i], CASTLE_BLOCK_WALLS[i]));
        IntStream.range(0, l).forEach(i -> this.registerStoneBrickRecipes(consumer, STONE_BRICKS[i],
                STONE_BRICK_TOWERS[i], STONE_BRICK_CORNERS[i], STONE_BRICK_WALLS[i]));
        IntStream.range(0, l).forEach(i -> this.registerColorRecipes(consumer, STONE_BRICKS[i], BRICK_COLORS.get(i)));
        //Arrows
        this.orderArrowsAndRegister(consumer,
                MATERIALS_ORDERED, ITEM_COAL_ARROW, ITEM_REDSTONE_ARROW, ITEM_LAPIS_ARROW, ITEM_EMERALD_ARROW, ITEM_OBSIDIAN_ARROW,
                ITEM_INFUSED_LAVA_ARROW, ITEM_GUARDIAN_ARROW, ITEM_SUPER_STAR_ARROW, ITEM_ENDER_DRAGON_ARROW
        );
        //Lesser Souls to Boss Souls recipes
        this.registerSoulRecipes(consumer, WITHER_BOSS_SOUL, WITHER_SKELETON_SOUL.get(), WITHER_BONE.get(), Items.SOUL_SAND);
        this.registerSoulRecipes(consumer, ELDER_GUARDIAN_SOUL, GUARDIAN_SOUL.get(), GUARDIAN_SCALE.get(), Items.PRISMARINE);
        this.registerSoulRecipes(consumer, ENDER_DRAGON_SOUL, ENDERMAN_SOUL.get(), ENDER_DRAGON_SCALE.get(), Items.END_STONE);
        /*
          Smithing Table
         */
        //ArmorBase + Soul = Complete Form
        this.regSmithingArmorBaseToFull(consumer, SUPER_STAR_BASES, WITHER_BOSS_SOUL.get());
        this.regSmithingArmorBaseToFull(consumer, GUARDIAN_BASES, ELDER_GUARDIAN_SOUL.get());
        this.regSmithingArmorBaseToFull(consumer, ENDER_DRAGON_BASES, ENDER_DRAGON_SOUL.get());
        //Vanilla Material + Glowstone = Enchaned: Material
        this.registerEnhancedRecipe(consumer, ENHANCED_CHAINMAIL, CHAINMAIL.get());
        this.registerEnhancedRecipe(consumer, ENHANCED_IRON, Items.IRON_INGOT);
        this.registerEnhancedRecipe(consumer, ENHANCED_GOLD, Items.GOLD_INGOT);
        this.registerEnhancedRecipe(consumer, ENHANCED_DIAMOND, Items.DIAMOND);
        this.registerEnhancedRecipe(consumer, ENHANCED_NETHERITE, Items.NETHERITE_INGOT);
        //Enhanced: Armors
        this.registerEnhancedVanillaRecipes(consumer, ENHANCED_CHAINMAIL,
                getAPItem("chainmail_helmet"), getAPItem("chainmail_chestplate"),
                getAPItem("chainmail_leggings"), getAPItem("chainmail_boots"));
        this.registerEnhancedVanillaRecipes(consumer, ENHANCED_IRON,
                getAPItem("iron_helmet"), getAPItem("iron_chestplate"),
                getAPItem("iron_leggings"), getAPItem("iron_boots"));
        this.registerEnhancedVanillaRecipes(consumer, ENHANCED_GOLD,
                getAPItem("golden_helmet"), getAPItem("golden_chestplate"),
                getAPItem("golden_leggings"), getAPItem("golden_boots"));
        this.registerEnhancedVanillaRecipes(consumer, ENHANCED_DIAMOND,
                getAPItem("diamond_helmet"), getAPItem("diamond_chestplate"),
                getAPItem("diamond_leggings"), getAPItem("diamond_boots"));
        this.regSmithingNormalToEnhanced(consumer, ENHANCED_NETHERITE, Items.NETHERITE_HELMET);
        this.regSmithingNormalToEnhanced(consumer, ENHANCED_NETHERITE, Items.NETHERITE_CHESTPLATE);
        this.regSmithingNormalToEnhanced(consumer, ENHANCED_NETHERITE, Items.NETHERITE_LEGGINGS);
        this.regSmithingNormalToEnhanced(consumer, ENHANCED_NETHERITE, Items.NETHERITE_BOOTS);
    }

    private void regSmithingNormalToEnhanced(Consumer<IFinishedRecipe> consumer, RegistryObject<Item> enMaterial, IItemProvider vanilla) {
        String path = vanilla.asItem().getRegistryName().getPath();
        SmithingRecipeBuilder.smithingRecipe(fromItems(vanilla), //Base
                fromItems(enMaterial.get()), //Addition
                getAPItem(path) // Result
        ).addCriterion("has_soul", hasItem(enMaterial.get()))
                .build(consumer, setRL("smithing/" + path));
    }

    private void registerEnhancedVanillaRecipes(Consumer<IFinishedRecipe> consumer, RegistryObject<Item> enMaterial,
                                                IItemProvider head, IItemProvider chest, IItemProvider legs, IItemProvider feet) {
        IItemProvider material = enMaterial.get();
        String headPath = head.asItem().getRegistryName().getPath();
        String chestPath = chest.asItem().getRegistryName().getPath();
        String legsPath = legs.asItem().getRegistryName().getPath();
        String feetPath = feet.asItem().getRegistryName().getPath();
        ShapedRecipeBuilder.shapedRecipe(head, 1)
                .patternLine("MMM")
                .patternLine("M M")
                .key('M', material)
                .setGroup("armorplus:ap_en_armors")
                .addCriterion("has_req_material", hasItem(material))
                .build(consumer, setRL("enhanced/" + headPath));
        ShapedRecipeBuilder.shapedRecipe(chest, 1)
                .patternLine("M M")
                .patternLine("MMM")
                .patternLine("MMM")
                .key('M', material)
                .setGroup("armorplus:ap_en_armors")
                .addCriterion("has_req_material", hasItem(material))
                .build(consumer, setRL("enhanced/" + chestPath));
        ShapedRecipeBuilder.shapedRecipe(legs, 1)
                .patternLine("MMM")
                .patternLine("M M")
                .patternLine("M M")
                .key('M', material)
                .setGroup("armorplus:ap_en_armors")
                .addCriterion("has_req_material", hasItem(material))
                .build(consumer, setRL("enhanced/" + legsPath));
        ShapedRecipeBuilder.shapedRecipe(feet, 1)
                .patternLine("M M")
                .patternLine("M M")
                .key('M', material)
                .setGroup("armorplus:ap_en_armors")
                .addCriterion("has_req_material", hasItem(material))
                .build(consumer, setRL("enhanced/" + feetPath));

    }

    private void registerEnhancedRecipe(Consumer<IFinishedRecipe> consumer, RegistryObject<Item> enMaterial, IItemProvider material) {
        IItemProvider ingredient = enMaterial.get();
        String path = enMaterial.getId().getPath();
        ShapedRecipeBuilder.shapedRecipe(ingredient, 1)
                .patternLine("GGG")
                .patternLine("GMG")
                .patternLine("GGG")
                .key('M', material)
                .key('G', Items.GLOWSTONE_DUST)
                .setGroup("armorplus:ap_en_mats")
                .addCriterion("has_req_base_material", hasItem(material))
                .build(consumer, setRL("enhanced/" + path));
    }

    /**
     * Creates the recipe json for the boss souls, by combining lesser souls and some representing materials
     *
     * @param consumer   the builder
     * @param bossSoul   The boss soul that is being crafted
     * @param lesserSoul The lesser soul from that dimension
     * @param mat        a material from the boss soul's dimension
     * @param extra      some extra ingredients from the same dimension
     */
    private void registerSoulRecipes(Consumer<IFinishedRecipe> consumer, RegistryObject<Item> bossSoul,
                                     IItemProvider lesserSoul, IItemProvider mat, IItemProvider extra) {
        IItemProvider ingredient = bossSoul.get();
        String path = bossSoul.getId().getPath();
        ShapedRecipeBuilder.shapedRecipe(ingredient, 1)
                .patternLine("ESE")
                .patternLine("SXS")
                .patternLine("ESE")
                .key('S', lesserSoul)
                .key('X', mat)
                .key('E', extra)
                .setGroup("armorplus:ap_souls")
                .addCriterion("has_lesser_soul", hasItem(lesserSoul))
                .build(consumer, setRL("soul/" + path));
    }

    private void regSmithingArmorBaseToFull(Consumer<IFinishedRecipe> consumer, Set<RegistryObject<Item>> bases, IItemProvider soul) {
        bases.forEach(base -> SmithingRecipeBuilder.smithingRecipe(fromItems(base.get()), //Base
                fromItems(soul), //Addition
                quickModLookupItem(base.getId()) // Result
        ).addCriterion("has_soul", hasItem(soul))
                .build(consumer, setRL("smithing/" + base.getId().getPath())));
    }

    private Item quickModLookupItem(ResourceLocation loc) {
        return ForgeRegistries.ITEMS.getValue(setRL(loc.getPath().replace("_base", "")));
    }

    private void orderArrowsAndRegister(Consumer<IFinishedRecipe> consumer, List<IItemProvider> materials, RegistryObject<ArrowItem>... arrows) {
        IntStream.range(0, arrows.length).forEach(l -> registerArrowRecipes(consumer, arrows[l], materials.get(l)));
    }

    private void registerArrowRecipes(Consumer<IFinishedRecipe> consumer, RegistryObject<ArrowItem> arrow, IItemProvider material) {
        IItemProvider ingredient = arrow.get();
        String path = arrow.getId().getPath();
        ShapedRecipeBuilder.shapedRecipe(ingredient, 8)
                .patternLine("SSS")
                .patternLine("SXS")
                .patternLine("SSS")
                .key('S', Items.ARROW)
                .key('X', material)
                .setGroup("armorplus:ap_arrows")
                .addCriterion("has_arrows", hasItem(Items.ARROW))
                .build(consumer, setRL(path.replace("item_", "").replace("_arrow", "") + "/" + path));
    }

    private void registerColorRecipes(Consumer<IFinishedRecipe> consumer, RegistryObject<Block> bricks, IOptionalNamedTag<Item> color) {
        IItemProvider ingredient = bricks.get();
        String path = bricks.getId().getPath();
        ShapedRecipeBuilder.shapedRecipe(ingredient, 8)
                .patternLine("SSS")
                .patternLine("SXS")
                .patternLine("SSS")
                .key('S', Items.STONE_BRICKS)
                .key('X', color)
                .setGroup("armorplus:colored_stone_bricks")
                .addCriterion("has_stonebricks", hasItem(Items.STONE_BRICKS))
                .addCriterion("has_dye", hasItem(color))
                .build(consumer, setRL("bricks/" + path));
    }

    private void registerStoneBrickRecipes(Consumer<IFinishedRecipe> consumer,
                                           RegistryObject<Block> bricks, RegistryObject<Block> tower,
                                           RegistryObject<Block> corner, RegistryObject<Block> wall) {
        IItemProvider ingredient = bricks.get();
        String path = bricks.getId().getPath();
        //corner
        ShapedRecipeBuilder.shapedRecipe(corner.get(), 4)
                .patternLine("   ")
                .patternLine("S  ")
                .patternLine("SSS")
                .key('S', ingredient)
                .setGroup("armorplus:stone_brick_corner")
                .addCriterion("has_bricks", hasItem(ingredient))
                .build(consumer, setRL("corner/" + corner.getId().getPath()));
        ShapelessRecipeBuilder.shapelessRecipe(ingredient)
                .addIngredient(corner.get())
                .addCriterion("has_bricks", hasItem(ingredient))
                .setGroup("armorplus:stone_bricks")
                .build(consumer, setRL("corner/" + path));
        //wall
        ShapedRecipeBuilder.shapedRecipe(wall.get(), 6)
                .patternLine("   ")
                .patternLine("SSS")
                .patternLine("SSS")
                .key('S', ingredient)
                .setGroup("armorplus:stone_brick_wall")
                .addCriterion("has_bricks", hasItem(ingredient))
                .build(consumer, setRL("wall/" + wall.getId().getPath()));
        ShapelessRecipeBuilder.shapelessRecipe(ingredient)
                .addIngredient(wall.get())
                .addCriterion("has_bricks", hasItem(ingredient))
                .setGroup("armorplus:stone_bricks")
                .build(consumer, setRL("wall/" + path));
        //tower
        ShapedRecipeBuilder.shapedRecipe(tower.get(), 8)
                .patternLine("S S")
                .patternLine("SSS")
                .patternLine("SSS")
                .key('S', ingredient)
                .setGroup("armorplus:stone_brick_tower")
                .addCriterion("has_bricks", hasItem(ingredient))
                .build(consumer, setRL("tower/" + tower.getId().getPath()));
        ShapelessRecipeBuilder.shapelessRecipe(ingredient)
                .addIngredient(tower.get())
                .addCriterion("has_bricks", hasItem(ingredient))
                .setGroup("armorplus:stone_bricks")
                .build(consumer, setRL("tower/" + path));
    }


    @Override
    public String getName() {
        return "Recipes";
    }
}
