/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.materials;

import com.sofodev.armorplus.common.registry.items.base.ItemBase;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemMaterial extends ItemBase {
    public ItemMaterial(Properties properties) {
        super(properties);
    }

    public enum Ingredient {
        CHAINMAIL,
        GUARDIAN_SCALE,
        WITHER_BONE,
        ENDER_DRAGON_SCALE,
        THE_ULTIMATE_MATERIAL;

        Ingredient() {
        }

        public String getName() {
            return name().toLowerCase();
        }
    }
}
