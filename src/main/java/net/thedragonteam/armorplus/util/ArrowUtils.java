/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ArrowUtils {

    @SideOnly(value = Side.CLIENT)
    public static void addArrowInformation(List<String> tooltip, String effect, double damage, TextFormatting formatting) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add(String.format("§9Ability: §r%s", effect));
            tooltip.add(String.format("§3Base Arrow Damage: §r%s", damage));
        } else
            tooltip.add(I18n.format("tooltip.shift.showinfo", formatting, keyBindSneak.getDisplayName(), TextFormatting.GRAY));
    }
}
