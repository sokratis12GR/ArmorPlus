/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.blocks.castle;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

/**
 * @author Sokratis Fotkatzikis
 */
public enum BrickColor implements IStringSerializable {
    WHITE,
    RED,
    BLACK,
    BLUE,
    GREEN,
    YELLOW,
    PURPLE;

    BrickColor() {
    }

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public String getName() {
        return this.toString();
    }
}
