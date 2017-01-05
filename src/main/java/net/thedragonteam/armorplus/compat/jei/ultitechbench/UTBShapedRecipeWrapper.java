/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.ultitechbench;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import mezz.jei.util.BrokenCraftingRecipeException;
import mezz.jei.util.ErrorUtil;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapedRecipes;

import java.util.Arrays;
import java.util.List;

/**
 * net.thedragonteam.armorplus.compat.jei.benches
 * ArmorPlus created by sokratis12GR on 6/22/2016 6:16 PM.
 * - TheDragonTeam
 */
class UTBShapedRecipeWrapper extends BlankRecipeWrapper implements IShapedCraftingRecipeWrapper {

    private final ShapedRecipes recipe;

    public UTBShapedRecipeWrapper(ShapedRecipes recipe) {
        this.recipe = recipe;
        for (ItemStack itemStack : this.recipe.input) {
            if (itemStack != null && itemStack.getCount() != 1) {
                itemStack.setCount(1);
            }
        }
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
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
        return recipe.recipeWidth;
    }

    @Override
    public int getHeight() {
        return recipe.recipeHeight;
    }
}