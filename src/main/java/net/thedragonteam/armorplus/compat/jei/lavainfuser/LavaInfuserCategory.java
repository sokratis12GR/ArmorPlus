/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.compat.jei.ArmorPlusPlugin;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class LavaInfuserCategory extends LavaInfuserRecipeCategory<InfusingRecipe> {
    private final IDrawable background;
    private final String localizedName;

    public LavaInfuserCategory() {
        ResourceLocation location = new ResourceLocation("armorplus", "textures/gui/container/gui_lava_infuser.png");
        background = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(location, 7, 20, 138, 46);
        localizedName = localize("gui.jei.category.armorplus.infusing");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        fusion.draw(minecraft, 1, 1);
        arrow.draw(minecraft, 84, 15);
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public String getUid() {
        return Constants.Compat.JEI_CATEGORY_LAVA_INFUSER_INFUSING;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, InfusingRecipe recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(inputSlot, true, 61, 14);
        guiItemStacks.init(outputSlot, false, 115, 14);

        guiItemStacks.set(ingredients);
    }
}