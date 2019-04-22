/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.items.enums;

/**
 * @author Sokratis Fotkatzikis
 **/
public enum MetalItems {
    ELECTRICAL_INGOT,
    STEEL_INGOT;

    private final int id;

    MetalItems() {
        this.id = ordinal();
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public int getId() {
        return id;
    }
}