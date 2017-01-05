/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.client.gui.base.GuiBaseBench;
import net.thedragonteam.armorplus.container.ContainerWorkbenchNew;

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:37 AM.
 * - TheDragonTeam
 */
public class GuiWorkbenchNew extends GuiBaseBench {
    private static final ResourceLocation AP_WORKBENCH_NEW_GUI_TEXTURES = new ResourceLocation("armorplus:textures/gui/container/gui_workbench-new.png");

    public GuiWorkbenchNew(InventoryPlayer playerInv, World worldIn) {
        super(new ContainerWorkbenchNew(playerInv, worldIn), AP_WORKBENCH_NEW_GUI_TEXTURES, "workbench-new", 176, 256);
    }
}
