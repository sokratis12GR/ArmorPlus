/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.gui;

import com.sofodev.armorplus.common.container.ContainerLavaInfuser;
import com.sofodev.armorplus.common.tileentity.TileLavaInfuser;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * @author Sokratis Fotkatzikis
 */
public class GuiLavaInfuser extends GuiContainer {

    private ResourceLocation AP_LAVA_INFUSER_GUI_TEXTURES = Utils.setRL("textures/gui/container/gui_lava_infuser.png");

    private TileLavaInfuser tile;

    public GuiLavaInfuser(InventoryPlayer playerInv, TileLavaInfuser tile) {
        super(new ContainerLavaInfuser(playerInv, tile));
        this.tile = tile;
        this.xSize = 176;
        this.ySize = 165;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString(new TextComponentTranslation("container.armorplus.lava_infuser").getFormattedText(), 28, 5, 0xffffff);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(AP_LAVA_INFUSER_GUI_TEXTURES);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

        int arrowSize = this.getInfusionProgressScaled(24);
        this.drawTexturedModalRect(x + 105, y + 35, 176, 44, arrowSize + 1, 16);

        if (TileLavaInfuser.isInfusing(this.tile)) {
            int k = this.getInfuseLeftScaled(33);
            this.drawTexturedModalRect(x + 42, y + 22 + 38 - k, 176, 37 - k, 16, k + 1);
        }
    }

    private int getInfusionProgressScaled(int pixels) {
        int x = this.tile.getField(2);
        int y = this.tile.getField(3);
        return (y != 0 && x != 0) ? x * pixels / y : 0;
    }

    private int getInfuseLeftScaled(int pixels) {
        int i = this.tile.getField(1);

        if (i == 0) {
            i = 200;
        }

        return this.tile.getField(0) * pixels / i;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
