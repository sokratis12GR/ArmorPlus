package net.thedragonteam.armorplus.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by sokratis12GR on 7/1/2017.
 */
@SideOnly(Side.CLIENT)
public class ClientEventHandler {

    private void someting() {
        RenderingRegistry.loadEntityRenderers(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderManager().entityRenderMap);
    }
}
