/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.old.ultitechbench;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapelessRecipes;

import javax.annotation.Nonnull;

/**
 * net.thedragonteam.armorplus.compat.jei.old.hightechbench
 * ArmorPlus created by sokratis12GR on 8/31/2016 7:03 PM.
 * - TheDragonTeam
 */
public class UTBShapelessRecipeHandler implements IRecipeHandler<ShapelessRecipes> {

    @Override
    @Nonnull
    public Class<ShapelessRecipes> getRecipeClass() {
        return ShapelessRecipes.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return Constants.Compat.JEI_CATEGORY_ULTI_TECH_BENCH;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull ShapelessRecipes recipe) {
        return Constants.Compat.JEI_CATEGORY_ULTI_TECH_BENCH;
    }

    @Override
    @Nonnull
    public IRecipeWrapper getRecipeWrapper(@Nonnull ShapelessRecipes recipe) {
        return new UTBShapelessRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@Nonnull ShapelessRecipes recipe) {
        if (recipe.getRecipeOutput() == null) {
            String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
            Log.error("Recipe has no output. {}", recipeInfo);
            return false;
        }
        int inputCount = 0;
        for (Object input : recipe.input) {
            if (input instanceof ItemStack) {
                inputCount++;
            } else {
                String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
                Log.error("Recipe has an input that is not an ItemStack. {}", recipeInfo);
                return false;
            }
        }
        if (inputCount > 25) {
            String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
            Log.error("Recipe has too many inputs. {}", recipeInfo);
            return false;
        }
        return inputCount > 0;
    }
}