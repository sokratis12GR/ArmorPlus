/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.api.crafting.lavainfuser.LavaInfuserManager;
import net.thedragonteam.armorplus.api.crafting.lavainfuser.SlotLavaInfuserFuel;
import net.thedragonteam.armorplus.api.crafting.lavainfuser.SlotLavaInfuserOutput;
import net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser;

import javax.annotation.Nonnull;

public class ContainerLavaInfuser extends Container {

    private static final int ITEM_BOX = 18;
    private final TileEntityLavaInfuser tile;
    private int cookTime;
    private int totalCookTime;
    private int furnaceBurnTime;
    private int currentItemBurnTime;

    public ContainerLavaInfuser(InventoryPlayer playerInventory, TileEntityLavaInfuser tile) {
        this.tile = tile;
        this.addSlotToContainer(new Slot(tile, 0, 69, 35));
        this.addSlotToContainer(new SlotLavaInfuserFuel(tile, 1, 34, 35));
        this.addSlotToContainer(new SlotLavaInfuserOutput(playerInventory.player, tile, 2, 124, 35));

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * ITEM_BOX, 84 + i * 18));

        for (int k = 0; k < 9; ++k) this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * ITEM_BOX, 142));
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tile);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener listener : this.listeners) {

            if (this.cookTime != this.tile.getField(2)) listener.sendProgressBarUpdate(this, 2, this.tile.getField(2));

            if (this.furnaceBurnTime != this.tile.getField(0))
                listener.sendProgressBarUpdate(this, 0, this.tile.getField(0));

            if (this.currentItemBurnTime != this.tile.getField(1))
                listener.sendProgressBarUpdate(this, 1, this.tile.getField(1));

            if (this.totalCookTime != this.tile.getField(3))
                listener.sendProgressBarUpdate(this, 3, this.tile.getField(3));
        }

        this.cookTime = this.tile.getField(2);
        this.furnaceBurnTime = this.tile.getField(0);
        this.currentItemBurnTime = this.tile.getField(1);
        this.totalCookTime = this.tile.getField(3);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data) {
        this.tile.setField(id, data);
    }

    /**
     * Determines whether supplied player can use this container
     */
    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return true;
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    @Override
    @Nonnull
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (!LavaInfuserManager.getInstance().getSmeltingResult(itemstack1).isEmpty()) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (TileEntityLavaInfuser.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
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

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}