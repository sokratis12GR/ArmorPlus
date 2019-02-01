/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.container.base;

import com.sofodev.armorplus.container.SlotArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

/**
 * @author Sokratis Fotkatzikis
 */
public class ContainerBenchBase extends ContainerBase {

    public static int recipeSlots;
    public static int mainInventorySlots;
    public static int fullInventorySlots;
    public World world;

    public ContainerBenchBase(TileEntity tile, int recipeSlotsIn, int mainInventorySlotsIn, int fullInventorySlotsIn) {
        world = tile.getWorld();
        recipeSlots = recipeSlotsIn;
        mainInventorySlots = mainInventorySlotsIn;
        fullInventorySlots = fullInventorySlotsIn;
    }

    protected static void onContainerClosed(EntityPlayer playerIn, boolean isRemote, int recipeSizeTotal, InventoryCraftingImproved removeItemStack) {
        if (!isRemote) {
            IntStream.range(0, recipeSizeTotal).mapToObj(removeItemStack::removeStackFromSlot).filter(itemstack ->
                !itemstack.isEmpty()
            ).forEachOrdered(itemstack -> playerIn.dropItem(itemstack, false));
        }
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    @Override
    @Nonnull
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 0) {
                itemstack1.getItem().onCreated(itemstack1, world, playerIn);

                if (!this.mergeItemStack(itemstack1, recipeSlots, fullInventorySlots, true)) return ItemStack.EMPTY;

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= recipeSlots && index < mainInventorySlots) {
                if (!this.mergeItemStack(itemstack1, mainInventorySlots, fullInventorySlots, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= mainInventorySlots && index < fullInventorySlots) {
                if (!this.mergeItemStack(itemstack1, recipeSlots, mainInventorySlots, false)) return ItemStack.EMPTY;
            } else if (!this.mergeItemStack(itemstack1, recipeSlots, fullInventorySlots, false)) return ItemStack.EMPTY;

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

    protected void addPlayerArmorInventory(InventoryPlayer inventory, int xPos, int yPos) {
        this.addPlayerArmorInventoryTop(inventory, xPos, yPos);
        this.addPlayerArmorInventoryBot(inventory, xPos, yPos + ITEM_BOX);
    }

    protected void addPlayerArmorInventoryTop(InventoryPlayer inventory, int xPos, int yPos) {
        for (int k = 0; k < 2; k++) {
            EntityEquipmentSlot equipmentSlot = EQUIPMENT_SLOTS[k];
            addSlotToContainer(new SlotArmor(inventory, 4 * 9 + (3 - k), xPos + k * ITEM_BOX, yPos, inventory.player, equipmentSlot));
        }
    }

    protected void addPlayerArmorInventoryBot(InventoryPlayer inventory, int xPos, int yPos) {
        for (int k = 0; k < 2; k++) {
            EntityEquipmentSlot equipmentSlot = EQUIPMENT_SLOTS[k + 2];
            addSlotToContainer(new SlotArmor(inventory, 4 * 9 + (3 - (k + 2)), xPos + k * ITEM_BOX, yPos, inventory.player, equipmentSlot));
        }
    }
}
