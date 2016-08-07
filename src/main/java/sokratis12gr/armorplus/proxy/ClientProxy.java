package sokratis12gr.armorplus.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import sokratis12gr.armorplus.ArmorPlus;
import sokratis12gr.armorplus.client.ClientTickHandler;
import sokratis12gr.armorplus.registry.ModBlocks;
import sokratis12gr.armorplus.registry.ModItems;

import java.io.File;


public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        MinecraftForge.EVENT_BUS.register(new ClientTickHandler());
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public boolean isPaused()
    {
        if(FMLClientHandler.instance().getClient().isSingleplayer() && !FMLClientHandler.instance().getClient().getIntegratedServer().getPublic())
        {
            GuiScreen screen = FMLClientHandler.instance().getClient().currentScreen;

            if(screen != null && screen.doesGuiPauseGame())
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public File getMinecraftDir()
    {
        return Minecraft.getMinecraft().mcDataDir;
    }


    @Override
    public void registerRenderers(ArmorPlus ins) {
        ModItems.initModels();
        ModBlocks.registerRenders();
    }

    @Override
    public EntityPlayer getPlayer(MessageContext context)
    {
        if(FMLCommonHandler.instance().getEffectiveSide().isServer())
        {
            return context.getServerHandler().playerEntity;
        }
        else {
            return Minecraft.getMinecraft().thePlayer;
        }
    }

    @Override
    public void handlePacket(Runnable runnable, EntityPlayer player)
    {
        if(player == null || player.worldObj.isRemote)
        {
            Minecraft.getMinecraft().addScheduledTask(runnable);
        }
        else if(player != null && !player.worldObj.isRemote)
        {
            ((WorldServer)player.worldObj).addScheduledTask(runnable); //singleplayer
        }
    }
}


