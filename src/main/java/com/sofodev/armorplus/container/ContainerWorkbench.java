/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.container;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseSlotCrafting;
import com.sofodev.armorplus.container.base.ContainerBenchBase;
import com.sofodev.armorplus.container.base.InventoryCraftingImproved;
import com.sofodev.armorplus.tileentity.TileWB;
import com.sofodev.armorplus.util.EnumHelperUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import static com.sofodev.thedragonlib.util.ItemStackUtils.getItem;

/**
 * @author Sokratis Fotkatzikis
 */
public class ContainerWorkbench extends ContainerBenchBase {

    private static final int RECIPE_SIZE = 3;
    private static final int RECIPE_SIZE_TOTAL = RECIPE_SIZE * RECIPE_SIZE;
    private static final int RECIPE_SLOTS = RECIPE_SIZE_TOTAL + 1;
    public InventoryCraftingImproved craftMatrix = new InventoryCraftingImproved(this, RECIPE_SIZE, RECIPE_SIZE);
    public IInventory craftResult = new InventoryCraftResult();

    public ContainerWorkbench(InventoryPlayer playerInventory, TileWB tile) {
        super(tile, RECIPE_SLOTS);
        this.world = tile.getWorld();
        this.addSlot(new BaseSlotCrafting(BaseCraftingManager.getWBInstance(), playerInventory.player, this.craftMatrix, this.craftResult, 0, 102, 35) {
            @Override
            protected void onCrafting(ItemStack stack) {
                super.onCrafting(stack);
                if (stack.getItem() == getItem("lapis_sword") || stack.getItem() == getItem("lapis_battle_axe") || stack.getItem() == getItem("lapis_bow")) {
                    stack.addEnchantment(EnumHelperUtil.getEnchantment("minecraft:looting"), 3);
                }
            }
        });

        for (int yIndex = 0; yIndex < RECIPE_SIZE; ++yIndex)
            for (int xIndex = 0; xIndex < RECIPE_SIZE; ++xIndex)
                this.addSlot(new Slot(this.craftMatrix, xIndex + yIndex * RECIPE_SIZE, 8 + xIndex * ITEM_BOX, 17 + yIndex * ITEM_BOX));

        this.addPlayerInventory(playerInventory, 8, 142, 84);
        this.addPlayerArmorInventory(playerInventory, 134, 26);
        this.onCraftMatrixChanged(this.craftMatrix);
        this.onContainerClosed(playerInventory.player);
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        this.craftResult.setInventorySlotContents(0, BaseCraftingManager.getWBInstance().findMatchingRecipe(this.craftMatrix, this.world));
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