package net.thedragonteam.armorplus.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.thedragonteam.armorplus.api.lavainfuser.SlotLavaInfuserFuel;
import net.thedragonteam.armorplus.api.lavainfuser.SlotLavaInfuserOutput;
import net.thedragonteam.armorplus.container.base.ContainerBase;
import net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ContainerLavaInfuser extends ContainerBase {
    private static final int ITEM_BOX = 18;
    private int cookTime = 0;
    private int totalCookTime = 0;
    private int furnaceBurnTime = 0;
    private int currentItemBurnTime = 0;
    private TileEntityLavaInfuser tile;

    public ContainerLavaInfuser(InventoryPlayer playerInventory, TileEntityLavaInfuser tile) {
        this.tile = tile;
        this.addSlotToContainer(new Slot(tile, 0, 69, 35));
        this.addSlotToContainer(new SlotLavaInfuserFuel(tile, 1, 34, 35));
        this.addSlotToContainer(new SlotLavaInfuserOutput(playerInventory.player, tile, 2, 124, 35));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * ITEM_BOX, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * ITEM_BOX, 142));
        }
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tile);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        this.listeners.forEach(listener -> {
            if (this.cookTime != this.tile.getField(2))
                listener.sendWindowProperty(this, 2, this.tile.getField(2));
            if (this.furnaceBurnTime != this.tile.getField(0))
                listener.sendWindowProperty(this, 0, this.tile.getField(0));
            if (this.currentItemBurnTime != this.tile.getField(1))
                listener.sendWindowProperty(this, 1, this.tile.getField(1));
            if (this.totalCookTime != this.tile.getField(3))
                listener.sendWindowProperty(this, 3, this.tile.getField(3));
        });

        this.cookTime = this.tile.getField(2);
        this.furnaceBurnTime = this.tile.getField(0);
        this.currentItemBurnTime = this.tile.getField(1);
        this.totalCookTime = this.tile.getField(3);
    }

    @Override
    public void updateProgressBar(int id, int data) {
        this.tile.setField(id, data);
    }


    @Override
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
                if (!FurnaceRecipes.instance().getSmeltingResult(itemstack1).isEmpty()) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (TileEntityFurnace.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
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