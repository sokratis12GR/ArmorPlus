/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.thedragonteam.armorplus.api.Constants;

import javax.annotation.Nonnull;

public class InfusingRecipeHandler implements IRecipeHandler<InfusingRecipe> {

    @Override
    @Nonnull
    public Class<InfusingRecipe> getRecipeClass() {
        return InfusingRecipe.class;
    }

    @Override
    @Nonnull
    public String getRecipeCategoryUid(@Nonnull InfusingRecipe recipe) {
        return Constants.Compat.JEI_CATEGORY_LAVA_INFUSER_INFUSING;
    }

    @Override
    @Nonnull
    public IRecipeWrapper getRecipeWrapper(@Nonnull InfusingRecipe recipe) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(@Nonnull InfusingRecipe recipe) {
        if (recipe.getInputs().isEmpty()) {
            String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
            Log.error("Recipe has no inputs. {}", recipeInfo);
        }
        if (recipe.getOutputs().isEmpty()) {
            String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
            Log.error("Recipe has no outputs. {}", recipeInfo);
        }
        return true;
    }

}