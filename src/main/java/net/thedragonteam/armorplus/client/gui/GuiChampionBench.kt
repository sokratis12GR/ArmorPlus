/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui

import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.ResourceLocation
import net.thedragonteam.armorplus.client.gui.base.GuiBaseBench
import net.thedragonteam.armorplus.container.ContainerChampionBench
import net.thedragonteam.armorplus.tileentity.TileEntityChampionBench
import net.thedragonteam.armorplus.util.TextUtils

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:37 AM.
 * - TheDragonTeam
 */
class GuiChampionBench(playerInv: InventoryPlayer, tile: TileEntityChampionBench) : GuiBaseBench(ContainerChampionBench(playerInv, tile), GuiChampionBench.AP_CHAMPION_BENCH_GUI_TEXTURES, "champion_bench", 256, 256) {
    companion object {
        private val AP_CHAMPION_BENCH_GUI_TEXTURES = ResourceLocation("armorplus:textures/gui/container/gui_champion_bench.png")
    }

    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        this.fontRenderer.drawString(TextUtils.formattedText("container.armorplus.champion_bench"), 28, 5, 4210752)
    }
}