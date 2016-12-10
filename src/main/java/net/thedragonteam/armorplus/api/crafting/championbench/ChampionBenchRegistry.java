/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.championbench;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class ChampionBenchRegistry {

    /**
     * Created a 5x5 shaped recipe
     *
     * @param output is the recipe's result
     * @param params are the parameters for the recipe
     *
     * @result example: UltiTechBenchRegistry.addRecipe(new ItemStack(Items.Stick), "XXXXX", "XXXXX", "XXXXX", "XXXXX", XXXXX", 'X', Items.Stick )
     */
    public static void addRecipe(ItemStack output, Object... params) {
        addShapedRecipe(output, params);
    }

    public static IRecipe addShapedRecipe(ItemStack output, Object... params) {
        return ChampionBenchCraftingManager.getInstance().addRecipe(output, params);
    }

    public static void addShapelessRecipe(ItemStack output, Object... params) {
        ChampionBenchCraftingManager.getInstance().addShapelessRecipe(output, params);
    }

    /**
     * @param recipe {@link ChampionBenchCraftingManager#addRecipe(IRecipe)}
     */
    public static void addRecipe(IRecipe recipe) {
        ChampionBenchCraftingManager.getInstance().getRecipeList().add(recipe);
    }

    /**
     * @param recipe {@link ChampionBenchCraftingManager#removeRecipe(IRecipe)} (IRecipe)}
     */
    public static void removeRecipe(IRecipe recipe) {
        ChampionBenchCraftingManager.getInstance().getRecipeList().remove(recipe);
    }
}
