/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.utils.ShapedRecipeUtils;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

/**
 * net.thedragonteam.armorplus.api.crafting.benches
 * ArmorPlus created by sokratis12GR on 6/19/2016 1:27 PM.
 * - TheDragonTeam
 */
public class HTBShapedRecipes implements IRecipe {
    /**
     * How many horizontal itemHandler this recipe is wide.
     */
    public final int recipeWidth;
    /**
     * How many vertical itemHandler this recipe uses.
     */
    public final int recipeHeight;
    /**
     * Is a array of ItemStack that composes the recipe.
     */
    public final ItemStack[] input;
    /**
     * Is the ItemStack that you get when craft the recipe.
     */
    private final ItemStack recipeOutput;
    private boolean copyIngredientNBT;

    public HTBShapedRecipes(int width, int height, ItemStack[] ingredientsIn, ItemStack output) {
        this.recipeWidth = width;
        this.recipeHeight = height;
        this.input = ingredientsIn;

        IntStream.range(0, this.input.length).filter(i -> this.input[i].isEmpty()).forEachOrdered(i -> this.input[i] = ItemStack.EMPTY);

        this.recipeOutput = output;
    }

    @Override
    @Nonnull
    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    @Override
    @Nonnull
    public NonNullList<ItemStack> getRemainingItems(@Nonnull InventoryCrafting inv) {
        return ShapedRecipeUtils.getRemainingItems(inv);
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World worldIn) {
        return ShapedRecipeUtils.matches(5, recipeWidth, recipeHeight, input, inv);
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Override
    @Nonnull
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
        return ShapedRecipeUtils.getCraftingResult(getRecipeOutput(), copyIngredientNBT, inv);
    }

    public int getWidth() {
        return recipeWidth;
    }

    public int getHeight() {
        return recipeHeight;
    }

    /**
     * Returns the input for this recipe, any mod accessing this value should never
     * manipulate the values in this array as it will effect the recipe itself.
     *
     * @return The recipes input vales.
     */
    public Object[] getInput() {
        return this.input;
    }

    /**
     * Returns the size of the recipe area
     */
    @Override
    public int getRecipeSize() {
        return this.recipeWidth * this.recipeHeight;
    }
}