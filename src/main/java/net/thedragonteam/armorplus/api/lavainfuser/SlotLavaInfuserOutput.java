/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.lavainfuser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */

public class SlotLavaInfuserOutput extends Slot {
    /**
     * The player that is using the GUI where this slot resides.
     */
    private EntityPlayer player;
    private int removeCount = 0;

    public SlotLavaInfuserOutput(EntityPlayer player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
        this.player = player;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int amount) {
        if (this.getHasStack()) this.removeCount += Math.min(amount, this.getStack().getCount());
        return super.decrStackSize(amount);
    }

    @Override
    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
        this.onCrafting(stack);
        super.onTake(thePlayer, stack);
        return stack;
    }

    @Override
    protected void onCrafting(ItemStack stack, int amount) {
        this.removeCount += amount;
        this.onCrafting(stack);
    }

    @Override
    protected void onCrafting(ItemStack stack) {
        stack.onCrafting(this.player.world, this.player, this.removeCount);
        this.removeCount = 0;
        FMLCommonHandler.instance().firePlayerSmeltedEvent(player, stack);
    }
}