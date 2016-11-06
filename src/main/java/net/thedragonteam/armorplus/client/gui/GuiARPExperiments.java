/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.thedragonteam.armorplus.ArmorPlus;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/13/2516 6:42 PM.
 * - TheDragonTeam
 */
public class GuiARPExperiments extends GuiChat {

    int guiWidth = 256;
    int guiHeight = 256;

    @Override
    public void drawScreen(int x, int y, float ticks) {
        int guiX = (width - guiWidth) / 2;
        int guiY = (height - guiHeight) / 2;
        GL11.glColor4f(1, 1, 1, 1);
        drawDefaultBackground();
        mc.renderEngine.bindTexture(new ResourceLocation(ArmorPlus.MODID, "textures/gui/gui_armorplus.png"));
        drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);
        fontRendererObj.drawString(new TextComponentString("ArmorPlus Dev Book").getFormattedText(), guiX + 25, guiY + 20, 0x000000);
        drawHorizontalLine(guiX + 10, guiX + 50, guiY + 40, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Player's Name: " + mc.thePlayer.getName()).getFormattedText(), guiX + 25, guiY + 30, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Player's Health: " + mc.thePlayer.getHealth() + "/" + mc.thePlayer.getMaxHealth()).getFormattedText(), guiX + 25, guiY + 40, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Player's Bed Location: x: " + mc.thePlayer.getBedLocation().getX() + " y: " + mc.thePlayer.getBedLocation().getY() + " z: " + mc.thePlayer.getBedLocation().getZ()).getFormattedText(), guiX + 25, guiY + 50, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Player's Score: " + mc.thePlayer.getScore()).getFormattedText(), guiX + 25, guiY + 60, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Player's Team: " + mc.thePlayer.getTeam()).getFormattedText(), guiX + 25, guiY + 70, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Player's Location: x: " + mc.thePlayer.getPosition().getX() + " y: " + mc.thePlayer.getPosition().getY() + " y: " + mc.thePlayer.getPosition().getZ()).getFormattedText(), guiX + 25, guiY + 80, 0x000000);
        super.drawScreen(x, y, ticks);
    }

    @Override
    public void initGui() {
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