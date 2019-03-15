/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.utils;

import com.google.common.collect.Maps;
import com.sofodev.armorplus.api.crafting.IRecipe;
import com.sofodev.armorplus.container.base.InventoryCraftingImproved;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CraftingUtils {

    /**
     * Adds a shapeless crafting recipe to the the game.
     */
    public static void addShapelessRecipe(NonNullList<ItemStack> list, Object... recipeComponents) {
        Arrays.stream(recipeComponents).forEachOrdered(object -> {
            if (object instanceof ItemStack) {
                list.add(((ItemStack) object).copy());
            } else if (object instanceof IItemProvider) {
                list.add(new ItemStack((IItemProvider) object));
            } else {
                throw new AssertionError("Invalid shapeless recipe: unknown type " + object.getClass().getName() + "!");
            }
        });
    }

    public static Map<Character, ItemStack> getCharacterItemStackMap(int index, Object[] recipeComponents) {
        Map<Character, ItemStack> map;
        for (map = Maps.newHashMap(); index < recipeComponents.length; index += 2) {
            int secondary = index + 1;
            Character character = (Character) recipeComponents[index];
            ItemStack itemstack = ItemStack.EMPTY;

            if (recipeComponents[secondary] instanceof IItemProvider) {
                itemstack = new ItemStack((IItemProvider) (recipeComponents[secondary]));
            } else if (recipeComponents[secondary] instanceof ItemStack) {
                itemstack = (ItemStack) recipeComponents[secondary];
            }

            map.put(character, itemstack);
        }
        return map;
    }

    /**
     * Retrieves an ItemStack that has multiple recipes for it.
     */
    public static ItemStack findMatchingRecipe(List<IRecipe> recipes, InventoryCraftingImproved craftMatrix, World worldIn) {
        return recipes.stream().filter(irecipe -> irecipe.matches(craftMatrix, worldIn)).findFirst().map(irecipe -> irecipe.getCraftingResult(craftMatrix)).orElse(ItemStack.EMPTY);
    }

    public static NonNullList<ItemStack> getRemainingItems(List<IRecipe> recipes, InventoryCraftingImproved craftMatrix, World worldIn) {
        for (IRecipe recipe : recipes) {
            if (recipe.matches(craftMatrix, worldIn)) {
                return recipe.getRemainingItems(craftMatrix);
            }
        }

        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(craftMatrix.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            nonnulllist.set(i, craftMatrix.getStackInSlot(i));
        }
        return nonnulllist;
    }

    public static void onTake(EntityPlayer player, InventoryCraftingImproved craftMatrix, NonNullList<ItemStack> input) {
        for (int i = 0; i < input.size(); ++i) {
            ItemStack slotStack = craftMatrix.getStackInSlot(i);
            ItemStack inputStack = input.get(i);

            if (!slotStack.isEmpty()) {
                craftMatrix.decrStackSize(i, 1);
                slotStack = craftMatrix.getStackInSlot(i);
            }

            if (!inputStack.isEmpty()) {
                if (slotStack.isEmpty()) {
                    craftMatrix.setInventorySlotContents(i, inputStack);
                } else if (ItemStack.areItemsEqual(slotStack, inputStack) && ItemStack.areItemStackTagsEqual(slotStack, inputStack)) {
                    inputStack.grow(slotStack.getCount());
                    craftMatrix.setInventorySlotContents(i, inputStack);
                } else if (!player.inventory.addItemStackToInventory(inputStack)) {
                    player.dropItem(inputStack, false);
                }
            }
        }
    }

    public static boolean itemMatches(@Nonnull ItemStack target, @Nonnull ItemStack input, boolean strict) {
        if (input.isEmpty() && !target.isEmpty() || !input.isEmpty() && target.isEmpty()) {
            return false;
        }
        return (target.getItem() == input.getItem());
    }
}
