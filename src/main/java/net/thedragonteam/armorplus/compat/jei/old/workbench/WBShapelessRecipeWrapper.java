/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.old.workbench;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.BrokenCraftingRecipeException;
import mezz.jei.util.ErrorUtil;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapelessRecipes;

import javax.annotation.Nonnull;

/**
 * net.thedragonteam.armorplus.compat.jei.benches
 * ArmorPlus created by sokratis12GR on 6/22/2016 7:04 PM.
 * - TheDragonTeam
 */
public class WBShapelessRecipeWrapper extends BlankRecipeWrapper implements IRecipeWrapper {

    private final ShapelessRecipes recipe;

    WBShapelessRecipeWrapper(@Nonnull ShapelessRecipes recipe) {
        this.recipe = recipe;
        for (Object input : this.recipe.input) {
            if (input instanceof ItemStack) {
                ItemStack itemStack = (ItemStack) input;
                if (itemStack.stackSize != 1) {
                    itemStack.stackSize = 1;
                }
            }
        }
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ItemStack recipeOutput = recipe.getRecipeOutput();

        try {
            ingredients.setInputs(ItemStack.class, recipe.input);
            ingredients.setOutput(ItemStack.class, recipeOutput);
        } catch (RuntimeException e) {
            String info = ErrorUtil.getInfoFromBrokenCraftingRecipe(recipe, recipe.input, recipeOutput);
            throw new BrokenCraftingRecipeException(info, e);
        }
    }
}