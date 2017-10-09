/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui.base

import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.inventory.Container
import net.minecraft.util.ResourceLocation
import net.thedragonteam.armorplus.util.TextUtils

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
open class GuiBaseBench(container: Container, var resourceLocation: ResourceLocation, var name: String, xSize: Int, ySize: Int) : GuiContainer(container) {

    init {
        this.xSize = xSize
        this.ySize = ySize
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        this.fontRenderer.drawString(TextUtils.formattedText("container.armorplus." + name), 28, 5, 4210752)
        this.fontRenderer.drawString(TextUtils.formattedText("container.armorplus.inventory"), 8, this.ySize - 96 + 2, 4210752)
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
        this.mc.textureManager.bindTexture(resourceLocation)
        val i = (this.width - this.xSize) / 2
        val j = (this.height - this.ySize) / 2
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize)
    }
}
