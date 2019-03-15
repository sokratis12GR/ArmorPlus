/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.container;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseSlotCrafting;
import com.sofodev.armorplus.container.base.ContainerBenchBase;
import com.sofodev.armorplus.container.base.InventoryCraftingImproved;
import com.sofodev.armorplus.tileentity.TileCB;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class ContainerChampionBench extends ContainerBenchBase {

    private static final int RECIPE_SIZE = 9;

    private static final int RECIPE_SIZE_TOTAL = RECIPE_SIZE * RECIPE_SIZE;
    private static final int RECIPE_SLOTS = RECIPE_SIZE_TOTAL + 1;

    public InventoryCraftingImproved craftMatrix = new InventoryCraftingImproved(this, RECIPE_SIZE, RECIPE_SIZE);
    public IInventory craftResult = new InventoryCraftResult();

    public ContainerChampionBench(InventoryPlayer playerInventory, TileCB tile) {
        super(tile, RECIPE_SLOTS);
        this.world = tile.getWorld();
        this.addSlot(new BaseSlotCrafting(BaseCraftingManager.getCBInstance(), playerInventory.player, this.craftMatrix, this.craftResult, 0, 190, 52));

        for (int yIndex = 0; yIndex < RECIPE_SIZE; ++yIndex) {
            for (int xIndex = 0; xIndex < RECIPE_SIZE; ++xIndex) {
                Slot craftingSlot = new Slot(this.craftMatrix, xIndex + yIndex * RECIPE_SIZE, 12 + xIndex * ITEM_BOX, 17 + yIndex * ITEM_BOX);
                this.addSlot(craftingSlot);
            }
        }

        for (int row = 0; row < PLAYER_COL_SLOTS; ++row) {
            for (int col = 0; col < PLAYER_ROW_SLOTS; ++col) {
                Slot slot = new Slot(playerInventory, col + row * 9 + 9, 12 + col * ITEM_BOX, 196 + row * ITEM_BOX);
                this.addSlot(slot);
            }
        }

        for (int col = 0; col < PLAYER_ROW_SLOTS; col++) {
            Slot slot = new Slot(playerInventory, col, 228, 88 + col * ITEM_BOX);
            addSlot(slot);
        }

        this.addPlayerArmorInventory(playerInventory, 183, 214);
        this.onCraftMatrixChanged(this.craftMatrix);
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        this.craftResult.setInventorySlotContents(0, BaseCraftingManager.getCBInstance().findMatchingRecipe(this.craftMatrix, this.world));
    }

    /**
     * Called when the container is closed.
     */
    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        onContainerClosed(playerIn, this.craftMatrix);
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
