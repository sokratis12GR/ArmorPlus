/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.base;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.IShapedRecipe;
import net.thedragonteam.armorplus.api.crafting.utils.ShapedRecipeUtils;
import net.thedragonteam.armorplus.container.base.InventoryCraftingImproved;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class BaseShapedRecipe implements IRecipe, IShapedRecipe {
    private int xy;
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
    public final NonNullList<ItemStack> input;
    /**
     * Is the ItemStack that you get when craft the recipe.
     */
    private final ItemStack recipeOutput;
    private boolean copyIngredientNBT;

    public BaseShapedRecipe(int xy, int width, int height, NonNullList<ItemStack> ingredientsIn, ItemStack output) {
        this.xy = xy;
        this.recipeWidth = width;
        this.recipeHeight = height;
        this.input = ingredientsIn;

        IntStream.range(0, this.getInput().size()).filter(i -> this.getInput().get(i).isEmpty()).forEachOrdered(i -> this.getInput().set(i, ItemStack.EMPTY));
        this.recipeOutput = output;
    }

    @Override
    @Nonnull
    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @Override
    public boolean matches(@Nonnull InventoryCraftingImproved inv, @Nonnull World worldIn) {
        return ShapedRecipeUtils.matches(recipeWidth, recipeHeight, input, inv);
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Override
    @Nonnull
    public ItemStack getCraftingResult(@Nonnull InventoryCraftingImproved inv) {
        return ShapedRecipeUtils.getCraftingResult(getRecipeOutput(), copyIngredientNBT, inv);
    }

    /**
     * Returns the input for this recipe, any mod accessing this value should never
     * manipulate the values in this array as it will effect the recipe itself.
     *
     * @return The recipes input vales.
     */
    public NonNullList<ItemStack> getInput() {
        return this.input;
    }

    public int getWidth() {
        return recipeWidth;
    }

    public int getHeight() {
        return recipeHeight;
    }

    /**
     * Returns the size of the recipe area
     */
    @Override
    public int getRecipeSize() {
        return this.recipeWidth * this.recipeHeight;
    }

    @Override
    public int getRecipeWidth() {
        return xy;
    }

    @Override
    public int getRecipeHeight() {
        return xy;
    }
}