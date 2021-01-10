package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;

import static net.minecraft.block.material.MaterialColor.*;

public enum BrickColor implements IStringSerializable {
    BLACK(BLACK_TERRACOTTA),
    BLUE(BLUE_TERRACOTTA),
    GREEN(GREEN_TERRACOTTA),
    PURPLE(PURPLE_TERRACOTTA),
    RED(RED_TERRACOTTA),
    WHITE(WHITE_TERRACOTTA),
    YELLOW(YELLOW_TERRACOTTA),
    ORANGE(ORANGE_TERRACOTTA);

    private final MaterialColor color;

    BrickColor(MaterialColor color) {
        this.color = color;
    }

    public MaterialColor get() {
        return color;
    }

    @Override
    public String getString() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    public String getName() {
        return getString();
    }
}