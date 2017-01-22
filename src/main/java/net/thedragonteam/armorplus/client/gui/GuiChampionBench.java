/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.container.ContainerChampionBench;
import net.thedragonteam.armorplus.tileentity.TileEntityChampionBench;
import net.thedragonteam.thedragonlib.util.TextHelper;

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:37 AM.
 * - TheDragonTeam
 */
public class GuiChampionBench extends GuiContainer {

    private static final ResourceLocation AP_CHAMPION_BENCH_GUI_TEXTURES = new ResourceLocation("armorplus:textures/gui/container/gui_champion_bench.png");

    public GuiChampionBench(InventoryPlayer playerInv, TileEntityChampionBench tile) {
        super(new ContainerChampionBench(playerInv, tile));
        this.xSize = 256;
        this.ySize = 256;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString(TextHelper.localize("container.armorplus.champion_bench"), 28, 5, 4210752);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(AP_CHAMPION_BENCH_GUI_TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}
