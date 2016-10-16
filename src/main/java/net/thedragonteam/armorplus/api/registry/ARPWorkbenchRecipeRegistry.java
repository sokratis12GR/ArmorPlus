/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.registry;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.recipe.ARPWorkbenchRecipe;

import java.util.ArrayList;
import java.util.List;

/**
 * net.thedragonteam.armorplus.api.registry
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:47 PM.
 * - TheDragonTeam
 */
public class ARPWorkbenchRecipeRegistry {
    private static List<ARPWorkbenchRecipe> recipeList = new ArrayList<ARPWorkbenchRecipe>();

    public static void registerRecipe(ARPWorkbenchRecipe recipe) {
        recipeList.add(recipe);
    }

    public static void registerRecipe(ItemStack outputStack, Object... objects) {
        registerRecipe(new ARPWorkbenchRecipe(outputStack, objects));
    }

    public static ARPWorkbenchRecipe getMatchingRecipe(List<ItemStack> itemList, World world, BlockPos pos) {
        for (ARPWorkbenchRecipe recipe : recipeList) {
            if (recipe.matches(itemList, world, pos)) {
                return recipe;
            }
        }

        return null;
    }

    public static List<ARPWorkbenchRecipe> getRecipeList() {
        return new ArrayList<ARPWorkbenchRecipe>(recipeList);
    }
}