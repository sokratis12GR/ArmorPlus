/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.compat.jei.lavainfuser;

import com.sofodev.armorplus.common.compat.jei.ArmorPlusPlugin;
import com.sofodev.armorplus.common.util.Utils;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public abstract class LavaInfuserRecipeCategory implements IRecipeCategory {

    public static int inputSlot = 0;
    public static int outputSlot = 2;
    protected ResourceLocation backgroundLocation = Utils.setRL("textures/gui/container/gui_lava_infuser.png");
    protected IDrawableAnimated fusion;
    protected IDrawableAnimated arrow;
    protected IDrawableStatic lavaBucket;

    LavaInfuserRecipeCategory() {
        IGuiHelper helper = ArmorPlusPlugin.jeiHelper.getGuiHelper();
        IDrawableStatic flameDrawable = helper.createDrawable(backgroundLocation, 176, 0, 16, 44);
        fusion = helper.createAnimatedDrawable(flameDrawable, 300, IDrawableAnimated.StartDirection.TOP, true);

        IDrawableStatic arrowDrawable = helper.createDrawable(backgroundLocation, 176, 44, 24, 17);
        this.arrow = helper.createAnimatedDrawable(arrowDrawable, 200, IDrawableAnimated.StartDirection.LEFT, false);

        this.lavaBucket = helper.createDrawable(new ResourceLocation("textures/items/bucket_lava.png"), 0, 0, 16, 16);

    }
}