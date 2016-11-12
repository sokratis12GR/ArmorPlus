/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.compat.jei.workbench;

import net.thedragonteam.armorplus.api.recipe.WorkbenchRecipe;
import net.thedragonteam.armorplus.api.registry.WorkbenchRecipeRegistry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * net.thedragonteam.armorplus.compat.jei.benches
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:35 PM.
 * - TheDragonTeam
 */
public class WorkbenchRecipeMaker {
    @Nonnull
    public static List<WorkbenchRecipeJEI> getRecipes() {
        List<WorkbenchRecipe> recipeList = WorkbenchRecipeRegistry.getRecipeList();

        return recipeList.stream().map(WorkbenchRecipeJEI::new).collect(Collectors.toCollection(ArrayList::new));
    }
}