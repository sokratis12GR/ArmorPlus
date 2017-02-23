/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.ultitechbench

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

class UTBCategory : BlankRecipeCategory<IRecipeWrapper>() {
    private val background: IDrawable
    private val localizedName: String = Translator.translateToLocal("gui.jei.category.ulti_tech_bench")
    private val craftingGridHelper: ICraftingGridHelper

    init {
        val location = ResourceLocation("armorplus", "textures/gui/container/gui_ulti_tech_bench.png")
        background = ArmorPlusPlugin.jeiHelper.guiHelper.createDrawable(location, 11, 16, width, height)
        craftingGridHelper = ArmorPlusPlugin.jeiHelper.guiHelper.createCraftingGridHelper(INPUT_SLOT, OUTPUT_SLOT)
    }

    override fun getUid(): String {
        return Constants.Compat.JEI_CATEGORY_ULTI_TECH_BENCH
    }

    override fun getTitle(): String {
        return localizedName
    }

    override fun getBackground(): IDrawable {
        return background
    }

    override fun getIcon(): IDrawable? {
        return null
    }

    override fun setRecipe(recipeLayout: IRecipeLayout, recipeWrapper: IRecipeWrapper, ingredients: IIngredients) {
        val guiItemStacks = recipeLayout.itemStacks

        guiItemStacks.init(OUTPUT_SLOT, false, 136, 36)

        for (y in 0 .. 4) {
            for (x in 0 .. 4) {
                val index = INPUT_SLOT + x + y * 5
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

        val width = 156
        val height = 93
        private val OUTPUT_SLOT = 0
        private val INPUT_SLOT = 1
    }
}
