/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis
 */
public class CombinedTool {

    private final String color;
    private final ToolSet toolSet;

    public CombinedTool(String color, ToolSet toolSet) {
        this.color = color;
        this.toolSet = toolSet;
    }

    public String getColor() {
        return color;
    }

    public ToolSet getToolSet() {
        return toolSet;
    }
}
