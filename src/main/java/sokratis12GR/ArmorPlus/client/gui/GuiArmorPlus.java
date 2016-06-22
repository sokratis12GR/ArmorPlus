package sokratis12GR.ArmorPlus.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import sokratis12GR.ArmorPlus.ArmorPlus;

import java.io.IOException;

/**
 * sokratis12GR.ArmorPlus.client.gui
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
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "ArmorPlus Book (WIP)").getText(), guiX + 10, guiY + 5, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Armor Effects").getText(), guiX + 10, guiY + 15, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Coal Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Night Vision").getText(), guiX + 10, guiY + 30, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Lapis Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Water Breathing").getText(), guiX + 10, guiY + 40, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Redstone Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Speed 2").getText(), guiX + 10, guiY + 50, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Emerald Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Haste 2").getText(), guiX + 10, guiY + 60, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Obsidian Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Resistance").getText(), guiX + 10, guiY + 70, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Lava Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Resistance & Fire Resistance").getText(), guiX + 11, guiY + 80, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Guardian Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Water Breathing (Full Set)").getText(), guiX + 11, guiY + 90, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Super Star Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Regeneration 2").getText(), guiX + 10, guiY + 100, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Ender Dragon Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Flight (Full Set)").getText(), guiX + 10, guiY + 110, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "The Ulitmate Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Water Breathing,").getText(), guiX + 10, guiY + 120, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.ITALIC + "Night Vision, Regeneration 2 & Flight (Full Set)").getText(), guiX + 10, guiY + 130, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Ardite Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Fire Resistance (Full Set)").getText(), guiX + 10, guiY + 140, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Cobalt Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Haste 2 (Full Set)").getText(), guiX + 10, guiY + 150, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Manyullyn Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Strength 2 (Full Set)").getText(), guiX + 10, guiY + 160, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Pig Iron Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Saturation (Full Set)").getText(), guiX + 10, guiY + 170, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Knight Slime Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Haste 3 (Full Set)").getText(), guiX + 10, guiY + 180, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Chicken Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Speed 5 (Full Set)").getText(), guiX + 10, guiY + 190, 0xffaa00);
        drawString(fontRendererObj, new TextComponentString(TextFormatting.BOLD + "Slime Armor" + TextFormatting.RESET + TextFormatting.ITALIC + " - Jump Boost 3 (Full Set)").getText(), guiX + 10, guiY + 200, 0xffaa00);
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