package net.thedragonteam.armorplus.compat.jei.lavainfuser

import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.BlankRecipeWrapper
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.item.ItemStack
import net.thedragonteam.armorplus.compat.minetweaker.lavainfuser.LavaInfuserRecipe

class LavaInfuserRecipeWrapper(val recipe: LavaInfuserRecipe) : BlankRecipeWrapper(), IRecipeWrapper {

    override fun getIngredients(ingredients: IIngredients) {
        val recipeOutput = recipe.recipeOutput

        ingredients.setInput(ItemStack::class.java, recipe.input)
        ingredients.setOutput(ItemStack::class.java, recipeOutput)
    }
}