/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.old.hightechbench;

import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICustomCraftingRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import mezz.jei.util.Translator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.compat.jei.ArmorPlusPlugin;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * net.thedragonteam.armorplus.compat.jei.old.hightechbench
 * ArmorPlus created by sokratis12GR on 8/31/2016 11:57 PM.
 * - TheDragonTeam
 */
public class HTBRecipeCategory extends BlankRecipeCategory<IRecipeWrapper> {

    public static final int width = 136;
    public static final int height = 70;
    private static final int OUTPUT_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private final IDrawable background;
    private final String localizedName;
    private final ICraftingGridHelper craftingGridHelper;

    public HTBRecipeCategory() {
        ResourceLocation location = new ResourceLocation("armorplus", "textures/gui/container/gui_high_tech_bench.png");
        background = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(location, 11, 16, width, height);
        localizedName = Translator.translateToLocal("gui.jei.category.high_tech_bench");
        craftingGridHelper = ArmorPlusPlugin.jeiHelper.getGuiHelper().createCraftingGridHelper(INPUT_SLOT, OUTPUT_SLOT);
    }

    @Nonnull
    @Override
    public String getUid() {
        return Constants.Compat.JEI_CATEGORY_HIGH_TECH_BENCH;
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
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(OUTPUT_SLOT, false, 113, 26);

        for (int y = 0; y < 4; ++y)
            for (int x = 0; x < 4; ++x) {
                int index = INPUT_SLOT + x + (y * 4);
                guiItemStacks.init(index, true, x * 18, y * 18);
            }

        if (recipeWrapper instanceof ICustomCraftingRecipeWrapper) {
            ICustomCraftingRecipeWrapper customWrapper = (ICustomCraftingRecipeWrapper) recipeWrapper;
            customWrapper.setRecipe(recipeLayout, ingredients);
            return;
        }

        List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
        List<ItemStack> outputs = ingredients.getOutputs(ItemStack.class);

        if (recipeWrapper instanceof IShapedCraftingRecipeWrapper) {
            IShapedCraftingRecipeWrapper wrapper = (IShapedCraftingRecipeWrapper) recipeWrapper;
            craftingGridHelper.setInputStacks(guiItemStacks, inputs, wrapper.getWidth(), wrapper.getHeight());
            craftingGridHelper.setOutput(guiItemStacks, outputs);
        } else {
            craftingGridHelper.setInputStacks(guiItemStacks, inputs);
            craftingGridHelper.setOutput(guiItemStacks, outputs);
        }
    }
}