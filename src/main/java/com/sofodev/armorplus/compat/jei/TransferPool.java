package com.sofodev.armorplus.compat.jei;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * A copy of what a Rune Altar screen and the player's inventory hold, to work out on the client whether a recipe can be filled.
 * Whatever is taken is gone, so one item can't count for two slots.
 */
final class TransferPool {

    private final List<ItemStack> stacks = new ArrayList<>();

    /** @param resultSlot the menu's result slot, every other slot counts */
    TransferPool(AbstractContainerMenu menu, int resultSlot) {
        for (Slot slot : menu.slots) {
            if (slot.index != resultSlot && slot.hasItem()) this.stacks.add(slot.getItem().copy());
        }
    }

    /** @return the first kind of matching item with enough of it, EMPTY if there's none */
    ItemStack take(Predicate<ItemStack> matches, int count) {
        for (ItemStack stack : this.stacks) {
            if (stack.isEmpty() || !matches.test(stack) || amountOf(stack) < count) continue;
            ItemStack taken = stack.copyWithCount(count);
            int left = count;
            for (ItemStack other : this.stacks) {
                if (ItemStack.isSameItemSameComponents(other, taken)) left -= other.split(left).getCount();
            }
            return taken;
        }
        return ItemStack.EMPTY;
    }

    private int amountOf(ItemStack kind) {
        int amount = 0;
        for (ItemStack stack : this.stacks) {
            if (ItemStack.isSameItemSameComponents(stack, kind)) amount += stack.getCount();
        }
        return amount;
    }
}
