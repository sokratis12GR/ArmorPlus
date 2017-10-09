/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui

import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.ResourceLocation
import net.thedragonteam.armorplus.container.ContainerLavaInfuser
import net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser
import net.thedragonteam.armorplus.util.TextUtils

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
class GuiLavaInfuser(playerInv: InventoryPlayer, private val tile: TileEntityLavaInfuser) : GuiContainer(ContainerLavaInfuser(playerInv, tile)) {

    init {
        this.xSize = 176
        this.ySize = 165
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        this.fontRenderer.drawString(TextUtils.formattedText("container.armorplus.lava_infuser"), 28, 5, 0xffffff)
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
        this.mc.textureManager.bindTexture(AP_LAVA_INFUSER_GUI_TEXTURES)
        val i = (this.width - this.xSize) / 2
        val j = (this.height - this.ySize) / 2
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize)

        val l = this.getInfusionProgressScaled(24)
        this.drawTexturedModalRect(i + 91, j + 34, 176, 44, l + 1, 16)

        if (TileEntityLavaInfuser.isInfusing(this.tile)) {
            val k = this.getInfuseLeftScaled(33)
            this.drawTexturedModalRect(i + 8, j + 21 + 43 - k, 176, 43 - k, 16, k + 1)
        }
    }

    private fun getInfusionProgressScaled(pixels: Int): Int {
        val i = this.tile.getField(2)
        val j = this.tile.getField(3)
        return if (j != 0 && i != 0) i * pixels / j else 0
    }

    private fun getInfuseLeftScaled(pixels: Int): Int {
        var i = this.tile.getField(1)

        if (i == 0) {
            i = 200
        }

        return this.tile.getField(0) * pixels / i
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        this.drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        this.renderHoveredToolTip(mouseX, mouseY)
    }

    companion object {

        private val AP_LAVA_INFUSER_GUI_TEXTURES = ResourceLocation("armorplus:textures/gui/container/gui_lava_infuser.png")
    }

}
