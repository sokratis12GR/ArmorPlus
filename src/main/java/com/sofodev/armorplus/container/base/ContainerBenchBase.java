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

/**
 * @author Sokratis Fotkatzikis
 */
public class ContainerBenchBase extends ContainerBase {

    public static int recipeSlots;
    // 36 - Full
    // 27 - Main
    public static int mainInventorySlots;
    public static int fullInventorySlots;
    public World world;

    public ContainerBenchBase(TileEntity tile, int recipeSlotsIn) {
        world = tile.getWorld();
        recipeSlots = recipeSlotsIn;
        mainInventorySlots = recipeSlotsIn + 27;
        fullInventorySlots = recipeSlotsIn + 36;
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
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();

            if (index == 0) {
                slotStack.getItem().onCreated(slotStack, world, playerIn);

                if (!this.mergeItemStack(slotStack, recipeSlots, fullInventorySlots, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(slotStack, itemstack);
            } else if (index >= recipeSlots && index < mainInventorySlots) {
                if (!this.mergeItemStack(slotStack, mainInventorySlots, fullInventorySlots, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= mainInventorySlots && index < fullInventorySlots) {
                if (!this.mergeItemStack(slotStack, recipeSlots, mainInventorySlots, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(slotStack, recipeSlots, fullInventorySlots, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack takenStack = slot.onTake(playerIn, slotStack);

            if (index == 0) {
                playerIn.dropItem(takenStack, false);
            }
        }

        return itemstack;
    }

    public static void onContainerClosed(EntityPlayer player, InventoryCraftingImproved craftingMatrix) {
        if (!player.world.isRemote) {
            for (int i = 0; i < craftingMatrix.getSizeInventory(); i++) {
                ItemStack itemstack = craftingMatrix.removeStackFromSlot(i);
                if (!itemstack.isEmpty()) {
                    player.dropItem(itemstack, false);
                }
            }
        }
    }

    protected void addPlayerArmorInventory(InventoryPlayer inventory, int xPos, int yPos) {
        this.addPlayerArmorInventoryTop(inventory, xPos, yPos);
        this.addPlayerArmorInventoryBot(inventory, xPos, yPos + ITEM_BOX);
    }

    protected void addPlayerArmorInventoryTop(InventoryPlayer inventory, int xPos, int yPos) {
        for (int index = 0; index < 2; index++) {
            EntityEquipmentSlot equipmentSlot = EQUIPMENT_SLOTS[index];
            addSlotToContainer(new SlotArmor(inventory, 4 * 9 + (3 - index), xPos + index * ITEM_BOX, yPos, inventory.player, equipmentSlot));
        }
    }

    protected void addPlayerArmorInventoryBot(InventoryPlayer inventory, int xPos, int yPos) {
        for (int index = 0; index < 2; index++) {
            EntityEquipmentSlot equipmentSlot = EQUIPMENT_SLOTS[index + 2];
            addSlotToContainer(new SlotArmor(inventory, 4 * 9 + (3 - (index + 2)), xPos + index * ITEM_BOX, yPos, inventory.player, equipmentSlot));
        }
    }
}
