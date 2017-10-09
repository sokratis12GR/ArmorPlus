package net.thedragonteam.armorplus.container.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ContainerBase extends Container {

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}