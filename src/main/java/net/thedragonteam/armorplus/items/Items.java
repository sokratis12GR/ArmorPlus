/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;

public enum Items implements IStringSerializable {
    CHAINMAIL("chainmail", 0),
    ELECTRICAL_INGOT("electrical_ingot", 1),
    GUARDIAN_SCALE("guardian_scale", TextFormatting.AQUA, 2),
    ENDER_DRAGON_SCALE("ender_dragon_scale", TextFormatting.DARK_PURPLE, 3),
    STEEL_INGOT("steel_ingot", 4),
    WITHER_BONE("wither_bone", TextFormatting.WHITE, 5),
    THE_ULTIMATE_MATERIAL("the_ultimate_material", TextFormatting.GREEN, 6),
    ARMORPLUS_BOOK("armorplus_book", 10),
    ARMORPLUS_INFO_BOOK("armorplus_info_book", 11);

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

    public String toString() {
        return this.name;
    }

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
