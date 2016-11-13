/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.registry;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.recipe.HighTechBenchRecipe;

import java.util.ArrayList;
import java.util.List;

/**
 * net.thedragonteam.armorplus.api.registry
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:47 PM.
 * - TheDragonTeam
 */
public class HighTechBenchRecipeRegistry {
    private static List<HighTechBenchRecipe> recipeList = new ArrayList<>();

    public static void registerRecipe(HighTechBenchRecipe recipe) {
        recipeList.add(recipe);
    }

    public static void registerRecipe(ItemStack outputStack, Object... objects) {
        registerRecipe(new HighTechBenchRecipe(outputStack, objects));
    }

    public static HighTechBenchRecipe getMatchingRecipe(List<ItemStack> itemList, World world, BlockPos pos) {
        for (HighTechBenchRecipe recipe : recipeList) if (recipe.matches(itemList, world, pos)) return recipe;

        return null;
    }

    public static List<HighTechBenchRecipe> getRecipeList() {
        return new ArrayList<>(recipeList);
    }
}