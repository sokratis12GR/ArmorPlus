/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * @author Sokratis Fotkatzikis
 */
public class SlotArmor extends Slot {

    public static final String[] EMPTY_SLOT_NAMES = new String[]{"minecraft:items/empty_armor_slot_boots", "minecraft:items/empty_armor_slot_leggings", "minecraft:items/empty_armor_slot_chestplate", "minecraft:items/empty_armor_slot_helmet"};
    private final EntityPlayer player;
    private final EntityEquipmentSlot armorType;

    /**
     * Make a new instance.
     *
     * @param inventory The inventory this slot will be in.
     * @param index     The index of this slot.
     * @param x         X coordinate.
     * @param y         POS_Y coordinate.
     * @param player    The player entity.
     * @param armorType The armor type.
     */
    public SlotArmor(IInventory inventory, int index, int x, int y, EntityPlayer player, EntityEquipmentSlot armorType) {
        super(inventory, index, x, y);
        this.player = player;
        this.armorType = armorType;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem().canEquip(stack, armorType, player);
    }

    @Nullable
    @Override
    public String getSlotTexture() {
        return EMPTY_SLOT_NAMES[armorType.getIndex()];
    }

}