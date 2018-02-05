/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
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

    /**
     * @param recipe {@link BaseCraftingManager#removeRecipe(IRecipe)}
     */
    public static void removeRecipe(IRecipe recipe) {
        BaseCraftingManager.getWBInstance().getRecipeList().remove(recipe);
    }
}
