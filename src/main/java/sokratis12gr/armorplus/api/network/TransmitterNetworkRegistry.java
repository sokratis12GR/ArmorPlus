package sokratis12gr.armorplus.api.network;

import java.util.HashSet;

import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Sets;

public class TransmitterNetworkRegistry
{
    private static TransmitterNetworkRegistry INSTANCE = new TransmitterNetworkRegistry();
    private static boolean loaderRegistered = false;

    private HashSet<DynamicNetwork> networks = Sets.newHashSet();
    private HashSet<DynamicNetwork> networksToChange = Sets.newHashSet();


    private Logger logger = LogManager.getLogger("ArmorPlusTransmitters");

    public static void initiate()
    {
        if(!loaderRegistered)
        {
            loaderRegistered = true;

            MinecraftForge.EVENT_BUS.register(INSTANCE);
        }
    }

    public static void reset()
    {
        getInstance().networks.clear();
        getInstance().networksToChange.clear();
    }

    public static void registerChangedNetwork(DynamicNetwork network)
    {
        getInstance().networksToChange.add(network);
    }

    public static TransmitterNetworkRegistry getInstance()
    {
        return INSTANCE;
    }

    public void registerNetwork(DynamicNetwork network)
    {
        networks.add(network);
    }

    public void removeNetwork(DynamicNetwork network)
    {
        if(networks.contains(network))
        {
            networks.remove(network);
        }
    }

    @Override
    public String toString()
    {
        return "Network Registry:\n" + networks;
    }

    public String[] toStrings()
    {
        String[] strings = new String[networks.size()];
        int i = 0;

        for(DynamicNetwork network : networks)
        {
            strings[i++] = network.toString();
        }

        return strings;
    }

}