/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.client.gui.base.GuiBaseBench;
import net.thedragonteam.armorplus.container.ContainerChampionBench;
import net.thedragonteam.armorplus.tileentity.TileEntityChampionBench;

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:37 AM.
 * - TheDragonTeam
 */
public class GuiChampionBench extends GuiBaseBench {
    private static final ResourceLocation AP_CHAMPION_BENCH_GUI_TEXTURES = new ResourceLocation("armorplus:textures/gui/container/gui_champion_bench.png");

    public GuiChampionBench(InventoryPlayer playerInv, TileEntityChampionBench tile) {
        super(new ContainerChampionBench(playerInv, tile), AP_CHAMPION_BENCH_GUI_TEXTURES, "champion_bench", 256, 256);
    }
}
