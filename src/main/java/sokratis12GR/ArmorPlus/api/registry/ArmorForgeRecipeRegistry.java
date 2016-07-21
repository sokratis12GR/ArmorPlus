package sokratis12gr.armorplus.api.registry;

/**
 * sokratis12GR.ArmorPlus.api.registry
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:47 PM.
 */

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import sokratis12gr.armorplus.api.recipe.ArmorForgeRecipe;

import java.util.ArrayList;
import java.util.List;

public class ArmorForgeRecipeRegistry {
    private static List<ArmorForgeRecipe> recipeList = new ArrayList<ArmorForgeRecipe>();

    public static void registerRecipe(ArmorForgeRecipe recipe) {
        recipeList.add(recipe);
    }

    public static void registerRecipe(ItemStack outputStack, Object... objects) {
        registerRecipe(new ArmorForgeRecipe(outputStack, objects));
    }

    public static ArmorForgeRecipe getMatchingRecipe(List<ItemStack> itemList, World world, BlockPos pos) {
        for (ArmorForgeRecipe recipe : recipeList) {
            if (recipe.matches(itemList, world, pos)) {
                return recipe;
            }
        }

        return null;
    }

    public static List<ArmorForgeRecipe> getRecipeList() {
        return new ArrayList<ArmorForgeRecipe>(recipeList);
    }
}