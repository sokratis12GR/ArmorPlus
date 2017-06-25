/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler
import net.thedragonteam.armorplus.container.ContainerLavaInfuser
import net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser

class GuiHandler : IGuiHandler {

    override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        val te = world.getTileEntity(BlockPos(x, y, z))
        when (ID) {
            GUI_ARMORPLUS_INFO -> return GuiArmorPlusInfo()
            GUI_LAVA_INFUSER -> if (te != null && te is TileEntityLavaInfuser)
                return ContainerLavaInfuser(player.inventory, (te as TileEntityLavaInfuser?)!!)
            else -> {
            }
        }
        return null
    }

    override fun getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        val te = world.getTileEntity(BlockPos(x, y, z))
        when (ID) {
            GUI_ARMORPLUS_INFO -> return GuiArmorPlusInfo()
            GUI_LAVA_INFUSER -> if (te != null && te is TileEntityLavaInfuser)
                return GuiLavaInfuser(player.inventory, (te as TileEntityLavaInfuser?)!!)
            else -> {
            }
        }
        return null
    }

    companion object {

        val GUI_ARMORPLUS_INFO = 0
        val GUI_LAVA_INFUSER = 9
    }
}