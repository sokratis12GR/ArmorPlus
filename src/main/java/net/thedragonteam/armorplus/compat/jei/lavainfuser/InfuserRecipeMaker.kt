/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser

import mezz.jei.api.IJeiHelpers
import net.thedragonteam.armorplus.api.crafting.lavainfuser.LavaInfuserManager
import java.util.*

object InfuserRecipeMaker {

    fun getFurnaceRecipes(helpers: IJeiHelpers): List<InfusingRecipe> {
        val stackHelper = helpers.stackHelper
        val lavaInfuserManager = LavaInfuserManager.getInstance()
        val smeltingMap = lavaInfuserManager.infusingList

        val recipes = ArrayList<InfusingRecipe>()

        for ((input, output) in smeltingMap) {

            val inputs = stackHelper.getSubtypes(input)
            val recipe = InfusingRecipe(inputs, output)
            recipes.add(recipe)
        }

        return recipes
    }

}