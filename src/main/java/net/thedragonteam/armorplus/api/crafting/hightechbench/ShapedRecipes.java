/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;

/**
 * net.thedragonteam.armorplus.api.crafting.benches
 * ArmorPlus created by sokratis12GR on 6/19/2016 1:27 PM.
 * - TheDragonTeam
 */
public class ShapedRecipes implements IRecipe {

    /**
     * How many horizontal slots this recipe is wide.
     */
    public final int width;
    /**
     * How many vertical slots this recipe uses.
     */
    public final int height;
    /**
     * Is a array of ItemStack that composes the recipe.
     */
    public final ItemStack[] input;
    /**
     * Is the ItemStack that you get when craft the recipe.
     */
    private final ItemStack recipeOutput;
    private boolean copyIngredientNBT;

    public ShapedRecipes(int width, int height, ItemStack[] input, ItemStack output) {
        this.width = width;
        this.height = height;
        this.input = input;
        this.recipeOutput = output;
    }

    @Nullable
    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    public ItemStack[] getRemainingItems(InventoryCrafting inv) {
        ItemStack[] aitemstack = new ItemStack[inv.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            aitemstack[i] = ForgeHooks.getContainerItem(itemstack);
        }

        return aitemstack;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting inv, World worldIn) {
        for (int i = 0; i <= 4 - this.width; ++i) {
            for (int j = 0; j <= 4 - this.height; ++j) {
                if (this.checkMatch(inv, i, j, true) || this.checkMatch(inv, i, j, false)) {
                    return true;
                }

            }
        }

        return false;
    }

    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     */
    private boolean checkMatch(InventoryCrafting inv, int width, int height, boolean mirrored) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                int k = i - width;
                int l = j - height;
                ItemStack itemstack = null;

                if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
                    itemstack = mirrored ? this.input[this.width - k - 1 + l * this.width] : this.input[k + l * this.width];
                }

                ItemStack itemstack1 = inv.getStackInRowAndColumn(i, j);

                if (itemstack1 != null || itemstack != null) {
                    if (itemstack1 == null || itemstack == null || itemstack.getItem() != itemstack1.getItem() || itemstack.getMetadata() != 32767 && itemstack.getMetadata() != itemstack1.getMetadata()) {
                        return false;
                    }

                }
            }
        }

        return true;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Nullable
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack itemstack = this.getRecipeOutput().copy();

        if (this.copyIngredientNBT) {
            for (int i = 0; i < inv.getSizeInventory(); ++i) {
                ItemStack itemstack1 = inv.getStackInSlot(i);

                if (itemstack1 != null && itemstack1.hasTagCompound()) {
                    itemstack.setTagCompound(itemstack1.getTagCompound().copy());
                }
            }
        }

        return itemstack;
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize() {
        return this.width * this.height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}