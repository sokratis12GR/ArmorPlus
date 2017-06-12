package net.thedragonteam.armorplus.blocks.dungeon;

import net.minecraft.util.IStringSerializable;

/**
 * Ender Dungeon Blocks - by sokratis12GR
 */
public enum EnumEnderBlocks implements IStringSerializable {
    ENDER_STONE("ender_stone"),
    ENDER_STONE_BRICKS("ender_stone_bricks"),
    ENDER_PILLAR("ender_pillar"),
    ENDER_GLOWSTONE("ender_glowstone", 1.0F),
    ENDER_FLOOR_1("ender_floor_1"),
    ENDER_FLOOR_2("ender_floor_2"),
    ENDER_STONE_TRAP("ender_stone_trap"),;

    private final String name;
    private final float lightLevel;

    EnumEnderBlocks(String nameIn, float lightLevelIn) {
        this.name = nameIn;
        this.lightLevel = lightLevelIn;
    }

    EnumEnderBlocks(String nameIn) {
        this(nameIn, 0);
    }

    @Override
    public String getName() {
        return name;
    }

    public float getLightLevel() {
        return lightLevel;
    }
}
