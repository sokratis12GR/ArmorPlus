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

import java.util.List;

import static net.thedragonteam.armorplus.util.TextUtils.formattedText;

public class ArrowUtils {

    @SideOnly(value = Side.CLIENT)
    public static void addArrowInformation(List<String> tooltip, String effect, double damage, TextFormatting formatting) {
        KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add(formattedText("§9Ability: §r%s", effect));
            tooltip.add(formattedText("§3Base Arrow Damage: §r%s", damage));
        } else {
            tooltip.add(formattedText(TextFormatting.GRAY, "tooltip.showinfo.beginning", formattedText(formatting, "tooltip.showinfo.keybind", keyBindSneak.getDisplayName(),
                    formattedText(TextFormatting.GRAY, "tooltip.showinfo.end"))));
        }
    }
}