package com.sofodev.armorplus.registry.blocks.ore;

public enum Variant {
    STONE(10f, 500f, 2),
    OBSIDIAN(20f, 1000f, 3),
    ORIGINAL(25f, 2000f, 4);

    private final float hardness;
    private final float resistance;
    private final int harvestLevel;

    Variant(float hardness, float resistance, int harvestLevel) {
        this.hardness = hardness;
        this.resistance = resistance;
        this.harvestLevel = harvestLevel;
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
}