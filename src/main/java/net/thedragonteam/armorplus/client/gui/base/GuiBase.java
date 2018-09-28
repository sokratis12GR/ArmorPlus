package net.thedragonteam.armorplus.client.gui.base;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public abstract class GuiBase extends GuiContainer {

    public GuiBase( Container inventorySlotsIn) {
        super(inventorySlotsIn);
    }
}
