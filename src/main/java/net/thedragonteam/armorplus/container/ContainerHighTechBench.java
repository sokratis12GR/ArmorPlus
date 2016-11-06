/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.crafting.base.InventoryCrafting;
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

        for (int i = 0; i < 4; ++i)//i = y
            for (int j = 0; j < 4; ++j)//j = x
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 4, 12 + j * 18, 17 + i * 18));

        for (int k = 0; k < 3; ++k)//k = y
            for (int i1 = 0; i1 < 9; ++i1)//i1 = x
                this.addSlotToContainer(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 102 + k * 18));

        //l = x
        for (int l = 0; l < 9; ++l) this.addSlotToContainer(new Slot(playerInventory, l, 8 + l * 18, 160));

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

    @Nullable
    @Override
    public Slot getSlotFromInventory(IInventory inv, int slotIn) {
        if (this.inventoryItemStacks == null) {
            getSlotFromInventory(inv, slotIn).inventory.clear();
        }
        return super.getSlotFromInventory(inv, slotIn);
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
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 0) {
                if (!this.mergeItemStack(itemstack1, 10, 46, true)) return null;

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= 10 && index < 37) if (!this.mergeItemStack(itemstack1, 37, 46, false)) return null;
            else if (index >= 37 && index < 46) if (!this.mergeItemStack(itemstack1, 10, 37, false)) return null;
            else if (!this.mergeItemStack(itemstack1, 10, 46, false)) return null;

            switch (itemstack1.stackSize) {
                case 0:
                    slot.putStack((ItemStack) null);
                    break;
                default:
                    slot.onSlotChanged();
                    break;
            }

            if (itemstack1.stackSize == itemstack.stackSize) return null;

            slot.onPickupFromSlot(playerIn, itemstack1);
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