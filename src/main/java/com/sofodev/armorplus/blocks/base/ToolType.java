/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.base;

import java.util.Locale;

/**
 * @author Sokratis Fotkatzikis
 */
public enum ToolType {
    PICKAXE,
    AXE,
    SHOVEL;

    public String getTool() {
        return name().toLowerCase(Locale.ROOT);
    }
}