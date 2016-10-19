/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.compat.jei.workbench;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapelessOreRecipe;

import java.util.List;

public class WorkbenchShapelessOreRecipeHandler implements IRecipeHandler<ShapelessOreRecipe> {
    private final IGuiHelper guiHelper;

    public WorkbenchShapelessOreRecipeHandler(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public Class<ShapelessOreRecipe> getRecipeClass() {
        return ShapelessOreRecipe.class;
    }

    @Override
    public String getRecipeCategoryUid() {
        return Constants.Compat.JEI_CATEGORY_WORKBENCH;
    }

    @Override
    public String getRecipeCategoryUid(ShapelessOreRecipe recipe) {
        return Constants.Compat.JEI_CATEGORY_WORKBENCH;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(ShapelessOreRecipe recipe) {
        return new WorkbenchShapelessOreRecipeWrapper(guiHelper, recipe);
    }

    @Override
    public boolean isRecipeValid(ShapelessOreRecipe recipe) {
        if (recipe.getRecipeOutput() == null) {
            String recipeInfo = ErrorUtil.getInfoFromBrokenRecipe(recipe, this);
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
        if (inputCount > 9) {
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