/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.hightechbench

import mezz.jei.api.IJeiHelpers
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.BlankRecipeWrapper
import mezz.jei.api.recipe.IRecipeWrapper
import mezz.jei.recipes.BrokenCraftingRecipeException
import mezz.jei.util.ErrorUtil
import net.minecraft.item.ItemStack
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapelessOreRecipe

class HTBShapelessOreRecipeWrapper(private val jeiHelpers: IJeiHelpers, private val recipe: ShapelessOreRecipe) : BlankRecipeWrapper(), IRecipeWrapper {

    init {
        this.recipe.input
                .asSequence()
                .filterIsInstance<ItemStack>()
                .filter { it.count != 1 }
                .forEach { it.count = 1 }
    }

    override fun getIngredients(ingredients: IIngredients) {
        val stackHelper = jeiHelpers.stackHelper
        val recipeOutput = recipe.recipeOutput

        try {
            val inputs = stackHelper.expandRecipeItemStackInputs(recipe.input)
            ingredients.setInputLists(ItemStack::class.java, inputs)

            ingredients.setOutput(ItemStack::class.java, recipeOutput)
        } catch (e: RuntimeException) {
            val info = ErrorUtil.getInfoFromBrokenCraftingRecipe(recipe, recipe.input, recipeOutput)
            throw BrokenCraftingRecipeException(info, e)
        }

    }
}