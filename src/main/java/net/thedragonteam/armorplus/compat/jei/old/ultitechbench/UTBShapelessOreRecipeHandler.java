/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.old.ultitechbench;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapelessOreRecipe;

import java.util.List;

public class UTBShapelessOreRecipeHandler implements IRecipeHandler<ShapelessOreRecipe> {
    private final IJeiHelpers guiHelper;

    public UTBShapelessOreRecipeHandler(IJeiHelpers guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public Class<ShapelessOreRecipe> getRecipeClass() {
        return ShapelessOreRecipe.class;
    }

    @Override
    public String getRecipeCategoryUid() {
        return Constants.Compat.JEI_CATEGORY_ULTI_TECH_BENCH;
    }

    @Override
    public String getRecipeCategoryUid(ShapelessOreRecipe recipe) {
        return Constants.Compat.JEI_CATEGORY_ULTI_TECH_BENCH;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(ShapelessOreRecipe recipe) {
        return new UTBShapelessOreRecipeWrapper(guiHelper, recipe);
    }

    @Override
    public boolean isRecipeValid(ShapelessOreRecipe recipe) {
        if (recipe.getRecipeOutput() == null) {
            String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
            Log.error("Recipe has no outputs. {}", recipeInfo);
            return false;
        }
        int inputCount = 0;
        for (Object input : recipe.getInput()) {
            if (input instanceof List) {
                if (((List) input).isEmpty()) {
                    // missing items for an oreDict name. This is normal behavior, but the recipe is invalid.
                    return false;
                }
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