/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerBase extends Container {

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}