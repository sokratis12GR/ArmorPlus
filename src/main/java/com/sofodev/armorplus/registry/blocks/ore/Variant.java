package com.sofodev.armorplus.registry.blocks.ore;

public enum Variant {
    STONE(10f, 500f, 2, 0),
    OBSIDIAN(20f, 1000f, 3, 4),
    ORIGINAL(25f, 2000f, 4, 8);

    private final float hardness;
    private final float resistance;
    private final int harvestLevel;
    private int lightValue;

    Variant(float hardness, float resistance, int harvestLevel, int lightValue) {
        this.hardness = hardness;
        this.resistance = resistance;
        this.harvestLevel = harvestLevel;
        this.lightValue = lightValue;
    }

    public float getHardness() {
        return this.hardness;
    }

    public float getResistance() {
        return this.resistance;
    }

    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    public int getLightValue() {
        return lightValue;
    }
}
