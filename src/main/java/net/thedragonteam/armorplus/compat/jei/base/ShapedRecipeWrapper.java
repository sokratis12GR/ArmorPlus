package net.thedragonteam.armorplus.compat.jei.base;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.compat.jei.JEIUtils;

public class ShapedRecipeWrapper implements IShapedCraftingRecipeWrapper {

    private final IRecipe recipe;
    private final NonNullList<ItemStack> input;
    private final int width;
    private final int height;

    public ShapedRecipeWrapper(IRecipe recipe, NonNullList<ItemStack> inputItems, int width, int height) {
        this.recipe = recipe;
        this.input = inputItems;
        this.width = width;
        this.height = height;
        inputItems.stream().filter(itemStack -> !itemStack.isEmpty() && itemStack.getCount() != 1).forEach(itemStack -> itemStack.setCount(1));
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        JEIUtils.getIngredients(ingredients, recipe, input);
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