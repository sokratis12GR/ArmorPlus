/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemStackUtils {
    public static ItemStack getTICItem(String name, int meta) {
        return getItemStack(Item.getByNameOrId("tconstruct:" + name), meta);
    }

    public static ItemStack getARPItem(String name, int meta) {
        return getItemStack(Item.getByNameOrId("armorplus:" + name), meta);
    }

    public static ItemStack getItemStack(Item item, int meta) {
        return new ItemStack(item, 1, meta);
    }

    public static ItemStack getItemStack(Item item) {
        return new ItemStack(item, 1);
    }

    public static ItemStack getItemStack(Block block, int meta) {
        return new ItemStack(block, 1, meta);
    }

    public static ItemStack getItemStack(Block block) {
        return new ItemStack(block, 1);
    }
}
