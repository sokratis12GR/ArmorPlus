/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.lavainfuser;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser.isItemFuel;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class SlotLavaInfuserFuel extends Slot {

    public SlotLavaInfuserFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    public static boolean isAllowed(ItemStack stack) {
        return stack.getItem() == Items.LAVA_BUCKET || stack.getItem() == ModItems.itemLavaCrystal;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return isItemFuel(stack) || isAllowed(stack);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return isAllowed(stack) ? 1 : super.getItemStackLimit(stack);
    }
}