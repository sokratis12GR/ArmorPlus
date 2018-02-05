/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.base;

import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;

public class CategoryBaseAdvanced extends CategoryBase {

    private final int buttonX;
    private final int buttonY;

    public CategoryBaseAdvanced(String name, int u, int v, int widthU, int heightV, int outputXPos, int outputYPos, int xy, String category, int buttonX, int buttonY) {
        super(name, u, v, widthU, heightV, outputXPos, outputYPos, xy, category);
        this.buttonX = buttonX;
        this.buttonY = buttonY;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        recipeLayout.setRecipeTransferButton(buttonX, buttonY);
        super.setRecipe(recipeLayout, recipeWrapper, ingredients);
    }

}
