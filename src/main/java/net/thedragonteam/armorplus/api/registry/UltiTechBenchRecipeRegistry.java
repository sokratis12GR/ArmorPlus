/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.registry;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.recipe.UltiTechBenchRecipe;

import java.util.ArrayList;
import java.util.List;

/**
 * net.thedragonteam.armorplus.api.registry
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:47 PM.
 * - TheDragonTeam
 */
public class UltiTechBenchRecipeRegistry {
    private static List<UltiTechBenchRecipe> recipeList = new ArrayList<UltiTechBenchRecipe>();

    public static void registerRecipe(UltiTechBenchRecipe recipe) {
        recipeList.add(recipe);
    }

    public static void registerRecipe(ItemStack outputStack, Object... objects) {
        registerRecipe(new UltiTechBenchRecipe(outputStack, objects));
    }

    public static UltiTechBenchRecipe getMatchingRecipe(List<ItemStack> itemList, World world, BlockPos pos) {
        for (UltiTechBenchRecipe recipe : recipeList) {
            if (recipe.matches(itemList, world, pos)) {
                return recipe;
            }
        }

        return null;
    }

    public static List<UltiTechBenchRecipe> getRecipeList() {
        return new ArrayList<UltiTechBenchRecipe>(recipeList);
    }
}