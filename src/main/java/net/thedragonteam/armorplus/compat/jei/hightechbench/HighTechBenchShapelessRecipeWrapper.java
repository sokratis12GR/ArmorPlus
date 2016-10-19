/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.compat.jei.hightechbench;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.plugins.vanilla.crafting.AbstractShapelessRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapelessRecipes;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

/**
 * net.thedragonteam.armorplus.compat.jei.hightechbench
 * ArmorPlus created by sokratis12GR on 8/31/2016 7:04 PM.
 * - TheDragonTeam
 */
class HighTechBenchShapelessRecipeWrapper extends AbstractShapelessRecipeWrapper {

    @Nonnull
    private final ShapelessRecipes recipe;

    HighTechBenchShapelessRecipeWrapper(@Nonnull IGuiHelper guiHelper, @Nonnull ShapelessRecipes recipe) {
        super(guiHelper);
        this.recipe = recipe;
        for (Object input : this.recipe.recipeItems) {
            if (input instanceof ItemStack) {
                ItemStack itemStack = (ItemStack) input;
                if (itemStack.stackSize != 1) {
                    itemStack.stackSize = 1;
                }
            }
        }
    }

    @Override
    public void getIngredients(IIngredients ingredients) {

    }

    @Nonnull
    @Override
    public List<ItemStack> getInputs() {
        return recipe.recipeItems;
    }

    @Nonnull
    @Override
    public List<ItemStack> getOutputs() {
        return Collections.singletonList(recipe.getRecipeOutput());
    }
}