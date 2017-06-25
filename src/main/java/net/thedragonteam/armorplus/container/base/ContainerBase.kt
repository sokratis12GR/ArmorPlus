/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.container.base

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container

open class ContainerBase : Container() {

    override fun canInteractWith(player: EntityPlayer): Boolean {
        return true
    }
}