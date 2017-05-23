/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser

import mezz.jei.api.gui.IDrawableAnimated
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.recipe.BlankRecipeCategory
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.util.ResourceLocation
import net.thedragonteam.armorplus.compat.jei.ArmorPlusPlugin

abstract class LavaInfuserRecipeCategory<T : IRecipeWrapper> : BlankRecipeCategory<T>() {

    protected val backgroundLocation: ResourceLocation = ResourceLocation("armorplus", "textures/gui/container/gui_lava_infuser.png")
    protected val fusion: IDrawableAnimated
    protected val arrow: IDrawableAnimated
    protected val lavaBucket: IDrawableStatic

    init {

        val flameDrawable = ArmorPlusPlugin.jeiHelper.guiHelper.createDrawable(backgroundLocation, 176, 0, 16, 44)
        fusion = ArmorPlusPlugin.jeiHelper.guiHelper.createAnimatedDrawable(flameDrawable, 300, IDrawableAnimated.StartDirection.TOP, true)

        val arrowDrawable = ArmorPlusPlugin.jeiHelper.guiHelper.createDrawable(backgroundLocation, 176, 44, 24, 17)
        this.arrow = ArmorPlusPlugin.jeiHelper.guiHelper.createAnimatedDrawable(arrowDrawable, 200, IDrawableAnimated.StartDirection.LEFT, false)

        this.lavaBucket = ArmorPlusPlugin.jeiHelper.guiHelper.createDrawable(ResourceLocation("minecraft", "textures/items/bucket_lava.png"), 0, 0, 16, 16)
    }

    companion object {
        const val inputSlot = 0
        const val outputSlot = 2
    }
}