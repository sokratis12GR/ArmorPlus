/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.utils;

import com.sofodev.armorplus.container.base.InventoryCraftingImproved;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.sofodev.armorplus.api.crafting.utils.CraftingUtils.itemMatches;

public class ShapelessOreRecipeUtils {

    public static void createRecipe(ItemStack output, NonNullList<Object> input, Object... recipe) {
        Arrays.stream(recipe).forEachOrdered(in -> {
            if (in instanceof ItemStack) {
                input.add(((ItemStack) in).copy());
            } else if (in instanceof IItemProvider) {
                input.add(new ItemStack((IItemProvider) in));
            } else {
                StringBuilder ret = new StringBuilder("Invalid shapeless ore recipe: ");
                Arrays.stream(recipe).forEachOrdered(tmp -> ret.append(tmp).append(", "));
                ret.append(output);
                throw new RuntimeException(ret.toString());
            }
        });
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @SuppressWarnings("unchecked")
    public static boolean matches(NonNullList<Object> input, InventoryCraftingImproved inv) {
        NonNullList<Object> required = NonNullList.create();
        required.addAll(input);

        for (int x = 0; x < inv.getSizeInventory(); x++) {
            ItemStack slot = inv.getStackInSlot(x);

            if (!slot.isEmpty()) {
                boolean inRecipe = false;

                for (Object aRequired : required) {
                    boolean match = false;

                    if (aRequired instanceof ItemStack) {
                        match = itemMatches((ItemStack) aRequired, slot, false);
                    } else if (aRequired instanceof List) {
                        Iterator<ItemStack> itr = ((List<ItemStack>) aRequired).iterator();
                        while (itr.hasNext() && !match) {
                            match = itemMatches(itr.next(), slot, false);
                        }
                    }

                    if (match) {
                        inRecipe = true;
                        required.remove(aRequired);
                        break;
                    }
                }

                if (!inRecipe) {
                    return false;
                }
            }
        }

        return required.isEmpty();
    }
}
