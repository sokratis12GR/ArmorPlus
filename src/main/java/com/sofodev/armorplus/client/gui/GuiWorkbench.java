/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.gui;

import com.sofodev.armorplus.client.gui.base.GuiBaseBench;
import com.sofodev.armorplus.common.container.ContainerWorkbench;
import com.sofodev.armorplus.common.tileentity.TileWB;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author Sokratis Fotkatzikis
 */
public class GuiWorkbench extends GuiBaseBench {

    public GuiWorkbench(InventoryPlayer playerInv, TileWB tile) {
        super(new ContainerWorkbench(playerInv, tile), "workbench", 176, 165, 0x000000);
    }
}
