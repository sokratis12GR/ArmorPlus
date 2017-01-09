/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.client.gui.base.GuiBaseBench;
import net.thedragonteam.armorplus.container.ContainerUltiTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityUltiTechBench;

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:37 AM.
 * - TheDragonTeam
 */
public class GuiUltiTechBench extends GuiBaseBench {
    private static final ResourceLocation AP_ULTI_TECH_BENCH_GUI_TEXTURES = new ResourceLocation("armorplus:textures/gui/container/gui_ulti_tech_bench.png");

    public GuiUltiTechBench(InventoryPlayer playerInv, TileEntityUltiTechBench tile) {
        super(new ContainerUltiTechBench(playerInv, tile), AP_ULTI_TECH_BENCH_GUI_TEXTURES, "ulti_tech_bench", 176, 200);
    }
}
