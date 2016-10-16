/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.ultitechbench;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * net.thedragonteam.armorplus.api.crafting.ultitechbench
 * ArmorPlus created by sokratis12GR on 10/16/2016 1:26 PM.
 * - TheDragonTeam
 */
public interface IRecipe {
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    boolean matches(InventoryCrafting inv, World worldIn);

    /**
     * Returns an Item that is the result of this recipe
     */
    @Nullable
    ItemStack getCraftingResult(InventoryCrafting inv);

    /**
     * Returns the size of the recipe area
     */
    int getRecipeSize();

    @Nullable
    ItemStack getRecipeOutput();

    ItemStack[] getRemainingItems(InventoryCrafting inv);

}