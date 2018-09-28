package net.thedragonteam.armorplus.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.thedragonteam.armorplus.client.gui.base.GuiBaseBench;
import net.thedragonteam.armorplus.container.ContainerWorkbench;
import net.thedragonteam.armorplus.tileentity.TileWB;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class GuiWorkbench extends GuiBaseBench {

    public GuiWorkbench(InventoryPlayer playerInv, TileWB tile) {
        super(new ContainerWorkbench(playerInv, tile), "workbench", 176, 165, 0x000000);
    }
}
