/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser;

import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.compat.jei.ArmorPlusPlugin;

public abstract class LavaInfuserRecipeCategory<T extends IRecipeWrapper> extends BlankRecipeCategory<T> {
	protected static final int inputSlot = 0;
	protected static final int outputSlot = 2;

	protected final ResourceLocation backgroundLocation;
	protected final IDrawableAnimated fusion;
	protected final IDrawableAnimated arrow;

	public LavaInfuserRecipeCategory() {
		backgroundLocation = new ResourceLocation("armorplus", "textures/gui/container/gui_lava_infuser.png");

		IDrawableStatic flameDrawable = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(backgroundLocation, 176, 0, 16, 44);
		fusion = ArmorPlusPlugin.jeiHelper.getGuiHelper().createAnimatedDrawable(flameDrawable, 300, IDrawableAnimated.StartDirection.TOP, true);

		IDrawableStatic arrowDrawable = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(backgroundLocation, 176, 44, 24, 17);
		this.arrow = ArmorPlusPlugin.jeiHelper.getGuiHelper().createAnimatedDrawable(arrowDrawable, 200, IDrawableAnimated.StartDirection.LEFT, false);
	}
}