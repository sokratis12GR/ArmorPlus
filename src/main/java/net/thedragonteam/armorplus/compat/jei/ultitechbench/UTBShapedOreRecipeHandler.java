/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.ultitechbench;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapedOreRecipe;

import java.util.List;

public class UTBShapedOreRecipeHandler implements IRecipeHandler<ShapedOreRecipe> {

    private final IJeiHelpers jeiHelpers;

    public UTBShapedOreRecipeHandler(IJeiHelpers jeiHelpers) {
        this.jeiHelpers = jeiHelpers;
    }

    @Override
    public Class<ShapedOreRecipe> getRecipeClass() {
        return ShapedOreRecipe.class;
    }

    @Override
    public String getRecipeCategoryUid(ShapedOreRecipe recipe) {
        return Constants.Compat.JEI_CATEGORY_ULTI_TECH_BENCH;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(ShapedOreRecipe recipe) {
        return new UTBShapedOreRecipeWrapper(jeiHelpers, recipe);
    }

    @Override
    public boolean isRecipeValid(ShapedOreRecipe recipe) {
        if (recipe.getRecipeOutput() == null) {
            String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
            Log.error("Recipe has no output. {}", recipeInfo);
            return false;
        }
        int inputCount = 0;
        for (Object input : recipe.getInput()) {
            if (input instanceof List && ((List) input).isEmpty()) {
                // missing items for an oreDict name. This is normal behavior, but the recipe is invalid.
                return false;
            }
            if (input != null) {
                inputCount++;
            }
        }
        if (inputCount > 25) {
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