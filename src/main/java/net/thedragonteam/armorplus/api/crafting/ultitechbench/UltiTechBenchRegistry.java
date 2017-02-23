/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class UltiTechBenchRegistry {

    /**
     * Created a 5x5 shaped recipe
     *
     * @param output is the recipe's result
     * @param params are the parameters for the recipe
     * @result example: UltiTechBenchRegistry.addInfusingRecipe(new ItemStack(Items.Stick), "XXXXX", "XXXXX", "XXXXX", "XXXXX", XXXXX", 'X', Items.Stick )
     */
    public static void addRecipe(ItemStack output, Object... params) {
        addShapedRecipe(output, params);
    }

    public static IRecipe addShapedRecipe(ItemStack output, Object... params) {
        return UltiTechBenchCraftingManager.getInstance().addRecipe(output, params);
    }

    public static void addShapelessRecipe(ItemStack output, Object... params) {
        UltiTechBenchCraftingManager.getInstance().addShapelessRecipe(output, params);
    }

    /**
     * @param recipe {@link UltiTechBenchCraftingManager#addRecipe(IRecipe)}
     */
    public static void addRecipe(IRecipe recipe) {
        UltiTechBenchCraftingManager.getInstance().getRecipeList().add(recipe);
    }

    /**
     * @param recipe {@link UltiTechBenchCraftingManager#removeRecipe(IRecipe)} (IRecipe)}
     */
    public static void removeRecipe(IRecipe recipe) {
        UltiTechBenchCraftingManager.getInstance().getRecipeList().remove(recipe);
    }
}
