/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.thedragonteam.armorplus.api.crafting.lavainfuser.LavaInfuserCraftingManager;
import net.thedragonteam.armorplus.api.crafting.lavainfuser.SlotCrafting;
import net.thedragonteam.armorplus.container.base.ContainerBase;
import net.thedragonteam.armorplus.tileentity.base.TileEntityLavaInfuser;

public class ContainerLavaInfuser extends ContainerBase {

    private static final int ITEM_BOX = 18;
    private static final int RECIPE_SLOTS = 2;
    private static final int FULL_INVENTORY_SLOTS = RECIPE_SLOTS + 36;
    private static final int MAIN_INVENTORY_SLOTS = RECIPE_SLOTS + 27;
    private TileEntityLavaInfuser tile;
    private final World world;


    public ContainerLavaInfuser(InventoryPlayer playerInv, TileEntityLavaInfuser tile) {
        this.tile = tile;
        this.world = tile.getWorld();
        addSlotToContainer(new SlotCrafting(playerInv.player, tile.itemHandler, 0, 124, 35));

        addSlotToContainer(new SlotIngredient(tile.itemHandler, 1, 30 + ITEM_BOX, 17 + ITEM_BOX));

        for (int k = 0; k < 3; ++k)
            for (int i1 = 0; i1 < 9; ++i1)
                addSlotToContainer(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));

        for (int l = 0; l < 9; ++l)
            addSlotToContainer(new Slot(playerInv, l, 8 + l * 18, 142));

        onCraftMatrixChanged(tile.itemHandler);
    }


    public void onCraftMatrixChanged(IItemHandler itemHandler) {
        this.tile.itemHandler.setStackInSlot(0, LavaInfuserCraftingManager.getInstance().findMatchingRecipe(this.tile.itemHandler, this.world));
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 0) {
                itemstack1.getItem().onCreated(itemstack1, this.world, playerIn);

                if (!this.mergeItemStack(itemstack1, RECIPE_SLOTS, FULL_INVENTORY_SLOTS, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= RECIPE_SLOTS && index < MAIN_INVENTORY_SLOTS) {
                if (!this.mergeItemStack(itemstack1, MAIN_INVENTORY_SLOTS, FULL_INVENTORY_SLOTS, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= MAIN_INVENTORY_SLOTS && index < FULL_INVENTORY_SLOTS) {
                if (!this.mergeItemStack(itemstack1, RECIPE_SLOTS, MAIN_INVENTORY_SLOTS, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, RECIPE_SLOTS, FULL_INVENTORY_SLOTS, false)) {
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

            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);

            if (index == 0) {
                playerIn.dropItem(itemstack2, false);
            }
        }

        return itemstack;
    }

    /**
     * Called when the container is closed.
     */
    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);

        if (!this.tile.getWorld().isRemote) {
            for (int i = 0; i < this.tile.inventorySize; ++i) {
                ItemStack itemstack = this.tile.itemHandler.getStackInSlot(i);

                if (!itemstack.isEmpty()) {
                    playerIn.dropItem(itemstack, false);
                }
            }
        }
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
    @Override
    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return slotIn.inventory != this.tile.itemHandler && super.canMergeSlot(stack, slotIn);
    }
}