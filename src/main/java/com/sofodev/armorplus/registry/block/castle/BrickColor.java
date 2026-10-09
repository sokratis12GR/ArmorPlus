package com.sofodev.armorplus.registry.block.castle;

import net.minecraft.world.level.material.MapColor;

import java.io.Serializable;
import java.util.Locale;

import static net.minecraft.world.level.material.MapColor.*;

public enum BrickColor implements Serializable {
    BLACK(TERRACOTTA_BLACK),
    BLUE(TERRACOTTA_BLUE),
    BROWN(TERRACOTTA_BROWN),
    CYAN(TERRACOTTA_CYAN),
    GRAY(TERRACOTTA_GRAY),
    GREEN(TERRACOTTA_GREEN),
    LIGHT_BLUE(TERRACOTTA_LIGHT_BLUE),
    LIGHT_GRAY(TERRACOTTA_LIGHT_GRAY),
    LIME(TERRACOTTA_LIGHT_GREEN),
    MAGENTA(TERRACOTTA_MAGENTA),
    ORANGE(TERRACOTTA_ORANGE),
    PINK(TERRACOTTA_PINK),
    PURPLE(TERRACOTTA_PURPLE),
    RED(TERRACOTTA_RED),
    WHITE(TERRACOTTA_WHITE),
    YELLOW(TERRACOTTA_YELLOW);

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