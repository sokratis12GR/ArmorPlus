package net.thedragonteam.armorplus.compat.jei.base;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.compat.jei.JEIUtils;

public class ShapelessOreRecipeWrapper implements IRecipeWrapper {

    private final IJeiHelpers jeiHelpers;
    private final IRecipe recipe;
    private final NonNullList<Object> inputItems;

    public ShapelessOreRecipeWrapper(IJeiHelpers jeiHelpers, IRecipe recipe, NonNullList<Object> inputItems) {
        this.jeiHelpers = jeiHelpers;
        this.recipe = recipe;
        this.inputItems = inputItems;
        inputItems.stream().filter(itemStack -> itemStack instanceof ItemStack).filter(itemStack -> !((ItemStack) itemStack).isEmpty() && ((ItemStack) itemStack).getCount() != 1).forEach(itemStack -> ((ItemStack) itemStack).setCount(1));
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        JEIUtils.getIngredients(ingredients, recipe, jeiHelpers, inputItems);
    }
}