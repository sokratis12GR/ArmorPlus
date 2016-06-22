package sokratis12GR.ArmorPlus.compat.jei.armorforge;

/**
 * sokratis12GR.ArmorPlus.compat.jei
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:35 PM.
 */

import sokratis12GR.ArmorPlus.api.recipe.ArmorForgeRecipe;
import sokratis12GR.ArmorPlus.api.registry.ArmorForgeRecipeRegistry;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class ArmorForgeRecipeMaker
{
    @Nonnull
    public static List<ArmorForgeRecipeJEI> getRecipes()
    {
        List<ArmorForgeRecipe> recipeList = ArmorForgeRecipeRegistry.getRecipeList();
        ArrayList<ArmorForgeRecipeJEI> recipes = new ArrayList<ArmorForgeRecipeJEI>();

        for (ArmorForgeRecipe recipe : recipeList)
            recipes.add(new ArmorForgeRecipeJEI(recipe));

        return recipes;
    }
}