package com.sofodev.armorplus.registry.blocks.ore;

public enum Variant {
    STONE(10f, 500f, 0),
    OBSIDIAN(20f, 1000f, 4),
    ORIGINAL(25f, 2000f, 8);

    private final float hardness;
    private final float resistance;
    private final int lightValue;

    Variant(float hardness, float resistance, int lightValue) {
        this.hardness = hardness;
        this.resistance = resistance;
        this.lightValue = lightValue;
    }

    public float getHardness() {
        return this.hardness;
    }

    public float getResistance() {
        return this.resistance;
    }

    public int getLightValue() {
        return lightValue;
    }
}
