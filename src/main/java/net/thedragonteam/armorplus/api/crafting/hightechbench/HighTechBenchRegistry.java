/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.hightechbench;

import net.minecraft.item.ItemStack;

public class HighTechBenchRegistry {

    /**
     * Created a 4x4 shaped recipe
     *
     * @param output is the recipe's result
     * @param params are the parameters for the recipe
     *
     * @result example: ARPHighTechBenchRegistry.addRecipe(new ItemStack(Items.Stick), "XXXX", "XXXX", "XXXX", "XXXX", 'X', Items.Stick )
     * @see net.thedragonteam.armorplus.api.crafting.hightechbench.recipes.ModEnderDragonRecipes
     * @see net.thedragonteam.armorplus.api.crafting.hightechbench.recipes.ModGuardianRecipes
     * @see net.thedragonteam.armorplus.api.crafting.hightechbench.recipes.ModSuperStarRecipes
     * @see net.thedragonteam.armorplus.api.crafting.hightechbench.recipes.ModTheUltimateRecipes
     */
    public static void addRecipe(ItemStack output, Object... params) {
        addShapedRecipe(output, params);
    }

    public static IRecipe addShapedRecipe(ItemStack output, Object... params) {
        return HighTechBenchCraftingManager.getInstance().addRecipe(output, params);
    }

    public static void addShapelessRecipe(ItemStack output, Object... params) {
        HighTechBenchCraftingManager.getInstance().addShapelessRecipe(output, params);
    }

    /**
     * @param recipe {@link HighTechBenchCraftingManager#addRecipe(IRecipe)}
     */
    public static void addRecipe(IRecipe recipe) {
        HighTechBenchCraftingManager.getInstance().getRecipeList().add(recipe);
    }

    /**
     * @param recipe {@link HighTechBenchCraftingManager#removeRecipe(IRecipe)} (IRecipe)}
     */
    public static void removeRecipe(IRecipe recipe) {
        HighTechBenchCraftingManager.getInstance().getRecipeList().remove(recipe);
    }
}
