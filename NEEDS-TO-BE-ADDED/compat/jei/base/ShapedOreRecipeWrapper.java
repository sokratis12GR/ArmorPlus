/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.jei.base;

//public class ShapedOreRecipeWrapper implements IShapedCraftingRecipeWrapper {
//
//    private final IJeiHelpers jeiHelpers;
//    private final IRecipe recipe;
//    private final Object[] inputItems;
//    private final int width;
//    private final int height;
//
//    public ShapedOreRecipeWrapper(IJeiHelpers jeiHelpers, IRecipe recipe, Object[] inputItems, int width, int height) {
//        this.jeiHelpers = jeiHelpers;
//        this.recipe = recipe;
//        this.inputItems = inputItems;
//        this.width = width;
//        this.height = height;
//        Arrays.stream(inputItems).filter(input -> input instanceof ItemStack).map(input -> (ItemStack) input).filter(itemStack -> !itemStack.isEmpty() && itemStack.getCount() != 1).forEach(itemStack -> itemStack.setCount(1));
//    }
//
//    @Override
//    public void getIngredients(IIngredients ingredients) {
//        JEIUtils.getIngredients(ingredients, recipe, jeiHelpers, Arrays.asList(inputItems));
//    }
//
//    @Override
//    public int getWidth() {
//        return this.width;
//    }
//
//    @Override
//    public int getHeight() {
//        return this.height;
//    }
//
//}