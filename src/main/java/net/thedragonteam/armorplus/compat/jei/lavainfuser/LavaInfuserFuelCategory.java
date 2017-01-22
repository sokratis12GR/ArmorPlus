/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.config.Constants;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.compat.jei.ArmorPlusPlugin;

import javax.annotation.Nullable;

import static net.thedragonteam.armorplus.api.Constants.Compat;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class LavaInfuserFuelCategory extends LavaInfuserRecipeCategory<InfuserFuelRecipe> {
    private final IDrawable background;
    private final IDrawable fusion;
    private final String localizedName;

    public LavaInfuserFuelCategory() {
        background = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(backgroundLocation, 7, 20, 43, 45, 0, 0, 0, 80);

        ResourceLocation recipeBackgroundResource = new ResourceLocation(Constants.RESOURCE_DOMAIN, Constants.TEXTURE_RECIPE_BACKGROUND_PATH);
        fusion = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(recipeBackgroundResource, 215, 0, 14, 14);
        localizedName = localize("gui.jei.category.armorplus.infusing.fuel");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public String getUid() {
        return Compat.JEI_CATEGORY_LAVA_INFUSER_FUEL;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return fusion;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, InfuserFuelRecipe recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(fuelSlot, true, 16, 14);
        guiItemStacks.set(ingredients);
    }
}