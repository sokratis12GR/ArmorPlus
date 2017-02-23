/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.ultitechbench

import mezz.jei.api.recipe.IRecipeHandler
import mezz.jei.api.recipe.IRecipeWrapper
import mezz.jei.util.ErrorUtil
import mezz.jei.util.Log
import net.thedragonteam.armorplus.api.Constants
import net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapedRecipes
import java.util.*

/**
 * net.thedragonteam.armorplus.compat.jei.benches
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:42 PM.
 * - TheDragonTeam
 */
class UTBShapedRecipeHandler : IRecipeHandler<ShapedRecipes> {

    override fun getRecipeClass(): Class<ShapedRecipes> {
        return ShapedRecipes::class.java
    }

    override fun getRecipeCategoryUid(recipe: ShapedRecipes): String {
        return Constants.Compat.JEI_CATEGORY_ULTI_TECH_BENCH
    }

    override fun getRecipeWrapper(recipe: ShapedRecipes): IRecipeWrapper {
        return UTBShapedRecipeWrapper(recipe)
    }

    override fun isRecipeValid(recipe: ShapedRecipes): Boolean {
        val inputCount = Arrays.stream(recipe.input).filter { input -> !input.isEmpty }.count().toInt()
        if (inputCount > 25) {
            val recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this)
            Log.error("Recipe has too many inputs. {}", recipeInfo)
            return false
        }
        if (inputCount == 0) {
            val recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this)
            Log.error("Recipe has no inputs. {}", recipeInfo)
            return false
        }
        return true
    }
}