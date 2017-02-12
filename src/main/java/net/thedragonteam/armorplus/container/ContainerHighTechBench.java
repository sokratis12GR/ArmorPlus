/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.SlotCrafting;
import net.thedragonteam.armorplus.container.base.ContainerBenchBase;
import net.thedragonteam.armorplus.container.base.InventoryCraftingImproved;
import net.thedragonteam.armorplus.tileentity.TileEntityHighTechBench;

/**
 * net.thedragonteam.armorplus.container
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:39 AM.
 * - TheDragonTeam
 */
public class ContainerHighTechBench extends ContainerBenchBase {

    private static final int ITEM_BOX = 18;
    private static final int RECIPE_SLOTS = 17;
    private static final int RECIPE_SIZE = 4;
    private static final int RECIPE_SIZE_TOTAL = 16;
    private static final int ROW_SLOTS = 9;
    private static final int FULL_INVENTORY_SLOTS = RECIPE_SLOTS + 36;
    private static final int MAIN_INVENTORY_SLOTS = RECIPE_SLOTS + 27;
    /**
     * The crafting matrix inventory (4x4).
     */
    public InventoryCraftingImproved craftMatrix = new InventoryCraftingImproved(this, 4, 4);
    public IInventory craftResult = new InventoryCraftResult();

    public ContainerHighTechBench(InventoryPlayer playerInventory, TileEntityHighTechBench tile) {
        super(tile, RECIPE_SLOTS, MAIN_INVENTORY_SLOTS, FULL_INVENTORY_SLOTS);
        this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 44));

        for (int i = 0; i < RECIPE_SIZE; ++i)
            for (int j = 0; j < RECIPE_SIZE; ++j)
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * RECIPE_SIZE, 12 + j * ITEM_BOX, 17 + i * ITEM_BOX));

        for (int k = 0; k < 3; ++k)
            for (int i1 = 0; i1 < ROW_SLOTS; ++i1)
                this.addSlotToContainer(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * ITEM_BOX, 102 + k * ITEM_BOX));

        for (int l = 0; l < ROW_SLOTS; ++l)
            this.addSlotToContainer(new Slot(playerInventory, l, 8 + l * ITEM_BOX, 160));

        this.onCraftMatrixChanged(this.craftMatrix);
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        this.craftResult.setInventorySlotContents(0, HighTechBenchCraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.world));
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
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
    @Override
    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
    }
}