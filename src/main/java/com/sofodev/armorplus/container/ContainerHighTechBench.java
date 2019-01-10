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

import java.util.stream.IntStream;

/**
 * @author Sokratis Fotkatzikis
 */
public class ContainerHighTechBench extends ContainerBenchBase {

    private static final int ITEM_BOX = 18;
    private static final int RECIPE_SLOTS = 26;
    private static final int RECIPE_SIZE = 5;
    private static final int RECIPE_SIZE_TOTAL = 25;
    private static final int ROW_SLOTS = 9;
    private static final int FULL_INVENTORY_SLOTS = RECIPE_SLOTS + 36;
    private static final int MAIN_INVENTORY_SLOTS = RECIPE_SLOTS + 27;
    public InventoryCraftingImproved craftMatrix = new InventoryCraftingImproved(this, 5, 5);
    public IInventory craftResult = new InventoryCraftResult();

    public ContainerHighTechBench(InventoryPlayer playerInventory, TileHTB tile) {
        super(tile, RECIPE_SLOTS, MAIN_INVENTORY_SLOTS, FULL_INVENTORY_SLOTS);
        this.world = tile.getWorld();
        this.addSlotToContainer(new BaseSlotCrafting(BaseCraftingManager.getHTBInstance(), playerInventory.player, this.craftMatrix, this.craftResult, 0, 150, 53));

        for (int yIndex = 0; yIndex < RECIPE_SIZE; ++yIndex)
            for (int xIndex = 0; xIndex < RECIPE_SIZE; ++xIndex)
                this.addSlotToContainer(new Slot(this.craftMatrix, xIndex + yIndex * RECIPE_SIZE, 12 + xIndex * ITEM_BOX, 17 + yIndex * ITEM_BOX));

        for (int height = 0; height < 3; ++height)
            for (int width = 0; width < ROW_SLOTS; ++width)
                this.addSlotToContainer(new Slot(playerInventory, width + height * 9 + 9, 8 + width * ITEM_BOX, 118 + height * ITEM_BOX));

        IntStream.range(0, ROW_SLOTS).mapToObj(index -> new Slot(playerInventory, index, 8 + index * ITEM_BOX, 176)).forEachOrdered(this::addSlotToContainer);

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
        onContainerClosed(playerIn, this.world.isRemote, RECIPE_SIZE_TOTAL, this.craftMatrix);
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
