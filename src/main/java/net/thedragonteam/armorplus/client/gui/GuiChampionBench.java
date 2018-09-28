/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.thedragonteam.armorplus.client.gui.base.GuiBaseBench;
import net.thedragonteam.armorplus.container.ContainerChampionBench;
import net.thedragonteam.armorplus.tileentity.TileCB;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
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
