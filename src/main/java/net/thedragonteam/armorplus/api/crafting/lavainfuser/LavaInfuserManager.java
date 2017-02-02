/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.lavainfuser;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Map;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class LavaInfuserManager {
    private static final LavaInfuserManager INFUSING_BASE = new LavaInfuserManager();
    private final Map<ItemStack, ItemStack> infusingList = Maps.newHashMap();
    private final Map<ItemStack, Double> experienceList = Maps.newHashMap();

    /**
     * Returns an instance of LavaInfuserCraftingManager.
     */
    public static LavaInfuserManager getInstance() {
        return INFUSING_BASE;
    }

    private LavaInfuserManager() {
        this.addInfusingRecipe(new ItemStack(ModItems.lavaCrystal, 1, 0), getItemStack(ModItems.lavaCrystal, 1), 0.1D);
        this.addInfusingRecipeForBlock(ModBlocks.compressedObsidian, getItemStack(ModBlocks.lavaInfusedObsidian), 0.2D);
    }

    /**
     * Adds a smelting recipe, where the input item is an instance of Block.
     */
    public void addInfusingRecipeForBlock(Block input, ItemStack stack, double experience) {
        this.addInfusing(Item.getItemFromBlock(input), stack, experience);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     */
    public void addInfusing(Item input, ItemStack stack, double experience) {
        this.addInfusingRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }

    public void addInfusing(ItemStack input, ItemStack stack) {
        this.addInfusingRecipe(input, stack, 0.0D);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
    public void addInfusingRecipe(ItemStack input, ItemStack stack, double experience) {
        if (getSmeltingResult(input) != ItemStack.EMPTY) {
            LogHelper.info("Ignored smelting recipe with conflicting input: " + input + " = " + stack);
            return;
        }
        this.infusingList.put(input, stack);
        this.experienceList.put(stack, experience);
    }

    /**
     * Removes an IRecipe to the list of crafting recipes.
     */
    public void removeRecipe(ItemStack recipe) {
        this.infusingList.remove(recipe);
    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getSmeltingResult(ItemStack stack) {
        return this.infusingList.entrySet().stream().filter(entry -> this.compareItemStacks(stack, entry.getKey())).findFirst().map(Map.Entry::getValue).orElse(ItemStack.EMPTY);
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

    public double getSmeltingExperience(ItemStack stack) {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        return this.experienceList.entrySet().stream().filter(entry -> this.compareItemStacks(stack, entry.getKey())).findFirst().map(Map.Entry::getValue).orElse(0.0D);
    }
}