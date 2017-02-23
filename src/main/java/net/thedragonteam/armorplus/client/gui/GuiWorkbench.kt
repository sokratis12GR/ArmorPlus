/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui

import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.ResourceLocation
import net.thedragonteam.armorplus.client.gui.base.GuiBaseBench
import net.thedragonteam.armorplus.container.ContainerWorkbench
import net.thedragonteam.armorplus.tileentity.TileEntityWorkbench

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:37 AM.
 * - TheDragonTeam
 */
class GuiWorkbench(playerInv: InventoryPlayer, tile: TileEntityWorkbench) : GuiBaseBench(ContainerWorkbench(playerInv, tile), GuiWorkbench.AP_WORKBENCH_GUI_TEXTURES, "workbench", 176, 165) {
    companion object {
        private val AP_WORKBENCH_GUI_TEXTURES = ResourceLocation("armorplus:textures/gui/container/gui_workbench.png")
    }
}
