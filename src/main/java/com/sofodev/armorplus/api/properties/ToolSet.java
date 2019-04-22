/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis
 */
public class ToolSet {

    private final Tool pickaxe;

    public ToolSet(double efficiency, int dur) {
        pickaxe = new Tool(efficiency, dur);
    }

    /**
     * Creates a set of tools for the type with the specified values which are the pickaxe, shovel and the axe
     *
     * @param pickaxe The tool properties for the pickaxe
     */
    public ToolSet(Tool pickaxe) {
        this.pickaxe = pickaxe;
    }

    public Tool getPickaxe() {
        return pickaxe;
    }
}
