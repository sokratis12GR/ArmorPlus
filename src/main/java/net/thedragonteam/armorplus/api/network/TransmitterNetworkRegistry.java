package net.thedragonteam.armorplus.api.network;

import java.util.HashSet;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.thedragonteam.armorplus.api.ArmorPlusAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Sets;

import static net.thedragonteam.armorplus.ARPConfig.debugMode;

public class TransmitterNetworkRegistry {
    private static TransmitterNetworkRegistry INSTANCE = new TransmitterNetworkRegistry();
    private static boolean loaderRegistered = false;

    private HashSet<DynamicNetwork> networks = Sets.newHashSet();
    private HashSet<DynamicNetwork> networksToChange = Sets.newHashSet();

    private HashSet<IGridTransmitter> invalidTransmitters = Sets.newHashSet();

    private Logger logger = LogManager.getLogger("ArmorPlusTransmitters");

    public static void initiate() {
        if (!loaderRegistered) {
            loaderRegistered = true;

            MinecraftForge.EVENT_BUS.register(INSTANCE);
        }
    }

    public static void reset() {
        getInstance().networks.clear();
        getInstance().networksToChange.clear();
        getInstance().invalidTransmitters.clear();
    }

    public static void invalidateTransmitter(IGridTransmitter transmitter) {
        getInstance().invalidTransmitters.add(transmitter);
    }

    public static void registerChangedNetwork(DynamicNetwork network) {
        getInstance().networksToChange.add(network);
    }

    public static TransmitterNetworkRegistry getInstance() {
        return INSTANCE;
    }

    public void registerNetwork(DynamicNetwork network) {
        networks.add(network);
    }

    public void removeNetwork(DynamicNetwork network) {
        if (networks.contains(network)) {
            networks.remove(network);
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.side == Side.SERVER) {
            tickEnd();
        }
    }

    public void tickEnd() {
        removeInvalidTransmitters();

        for (DynamicNetwork net : networks) {
            net.tick();
        }
    }

    public void removeInvalidTransmitters() {
        if (debugMode && !invalidTransmitters.isEmpty()) {
            logger.info("Dealing with " + invalidTransmitters.size() + " invalid Transmitters");
        }

        for (IGridTransmitter invalid : invalidTransmitters) {
            if (!(invalid.isOrphan() && invalid.isValid())) {
                DynamicNetwork n = invalid.getTransmitterNetwork();

                if (n != null) {
                    n.invalidate();
                }
            }
        }

        invalidTransmitters.clear();
    }

    @Override
    public String toString() {
        return "Network Registry:\n" + networks;
    }

    public String[] toStrings() {
        String[] strings = new String[networks.size()];
        int i = 0;

        for (DynamicNetwork network : networks) {
            strings[i++] = network.toString();
        }

        return strings;
    }
}