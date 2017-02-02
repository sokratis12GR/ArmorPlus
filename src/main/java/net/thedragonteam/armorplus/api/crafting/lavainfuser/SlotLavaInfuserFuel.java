/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.lavainfuser;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser.isItemFuel;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class SlotLavaInfuserFuel extends Slot {

    public static Item[] itemList = new Item[]{Items.LAVA_BUCKET, ModItems.lavaCrystal};

    public SlotLavaInfuserFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack) {
        return isItemFuel(stack) || isAllowed(stack);
    }

    public int getItemStackLimit(ItemStack stack) {
        return isAllowed(stack) ? 1 : super.getItemStackLimit(stack);
    }

    public static boolean isAllowed(ItemStack stack) {
        for (Item anItemList : itemList) {
            if (!getItemStack(anItemList).isEmpty())
                return stack.getItem() == anItemList;
        }
        return stack.getItem() == Items.LAVA_BUCKET;
    }
}