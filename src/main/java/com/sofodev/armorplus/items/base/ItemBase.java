/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.base;

import com.sofodev.armorplus.items.enums.MetalItems;
import net.minecraft.item.Item;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemBase extends Item {

    public ItemBase(Properties properties) {
        super(properties);
    }

    public ItemBase(MetalItems item, Properties properties) {
        this(properties);
    }

}