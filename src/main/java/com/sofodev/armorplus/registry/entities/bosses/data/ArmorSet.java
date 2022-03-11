package com.sofodev.armorplus.registry.entities.bosses.data;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.getItemStack;

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
        this.set = new ItemStack[]{getItemStack(head), getItemStack(chest), getItemStack(legs), getItemStack(feet)};
    }

    public ArmorSet(String head, String chest, String legs, String feet) {
        this(getAPItem(head), getAPItem(chest), getAPItem(legs), getAPItem(feet));
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