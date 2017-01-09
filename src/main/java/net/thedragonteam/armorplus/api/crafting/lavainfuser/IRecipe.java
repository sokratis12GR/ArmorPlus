/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.lavainfuser;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

public interface IRecipe
{
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    boolean matches(IItemHandler itemHandler, World worldIn);

    /**
     * Returns an Item that is the result of this recipe
     */
    ItemStack getCraftingResult(IItemHandler itemHandler);

    /**
     * Returns the size of the recipe area
     */
    int getRecipeSize();

    ItemStack getRecipeOutput();

    NonNullList<ItemStack> getRemainingItems(IItemHandler itemHandler);
}