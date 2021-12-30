package com.sofodev.armorplus.network;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.network.packet.PacketFlyingSync;
import net.minecraftforge.network.simple.SimpleChannel;

import static com.sofodev.armorplus.utils.Utils.setRL;

public class PacketHandler extends BasePacketHandler {

    private static final SimpleChannel netHandler = createChannel(setRL(ArmorPlus.MODID));

    @Override
    protected SimpleChannel getChannel() {
        return netHandler;
    }

    @Override
    public void initialize() {
        //Registers the flight packet, which is mandatory to making flight possible, let alone helps with compatibility with other mods
        registerServerToClient(PacketFlyingSync.class, PacketFlyingSync::encode, PacketFlyingSync::decode, PacketFlyingSync::handle);
    }
}