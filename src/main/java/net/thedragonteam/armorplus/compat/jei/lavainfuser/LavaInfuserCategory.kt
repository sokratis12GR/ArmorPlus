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
import net.thedragonteam.thedragonlib.util.TextUtils

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
class LavaInfuserCategory : LavaInfuserRecipeCategory<LavaInfuserRecipeWrapper>() {
    private val background: IDrawable
    private val localizedName: String = TextUtils.formattedText("gui.jei.category.armorplus.infusing")

    init {
        val location = ResourceLocation("armorplus", "textures/gui/container/gui_lava_infuser.png")
        background = ArmorPlusPlugin.jeiHelper.guiHelper.createDrawable(location, 7, 20, 138, 46)
    }

    override fun getBackground(): IDrawable = background

    override fun drawExtras(minecraft: Minecraft?) {
        fusion.draw(minecraft, 1, 1)
        arrow.draw(minecraft, 84, 15)
        lavaBucket.draw(minecraft, 26, 14)
    }

    override fun getTitle(): String = localizedName

    override fun getUid(): String = Constants.Compat.JEI_CATEGORY_LAVA_INFUSER

    override fun setRecipe(recipeLayout: IRecipeLayout, recipeWrapper: LavaInfuserRecipeWrapper, ingredients: IIngredients) {
        recipeLayout.itemStacks.init(inputSlot, true, 61, 14);
        recipeLayout.itemStacks.set(inputSlot, recipeWrapper.recipe.input)

        recipeLayout.itemStacks.init(outputSlot, false, 115, 14);
        recipeLayout.itemStacks.set(outputSlot, recipeWrapper.recipe.output)
    }
}