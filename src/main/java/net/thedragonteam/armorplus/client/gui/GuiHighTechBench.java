/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.client.gui.base.GuiBaseBench;
import net.thedragonteam.armorplus.container.ContainerHighTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityHighTechBench;

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:37 AM.
 * - TheDragonTeam
 */
public class GuiHighTechBench extends GuiBaseBench {
    private static final ResourceLocation AP_HIGH_TECH_BENCH_GUI_TEXTURES = new ResourceLocation("armorplus:textures/gui/container/gui_high_tech_bench.png");

    public GuiHighTechBench(InventoryPlayer playerInv, TileEntityHighTechBench tile) {
        super(new ContainerHighTechBench(playerInv, tile), AP_HIGH_TECH_BENCH_GUI_TEXTURES, "high_tech_bench", 176, 184);
    }
}
