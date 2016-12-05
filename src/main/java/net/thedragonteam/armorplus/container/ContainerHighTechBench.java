/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.SlotCrafting;

import javax.annotation.Nullable;

/**
 * net.thedragonteam.armorplus.container
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:39 AM.
 * - TheDragonTeam
 */
public class ContainerHighTechBench extends Container {

    /**
     * The crafting matrix inventory (4x4).
     */
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 4, 4);
    public IInventory craftResult = new InventoryCraftResult();

    private World worldObj;

    public ContainerHighTechBench(InventoryPlayer playerInventory, World worldIn) {
        this.worldObj = worldIn;
        this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 44));

        for (int y = 0; y < 4; ++y)
            for (int x = 0; x < 4; ++x)
                this.addSlotToContainer(new Slot(this.craftMatrix, x + y * 4, 12 + x * 18, 17 + y * 18));

        for (int y = 0; y < 3; ++y)
            for (int x = 0; x < 9; ++x)
                this.addSlotToContainer(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 102 + y * 18));

        for (int x = 0; x < 9; ++x) this.addSlotToContainer(new Slot(playerInventory, x, 8 + x * 18, 160));

        this.onCraftMatrixChanged(this.craftMatrix);
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        this.craftResult.setInventorySlotContents(0, HighTechBenchCraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);

        if (!this.worldObj.isRemote) for (int i = 0; i < 16; ++i) {
            ItemStack itemstack = this.craftMatrix.removeStackFromSlot(i);

            if (itemstack != null) playerIn.dropItem(itemstack, false);
        }
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    @Nullable
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();

            if (index == 0) {
                if (!this.mergeItemStack(slotStack, 10, 53, true)) {
                    return null;
                }

                slot.onSlotChange(slotStack, itemstack);
            } else if (index >= 10 && index < 37) {
                if (!this.mergeItemStack(slotStack, 37, 53, false)) {
                    return null;
                }
            } else if (index >= 37 && index < 53) {
                if (!this.mergeItemStack(slotStack, 10, 37, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(slotStack, 10, 53, false)) {
                return null;
            }

            if (slotStack.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(playerIn, slotStack);
        }

        return itemstack;
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
    }
}