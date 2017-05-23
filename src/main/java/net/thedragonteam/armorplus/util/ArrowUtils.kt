/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util

import net.minecraft.client.Minecraft
import net.minecraft.client.settings.GameSettings
import net.minecraft.util.text.TextFormatting
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.thedragonlib.util.TextUtils

object ArrowUtils {

    @SideOnly(value = Side.CLIENT)
    fun addArrowInformation(tooltip: MutableList<String>, effect: String, damage: Double, formatting: TextFormatting) {
        val keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak
        when {
            GameSettings.isKeyDown(keyBindSneak) -> {
                tooltip.add(TextUtils.formattedText("§9Ability: §r%s", effect))
                tooltip.add(TextUtils.formattedText("§3Base Arrow Damage: §r%s", damage))
            }
            else -> {
                tooltip.add(TextUtils.formattedText(TextFormatting.GRAY, "tooltip.showinfo.beginning", TextUtils.formattedText(formatting, "tooltip.showinfo.keybind", keyBindSneak.displayName,
                        TextUtils.formattedText(TextFormatting.GRAY, "tooltip.showinfo.end"))))
            }
        }
    }
}
