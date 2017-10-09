package net.thedragonteam.armorplus.events;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = ArmorPlus.MODID)
public class ClientEventHandler {

    public static void onRender() {
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        //  RenderingRegistry.loadEntityRenderers(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderManager().entityRenderMap);
    }
}
