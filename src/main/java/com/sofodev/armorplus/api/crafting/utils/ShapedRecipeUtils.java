/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.utils;

import com.sofodev.armorplus.common.container.base.InventoryCraftingImproved;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

import static java.util.Objects.requireNonNull;

public class ShapedRecipeUtils {

    /**
     * Returns an Item that is the result of this recipe
     */
    @Nonnull
    public static ItemStack getCraftingResult(ItemStack output, boolean copyIngredientNBT, InventoryCraftingImproved inv) {
        ItemStack itemstack = output.copy();

        if (copyIngredientNBT) {
            IntStream.range(0, inv.getSizeInventory()).mapToObj(inv::getStackInSlot).filter(
                itemstack1 -> !itemstack1.isEmpty() && itemstack1.hasTagCompound()
            ).map(itemstack1 -> requireNonNull(itemstack1.getTagCompound()).copy()).forEachOrdered(itemstack::setTagCompound);
        }

        return itemstack;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public static boolean matches(int width, int height, NonNullList<ItemStack> input, InventoryCraftingImproved inv) {
        return IntStream.rangeClosed(0, inv.getWidth() - width).anyMatch(x -> IntStream.rangeClosed(0, inv.getHeight() - height).anyMatch(y -> checkMatch(width, height, input, inv, x, y, true) || checkMatch(width, height, input, inv, x, y, false)));
    }

    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     */
    private static boolean checkMatch(int width, int height, NonNullList<ItemStack> input, InventoryCraftingImproved inv, int startX, int startY, boolean isMirrored) {
        for (int x = 0; x < inv.getWidth(); ++x) {
            for (int y = 0; y < inv.getHeight(); ++y) {
                int subX = x - startX;
                int subY = y - startY;
                ItemStack stack = ItemStack.EMPTY;

                if (subX >= 0 && subY >= 0 && subX < width && subY < height) {
                    stack = isMirrored ? input.get(width - subX - 1 + subY * width) : input.get(subX + subY * width);
                }

                ItemStack slotStack = inv.getStackInRowAndColumn(x, y);

                if ((!slotStack.isEmpty() || !stack.isEmpty()) && (slotStack.isEmpty() != stack.isEmpty() || stack.getItem() != slotStack.getItem() || stack.getMetadata() != OreDictionary.WILDCARD_VALUE && stack.getMetadata() != slotStack.getMetadata())) {
                    return false;
                }
            }
        }

        return true;
    }
}
