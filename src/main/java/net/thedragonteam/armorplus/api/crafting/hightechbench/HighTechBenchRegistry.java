/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager;

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
