/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GuiUtils extends GuiScreen {
    private Minecraft mc = Minecraft.getMinecraft();
    private FontRenderer fontRenderer = mc.fontRendererObj;

    /**
     * Draws a slot that is disabled...
     *
     * @param x          slot x
     * @param y          slot y
     * @param renderItem Item Render
     */
    public static void drawDisabledSlot(int x, int y, RenderItem renderItem) {
        new GuiUtils().zLevel = 100.f;
        renderItem.zLevel = 100.0f;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int colorOverlay = new Color(139, 139, 139, 200).hashCode();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.colorMask(true, true, true, false);
        renderItem.renderItemAndEffectIntoGUI(new ItemStack(Blocks.AIR), x, y);
        new GuiUtils().drawGradientRect(x, y, x + 16, y + 16, colorOverlay, colorOverlay);
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        new GuiUtils().zLevel = 0.0f;
        renderItem.zLevel = 0.0f;
    }
}
