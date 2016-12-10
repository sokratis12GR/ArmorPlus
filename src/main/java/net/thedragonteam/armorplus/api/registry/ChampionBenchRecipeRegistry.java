/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.registry;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.recipe.ChampionBenchRecipe;

import java.util.ArrayList;
import java.util.List;

/**
 * net.thedragonteam.armorplus.api.registry
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:47 PM.
 * - TheDragonTeam
 */
public class ChampionBenchRecipeRegistry {
    private static List<ChampionBenchRecipe> recipeList = new ArrayList<>();

    public static void registerRecipe(ChampionBenchRecipe recipe) {
        recipeList.add(recipe);
    }

    public static void registerRecipe(ItemStack outputStack, Object... objects) {
        registerRecipe(new ChampionBenchRecipe(outputStack, objects));
    }

    public static ChampionBenchRecipe getMatchingRecipe(List<ItemStack> itemList, World world, BlockPos pos) {
        for (ChampionBenchRecipe recipe : recipeList)
            if (recipe.matches(itemList, world, pos)) return recipe;

        return null;
    }

    public static List<ChampionBenchRecipe> getRecipeList() {
        return new ArrayList<>(recipeList);
    }
}