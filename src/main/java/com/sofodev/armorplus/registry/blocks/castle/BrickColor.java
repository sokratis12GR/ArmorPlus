package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.world.level.material.MapColor;

import java.io.Serializable;
import java.util.Locale;

import static net.minecraft.world.level.material.MapColor.*;

public enum BrickColor implements Serializable {
    BLACK(TERRACOTTA_BLACK), BLUE(TERRACOTTA_BLUE), GREEN(TERRACOTTA_GREEN), PURPLE(TERRACOTTA_PURPLE), RED(TERRACOTTA_RED), WHITE(TERRACOTTA_WHITE), YELLOW(TERRACOTTA_YELLOW), ORANGE(TERRACOTTA_ORANGE);

    private final MapColor color;

    BrickColor(MapColor color) {
        this.color = color;
    }

    public MapColor get() {
        return color;
    }

    public String getName() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}