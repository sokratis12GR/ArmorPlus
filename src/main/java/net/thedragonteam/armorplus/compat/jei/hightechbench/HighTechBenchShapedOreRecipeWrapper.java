/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.compat.jei.hightechbench;

import mezz.jei.Internal;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedOreRecipe;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HighTechBenchShapedOreRecipeWrapper implements IShapedCraftingRecipeWrapper {

    private final ShapedOreRecipe recipe;
    private final int width;
    private final int height;

    public HighTechBenchShapedOreRecipeWrapper(ShapedOreRecipe recipe) {
        this.recipe = recipe;
        for (Object input : this.recipe.getInput()) {
            if (input instanceof ItemStack) {
                ItemStack itemStack = (ItemStack) input;
                if (itemStack.stackSize != 1) {
                    itemStack.stackSize = 1;
                }
            }
        }
        this.width = ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, this.recipe, "width");
        this.height = ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, this.recipe, "height");
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        IStackHelper stackHelper = Internal.getStackHelper();

        List<List<ItemStack>> inputs = stackHelper.expandRecipeItemStackInputs(Arrays.asList(recipe.getInput()));
        ingredients.setInputLists(ItemStack.class, inputs);

        ItemStack recipeOutput = recipe.getRecipeOutput();
        if (recipeOutput != null) {
            ingredients.setOutput(ItemStack.class, recipeOutput);
        }
    }

    @Override
    public List getInputs() {
        return Arrays.asList(recipe.getInput());
    }

    @Override
    public List<ItemStack> getOutputs() {
        return Collections.singletonList(recipe.getRecipeOutput());
    }

    @Override
    public List<FluidStack> getFluidInputs() {
        return null;
    }

    @Override
    public List<FluidStack> getFluidOutputs() {
        return null;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {

    }

    @Override
    public void drawAnimations(Minecraft minecraft, int recipeWidth, int recipeHeight) {

    }

    @Nullable
    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return null;
    }

    @Override
    public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
        return false;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}