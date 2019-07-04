/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.workbench;

import com.sofodev.armorplus.api.crafting.IRecipe;
import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import net.minecraft.item.ItemStack;

import java.util.Arrays;

/**
 * @author Sokratis Fotkatzikis
 */
public class WorkbenchRegistry {

    /**
     * Created a 3x3 shaped recipe
     *
     * @param output is the recipe's result
     * @param params are the parameters for the recipe
     */
    public static void addRecipe(ItemStack output, Object... params) {
        addShapedRecipe(output, params);
    }

    public static void addShapedRecipe(ItemStack output, Object... params) {
        BaseCraftingManager.getWBInstance().addRecipe(output, params);
    }

    public static void addShapelessRecipe(ItemStack output, Object... params) {
        BaseCraftingManager.getWBInstance().addShapelessRecipe(output, params);
    }

    /**
     * @param recipe {@link BaseCraftingManager#addRecipe(IRecipe)}
     */
    public static void addRecipe(IRecipe recipe) {
        BaseCraftingManager.getWBInstance().getRecipeList().add(recipe);
    }

    public static void addRecipe(IRecipe... recipeList) {
        Arrays.stream(recipeList).forEach(WorkbenchRegistry::addRecipe);
    }

    /**
     * @param recipe {@link BaseCraftingManager#removeRecipe(IRecipe)}
     */
    public static void removeRecipe(IRecipe recipe) {
        BaseCraftingManager.getWBInstance().getRecipeList().remove(recipe);
    }
}
