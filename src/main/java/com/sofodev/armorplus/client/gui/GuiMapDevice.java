package com.sofodev.armorplus.client.gui;

import com.sofodev.armorplus.common.container.ContainerMapDevice;
import com.sofodev.armorplus.common.tileentity.TileEntityMapDevice;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMapDevice extends GuiContainer {
    private static final ResourceLocation DEVICE_GUI_TEXTURES = new ResourceLocation("armorplus:textures/gui/container/map_device.png");
    private final TileEntityMapDevice tile;
    private GuiMapDevice.ConfirmButton confirmButton;

    public GuiMapDevice(InventoryPlayer playerInventory, TileEntityMapDevice tile) {
        super(new ContainerMapDevice(playerInventory, tile));
        this.tile = tile;
        this.xSize = 230;
        this.ySize = 219;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    @Override
    public void initGui() {
        super.initGui();
        this.confirmButton = new GuiMapDevice.ConfirmButton(-1, this.guiLeft + 164, this.guiTop + 107);
        this.buttonList.add(this.confirmButton);
        this.buttonList.add(new GuiMapDevice.CancelButton(-2, this.guiLeft + 190, this.guiTop + 107));
        this.confirmButton.enabled = false;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen() {
        super.updateScreen();
        this.confirmButton.enabled = !this.tile.getStackInSlot(0).isEmpty();
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == -2) {
            this.mc.player.connection.sendPacket(new CPacketCloseWindow(this.mc.player.openContainer.windowId));
            this.mc.displayGuiScreen(null);
        } else if (button.id == -1) {
            String channel = "AP|Map Device";
            PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
            packetbuffer.writeInt(this.tile.getField(1));
            packetbuffer.writeInt(this.tile.getField(2));
            this.mc.getConnection().sendPacket(new CPacketCustomPayload(channel, packetbuffer));
            this.mc.player.connection.sendPacket(new CPacketCloseWindow(this.mc.player.openContainer.windowId));
            this.mc.displayGuiScreen(null);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        RenderHelper.disableStandardItemLighting();
        this.drawCenteredString(this.fontRenderer,"Map Device", 62, 10, 14737632);

        for (GuiButton guibutton : this.buttonList) {
            if (guibutton.isMouseOver()) {
                guibutton.drawButtonForegroundLayer(mouseX - this.guiLeft, mouseY - this.guiTop);
                break;
            }
        }

        RenderHelper.enableGUIStandardItemLighting();
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(DEVICE_GUI_TEXTURES);
        int widthX = (this.width - this.xSize) / 2;
        int heightY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(widthX, heightY, 0, 0, this.xSize, this.ySize);
        this.itemRender.zLevel = 100.0F;
        this.itemRender.zLevel = 0.0F;
    }

    @SideOnly(Side.CLIENT)
    static class Button extends GuiButton {
        private final ResourceLocation iconTexture;
        private final int iconX;
        private final int iconY;
        private boolean selected;

        protected Button(int buttonId, int x, int y, ResourceLocation iconTextureIn, int iconXIn, int iconYIn) {
            super(buttonId, x, y, 22, 22, "");
            this.iconTexture = iconTextureIn;
            this.iconX = iconXIn;
            this.iconY = iconYIn;
        }

        /**
         * Draws this button to the screen.
         */
        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (this.visible) {
                mc.getTextureManager().bindTexture(GuiMapDevice.DEVICE_GUI_TEXTURES);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                int i = 219;
                int j = 0;

                if (!this.enabled) {
                    j += this.width * 2;
                } else if (this.selected) {
                    j += this.width;
                } else if (this.hovered) {
                    j += this.width * 3;
                }

                this.drawTexturedModalRect(this.x, this.y, j, 219, this.width, this.height);

                if (!GuiMapDevice.DEVICE_GUI_TEXTURES.equals(this.iconTexture)) {
                    mc.getTextureManager().bindTexture(this.iconTexture);
                }

                this.drawTexturedModalRect(this.x + 2, this.y + 2, this.iconX, this.iconY, 18, 18);
            }
        }

        public boolean isSelected() {
            return this.selected;
        }

        public void setSelected(boolean selectedIn) {
            this.selected = selectedIn;
        }
    }

    @SideOnly(Side.CLIENT)
    class CancelButton extends GuiMapDevice.Button {
        public CancelButton(int buttonId, int x, int y) {
            super(buttonId, x, y, GuiMapDevice.DEVICE_GUI_TEXTURES, 112, 220);
        }

        public void drawButtonForegroundLayer(int mouseX, int mouseY) {
            GuiMapDevice.this.drawHoveringText(I18n.format("gui.cancel"), mouseX, mouseY);
        }
    }

    @SideOnly(Side.CLIENT)
    class ConfirmButton extends GuiMapDevice.Button {
        public ConfirmButton(int buttonId, int x, int y) {
            super(buttonId, x, y, GuiMapDevice.DEVICE_GUI_TEXTURES, 90, 220);
        }

        public void drawButtonForegroundLayer(int mouseX, int mouseY) {
            GuiMapDevice.this.drawHoveringText(I18n.format("gui.done"), mouseX, mouseY);
        }
    }
}