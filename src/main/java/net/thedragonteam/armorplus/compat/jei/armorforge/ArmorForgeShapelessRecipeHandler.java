package net.thedragonteam.armorplus.compat.jei.armorforge;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.api.crafting.armorforge.ShapelessRecipes;

import javax.annotation.Nonnull;

/**
 * sokratis12gr.armorplus.compat.jei.armorforge
 * ArmorPlus created by sokratis12GR on 6/22/2016 7:03 PM.
 */
public class ArmorForgeShapelessRecipeHandler implements IRecipeHandler<ShapelessRecipes> {

    @Nonnull
    private final IGuiHelper guiHelper;

    public ArmorForgeShapelessRecipeHandler(@Nonnull IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    @Nonnull
    public Class<ShapelessRecipes> getRecipeClass() {
        return ShapelessRecipes.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return Constants.Compat.JEI_CATEGORY_ARMOR_FORGE;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull ShapelessRecipes recipe) {
        return Constants.Compat.JEI_CATEGORY_ARMOR_FORGE;
    }

    @Override
    @Nonnull
    public IRecipeWrapper getRecipeWrapper(@Nonnull ShapelessRecipes recipe) {
        return new ArmorForgeShapelessRecipeWrapper(guiHelper, recipe);
    }

    @Override
    public boolean isRecipeValid(@Nonnull ShapelessRecipes recipe) {
        if (recipe.getRecipeOutput() == null) {
            String recipeInfo = ErrorUtil.getInfoFromBrokenRecipe(recipe, this);
            Log.error("Recipe has no output. {}", recipeInfo);
            return false;
        }
        int inputCount = 0;
        for (Object input : recipe.recipeItems) {
            if (input instanceof ItemStack) {
                inputCount++;
            } else {
                String recipeInfo = ErrorUtil.getInfoFromBrokenRecipe(recipe, this);
                Log.error("Recipe has an input that is not an ItemStack. {}", recipeInfo);
                return false;
            }
        }
        if (inputCount > 9) {
            String recipeInfo = ErrorUtil.getInfoFromBrokenRecipe(recipe, this);
            Log.error("Recipe has too many inputs. {}", recipeInfo);
            return false;
        }
        return inputCount > 0;
    }
}