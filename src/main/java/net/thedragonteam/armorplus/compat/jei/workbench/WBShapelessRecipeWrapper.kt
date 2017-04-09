/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.workbench

import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.BlankRecipeWrapper
import mezz.jei.api.recipe.IRecipeWrapper
import mezz.jei.recipes.BrokenCraftingRecipeException
import mezz.jei.util.ErrorUtil
import net.minecraft.item.ItemStack
import net.thedragonteam.armorplus.api.crafting.workbench.ShapelessRecipes

internal class WBShapelessRecipeWrapper(private val recipe: ShapelessRecipes) : BlankRecipeWrapper(), IRecipeWrapper {

    init {
        this.recipe.input
                .asSequence()
                .filter { it is ItemStack && it.count != 1 }
                .forEach { it.count = 1 }
    }

    override fun getIngredients(ingredients: IIngredients) {
        val recipeOutput = recipe.recipeOutput

        try {
            ingredients.setInputs(ItemStack::class.java, recipe.input)
            ingredients.setOutput(ItemStack::class.java, recipeOutput)
        } catch (e: RuntimeException) {
            val info = ErrorUtil.getInfoFromBrokenCraftingRecipe(recipe, recipe.input, recipeOutput)
            throw BrokenCraftingRecipeException(info, e)
        }

    }
}