package com.sofodev.armorplus.data;

import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.Tags.IOptionalNamedTag;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fml.RegistryObject;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.utils.Utils.setRL;
import static net.minecraftforge.common.Tags.Items.*;

public class Recipes extends RecipeProvider implements IDataProvider, IConditionBuilder {

    private static final List<IOptionalNamedTag<Item>> BRICK_COLORS = Stream.of(
            DYES_BLACK, DYES_BLUE, DYES_GREEN, DYES_PURPLE, DYES_RED, DYES_WHITE, DYES_YELLOW, DYES_ORANGE
    ).collect(Collectors.toList());

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        int l = STONE_BRICKS.length;
        IntStream.range(0, CASTLE_BLOCKS.length).forEach(i -> this.registerStoneBrickRecipes(consumer, CASTLE_BLOCKS[i],
                CASTLE_BLOCK_TOWERS[i], CASTLE_BLOCK_CORNERS[i], CASTLE_BLOCK_WALLS[i]));
        IntStream.range(0, l).forEach(i -> this.registerStoneBrickRecipes(consumer, STONE_BRICKS[i],
                STONE_BRICK_TOWERS[i], STONE_BRICK_CORNERS[i], STONE_BRICK_WALLS[i]));
        IntStream.range(0, l).forEach(i -> this.registerColorRecipes(consumer, STONE_BRICKS[i], BRICK_COLORS.get(i)));
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
