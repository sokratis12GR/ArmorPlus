/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.enums;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;

public enum Items implements IStringSerializable {
    ELECTRICAL_INGOT("electrical_ingot", 0),
    STEEL_INGOT("steel_ingot", 1);

    private final String name;

    private final TextFormatting formatting;

    private final int id;

    Items(String nameIn) {
        this(nameIn, TextFormatting.WHITE);
    }

    Items(String nameIn, int ID) {
        this(nameIn, TextFormatting.WHITE, ID);
    }

    Items(String nameIn, TextFormatting formattingIn) {
        this(nameIn, formattingIn, 0);
    }

    Items(String nameIn, TextFormatting formattingIn, int ID) {
        this.name = nameIn;
        this.formatting = formattingIn;
        this.id = ID;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }

    public TextFormatting getFormatting() {
        return formatting;
    }

    public int getId() {
        return id;
    }
}
