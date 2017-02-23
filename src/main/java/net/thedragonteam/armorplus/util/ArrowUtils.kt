/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util

import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraft.client.settings.GameSettings
import net.minecraft.util.text.TextFormatting
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object ArrowUtils {

    @SideOnly(value = Side.CLIENT)
    fun addArrowInformation(tooltip: MutableList<String>, effect: String, damage: Double, formatting: TextFormatting) {
        val keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak
        when {
            GameSettings.isKeyDown(keyBindSneak) -> {
                tooltip.add(String.format("§9Ability: §r%s", effect))
                tooltip.add(String.format("§3Base Arrow Damage: §r%s", damage))
            }
            else -> tooltip.add(I18n.format("tooltip.shift.showinfo", formatting, keyBindSneak.displayName, TextFormatting.GRAY))
        }
    }
}
