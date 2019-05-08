/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.container.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;

import static net.minecraft.inventory.EntityEquipmentSlot.*;

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
        for (int yIndex = 0; yIndex < PLAYER_COL_SLOTS; yIndex++) {
            for (int xIndex = 0; xIndex < PLAYER_ROW_SLOTS; xIndex++) {
                this.addSlot(new Slot(playerInventory, xIndex + yIndex * 9 + 9, xPos + xIndex * ITEM_BOX, yInventoryRow + yIndex * ITEM_BOX));
            }
        }

        for (int index = 0; index < PLAYER_ROW_SLOTS; index++) {
            this.addSlot(new Slot(playerInventory, index, xPos + index * ITEM_BOX, yFirstRow));
        }
    }
}