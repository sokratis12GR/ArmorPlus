/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class ToolTipUtils {

    public static void showInfo(List<String> tooltip, KeyBinding keyBinding, TextFormatting formatting) {
        tooltip.add(I18n.format("tooltip.shift.showinfo", formatting, keyBinding.getDisplayName(), TextFormatting.GRAY));
    }

    public static void addToolTipFull(List<String> tooltip, String ability, int amplifier) {
        addToolTipFull(tooltip, ability + " " + (amplifier + 1));
    }

    public static void addToolTipFull(List<String> tooltip, String ability) {
        tooltip.add("\2479Ability: " + "\247r" + ability);
        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
    }

    public static void addToolTipPiece(List<String> tooltip, String ability, int amplifier) {
        addToolTipPiece(tooltip, ability + " " + (amplifier + 1));
    }

    public static void addToolTipPiece(List<String> tooltip, String ability) {
        tooltip.add("\2479Ability: " + "\247r" + ability);
        tooltip.add("\2473Use: " + "\247rEquip A Piece");
    }

    public static boolean isKeyDown() {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        return GameSettings.isKeyDown(keyBindSneak);
    }
}
