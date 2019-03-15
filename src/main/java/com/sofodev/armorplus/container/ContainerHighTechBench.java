/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.container;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseSlotCrafting;
import com.sofodev.armorplus.container.base.ContainerBenchBase;
import com.sofodev.armorplus.container.base.InventoryCraftingImproved;
import com.sofodev.armorplus.tileentity.TileHTB;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class ContainerHighTechBench extends ContainerBenchBase {

    private static final int RECIPE_SIZE = 5;
    private static final int RECIPE_SIZE_TOTAL = RECIPE_SIZE * RECIPE_SIZE;
    private static final int RECIPE_SLOTS = RECIPE_SIZE_TOTAL + 1;

    public InventoryCraftingImproved craftMatrix = new InventoryCraftingImproved(this, RECIPE_SIZE, RECIPE_SIZE);
    public IInventory craftResult = new InventoryCraftResult();

    public ContainerHighTechBench(InventoryPlayer playerInventory, TileHTB tile) {
        super(tile, RECIPE_SLOTS);
        this.world = tile.getWorld();
        this.addSlot(new BaseSlotCrafting(BaseCraftingManager.getHTBInstance(), playerInventory.player, this.craftMatrix, this.craftResult, 0, 130, 34));

        for (int yIndex = 0; yIndex < RECIPE_SIZE; ++yIndex) {
            for (int xIndex = 0; xIndex < RECIPE_SIZE; ++xIndex) {
                this.addSlot(new Slot(this.craftMatrix, xIndex + yIndex * RECIPE_SIZE, 8 + xIndex * ITEM_BOX, 16 + yIndex * ITEM_BOX));
            }
        }

        this.addPlayerInventory(playerInventory, 8, 176, 118);
        this.addPlayerArmorInventory(playerInventory, 116, 70);

        this.onCraftMatrixChanged(this.craftMatrix);
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        this.craftResult.setInventorySlotContents(0, BaseCraftingManager.getHTBInstance().findMatchingRecipe(this.craftMatrix, this.world));
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
