/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.SlotCrafting;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.tileentity.TileEntityUltiTechBench;

/**
 * net.thedragonteam.armorplus.container
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:39 AM.
 * - TheDragonTeam
 */
public class ContainerUltiTechBench extends Container {

    private static final int ITEM_BOX = 18;
    private static final int RECIPE_SLOTS = 26;
    private static final int RECIPE_SIZE = 5;
    private static final int RECIPE_SIZE_TOTAL = 25;
    private static final int ROW_SLOTS = 9;
    private static final int FULL_INVENTORY_SLOTS = RECIPE_SLOTS + 36;
    private static final int MAIN_INVENTORY_SLOTS = RECIPE_SLOTS + 27;
    private final World world;
    /**
     * The crafting matrix inventory (5x5).
     */
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 5, 5);
    public IInventory craftResult = new InventoryCraftResult();

    public ContainerUltiTechBench(InventoryPlayer playerInventory, TileEntityUltiTechBench tile) {
        this.world = tile.getWorld();
        this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 150, 53));

        for (int i = 0; i < RECIPE_SIZE; ++i)
            for (int j = 0; j < RECIPE_SIZE; ++j)
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * RECIPE_SIZE, 12 + j * ITEM_BOX, 17 + i * ITEM_BOX));

        for (int k = 0; k < 3; ++k)//k = y
            for (int i1 = 0; i1 < ROW_SLOTS; ++i1)
                this.addSlotToContainer(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * ITEM_BOX, 118 + k * ITEM_BOX));

        for (int l = 0; l < ROW_SLOTS; ++l)
            this.addSlotToContainer(new Slot(playerInventory, l, 8 + l * ITEM_BOX, 176));

        this.onCraftMatrixChanged(this.craftMatrix);
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        this.craftResult.setInventorySlotContents(0, UltiTechBenchCraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.world));
    }

    /**
     * Called when the container is closed.
     */
    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);

        if (!this.world.isRemote) {
            for (int i = 0; i < RECIPE_SIZE_TOTAL; ++i) {
                ItemStack itemstack = this.craftMatrix.removeStackFromSlot(i);

                if (!itemstack.isEmpty()) {
                    playerIn.dropItem(itemstack, false);
                }
            }
        }
    }

    /**
     * Determines whether supplied player can use this container
     */
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 0) {
                itemstack1.getItem().onCreated(itemstack1, this.world, playerIn);

                if (!this.mergeItemStack(itemstack1, RECIPE_SLOTS, FULL_INVENTORY_SLOTS, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= RECIPE_SLOTS && index < MAIN_INVENTORY_SLOTS) {
                if (!this.mergeItemStack(itemstack1, MAIN_INVENTORY_SLOTS, FULL_INVENTORY_SLOTS, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= MAIN_INVENTORY_SLOTS && index < FULL_INVENTORY_SLOTS) {
                if (!this.mergeItemStack(itemstack1, RECIPE_SLOTS, MAIN_INVENTORY_SLOTS, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, RECIPE_SLOTS, FULL_INVENTORY_SLOTS, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);

            if (index == 0) {
                playerIn.dropItem(itemstack2, false);
            }
        }

        return itemstack;
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
    @Override
    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
    }
}