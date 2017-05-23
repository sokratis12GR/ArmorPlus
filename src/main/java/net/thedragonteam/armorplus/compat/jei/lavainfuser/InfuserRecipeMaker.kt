/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser

import net.thedragonteam.armorplus.api.crafting.lavainfuser.LavaInfuserManager
import net.thedragonteam.armorplus.compat.minetweaker.lavainfuser.LavaInfuserRecipe
import java.util.*

object InfuserRecipeMaker {

    fun getInfuserRecipes(): List<LavaInfuserRecipe> {
        val lavaInfuserManager = LavaInfuserManager.getInstance()
        val infusingMap = lavaInfuserManager.infusingList

        val recipes = ArrayList<LavaInfuserRecipe>()

        infusingMap.forEach { (input, output) ->
            val recipe = LavaInfuserRecipe(input, output)
            recipes.add(recipe)
        }

        return recipes
    }

}