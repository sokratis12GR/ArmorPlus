package net.thedragonteam.armorplus.api.crafting.advarmorforge;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * sokratis12GR.ArmorPlus.api.crafting
 * ArmorPlus created by sokratis12GR on 6/19/2016 1:26 PM.
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