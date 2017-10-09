/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.lavainfuser;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Map;
import java.util.Optional;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class LavaInfuserManager {
    private static final LavaInfuserManager INSTANCE = new LavaInfuserManager();
    private final Map<ItemStack, ItemStack> infusingList = Maps.newHashMap();
    private final Map<ItemStack, Double> experienceList = Maps.newHashMap();

    private LavaInfuserManager() {
        this.addInfusingRecipe(getItemStack(ModItems.lavaCrystal, 0), getItemStack(ModItems.lavaCrystal, 1), 0.1D);
        this.addInfusingRecipe(getItemStack(ModBlocks.blockLavaCrystal), getItemStack(ModBlocks.blockInfusedLavaCrystal), 0.2D);
        this.addInfusingRecipe(ModBlocks.compressedObsidian, getItemStack(ModBlocks.blockLavaInfusedObsidian), 0.2D);
    }

    /**
     * Returns an instance of LavaInfuserCraftingManager.
     */
    public static LavaInfuserManager getInstance() {
        return INSTANCE;
    }

    public void addInfusingRecipe(Object input, ItemStack stack, double experience) {
        if (input instanceof Block) {
            this.addInfusingRecipe(getItemStack(input), stack, experience);
        } else if (input instanceof Item) {
            this.addInfusingRecipe(getItemStack(input, 32767), stack, experience);
        }
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
    }

    /**
     * Removes an IRecipe to the list of crafting recipes.
     */
    public void removeFromRecipe(ItemStack recipe) {
        this.infusingList.remove(recipe);
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
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getInfusingList() {
        return this.infusingList;
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