/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.lavainfuser;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofodev.armorplus.compat.crafttweaker.lavainfuser.LavaInfuserRecipe;
import com.sofodev.thedragonlib.util.LogHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.sofodev.armorplus.util.Utils.getBlock;
import static com.sofodev.armorplus.util.Utils.getItem;

/**
 * @author Sokratis Fotkatzikis
 */
public class LavaInfuserManager {
    private static final LavaInfuserManager INSTANCE = new LavaInfuserManager();
    private final Map<ItemStack, ItemStack> infusingList = Maps.newHashMap();
    private final Map<ItemStack, Double> experienceList = Maps.newHashMap();
    private final List<LavaInfuserRecipe> recipes = Lists.newArrayList();

    private LavaInfuserManager() {
        this.addInfusingRecipe(new ItemStack(getItem("lava_crystal"), 1), new ItemStack(getItem("infused_lava_crystal")), 0.1D);
        this.addInfusingRecipe(new ItemStack(getBlock("block_lava_crystal")), new ItemStack(getBlock("block_infused_lava_crystal")), 0.2D);
        this.addInfusingRecipe(getBlock("compressed_obsidian"), new ItemStack(getBlock("block_infused_obsidian")), 0.2D);
    }

    /**
     * Returns an instance of LavaInfuserCraftingManager.
     */
    public static LavaInfuserManager getInstance() {
        return INSTANCE;
    }

    public void addInfusingRecipe(LavaInfuserRecipe recipe) {
        this.infusingList.put(recipe.input, recipe.output);
        this.experienceList.put(recipe.output, recipe.xp);
        this.recipes.add(recipe);
    }

    public void addInfusingRecipe(IItemProvider input, ItemStack stack, double experience) {
        this.addInfusingRecipe(new ItemStack(input), stack, experience);
    }

    public void addInfusingRecipe(ItemStack input, ItemStack stack) {
        this.addInfusingRecipe(input, stack, 0.0D);
    }

    /**
     * Adds a infusing recipe using an ItemStack as the input for the recipe.
     */
    public void addInfusingRecipe(ItemStack input, ItemStack stack, double experience) {
        if (!getInfusingResult(input).isEmpty()) {
            LogHelper.info("Ignored infusing recipe with conflicting input: " + input + " = " + stack);
            return;
        }
        this.infusingList.put(input, stack);
        this.experienceList.put(stack, experience);
        this.recipes.add(new LavaInfuserRecipe(input, stack, experience));
    }

    /**
     * Removes an IRecipe to the list of crafting recipes.
     */
    public void removeFromRecipe(ItemStack recipe) {
        this.infusingList.remove(recipe);
    }

    /**
     * Removes an IRecipe to the list of crafting recipes.
     */
    public void removeRecipe(LavaInfuserRecipe recipe) {
        this.recipes.remove(recipe);
    }

    /**
     * Returns the infusing result of an item.
     */
    public ItemStack getInfusingResult(ItemStack stack) {
        for (Map.Entry<ItemStack, ItemStack> entry : this.infusingList.entrySet()) {
            if (this.compareItemStacks(stack, entry.getKey())) {
                return Optional.of(entry).map(Map.Entry::getValue).orElse(ItemStack.EMPTY);
            }
        }
        return Optional.<Map.Entry<ItemStack, ItemStack>>empty().map(Map.Entry::getValue).orElse(ItemStack.EMPTY);
    }

    /**
     * Compares two itemstacks to ensure that they are the same. This checks both the item and the metadata of the item.
     */
    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
        return stack2.getItem() == stack1.getItem();
    }

    public Map<ItemStack, ItemStack> getInfusingList() {
        return this.infusingList;
    }

    public List<LavaInfuserRecipe> getRecipeList() {
        return this.recipes;
    }

    public double getInfusingExperience(ItemStack stack) {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        for (Map.Entry<ItemStack, Double> entry : this.experienceList.entrySet()) {
            if (this.compareItemStacks(stack, entry.getKey())) {
                return Optional.of(entry).map(Map.Entry::getValue).orElse(0.0D);
            }
        }
        return Optional.<Map.Entry<ItemStack, Double>>empty().map(Map.Entry::getValue).orElse(0.0D);
    }
}