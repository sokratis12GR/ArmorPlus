/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.lavainfuser

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

import net.thedragonteam.armorplus.api.crafting.lavainfuser.LavaInfuserManager.getInstance
import net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack

object LavaInfuserRegistry {

    /**
     * @param input  the input ItemStack
     * *
     * @param output the result of what the input ItemStack will become
     * *
     * @param exp    the amount of exp that will be gained when the infusing is done
     */
    fun addInfusingRecipe(input: ItemStack, output: ItemStack, exp: Double) {
        getInstance().addInfusingRecipe(input, output, exp)
    }

    fun addInfusingRecipe(input: ItemStack, output: ItemStack) {
        getInstance().addInfusing(input, output)
    }

    @JvmOverloads fun addInfusingRecipe(input: Block, output: ItemStack, exp: Double = 0.0) {
        addInfusingRecipe(getItemStack(input), output, exp)
    }

    @JvmOverloads fun addInfusingRecipe(input: Item, output: ItemStack, exp: Double = 0.0) {
        addInfusingRecipe(getItemStack(input), output, exp)
    }


    /**
     * @param recipe [LavaInfuserManager.removeRecipe] (IRecipe)}
     */
    fun removeInfusingRecipe(recipe: ItemStack) {
        getInstance().infusingList.remove(recipe)
    }
}
