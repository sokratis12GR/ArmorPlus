/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.container.ContainerLavaInfuser;
import net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser;
import net.thedragonteam.thedragonlib.util.TextHelper;

public class GuiLavaInfuser extends GuiContainer {

    private static final ResourceLocation AP_LAVA_INFUSER_GUI_TEXTURES = new ResourceLocation("armorplus:textures/gui/container/gui_lava_infuser.png");

    private TileEntityLavaInfuser tile;

    public GuiLavaInfuser(InventoryPlayer playerInv, TileEntityLavaInfuser tile) {
        super(new ContainerLavaInfuser(playerInv, tile));
        this.tile = tile;
        this.xSize = 176;
        this.ySize = 165;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString(TextHelper.localize("container.armorplus.lava_infuser"), 28, 5, 0xffffff);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(AP_LAVA_INFUSER_GUI_TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        int l = this.getInfusionProgressScaled(24);
        this.drawTexturedModalRect(i + 91, j + 34, 176, 44, l + 1, 16);

        if (TileEntityLavaInfuser.isInfusing(this.tile)) {
            int k = this.getInfuseLeftScaled(33);
            this.drawTexturedModalRect(i + 8, j + 21 + 43 - k, 176, 43 - k, 16, k + 1);
        }
    }

    private int getInfusionProgressScaled(int pixels) {
        int i = this.tile.getField(2);
        int j = this.tile.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getInfuseLeftScaled(int pixels) {
        int i = this.tile.getField(1);

        if (i == 0) {
            i = 200;
        }

        return this.tile.getField(0) * pixels / i;
    }

}
