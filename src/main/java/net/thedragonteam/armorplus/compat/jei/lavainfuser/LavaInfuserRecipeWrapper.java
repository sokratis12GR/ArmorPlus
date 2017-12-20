package net.thedragonteam.armorplus.compat.jei.lavainfuser;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.compat.crafttweaker.lavainfuser.LavaInfuserRecipe;

public class LavaInfuserRecipeWrapper implements IRecipeWrapper {

    public final LavaInfuserRecipe recipe;

    public LavaInfuserRecipeWrapper(LavaInfuserRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ItemStack recipeOutput = recipe.output;

        ingredients.setInput(ItemStack.class, recipe.input);
        if (!recipeOutput.isEmpty()) {
            ingredients.setOutput(ItemStack.class, recipeOutput);
        }
    }

}