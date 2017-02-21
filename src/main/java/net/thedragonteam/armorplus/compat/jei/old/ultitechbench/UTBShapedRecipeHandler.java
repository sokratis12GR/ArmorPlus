/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.old.ultitechbench;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapedRecipes;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Objects;

/**
 * net.thedragonteam.armorplus.compat.jei.old.hightechbench
 * ArmorPlus created by sokratis12GR on 8/31/2016 11:42 PM.
 * - TheDragonTeam
 */
public class UTBShapedRecipeHandler implements IRecipeHandler<ShapedRecipes> {

    @Override
    @Nonnull
    public Class<ShapedRecipes> getRecipeClass() {
        return ShapedRecipes.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return Constants.Compat.JEI_CATEGORY_ULTI_TECH_BENCH;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull ShapedRecipes recipe) {
        return Constants.Compat.JEI_CATEGORY_ULTI_TECH_BENCH;
    }

    @Override
    @Nonnull
    public IRecipeWrapper getRecipeWrapper(@Nonnull ShapedRecipes recipe) {
        return new UTBShapedRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@Nonnull ShapedRecipes recipe) {
        if (recipe.getRecipeOutput() == null) {
            String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
            Log.error("Recipe has no outputs. {}", recipeInfo);
            return false;
        }
        int inputCount = (int) Arrays.stream(recipe.input).filter(Objects::nonNull).count();
        if (inputCount > 25) {
            String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
            Log.error("Recipe has too many inputs. {}", recipeInfo);
            return false;
        }
        if (inputCount == 0) {
            String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
            Log.error("Recipe has no inputs. {}", recipeInfo);
            return false;
        }
        return true;
    }
}