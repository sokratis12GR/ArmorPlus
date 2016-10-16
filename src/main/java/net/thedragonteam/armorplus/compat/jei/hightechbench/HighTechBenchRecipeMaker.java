/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.compat.jei.hightechbench;

import net.thedragonteam.armorplus.api.recipe.ARPHighTechBenchRecipe;
import net.thedragonteam.armorplus.api.registry.ARPHighTechBenchRecipeRegistry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * net.thedragonteam.armorplus.compat.jei.hightechbench
 * ArmorPlus created by sokratis12GR on 8/31/2016 11:35 PM.
 * - TheDragonTeam
 */
public class HighTechBenchRecipeMaker {
    @Nonnull
    public static List<HighTechBenchRecipeJEI> getRecipes() {
        List<ARPHighTechBenchRecipe> recipeList = ARPHighTechBenchRecipeRegistry.getRecipeList();
        ArrayList<HighTechBenchRecipeJEI> recipes = new ArrayList<HighTechBenchRecipeJEI>();

        for (ARPHighTechBenchRecipe recipe : recipeList)
            recipes.add(new HighTechBenchRecipeJEI(recipe));

        return recipes;
    }
}