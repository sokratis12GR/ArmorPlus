/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.championbench;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.utils.CraftingUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static net.thedragonteam.armorplus.registry.APBlocks.ultiTechBench;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * net.thedragonteam.armorplus.api.crafting.hightechbench
 * ArmorPlus created by sokratis12GR on 6/19/2016 12:29PM.
 * - TheDragonTeam
 */
public class ChampionBenchCraftingManager {
    /**
     * The
     * static instance of
     * this class
     */
    private static final ChampionBenchCraftingManager INSTANCE = new ChampionBenchCraftingManager();
    private final List<IRecipe> recipes = Lists.newArrayList();

    private ChampionBenchCraftingManager() {
        this.addRecipe(new CBShapedOreRecipe(getItemStack(ultiTechBench),
            "LLLLLLLLL",
            "LLLLLLLLL",
            "LLLLLLLLL",
            "LLLLLLLLL",
            "LLLLLLLLL",
            "LLLLLLLLL",
            "LLLLLLLLL",
            "LLLLLLLLL",
            "LLLLLLLLL",
            'L', getItemStack(lavaCrystal, 1)
        ));

        this.recipes.sort((pCompare1, pCompare2) -> Integer.compare(pCompare2.getRecipeSize(), pCompare1.getRecipeSize()));
    }

    /**
     * Returns the
     * static instance of
     * this class
     */
    public static ChampionBenchCraftingManager getInstance() {
        // The static instance of this class
        return INSTANCE;
    }

    /**
     * Adds a shaped recipe to the games recipe list.
     */
    public CBShapedRecipe addRecipe(ItemStack stack, Object... recipeComponents) {
        StringBuilder s = new StringBuilder();
        int index = 0, width = 0, height = 0;

        if (recipeComponents[index] instanceof String[]) {
            String[] astring = (String[]) recipeComponents[index++];

            for (String s2 : astring) {
                ++height;
                width = s2.length();
                s.append(s2);
            }
        } else {
            while (recipeComponents[index] instanceof String) {
                String s1 = (String) recipeComponents[index++];
                ++height;
                width = s1.length();
                s.append(s1);
            }
        }

        Map<Character, ItemStack> map;

        for (map = Maps.newHashMap(); index < recipeComponents.length; index += 2) {
            Character character = (Character) recipeComponents[index];
            ItemStack itemstack = ItemStack.EMPTY;

            if (recipeComponents[index + 1] instanceof Item) {
                itemstack = new ItemStack((Item) recipeComponents[index + 1]);
            } else if (recipeComponents[index + 1] instanceof Block) {
                itemstack = new ItemStack((Block) recipeComponents[index + 1], 1, 32767);
            } else if (recipeComponents[index + 1] instanceof ItemStack) {
                itemstack = (ItemStack) recipeComponents[index + 1];
            }

            map.put(character, itemstack);
        }

        ItemStack[] aitemstack = new ItemStack[width * height];

        IntStream.range(0, width * height).forEachOrdered(l -> {
            char c0 = s.charAt(l);
            aitemstack[l] = map.containsKey(c0) ? map.get(c0).copy() : ItemStack.EMPTY;
        });

        CBShapedRecipe shapedrecipes = new CBShapedRecipe(width, height, aitemstack, stack);
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
    }

    /**
     * Adds a shapeless crafting recipe to the the game.
     */
    public void addShapelessRecipe(ItemStack stack, Object... recipeComponents) {
        List<ItemStack> list = Lists.newArrayList();
        CraftingUtils.addShapelessRecipe(list, recipeComponents);
        this.recipes.add(new CBShapelessRecipe(stack, list));
    }

    /**
     * Adds an IRecipe to the list of crafting recipes.
     */
    public void addRecipe(IRecipe recipe) {
        this.recipes.add(recipe);
    }

    /**
     * Removes an IRecipe to the list of crafting recipes.
     */
    public void removeRecipe(IRecipe recipe) {
        this.recipes.remove(recipe);
    }

    /**
     * Retrieves an ItemStack that has multiple recipes for it.
     */
    public ItemStack findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn) {
        return CraftingUtils.findMatchingRecipe(recipes, craftMatrix, worldIn);
    }

    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting craftMatrix, World worldIn) {
        return CraftingUtils.getRemainingItems(recipes, craftMatrix, worldIn);
    }

    public List<IRecipe> getRecipeList() {
        return this.recipes;
    }
}