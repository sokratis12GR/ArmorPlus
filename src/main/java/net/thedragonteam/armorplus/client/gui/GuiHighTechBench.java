package net.thedragonteam.armorplus.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.thedragonteam.armorplus.client.gui.base.GuiBaseBench;
import net.thedragonteam.armorplus.container.ContainerHighTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityHighTechBench;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class GuiHighTechBench extends GuiBaseBench {

    public GuiHighTechBench(InventoryPlayer playerInv, TileEntityHighTechBench tile) {
        super(new ContainerHighTechBench(playerInv, tile), "high_tech_bench", 176, 199);
    }
}
