/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui.base;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.thedragonlib.util.TextHelper;

public class GuiBaseBench extends GuiContainer {

    public ResourceLocation resourceLocation;
    public String name;

    public GuiBaseBench(Container container, ResourceLocation resourceLocation, String name, int xSize, int ySize) {
        super(container);
        this.resourceLocation = resourceLocation;
        this.name = name;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString(TextHelper.localize("container.armorplus." + name), 28, 5, 4210752);
        this.fontRendererObj.drawString(TextHelper.localize("container.armorplus.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(resourceLocation);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}
