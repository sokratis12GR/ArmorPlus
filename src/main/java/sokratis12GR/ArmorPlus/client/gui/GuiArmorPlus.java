package sokratis12GR.ArmorPlus.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import sokratis12GR.ArmorPlus.ArmorPlus;

import java.io.IOException;

/**
 * sokratis12GR.ArmorPlus.client.gui
 * ArmorPlus created by sokratis12GR on 6/13/2016 6:42 PM.
 */
public class GuiArmorPlus extends GuiScreen {

    int guiWidth = 250;
    int guiHeight = 150;

    int guiX = (width - guiWidth) / 2;
    int guiY = (height - guiHeight) / 2;

    GuiButton buttonLink1;

    @Override
    public void drawScreen(int x, int y, float ticks) {
        int guiX = (width - guiWidth) / 2;
        int guiY = (height - guiHeight) / 2;
        GL11.glColor4f(1, 1, 1, 1);
        drawDefaultBackground();
        mc.renderEngine.bindTexture(new ResourceLocation(ArmorPlus.MODID, "textures/gui/GuiArmorPlus.png"));
        drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);
        fontRendererObj.drawStringWithShadow("ArmorPlus Book (WIP)", guiX + 10, guiY + 10, 0xffaa00);
        fontRendererObj.drawStringWithShadow("This is still Work In Progress", guiX + 10, guiY + 30, 0xffaa00);
        super.drawScreen(x, y, ticks);
    }

    @Override
    public void initGui() {
        buttonList.clear();
        buttonList.add(buttonLink1 = new GuiButton(1, guiX + 10, guiY + 50, 30, 20, "Recipes"));

        super.initGui();
    }

    @Override
    protected void keyTyped(char c, int key) throws IOException {
        switch (key) {
            case Keyboard.KEY_E:
                mc.displayGuiScreen(null);
        }
        super.keyTyped(c, key);
    }
}