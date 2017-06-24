package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;

/**
 * Created by sokratis12GR on 6/21/2017.
 */
public class GuiAnalyticsDisclaimer extends GuiScreen {

    public GuiAnalyticsDisclaimer() {
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        int offset = Math.max(85, 10);
        this.drawCenteredString(this.fontRenderer, "ArmorPlus uses anonymous analytics to improve our mod", this.width / 2, offset, 0xFFFFFF);
        this.drawCenteredString(this.fontRenderer, "Do you want to Opt-In:", this.width / 2, offset + 25, 0xFFFFFF);
        this.buttonList.add(new GuiOptionButton(0, this.width / 2 - 155, this.height / 6 + 96, "Yes"));
        this.buttonList.add(new GuiOptionButton(1, this.width / 2 - 155 + 160, this.height / 6 + 96, "No"));
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
