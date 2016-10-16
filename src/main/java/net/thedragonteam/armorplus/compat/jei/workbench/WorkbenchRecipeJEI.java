/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.compat.jei.workbench;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.recipe.WorkbenchRecipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * net.thedragonteam.armorplus.compat.jei.benches
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:39 PM.
 * - TheDragonTeam
 */
class WorkbenchRecipeJEI extends BlankRecipeWrapper {

    private WorkbenchRecipe recipe;

    WorkbenchRecipeJEI(WorkbenchRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {

    }

    @Override
    @Nonnull
    public List<Collection> getInputs() {
        ArrayList<Collection> ret = new ArrayList<Collection>();
        ret.add(recipe.getInput());
        return ret;
    }

    @Override
    @Nonnull
    public List<ItemStack> getOutputs() {
        return Collections.singletonList(recipe.getRecipeOutput(new ArrayList<ItemStack>()));
    }

    @Nullable
    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return null;
    }
}