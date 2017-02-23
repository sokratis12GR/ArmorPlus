/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.ultitechbench

import mezz.jei.api.IJeiHelpers
import mezz.jei.api.recipe.IRecipeHandler
import mezz.jei.api.recipe.IRecipeWrapper
import mezz.jei.util.ErrorUtil
import mezz.jei.util.Log
import net.thedragonteam.armorplus.api.Constants
import net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapedOreRecipe

class UTBShapedOreRecipeHandler(private val jeiHelpers: IJeiHelpers) : IRecipeHandler<ShapedOreRecipe> {

    override fun getRecipeClass(): Class<ShapedOreRecipe> {
        return ShapedOreRecipe::class.java
    }

    override fun getRecipeCategoryUid(recipe: ShapedOreRecipe): String {
        return Constants.Compat.JEI_CATEGORY_ULTI_TECH_BENCH
    }

    override fun getRecipeWrapper(recipe: ShapedOreRecipe): IRecipeWrapper {
        return UTBShapedOreRecipeWrapper(jeiHelpers, recipe)
    }

    override fun isRecipeValid(recipe: ShapedOreRecipe): Boolean {
        var inputCount = 0
        for (input in recipe.input) {
            if (input is List<*> && input.isEmpty()) return false
            if (input != null) inputCount++
        }
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