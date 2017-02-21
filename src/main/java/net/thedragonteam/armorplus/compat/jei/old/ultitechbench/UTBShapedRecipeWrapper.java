/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.old.ultitechbench;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import mezz.jei.util.BrokenCraftingRecipeException;
import mezz.jei.util.ErrorUtil;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapedRecipes;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

/**
 * net.thedragonteam.armorplus.compat.jei.old.hightechbench
 * ArmorPlus created by sokratis12GR on 8/31/2016 6:16 PM.
 * - TheDragonTeam
 */
public class UTBShapedRecipeWrapper extends BlankRecipeWrapper implements IShapedCraftingRecipeWrapper {

    @Nonnull
    private final ShapedRecipes recipe;

    UTBShapedRecipeWrapper(@Nonnull ShapedRecipes recipe) {
        this.recipe = recipe;
        for (ItemStack itemStack : this.recipe.input) {
            if (itemStack != null && itemStack.stackSize != 1) {
                itemStack.stackSize = 1;
            }
        }
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        List<ItemStack> recipeItems = Arrays.asList(recipe.input);
        ItemStack recipeOutput = recipe.getRecipeOutput();
        try {
            ingredients.setInputs(ItemStack.class, recipeItems);
            ingredients.setOutput(ItemStack.class, recipeOutput);
        } catch (RuntimeException e) {
            String info = ErrorUtil.getInfoFromBrokenCraftingRecipe(recipe, recipeItems, recipeOutput);
            throw new BrokenCraftingRecipeException(info, e);
        }
    }

    @Override
    public int getWidth() {
        return recipe.getWidth();
    }

    @Override
    public int getHeight() {
        return recipe.getHeight();
    }
}