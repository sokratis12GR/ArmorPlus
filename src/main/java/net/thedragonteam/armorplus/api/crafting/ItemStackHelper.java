package net.thedragonteam.armorplus.api.crafting;

import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * sokratis12GR.ArmorPlus.api
 * ArmorPlus created by sokratis12GR on 6/21/2016 4:04 PM.
 */
public class ItemStackHelper {
    @Nullable
    public static ItemStack getAndSplit(ItemStack[] stacks, int index, int amount) {
        if (index >= 0 && index < stacks.length && stacks[index] != null && amount > 0) {
            ItemStack itemstack = stacks[index].splitStack(amount);

            if (stacks[index].stackSize == 0) {
                stacks[index] = null;
            }

            return itemstack;
        } else {
            return null;
        }
    }

    @Nullable
    public static ItemStack getAndRemove(ItemStack[] stacks, int index) {
        if (index >= 0 && index < stacks.length) {
            ItemStack itemstack = stacks[index];
            stacks[index] = null;
            return itemstack;
        } else {
            return null;
        }
    }
}