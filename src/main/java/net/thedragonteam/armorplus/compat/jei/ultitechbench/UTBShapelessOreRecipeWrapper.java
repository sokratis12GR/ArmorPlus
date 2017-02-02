/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.ultitechbench;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.util.BrokenCraftingRecipeException;
import mezz.jei.util.ErrorUtil;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapelessOreRecipe;

import javax.annotation.Nonnull;
import java.util.List;

public class UTBShapelessOreRecipeWrapper extends BlankRecipeWrapper implements IRecipeWrapper {
    private final IJeiHelpers jeiHelpers;
    private final ShapelessOreRecipe recipe;

    public UTBShapelessOreRecipeWrapper(IJeiHelpers jeiHelpers, ShapelessOreRecipe recipe) {
        this.jeiHelpers = jeiHelpers;
        this.recipe = recipe;
        for (Object input : this.recipe.getInput()) {
            if (input instanceof ItemStack) {
                ItemStack itemStack = (ItemStack) input;
                if (itemStack.getCount() != 1) {
                    itemStack.setCount(1);
                }
            }
        }
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        IStackHelper stackHelper = jeiHelpers.getStackHelper();
        ItemStack recipeOutput = recipe.getRecipeOutput();

        try {
            List<List<ItemStack>> inputs = stackHelper.expandRecipeItemStackInputs(recipe.getInput());
            ingredients.setInputLists(ItemStack.class, inputs);

            if (!recipeOutput.isEmpty()) {
                ingredients.setOutput(ItemStack.class, recipeOutput);
            }
        } catch (RuntimeException e) {
            String info = ErrorUtil.getInfoFromBrokenCraftingRecipe(recipe, recipe.getInput(), recipeOutput);
            throw new BrokenCraftingRecipeException(info, e);
        }
    }
}