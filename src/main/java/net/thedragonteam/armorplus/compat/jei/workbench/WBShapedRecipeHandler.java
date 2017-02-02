/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.workbench;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedRecipes;

import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * net.thedragonteam.armorplus.compat.jei.benches
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:42 PM.
 * - TheDragonTeam
 */
public class WBShapedRecipeHandler implements IRecipeHandler<ShapedRecipes> {

    @Override
    @Nonnull
    public Class<ShapedRecipes> getRecipeClass() {
        return ShapedRecipes.class;
    }

    @Override
    @Nonnull
    public String getRecipeCategoryUid(@Nonnull ShapedRecipes recipe) {
        return Constants.Compat.JEI_CATEGORY_WORKBENCH;
    }

    @Override
    @Nonnull
    public IRecipeWrapper getRecipeWrapper(@Nonnull ShapedRecipes recipe) {
        return new WBShapedRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@Nonnull ShapedRecipes recipe) {
        int inputCount = (int) Arrays.stream(recipe.input).filter(ItemStack::isEmpty).count();
        if (inputCount > 9) {
            String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
            Log.error("Recipe has too many inputs. {}", recipeInfo);
            return false;
        }
        if (inputCount == 0) {
            String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
            Log.error("Recipe has no inputs. {}", recipeInfo);
            return false;
        }
        return true;
    }
}