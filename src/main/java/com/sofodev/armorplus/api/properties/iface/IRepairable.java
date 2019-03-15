/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.properties.iface;

import net.minecraft.item.ItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public interface IRepairable {

    /**
     * @return the represented ItemStack that will be used for repairing the item
     */
    default ItemStack getRepairStack() {
        return ItemStack.EMPTY;
    }
}
