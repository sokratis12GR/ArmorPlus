/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.compat.jei.armorforge;

import net.thedragonteam.armorplus.api.recipe.ArmorForgeRecipe;
import net.thedragonteam.armorplus.api.registry.ArmorForgeRecipeRegistry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * net.thedragonteam.armorplus.compat.jei.armorforge
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:35 PM.
 * - TheDragonTeam
 */
public class ArmorForgeRecipeMaker {
    @Nonnull
    public static List<ArmorForgeRecipeJEI> getRecipes() {
        List<ArmorForgeRecipe> recipeList = ArmorForgeRecipeRegistry.getRecipeList();
        ArrayList<ArmorForgeRecipeJEI> recipes = new ArrayList<ArmorForgeRecipeJEI>();

        for (ArmorForgeRecipe recipe : recipeList)
            recipes.add(new ArmorForgeRecipeJEI(recipe));

        return recipes;
    }
}