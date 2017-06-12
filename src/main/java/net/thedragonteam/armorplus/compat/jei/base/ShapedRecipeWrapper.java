package net.thedragonteam.armorplus.compat.jei.base;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.thedragonteam.armorplus.compat.jei.JEIUtils;

import java.util.Arrays;

public class ShapedRecipeWrapper implements IShapedCraftingRecipeWrapper {

    private final IRecipe recipe;
    private final ItemStack[] inputItems;
    private final int width;
    private final int height;

    public ShapedRecipeWrapper(IRecipe recipe, ItemStack[] inputItems, int width, int height) {
        this.recipe = recipe;
        this.inputItems = inputItems;
        this.width = width;
        this.height = height;
        for (ItemStack itemStack : inputItems) {
            if (!itemStack.isEmpty() && itemStack.getCount() != 1) {
                itemStack.setCount(1);
            }
        }
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        JEIUtils.getIngredients(ingredients, recipe, Arrays.asList(inputItems));
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