/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.gui;

import com.sofodev.armorplus.client.gui.base.GuiBaseBench;
import com.sofodev.armorplus.common.container.ContainerChampionBench;
import com.sofodev.armorplus.common.tileentity.TileCB;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * @author Sokratis Fotkatzikis
 */
public class GuiChampionBench extends GuiBaseBench {

    public GuiChampionBench(InventoryPlayer playerInv, TileCB tile) {
        super(new ContainerChampionBench(playerInv, tile), "champion_bench", 256, 256, 0x000000);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString(new TextComponentTranslation("container.armorplus.champion_bench").getFormattedText(), 28, 5, 0x000000);
        this.fontRenderer.drawString(new TextComponentTranslation("container.armorplus.inventory").getFormattedText(), 8, this.ySize - 78 + 2, 0x000000);
    }
}
