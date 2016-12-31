/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/13/2516 6:42 PM.
 * - TheDragonTeam
 */
public class GuiArmorPlusInfo extends GuiScreen {

    int guiWidth = 256;
    int guiHeight = 256;

    @Override
    public void drawScreen(int x, int y, float ticks) {
        int guiX = (width - guiWidth) / 2;
        int guiY = (height - guiHeight) / 2;
        GL11.glColor4f(1, 1, 1, 1);
        mc.renderEngine.bindTexture(new ResourceLocation(ArmorPlus.MODID, "textures/gui/gui_armorplus.png"));
        drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);

        fontRendererObj.drawString(localize("gui.armorplus.info.text_line_heading"), guiX + 25, guiY + 20, 0x000000);
        fontRendererObj.drawString(localize("gui.armorplus.info.text_line_one"), guiX + 25, guiY + 40, 0x000000);
        fontRendererObj.drawString(localize("gui.armorplus.info.text_line_two"), guiX + 25, guiY + 50, 0x000000);
        fontRendererObj.drawString(localize("gui.armorplus.info.text_line_three"), guiX + 25, guiY + 60, 0x000000);
        fontRendererObj.drawString(localize("gui.armorplus.info.text_line_four"), guiX + 25, guiY + 70, 0x000000);
        fontRendererObj.drawString(localize("gui.armorplus.info.text_line_five"), guiX + 25, guiY + 80, 0x000000);
        fontRendererObj.drawString(localize("gui.armorplus.info.text_line_six", APConfig.gameMode), guiX + 25, guiY + 100, 0x000000);
        super.drawScreen(x, y, ticks);
    }

    @Override
    protected void keyTyped(char c, int key) throws IOException {
        switch (key) {
            case Keyboard.KEY_E:
                mc.displayGuiScreen(null);
        }
        super.keyTyped(c, key);
    }

    @Override
    protected void setText(String newChatText, boolean shouldOverwrite) {
        super.setText(newChatText, shouldOverwrite);
    }
}