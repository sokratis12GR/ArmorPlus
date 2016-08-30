/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.compat.jei.advarmorforge;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.api.crafting.advarmorforge.ShapedRecipes;

import javax.annotation.Nonnull;

/**
 * net.thedragonteam.armorplus.compat.jei.armorforge
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:42 PM.
 * - TheDragonTeam
 */
public class AdvArmorForgeShapedRecipeHandler implements IRecipeHandler<ShapedRecipes> {

    @Override
    @Nonnull
    public Class<ShapedRecipes> getRecipeClass() {
        return ShapedRecipes.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return Constants.Compat.JEI_CATEGORY_ADVANCED_ARMOR_FORGE;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull ShapedRecipes recipe) {
        return Constants.Compat.JEI_CATEGORY_ADVANCED_ARMOR_FORGE;
    }

    @Override
    @Nonnull
    public IRecipeWrapper getRecipeWrapper(@Nonnull ShapedRecipes recipe) {
        return new AdvArmorForgeShapedRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@Nonnull ShapedRecipes recipe) {
        if (recipe.getRecipeOutput() == null) {
            String recipeInfo = ErrorUtil.getInfoFromBrokenRecipe(recipe, this);
            Log.error("Recipe has no outputs. {}", recipeInfo);
            return false;
        }
        int inputCount = 0;
        for (ItemStack input : recipe.recipeItems) {
            if (input != null) {
                inputCount++;
            }
        }
        if (inputCount > 16) {
            String recipeInfo = ErrorUtil.getInfoFromBrokenRecipe(recipe, this);
            Log.error("Recipe has too many inputs. {}", recipeInfo);
            return false;
        }
        if (inputCount == 0) {
            String recipeInfo = ErrorUtil.getInfoFromBrokenRecipe(recipe, this);
            Log.error("Recipe has no inputs. {}", recipeInfo);
            return false;
        }
        return true;
    }
}