package sokratis12GR.ArmorPlus.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * sokratis12GR.ArmorPlus.crafting
 * ArmorPlus created by sokratis12GR on 6/19/2016 1:26 PM.
 */
public interface IRecipe {
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    boolean matches(net.minecraft.inventory.InventoryCrafting inv, World worldIn);

    /**
     * Returns an Item that is the result of this recipe
     */
    @Nullable
    ItemStack getCraftingResult(net.minecraft.inventory.InventoryCrafting inv);

    /**
     * Returns the size of the recipe area
     */
    int getRecipeSize();

    @Nullable
    ItemStack getRecipeOutput();

    ItemStack[] getRemainingItems(net.minecraft.inventory.InventoryCrafting inv);
}