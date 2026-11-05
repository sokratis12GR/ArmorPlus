package com.sofodev.armorplus.registry.item;

import net.minecraft.ChatFormatting;

import static net.minecraft.ChatFormatting.AQUA;
import static net.minecraft.ChatFormatting.BLUE;
import static net.minecraft.ChatFormatting.DARK_AQUA;
import static net.minecraft.ChatFormatting.DARK_BLUE;
import static net.minecraft.ChatFormatting.DARK_GRAY;
import static net.minecraft.ChatFormatting.DARK_GREEN;
import static net.minecraft.ChatFormatting.DARK_PURPLE;
import static net.minecraft.ChatFormatting.DARK_RED;
import static net.minecraft.ChatFormatting.GOLD;
import static net.minecraft.ChatFormatting.GRAY;
import static net.minecraft.ChatFormatting.GREEN;
import static net.minecraft.ChatFormatting.LIGHT_PURPLE;
import static net.minecraft.ChatFormatting.RED;
import static net.minecraft.ChatFormatting.RESET;
import static net.minecraft.ChatFormatting.WHITE;
import static net.minecraft.ChatFormatting.YELLOW;

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
    WARDEN(DARK_AQUA),
    SLAYER(DARK_PURPLE),
    AMETHYST(LIGHT_PURPLE),
    QUARTZ(WHITE),
    GLOWSTONE(YELLOW),
    HONEY(GOLD),
    CHICKEN(WHITE),
    SLIME(GREEN),
    ARDITE(GOLD),
    COBALT(BLUE),
    KNIGHT_SLIME(LIGHT_PURPLE),
    PIG_IRON(RED),
    MANYULLYN(DARK_PURPLE),
    FROST(AQUA),
    FROST_LAVA(DARK_RED),
    CHAINMAIL(GRAY),
    GOLDEN(YELLOW),
    COPPER(GOLD),
    IRON(WHITE),
    DIAMOND(AQUA),
    NETHERITE(DARK_GRAY);

    private final ChatFormatting color;

    APRarity(ChatFormatting color) {
        this.color = color;
    }

    public ChatFormatting getColor() {
        return color;
    }
}