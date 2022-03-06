package com.sofodev.armorplus.registry.items;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

import java.util.Locale;

import static net.minecraft.ChatFormatting.*;

public enum APRarity {
    NONE(RESET),
    COAL(GRAY),
    REDSTONE(DARK_RED),
    LAPIS(DARK_BLUE),
    EMERALD(DARK_GREEN),
    OBSIDIAN(DARK_GRAY),
    INFUSED_LAVA(GOLD),
    GUARDIAN(BLUE),
    SUPER_STAR(WHITE),
    ENDER_DRAGON(DARK_PURPLE),
    SLAYER(DARK_PURPLE);

    private final ChatFormatting color;

    APRarity(ChatFormatting color) {
        this.color = color;
    }

    public ChatFormatting getColor() {
        return color;
    }

    public Rarity getRarity() {
        return Rarity.create(this.name().toLowerCase(Locale.ENGLISH), this.getColor());
    }
}