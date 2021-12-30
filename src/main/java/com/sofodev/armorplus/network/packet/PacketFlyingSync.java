package com.sofodev.armorplus.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketFlyingSync {

    private final boolean allowFlying;
    private final boolean isFlying;

    public PacketFlyingSync(boolean allowFlying, boolean isFlying) {
        this.allowFlying = allowFlying;
        this.isFlying = isFlying;
    }

    public static void handle(PacketFlyingSync message, Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        ctx.enqueueWork(() -> {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                player.getAbilities().mayfly = message.allowFlying;
                player.getAbilities().flying = message.isFlying;
            }
        });
        ctx.setPacketHandled(true);
    }

    public static void encode(PacketFlyingSync pkt, FriendlyByteBuf buf) {
        buf.writeBoolean(pkt.allowFlying);
        buf.writeBoolean(pkt.isFlying);
    }

    public static PacketFlyingSync decode(FriendlyByteBuf buf) {
        return new PacketFlyingSync(buf.readBoolean(), buf.readBoolean());
    }
}