/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.jei.base;

//public class ShapelessRecipeWrapper implements IRecipeWrapper {
//
//    private final IRecipe recipe;
//    private final NonNullList<ItemStack> input;
//
//    public ShapelessRecipeWrapper(IRecipe recipe, NonNullList<ItemStack> inputList) {
//        this.recipe = recipe;
//        this.input = inputList;
//        inputList.stream().filter(Objects::nonNull).filter(itemStack ->
//            !itemStack.isEmpty() && itemStack.getCount() != 1
//        ).forEachOrdered(itemStack -> itemStack.setCount(1));
//    }
//
//    @Override
//    public void getIngredients(IIngredients ingredients) {
//        JEIUtils.getIngredients(ingredients, recipe, input);
//    }
//}