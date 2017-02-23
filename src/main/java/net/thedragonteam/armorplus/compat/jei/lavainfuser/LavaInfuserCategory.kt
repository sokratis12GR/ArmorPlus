/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser

import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraft.client.Minecraft
import net.minecraft.util.ResourceLocation
import net.thedragonteam.armorplus.api.Constants
import net.thedragonteam.armorplus.compat.jei.ArmorPlusPlugin
import net.thedragonteam.thedragonlib.util.TextHelper.localize

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
class LavaInfuserCategory : LavaInfuserRecipeCategory<InfusingRecipe>() {
    private val background: IDrawable
    private val localizedName: String = localize("gui.jei.category.armorplus.infusing")

    init {
        val location = ResourceLocation("armorplus", "textures/gui/container/gui_lava_infuser.png")
        background = ArmorPlusPlugin.jeiHelper.guiHelper.createDrawable(location, 7, 20, 138, 46)
    }

    override fun getBackground(): IDrawable {
        return background
    }

    override fun drawExtras(minecraft: Minecraft?) {
        fusion.draw(minecraft!!, 1, 1)
        arrow.draw(minecraft, 84, 15)
    }

    override fun getTitle(): String {
        return localizedName
    }

    override fun getUid(): String {
        return Constants.Compat.JEI_CATEGORY_LAVA_INFUSER_INFUSING
    }

    override fun setRecipe(recipeLayout: IRecipeLayout, recipeWrapper: InfusingRecipe, ingredients: IIngredients) {
        val guiItemStacks = recipeLayout.itemStacks

        guiItemStacks.init(LavaInfuserRecipeCategory.inputSlot, true, 61, 14)
        guiItemStacks.init(LavaInfuserRecipeCategory.outputSlot, false, 115, 14)

        guiItemStacks.set(ingredients)
    }
}