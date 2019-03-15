/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.properties;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class ArmorSet {

    private final Item head;
    private final Item chest;
    private final Item legs;
    private final Item feet;
    private final ItemStack[] set;

    public ArmorSet(Item head, Item chest, Item legs, Item feet) {
        this.head = head;
        this.chest = chest;
        this.legs = legs;
        this.feet = feet;
        this.set = new ItemStack[]{new ItemStack(head), new ItemStack(chest), new ItemStack(legs), new ItemStack(feet)};
    }

    public Item getHead() {
        return head;
    }

    public Item getChest() {
        return chest;
    }

    public Item getLegs() {
        return legs;
    }

    public Item getFeet() {
        return feet;
    }

    public ItemStack[] getSet() {
        return set;
    }
}
