/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks;

import com.sofodev.armorplus.blocks.base.ToolType;

public class HarvestProps {
    private final float hardness;
    private final ToolType tool;
    private final int harvestLevel;
    private final boolean unbreakable;

    public HarvestProps(float hardness, ToolType tool, int harvestLevel, boolean unbreakable) {
        this.hardness = hardness;
        this.tool = tool;
        this.harvestLevel = harvestLevel;
        this.unbreakable = unbreakable;
    }

    public float getHardness() {
        return hardness;
    }

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public ToolType getTool() {
        return tool;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }
}
