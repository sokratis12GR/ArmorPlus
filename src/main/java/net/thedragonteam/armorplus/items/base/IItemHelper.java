/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IItemHelper {

    void getItemStack(ItemStack stack);

    void getItem(Item item);

    ItemStack getItemStack();

    Item getItem();

    void initModel();
}
