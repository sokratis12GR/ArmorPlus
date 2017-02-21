/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.minecraft.item.Item.getByNameOrId;
import static net.minecraft.item.Item.getItemFromBlock;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */

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

    public static ItemStack getItemStack(Item item, int amount, int meta) {
        return new ItemStack(item, amount, meta);
    }

    public static ItemStack getItemStack(Item item, int meta) {
        return getItemStack(item, 1, meta);
    }

    public static ItemStack getItemStack(Item item) {
        return new ItemStack(item, 1);
    }

    public static ItemStack getItemStack(Block block, int amount, int meta) {
        return new ItemStack(block, amount, meta);
    }

    public static ItemStack getItemStack(Block block, int meta) {
        return getItemStack(block, 1, meta);
    }

    public static ItemStack getItemStack(Block block) {
        return new ItemStack(block, 1);
    }

    public static ItemStack getEmptyStack() {
        return null;
    }

    public static Item getItem(ItemStack stack) {
        return stack.getItem();
    }

    public static Item getItem(Block block) {
        return getItemFromBlock(block);
    }

    public static Item getItem(String modName, String itemName) {
        return getByNameOrId(modName + ":" + itemName);
    }

    public static Item getItem(String name) {
        return getByNameOrId(name);
    }
}
