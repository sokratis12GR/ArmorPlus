package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.thedragonteam.armorplus.ArmorPlus;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

/**
 * sokratis12gr.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/13/2016 6:42 PM.
 */
public class GuiArmorPlus extends GuiScreen {

    int guiWidth = 265;
    int guiHeight = 250;

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

        fontRendererObj.drawString(new TextComponentString("ArmorPlus Book (WIP)").getFormattedText(), guiX + 10, guiY + 5, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Armor Effects").getFormattedText(), guiX + 10, guiY + 15, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Coal Armor" + " - Night Vision").getFormattedText(), guiX + 10, guiY + 30, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Lapis Armor" + " - Water Breathing").getFormattedText(), guiX + 10, guiY + 40, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Redstone Armor" + " - Speed 2").getFormattedText(), guiX + 10, guiY + 50, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Emerald Armor" + " - Haste 2").getFormattedText(), guiX + 10, guiY + 60, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Obsidian Armor" + " - Resistance").getFormattedText(), guiX + 10, guiY + 70, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Lava Armor" + " - Resistance & Fire Resistance").getFormattedText(), guiX + 11, guiY + 80, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Guardian Armor" + " - Water Breathing (Full Set)").getFormattedText(), guiX + 11, guiY + 90, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Super Star Armor" + " - Regeneration 2").getFormattedText(), guiX + 10, guiY + 100, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Ender Dragon Armor" + " - Flight (Full Set)").getFormattedText(), guiX + 10, guiY + 110, 0x000000);
        fontRendererObj.drawString(new TextComponentString("The Ulitmate Armor" + " - Water Breathing,").getFormattedText(), guiX + 10, guiY + 120, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Night Vision, Regeneration 2 & Flight (Full Set)").getFormattedText(), guiX + 10, guiY + 130, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Ardite Armor" + " - Fire Resistance (Full Set)").getFormattedText(), guiX + 10, guiY + 140, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Cobalt Armor" + " - Haste 3 (Full Set)").getFormattedText(), guiX + 10, guiY + 150, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Manyullyn Armor" + " - Strength 2 (Full Set)").getFormattedText(), guiX + 10, guiY + 160, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Pig Iron Armor" + " - Saturation (Full Set)").getFormattedText(), guiX + 10, guiY + 170, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Knight Slime Armor" + " - Haste 2 (Full Set)").getFormattedText(), guiX + 10, guiY + 180, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Chicken Armor" + " - Speed 5 (Full Set)").getFormattedText(), guiX + 10, guiY + 190, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Slime Armor" + " - Jump Boost 3 (Full Set)").getFormattedText(), guiX + 10, guiY + 200, 0x000000);
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

    @Override
    protected void setText(String newChatText, boolean shouldOverwrite) {
        super.setText(newChatText, shouldOverwrite);
    }
}