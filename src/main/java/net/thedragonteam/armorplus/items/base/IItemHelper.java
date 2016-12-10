/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IItemHelper {

    ItemStack getItemStack(ItemStack stack);

    Item getItem(Item item);

    ItemStack getItemStack();

    Item getItem();

    void initModel();

    String getName(String name);

    String getName();
}
