/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench;

import com.sofodev.armorplus.api.crafting.IRecipe;
import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import net.minecraft.item.ItemStack;

public class HighTechBenchRegistry {

    /**
     * Created a 5x5 shaped recipe
     *
     * @param output is the recipe's result
     * @param params are the parameters for the recipe
     */
    public static void addRecipe(ItemStack output, Object... params) {
        addShapedRecipe(output, params);
    }

    public static IRecipe addShapedRecipe(ItemStack output, Object... params) {
        return BaseCraftingManager.getHTBInstance().addRecipe(output, params);
    }

    public static void addShapelessRecipe(ItemStack output, Object... params) {
        BaseCraftingManager.getHTBInstance().addShapelessRecipe(output, params);
    }

    /**
     * @param recipe {@link BaseCraftingManager#addRecipe(IRecipe)}
     */
    public static void addRecipe(IRecipe recipe) {
        BaseCraftingManager.getHTBInstance().getRecipeList().add(recipe);
    }

    /**
     * @param recipe {@link BaseCraftingManager#removeRecipe(IRecipe)} (IRecipe)}
     */
    public static void removeRecipe(IRecipe recipe) {
        BaseCraftingManager.getHTBInstance().getRecipeList().remove(recipe);
    }
}
