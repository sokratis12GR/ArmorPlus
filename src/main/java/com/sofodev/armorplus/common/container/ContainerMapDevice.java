package com.sofodev.armorplus.common.container;

import com.sofodev.armorplus.common.container.base.ContainerBase;
import com.sofodev.armorplus.common.registry.items.ItemCombinedMap;
import com.sofodev.armorplus.common.tileentity.TileEntityMapDevice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerMapDevice extends ContainerBase {

    private final TileEntityMapDevice tileEntity;
    /**
     * This beacon's slot where you put in Emerald, Diamond, Gold or Iron Ingot.
     */
    private final ContainerMapDevice.MapSlot mapSlot;

    public ContainerMapDevice(InventoryPlayer inventory, TileEntityMapDevice tileEntityIn) {
        this.tileEntity = tileEntityIn;
        this.mapSlot = new ContainerMapDevice.MapSlot(tileEntityIn, 0, 136, 110);
        this.addSlotToContainer(this.mapSlot);

        this.addPlayerInventory(inventory, 8, 142, 84);
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileEntity);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.tileEntity.setField(id, data);
    }

    public TileEntityMapDevice getTileEntity() {
        return this.tileEntity;
    }

    /**
     * Called when the container is closed.
     */
    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);

        if (!playerIn.world.isRemote) {
            ItemStack itemstack = this.mapSlot.decrStackSize(this.mapSlot.getSlotStackLimit());

            if (!itemstack.isEmpty()) {
                playerIn.dropItem(itemstack, false);
            }
        }
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 0) {
                if (!this.mergeItemStack(itemstack1, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (this.mergeItemStack(itemstack1, 0, 1, false)) {
                return ItemStack.EMPTY;
            } else if (index < 28) {
                if (!this.mergeItemStack(itemstack1, 28, 37, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < 37) {
                if (!this.mergeItemStack(itemstack1, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 1, 37, false)) {
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

    public static class MapSlot extends Slot {

        public MapSlot(IInventory inventoryIn, int index, int xIn, int yIn) {
            super(inventoryIn, index, xIn, yIn);
        }

        /**
         * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
         */
        @Override
        public boolean isItemValid(ItemStack stack) {
            return stack.getItem() instanceof ItemCombinedMap;
        }

        /**
         * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the
         * case of armor slots)
         */
        @Override
        public int getSlotStackLimit() {
            return 1;
        }
    }

}
