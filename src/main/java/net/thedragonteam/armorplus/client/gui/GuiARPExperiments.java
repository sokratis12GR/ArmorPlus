/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.thedragonteam.armorplus.ArmorPlus;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.List;

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/13/2516 6:42 PM.
 * - TheDragonTeam
 */
public class GuiARPExperiments extends GuiScreen implements GuiListExtended.IGuiListEntry {

    int guiWidth = 256;
    int guiHeight = 256;

    int guiX = (width - guiWidth) / 2;
    int guiY = (height - guiHeight) / 2;


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
        super.drawScreen(x, y, ticks);
    }

    @Override
    public void initGui() {
        buttonList.clear();
        super.initGui();
    }

    @Override
    protected void drawHoveringText(List<String> textLines, int x, int y, FontRenderer font) {
        super.drawHoveringText(textLines, x, y, font);
    }

    @Override
    protected boolean handleComponentClick(ITextComponent component) {
        return super.handleComponentClick(component);
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

    @Override
    protected void handleComponentHover(ITextComponent component, int x, int y) {
        super.handleComponentHover(component, x, y);
    }

    @Override
    public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {

    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
    }

    @Override
    public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
        return false;
    }

    @Override
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {

    }
}