/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.minecraft.item.Item.getByNameOrId;

public class ItemStackUtils {
    public static ItemStack getTICItemStack(String name, int meta) {
        return getItemStack(getByNameOrId("tconstruct:" + name), meta);
    }

    public static ItemStack getAPItemStack(String name, int meta) {
        return getItemStack(getByNameOrId("armorplus:" + name), meta);
    }

    public static ItemStack getAPItemStack(String name) {
        return getItemStack(getByNameOrId("armorplus:" + name), 0);
    }

    public static ItemStack getItemStack(String modid, String itemName, int meta) {
        return getItemStack(getByNameOrId(modid + ":" + itemName), meta);
    }

    public static ItemStack getItemStack(String name, int meta) {
        return getItemStack(getByNameOrId(name), meta);
    }

    public static ItemStack getItemStack(String name) {
        return getItemStack(getByNameOrId(name), 0);
    }

    public static ItemStack getItemStack(String modid, String itemName) {
        return getItemStack(getByNameOrId(modid + ":" + itemName), 0);
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

    public static ItemStack getEmptyStack() {
        return ItemStack.EMPTY;
    }
}
