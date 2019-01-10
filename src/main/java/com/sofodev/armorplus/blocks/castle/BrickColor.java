/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.castle;

import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;

/**
 * @author Sokratis Fotkatzikis
 */
public enum BrickColor implements IStringSerializable {
    WHITE(MapColor.SNOW),
    RED(MapColor.RED),
    BLACK(MapColor.BLACK),
    BLUE(MapColor.BLUE),
    GREEN(MapColor.GREEN),
    YELLOW(MapColor.YELLOW),
    PURPLE(MapColor.PURPLE);

    /**
     * The color that represents this entry on a map.
     */
    private final MapColor mapColor;

    BrickColor(MapColor mapColorIn) {
        this.mapColor = mapColorIn;
    }

    /**
     * The color which represents this entry on a map.
     */
    public MapColor getMapColor() {
        return this.mapColor;
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
