/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.compat.jei.ultitechbench;

import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.compat.jei.ArmorPlusPlugin;

import javax.annotation.Nonnull;

/**
 * net.thedragonteam.armorplus.compat.jei.hightechbench
 * ArmorPlus created by sokratis12GR on 8/31/2016 11:57 PM.
 * - TheDragonTeam
 */
public class UltiTechBenchRecipeCategory implements IRecipeCategory {

    public static final int width = 156;
    public static final int height = 93;
    private static final int OUTPUT_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    @Nonnull
    private final IDrawable background;
    @Nonnull
    private final String localizedName;
    @Nonnull
    private final ICraftingGridHelper craftingGridHelper;

    public UltiTechBenchRecipeCategory() {
        ResourceLocation location = new ResourceLocation("armorplus", "textures/gui/container/gui_ulti_tech_bench.png");
        background = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(location, 11, 16, width, height);
        localizedName = Translator.translateToLocal("gui.jei.category.ulti_tech_bench");
        craftingGridHelper = ArmorPlusPlugin.jeiHelper.getGuiHelper().createCraftingGridHelper(INPUT_SLOT, OUTPUT_SLOT);
    }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft) {

    }

    @Override
    public void drawAnimations(@Nonnull Minecraft minecraft) {

    }

    @Nonnull
    @Override
    public String getUid() {
        return Constants.Compat.JEI_CATEGORY_ULTI_TECH_BENCH;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return localizedName;
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(OUTPUT_SLOT, false, 136, 36);

        for (int y = 0; y < 5; ++y) {
            for (int x = 0; x < 5; ++x) {
                int index = INPUT_SLOT + x + (y * 5);
                guiItemStacks.init(index, true, x * 18, y * 18);
            }
        }

        if (recipeWrapper instanceof IShapedCraftingRecipeWrapper) {
            IShapedCraftingRecipeWrapper wrapper = (IShapedCraftingRecipeWrapper) recipeWrapper;
            craftingGridHelper.setInput(guiItemStacks, wrapper.getInputs(), wrapper.getWidth(), wrapper.getHeight());
            craftingGridHelper.setOutput(guiItemStacks, wrapper.getOutputs());
        } else {
            craftingGridHelper.setInput(guiItemStacks, recipeWrapper.getInputs());
            craftingGridHelper.setOutput(guiItemStacks, recipeWrapper.getOutputs());
        }
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(OUTPUT_SLOT, false, 136, 36);

        for (int y = 0; y < 5; ++y) {
            for (int x = 0; x < 5; ++x) {
                int index = INPUT_SLOT + x + (y * 5);
                guiItemStacks.init(index, true, x * 18, y * 18);
            }
        }

        if (recipeWrapper instanceof IShapedCraftingRecipeWrapper) {
            IShapedCraftingRecipeWrapper wrapper = (IShapedCraftingRecipeWrapper) recipeWrapper;
            craftingGridHelper.setInput(guiItemStacks, wrapper.getInputs(), wrapper.getWidth(), wrapper.getHeight());
            craftingGridHelper.setOutput(guiItemStacks, wrapper.getOutputs());
        } else {
            craftingGridHelper.setInput(guiItemStacks, recipeWrapper.getInputs());
            craftingGridHelper.setOutput(guiItemStacks, recipeWrapper.getOutputs());
        }
    }

}