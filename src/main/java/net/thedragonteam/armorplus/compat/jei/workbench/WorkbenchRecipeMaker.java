/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.compat.jei.workbench;

import net.thedragonteam.armorplus.api.recipe.ARPWorkbenchRecipe;
import net.thedragonteam.armorplus.api.registry.ARPWorkbenchRecipeRegistry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * net.thedragonteam.armorplus.compat.jei.benches
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:35 PM.
 * - TheDragonTeam
 */
public class WorkbenchRecipeMaker {
    @Nonnull
    public static List<WorkbenchRecipeJEI> getRecipes() {
        List<ARPWorkbenchRecipe> recipeList = ARPWorkbenchRecipeRegistry.getRecipeList();
        ArrayList<WorkbenchRecipeJEI> recipes = new ArrayList<WorkbenchRecipeJEI>();

        for (ARPWorkbenchRecipe recipe : recipeList)
            recipes.add(new WorkbenchRecipeJEI(recipe));

        return recipes;
    }
}