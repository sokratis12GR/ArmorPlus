/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.caps.abilities.data;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public interface ISpecialItem {

    MaterialType getMaterial();

    default boolean isSpecialArmor(ItemStack stack) {
        return stack.getItem() instanceof ItemArmor;
    }

    EntityEquipmentSlot getSlot();

}
