/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler
import net.thedragonteam.armorplus.container.*
import net.thedragonteam.armorplus.tileentity.*

class GuiHandler : IGuiHandler {

    override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        val te = world.getTileEntity(BlockPos(x, y, z))
        when (ID) {
            GUI_ARMORPLUS_INFO -> return GuiArmorPlusInfo()
            GUI_WORKBENCH -> if (te != null && te is TileEntityWorkbench)
                return ContainerWorkbench(player.inventory, (te as TileEntityWorkbench?)!!)
            GUI_HIGH_TECH_BENCH -> if (te != null && te is TileEntityHighTechBench)
                return ContainerHighTechBench(player.inventory, (te as TileEntityHighTechBench?)!!)
            GUI_ULTI_TECH_BENCH -> if (te != null && te is TileEntityUltiTechBench)
                return ContainerUltiTechBench(player.inventory, (te as TileEntityUltiTechBench?)!!)
            GUI_CHAMPION_BENCH -> if (te != null && te is TileEntityChampionBench)
                return ContainerChampionBench(player.inventory, (te as TileEntityChampionBench?)!!)
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
            GUI_WORKBENCH -> if (te != null && te is TileEntityWorkbench)
                return GuiWorkbench(player.inventory, (te as TileEntityWorkbench?)!!)
            GUI_HIGH_TECH_BENCH -> if (te != null && te is TileEntityHighTechBench)
                return GuiHighTechBench(player.inventory, (te as TileEntityHighTechBench?)!!)
            GUI_ULTI_TECH_BENCH -> if (te != null && te is TileEntityUltiTechBench)
                return GuiUltiTechBench(player.inventory, (te as TileEntityUltiTechBench?)!!)
            GUI_CHAMPION_BENCH -> if (te != null && te is TileEntityChampionBench)
                return GuiChampionBench(player.inventory, (te as TileEntityChampionBench?)!!)
            GUI_LAVA_INFUSER -> if (te != null && te is TileEntityLavaInfuser)
                return GuiLavaInfuser(player.inventory, (te as TileEntityLavaInfuser?)!!)
            else -> {
            }
        }
        return null
    }

    companion object {

        val GUI_ARMORPLUS_INFO = 0
        val GUI_WORKBENCH = 2
        val GUI_HIGH_TECH_BENCH = 3
        val GUI_ULTI_TECH_BENCH = 4
        val GUI_CHAMPION_BENCH = 5
        val GUI_LAVA_INFUSER = 9
    }
}