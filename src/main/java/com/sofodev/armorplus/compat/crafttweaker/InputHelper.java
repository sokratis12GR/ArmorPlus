/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.crafttweaker;

import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.oredict.IOreDictEntry;
import crafttweaker.mc1120.item.MCItemStack;
import crafttweaker.mc1120.liquid.MCLiquidStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class InputHelper {
    public static boolean isABlock(IItemStack block) {
        return isABlock(toStack(block));
    }

    public static IItemStack[] toStacks(IIngredient[] iIngredient) {
        ArrayList<IItemStack> stacks = new ArrayList<>();

        Arrays.stream(iIngredient).map(IIngredient::getItems).forEach(stacks::addAll);

        return stacks.toArray(new IItemStack[0]);
    }

    public static boolean isABlock(ItemStack block) {
        return block.getItem() instanceof ItemBlock;
    }

    public static ItemStack toStack(IItemStack iStack) {
        if (iStack == null) {
            return null;
        }
        Object internal = iStack.getInternal();
        if (!(internal instanceof ItemStack)) {
            LogHelper.error("Not a valid item stack: " + iStack);
        }

        return (ItemStack) internal;
    }

    public static IIngredient toIngredient(ItemStack stack) {
        return toIItemStack(stack);
    }

    public static IIngredient toIngredient(FluidStack stack) {
        return stack == null ? null : new MCLiquidStack(stack);
    }

    public static IItemStack toIItemStack(ItemStack stack) {
        return stack == null ? null : new MCItemStack(stack);
    }

    public static ItemStack[] toStacks(IItemStack[] iStack) {
        return iStack == null ? null : Arrays.stream(iStack).map(InputHelper::toStack).toArray(ItemStack[]::new);
    }

    public static Object toObject(IIngredient iStack) {
        return iStack == null ? null : iStack instanceof IOreDictEntry ? toString((IOreDictEntry) iStack) : iStack instanceof IItemStack ? toStack((IItemStack) iStack) : null;
    }

    public static Object[] toObjects(IIngredient[] ingredient) {
        return ingredient == null ? null : Arrays.stream(ingredient).map(iIngredient -> iIngredient != null ? toObject(iIngredient) : "").toArray();
    }

    public static String toString(IOreDictEntry entry) {
        return entry.getName();
    }

}