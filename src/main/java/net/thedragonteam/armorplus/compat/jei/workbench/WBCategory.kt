/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.workbench

import mezz.jei.api.gui.ICraftingGridHelper
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.BlankRecipeCategory
import mezz.jei.api.recipe.IRecipeWrapper
import mezz.jei.api.recipe.wrapper.ICustomCraftingRecipeWrapper
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper
import mezz.jei.util.Translator
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.thedragonteam.armorplus.api.Constants
import net.thedragonteam.armorplus.compat.jei.ArmorPlusPlugin

class WBCategory : BlankRecipeCategory<IRecipeWrapper>() {
    private val background: IDrawable
    private val localizedName: String = Translator.translateToLocal("gui.jei.category.workbench")
    private val craftingGridHelper: ICraftingGridHelper

    init {
        val location = ResourceLocation("armorplus", "textures/gui/container/gui_workbench.png")
        background = ArmorPlusPlugin.jeiHelper.guiHelper.createDrawable(location, 29, 16, width, height)
        craftingGridHelper = ArmorPlusPlugin.jeiHelper.guiHelper.createCraftingGridHelper(INPUT_SLOT, OUTPUT_SLOT)
    }

    override fun getUid(): String = Constants.Compat.JEI_CATEGORY_WORKBENCH

    override fun getTitle(): String = localizedName

    override fun getBackground(): IDrawable = background

    override fun getIcon(): IDrawable? = null

    override fun setRecipe(recipeLayout: IRecipeLayout, recipeWrapper: IRecipeWrapper, ingredients: IIngredients) {
        val guiItemStacks = recipeLayout.itemStacks

        guiItemStacks.init(OUTPUT_SLOT, false, 94, 18)

        (0.rangeTo(2)).forEach { y ->
            (0.rangeTo(2)).forEach { x ->
                val index = INPUT_SLOT + x + y * 3
                guiItemStacks.init(index, true, x * 18, y * 18)
            }
        }

        if (recipeWrapper is ICustomCraftingRecipeWrapper) {
            recipeWrapper.setRecipe(recipeLayout, ingredients)
            return
        }

        val inputs = ingredients.getInputs(ItemStack::class.java)
        val outputs = ingredients.getOutputs(ItemStack::class.java)

        when (recipeWrapper) {
            is IShapedCraftingRecipeWrapper -> craftingGridHelper.setInputs(guiItemStacks, inputs, recipeWrapper.width, recipeWrapper.height)
            else -> {
                craftingGridHelper.setInputs(guiItemStacks, inputs)
                recipeLayout.setShapeless()
            }
        }
        guiItemStacks.set(OUTPUT_SLOT, outputs[0])
    }

    companion object {

        val width = 116
        val height = 54
        private val OUTPUT_SLOT = 0
        private val INPUT_SLOT = 1
    }
}
