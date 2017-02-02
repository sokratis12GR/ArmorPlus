/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench_new;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.container.inventory.InventoryCraftingNew;

import java.util.List;

/**
 * net.thedragonteam.armorplus.api.crafting.benches
 * ArmorPlus created by sokratis12GR on 6/19/2016 12:29PM.
 * - TheDragonTeam
 */
public class WorkbenchNewCraftingManager {
    /**
     * The
     * static instance of
     * this class
     */
    private static final WorkbenchNewCraftingManager INSTANCE = new WorkbenchNewCraftingManager();
    private final List<IRecipe> recipes = Lists.newArrayList();

    private WorkbenchNewCraftingManager() {
        this.recipes.sort((pCompare1, pCompare2) -> ((pCompare2.getRecipeSize() < pCompare1.getRecipeSize() ? -1 : (pCompare2.getRecipeSize() > pCompare1.getRecipeSize() ? 1 : 0))));
    }

    /**
     * Returns the
     * static instance of
     * this class
     */
    public static WorkbenchNewCraftingManager getInstance() {
        // The static instance of this class
        return INSTANCE;
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
    public ItemStack findMatchingRecipe(InventoryCraftingNew craftMatrix, World worldIn) {
        return this.recipes.stream().filter(irecipe -> irecipe.matches(craftMatrix, worldIn)).findFirst().map(irecipe -> irecipe.getCraftingResult(craftMatrix)).orElse(ItemStack.EMPTY);
    }

    public NonNullList<ItemStack> getRemainingItems(InventoryCraftingNew craftMatrix, World worldIn) {
        for (IRecipe irecipe : this.recipes)
            if (irecipe.matches(craftMatrix, worldIn)) return irecipe.getRemainingItems(craftMatrix);

        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(craftMatrix.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < nonnulllist.size(); ++i) nonnulllist.set(i, craftMatrix.getStackInSlot(i));

        return nonnulllist;
    }

    public List<IRecipe> getRecipeList() {
        return this.recipes;
    }
}