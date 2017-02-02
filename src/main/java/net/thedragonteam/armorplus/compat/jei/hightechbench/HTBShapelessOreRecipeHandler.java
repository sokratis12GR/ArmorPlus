/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.hightechbench;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapelessOreRecipe;

import javax.annotation.Nonnull;
import java.util.List;

public class HTBShapelessOreRecipeHandler implements IRecipeHandler<ShapelessOreRecipe> {
    private final IJeiHelpers jeiHelpers;

    public HTBShapelessOreRecipeHandler(IJeiHelpers jeiHelpers) {
        this.jeiHelpers = jeiHelpers;
    }

    @Override
    @Nonnull
    public Class<ShapelessOreRecipe> getRecipeClass() {
        return ShapelessOreRecipe.class;
    }

    @Override
    @Nonnull
    public String getRecipeCategoryUid(@Nonnull ShapelessOreRecipe recipe) {
        return Constants.Compat.JEI_CATEGORY_HIGH_TECH_BENCH;
    }

    @Override
    @Nonnull
    public IRecipeWrapper getRecipeWrapper(@Nonnull ShapelessOreRecipe recipe) {
        return new HTBShapelessOreRecipeWrapper(jeiHelpers, recipe);
    }

    @Override
    public boolean isRecipeValid(@Nonnull ShapelessOreRecipe recipe) {
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
        if (inputCount > 16) {
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