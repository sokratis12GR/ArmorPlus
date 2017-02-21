/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.old.workbench;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import mezz.jei.util.BrokenCraftingRecipeException;
import mezz.jei.util.ErrorUtil;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class WBShapedOreRecipeWrapper extends BlankRecipeWrapper implements IShapedCraftingRecipeWrapper {

    private final IJeiHelpers jeiHelpers;
    private final ShapedOreRecipe recipe;

    public WBShapedOreRecipeWrapper(IJeiHelpers jeiHelpers, ShapedOreRecipe recipe) {
        this.jeiHelpers = jeiHelpers;
        this.recipe = recipe;
        for (Object input : this.recipe.getInput()) {
            if (input instanceof ItemStack) {
                ItemStack itemStack = (ItemStack) input;
                if (itemStack.stackSize != 1) {
                    itemStack.stackSize = 1;
                }
            }
        }
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        IStackHelper stackHelper = jeiHelpers.getStackHelper();
        ItemStack recipeOutput = recipe.getRecipeOutput();

        try {
            List<List<ItemStack>> inputs = stackHelper.expandRecipeItemStackInputs(Arrays.asList(recipe.getInput()));
            ingredients.setInputLists(ItemStack.class, inputs);
            ingredients.setOutput(ItemStack.class, recipeOutput);
        } catch (RuntimeException e) {
            String info = ErrorUtil.getInfoFromBrokenCraftingRecipe(recipe, Arrays.asList(recipe.getInput()), recipeOutput);
            throw new BrokenCraftingRecipeException(info, e);
        }
    }

    @Override
    public int getWidth() {
        return recipe.getWidth();
    }

    @Override
    public int getHeight() {
        return recipe.getHeight();
    }

}