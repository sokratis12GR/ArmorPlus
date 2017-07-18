/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.championbench;

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
    public final ItemStack[] recipeItems;
    /**
     * Is the ItemStack that you get when craft the recipe.
     */
    private final ItemStack recipeOutput;
    private boolean copyIngredientNBT;

    public ShapedRecipes(int width, int height, ItemStack[] ingredientsIn, ItemStack output) {
        this.recipeWidth = width;
        this.recipeHeight = height;
        this.recipeItems = ingredientsIn;

        IntStream.range(0, this.recipeItems.length).filter(i -> this.recipeItems[i].isEmpty()).forEachOrdered(i -> this.recipeItems[i] = ItemStack.EMPTY);
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
        for (int i = 0; i <= 10 - this.recipeWidth; ++i) {
            for (int j = 0; j <= 10 - this.recipeHeight; ++j) {
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
    private boolean checkMatch(InventoryCrafting inv, int p_77573_2_, int p_77573_3_, boolean mirrored_) {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                int k = i - p_77573_2_;
                int l = j - p_77573_3_;
                ItemStack itemstack = ItemStack.EMPTY;

                if (k >= 0 && l >= 0 && k < this.recipeWidth && l < this.recipeHeight) {
                    itemstack = mirrored_ ? this.recipeItems[this.recipeWidth - k - 1 + l * this.recipeWidth] : this.recipeItems[k + l * this.recipeWidth];
                }

                ItemStack itemstack1 = inv.getStackInRowAndColumn(i, j);

                if (!itemstack1.isEmpty() || !itemstack.isEmpty()) {
                    if (itemstack1.isEmpty() != itemstack.isEmpty() || itemstack.getItem() != itemstack1.getItem() || itemstack.getMetadata() != 32767 && itemstack.getMetadata() != itemstack1.getMetadata()) {
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
     * Returns the size of the recipe area
     */
    @Override
    public int getRecipeSize() {
        return this.recipeWidth * this.recipeHeight;
    }
}