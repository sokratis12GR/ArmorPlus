/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.thedragonteam.armorplus.ArmorPlus;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

import static net.minecraft.potion.Potion.getPotionFromResourceLocation;
import static net.thedragonteam.armorplus.ARPConfig.*;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

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
        fontRendererObj.drawString(new TextComponentString("Coal Armor" + " - " + localize(getPotionFromResourceLocation(coalArmorAddPotionEffect).getName() + ".name").trim()).getFormattedText(), guiX + 25, guiY + 40, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Lapis Armor" + " - " + localize(getPotionFromResourceLocation(lapisArmorAddPotionEffect).getName() + ".name").trim()).getFormattedText(), guiX + 25, guiY + 50, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Redstone Armor" + " - " + localize(getPotionFromResourceLocation(redstoneArmorAddPotionEffect).getName() + ".name").trim() + " " + (redstoneArmorEffectlevel + 1)).getFormattedText(), guiX + 25, guiY + 60, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Emerald Armor" + " - " + localize(getPotionFromResourceLocation(emeraldArmorAddPotionEffect).getName() + ".name").trim() + " " + (emeraldArmorEffectlevel + 1)).getFormattedText(), guiX + 25, guiY + 70, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Obsidian Armor" + " - " + localize(getPotionFromResourceLocation(obsidianArmorAddPotionEffect).getName() + ".name").trim()).getFormattedText(), guiX + 25, guiY + 80, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Lava Armor" + " - " + localize(getPotionFromResourceLocation(lavaArmorAddPotionEffect).getName() + ".name").trim()).getFormattedText(), guiX + 25, guiY + 90, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Guardian Armor" + " - " + localize(getPotionFromResourceLocation(guardianArmorAddPotionEffect).getName() + ".name").trim()).getFormattedText(), guiX + 25, guiY + 100, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Super Star Armor" + " - " + localize(getPotionFromResourceLocation(superStarArmorAddPotionEffect).getName() + ".name").trim() + " " + (superstarArmorEffectlevel + 1)).getFormattedText(), guiX + 25, guiY + 110, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Ender Dragon Armor" + " - Flight").getFormattedText(), guiX + 25, guiY + 120, 0x000000);
        fontRendererObj.drawString(new TextComponentString("The Ultimate Armor" + " - " + localize(getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[0]).getName() + ".name").trim() + ",").getFormattedText(), guiX + 25, guiY + 130, 0x000000);
        fontRendererObj.drawString(new TextComponentString(localize(getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[1]).getName() + ".name").trim() + "," + localize(getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[2]).getName() + ".name").trim() + " II & Flight").getFormattedText(), guiX + 25, guiY + 140, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Ardite Armor" + " - " + localize(getPotionFromResourceLocation(arditeArmorAddPotionEffect).getName() + ".name").trim()).getFormattedText(), guiX + 25, guiY + 150, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Cobalt Armor" + " - " + localize(getPotionFromResourceLocation(cobaltArmorAddPotionEffect).getName() + ".name").trim() + " III").getFormattedText(), guiX + 25, guiY + 160, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Manyullyn Armor" + " - " + localize(getPotionFromResourceLocation(manyullynArmorAddPotionEffect).getName() + ".name").trim() + " II").getFormattedText(), guiX + 25, guiY + 170, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Pig Iron Armor" + " - " + localize(getPotionFromResourceLocation(pigIronArmorAddPotionEffect).getName() + ".name").trim()).getFormattedText(), guiX + 25, guiY + 180, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Knight Slime Armor" + " - " + localize(getPotionFromResourceLocation(knightSlimeArmorAddPotionEffect).getName() + ".name").trim() + " II").getFormattedText(), guiX + 25, guiY + 190, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Chicken Armor" + " - " + localize(getPotionFromResourceLocation(chickenArmorAddPotionEffect).getName() + ".name").trim() + " V").getFormattedText(), guiX + 25, guiY + 200, 0x000000);
        fontRendererObj.drawString(new TextComponentString("Slime Armor" + " - " + localize(getPotionFromResourceLocation(slimeArmorAddPotionEffect).getName() + ".name").trim() + " III").getFormattedText(), guiX + 25, guiY + 210, 0x000000);
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