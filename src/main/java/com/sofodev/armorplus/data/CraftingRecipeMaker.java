package com.sofodev.armorplus.data;

import com.sofodev.armorplus.data.recipe.Grid;
import com.sofodev.armorplus.data.recipe.GridInput;
import com.sofodev.armorplus.data.recipe.Input;
import com.sofodev.armorplus.data.recipe.Result;
import com.sofodev.armorplus.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.utils.DataUtils.getPath;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.setRL;
import static net.minecraft.item.crafting.Ingredient.*;

public class CraftingRecipeMaker extends RecipeProvider {
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public CraftingRecipeMaker(DataGenerator generatorIn) {
        super(generatorIn);
    }

    public static CraftingRecipeMaker get(DataGenerator generator) {
        return new CraftingRecipeMaker(generator);
    }

    public void buildSword(Consumer<IFinishedRecipe> con, RegistryObject<? extends Item> sword, IItemProvider material, IItemProvider handle) {
        String path = getPath(sword).replace("item_", "").replace("_sword", "").replace("_base", "");
        build(con, Result.build(sword.get(), path + "_swords", path),
                GridInput.build("M  ", "M  ", "S  ", 'S', 'M'), handle, fromItems(material)
        );
    }

    public void buildBattleAxe(Consumer<IFinishedRecipe> con, RegistryObject<? extends Item> battleAxe, IItemProvider material, IItemProvider handle) {
        String path = getPath(battleAxe).replace("item_", "").replace("_battle_axe", "").replace("_base", "");
        build(con, Result.build(battleAxe.get(), path + "_battle_axes", path),
                GridInput.build("M M", "MSM", " S ", 'S', 'M'), handle, fromItems(material)
        );
    }

    public void buildPickaxe(Consumer<IFinishedRecipe> con, RegistryObject<? extends Item> battleAxe, IItemProvider material, IItemProvider handle) {
        String path = getPath(battleAxe).replace("item_", "").replace("_pickaxe", "").replace("_base", "");
        build(con, Result.build(battleAxe.get(), path + "_pickaxes", path),
                GridInput.build("MMM", " S ", " S ", 'S', 'M'), handle, fromItems(material)
        );
    }

