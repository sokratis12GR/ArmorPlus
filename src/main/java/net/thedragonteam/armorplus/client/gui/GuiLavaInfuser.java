/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.container.ContainerLavaInfuser;
import net.thedragonteam.armorplus.tileentity.base.TileEntityLavaInfuser;
import net.thedragonteam.thedragonlib.util.TextHelper;

import java.awt.*;

public class GuiLavaInfuser extends GuiContainer {

    private static final ResourceLocation AP_LAVA_INFUSER_GUI_TEXTURES = new ResourceLocation("armorplus:textures/gui/container/gui_lava_infuser.png");

    public GuiLavaInfuser(InventoryPlayer playerInv, TileEntityLavaInfuser tile) {
        super(new ContainerLavaInfuser(playerInv, tile));
        this.xSize = 256;
        this.ySize = 256;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        this.fontRendererObj.drawString(TextHelper.localize("container.armorplus.lava_infuser"), 28, 5, Color.lightGray.getRGB());
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(AP_LAVA_INFUSER_GUI_TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}
