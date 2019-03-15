/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.base;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.items.enums.Cosmetics;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemCosmetic extends ItemBase {

    private final Cosmetics cosmetics;

    public ItemCosmetic(Cosmetics cosmetics) {
        super(new Properties().group(ArmorPlus.tabArmorplusItems));
        this.cosmetics = cosmetics;
    }
}
