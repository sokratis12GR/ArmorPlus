/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager;

public class UltiTechBenchRegistry {

    /**
     * Created a 7x7 shaped recipe
     *
     * @param output is the recipe's result
     * @param params are the parameters for the recipe
     */
    public static void addRecipe(ItemStack output, Object... params) {
        addShapedRecipe(output, params);
    }

    public static IRecipe addShapedRecipe(ItemStack output, Object... params) {
        return BaseCraftingManager.getUTBInstance().addRecipe(output, params);
    }

    public static void addShapelessRecipe(ItemStack output, Object... params) {
        BaseCraftingManager.getUTBInstance().addShapelessRecipe(output, params);
    }

    /**
     * @param recipe {@link BaseCraftingManager#addRecipe(IRecipe)}
     */
    public static void addRecipe(IRecipe recipe) {
        BaseCraftingManager.getUTBInstance().getRecipeList().add(recipe);
    }

    /**
     * @param recipe {@link BaseCraftingManager#removeRecipe(IRecipe)} (IRecipe)}
     */
    public static void removeRecipe(IRecipe recipe) {
        BaseCraftingManager.getUTBInstance().getRecipeList().remove(recipe);
    }
}
