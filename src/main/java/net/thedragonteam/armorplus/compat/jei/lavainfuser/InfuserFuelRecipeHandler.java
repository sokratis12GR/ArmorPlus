/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.thedragonteam.armorplus.api.Constants;

public class InfuserFuelRecipeHandler implements IRecipeHandler<InfuserFuelRecipe> {
	@Override
	public Class<InfuserFuelRecipe> getRecipeClass() {
		return InfuserFuelRecipe.class;
	}

	@Override
	public String getRecipeCategoryUid(InfuserFuelRecipe recipe) {
			return Constants.Compat.JEI_CATEGORY_LAVA_INFUSER_FUEL;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(InfuserFuelRecipe recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(InfuserFuelRecipe recipe) {
		if (recipe.getInputs().isEmpty()) {
			String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
			Log.error("Recipe has no inputs. {}", recipeInfo);
		}
		return true;
	}
}