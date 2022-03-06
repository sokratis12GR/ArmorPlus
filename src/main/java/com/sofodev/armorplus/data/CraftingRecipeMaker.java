package com.sofodev.armorplus.data;

import com.sofodev.armorplus.data.recipe.Grid;
import com.sofodev.armorplus.data.recipe.GridInput;
import com.sofodev.armorplus.data.recipe.Input;
import com.sofodev.armorplus.data.recipe.Result;
import com.sofodev.armorplus.utils.Utils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.utils.DataUtils.getPath;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.setRL;
import static net.minecraft.world.item.crafting.Ingredient.EMPTY;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class CraftingRecipeMaker extends RecipeProvider {

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public CraftingRecipeMaker(DataGenerator generatorIn) {
        super(generatorIn);
    }

    public static CraftingRecipeMaker get(DataGenerator generator) {
        return new CraftingRecipeMaker(generator);
    }

    public void buildSword(Consumer<FinishedRecipe> con, RegistryObject<? extends Item> sword, ItemLike material, ItemLike handle) {
        String path = getPath(sword).replace("item_", "").replace("_sword", "").replace("_base", "");
        build(con, Result.build(sword.get(), path + "_swords", path),
                GridInput.build("M  ", "M  ", "S  ", 'S', 'M'), handle, of(material)
        );
    }

    public void buildSword(Consumer<FinishedRecipe> con, RegistryObject<? extends Item> sword, ItemLike material, ItemLike core, ItemLike handle) {
        String path = getPath(sword).replace("item_", "").replace("_sword", "").replace("_base", "");
        build(con, Result.build(sword.get(), path + "_swords", path),
                GridInput.build("M  ", "C  ", "S  ", 'S', 'M', 'C'), handle, of(material), of(core)
        );
    }

    public void buildSword(Consumer<FinishedRecipe> con, RegistryObject<? extends Item> sword, ItemLike material,
                           ItemLike coreA, ItemLike coreB, ItemLike coreC, ItemLike handle) {
        String path = getPath(sword).replace("item_", "").replace("_sword", "").replace("_base", "");
        build(con, Result.build(sword.get(), path + "_swords", path),
                GridInput.build(" M ", "ABC", " S ", 'S', 'M', 'A', 'B', 'C'), handle, of(material), of(coreA), of(coreB), of(coreC)
        );
    }

    public void buildBattleAxe(Consumer<FinishedRecipe> con, RegistryObject<? extends Item> battleAxe, ItemLike material, ItemLike handle) {
        String path = getPath(battleAxe).replace("item_", "").replace("_battle_axe", "").replace("_base", "");
        build(con, Result.build(battleAxe.get(), path + "_battle_axes", path),
                GridInput.build("M M", "MSM", " S ", 'S', 'M'), handle, of(material)
        );
    }

    public void buildBattleAxe(Consumer<FinishedRecipe> con, RegistryObject<? extends Item> battleAxe, ItemLike material, ItemLike core, ItemLike handle) {
        String path = getPath(battleAxe).replace("item_", "").replace("_battle_axe", "").replace("_base", "");
        build(con, Result.build(battleAxe.get(), path + "_battle_axes", path),
                GridInput.build("M M", "MCM", " S ", 'S', 'M', 'C'), handle, of(material), Ingredient.of(core)
        );
    }

    public void buildBattleAxe(Consumer<FinishedRecipe> con, RegistryObject<? extends Item> battleAxe, ItemLike material,
                               ItemLike coreA, ItemLike coreB, ItemLike coreC, ItemLike handle) {
        String path = getPath(battleAxe).replace("item_", "").replace("_battle_axe", "").replace("_base", "");
        build(con, Result.build(battleAxe.get(), path + "_battle_axes", path),
                GridInput.build("M M", "ABC", " S ", 'S', 'M', 'A', 'B', 'C'), handle, of(material), of(coreA), of(coreB), of(coreC)
        );
    }

    public void buildPickaxe(Consumer<FinishedRecipe> con, RegistryObject<? extends Item> battleAxe, ItemLike material, ItemLike handle) {
        String path = getPath(battleAxe).replace("item_", "").replace("_pickaxe", "").replace("_base", "");
        build(con, Result.build(battleAxe.get(), path + "_pickaxes", path),
                GridInput.build("MMM", " S ", " S ", 'S', 'M'), handle, of(material)
        );
    }

    public void buildPickaxe(Consumer<FinishedRecipe> con, RegistryObject<? extends Item> battleAxe, ItemLike material, ItemLike core, ItemLike handle) {
        String path = getPath(battleAxe).replace("item_", "").replace("_pickaxe", "").replace("_base", "");
        build(con, Result.build(battleAxe.get(), path + "_pickaxes", path),
                GridInput.build("MMM", " C ", " S ", 'S', 'M', 'C'), handle, of(material), of(core)
        );
    }

    public void buildPickaxe(Consumer<FinishedRecipe> con, RegistryObject<? extends Item> battleAxe, ItemLike material,
                             ItemLike coreA, ItemLike coreB, ItemLike coreC, ItemLike handle) {
        String path = getPath(battleAxe).replace("item_", "").replace("_pickaxe", "").replace("_base", "");
        build(con, Result.build(battleAxe.get(), path + "_pickaxes", path),
                GridInput.build("AMC", " B ", " S ", 'S', 'M', 'A', 'B', 'C'), handle, of(material), of(coreA), of(coreB), of(coreC)
        );
    }

    public void buildBow(Consumer<FinishedRecipe> con, RegistryObject<? extends Item> bow, ItemLike material) {
        String path = getPath(bow).replace("item_", "").replace("_bow", "").replace("_base", "");
        build(con, Result.build(bow.get(), path + "_bows", path),
                GridInput.build("SM ", "S M", "SM ", 'S', 'M'), Items.STRING, of(material)
        );

        build(con, Result.build(bow.get(), path + "_bows", path).setSuffix("_alt"),
                GridInput.build(" MS", "M S", " MS", 'S', 'M'), Items.STRING, of(material)
        );
    }

    public void buildBow(Consumer<FinishedRecipe> con, RegistryObject<? extends Item> bow, ItemLike core, ItemLike material) {
        String path = getPath(bow).replace("item_", "").replace("_bow", "").replace("_base", "");
        build(con, Result.build(bow.get(), path + "_bows", path),
                GridInput.build("SM ", "S C", "SM ", 'S', 'M', 'C'), Items.STRING, of(material), of(core)
        );

        build(con, Result.build(bow.get(), path + "_bows", path).setSuffix("_alt"),
                GridInput.build(" MS", "C S", " MS", 'S', 'M', 'C'), Items.STRING, of(material), of(core)
        );
    }

    public void buildBow(Consumer<FinishedRecipe> con, RegistryObject<? extends Item> bow, ItemLike material, ItemLike coreA, ItemLike coreB, ItemLike coreC) {
        String path = getPath(bow).replace("item_", "").replace("_bow", "").replace("_base", "");
        build(con, Result.build(bow.get(), path + "_bows", path),
                GridInput.build("SAM", "SBM", "SCM", 'S', 'M', 'A', 'B', 'C'), Items.STRING, of(material), of(coreA), of(coreB), of(coreC)
        );

        build(con, Result.build(bow.get(), path + "_bows", path).setSuffix("_alt"),
                GridInput.build("MAS", "MBS", "MCS", 'S', 'M', 'A', 'B', 'C'), Items.STRING, of(material), of(coreA), of(coreB), of(coreC)
        );
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void buildArmorSet(Consumer<FinishedRecipe> con, String name, ItemLike material, String itemSuffix) {
        this.buildArmorSet(con, name, material, name, name, "", itemSuffix);
    }

    public void buildArmorSet(Consumer<FinishedRecipe> con, String name, ItemLike material, String group, String path, String suffix, String itemSuffix) {
        this.buildArmorSet(con, material,
                getAPItem(name + "_helmet" + itemSuffix.trim()),
                getAPItem(name + "_chestplate" + itemSuffix.trim()),
                getAPItem(name + "_leggings" + itemSuffix.trim()),
                getAPItem(name + "_boots" + itemSuffix.trim()),
                group, path, suffix
        );
    }

    public void buildArmorSet(Consumer<FinishedRecipe> con, ItemLike material,
                              ItemLike head, ItemLike chest, ItemLike legs, ItemLike feet, String group, String path, String suffix) {
        this.buildArmorSet(con, Result.build(head, group, path).setSuffix(suffix), Result.build(chest, group, path).setSuffix(suffix),
                Result.build(legs, group, path).setSuffix(suffix), Result.build(feet, group, path).setSuffix(suffix), material
        );
    }

    //START SINGLE-CORES
    public void buildArmorSet(Consumer<FinishedRecipe> con, String name, ItemLike material, ItemLike core, String itemSuffix) {
        this.buildArmorSet(con, name, material, core, name, name, "", itemSuffix);
    }

    public void buildArmorSet(Consumer<FinishedRecipe> con, String name, ItemLike material, ItemLike core, String group, String path, String suffix, String itemSuffix) {
        this.buildArmorSet(con, material, core,
                getAPItem(name + "_helmet" + itemSuffix.trim()),
                getAPItem(name + "_chestplate" + itemSuffix.trim()),
                getAPItem(name + "_leggings" + itemSuffix.trim()),
                getAPItem(name + "_boots" + itemSuffix.trim()),
                group, path, suffix
        );
    }

    public void buildArmorSet(Consumer<FinishedRecipe> con, ItemLike material, ItemLike core,
                              ItemLike head, ItemLike chest, ItemLike legs, ItemLike feet, String group, String path, String suffix) {
        this.buildArmorSet(con, Result.build(head, group, path).setSuffix(suffix), Result.build(chest, group, path).setSuffix(suffix),
                Result.build(legs, group, path).setSuffix(suffix), Result.build(feet, group, path).setSuffix(suffix), material, core
        );
    }
    //END SINGLE-CORES

    //START TRI-CORES
    public void buildComplexArmorSet(Consumer<FinishedRecipe> con, String name, ItemLike material,
                                     String left, String middle, String right, String itemSuffix) {
        this.buildArmorSet(con, name, material,
                Utils.getAPItem(left + "_helmet_base"), Utils.getAPItem(middle + "_helmet_base"), Utils.getAPItem(right + "_helmet_base"),
                Utils.getAPItem(left + "_chestplate_base"), Utils.getAPItem(middle + "_chestplate_base"), Utils.getAPItem(right + "_chestplate_base"),
                Utils.getAPItem(left + "_leggings_base"), Utils.getAPItem(middle + "_leggings_base"), Utils.getAPItem(right + "_leggings_base"),
                Utils.getAPItem(left + "_boots_base"), Utils.getAPItem(middle + "_boots_base"), Utils.getAPItem(right + "_boots_base"),
                name, name, "", itemSuffix);
    }

    public void buildArmorSet(Consumer<FinishedRecipe> con, String name, ItemLike material,
                              ItemLike coreHA, ItemLike coreHB, ItemLike coreHC,
                              ItemLike coreCA, ItemLike coreCB, ItemLike coreCC,
                              ItemLike coreLA, ItemLike coreLB, ItemLike coreLC,
                              ItemLike coreBA, ItemLike coreBB, ItemLike coreBC,
                              String group, String path, String suffix, String itemSuffix) {
        this.buildArmorSet(con, material,
                coreHA, coreHB, coreHC,
                coreCA, coreCB, coreCC,
                coreLA, coreLB, coreLC,
                coreBA, coreBB, coreBC,
                getAPItem(name + "_helmet" + itemSuffix.trim()),
                getAPItem(name + "_chestplate" + itemSuffix.trim()),
                getAPItem(name + "_leggings" + itemSuffix.trim()),
                getAPItem(name + "_boots" + itemSuffix.trim()),
                group, path, suffix
        );
    }

    public void buildArmorSet(Consumer<FinishedRecipe> con, ItemLike material,
                              ItemLike coreHA, ItemLike coreHB, ItemLike coreHC,
                              ItemLike coreCA, ItemLike coreCB, ItemLike coreCC,
                              ItemLike coreLA, ItemLike coreLB, ItemLike coreLC,
                              ItemLike coreBA, ItemLike coreBB, ItemLike coreBC,
                              ItemLike head, ItemLike chest, ItemLike legs, ItemLike feet, String group, String path, String suffix) {
        this.buildArmorSet(con, Result.build(head, group, path).setSuffix(suffix), Result.build(chest, group, path).setSuffix(suffix),
                Result.build(legs, group, path).setSuffix(suffix), Result.build(feet, group, path).setSuffix(suffix), material,
                coreHA, coreHB, coreHC,
                coreCA, coreCB, coreCC,
                coreLA, coreLB, coreLC,
                coreBA, coreBB, coreBC
        );
    }

    //END - TRI-CORES
    public void buildEnhanced(Consumer<FinishedRecipe> con, RegistryObject<Item> enMaterial, ItemLike material) {
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
    public void buildSoul(Consumer<FinishedRecipe> con, RegistryObject<Item> bossSoul,
                          ItemLike lesserSoul, ItemLike mat, ItemLike extra) {
        this.build(con, Result.build(bossSoul.get(), 1, "ap_souls", "soul"),
                GridInput.build("ESE", "SXS", "ESE", 'S', 'X', 'E'),
                lesserSoul, of(mat), of(extra)
        );
    }

    public void buildOrderedArrow(Consumer<FinishedRecipe> con, List<RegistryObject<ArrowItem>> arrows) {
        IntStream.range(0, arrows.size()).forEach(i -> buildArrow(con, arrows.get(i), Recipes.MATERIALS_ORDERED.get(i)));
    }

    public void buildArrow(Consumer<FinishedRecipe> con, RegistryObject<ArrowItem> arrow, ItemLike material) {
        String path = getPath(arrow).replace("item_", "").replace("_arrow", "");
        String group = "ap_arrows";
        this.buildFilling(con, Result.build(arrow.get(), 8, group, path), material, Items.ARROW);
    }

    public void buildColoredBrick(Consumer<FinishedRecipe> con, RegistryObject<Block> bricks, Tags.IOptionalNamedTag<Item> color) {
        this.buildFilling(con, Result.build(bricks.get(), 8, "colored_stone_bricks", "bricks"), Items.STONE_BRICKS, of(color));
    }

    public void buildStoneBrick(Consumer<FinishedRecipe> con, RegistryObject<Block> bricks, RegistryObject<Block> tower,
                                RegistryObject<Block> corner, RegistryObject<Block> wall, RegistryObject<Block> stairs, RegistryObject<Block> slab) {
        final String group = "stone_brick";
        final String pathWall = "wall";
        final String pathTower = "tower";
        final String pathCorner = "corner";
        final String pathStairs = "stairs";
        final String pathSlab = "slab";
        //shaped
        this.build(con, Result.build(corner.get(), 4, getGroup(pathCorner), pathCorner),
                GridInput.build("   ", "S  ", "SSS", 'S'), bricks.get()
        );
        this.build(con, Result.build(wall.get(), 6, getGroup(pathWall), pathWall),
                GridInput.build("   ", "SSS", "SSS", 'S'), bricks.get()
        );
        this.build(con, Result.build(tower.get(), 8, getGroup(pathTower), pathTower),
                GridInput.build("S S", "SSS", "SSS", 'S'), bricks.get()
        );
        this.build(con, Result.build(stairs.get(), 4, getGroup(pathStairs), pathStairs),
                GridInput.build("S  ", "SS ", "SSS", 'S'), bricks.get()
        );
        this.build(con, Result.build(slab.get(), 6, getGroup(pathSlab), pathSlab),
                GridInput.build("   ", "SSS", "   ", 'S'), bricks.get()
        );
    }

    private String getGroup(String form) {
        return "stone_brick_" + form;
    }

    //=========\\
    // Generic \\
    //=========\\

    public void buildStorage(Consumer<FinishedRecipe> con, ItemLike result, ItemLike item) {
        this.build(con, Result.build(result, 1, "storage", "storage"), GridInput.buildBigSquare('X'), item);
        this.build(con, Result.build(item, 9, "storage", "storage").setSuffix("_to_item"), result);
    }

    public void buildFilling(Consumer<FinishedRecipe> con, ItemLike result, ItemLike center, ItemLike filler) {
        this.buildFilling(con, Result.build(result), center, of(filler));
    }

    public void buildFilling(Consumer<FinishedRecipe> con, ItemLike result, int count, ItemLike center, ItemLike filler) {
        this.buildFilling(con, Result.build(result, count), center, of(filler));
    }

    public void buildFilling(Consumer<FinishedRecipe> con, Result result, ItemLike center, ItemLike filler) {
        this.buildFilling(con, result, center, of(filler));
    }

    public void buildFilling(Consumer<FinishedRecipe> con, Result result, ItemLike center, Ingredient filler) {
        this.build(con, result, GridInput.build("SSS", "SXS", "SSS", 'X', 'S'), center, filler);
    }

    //=======\\
    // Armor \\
    //=======\\

    public void buildArmorSet(Consumer<FinishedRecipe> con, Result head, Result chest, Result legs, Result feet, ItemLike material) {
        this.buildHelmet(con, head, material);
        this.buildChestplate(con, chest, material);
        this.buildLeggings(con, legs, material);
        this.buildBoots(con, feet, material);
    }

    public void buildHelmet(Consumer<FinishedRecipe> con, Result result, ItemLike material) {
        this.build(con, result, GridInput.build("SSS", "S S", 'S'), material);
        this.build(con, result.setSuffix("_alt"), GridInput.build("   ", "SSS", "S S", 'S'), material);
    }

    public void buildChestplate(Consumer<FinishedRecipe> con, Result result, ItemLike material) {
        this.build(con, result, GridInput.build("S S", "SSS", "SSS", 'S'), material);
    }

    public void buildLeggings(Consumer<FinishedRecipe> con, Result result, ItemLike material) {
        this.build(con, result, GridInput.build("SSS", "S S", "S S", 'S'), material);
    }

    public void buildBoots(Consumer<FinishedRecipe> con, Result result, ItemLike material) {
        this.build(con, result, GridInput.build("S S", "S S", 'S'), material);
        this.build(con, result.setSuffix("_alt"), GridInput.build("   ", "S S", "S S", 'S'), material);
    }

    //START SINGLE-CORES
    public void buildArmorSet(Consumer<FinishedRecipe> con, Result head, Result chest, Result legs, Result feet, ItemLike material, ItemLike core) {
        this.buildHelmet(con, head, material, core);
        this.buildChestplate(con, chest, material, core);
        this.buildLeggings(con, legs, material, core);
        this.buildBoots(con, feet, material, core);
    }

    public void buildHelmet(Consumer<FinishedRecipe> con, Result result, ItemLike material, ItemLike core) {
        this.build(con, result, GridInput.build("SCS", "S S", 'S', 'C'), material, of(core));
        this.build(con, result.setSuffix("_alt"), GridInput.build("   ", "SCS", "S S", 'S', 'C'), material, of(core));
    }

    public void buildChestplate(Consumer<FinishedRecipe> con, Result result, ItemLike material, ItemLike core) {
        this.build(con, result, GridInput.build("S S", "SCS", "SSS", 'S', 'C'), material, of(core));
    }

    public void buildLeggings(Consumer<FinishedRecipe> con, Result result, ItemLike material, ItemLike core) {
        this.build(con, result, GridInput.build("SCS", "S S", "S S", 'S', 'C'), material, of(core));
    }

    public void buildBoots(Consumer<FinishedRecipe> con, Result result, ItemLike material, ItemLike core) {
        this.build(con, result, GridInput.build("S S", "SCS", 'S', 'C'), material, of(core));
        this.build(con, result.setSuffix("_alt"), GridInput.build("   ", "S S", "SCS", 'S', 'C'), material, of(core));
    }
    //END SINGLE-CORES

    //START TRI-CORES
    public void buildArmorSet(Consumer<FinishedRecipe> con, Result head, Result chest, Result legs, Result feet, ItemLike material,
                              ItemLike coreHA, ItemLike coreHB, ItemLike coreHC,
                              ItemLike coreCA, ItemLike coreCB, ItemLike coreCC,
                              ItemLike coreLA, ItemLike coreLB, ItemLike coreLC,
                              ItemLike coreBA, ItemLike coreBB, ItemLike coreBC
    ) {
        this.buildHelmet(con, head, material, coreHA, coreHB, coreHC);
        this.buildChestplate(con, chest, material, coreCA, coreCB, coreCC);
        this.buildLeggings(con, legs, material, coreLA, coreLB, coreLC);
        this.buildBoots(con, feet, material, coreBA, coreBB, coreBC);
    }

    public void buildHelmet(Consumer<FinishedRecipe> con, Result result, ItemLike material, ItemLike coreHA, ItemLike coreHB, ItemLike coreHC) {
        this.build(con, result, GridInput.build("ABC", "S S", 'S', 'A', 'B', 'C'), material, of(coreHA), of(coreHB), of(coreHC));
        this.build(con, result.setSuffix("_alt"), GridInput.build("   ", "ABC", "S S", 'S', 'A', 'B', 'C'), material, of(coreHA), of(coreHB), of(coreHC));
    }

    public void buildChestplate(Consumer<FinishedRecipe> con, Result result, ItemLike material, ItemLike coreCA, ItemLike coreCB, ItemLike coreCC) {
        this.build(con, result, GridInput.build("A C", "SBS", "SSS", 'S', 'A', 'B', 'C'), material, of(coreCA), of(coreCB), of(coreCC));
    }

    public void buildLeggings(Consumer<FinishedRecipe> con, Result result, ItemLike material, ItemLike coreLA, ItemLike coreLB, ItemLike coreLC) {
        this.build(con, result, GridInput.build("ABC", "S S", "S S", 'S', 'A', 'B', 'C'), material, of(coreLA), of(coreLB), of(coreLC));
    }

    public void buildBoots(Consumer<FinishedRecipe> con, Result result, ItemLike material, ItemLike coreBA, ItemLike coreBB, ItemLike coreBC) {
        this.build(con, result, GridInput.build("S S", "ABC", 'S', 'A', 'B', 'C'), material, of(coreBA), of(coreBB), of(coreBC));
        this.build(con, result.setSuffix("_alt"), GridInput.build("   ", "S S", "ABC", 'S', 'A', 'B', 'C'), material, of(coreBA), of(coreBB), of(coreBC));
    }
    //END TRI-CORES

    //#########################\\
    //  HELPER METHOD - SHAPED \\
    //#########################\\

    /**
     * <h3>Creates a recipe with the specified settings:</h3>
     * <p>
     * {@link Result} <b>Result</b> contains information such as:
     * <ul>
     * <li>{@link Result#getObject()} - The ItemLike result item.</li>
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
    public void build(Consumer<FinishedRecipe> consumer, Result result, GridInput layout, ItemLike mainInput, Ingredient... additional) {
        HashMap<GridInput, List<ItemLike>> arrangedGrid = new HashMap<>();
        String path = getPath(result.getObject());
        Grid grid = layout.getGrid();
        Input input = layout.getSimpleInput();
        this.logGrid(result, path, grid);
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(result.getObject(), result.getCount());
        builder.pattern(grid.getFirstRow());
        builder.pattern(grid.getSecondRow());
        builder.pattern(grid.getThirdRow());
        builder.define(input.getA(), mainInput);
        IntStream.range(0, additional.length).forEach(i -> this.addIngredients(builder, input.getCharList().get(i + 1), additional[i]));
        builder.group(Utils.setLocation(result.getGroup().orElse(path)));
        builder.unlockedBy("has_req", has(mainInput));
        builder.save(consumer, setRL("crafting/shaped/" + result.getPath().orElse("").trim() + (result.getPrefix() + path + result.getSuffix())));
    }

    /**
     * @see CraftingRecipeMaker#build(Consumer, Result, GridInput, ItemLike, Ingredient...)
     * @since 16.2.0
     */
    public void build(Consumer<FinishedRecipe> con, Result result, GridInput layout, ItemLike input) {
        this.build(con, result, layout, input, EMPTY);
    }

    /**
     * @param consumer The consumer that will act as our builder for creating the recipes.
     * @param result   The resulting item stack.
     * @param layout   The layout of the recipe.
     * @param inputs   The 1st-9th item ingredients. (Not used if no char is specified)
     * @see CraftingRecipeMaker#build(Consumer, Result, GridInput, ItemLike, Ingredient...)
     * @since 16.2.0
     */
    public void build(Consumer<FinishedRecipe> consumer, Result result, GridInput layout, ItemLike... inputs) {
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(result.getObject(), result.getCount());
        String path = getPath(result.getObject());
        Grid grid = layout.getGrid();
        Input input = layout.getSimpleInput();
        this.logGrid(result, path, grid);
        builder.pattern(grid.getFirstRow());
        builder.pattern(grid.getSecondRow());
        builder.pattern(grid.getThirdRow());
        IntStream.range(0, inputs.length).forEach(i -> this.addIngredients(builder, input.getCharList().get(i), inputs[i]));
        boolean hasGroup = result.getGroup().isPresent();
        if (hasGroup) builder.group(Utils.setLocation(result.getGroup().get()));
        builder.unlockedBy("has_req", has(inputs[0]));
        builder.save(consumer, setRL("crafting/shaped/" + result.getPath().orElse("").trim() + (result.getPrefix() + path + result.getSuffix())));
    }

    private void logGrid(Result result, String path, Grid grid) {
        LOGGER.info("Item: {}, count: {}", path, result.getCount());
        LOGGER.info("Building Pattern: [{}]", grid.getFirstRow());
        LOGGER.info("Building Pattern: [{}]", grid.getSecondRow());
        LOGGER.info("Building Pattern: [{}]", grid.getThirdRow());
    }

    private void addIngredients(ShapedRecipeBuilder builder, char character, Ingredient input) {
        if (!input.isSimple()) {
            builder.define(character, input);
        }
    }

    private void addIngredients(ShapedRecipeBuilder builder, char character, ItemLike input) {
        builder.define(character, input);
    }

    //##############################\\
    //  HELPER METHODS - SHAPELESS  \\
    //##############################\\

    /**
     * @see CraftingRecipeMaker#build(Consumer, Result, ItemLike, Ingredient...)
     */
    public void build(Consumer<FinishedRecipe> con, Result result, ItemLike item) {
        this.build(con, result, item, EMPTY);
    }

    public void build(Consumer<FinishedRecipe> con, Result result, ItemLike inputA, Ingredient... inputs) {
        String path = getPath(result.getObject());
        LOGGER.info("Item: {}, count: {}", path, result.getCount());
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(result.getObject(), result.getCount());
        builder.requires(inputA);
        Arrays.stream(inputs).forEach(ingredient -> this.addIngredients(builder, ingredient));
        boolean hasGroup = result.getGroup().isPresent();
        if (hasGroup) builder.group(Utils.setLocation(result.getGroup().get()));
        builder.unlockedBy("has_req", has(inputA));
        builder.save(con, setRL("crafting/shapeless/" + result.getPath().orElse("").trim() + (result.getPrefix() + path + result.getSuffix())));
    }

    public void build(Consumer<FinishedRecipe> con, Result result, ItemLike... item) {
        String path = getPath(result.getObject());
        LOGGER.info("Item: {}, count: {}", path, result.getCount());
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(result.getObject(), result.getCount());
        Arrays.stream(item).forEach(ingredient -> this.addIngredients(builder, ingredient));
        boolean hasGroup = result.getGroup().isPresent();
        if (hasGroup) builder.group(Utils.setLocation(result.getGroup().get()));
        builder.unlockedBy("has_req", has(item[0]));
        builder.save(con, setRL("crafting/shapeless/" + result.getPath().orElse("").trim() + (result.getPrefix() + path + result.getSuffix())));
    }

    private void addIngredients(ShapelessRecipeBuilder builder, Ingredient input) {
        if (!input.isSimple()) {
            builder.requires(input);
        }
    }

    private void addIngredients(ShapelessRecipeBuilder builder, ItemLike input) {
        builder.requires(input);
    }

}
