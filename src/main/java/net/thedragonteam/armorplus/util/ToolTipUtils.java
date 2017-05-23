/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.thedragonlib.util.TextUtils;

import java.util.List;

import static net.minecraft.util.text.TextFormatting.GRAY;

@SideOnly(Side.CLIENT)
public class ToolTipUtils {

    public static void showInfo(List<String> tooltip, KeyBinding keyBinding, TextFormatting formatting) {
        tooltip.add(TextUtils.INSTANCE.formattedText(GRAY, "tooltip.showinfo.beginning", TextUtils.INSTANCE.formattedText(formatting, "tooltip.showinfo.keybind", keyBinding.getDisplayName(),
                TextUtils.INSTANCE.formattedText(GRAY, "tooltip.showinfo.end"))));
    }

    public static void addToolTipFull(List<String> tooltip, String ability, int amplifier) {
        addToolTipFull(tooltip, ability + " " + level(amplifier));
    }

    public static void addToolTipFull(List<String> tooltip, String ability) {
        tooltip.add("\2479Ability: " + "\247r" + ability);
        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
    }

    public static void addToolTipPiece(List<String> tooltip, String ability, int amplifier) {
        addToolTipPiece(tooltip, ability + " " + level(amplifier));
    }

    public static void addToolTipPiece(List<String> tooltip, String ability) {
        tooltip.add("\2479Ability: " + "\247r" + ability);
        tooltip.add("\2473Use: " + "\247rEquip A Piece");
    }

    public static boolean isKeyDown() {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        return GameSettings.isKeyDown(keyBindSneak);
    }

    private static int level(int amplifier) {
        return amplifier + 1;
    }
}
