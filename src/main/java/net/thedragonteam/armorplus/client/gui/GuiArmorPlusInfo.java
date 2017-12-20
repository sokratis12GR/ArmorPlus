package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.thedragonteam.armorplus.APConfig;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

import static net.thedragonteam.armorplus.util.TextUtils.formattedText;
import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class GuiArmorPlusInfo extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int guiWidth = 256;
        int guiX = (width - guiWidth) / 2;
        int guiHeight = 256;
        int guiY = (height - guiHeight) / 2;
        GL11.glColor4f(1f, 1f, 1f, 1f);
        mc.renderEngine.bindTexture(setRL("textures/gui/gui_armorplus.png"));
        drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);

        fontRenderer.drawString(formattedText("gui.armorplus.info.text_line_heading"), guiX + 25, guiY + 20, 0x000000);
        fontRenderer.drawString(formattedText("gui.armorplus.info.text_line_one"), guiX + 25, guiY + 40, 0x000000);
        fontRenderer.drawString(formattedText("gui.armorplus.info.text_line_two"), guiX + 25, guiY + 50, 0x000000);
        fontRenderer.drawString(formattedText("gui.armorplus.info.text_line_three"), guiX + 25, guiY + 60, 0x000000);
        fontRenderer.drawString(formattedText("gui.armorplus.info.text_line_four"), guiX + 25, guiY + 70, 0x000000);
        fontRenderer.drawString(formattedText("gui.armorplus.info.text_line_five"), guiX + 25, guiY + 80, 0x000000);
        fontRenderer.drawString(formattedText("gui.armorplus.info.text_line_six", APConfig.gameMode), guiX + 25, guiY + 100, 0x000000);
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