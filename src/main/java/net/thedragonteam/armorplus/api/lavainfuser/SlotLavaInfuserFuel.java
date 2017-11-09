/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.lavainfuser;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser.isItemFuel;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class SlotLavaInfuserFuel extends Slot {

    public SlotLavaInfuserFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return isItemFuel(stack) || isAllowed(stack);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return isAllowed(stack) ? 1 : super.getItemStackLimit(stack);
    }

    private static Item[] itemList = new Item[]{Items.LAVA_BUCKET, ModItems.lavaCrystal};

    public static boolean isAllowed(ItemStack stack) {
        for (Item item : itemList) {
            if (!getItemStack(item).isEmpty()) {
                return stack.getItem() == item;
            }
        }
        return stack.getItem() == Items.LAVA_BUCKET;
    }
}