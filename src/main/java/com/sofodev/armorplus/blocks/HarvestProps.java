/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks;

import net.minecraftforge.common.ToolType;

public class HarvestProps {
    private final ToolType tool;
    private final int harvestLevel;
    private final boolean unbreakable;

    public HarvestProps(ToolType tool, int harvestLevel, boolean unbreakable) {
        this.tool = tool;
        this.harvestLevel = harvestLevel;
        this.unbreakable = unbreakable;
    }

    public HarvestProps(ToolType tool, int harvestLevel) {
        this(tool, harvestLevel, false);
    }

    public HarvestProps(ToolType tool) {
        this(tool, 0);
    }

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public ToolType getType() {
        return tool;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }
}
