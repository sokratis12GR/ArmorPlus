/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.gui;

import com.sofodev.armorplus.container.ContainerLavaInfuser;
import com.sofodev.armorplus.tileentity.TileLavaInfuser;
import com.sofodev.armorplus.util.Utils;
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
        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(AP_LAVA_INFUSER_GUI_TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        int l = this.getInfusionProgressScaled(24);
        this.drawTexturedModalRect(i + 91, j + 34, 176, 44, l + 1, 16);

        if (TileLavaInfuser.isInfusing(this.tile)) {
            int k = this.getInfuseLeftScaled(33);
            this.drawTexturedModalRect(i + 8, j + 21 + 43 - k, 176, 43 - k, 16, k + 1);
        }
    }

    private int getInfusionProgressScaled(int pixels) {
        int i = this.tile.getField(2);
        int j = this.tile.getField(3);
        return (j != 0 && i != 0) ? i * pixels / j : 0;
    }

    private int getInfuseLeftScaled(int pixels) {
        int i = this.tile.getField(1);

        if (i == 0) {
            i = 200;
        }

        return this.tile.getField(0) * pixels / i;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

}
