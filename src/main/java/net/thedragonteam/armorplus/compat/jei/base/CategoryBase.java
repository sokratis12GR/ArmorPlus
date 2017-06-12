/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.base;

import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.compat.jei.JEIUtils;
import net.thedragonteam.armorplus.util.TextUtils;

import javax.annotation.Nonnull;

import static java.lang.String.format;
import static net.thedragonteam.armorplus.compat.jei.ArmorPlusPlugin.jeiHelper;
import static net.thedragonteam.armorplus.util.Utils.setResourceLocation;

public class CategoryBase implements IRecipeCategory {

    private static final int OUTPUT_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private final IDrawable background;
    private final String localizedName;
    private final ICraftingGridHelper craftingGridHelper;
    private final int xPos;
    private final int yPos;
    private final int xy;
    private final String category;

    public CategoryBase(int u, int v, int widthU, int heightV, int outputXPos, int outputYPos, int xy, String category) {
        this.xPos = outputXPos;
        this.yPos = outputYPos;
        this.xy = xy;
        this.category = category;
        ResourceLocation location = setResourceLocation(format("textures/gui/container/gui_%sx%s.png", xy, xy));
        background = jeiHelper.getGuiHelper().createDrawable(location, u, v, widthU, heightV);
        localizedName = TextUtils.formattedText(format("gui.jei.category.%sx%s", xy, xy));
        craftingGridHelper = jeiHelper.getGuiHelper().createCraftingGridHelper(INPUT_SLOT, OUTPUT_SLOT);
    }

    @Nonnull
    @Override
    public String getUid() {
        return category;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return localizedName;
    }

    /**
     * Return the name of the mod associated with this recipe category.
     * Used for the recipe category tab's tooltip.
     *
     * @since JEI 4.5.0
     */
    @Override
    public String getModName() {
        return ArmorPlus.MODNAME;
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }


    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
        JEIUtils.setRecipe(recipeLayout, recipeWrapper, ingredients, craftingGridHelper, xPos, yPos, xy, xy, INPUT_SLOT, OUTPUT_SLOT);
    }
}
