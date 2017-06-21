package net.thedragonteam.armorplus.resources;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.client.gui.GuiAnalyticsDisclaimer;

@SideOnly(Side.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public void onInitGuiPost(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.getGui() instanceof GuiMainMenu) {
            event.getGui().mc.displayGuiScreen(new GuiAnalyticsDisclaimer());
        }
    }
}
