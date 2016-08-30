/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.compat.jei.advarmorforge;

import net.thedragonteam.armorplus.api.recipe.AdvancedArmorForgeRecipe;
import net.thedragonteam.armorplus.api.registry.AdvancedArmorForgeRecipeRegistry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * net.thedragonteam.armorplus.compat.jei.armorforge
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:35 PM.
 * - TheDragonTeam
 */
public class AdvArmorForgeRecipeMaker {
    @Nonnull
    public static List<AdvArmorForgeRecipeJEI> getRecipes() {
        List<AdvancedArmorForgeRecipe> recipeList = AdvancedArmorForgeRecipeRegistry.getRecipeList();
        ArrayList<AdvArmorForgeRecipeJEI> recipes = new ArrayList<AdvArmorForgeRecipeJEI>();

        for (AdvancedArmorForgeRecipe recipe : recipeList)
            recipes.add(new AdvArmorForgeRecipeJEI(recipe));

        return recipes;
    }
}