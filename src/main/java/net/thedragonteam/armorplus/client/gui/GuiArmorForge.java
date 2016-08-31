/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.container.ContainerArmorForge;
import net.thedragonteam.armorplus.tileentity.TileEntityArmorForge;

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:37 AM.
 * - TheDragonTeam
 */
public class GuiArmorForge extends GuiContainer {
    private static final ResourceLocation ARMOR_FORGE_GUI_TEXTURES = new ResourceLocation("armorplus:textures/gui/container/gui_armor_forge.png");

    public GuiArmorForge(InventoryPlayer playerInv, World worldIn, TileEntityArmorForge tileEntity) {
        this(playerInv, worldIn, BlockPos.ORIGIN, tileEntity);
    }

    public GuiArmorForge(InventoryPlayer playerInv, World worldIn, BlockPos blockPosition, TileEntityArmorForge tileEntity) {
        super(new ContainerArmorForge(playerInv, worldIn, blockPosition, tileEntity));
        this.xSize = 176;
        this.ySize = 165;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString(I18n.format("container.armorplus.armor_forge", new Object[0]), 28, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.armorplus.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(ARMOR_FORGE_GUI_TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}
