package net.thedragonteam.armorplus.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.lavainfuser.LavaInfuserManager;
import net.thedragonteam.armorplus.api.lavainfuser.SlotLavaInfuserFuel;
import net.thedragonteam.armorplus.api.lavainfuser.SlotLavaInfuserOutput;
import net.thedragonteam.armorplus.container.base.ContainerBase;
import net.thedragonteam.armorplus.tileentity.TileLavaInfuser;

import java.util.stream.IntStream;

import static net.minecraft.item.ItemStack.EMPTY;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ContainerLavaInfuser extends ContainerBase {
    private static final int ITEM_BOX = 18;
    private int infusionTime = 0;
    private int totalInfusionTime = 0;
    private int infuserInfusingTime = 0;
    private int currentItemInfusingTime = 0;
    private TileLavaInfuser tile;

    public ContainerLavaInfuser(InventoryPlayer playerInventory, TileLavaInfuser tile) {
        this.tile = tile;
        this.addSlotToContainer(new Slot(tile, 0, 69, 35));
        this.addSlotToContainer(new SlotLavaInfuserFuel(tile, 1, 34, 35));
        this.addSlotToContainer(new SlotLavaInfuserOutput(playerInventory.player, tile, 2, 124, 35));

        for (int height = 0; height < 3; height++) {
            for (int width = 0; width < 9; width++) {
                this.addSlotToContainer(new Slot(playerInventory, width + height * 9 + 9, 8 + width * ITEM_BOX, 84 + height * 18));
            }
        }

        IntStream.range(0, 9).mapToObj(index -> new Slot(playerInventory, index, 8 + index * ITEM_BOX, 142)).forEach(this::addSlotToContainer);
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
            if (this.infusionTime != this.tile.getField(2))
                listener.sendWindowProperty(this, 2, this.tile.getField(2));
            if (this.infuserInfusingTime != this.tile.getField(0))
                listener.sendWindowProperty(this, 0, this.tile.getField(0));
            if (this.currentItemInfusingTime != this.tile.getField(1))
                listener.sendWindowProperty(this, 1, this.tile.getField(1));
            if (this.totalInfusionTime != this.tile.getField(3))
                listener.sendWindowProperty(this, 3, this.tile.getField(3));
        });

        this.infusionTime = this.tile.getField(2);
        this.infuserInfusingTime = this.tile.getField(0);
        this.currentItemInfusingTime = this.tile.getField(1);
        this.totalInfusionTime = this.tile.getField(3);
    }

    @Override
    public void updateProgressBar(int id, int data) {
        this.tile.setField(id, data);
    }


    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = EMPTY;
        Slot slot = this.inventorySlots.get(index);


        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();

            if (index == 2) {
                if (!this.mergeItemStack(slotStack, 3, 39, true)) {
                    return EMPTY;
                }

                slot.onSlotChange(slotStack, itemstack);
            } else if (index != 1 && index != 0) {
                if (!LavaInfuserManager.getInstance().getInfusingResult(slotStack).isEmpty()) {
                    if (!this.mergeItemStack(slotStack, 0, 1, false)) {
                        return EMPTY;
                    }
                } else if (TileLavaInfuser.isItemFuel(slotStack)) {
                    if (!this.mergeItemStack(slotStack, 1, 2, false)) {
                        return EMPTY;
                    }
                } else if (index < 30) {
                    if (!this.mergeItemStack(slotStack, 30, 39, false)) {
                        return EMPTY;
                    }
                } else if (index < 39 && !this.mergeItemStack(slotStack, 3, 30, false)) {
                    return EMPTY;
                }
            }

            if (slotStack.isEmpty()) {
                slot.putStack(EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.getCount() == itemstack.getCount()) {
                return EMPTY;
            }

            slot.onTake(playerIn, slotStack);
        }

        return itemstack;
    }
}