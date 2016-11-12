/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.compat.jei.hightechbench;

import net.thedragonteam.armorplus.api.recipe.HighTechBenchRecipe;
import net.thedragonteam.armorplus.api.registry.HighTechBenchRecipeRegistry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * net.thedragonteam.armorplus.compat.jei.hightechbench
 * ArmorPlus created by sokratis12GR on 8/31/2016 11:35 PM.
 * - TheDragonTeam
 */
public class HighTechBenchRecipeMaker {
    @Nonnull
    public static List<HighTechBenchRecipeJEI> getRecipes() {
        List<HighTechBenchRecipe> recipeList = HighTechBenchRecipeRegistry.getRecipeList();

        return recipeList.stream().map(HighTechBenchRecipeJEI::new).collect(Collectors.toCollection(ArrayList::new));
    }
}