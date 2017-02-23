/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.workbench

import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.BlankRecipeWrapper
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper
import mezz.jei.util.BrokenCraftingRecipeException
import mezz.jei.util.ErrorUtil
import net.minecraft.item.ItemStack
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedRecipes
import java.util.*

/**
 * net.thedragonteam.armorplus.compat.jei.benches
 * ArmorPlus created by sokratis12GR on 6/22/2016 6:16 PM.
 * - TheDragonTeam
 */
internal class WBShapedRecipeWrapper(private val recipe: ShapedRecipes) : BlankRecipeWrapper(), IShapedCraftingRecipeWrapper {

    init {
        this.recipe.input
                .asSequence()
                .filter { it != null && it.count != 1 }
                .forEach { it.count = 1 }
    }

    override fun getIngredients(ingredients: IIngredients) {
        val recipeItems = Arrays.asList(*recipe.input)
        val recipeOutput = recipe.recipeOutput
        try {
            ingredients.setInputs(ItemStack::class.java, recipeItems)
            ingredients.setOutput(ItemStack::class.java, recipeOutput)
        } catch (e: RuntimeException) {
            val info = ErrorUtil.getInfoFromBrokenCraftingRecipe(recipe, recipeItems, recipeOutput)
            throw BrokenCraftingRecipeException(info, e)
        }

    }

    override fun getWidth(): Int {
        return recipe.recipeWidth
    }

    override fun getHeight(): Int {
        return recipe.recipeHeight
    }
}