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

    public BlockProperties(float resistance, float hardness,  HarvestProps props, float lightLevel, int lightOpacity) {
        this.resistance = resistance;
        this.hardness = hardness;
        this.tool = props.getType();
        this.harvestLevel = props.getHarvestLevel();
        this.lightLevel = lightLevel;
        this.lightOpacity = lightOpacity;
        this.unbreakable = props.isUnbreakable();
    }

    public BlockProperties(float resistance, float hardness, HarvestProps props, float lightLevel) {
        this(resistance, hardness, props, lightLevel, 0);
    }

    public BlockProperties(float resistance, float hardness, HarvestProps props) {
        this(resistance, hardness, props, 0);
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
