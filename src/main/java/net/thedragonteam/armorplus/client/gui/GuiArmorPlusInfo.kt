/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui

import net.minecraft.client.gui.GuiScreen
import net.minecraft.util.ResourceLocation
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.util.TextUtils.formattedText
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11
import java.io.IOException

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/13/2516 6:42 PM.
 * - TheDragonTeam
 */
class GuiArmorPlusInfo : GuiScreen() {

    override fun drawScreen(x: Int, y: Int, ticks: Float) {
        val guiWidth = 256
        val guiX = (width - guiWidth) / 2
        val guiHeight = 256
        val guiY = (height - guiHeight) / 2
        GL11.glColor4f(1f, 1f, 1f, 1f)
        mc.renderEngine.bindTexture(ResourceLocation(ArmorPlus.MODID, "textures/gui/gui_armorplus.png"))
        drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight)

        fontRenderer.drawString(formattedText("gui.armorplus.info.text_line_heading"), guiX + 25, guiY + 20, 0x000000)
        super.drawScreen(x, y, ticks)
    }

    @Throws(IOException::class)
    override fun keyTyped(c: Char, key: Int) {
        if (key == Keyboard.KEY_E) {
            mc.displayGuiScreen(null)
        }
        super.keyTyped(c, key)
    }

    override fun setText(newChatText: String?, shouldOverwrite: Boolean) {
        super.setText(newChatText, shouldOverwrite)
    }
}