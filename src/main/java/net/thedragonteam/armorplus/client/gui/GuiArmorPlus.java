/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.thedragonteam.armorplus.ArmorPlus;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

/**
 * sokratis12gr.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/13/2516 6:42 PM.
 */
public class GuiArmorPlus extends GuiScreen {

    private int guiWidth = 256;
    private int guiHeight = 256;

    @Override
    public void drawScreen(int x, int y, float ticks) {
        int guiX = (width - guiWidth) / 2;
        int guiY = (height - guiHeight) / 2;
        GL11.glColor4f(1, 1, 1, 1);
        drawDefaultBackground();
        mc.renderEngine.bindTexture(new ResourceLocation(ArmorPlus.MODID, "textures/gui/gui_armorplus.png"));
        drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);

        fontRendererObj.drawString(new TextComponentString("ArmorPlus Book").getFormattedText(), guiX + 25, guiY + 20, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Armor Effects").getFormattedText(), guiX + 25, guiY + 30, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Coal Armor" + " - Night Vision").getFormattedText(), guiX + 25, guiY + 40, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Lapis Armor" + " - Water Breathing").getFormattedText(), guiX + 25, guiY + 50, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Redstone Armor" + " - Speed II").getFormattedText(), guiX + 25, guiY + 60, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Emerald Armor" + " - Haste II").getFormattedText(), guiX + 25, guiY + 70, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Obsidian Armor" + " - Resistance").getFormattedText(), guiX + 25, guiY + 80, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Lava Armor" + " - Fire Resistance").getFormattedText(), guiX + 25, guiY + 90, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Guardian Armor" + " - Water Breathing").getFormattedText(), guiX + 25, guiY + 100, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Super Star Armor" + " - Regeneration 2").getFormattedText(), guiX + 25, guiY + 110, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Ender Dragon Armor" + " - Flight").getFormattedText(), guiX + 25, guiY + 120, 0x000000);
        fontRendererObj.drawString(new TextComponentString("The Ulitmate Armor" + " - Water Breathing,").getFormattedText(), guiX + 25, guiY + 130, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Night Vision, Regeneration II & Flight").getFormattedText(), guiX + 25, guiY + 140, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Ardite Armor" + " - Fire Resistance").getFormattedText(), guiX + 25, guiY + 150, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Cobalt Armor" + " - Haste III").getFormattedText(), guiX + 25, guiY + 160, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Manyullyn Armor" + " - Strength II").getFormattedText(), guiX + 25, guiY + 170, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Pig Iron Armor" + " - Saturation").getFormattedText(), guiX + 25, guiY + 180, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Knight Slime Armor" + " - Haste II").getFormattedText(), guiX + 25, guiY + 190, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Chicken Armor" + " - Speed V").getFormattedText(), guiX + 25, guiY + 200, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Slime Armor" + " - Jump Boost III").getFormattedText(), guiX + 25, guiY + 210, 0x000000);
        super.drawScreen(x, y, ticks);
    }

    @Override
    public void initGui() {
        buttonList.clear();
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

    @Override
    protected void setText(String newChatText, boolean shouldOverwrite) {
        super.setText(newChatText, shouldOverwrite);
    }
}