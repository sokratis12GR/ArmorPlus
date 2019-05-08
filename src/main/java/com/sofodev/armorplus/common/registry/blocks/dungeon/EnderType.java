/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.blocks.dungeon;

import net.minecraft.util.IStringSerializable;

/**
 * @author Sokratis Fotkatzikis
 */
public enum EnderType implements IStringSerializable {
    ENDER_STONE,
    ENDER_STONE_BRICKS,
    ENDER_PILLAR,
    ENDER_GLOWSTONE(1.0F),
    ENDER_FLOOR_1,
    ENDER_FLOOR_2,
    ENDER_STONE_TRAP,
    ;

    private final float lightLevel;

    EnderType(float lightLevelIn) {
        this.lightLevel = lightLevelIn;
    }

    EnderType() {
        this(0);
    }

    @Override
    public String getName() {
        return this.name().toLowerCase();
    }

    public float getLightLevel() {
        return lightLevel;
    }
}
