/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.iface;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IItemHelper extends INameHelper<String> {

    ItemStack getItemStack(ItemStack stack);

    Item getItem(Item item);

    ItemStack getItemStack();

    Item getItem();
}