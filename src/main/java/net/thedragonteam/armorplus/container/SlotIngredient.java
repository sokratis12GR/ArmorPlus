/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import net.thedragonteam.thedragonlib.util.ItemStackHandlerImproved;

import javax.annotation.Nonnull;

import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class SlotIngredient extends SlotItemHandler {

    public SlotIngredient(ItemStackHandlerImproved inventory, int number, int x, int y) {
        super(inventory, number, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return super.isItemValid(stack) || stack == getItemStack(lavaCrystal) || stack == getItemStack(lavaCrystal, 1) || stack == getItemStack(Items.LAVA_BUCKET);
    }

    @Override
    public int getItemStackLimit(@Nonnull ItemStack stack) {
        return 1;
    }
}