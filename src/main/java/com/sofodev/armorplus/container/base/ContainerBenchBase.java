/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.container.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

/**
 * @author Sokratis Fotkatzikis
 */
public class ContainerBenchBase extends ContainerBase {

    public static int recipeSlots;
    public static int mainInventorySlots;
    public static int fullInventorySlots;
    public World world;

    public ContainerBenchBase(TileEntity tile, int recipeSlotsIn, int mainInventorySlotsIn, int fullInventorySlotsIn) {
        world = tile.getWorld();
        recipeSlots = recipeSlotsIn;
        mainInventorySlots = mainInventorySlotsIn;
        fullInventorySlots = fullInventorySlotsIn;
    }

    protected static void onContainerClosed(EntityPlayer playerIn, boolean isRemote, int recipeSizeTotal, InventoryCraftingImproved removeItemStack) {
        if (!isRemote) {
            IntStream.range(0, recipeSizeTotal).mapToObj(removeItemStack::removeStackFromSlot).filter(itemstack ->
                !itemstack.isEmpty()
            ).forEachOrdered(itemstack -> playerIn.dropItem(itemstack, false));
        }
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    @Override
    @Nonnull
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();
            boolean cannotMergeReverse = this.cannotMergeReverse(slotStack, recipeSlots, fullInventorySlots);
            boolean cannotMergeMainFull = this.cannotMerge(slotStack, mainInventorySlots, fullInventorySlots, false);
            boolean cannotMergeRecipeMain = this.cannotMergeFromRecipeSlots(slotStack, mainInventorySlots);
            boolean cannotMergeRecipeFull = this.cannotMergeFromRecipeSlots(slotStack, fullInventorySlots);

            if (index == 0) {
                slotStack.getItem().onCreated(slotStack, world, playerIn);

                if (cannotMergeReverse) return ItemStack.EMPTY;

                slot.onSlotChange(slotStack, itemstack);
            } else if (this.withinIndex(index, recipeSlots, mainInventorySlots)) {
                if (cannotMergeMainFull) {
                    return ItemStack.EMPTY;
                }
            } else if (this.withinIndex(index, mainInventorySlots, fullInventorySlots)) {
                if (cannotMergeRecipeMain) {
                    return ItemStack.EMPTY;
                }
            } else if (cannotMergeRecipeFull) return ItemStack.EMPTY;

            if (slotStack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack takenStack = slot.onTake(playerIn, slotStack);

            if (index == 0) {
                playerIn.dropItem(takenStack, false);
            }
        }

        return itemstack;
    }

    private boolean withinIndex(int index, int minSlotIndex, int maxSlotIndex) {
        return (index >= minSlotIndex && index < maxSlotIndex);
    }

    private boolean cannotMerge(ItemStack slotStack, int slots, int inventorySlots, boolean reverseDirection) {
        return !(this.mergeItemStack(slotStack, slots, inventorySlots, reverseDirection));
    }

    private boolean cannotMergeReverse(ItemStack slotStack, int slots, int inventorySlots) {
        return cannotMerge(slotStack, slots, inventorySlots, true);
    }

    private boolean cannotMergeFromRecipeSlots(ItemStack slotStack, int inventorySlots) {
        return cannotMerge(slotStack, recipeSlots, inventorySlots, false);
    }
}
