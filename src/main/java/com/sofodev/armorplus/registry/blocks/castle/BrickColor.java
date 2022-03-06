package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.world.level.material.MaterialColor;

import java.io.Serializable;
import java.util.Locale;

import static net.minecraft.world.level.material.MaterialColor.*;

public enum BrickColor implements Serializable {
    BLACK(TERRACOTTA_BLACK),
    BLUE(TERRACOTTA_BLUE),
    GREEN(TERRACOTTA_GREEN),
    PURPLE(TERRACOTTA_PURPLE),
    RED(TERRACOTTA_RED),
    WHITE(TERRACOTTA_WHITE),
    YELLOW(TERRACOTTA_YELLOW),
    ORANGE(TERRACOTTA_ORANGE);

    private final MaterialColor color;

    BrickColor(MaterialColor color) {
        this.color = color;
    }

    public MaterialColor get() {
        return color;
    }

    public String getName() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}