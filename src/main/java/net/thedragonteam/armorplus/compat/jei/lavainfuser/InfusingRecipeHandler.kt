/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser

import mezz.jei.api.recipe.IRecipeHandler
import mezz.jei.api.recipe.IRecipeWrapper
import mezz.jei.util.ErrorUtil
import mezz.jei.util.Log
import net.thedragonteam.armorplus.api.Constants

class InfusingRecipeHandler : IRecipeHandler<InfusingRecipe> {

    override fun getRecipeClass(): Class<InfusingRecipe> {
        return InfusingRecipe::class.java
    }

    override fun getRecipeCategoryUid(recipe: InfusingRecipe): String {
        return Constants.Compat.JEI_CATEGORY_LAVA_INFUSER_INFUSING
    }

    override fun getRecipeWrapper(recipe: InfusingRecipe): IRecipeWrapper {
        return recipe
    }

    override fun isRecipeValid(recipe: InfusingRecipe): Boolean {
        if (recipe.inputs.isEmpty()) {
            val recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this)
            Log.error("Recipe has no inputs. {}", recipeInfo)
        }
        if (recipe.outputs.isEmpty()) {
            val recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this)
            Log.error("Recipe has no outputs. {}", recipeInfo)
        }
        return true
    }

}