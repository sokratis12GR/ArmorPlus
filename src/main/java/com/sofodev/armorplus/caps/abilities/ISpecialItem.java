/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.caps.abilities;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public interface ISpecialItem {

    boolean isSpecial(ItemStack stack);

    MaterialType getMaterial();

    boolean hasSpecialMaterial();

    default boolean isSpecialArmor(ItemStack stack) {
        return isSpecial(stack) && stack.getItem() instanceof ItemArmor;
    }

    EntityEquipmentSlot getSlot();

}
