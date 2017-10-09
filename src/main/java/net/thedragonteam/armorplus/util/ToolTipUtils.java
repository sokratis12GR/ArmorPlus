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

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static net.minecraft.util.text.TextFormatting.GRAY;
import static net.thedragonteam.armorplus.util.TextUtils.formattedText;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
@SideOnly(Side.CLIENT)
public final class ToolTipUtils {

    public static void showInfo(List<String> tooltip, KeyBinding keyBinding, TextFormatting formatting) {
        tooltip.add(GRAY + formattedText("tooltip.showinfo.keybind", formatting, keyBinding.getDisplayName()) + GRAY);
    }

    public static void addToolTipFull(List<String> tooltip, String ability, int amplifier) {
        addToolTipFull(tooltip, ability + " " + level(amplifier));
    }

    public static void addToolTipFull(List<String> tooltip, String ability) {
        addToolTip(tooltip,
                format("§9Ability: §r%s", ability),
                "§3Use: §rEquip The Full Set"
        );
    }

    public static void addToolTipPiece(List<String> tooltip, String ability, int amplifier) {
        addToolTipPiece(tooltip, ability + " " + level(amplifier));
    }

    public static void addToolTipPiece(List<String> tooltip, String ability) {
        addToolTip(tooltip,
                format("§9Ability: §r%s", ability),
                "§3Use: §rEquip A Piece"
        );
    }

    public static boolean isKeyDown() {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        return GameSettings.isKeyDown(keyBindSneak);
    }

    private static int level(int amplifier) {
        return amplifier + 1;
    }

    private static void addToolTip(List<String> tooltip, String... lines) {
        tooltip.addAll(Arrays.asList(lines));
    }
}
