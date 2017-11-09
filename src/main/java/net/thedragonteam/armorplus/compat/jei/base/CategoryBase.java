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
import net.thedragonteam.armorplus.compat.jei.ArmorPlusPlugin;
import net.thedragonteam.armorplus.compat.jei.JEIUtils;
import net.thedragonteam.thedragonlib.util.TextHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

import static java.lang.String.format;
import static net.thedragonteam.armorplus.util.Utils.setRL;

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

    public CategoryBase(String name, int u, int v, int widthU, int heightV, int outputXPos, int outputYPos, int xy, String category) {
        this.xPos = outputXPos;
        this.yPos = outputYPos;
        this.xy = xy;
        this.category = category;
        ResourceLocation location = setRL(format("textures/gui/container/gui_%s.png", name));
        background = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(location, u, v, widthU, heightV);
        //noinspection MethodCallSideOnly
        localizedName = TextHelper.getFormattedText("gui.jei.category." + name);
        craftingGridHelper = ArmorPlusPlugin.jeiHelper.getGuiHelper().createCraftingGridHelper(INPUT_SLOT, OUTPUT_SLOT);
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
    @NotNull
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
    public void setRecipe(@NotNull IRecipeLayout recipeLayout, @NotNull IRecipeWrapper recipeWrapper, @NotNull IIngredients ingredients) {
        JEIUtils.setRecipe(recipeLayout, recipeWrapper, ingredients, craftingGridHelper, xPos, yPos, xy, xy, INPUT_SLOT, OUTPUT_SLOT);
    }
}
