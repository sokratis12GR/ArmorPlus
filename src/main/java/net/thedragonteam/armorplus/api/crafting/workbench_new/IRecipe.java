/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench_new;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.container.inventory.InventoryCraftingNew;

public interface IRecipe
{
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    boolean matches(InventoryCraftingNew inv, World worldIn);

    /**
     * Returns an Item that is the result of this recipe
     */
    ItemStack getCraftingResult(InventoryCraftingNew inv);

    /**
     * Returns the size of the recipe area
     */
    int getRecipeSize();

    ItemStack getRecipeOutput();

    NonNullList<ItemStack> getRemainingItems(InventoryCraftingNew inv);
}