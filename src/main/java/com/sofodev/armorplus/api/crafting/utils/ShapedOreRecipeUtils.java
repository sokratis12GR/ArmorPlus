/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.utils;

import com.sofodev.armorplus.container.base.InventoryCraftingImproved;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class ShapedOreRecipeUtils {

    public static void checkRecipeShape(int width, int height, StringBuilder shape, ItemStack output, Object... recipe) {
        if (width * height != shape.length()) {
            StringBuilder ret = new StringBuilder("Invalid shaped ore recipe: ");
            Arrays.stream(recipe).forEachOrdered(tmp -> ret.append(tmp).append(", "));
            ret.append(output);
            throw new RuntimeException(ret.toString());
        }
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public static boolean matches(int width, int height, Object[] input, InventoryCraftingImproved inv) {
        return IntStream.rangeClosed(0, inv.getWidth() - width).anyMatch(x -> IntStream.rangeClosed(0, inv.getHeight() - height).anyMatch(y -> checkMatch(width, height, input, inv, x, y, true) || checkMatch(width, height, input, inv, x, y, false)));
    }

    @SuppressWarnings("unchecked")
    private static boolean checkMatch(int width, int height, Object[] input, InventoryCraftingImproved inv, int startX, int startY, boolean mirror) {
        for (int x = 0; x < inv.getWidth(); x++) {
            for (int y = 0; y < inv.getHeight(); y++) {
                int subX = x - startX;
                int subY = y - startY;
                Object target = null;

                if (subX >= 0 && subY >= 0 && subX < width && subY < height) {
                    target = mirror ? input[width - subX - 1 + subY * width] : input[subX + subY * width];
                }

                ItemStack slot = inv.getStackInRowAndColumn(x, y);

                if (target instanceof ItemStack) {
                    if (!OreDictionary.itemMatches((ItemStack) target, slot, false)) return false;
                } else if (target instanceof List) {
                    boolean matched = false;

                    Iterator<ItemStack> itr = ((List<ItemStack>) target).iterator();
                    while (itr.hasNext() && !matched) {
                        matched = OreDictionary.itemMatches(itr.next(), slot, false);
                    }

                    if (!matched) return false;
                } else if (target == null && !slot.isEmpty()) return false;
            }
        }

        return true;
    }
}
