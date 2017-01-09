/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.client.gui.base.GuiBaseBench;
import net.thedragonteam.armorplus.container.ContainerWorkbench;
import net.thedragonteam.armorplus.tileentity.TileEntityWorkbench;

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:37 AM.
 * - TheDragonTeam
 */
public class GuiWorkbench extends GuiBaseBench {
    private static final ResourceLocation AP_WORKBENCH_GUI_TEXTURES = new ResourceLocation("armorplus:textures/gui/container/gui_workbench.png");

    public GuiWorkbench(InventoryPlayer playerInv, TileEntityWorkbench tile) {
        super(new ContainerWorkbench(playerInv, tile), AP_WORKBENCH_GUI_TEXTURES, "workbench", 176, 165);
    }
}
