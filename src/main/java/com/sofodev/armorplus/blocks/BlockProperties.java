/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks;

import com.sofodev.armorplus.blocks.base.ToolType;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockProperties {

    private final float resistance;
    private final float hardness;
    private final ToolType tool;
    private final int harvestLevel;
    private final boolean unbreakable;
    private final float lightLevel;
    private final int lightOpacity;

    public BlockProperties(float resistance, float hardness, ToolType tool, int harvestLevel, float lightLevel, int lightOpacity, boolean unbreakable) {
        this.resistance = resistance;
        this.hardness = hardness;
        this.tool = tool;
        this.harvestLevel = harvestLevel;
        this.lightLevel = lightLevel;
        this.lightOpacity = lightOpacity;
        this.unbreakable = unbreakable;
    }

    public BlockProperties(float resistance, float hardness, ToolType tool, int harvestLevel, float lightLevel, int lightOpacity) {
        this(resistance, hardness, tool, harvestLevel, lightLevel, lightOpacity, false);
    }

    public BlockProperties(float resistance, float hardness, ToolType tool, int harvestLevel, float lightLevel, boolean unbreakable) {
        this(resistance, hardness, tool, harvestLevel, lightLevel, 0, unbreakable);
    }

    public BlockProperties(float resistance, float hardness, ToolType tool, int harvestLevel, float lightLevel) {
        this(resistance, hardness, tool, harvestLevel, lightLevel, 0);
    }

    public BlockProperties(float resistance, float hardness, ToolType tool, int harvestLevel, boolean unbreakable) {
        this(resistance, hardness, tool, harvestLevel, 0, unbreakable);
    }

    public BlockProperties(float resistance, float hardness, ToolType tool, int harvestLevel) {
        this(resistance, hardness, tool, harvestLevel, 0);
    }

    public float getResistance() {
        return resistance;
    }

    public float getHardness() {
        return hardness;
    }

    public ToolType getToolType() {
        return tool;
    }

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public float getLightLevel() {
        return lightLevel;
    }

    public int getLightOpacity() {
        return lightOpacity;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }
}
