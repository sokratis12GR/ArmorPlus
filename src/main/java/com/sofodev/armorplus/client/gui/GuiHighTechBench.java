/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.gui;

import com.sofodev.armorplus.client.gui.base.GuiBaseBench;
import com.sofodev.armorplus.container.ContainerHighTechBench;
import com.sofodev.armorplus.tileentity.TileHTB;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author Sokratis Fotkatzikis
 */
public class GuiHighTechBench extends GuiBaseBench {

    public GuiHighTechBench(InventoryPlayer playerInv, TileHTB tile) {
        super(new ContainerHighTechBench(playerInv, tile), "high_tech_bench", 176, 199, 0x000000);
    }
}
