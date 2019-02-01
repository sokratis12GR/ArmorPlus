/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.gui;

import com.sofodev.armorplus.client.gui.base.GuiBaseBench;
import com.sofodev.armorplus.container.ContainerHighTechBench;
import com.sofodev.armorplus.tileentity.TileHTB;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * @author Sokratis Fotkatzikis
 */
public class GuiHighTechBench extends GuiBaseBench {

    public GuiHighTechBench(InventoryPlayer playerInv, TileHTB tile) {
        super(new ContainerHighTechBench(playerInv, tile), "high_tech_bench", 176, 199, 0x000000);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString(new TextComponentTranslation("container.armorplus.high_tech_bench").getFormattedText(), 28, 5, 0x000000);
        this.fontRenderer.drawString(new TextComponentTranslation("container.armorplus.inventory").getFormattedText(), 10, this.ySize - 96 + 4, 0x000000);
    }

}
