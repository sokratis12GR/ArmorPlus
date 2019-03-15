/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.jei.base;

//public class ShapelessOreRecipeWrapper implements IRecipeWrapper {
//
//    private final IJeiHelpers jeiHelpers;
//    private final IRecipe recipe;
//    private final NonNullList<Object> inputItems;
//
//    public ShapelessOreRecipeWrapper(IJeiHelpers jeiHelpers, IRecipe recipe, NonNullList<Object> inputItems) {
//        this.jeiHelpers = jeiHelpers;
//        this.recipe = recipe;
//        this.inputItems = inputItems;
//        inputItems.stream().filter(itemStack -> itemStack instanceof ItemStack).filter(itemStack
//            -> !((ItemStack) itemStack).isEmpty() && ((ItemStack) itemStack).getCount() != 1
//        ).forEach(itemStack
//            -> ((ItemStack) itemStack).setCount(1)
//        );
//    }
//
//    @Override
//    public void getIngredients(IIngredients ingredients) {
//        JEIUtils.getIngredients(ingredients, recipe, jeiHelpers, inputItems);
//    }
//}