package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;

import static net.minecraft.block.material.MaterialColor.*;

public enum BrickColor implements IStringSerializable {
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

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    public String getName() {
        return getSerializedName();
    }
}