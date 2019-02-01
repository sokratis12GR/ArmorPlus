/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.container.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;

import javax.annotation.Nonnull;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.inventory.EntityEquipmentSlot.FEET;

/**
 * @author Sokratis Fotkatzikis
 */
public abstract class ContainerBase extends Container {

    protected static final EntityEquipmentSlot[] EQUIPMENT_SLOTS = new EntityEquipmentSlot[]{HEAD, CHEST, LEGS, FEET};
    protected static final int ITEM_BOX = 18;
    protected static final int PLAYER_ROW_SLOTS = 9;
    protected static final int PLAYER_COL_SLOTS = 3;

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    /**
     * Draws the player inventory starting at the given position
     *
     * @param playerInventory The players inventory
     */
    protected void addPlayerInventory(InventoryPlayer playerInventory, int xPos, int yFirstRow, int yInventoryRow) {
        for (int height = 0; height < PLAYER_COL_SLOTS; height++) {
            for (int width = 0; width < PLAYER_ROW_SLOTS; width++) {
                this.addSlotToContainer(new Slot(playerInventory, width + height * 9 + 9, xPos + width * ITEM_BOX, yInventoryRow + height * ITEM_BOX));
            }
        }

        for (int row = 0; row < PLAYER_ROW_SLOTS; row++) {
            this.addSlotToContainer(new Slot(playerInventory, row, xPos + row * ITEM_BOX, yFirstRow));
        }
    }

    @Nonnull
    @Override
    protected Slot addSlotToContainer(Slot slotIn) {
        return super.addSlotToContainer(slotIn);
    }
}