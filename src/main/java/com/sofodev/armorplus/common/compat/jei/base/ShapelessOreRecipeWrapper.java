/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.compat.jei.base;

import com.sofodev.armorplus.api.crafting.IRecipe;
import com.sofodev.armorplus.common.compat.jei.JEIUtils;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ShapelessOreRecipeWrapper implements IRecipeWrapper {

    private final IJeiHelpers jeiHelpers;
    private final IRecipe recipe;
    private final NonNullList<Object> inputItems;

    public ShapelessOreRecipeWrapper(IJeiHelpers jeiHelpers, IRecipe recipe, NonNullList<Object> inputItems) {
        this.jeiHelpers = jeiHelpers;
        this.recipe = recipe;
        this.inputItems = inputItems;
        inputItems.stream().filter(itemStack -> itemStack instanceof ItemStack).filter(itemStack
            -> !((ItemStack) itemStack).isEmpty() && ((ItemStack) itemStack).getCount() != 1
        ).forEach(itemStack
            -> ((ItemStack) itemStack).setCount(1)
        );
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        JEIUtils.getIngredients(ingredients, recipe, jeiHelpers, inputItems);
    }
}