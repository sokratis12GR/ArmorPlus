/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.compat.jei.ultitechbench;

import net.thedragonteam.armorplus.api.recipe.UltiTechBenchRecipe;
import net.thedragonteam.armorplus.api.registry.UltiTechBenchRecipeRegistry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * net.thedragonteam.armorplus.compat.jei.hightechbench
 * ArmorPlus created by sokratis12GR on 8/31/2016 11:35 PM.
 * - TheDragonTeam
 */
public class UltiTechBenchRecipeMaker {
    @Nonnull
    public static List<UltiTechBenchRecipeJEI> getRecipes() {
        List<UltiTechBenchRecipe> recipeList = UltiTechBenchRecipeRegistry.getRecipeList();
        ArrayList<UltiTechBenchRecipeJEI> recipes = new ArrayList<UltiTechBenchRecipeJEI>();

        for (UltiTechBenchRecipe recipe : recipeList)
            recipes.add(new UltiTechBenchRecipeJEI(recipe));

        return recipes;
    }
}