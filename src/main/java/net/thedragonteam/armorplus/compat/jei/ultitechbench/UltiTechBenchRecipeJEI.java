/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.compat.jei.ultitechbench;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.recipe.UltiTechBenchRecipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * net.thedragonteam.armorplus.compat.jei.hightechbench
 * ArmorPlus created by sokratis12GR on 8/31/2016 11:39 PM.
 * - TheDragonTeam
 */
class UltiTechBenchRecipeJEI extends BlankRecipeWrapper {

    private UltiTechBenchRecipe recipe;

    UltiTechBenchRecipeJEI(UltiTechBenchRecipe recipe) {
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