    public void buildBow(Consumer<IFinishedRecipe> con, RegistryObject<? extends Item> bow, IItemProvider material) {
        String path = getPath(bow).replace("item_", "").replace("_bow", "").replace("_base", "");
        build(con, Result.build(bow.get(), path + "_bows", path),
                GridInput.build("SM ", "S M", "SM ", 'S', 'M'), Items.STRING, fromItems(material)
        );

        build(con, Result.build(bow.get(), path + "_bows", path).setSuffix("_alt"),
                GridInput.build(" MS", "M S", " MS", 'S', 'M'), Items.STRING, fromItems(material)
        );
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void buildArmorSet(Consumer<IFinishedRecipe> con, String name, IItemProvider material, String itemSuffix) {
        this.buildArmorSet(con, name, material, name, name, "", itemSuffix);
    }

    public void buildArmorSet(Consumer<IFinishedRecipe> con, String name, IItemProvider material, String group, String path, String suffix, String itemSuffix) {
        this.buildArmorSet(con, material,
                getAPItem(name + "_helmet" + itemSuffix.trim()),
                getAPItem(name + "_chestplate" + itemSuffix.trim()),
                getAPItem(name + "_leggings" + itemSuffix.trim()),
                getAPItem(name + "_boots" + itemSuffix.trim()),
                group, path, suffix
        );
    }

    public void buildArmorSet(Consumer<IFinishedRecipe> con, IItemProvider material,
                              IItemProvider head, IItemProvider chest, IItemProvider legs, IItemProvider feet, String group, String path, String suffix) {
        this.buildArmorSet(con, Result.build(head, group, path).setSuffix(suffix), Result.build(chest, group, path).setSuffix(suffix),
                Result.build(legs, group, path).setSuffix(suffix), Result.build(feet, group, path).setSuffix(suffix), material
        );
    }

    public void buildEnhanced(Consumer<IFinishedRecipe> con, RegistryObject<Item> enMaterial, IItemProvider material) {
        this.buildFilling(con, Result.build(enMaterial.get(), "ap_en_mats", "enhanced"), material, Items.GLOWSTONE_DUST);
    }

    /**
     * Creates the recipe json for the boss souls, by combining lesser souls and some representing materials
     *
     * @param con        the builder
     * @param bossSoul   The boss soul that is being crafted
     * @param lesserSoul The lesser soul from that dimension
     * @param mat        a material from the boss soul's dimension
     * @param extra      some extra ingredients from the same dimension
     */
    public void buildSoul(Consumer<IFinishedRecipe> con, RegistryObject<Item> bossSoul,
                          IItemProvider lesserSoul, IItemProvider mat, IItemProvider extra) {
        this.build(con, Result.build(bossSoul.get(), 1, "ap_souls", "soul"),
                GridInput.build("ESE", "SXS", "ESE", 'S', 'X', 'E'),
                lesserSoul, fromItems(mat), fromItems(extra)
        );
    }

    public void buildOrderedArrow(Consumer<IFinishedRecipe> con, List<RegistryObject<ArrowItem>> arrows) {
        IntStream.range(0, arrows.size()).forEach(i -> buildArrow(con, arrows.get(i), Recipes.MATERIALS_ORDERED.get(i)));
    }

    public void buildArrow(Consumer<IFinishedRecipe> con, RegistryObject<ArrowItem> arrow, IItemProvider material) {
        String path = getPath(arrow).replace("item_", "").replace("_arrow", "");
        String group = "ap_arrows";
        this.buildFilling(con, Result.build(arrow.get(), 8, group, path), material, Items.ARROW);
    }

    public void buildColoredBrick(Consumer<IFinishedRecipe> con, RegistryObject<Block> bricks, Tags.IOptionalNamedTag<Item> color) {
        this.buildFilling(con, Result.build(bricks.get(), 8, "colored_stone_bricks", "bricks"), Items.STONE_BRICKS, fromTag(color));
    }

    public void buildStoneBrick(Consumer<IFinishedRecipe> con, RegistryObject<Block> bricks, RegistryObject<Block> tower,
                                RegistryObject<Block> corner, RegistryObject<Block> wall) {
        final String group = "stone_brick";
        final String pathCorner = "corner";
        final String pathWall = "wall";
        final String pathTower = "tower";
        //shaped
        this.build(con, Result.build(wall.get(), 6, getGroup(pathCorner), pathCorner),
                GridInput.build("   ", "S  ", "SSS", 'S'), bricks.get()
        );
        this.build(con, Result.build(wall.get(), 6, getGroup(pathWall), pathWall),
                GridInput.build("   ", "SSS", "SSS", 'S'), bricks.get()
        );
        this.build(con, Result.build(tower.get(), 8, getGroup(pathTower), pathTower),
                GridInput.build("S S", "SSS", "SSS", 'S'), bricks.get()
        );
        //shapeless
        this.build(con, Result.build(bricks.get(), group, pathWall), wall.get());
        this.build(con, Result.build(bricks.get(), group, pathCorner), corner.get());
        this.build(con, Result.build(bricks.get(), group, pathTower), tower.get());
    }

    private String getGroup(String form) {
        return "stone_brick_" + form;
    }

    //=========\\
    // Generic \\
    //=========\\

    public void buildStorage(Consumer<IFinishedRecipe> con, IItemProvider result, IItemProvider item) {
        this.build(con, Result.build(result, 1, "storage", "storage"), GridInput.buildSquare(Grid.Size.BIG, 'X'), item);
        this.build(con, Result.build(item, 9, "storage", "storage").setSuffix("_to_item"), result);
    }

    public void buildFilling(Consumer<IFinishedRecipe> con, IItemProvider result, IItemProvider center, IItemProvider filler) {
        this.buildFilling(con, Result.build(result), center, fromItems(filler));
    }

    public void buildFilling(Consumer<IFinishedRecipe> con, IItemProvider result, int count, IItemProvider center, IItemProvider filler) {
        this.buildFilling(con, Result.build(result, count), center, fromItems(filler));
    }

    public void buildFilling(Consumer<IFinishedRecipe> con, Result result, IItemProvider center, IItemProvider filler) {
        this.buildFilling(con, result, center, fromItems(filler));
    }

    public void buildFilling(Consumer<IFinishedRecipe> con, Result result, IItemProvider center, Ingredient filler) {
        this.build(con, result, GridInput.build("SSS", "SXS", "SSS", 'X', 'S'), center, filler);
    }

    //=======\\
    // Armor \\
    //=======\\

    public void buildArmorSet(Consumer<IFinishedRecipe> con, Result head, Result chest, Result legs, Result feet, IItemProvider material) {
        this.buildHelmet(con, head, material);
        this.buildChestplate(con, chest, material);
        this.buildLeggings(con, legs, material);
        this.buildBoots(con, feet, material);
    }

    public void buildHelmet(Consumer<IFinishedRecipe> con, Result result, IItemProvider material) {
        this.build(con, result, GridInput.build("SSS", "S S", 'S'), material);
        this.build(con, result.setSuffix("_alt"), GridInput.build("   ", "SSS", "S S", 'S'), material);
    }

    public void buildChestplate(Consumer<IFinishedRecipe> con, Result result, IItemProvider material) {
        this.build(con, result, GridInput.build("S S", "SSS", "SSS", 'S'), material);
    }

    public void buildLeggings(Consumer<IFinishedRecipe> con, Result result, IItemProvider material) {
        this.build(con, result, GridInput.build("SSS", "S S", "S S", 'S'), material);
    }

    public void buildBoots(Consumer<IFinishedRecipe> con, Result result, IItemProvider material) {
        this.build(con, result, GridInput.build("S S", "S S", 'S'), material);
        this.build(con, result.setSuffix("_alt"), GridInput.build("   ", "S S", "S S", 'S'), material);
    }

    //#########################\\
    //  HELPER METHOD - SHAPED \\
    //#########################\\

    /**
     * <h3>Creates a recipe with the specified settings:</h3>
     * <p>
     * {@link Result} <b>Result</b> contains information such as:
     * <ul>
     * <li>{@link Result#getObject()} - The IItemProvider result item.</li>
     * <li>{@link Result#getCount()} - The amount of items the recipe will give.</li>
     * <li>{@link Result#getGroup()} - The recipe group that the recipe will be added to when viewing in the recipe book.</li>
     * <li>{@link Result#getPath()} - The path of the recipe in addition to recipes/crafting/shaped/ -> recipes/crafting/shaped/{@literal <path>}/</li>
     * </ul>
     * <p>
     * {@link GridInput} <b>Layout</b> is basically a 3x3 recipe grid:
     * <blockquote>
     *     "123"<br>
     *     "456"<br>
     *     "789"
     * </blockquote>
     * Additionally for every character used, you will need to define it as an extra param.
     * <p>
     * The <b>minimum</b> number of chars is <b>1</b> and the <b>maximum</b> is <b>9</b>.
     * </p>
     * <p> <b>Example:</b> <blockquote>GridInput.build(" X ", " X ", " S ", 'X', 'S');</blockquote></p>
     * <p> This will create a grid with the given layout and will assign chars 'X', and 'S'.</p>
     * <b> The order is very important!</b>
     *
     * @param consumer   The consumer that will act as our builder for creating the recipes.
     * @param result     The resulting item stack.
     * @param layout     The layout of the recipe.
     * @param mainInput  The 1st item ingredient.
     * @param additional The 2nd-9th item ingredients. (Not used if no char is specified)
     * @since 16.2.0
     */
    public void build(Consumer<IFinishedRecipe> consumer, Result result, GridInput layout, IItemProvider mainInput, Ingredient... additional) {
        String path = getPath(result.getObject());
        Grid grid = layout.getGrid();
        Input input = layout.getSimpleInput();
        this.logGrid(result, path, grid);
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shapedRecipe(result.getObject(), result.getCount());
        builder.patternLine(grid.getFirstRow());
        builder.patternLine(grid.getSecondRow());
        builder.patternLine(grid.getThirdRow());
        builder.key(input.getA(), mainInput);
        IntStream.range(0, additional.length).forEach(i -> this.addIngredients(builder, input.getCharList().get(i + 1), additional[i]));
        builder.setGroup(Utils.setLocation(result.getGroup().orElse(path)));
        builder.addCriterion("has_req", hasItem(mainInput));
        builder.build(consumer, setRL("crafting/shaped/" + result.getPath().orElse("").trim() + (result.getPrefix() + path + result.getSuffix())));
    }

    /**
     * @see CraftingRecipeMaker#build(Consumer, Result, GridInput, IItemProvider, Ingredient...)
     * @since 16.2.0
     */
    public void build(Consumer<IFinishedRecipe> con, Result result, GridInput layout, IItemProvider input) {
        this.build(con, result, layout, input, EMPTY);
    }

    /**
     * @param consumer The consumer that will act as our builder for creating the recipes.
     * @param result   The resulting item stack.
     * @param layout   The layout of the recipe.
     * @param inputs   The 1st-9th item ingredients. (Not used if no char is specified)
     * @see CraftingRecipeMaker#build(Consumer, Result, GridInput, IItemProvider, Ingredient...)
     * @since 16.2.0
     */
    public void build(Consumer<IFinishedRecipe> consumer, Result result, GridInput layout, IItemProvider... inputs) {
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shapedRecipe(result.getObject(), result.getCount());
        String path = getPath(result.getObject());
        Grid grid = layout.getGrid();
        Input input = layout.getSimpleInput();
        this.logGrid(result, path, grid);
        builder.patternLine(grid.getFirstRow());
        builder.patternLine(grid.getSecondRow());
        builder.patternLine(grid.getThirdRow());
        IntStream.range(0, inputs.length).forEach(i -> this.addIngredients(builder, input.getCharList().get(i), inputs[i]));
        boolean hasGroup = result.getGroup().isPresent();
        if (hasGroup) builder.setGroup(Utils.setLocation(result.getGroup().get()));
        builder.addCriterion("has_req", hasItem(inputs[0]));
        builder.build(consumer, setRL("crafting/shaped/" + result.getPath().orElse("").trim() + (result.getPrefix() + path + result.getSuffix())));
    }

    private void logGrid(Result result, String path, Grid grid) {
        LOGGER.info("Item: {}, count: {}", path, result.getCount());
        LOGGER.info("Building Pattern: [{}]", grid.getFirstRow());
        LOGGER.info("Building Pattern: [{}]", grid.getSecondRow());
        LOGGER.info("Building Pattern: [{}]", grid.getThirdRow());
    }

    private void addIngredients(ShapedRecipeBuilder builder, char character, Ingredient input) {
        if (!input.isSimple()) {
            builder.key(character, input);
        }
    }

    private void addIngredients(ShapedRecipeBuilder builder, char character, IItemProvider input) {
        builder.key(character, input);
    }

    //##############################\\
    //  HELPER METHODS - SHAPELESS  \\
    //##############################\\

    /**
     * @see CraftingRecipeMaker#build(Consumer, Result, IItemProvider, Ingredient...)
     */
    public void build(Consumer<IFinishedRecipe> con, Result result, IItemProvider item) {
        this.build(con, result, item, EMPTY);
    }

    public void build(Consumer<IFinishedRecipe> con, Result result, IItemProvider inputA, Ingredient... inputs) {
        String path = getPath(result.getObject());
        LOGGER.info("Item: {}, count: {}", path, result.getCount());
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapelessRecipe(result.getObject(), result.getCount());
        builder.addIngredient(inputA);
        Arrays.stream(inputs).forEach(ingredient -> this.addIngredients(builder, ingredient));
        boolean hasGroup = result.getGroup().isPresent();
        if (hasGroup) builder.setGroup(Utils.setLocation(result.getGroup().get()));
        builder.addCriterion("has_req", hasItem(inputA));
        builder.build(con, setRL("crafting/shapeless/" + result.getPath().orElse("").trim() + (result.getPrefix() + path + result.getSuffix())));
    }

    public void build(Consumer<IFinishedRecipe> con, Result result, IItemProvider... item) {
        String path = getPath(result.getObject());
        LOGGER.info("Item: {}, count: {}", path, result.getCount());
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapelessRecipe(result.getObject(), result.getCount());
        Arrays.stream(item).forEach(ingredient -> this.addIngredients(builder, ingredient));
        boolean hasGroup = result.getGroup().isPresent();
        if (hasGroup) builder.setGroup(Utils.setLocation(result.getGroup().get()));
        builder.addCriterion("has_req", hasItem(item[0]));
        builder.build(con, setRL("crafting/shapeless/" + result.getPath().orElse("").trim() + (result.getPrefix() + path + result.getSuffix())));
    }

    private void addIngredients(ShapelessRecipeBuilder builder, Ingredient input) {
        if (!input.isSimple()) {
            builder.addIngredient(input);
        }
    }

    private void addIngredients(ShapelessRecipeBuilder builder, IItemProvider input) {
        builder.addIngredient(input);
    }

}
