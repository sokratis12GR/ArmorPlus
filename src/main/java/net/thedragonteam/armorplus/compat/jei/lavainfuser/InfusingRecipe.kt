/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser

import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.BlankRecipeWrapper
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.thedragonteam.armorplus.api.crafting.lavainfuser.LavaInfuserManager
import net.thedragonteam.thedragonlib.util.TextHelper.localize
import java.awt.Color

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
class InfusingRecipe(inputs: List<ItemStack>, private val output: ItemStack) : BlankRecipeWrapper() {
    val inputs: List<List<ItemStack>>

    init {
        this.inputs = listOf(inputs)
    }

    override fun getIngredients(ingredients: IIngredients) {
        ingredients.setInputLists(ItemStack::class.java, inputs)
        ingredients.setOutput(ItemStack::class.java, output)
    }

    val outputs: List<ItemStack>
        get() = listOf(output)

    override fun drawInfo(minecraft: Minecraft?, recipeWidth: Int, recipeHeight: Int, mouseX: Int, mouseY: Int) {
        val lavaInfuserManager = LavaInfuserManager.getInstance()
        val experience = lavaInfuserManager.getSmeltingExperience(output)
        if (experience > 0) {
            val experienceString = localize("gui.jei.category.armorplus.infusing.experience", experience)
            val fontRendererObj = minecraft!!.fontRenderer
            val stringWidth = fontRendererObj.getStringWidth(experienceString)
            fontRendererObj.drawString(experienceString, recipeWidth - stringWidth, 0, Color.gray.rgb)
        }
    }
}