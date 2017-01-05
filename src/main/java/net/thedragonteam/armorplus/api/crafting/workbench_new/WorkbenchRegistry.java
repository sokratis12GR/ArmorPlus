/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench_new;

public class WorkbenchRegistry {

    /**
     * @param recipe {@link WorkbenchNewCraftingManager#addRecipe(IRecipe)}
     */
    public static void addRecipe(IRecipe recipe) {
        WorkbenchNewCraftingManager.getInstance().getRecipeList().add(recipe);
    }

    /**
     * @param recipe {@link WorkbenchNewCraftingManager#removeRecipe(IRecipe)}
     */
    public static void removeRecipe(IRecipe recipe) {
        WorkbenchNewCraftingManager.getInstance().getRecipeList().remove(recipe);
    }
}
