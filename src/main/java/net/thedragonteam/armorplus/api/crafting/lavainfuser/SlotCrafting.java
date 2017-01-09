/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.lavainfuser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.SlotItemHandler;
import net.thedragonteam.thedragonlib.util.ItemStackHandlerImproved;

public class SlotCrafting extends SlotItemHandler {
    /**
     * The craft matrix inventory linked to this result slot.
     */
    private final ItemStackHandlerImproved itemHandler;
    /**
     * The player that is using the GUI where this slot resides.
     */
    private final EntityPlayer player;
    /**
     * The number of items that have been crafted so far. Gets passed to ItemStack.onCrafting before being reset.
     */
    private int amountCrafted;

    public SlotCrafting(EntityPlayer player, ItemStackHandlerImproved itemHandler, int slotIndex, int xPosition, int yPosition) {
        super(itemHandler, slotIndex, xPosition, yPosition);
        this.player = player;
        this.itemHandler = itemHandler;
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor itemHandler as well as furnace fuel.
     */
    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    @Override
    public ItemStack decrStackSize(int amount) {
        if (this.getHasStack()) {
            this.amountCrafted += Math.min(amount, this.getStack().getCount());
        }

        return super.decrStackSize(amount);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    @Override
    protected void onCrafting(ItemStack stack, int amount) {
        this.amountCrafted += amount;
        this.onCrafting(stack);
    }

    @Override
    protected void onSwapCraft(int p_190900_1_) {
        this.amountCrafted += p_190900_1_;
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    @Override
    protected void onCrafting(ItemStack stack) {

        if (this.amountCrafted > 0) {
            stack.onCrafting(this.player.world, this.player, this.amountCrafted);
        }

        this.amountCrafted = 0;
    }


    @Override
    public ItemStack onTake(EntityPlayer player, ItemStack stack) {
        this.onCrafting(stack);
        ForgeHooks.setCraftingPlayer(player);
        NonNullList<ItemStack> nonnulllist = LavaInfuserCraftingManager.getInstance().getRemainingItems(this.itemHandler, player.world);
        ForgeHooks.setCraftingPlayer(null);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = this.itemHandler.getStackInSlot(i);
            ItemStack itemstack1 = nonnulllist.get(i);

            if (!itemstack.isEmpty()) {
                this.itemHandler.getStackInSlot(i);
                itemstack = this.itemHandler.getStackInSlot(i);
            }

            if (!itemstack1.isEmpty()) {
                if (itemstack.isEmpty()) {
                    this.itemHandler.setStackInSlot(i, itemstack1);
                } else if (ItemStack.areItemsEqual(itemstack, itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1)) {
                    itemstack1.grow(itemstack.getCount());
                    this.itemHandler.setStackInSlot(i, itemstack1);
                } else if (!this.player.inventory.addItemStackToInventory(itemstack1)) {
                    this.player.dropItem(itemstack1, false);
                }
            }
        }

        return stack;
    }
}
