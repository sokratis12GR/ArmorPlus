/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.thedragonteam.armorplus.client.gui.base.GuiBaseBench;
import net.thedragonteam.armorplus.container.ContainerUltiTechBench;
import net.thedragonteam.armorplus.tileentity.TileUTB;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class GuiUltiTechBench extends GuiBaseBench {

    public GuiUltiTechBench(InventoryPlayer playerInv, TileUTB tile) {
        super(new ContainerUltiTechBench(playerInv, tile), "ulti_tech_bench", 192, 237, 0x000000);
    }
}
