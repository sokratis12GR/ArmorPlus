/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items;

import com.sofodev.armorplus.items.base.ItemBase;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemUltimatePart extends ItemBase {

    public enum UltimatePart {
        HELMET_LEFT,
        HELMET_MIDDLE,
        HELMET_RIGHT,
        CHESTPLATE_LEFT,
        CHESTPLATE_MIDDLE,
        CHESTPLATE_RIGHT,
        LEGGINGS_LEFT,
        LEGGINGS_MIDDLE,
        LEGGINGS_RIGHT,
        BOOTS_LEFT,
        BOOTS_MIDDLE,
        BOOTS_RIGHT;

        UltimatePart() {
        }

        public String getName() {
            return "ultimate_part_" + this.name().toLowerCase();
        }
    }

    public ItemUltimatePart(Properties properties) {
        super(properties);
    }
}
