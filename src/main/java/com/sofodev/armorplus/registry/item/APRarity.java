package com.sofodev.armorplus.registry.item;

import net.minecraft.ChatFormatting;

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
}