/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.gui;

import com.sofodev.armorplus.common.config.ModConfig;
import com.sofodev.armorplus.common.util.TextUtils;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

import static com.sofodev.armorplus.common.util.TextUtils.translatedText;

/**
 * @author Sokratis Fotkatzikis
 */
public class GuiArmorPlusLore extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int guiWidth = 256;
        int guiX = (width - guiWidth) / 2;
        int guiHeight = 256;
        int guiY = (height - guiHeight) / 2;
        GL11.glColor4f(1f, 1f, 1f, 1f);
        mc.renderEngine.bindTexture(Utils.setRL("textures/gui/gui_armorplus_lore.png"));
        drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);

        fontRenderer.drawString("Welcome to the world of mystery and adventure", guiX + 25, guiY + 20, 0x000000);
        fontRenderer.drawString("Here you can find some of the upcoming changes for the mod", guiX + 25, guiY + 40, 0x000000);
        fontRenderer.drawString("Boss battles", guiX + 25, guiY + 50, 0x000000);
        fontRenderer.drawString(translatedText("gui.armorplus.info.text_line_three"), guiX + 25, guiY + 60, 0x000000);
        fontRenderer.drawString(translatedText("gui.armorplus.info.text_line_four"), guiX + 25, guiY + 70, 0x000000);
        fontRenderer.drawString(translatedText("gui.armorplus.info.text_line_five"), guiX + 25, guiY + 80, 0x000000);
        fontRenderer.drawString(translatedText("gui.armorplus.info.text_line_six", ModConfig.MainConfig.global.gameMode), guiX + 25, guiY + 100, 0x000000);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_E) {
            mc.displayGuiScreen(null);
        }
        super.keyTyped(typedChar, keyCode);
    }

}