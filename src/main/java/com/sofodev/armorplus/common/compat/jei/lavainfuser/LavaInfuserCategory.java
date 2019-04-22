/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.compat.jei.lavainfuser;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.common.compat.jei.ArmorPlusPlugin;
import com.sofodev.armorplus.common.util.TextUtils;
import com.sofodev.armorplus.common.util.Utils;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class LavaInfuserCategory extends LavaInfuserRecipeCategory {

    private IDrawable background;
    private String localizedName = TextUtils.translatedText("gui.jei.category.armorplus.infusing");

    public LavaInfuserCategory() {
        ResourceLocation location = Utils.setRL("textures/gui/container/gui_lava_infuser.png");
        background = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(location, 7, 20, 138, 46);
    }

    @Override
    public String getModName() {
        return ArmorPlus.MODNAME;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        fusion.draw(minecraft, 1, 1);
        arrow.draw(minecraft, 84, 15);
        lavaBucket.draw(minecraft, 26, 14);
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public String getUid() {
        return ArmorPlusPlugin.JEI_CATEGORY_LAVA_INFUSER;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(inputSlot, true, 61, 14);
        guiItemStacks.init(outputSlot, false, 115, 14);

        guiItemStacks.set(ingredients);
    }
}