/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.registry;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.recipe.ARPHighTechBenchRecipe;

import java.util.ArrayList;
import java.util.List;

/**
 * net.thedragonteam.armorplus.api.registry
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:47 PM.
 * - TheDragonTeam
 */
public class ARPHighTechBenchRecipeRegistry {
    private static List<ARPHighTechBenchRecipe> recipeList = new ArrayList<ARPHighTechBenchRecipe>();

    public static void registerRecipe(ARPHighTechBenchRecipe recipe) {
        recipeList.add(recipe);
    }

    public static void registerRecipe(ItemStack outputStack, Object... objects) {
        registerRecipe(new ARPHighTechBenchRecipe(outputStack, objects));
    }

    public static ARPHighTechBenchRecipe getMatchingRecipe(List<ItemStack> itemList, World world, BlockPos pos) {
        for (ARPHighTechBenchRecipe recipe : recipeList) {
            if (recipe.matches(itemList, world, pos)) {
                return recipe;
            }
        }

        return null;
    }

    public static List<ARPHighTechBenchRecipe> getRecipeList() {
        return new ArrayList<ARPHighTechBenchRecipe>(recipeList);
    }
}