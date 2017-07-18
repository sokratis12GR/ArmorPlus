/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

/**
 * net.thedragonteam.armorplus.api.crafting.benches
 * ArmorPlus created by sokratis12GR on 6/19/2016 1:27 PM.
 * - TheDragonTeam
 */
public class ShapedRecipes implements IRecipe {
    /**
     * How many horizontal itemHandler this recipe is wide.
     */
    public final int recipeWidth;
    /**
     * How many vertical itemHandler this recipe uses.
     */
    public final int recipeHeight;
    /**
     * Is a array of ItemStack that composes the recipe.
     */
    public final ItemStack[] input;
    /**
     * Is the ItemStack that you get when craft the recipe.
     */
    private final ItemStack recipeOutput;
    private boolean copyIngredientNBT;

    public ShapedRecipes(int width, int height, ItemStack[] ingredientsIn, ItemStack output) {
        this.recipeWidth = width;
        this.recipeHeight = height;
        this.input = ingredientsIn;

        IntStream.range(0, this.input.length).filter(i -> this.input[i].isEmpty()).forEachOrdered(i -> this.input[i] = ItemStack.EMPTY);

        this.recipeOutput = output;
    }

    @Override
    @Nonnull
    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    @Override
    @Nonnull
    public NonNullList<ItemStack> getRemainingItems(@Nonnull InventoryCrafting inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        IntStream.range(0, nonnulllist.size()).forEachOrdered(i -> {
            ItemStack itemstack = inv.getStackInSlot(i);
            nonnulllist.set(i, ForgeHooks.getContainerItem(itemstack));
        });

        return nonnulllist;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World worldIn) {
        for (int i = 0; i <= 4 - this.recipeWidth; ++i) {
            for (int j = 0; j <= 4 - this.recipeHeight; ++j) {
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
    private boolean checkMatch(InventoryCrafting inventoryCrafting, int width, int height, boolean isMirrored) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                int k = i - width;
                int l = j - height;
                ItemStack itemstack = ItemStack.EMPTY;

                if (k >= 0 && l >= 0 && k < this.recipeWidth && l < this.recipeHeight) {
                    itemstack = isMirrored ? this.input[this.recipeWidth - k - 1 + l * this.recipeWidth] : this.input[k + l * this.recipeWidth];
                }

                ItemStack itemstack1 = inventoryCrafting.getStackInRowAndColumn(i, j);

                if ((!itemstack1.isEmpty() || !itemstack.isEmpty()))
                    if ((itemstack1.isEmpty() != itemstack.isEmpty() || itemstack.getItem() != itemstack1.getItem() || itemstack.getMetadata() != 32767 && itemstack.getMetadata() != itemstack1.getMetadata())) {
                        return false;
                    }
            }
        }

        return true;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Override
    @Nonnull
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
        ItemStack itemstack = this.getRecipeOutput().copy();

        if (this.copyIngredientNBT) {
            IntStream.range(0, inv.getSizeInventory()).mapToObj(inv::getStackInSlot).filter(
                    itemstack1 -> !itemstack1.isEmpty() && itemstack1.hasTagCompound()
            ).map(itemstack1 -> itemstack1.getTagCompound().copy()).forEachOrdered(itemstack::setTagCompound);
        }

        return itemstack;
    }

    /**
     * Returns the input for this recipe, any mod accessing this value should never
     * manipulate the values in this array as it will effect the recipe itself.
     *
     * @return The recipes input vales.
     */
    public Object[] getInput() {
        return this.input;
    }

    /**
     * Returns the size of the recipe area
     */
    @Override
    public int getRecipeSize() {
        return this.recipeWidth * this.recipeHeight;
    }
}