package net.thedragonteam.armorplus.compat.jei.base;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.thedragonteam.armorplus.compat.jei.JEIUtils;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

public class ShapedOreRecipeWrapper implements IShapedCraftingRecipeWrapper {

    private final IJeiHelpers jeiHelpers;
    private final IRecipe recipe;
    private final Object[] inputItems;
    private final int width;
    private final int height;

    public ShapedOreRecipeWrapper(IJeiHelpers jeiHelpers, IRecipe recipe, Object[] inputItems, int width, int height) {
        this.jeiHelpers = jeiHelpers;
        this.recipe = recipe;
        this.inputItems = inputItems;
        this.width = width;
        this.height = height;
        stream(inputItems).filter(input -> input instanceof ItemStack).map(input -> (ItemStack) input).filter(itemStack -> !itemStack.isEmpty() && itemStack.getCount() != 1).forEachOrdered(itemStack -> itemStack.setCount(1));
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        JEIUtils.getIngredients(ingredients, recipe, jeiHelpers, asList(inputItems));
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

}