/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.blocks.base;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;
import java.util.Locale;

/**
 * @author Sokratis Fotkatzikis
 */
public enum ToolType implements IStringSerializable {
    PICKAXE("Pickaxe"),
    AXE("Axe"),
    SHOVEL("Shovel");

    private final String name;

    ToolType(String nameIn) {
        name = nameIn;
    }

    public String getTool() {
        return name().toLowerCase(Locale.ROOT);
    }

    @Override
    @Nonnull
    public String getName() {
        return name;
    }
}