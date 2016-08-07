package sokratis12gr.armorplus.api.network;

import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.Event;
import sokratis12gr.armorplus.api.IClientTicker;


public abstract class DynamicNetwork<A, N extends DynamicNetwork<A, N>> implements IClientTicker {

    protected World worldObj = null;

    public void register() {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            TransmitterNetworkRegistry.getInstance().registerNetwork(this);
        } else {
            MinecraftForge.EVENT_BUS.post(new ClientTickUpdate(this, (byte) 1));
        }
    }

    public World getWorld() {
        return worldObj;
    }


    public static class ClientTickUpdate extends Event {
        public DynamicNetwork network;
        public byte operation; /*0 remove, 1 add*/

        public ClientTickUpdate(DynamicNetwork net, byte b) {
            network = net;
            operation = b;
        }
    }
}