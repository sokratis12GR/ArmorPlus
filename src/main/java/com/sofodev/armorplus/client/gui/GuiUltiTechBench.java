/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.gui;

import com.sofodev.armorplus.client.gui.base.GuiBaseBench;
import com.sofodev.armorplus.container.ContainerUltiTechBench;
import com.sofodev.armorplus.tileentity.TileUTB;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author Sokratis Fotkatzikis
 */
public class GuiUltiTechBench extends GuiBaseBench {

    public GuiUltiTechBench(InventoryPlayer playerInv, TileUTB tile) {
        super(new ContainerUltiTechBench(playerInv, tile), "ulti_tech_bench", 192, 237, 0x000000);
    }
}
