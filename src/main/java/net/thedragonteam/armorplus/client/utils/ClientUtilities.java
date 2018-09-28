package net.thedragonteam.armorplus.client.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientUtilities {

    public static WorldClient world = Minecraft.getMinecraft().world;

    public static WorldClient getWorld() {
        return world;
    }
}
