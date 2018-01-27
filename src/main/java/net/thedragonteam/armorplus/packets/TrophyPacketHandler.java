package net.thedragonteam.armorplus.packets;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class TrophyPacketHandler implements IMessageHandler<TrophyPacket, IMessage> {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("armorplus");

    @Override
    public IMessage onMessage(TrophyPacket message, MessageContext ctx) {
        // No response packet
        return null;
    }
}