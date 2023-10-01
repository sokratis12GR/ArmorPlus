package com.sofodev.armorplus.network;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.network.packet.PacketFlyingSyncPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.SimpleChannel;


public class PacketHandler extends BasePacketHandler {

    private static final SimpleChannel INSTANCE = createChannel(new ResourceLocation(ArmorPlus.MODID, "main"));
    @Override
    protected SimpleChannel getChannel() {
        return INSTANCE;
    }

    @Override
    public void initialize() {
        //Registers the flight packet, which is mandatory to making flight possible, let alone helps with compatibility with other mods
        registerServerToClient(PacketFlyingSyncPacket.class,
                PacketFlyingSyncPacket::encode,
                PacketFlyingSyncPacket::decode,
                PacketFlyingSyncPacket::handle);
    }
}