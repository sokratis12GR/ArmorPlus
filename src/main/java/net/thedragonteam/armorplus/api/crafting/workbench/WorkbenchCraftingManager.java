/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.utils.CraftingUtils;
import net.thedragonteam.armorplus.api.crafting.workbench.recipes.ModItemRecipes;
import net.thedragonteam.armorplus.api.crafting.workbench.recipes.ModOriginRecipes;
import net.thedragonteam.armorplus.api.crafting.workbench.recipes.ModSpecialMobRecipes;
import net.thedragonteam.armorplus.api.crafting.workbench.recipes.ModWeaponsTierOneRecipes;
import net.thedragonteam.armorplus.container.base.InventoryCraftingImproved;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class WorkbenchCraftingManager {
    /**
     * The
     * static instance of
     * this class
     */
    private static final WorkbenchCraftingManager INSTANCE = new WorkbenchCraftingManager();
    private final List<IRecipe> recipes = Lists.newArrayList();

    private WorkbenchCraftingManager() {
        new ModItemRecipes().addRecipes(this);
        new ModOriginRecipes().addRecipes(this);
        new ModSpecialMobRecipes().addRecipes(this);
        new ModWeaponsTierOneRecipes().addRecipes(this);

        this.recipes.sort((pCompare1, pCompare2) -> Integer.compare(pCompare2.getRecipeSize(), pCompare1.getRecipeSize()));
    }

    /**
     * Returns the
     * static instance of
     * this class
     */
    public static WorkbenchCraftingManager getInstance() {
        // The static instance of this class
        return INSTANCE;
    }

    /**
     * Adds a shaped recipe to the games recipe list.
     */
    public WBShapedRecipe addRecipe(ItemStack stack, Object... recipeComponents) {
        StringBuilder s = new StringBuilder();
        int i = 0;
        int j = 0;
        int k = 0;

        if (recipeComponents[i] instanceof String[]) {
            String[] astring = (String[]) recipeComponents[i++];

            for (String s2 : astring) {
                ++k;
                j = s2.length();
                s.append(s2);
            }
        } else {
            while (recipeComponents[i] instanceof String) {
                String s1 = (String) recipeComponents[i++];
                ++k;
                j = s1.length();
                s.append(s1);
            }
        }

        Map<Character, ItemStack> map;

        for (map = Maps.newHashMap(); i < recipeComponents.length; i += 2) {
            Character character = (Character) recipeComponents[i];
            ItemStack itemstack = ItemStack.EMPTY;

            if (recipeComponents[i + 1] instanceof Item) {
                itemstack = new ItemStack((Item) recipeComponents[i + 1]);
            } else if (recipeComponents[i + 1] instanceof Block) {
                itemstack = new ItemStack((Block) recipeComponents[i + 1], 1, 32767);
            } else if (recipeComponents[i + 1] instanceof ItemStack) {
                itemstack = (ItemStack) recipeComponents[i + 1];
            }

            map.put(character, itemstack);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        IntStream.range(0, j * k).forEachOrdered(l -> {
            char c0 = s.charAt(l);
            aitemstack[l] = map.containsKey(c0) ? map.get(c0).copy() : ItemStack.EMPTY;
        });

        WBShapedRecipe shapedrecipes = new WBShapedRecipe(j, k, aitemstack, stack);
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
    }

    /**
     * Adds a shapeless crafting recipe to the the game.
     */
    public void addShapelessRecipe(ItemStack stack, Object... recipeComponents) {
        List<ItemStack> list = Lists.newArrayList();
        CraftingUtils.addShapelessRecipe(list, recipeComponents);
        this.recipes.add(new WBShapelessRecipe(stack, list));
    }

    /**
     * Adds an IRecipe to the list of crafting recipes.
     */
    public void addRecipe(IRecipe recipe) {
        this.recipes.add(recipe);
    }

    /**
     * Adds an IRecipe to the list of crafting recipes.
     */
    public void addRecipes(IRecipe... recipes) {
        Arrays.stream(recipes).forEach(this::addRecipe);
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
    public ItemStack findMatchingRecipe(InventoryCraftingImproved craftMatrix, World worldIn) {
        return CraftingUtils.findMatchingRecipe(recipes, craftMatrix, worldIn);
    }

    public NonNullList<ItemStack> getRemainingItems(InventoryCraftingImproved craftMatrix, World worldIn) {
        return CraftingUtils.getRemainingItems(recipes, craftMatrix, worldIn);
    }

    public List<IRecipe> getRecipeList() {
        return this.recipes;
    }
}