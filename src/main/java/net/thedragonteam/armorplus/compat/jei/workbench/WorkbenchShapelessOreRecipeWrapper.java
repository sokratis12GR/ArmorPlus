/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.compat.jei.workbench;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.plugins.vanilla.VanillaPlugin;
import mezz.jei.plugins.vanilla.crafting.AbstractShapelessRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapelessOreRecipe;

import java.util.Collections;
import java.util.List;

public class WorkbenchShapelessOreRecipeWrapper extends AbstractShapelessRecipeWrapper {

    private final ShapelessOreRecipe recipe;

    public WorkbenchShapelessOreRecipeWrapper(IGuiHelper guiHelper, ShapelessOreRecipe recipe) {
        super(guiHelper);
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
    public void getIngredients(IIngredients ingredients) {
        IStackHelper stackHelper = VanillaPlugin.jeiHelpers.getStackHelper();

        List<List<ItemStack>> inputs = stackHelper.expandRecipeItemStackInputs(recipe.getInput());
        ingredients.setInputLists(ItemStack.class, inputs);

        ItemStack recipeOutput = recipe.getRecipeOutput();
        if (recipeOutput != null) {
            ingredients.setOutput(ItemStack.class, recipeOutput);
        }
    }

    @Override
    public List getInputs() {
        return recipe.getInput();
    }

    @Override
    public List<ItemStack> getOutputs() {
        return Collections.singletonList(recipe.getRecipeOutput());
    }
}