package net.thedragonteam.armorplus.client.gui.base;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.util.TextUtils;

import static java.lang.String.format;
import static net.thedragonteam.armorplus.util.Utils.setRL;

public class GuiBaseBench extends GuiContainer {

    public ResourceLocation resourceLocation;
    public String name;

    public GuiBaseBench(Container container, ResourceLocation resourceLocation, String benchName, int xSize, int ySize) {
        super(container);
        this.resourceLocation = resourceLocation;
        this.name = benchName;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public GuiBaseBench(Container container, String benchName, int xSize, int ySize) {
        this(container, setRL(format("textures/gui/container/gui_%s.png", benchName)), benchName, xSize, ySize);
    }

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
        this.fontRenderer.drawString(TextUtils.formattedText("container.armorplus." + name), 28, 5, 4210752);
        this.fontRenderer.drawString(TextUtils.formattedText("container.armorplus.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(resourceLocation);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}
