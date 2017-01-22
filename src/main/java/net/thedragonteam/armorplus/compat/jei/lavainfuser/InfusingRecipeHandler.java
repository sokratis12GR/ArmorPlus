/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.thedragonteam.armorplus.api.Constants;

public class InfusingRecipeHandler implements IRecipeHandler<InfusingRecipe> {

    @Override
    public Class<InfusingRecipe> getRecipeClass() {
        return InfusingRecipe.class;
    }

    @Override
    public String getRecipeCategoryUid(InfusingRecipe recipe) {
        return Constants.Compat.JEI_CATEGORY_LAVA_INFUSER_INFUSING;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(InfusingRecipe recipe) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(InfusingRecipe recipe) {
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